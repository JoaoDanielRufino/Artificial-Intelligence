package timetabling;

import java.util.Random;

/*
 *  The array of genes represent the following matrix using 3 professors and 2 terms:
 *      
 *           1st Term        |       2nd Term     
 *   |        Prof1         |        Prof2         |        Prof3         |        Prof1         |        Prof2         |        Prof3         |
 *  0|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|
 *  1|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|-1 or (rand(classQty))|
 *
 *  This is a 2x6 matrix
 *  The line indicates the time of the subject
 *  The column indicates the professor
 *  -1 means that the professor will not teach
 *  If not -1, the value indicates the classroom
 */
public class DNA {

    private int genes[][];

    public DNA() {
        this.genes = randomMatrix(Timetabling.ROW_SIZE, Timetabling.COL_SIZE, Timetabling.CLASS_QTY);
    }

    private DNA(int genes[][]) {
        this.genes = genes;
    }

    private int[][] randomMatrix(int rowSize, int colSize, int classQty) {
        int g[][] = new int[rowSize][colSize];

        Random rand = new Random();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (rand.nextInt(3) == 0) {  // 33.3% chance to not teach
                    g[i][j] = -1;  // -1 means that the professor will not teach
                } else {
                    g[i][j] = rand.nextInt(classQty);
                }
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
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                childGenes[i][j] = mid < j ? this.getGeneAt(i, j) : parent.getGeneAt(i, j);
            }
        }

        return new DNA(childGenes);
    }

    public void mutate() {
        float mutationRate = 0.01f;
        Random rand = new Random();
        for (int i = 0; i < this.genes.length; i++) {
            for (int j = 0; j < this.genes[i].length; j++) {
                if (rand.nextFloat() < mutationRate) {
                    if (rand.nextInt(3) == 0) {  
                        this.genes[i][j] = -1;  
                    } else {
                        this.genes[i][j] = rand.nextInt(Timetabling.CLASS_QTY);
                    }
                }
            }
        }
    }

    public void print() {
        int prof = 0;
        for (int i = 0; i < Timetabling.COL_SIZE; i++) {
            System.out.print("\tProf-" + prof + "\t");
            prof++;
            prof = prof % Timetabling.PROF_QTY;
        }

        System.out.println("");

        for (int i = 0; i < this.genes.length; i++) {
            System.out.print("Hour-" + i + "  ");
            for (int j = 0; j < this.genes[i].length; j++) {
                if (this.genes[i][j] == -1) {
                    System.out.print("NoClass" + "\t\t");
                } else {
                    System.out.print("Room-" + this.genes[i][j] + "\t\t");
                }
            }
            System.out.println("");
        }
    }

    public int getGeneAt(int row, int col) {
        return this.genes[row][col];
    }
}
