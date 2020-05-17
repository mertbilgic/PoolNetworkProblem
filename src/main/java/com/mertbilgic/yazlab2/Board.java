/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mertbilgic.yazlab2;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author mertbilgic
 */
public class Board extends JFrame {

    public Board(String title) throws HeadlessException {
        super(title);
    }

    public void createGUI(int graph[][]) {

        Board board = new Board("Grafik Kullanımı");
        board.setResizable(true);//Ekranın genişleyebilir olmasını engelliyoruz
        board.setFocusable(false);//JFrame odaklanmasını engelliyoruz.
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setSize(500, 400);

        GraphDraw frame = new GraphDraw("Graph Draw", 500, 400);
        frame.drawGraph(frame, graph);
        frame.setFocusable(true);
        frame.setSize(frame.frameHeight, frame.frameWidth);

        board.add(frame);
        board.setVisible(true);

    }
}
