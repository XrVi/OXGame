package com.imtsy.oxgames.UI;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private final int width;
    private final int height;
    private final int cellSize;
    private final int[][] boardState;
    private int oWins;
    private int xWins;
    private int draws;
    public Board(int width, int height, int cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        boardState = new int[3][3];
        for(int row=0;row<3;++row){
            for(int col=0;col<3;++col){
                boardState[row][col]=-1;
            }
        }
        // 显示高度
        setPreferredSize(new Dimension(width, height+40));
    }
    // 绘制 O 型符号
    private void drawO(Graphics g, int row, int col, int cellSize) {
        int x = col * cellSize;
        int y = row * cellSize;

        // 绘制圆形
        g.drawOval(x, y, cellSize, cellSize);
    }
    // 绘制 X 型符号
    private void drawX(Graphics g, int row, int col, int cellSize) {
        int x1 = col * cellSize;
        int y1 = row * cellSize;
        int x2 = x1 + cellSize;
        int y2 = y1 + cellSize;

        // 绘制斜线 /
        g.drawLine(x1, y1, x2, y2);

        // 绘制斜线 \
        g.drawLine(x1, y2, x2, y1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // 绘制背景
        g2d.setColor(new Color(167, 129, 102, 237)); // 设置背景颜色
        g2d.fillRect(0, 0, width, height); // 绘制填充的矩形作为背景

        // 绘制边框
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5)); // 设置边框粗细
        g2d.drawRect(0, 0, width - 1, height - 1);

        // 绘制网格线
        g2d.setColor(new Color(200, 200, 200)); // 设置网格线颜色
        for (int i = 1; i < 3; i++) {
            int y = i * height / 3;
            g2d.drawLine(0, y, width - 1, y);
        }
        for (int i = 1; i < 3; i++) {
            int x = i * width / 3;
            g2d.drawLine(x, 0, x, height - 1);
        }

        // 绘制棋子
        // 绘制 O 型棋
        g2d.setColor(Color.BLACK); // 设置 O 型棋的颜色
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardState[row][col] == 0) {
                    drawO(g2d, row, col, cellSize);
                }
            }
        }

        // 绘制 X 型棋
        g2d.setColor(Color.BLACK); // 设置 X 型棋的颜色
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(boardState[row][col] == 1){
                    drawX(g2d, row, col, cellSize);
                }
            }
        }

        // 绘制底部记录
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        String text = "O Wins: " + oWins + "  Draws: " + draws + "  X Wins: " + xWins;
        g2d.drawString(text, 10, height + 30);

        // 回收垃圾
        g2d.dispose();
    }
    public void placePiece(int row, int col, int currentplayer) {
        boardState[row][col] = currentplayer;
        repaint();
    }
    public int[][] getBoardState() {
        return boardState;
    }
    public int getCellSize(){
        return cellSize;
    }
    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardState[i][j] = -1;
            }
        }
        repaint();
    }
    public void incrementOWins() {
        oWins++;
        repaint();
    }

    public void incrementXWins() {
        xWins++;
        repaint();
    }

    public void incrementDraws() {
        draws++;
        repaint();
    }

    public void clearStatistics() {
        oWins = 0;
        xWins = 0;
        draws = 0;
        repaint();
    }
}