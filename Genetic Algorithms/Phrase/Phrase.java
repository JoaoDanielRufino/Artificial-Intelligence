package phrase;

public class Phrase {

    public static void main(String[] args) {
        Population pop = new Population("Genetic Algorithm", 300);
        while(!pop.getFinished()){
            pop.calculateFitness();
            System.out.println(pop.getBest());
            System.out.println(pop.getGenerations());
            pop.naturalSelection();
            pop.newGeneration();    
        }
    }
    
}
