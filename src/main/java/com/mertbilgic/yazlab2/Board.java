/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mertbilgic.yazlab2;

import java.awt.HeadlessException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author mertbilgic
 */
//https://www.javatpoint.com/java-joptionpane
public class Board extends JFrame {

    public Board(String title) throws HeadlessException {
        super(title);
    }

    public void createGUI(int graph[][], String sink, String source,String message) {

        Board board = new Board("Graph Draw");
        board.setResizable(true);//Ekranın genişleyebilir olmasını engelliyoruz
        board.setFocusable(false);//JFrame odaklanmasını engelliyoruz.
        board.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        board.setSize(800, 650);

        GraphDraw frame = new GraphDraw("Graph Draw", 500, 400,message);
        frame.sink = sink;
        frame.source = source;
        frame.drawGraph(frame, graph);
        frame.setFocusable(true);
        frame.setSize(frame.frameHeight, frame.frameWidth);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        board.add(frame);
        board.setVisible(true);

    }

    public static void flashMessage(String message) {

        JOptionPane.showMessageDialog(null, message);

    }
}
