package timetabling;

import java.util.ArrayList;
import java.util.Random;

public class Population {

    private TimeGrid[] timeGrids;
    private ArrayList<TimeGrid> matingPool;
    private int generation;
    private boolean done;

    public Population(int size) {
        this.timeGrids = new TimeGrid[size];
        for (int i = 0; i < size; i++) {
            this.timeGrids[i] = new TimeGrid(new DNA());
        }
        this.matingPool = new ArrayList<>();
        this.generation = 0;
        this.done = false;
    }

    public void calculateFitness() {
        for (TimeGrid t : this.timeGrids) {
            t.calculateFitness();
        }
    }

    public double getMaxFitness() {
        double max = 0;
        for (TimeGrid t : this.timeGrids) {
            double fitness = t.getFitness();
            max = fitness > max ? fitness : max;
        }
        return max;
    }

    public void naturalSelection() {
        double maxFitness = this.getMaxFitness();

        this.matingPool.clear();
        for (TimeGrid t : this.timeGrids) {
            double fitnessNormalized = t.getFitness() / maxFitness; // Normalizing in a range of 0 - 1
            int n = (int) fitnessNormalized * 100; // 0% - 100%
            for (int i = 0; i < n; i++) {
                this.matingPool.add(t);
            }
        }
    }

    public void newGeneration() {
        TimeGrid[] newTimeGrid = new TimeGrid[this.timeGrids.length];

        Random rand = new Random();
        for (int i = 0; i < this.timeGrids.length; i++) {
            int a = rand.nextInt(this.matingPool.size());
            int b = rand.nextInt(this.matingPool.size());

            DNA parentA = this.matingPool.get(a).getDNA();
            DNA parentB = this.matingPool.get(b).getDNA();

            DNA child = parentA.crossover(parentB);
            child.mutate();

            newTimeGrid[i] = new TimeGrid(child);
        }

        this.timeGrids = newTimeGrid;
        this.generation++;
    }

    public void showBest() {
        double max = 0;
        TimeGrid best = null;

        for (TimeGrid t : this.timeGrids) {
            double fitness = t.getFitness();
            if (fitness > max) {
                max = fitness;
                best = t;
            }
        }

        if (best != null) {
            best.getDNA().print();
            System.out.println("Fitness: " + best.getFitness());
            System.out.println("Generation: " + this.generation);
            if (best.getFitness() == (Timetabling.ROW_SIZE * Timetabling.PROF_QTY * Timetabling.TERM_QTY * Timetabling.TERM_QTY * 5)) {
                this.done = true;
            }
        }
    }
    
    public boolean getDone() {
        return this.done;
    }
}
