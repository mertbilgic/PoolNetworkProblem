package com.mertbilgic.yazlab2;



/* Simple graph drawing class
Bert Huang
COMS 3137 Data Structures and Algorithms, Spring 2009

This class is really elementary, but lets you draw 
reasonably nice graphs/trees/diagrams. Feel free to 
improve upon it!
 */
//http://www1.cs.columbia.edu/~bert/courses/3137/hw3_files/GraphDraw.java
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphDraw extends JPanel {

    public int width;
    public int height;
    public int frameHeight;
    public int frameWidth;
    

    ArrayList<Node> nodes;
    ArrayList<edge> edges;

    public GraphDraw() { //Constructor
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 50;
        height = 50;
    }

    public GraphDraw(String name, int frameHeight, int frameWidth) { //Construct with label
        //this.setTitle(name);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 50;
        height = 50;
    }

    class Node {

        int x, y;
        String name;

        public Node(String myName, int myX, int myY) {
            x = myX;
            y = myY;
            name = myName;
        }
    }

    class edge {

        int i, j, x, y;

        public edge(int ii, int jj, int xx, int yy) {
            i = ii;
            j = jj;
            x = xx;
            y = yy;
        }
    }

    public void addNode(String name, int x, int y) {
        //add a node at pixel (x,y)
        nodes.add(new Node(name, x, y));
        this.repaint();
    }

    public void addEdge(int i, int j, int x, int y) {
        //add an edge between nodes i and j
        edges.add(new edge(i, j, x, y));
        this.repaint();
    }

    public void paint(Graphics g) { // draw the nodes and edges
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());

        g.setColor(Color.black);
        for (edge e : edges) {
            g.drawLine(nodes.get(e.i).x + e.x, nodes.get(e.i).y + e.y,
                    nodes.get(e.j).x + e.x, nodes.get(e.j).y + e.y);
            //g.drawString(nodes.get(e.), ERROR, ERROR);
        }

        for (Node n : nodes) {
            int nodeWidth = Math.max(width, f.stringWidth(n.name) + width / 2);
            g.setColor(Color.white);
            g.fillOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);
            g.setColor(Color.black);
            g.drawOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);

            g.drawString(n.name, n.x - f.stringWidth(n.name) / 2,
                    n.y + f.getHeight() / 2);
        }
    }

    public GraphDraw CreateFrame(GraphDraw frame, String frameName, int frameHeight, int frameWidth) {
        frame = new GraphDraw(frameName, frameHeight, frameWidth);
        frame.setSize(frameHeight, frameWidth);
        frame.setVisible(true);
        
        return frame;

    }

    public void drawGraph(GraphDraw frame, int graph[][]) {

        int node_start_x = frame.frameHeight / 8;
        int node_start_y = frame.frameWidth / 2;

        int x = node_start_x, y = node_start_y;

        int edgeStartCordinantX = 0, edgeStartCordinantY = 0;

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] > 0) {
                    if (graph[j][i] > 0 && i % 2 == 0) {
                        edgeStartCordinantX = 10;
                        edgeStartCordinantY = 10;
                    } else {
                        edgeStartCordinantX = -10;
                        edgeStartCordinantY = -10;
                    }
                    frame.addEdge(i, j, edgeStartCordinantX, edgeStartCordinantY);
                }
                edgeStartCordinantX = 0;
                edgeStartCordinantY = 0;
            }
            if (i != 0) {
                if (i % 2 == 0) {
                    y = node_start_y + 75;
                } else if (graph.length - 1 != i) {
                    y = node_start_y - 75;
                    x += 110;
                } else {
                    x += 110;
                    y = node_start_y;
                }
            }

            frame.addNode(String.valueOf(i), x, y);

        }
    }
}

