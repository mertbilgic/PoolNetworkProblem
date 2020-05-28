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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;

public class GraphDraw extends JPanel {

    public int width;
    public int height;
    public int frameHeight;
    public int frameWidth;
    public String source;
    public String sink;
    BufferedImage image ;
    ArrayList<Node> nodes;
    ArrayList<edge> edges;
    public int [] e1;
    public int [] e2;
    boolean[] visit;
    public int[][] g;

    public GraphDraw() { //Constructor
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nodes = new ArrayList<Node>();
        edges = new ArrayList<edge>();
        width = 50;
        height = 50;
        
    }
  
    public GraphDraw(String name, int frameHeight, int frameWidth) { try {
        //Construct with label
        //this.setTitle(name);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        image = ImageIO.read(new FileImageInputStream( new File("img/pool.png")));
        } catch (IOException ex) {
            Logger.getLogger(GraphDraw.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        int i, j, x, y, c;

        public edge(int ii, int jj, int xx, int yy, int cc) {
            i = ii;
            j = jj;
            x = xx;
            y = yy;
            c = cc;
        }
    }

    public void addNode(String name, int x, int y) {
        //add a node at pixel (x,y)
        nodes.add(new Node(name, x, y));
        this.repaint();
    }

    public void addEdge(int i, int j, int x, int y, int cost) {
        //add an edge between nodes i and j
        edges.add(new edge(i, j, x, y,cost));
        this.repaint();
    }
    
   

    public void paint(Graphics g) { // draw the nodes and edges
       
        
        FontMetrics f = g.getFontMetrics();
        int nodeHeight = Math.max(height, f.getHeight());
        g.setFont(new Font("default", Font.BOLD, 16));
        for (edge e : edges) {
            g.setColor(Color.black);
            g.drawLine(nodes.get(e.i).x + e.x, nodes.get(e.i).y + e.y, nodes.get(e.j).x + e.x, nodes.get(e.j).y + e.y);
           
            String test = String.valueOf(e.c);
            g.setColor(Color.WHITE);
            g.drawString(test, (nodes.get(e.i).x + nodes.get(e.j).x + 2*e.x)/2, (nodes.get(e.i).y + nodes.get(e.j).y + 2*e.y)/2);
     /* for(int p=0; p< e1[p];p++){
           for(int k=0;k< e2[k];k++){
            if(e.i == e1[p]){
            g.setColor(Color.red);
            g.drawLine(nodes.get(e.i).x + e.x, nodes.get(e.i).y + e.y,
                    nodes.get(e.j).x + e.x, nodes.get(e.j).y + e.y);
        }
            else if(e.i == e2[k]){
            g.setColor(Color.red);
            g.drawLine(nodes.get(e.i).x + e.x, nodes.get(e.i).y + e.y,
                    nodes.get(e.j).x + e.x, nodes.get(e.j).y + e.y);
            }
            
           }
           
            } */
           
       
        }
        
       
        

        for (Node n : nodes) {
            if(source.equals(n.name))
                g.setColor(Color.PINK);
            else if(sink.equals(n.name))
                g.setColor(Color.orange);
            else
                g.setColor(Color.WHITE);
            int nodeWidth = Math.max(width, f.stringWidth(n.name) + width / 2);
            g.fillOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);
            g.setColor(Color.black);
            g.drawOval(n.x - nodeWidth / 2, n.y - nodeHeight / 2,
                    nodeWidth, nodeHeight);

            g.drawString(n.name, n.x - f.stringWidth(n.name) / 2,
                    n.y + f.getHeight() / 2);
            if(sink.equals(n.name)){
                int nodeCount = Integer.parseInt(n.name);
                int k = 1;
                if(nodeCount%2 == 1 && nodes.size()-1 != nodeCount)
                    k = -3;
                g.drawImage(image, n.x-135, n.y+60*k, this);
            }
                
                
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

        int x = node_start_x+120, y = node_start_y;

        int edgeStartCordinantX = 0, edgeStartCordinantY = 0;
        
        int V = graph.length;
        
        boolean changeNode[][] = new boolean[V][V];
        
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] > 0) {
                    if (!changeNode[j][i]) {
                        changeNode[i][j] = true;
                        edgeStartCordinantX = 10;
                        edgeStartCordinantY = 10;                      
                    } else {
                        edgeStartCordinantX = -10;
                        edgeStartCordinantY = -10;
                    }
                    frame.addEdge(i, j, edgeStartCordinantX, edgeStartCordinantY,graph[i][j]);
                }
            }
            if (i != 0) {
                if (i % 2 == 0) {
                    y = node_start_y + 100;
                } else if (graph.length - 1 != i) {
                    y = node_start_y - 100;
                    x += 140;
                } else {
                    x += 140;
                    y = node_start_y;
                }
            }

            frame.addNode(String.valueOf(i), x, y+120);

        }
        
         
    }
   
}

