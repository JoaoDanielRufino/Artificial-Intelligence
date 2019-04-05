package timetabling;

public class TimeGrid {
    private DNA dna;
    private double fitness;
    
    public TimeGrid(DNA dna) {
        this.dna = dna;
        this.fitness = 0;
    }
    
    public void calculateFitness() {
        int colisions;
        for(int i = 0; i < Timetabling.ROW_SIZE; i++) {
            for(int j = 0; j < Timetabling.PROF_QTY; j++) {
                colisions = 0;
                for(int k = j; k < Timetabling.COL_SIZE; k += Timetabling.PROF_QTY) {
                    colisions += this.dna.getGeneAt(i, k);
                }
                if(colisions != 0)
                    this.fitness += (Timetabling.TERM_QTY) / (colisions*colisions);
                else if(colisions == 1)
                    this.fitness += (Timetabling.TERM_QTY*Timetabling.TERM_QTY);
                else
                    this.fitness += Timetabling.TERM_QTY;
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
