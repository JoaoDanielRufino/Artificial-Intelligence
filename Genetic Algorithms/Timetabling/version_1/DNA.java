package timetabling;

import java.util.Random;

/*
 *  The array of genes represent the following matrix using 3 professors and 2 terms:
 *      
 *           1st Term        |       2nd Term     
 *   | Prof1 | Prof2 | Porf3 | Prof1 | Prof2 | Porf3 |
 *  0| 0 or 1| 0 or 1| 0 or 1| 0 or 1| 0 or 1| 0 or 1|
 *  1| 0 or 1| 0 or 1| 0 or 1| 0 or 1| 0 or 1| 0 or 1|
 *
 *  This is a 2x6 matrix
 *  The line indicates the time of the subject
 *  The column indicates the professor
 */

public class DNA {   
    private int genes[][];
    
    public DNA() {
        this.genes = randomMatrix(Timetabling.ROW_SIZE, Timetabling.COL_SIZE);
    }
    
    private DNA(int genes[][]) {
        this.genes = genes;
    }
    
    private int[][] randomMatrix(int rowSize, int colSize) {
        int g[][] = new int[rowSize][colSize];
        
        Random rand = new Random();
        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize; j++) {
                g[i][j] = rand.nextInt(2);
            }
        }       
        return g;
    }
    
    public DNA crossover(DNA parent) {
        int rowSize = this.genes.length;
        int colSize = this.genes[0].length;
        int childGenes[][] = new int[rowSize][colSize];
        
        Random rand = new Random();
        int mid = rand.nextInt(colSize);
        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize; j++) {
                childGenes[i][j] = mid < j ? this.getGeneAt(i, j) : parent.getGeneAt(i, j);
            }
        }
        
        return new DNA(childGenes);
    }
    
    public void mutate() {
        float mutationRate = 0.01f;
        Random rand = new Random();
        for(int i = 0; i < this.genes.length; i++) {
            for(int j = 0; j < this.genes[i].length; j++) {
                if(rand.nextFloat() < mutationRate) {
                    this.genes[i][j] = this.genes[i][j] == 1 ? 0 : 1;
                }
            }
        }
    }
    
    public void print() {
        int prof = 0;
        for(int i = 0; i < Timetabling.COL_SIZE; i++) {
            System.out.print("Prof-" + prof + "  ");
            prof++;
            prof = prof%Timetabling.PROF_QTY;
        }
        
        System.out.println("");
        
        for(int i = 0; i < this.genes.length; i++) {
            for(int j = 0; j < this.genes[i].length; j++) {
                System.out.print(this.genes[i][j] + "\t");
            }
            System.out.println("");
        }
    }
    
    public int getGeneAt(int row, int col) {
        return this.genes[row][col];
    }
}
