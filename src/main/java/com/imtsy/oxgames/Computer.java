package com.imtsy.oxgames;

import com.imtsy.oxgames.AI.GameNode;
import com.imtsy.oxgames.AI.GameTree;
import com.imtsy.oxgames.UI.Board;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Computer extends Game{
    private GameTree tree;
    public Computer(GameTree tree){
        super();
        this.tree = tree;
    }
    public void AIPlacePiece(GameNode node){
        int minn = 10000;
        for(GameNode child: node.getChildren()){
            minn = Math.min(minn, child.getScore());
        }
        for(GameNode child: node.getChildren()){
            if(minn == child.getScore()){
                for(int i=0;i<3;++i){
                    for(int j=0;j<3;++j){
                        if(node.getBoard()[i][j]!=child.getBoard()[i][j]){
                            placePiece(i, j);
                            return ;
                        }
                    }
                }
            }
        }
    }
    public boolean AIPiece(GameNode node){
        if(node==null){
            return false;
        }
        for(GameNode child: node.getChildren()){
            if(AIPiece(child)){
                return true;
            }
        }
        int count=0;
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(node.getBoard()[i][j]==board.getBoardState()[i][j]){
                    count++;
                }
            }
        }
        if(count==9){
            AIPlacePiece(node);
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameTree tree = new GameTree();
            tree.buildTree(tree.getRoot(), 10);
            Computer computer = new Computer(tree);
            JFrame frame = new JFrame("OX Game");
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(computer.board);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


            // 启动游戏循环
            computer.startAIGameLoop();
        });
    }
    public void run(int row, int col){
        if(board.getBoardState()[row][col]!=-1) return ;
        if(placePiece(row, col)) return ;
        AIPiece(tree.getRoot());
    }
    public void startAIGameLoop(){
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // 根据点击位置计算行列索引
                int row = mouseY / board.getCellSize();
                int col = mouseX / board.getCellSize();

                // 启动游戏
                run(row, col);
            }
        });
    }
}
