package com.imtsy.oxgames.AI;

import java.util.ArrayList;
import java.util.List;

public class GameNode {
    private int[][] board;
    private int currentPlayer;
    private List<GameNode> children;
    private int score;

    public GameNode(int[][] board, int currentPlayer){
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.children = new ArrayList<>();
        this.score = 0;
    }
    public int[][] getBoard(){
        return board;
    }
    public int getCurrentPlayer(){
        return this.currentPlayer;
    }
    public int getScore(){
        return this.score;
    }
    public List<GameNode> getChildren(){
        return this.children;
    }
    public void setPlayer(int i, int j, int currentPlayer){
        this.board[i][j] = currentPlayer;
    }
    public void setScore(int score){
        this.score = score;
    }
}
