package com.imtsy.oxgames.AI;

import com.imtsy.oxgames.UI.Board;

public class GameTree {
    private GameNode root;
    public GameTree(){
        int[][] board = new int[3][3];
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                board[i][j] = -1;
            }
        }
        root = new GameNode(board, 0);

    }
    public GameNode getRoot(){
        return this.root;
    }
    public boolean isGameOver(int[][] board){
        // 检查每一行是否有相同棋子
        for(int col = 0;col<3;col++){
            if(board[0][col]!=-1 && board[0][col]==board[1][col] && board[1][col]==board[2][col])
            {
                return true;
            }
        }
        // 检查每一列是否有相同棋子
        for(int row=0;row<3;row++){
            if(board[row][0]!=-1 && board[row][0]==board[row][1] && board[row][1]==board[row][2]){
                return true;
            }
        }
        // 检查对角线是否有相同棋子
        if(board[0][0]!=-1&&board[0][0]==board[1][1]&&board[1][1]==board[2][2]){
            return true;
        }
        if(board[0][2]!=-1&&board[0][2]==board[1][1]&&board[1][1]==board[2][0]){
            return true;
        }
        return false;
    }
    // 递归构建博弈树
    int k = 0;
    public void buildTree(GameNode node, int depth){
        if(isGameOver(node.getBoard()) || depth==0){
            node.setScore(evaluateScore(node.getBoard()));
            return ;
        }

        int currentPlayer = node.getCurrentPlayer();
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(node.getBoard()[i][j]==-1){
                    node.getBoard()[i][j] = currentPlayer;
                    int[][] newBoard = copyNode(node.getBoard());

                    GameNode childNode = new GameNode(newBoard, 1-currentPlayer);
                    node.getChildren().add(childNode);
                    buildTree(childNode, depth-1);
                    node.getBoard()[i][j] = -1;

                    if(currentPlayer == 0){
                        node.setScore(Math.max(node.getScore(), childNode.getScore()));
                    }
                    else{
                        node.setScore(Math.min(node.getScore(), childNode.getScore()));
                    }
                }
            }
        }
    }
    public int[][] copyNode(int[][] board){
        int[][] newBoard = new int[3][3];
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
    public int evaluateScore(int[][] board){
        int score = 0;

        // 检查每一行
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == 0 && board[row][1] == 0 && board[row][2] == 0) {
                score += 100; // 玩家0获胜
            } else if (board[row][0] == 1 && board[row][1] == 1 && board[row][2] == 1) {
                score -= 100; // 玩家1获胜
            }
        }

        // 检查每一列
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == 0 && board[1][col] == 0 && board[2][col] == 0) {
                score += 100; // 玩家0获胜
            } else if (board[0][col] == 1 && board[1][col] == 1 && board[2][col] == 1) {
                score -= 100; // 玩家1获胜
            }
        }

        // 检查对角线
        if ((board[0][0] == 0 && board[1][1] == 0 && board[2][2] == 0) ||
                (board[0][2] == 0 && board[1][1] == 0 && board[2][0] == 0)) {
            score += 100; // 玩家0获胜
        } else if ((board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) ||
                (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1)) {
            score -= 100; // 玩家1获胜
        }

        return score;
    }
    public void printTree(GameNode node){
        if(node == null){
            return ;
        }
        for(GameNode child: node.getChildren()){
            printTree(child);
        }
//        System.out.println(node.getScore());
        System.out.println("==========");
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                System.out.print(node.getBoard()[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.println("==========");
    }
    public static void main(String[] args) {
        GameTree tree = new GameTree();
        tree.buildTree(tree.getRoot(), 1);
        tree.printTree(tree.getRoot());
        System.out.println(tree.k);
    }
}
