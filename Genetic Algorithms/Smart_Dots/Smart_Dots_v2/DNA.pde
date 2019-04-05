class DNA {
  private PVector[] genes;
  private final int SIZE = 800;

  public DNA() {
    this.genes = new PVector[SIZE];
    for (int i = 0; i < SIZE; i++) {
      this.genes[i] = PVector.random2D();
    }
  }
  
  private DNA(PVector[] genes) {
    this.genes = genes;
  }

  public DNA crossover(DNA parent) {
    PVector[] childGenes = new PVector[SIZE];
    int mid = (int) random(SIZE);
    for (int i = 0; i < SIZE; i++) {
      childGenes[i] = mid > i ? this.genes[i] : parent.geneAt(i);
    }
    return new DNA(childGenes);
  }

  public void mutate() {
    float mutationRate = 0.01;
    for (int i = 0; i < SIZE; i++) {
      if (random(1) < mutationRate)
        this.genes[i] = PVector.random2D();
    }
  }

  private PVector geneAt(int index) {
    return this.genes[index];
  }

  public DNA clone() {
    return this; 
  }

  public PVector getPVector(int i) {
    return this.genes[i];
  }

  public int getSize() {
    return SIZE;
  }
}
