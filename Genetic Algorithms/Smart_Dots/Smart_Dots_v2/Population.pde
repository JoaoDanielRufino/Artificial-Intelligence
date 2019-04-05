class Population {
  private Dot[] dots;
  private boolean allDead;
  private int generations;

  public Population(int size) {
    this.dots = new Dot[size];
    for (int i = 0; i < size; i++) {
      this.dots[i] = new Dot(width/2, height-20, new DNA());
    }
    this.allDead = false;
    this.generations = 0;
  }

  public void calculateFitness() {
    for (Dot d : this.dots) {
      d.calculateFitness();
    }
  }

  private float getMaxFitness() {
    float max = 0;
    for (Dot d : this.dots) {
      float fitness = d.getFitness();
      max = fitness > max ? fitness : max; 
    }
    return max;
  }

  private DNA pickParent() {
    float maxFitness = getMaxFitness();
    
    for (Dot d : this.dots) {
      float fitness = map(d.getFitness(), 0, maxFitness, 0, 1);
      if (fitness > random(1))
        return d.getDNA();
    }
    
    return null;
  }
  

  public void newGeneration() {
    Dot[] d = new Dot[this.dots.length];

    for (int i = 0; i < this.dots.length; i++) {
      DNA parentA = pickParent();
      DNA parentB = pickParent();

      DNA child = parentA.crossover(parentB);
      child.mutate();
      
      d[i] = new Dot(width/2, height-20, child);
    }

    this.dots = d;
    this.allDead = false;
    this.generations++;
  }

  public void update() {
    int count = 0;
    for (Dot d : this.dots) {
      d.update();
      if (d.isDead())
        count++;
    }
    if (count == this.dots.length)
      this.allDead = true;
  }

  public void show() {
    for (Dot d : this.dots) {
      d.show();
    }
  }

  public boolean getAllDead() {
    return this.allDead;
  }

  public int getGeneration() {
    return this.generations;
  }
}
