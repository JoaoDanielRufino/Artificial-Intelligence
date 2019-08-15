package timetabling;

public class Timetabling {
    public static final int POP_SIZE = 20;
    public static final int HOURS = 5;
    public static final int TERM_QTY = 3;
    public static final int PROF_QTY = 4;
    public static final int CLASS_QTY = 7;
    public static final int ROW_SIZE = HOURS;
    public static final int COL_SIZE = (TERM_QTY * PROF_QTY);

    public static void main(String[] args) {
        Population p = new Population(POP_SIZE);
        
        while(!p.getDone()) {
            p.calculateFitness();
            p.showBest();
            p.naturalSelection();
            p.newGeneration();
        }
    }
    
}
