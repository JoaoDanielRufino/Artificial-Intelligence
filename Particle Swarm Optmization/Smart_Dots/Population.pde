class Population {
  private Dot[] dots;
  private boolean allDead;
  private int generations;
  private float bestFitness;
  private PVector[] gBest;

  public Population(int size) {
    this.dots = new Dot[size];
    for (int i = 0; i < size; i++) {
      this.dots[i] = new Dot(width/2, height-100, new DNA());
    }
    this.allDead = false;
    this.bestFitness = -1;
    this.generations = 0;
  }

  public void calculateFitness() {
    for (Dot d : this.dots) {
      d.calculateFitness();
    }
  }

  private void findBestFitness() {
    int index = 0;
    float max = -1;

    for (int i = 0; i < this.dots.length; i++) {
      float fitness = this.dots[i].getFitness();
      if (fitness > max) {
        max = fitness;
        index = i;
      }
    }

    if (this.bestFitness < max) {
      this.bestFitness = max;
      this.gBest = this.dots[index].getPosition();
    }
  }

  public void updateDots() {
    findBestFitness(); // Update gBest

    for (Dot d : this.dots) {
      d.updateVelocity(this.gBest);
      d.updatePosition();
    }

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
