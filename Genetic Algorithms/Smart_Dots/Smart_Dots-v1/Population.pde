class Population {
  private Dot[] dots;
  private ArrayList<Dot> matingPool;
  private boolean allDead;
  private int generations;

  public Population(int size) {
    this.dots = new Dot[size];
    for (int i = 0; i < size; i++) {
      this.dots[i] = new Dot(width/2, height-20, new DNA());
    }
    this.matingPool = new ArrayList();
    this.allDead = false;
    this.generations = 0;
  }

  public void calculateFitness() {
    for (Dot d : this.dots) {
      d.calculateFitness();
    }
  }

  private float getMaxFitness() {
    int index = 0;
    float max = 0;

    for (int i = 0; i < this.dots.length; i++) {
      float fitness = this.dots[i].getFitness();
      if (fitness > max) {
        max = fitness;
        index = i;
      }
    }

    this.dots[0].setBest(false); // Resetting the best Dot
    this.dots[index].setBest(true);

    return max;
  }

  private Dot getBestDot() {
    for (Dot d : this.dots) {
      if (d.getIsBest())
        return d;
    }
    return null;
  }

  public void naturalSelection() {
    float maxFitness = getMaxFitness();

    this.matingPool.clear();
    for (Dot d : this.dots) {
      float fitnessNormalized = map(d.getFitness(), 0, maxFitness, 0, 1); // Fitness normalized in a range of 0% - 100%
      int n = (int) (fitnessNormalized * 100);
      for (int i = 0; i < n; i++) {
        this.matingPool.add(d);
      }
    }
  }

  public void newGeneration() {
    int i = 0;
    Dot[] d = new Dot[this.dots.length];

    Dot aux = getBestDot();
    if (aux != null) {
      d[0] = new Dot(width/2, height-20, aux.getDNA()); // Setting the first Dot as the Best Dot of the previous generation
      d[0].setBest(true);
      i++;
    }

    while (i < this.dots.length) {
      int a = (int) random(this.matingPool.size());
      int b = (int) random(this.matingPool.size());

      DNA parentA = this.matingPool.get(a).getDNA();
      DNA parentB = this.matingPool.get(b).getDNA();

      DNA child = parentA.crossover(parentB);
      child.mutate();

      d[i++] = new Dot(width/2, height-20, child);
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
