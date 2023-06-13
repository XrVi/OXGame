package com.imtsy.oxgames.UI;

import com.imtsy.oxgames.AI.GameTree;
import com.imtsy.oxgames.Computer;
import com.imtsy.oxgames.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame {
    private JButton btnPlayerVsPlayer;
    private JButton btnPlayerVsComputer;
    private JButton btnExit;

    public StartMenu() {
        // 设置窗口大小和布局
        setSize(400, 300);
        setLayout(new BorderLayout());

        // 设置面板边距和背景颜色
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        // 设置按钮样式
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonTextColor = Color.BLACK;
        Color buttonBackgroundColor = new Color(2, 64, 107);

        // 创建按钮
        btnPlayerVsPlayer = new JButton("人人对战");
        btnPlayerVsPlayer.setFont(buttonFont);
        btnPlayerVsPlayer.setForeground(buttonTextColor);
        btnPlayerVsPlayer.setBackground(buttonBackgroundColor);
        btnPlayerVsPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 处理人人对战逻辑
                SwingUtilities.invokeLater(() -> {
                    Game game = new Game();

                    JFrame frame = new JFrame("OX Game");
                    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(game.getBoard());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);


                    // 启动游戏循环
                    game.startGameLoop();
                });
            }
        });

        btnPlayerVsComputer = new JButton("人机对战");
        btnPlayerVsComputer.setFont(buttonFont);
        btnPlayerVsComputer.setForeground(buttonTextColor);
        btnPlayerVsComputer.setBackground(buttonBackgroundColor);
        btnPlayerVsComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 处理人机对战逻辑
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
        });

        btnExit = new JButton("退出游戏");
        btnExit.setFont(buttonFont);
        btnExit.setForeground(buttonTextColor);
        btnExit.setBackground(buttonBackgroundColor);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 将按钮添加到面板中
        panel.add(btnPlayerVsPlayer);
        panel.add(btnPlayerVsComputer);
        panel.add(btnExit);

        // 将面板添加到窗口中
        add(panel, BorderLayout.CENTER);

        // 设置窗口属性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中显示
        setUndecorated(true); // 去除窗口标题栏和边框
        getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG); // 自定义窗口装饰样式
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartMenu();
            }
        });
    }
}
