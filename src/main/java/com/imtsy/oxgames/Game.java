package com.imtsy.oxgames;

import com.imtsy.oxgames.UI.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {
    public Board board;
    public int currentplayer;
    public Game(){
        board = new Board(300, 300, 100);
        currentplayer = 0;
    }
    public boolean placePiece(int row, int col){
        if(board.getBoardState()[row][col] != -1) return false;
        // 玩家下棋
        board.placePiece(row, col, currentplayer);
        // 切换玩家
        currentplayer = 1 - currentplayer;
        return checkGameStatus();
    }

    public boolean checkGameStatus(){
        int Winner = checkForWinner(board.getBoardState());
        if(Winner!=-1){
            displayWinner(Winner);
            currentplayer = 0;
            board.clear();
            return true;
        }
        else if(isFullBoard(board.getBoardState())){
            displayDraw();
            currentplayer = 0;
            board.clear();
            return true;
        }
        return false;
    }
    private int checkForWinner(int[][] boardState){
        // 检查每一行是否有相同棋子
        for(int col = 0;col<3;col++){
            if(boardState[0][col]!=-1 && boardState[0][col]==boardState[1][col] && boardState[1][col]==boardState[2][col])
            {
                return boardState[0][col];
            }
        }
        // 检查每一列是否有相同棋子
        for(int row=0;row<3;row++){
            if(boardState[row][0]!=-1 && boardState[row][0]==boardState[row][1] && boardState[row][1]==boardState[row][2]){
                return boardState[row][0];
            }
        }
        // 检查对角线是否有相同棋子
        if(boardState[0][0]!=-1&&boardState[0][0]==boardState[1][1]&&boardState[1][1]==boardState[2][2]){
            return boardState[0][0];
        }
        if (boardState[0][2]!=-1&&boardState[0][2]==boardState[1][1]&&boardState[1][1]==boardState[2][0]){
            return boardState[0][2];
        }
        return -1;
    }
    public void displayWinner(int winner){
        String messageO = "O 棋子获胜！";
        String messageX = "X 棋子获胜！";
        String title = "游戏结果";
        if(winner==0){
             JOptionPane.showMessageDialog(board, messageO, title, JOptionPane.INFORMATION_MESSAGE);
            board.incrementOWins();
        }
        else if(winner==1){
             JOptionPane.showMessageDialog(board, messageX, title, JOptionPane.INFORMATION_MESSAGE);
            board.incrementXWins();
        }
    }
    public boolean isFullBoard(int[][] boardState){
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(boardState[i][j]==-1) return false;
            }
        }
        return true;
    }
    public void displayDraw(){
        String message = "平局！";
        String title = "游戏结果";
        JOptionPane.showMessageDialog(board, message, title, JOptionPane.INFORMATION_MESSAGE);
        board.incrementDraws();
    }
    public Board getBoard(){
        return this.board;
    }
    public void startGameLoop() {
        // TODO: 鼠标点击
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // 根据点击位置计算行列索引
                int row = mouseY / board.getCellSize();
                int col = mouseX / board.getCellSize();

                // 调用 placePiece 方法下棋
                placePiece(row, col); // 假设 currentPlayer 表示当前玩家
            }
        });
    }
}