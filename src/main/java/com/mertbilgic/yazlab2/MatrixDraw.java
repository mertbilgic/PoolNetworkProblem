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

/**
 *
 * @author mertbilgic
 */
public class MatrixDraw extends JPanel {

    private static final int GAP = 1;
    private static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 24);
    private int graph[][] ;
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
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btn);

        setLayout(new BorderLayout());
        add(matrix, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);

        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                for (int row = 0; row < grid.length; row++) {
                    for (int col = 0; col < grid[row].length; col++) {
                        graph[row][col] = Integer.parseInt(grid[row][col].getText().trim().toString());
                        //System.out.println(""+);
                    }
                }
            }
        });
    }

    public void createAndShowGui(MatrixDraw mainPanel) {

        JFrame frame = new JFrame("Matrix Draw");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

    
}
