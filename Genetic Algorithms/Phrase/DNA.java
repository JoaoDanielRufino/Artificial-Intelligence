package phrase;

import java.util.Random;

public class DNA {
    private char genes[];
    private int fitness;
    
    public DNA(int len){
        this.genes = generatePhrase(len);
        this.fitness = 0;
    }
    
    public DNA(char genes[]){
        this.genes = genes;
        this.fitness = 0;
    }
    
    private char[] generatePhrase(int len){
        Random rand = new Random();
        char tmp[] = new char[len];
        for(int i = 0; i < len; i++){
            tmp[i] = (char) (rand.nextInt(127 - 32) + 32); // random(32, 127)
        }
        return tmp;
    }
    
    public void calculateFitness(String target){
        int score = 0;
        for(int i = 0; i < this.genes.length; i++){
            if(this.genes[i] == target.charAt(i)){
                score++;
            }
        }
        this.fitness = (score * score);
    }
    
    public DNA crossover(DNA partner){
        Random rand = new Random();
        int mid = rand.nextInt(this.genes.length);
        char childGenes[] = new char[this.genes.length]; 
        
        for(int i = 0; i < this.genes.length; i++){
            childGenes[i] = i < mid ? this.genes[i] : partner.genes[i];
        }
        return new DNA(childGenes);
    }
    
    public void mutate(){
        Random rand = new Random();
        float mutationRate = 0.01f;
        
        for(int i = 0; i < this.genes.length; i++){
            if(rand.nextFloat() < mutationRate){
                char c = (char) (rand.nextInt(127 - 32) + 32);
                this.genes[i] = c;
            }
        }
    }
    
    public int getFitness(){
        return this.fitness;
    }
    
    public String getPhrase(){
        return new String(this.genes);
    }
}
