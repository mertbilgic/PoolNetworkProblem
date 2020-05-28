/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mertbilgic.yazlab2;

/**
 *
 * @author mertbilgic
 */
//https://stackoverflow.com/questions/2832472/how-to-return-2-values-from-a-java-method
public class Result {
    private int step;
    private int[][] graph;
   
    
    public Result(int step, int[][] graph) {
        this.step = step;
        this.graph = graph;
    }


   
    public int getStep() {
        return step;
    }

    public int[][] getGraph() {
        return graph;
    }
}

