package timetabling;

public class Timetabling {
    public static final int GEN_SIZE = 30;
    public static final int POP_SIZE = 10;
    public static final int HOURS = 4;
    public static final int TERM_QTY = 3;
    public static final int PROF_QTY = 4;
    public static final int ROW_SIZE = HOURS;
    public static final int COL_SIZE = (TERM_QTY * PROF_QTY);

    public static void main(String[] args) {
        Population p = new Population(POP_SIZE);
        
        for(int i = 0; i < GEN_SIZE; i++) {
            p.calculateFitness();
            p.showBest();
            p.naturalSelection();
            p.newGeneration();
        }
    }
    
}
