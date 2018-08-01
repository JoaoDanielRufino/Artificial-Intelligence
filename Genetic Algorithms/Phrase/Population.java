package phrase;

import java.util.ArrayList;
import java.util.Random;

public class Population {
    private ArrayList<DNA> dnas;
    private String target;
    private ArrayList<DNA> matingPool;
    private int generations;
    private boolean finished;
    
    public Population(String target, int popSize){
        this.dnas = new ArrayList();
        for(int i = 0; i < popSize; i++){
            this.dnas.add(new DNA(target.length()));
        }
        this.target = target;
        this.matingPool = new ArrayList();
        this.generations = 0;
        this.finished = false;
    }
    
    public void calculateFitness(){
        for(int i = 0; i < this.dnas.size(); i++){
            this.dnas.get(i).calculateFitness(this.target);
        }
    }
    
    public void naturalSelection(){
        int maxFitness = 0;
        for(DNA dna: this.dnas){
            maxFitness = dna.getFitness() > maxFitness ? dna.getFitness() : maxFitness;
        }
        
        this.matingPool.clear();
        for(int i = 0; i < this.dnas.size(); i++){
            float normalizedFitness = this.dnas.get(i).getFitness() / maxFitness;
            int n = (int) (normalizedFitness * 100); // Normalizing in a range of 0% - 100% of fitness
            for(int j = 0; j < n; j++){
                this.matingPool.add(this.dnas.get(i));
            }
        }
    }
    
    public void newGeneration(){
        int size = this.dnas.size();
        this.dnas.clear();
        
        Random rand = new Random();
        for(int i = 0; i < size; i++){    
            int a = rand.nextInt(this.matingPool.size());
            int b = rand.nextInt(this.matingPool.size());
            DNA partnerA = this.matingPool.get(a);
            DNA partnerB = this.matingPool.get(b);
            DNA child = partnerA.crossover(partnerB);
            child.mutate();
            this.dnas.add(child);
        }
        this.generations++;
    }
    
    public String getBest(){
        int max = 0;
        int index = -1;
        for(int i = 0; i < this.dnas.size(); i++){
            if(this.dnas.get(i).getFitness() > max){
                max = this.dnas.get(i).getFitness();
                index = i;
            }
        }
        if(this.dnas.get(index).getPhrase().equals(this.target))
            this.finished = true;
        if(index != -1)
            return this.dnas.get(index).getPhrase();
        else
            return null;
    }
    
    public int getGenerations(){
        return this.generations;
    }
    
    public boolean getFinished(){
        return this.finished;
    }
}
