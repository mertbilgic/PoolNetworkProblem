/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mertbilgic.yazlab2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
//https://stackoverflow.com/questions/36681289/displaying-jlabel-matrix
//https://stackoverflow.com/questions/11745391/java-how-to-flash-jpanel-window
//https://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing
//https://stackoverflow.com/questions/5064393/using-loop-to-get-values-from-jtextfields

/**
 *
 * @author mertbilgic
 */
public class MatrixDraw extends JPanel {

    private static final int GAP = 1;
    private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 24);
    private int graph[][] = null;
    private JFrame frame;

    int staticGraph[][] = new int[][]{
        {0, 16, 13, 0, 0, 0},
        {0, 0, 10, 12, 0, 0},
        {0, 4, 0, 0, 14, 0},
        {0, 0, 9, 0, 0, 20},
        {0, 0, 0, 7, 0, 4},
        {0, 0, 0, 0, 0, 0}
    };

    public MatrixDraw(int nodeCount) {
        graph = new int[nodeCount][nodeCount];

        JTextField[][] grid = new JTextField[nodeCount][nodeCount];
        JPanel matrix = new JPanel(new GridLayout(nodeCount, nodeCount, GAP, GAP));

        matrix.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        matrix.setBackground(Color.BLACK);
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new JTextField("       ", SwingConstants.CENTER);
                grid[row][col].setFont(LABEL_FONT); // make it big
                grid[row][col].setOpaque(true);
                grid[row][col].setBackground(Color.WHITE);

                matrix.add(grid[row][col]);
            }
        }
        JButton btn = new JButton("Save");
        JButton btn2 = new JButton("Get Static Graph");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btn);
        bottomPanel.add(btn2);

        setLayout(new BorderLayout());
        add(matrix, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                insertGraphData(grid);
                frame.setVisible(false);
            }
        });

        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                insertStaticGraph(grid, staticGraph);
            }
        });
    }

    public void createAndShowGui(MatrixDraw mainPanel) {

        frame = new JFrame("Matrix Draw");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void insertGraphData(JTextField[][] grid) {

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                try{
                    graph[row][col] = Integer.parseInt(grid[row][col].getText().trim().toString());

                }catch(Exception ex){
                    graph[row][col] = 0;
                }
            }
        }

    }

    public void insertStaticGraph(JTextField[][] grid, int graph[][]) {
        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                grid[row][col].setText(String.valueOf(graph[row][col]));
            }
        }

    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

}
