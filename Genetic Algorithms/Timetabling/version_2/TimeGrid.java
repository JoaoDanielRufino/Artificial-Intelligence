package timetabling;

import java.util.HashSet;
import java.util.Set;

public class TimeGrid {

    private DNA dna;
    private double fitness;

    public TimeGrid(DNA dna) {
        this.dna = dna;
        this.fitness = 0;
    }

    private int checkUniqueProfessorClass(int row, int col) {
        int colisions = 0;

        for (int k = col; k < Timetabling.COL_SIZE; k += Timetabling.PROF_QTY) {
            if (this.dna.getGeneAt(row, k) != -1) {
                colisions++;
            }
        }

        return colisions;
    }

    private int checkSameRoom(int row) {
        int colisions = 0;
        Set<Integer> mySet = new HashSet<>();
        
        for (int j = 0; j < Timetabling.COL_SIZE; j++) {
            if (this.dna.getGeneAt(row, j) != -1) {
                if (!mySet.add(this.dna.getGeneAt(row, j))) {  // There is a repeated element
                    colisions++;
                }
            }
        }
        
        mySet.clear();
        return colisions;
    }

    public void calculateFitness() {
        for (int i = 0; i < Timetabling.ROW_SIZE; i++) {
            int roomColision = checkSameRoom(i);
            for (int j = 0; j < Timetabling.PROF_QTY; j++) {
                int hourColision = checkUniqueProfessorClass(i, j);

                if (hourColision == 1 && roomColision == 0) {
                    this.fitness += (Timetabling.TERM_QTY * Timetabling.TERM_QTY)*5;
                } else if (roomColision > 1 && hourColision > 1) {
                    this.fitness += (Timetabling.TERM_QTY) / (roomColision*hourColision);
                } else {
                    this.fitness += Timetabling.TERM_QTY;
                }
            }
        }
    }

    public DNA getDNA() {
        return this.dna;
    }

    public double getFitness() {
        return this.fitness;
    }
}
