class Dot {
  private PVector pos;
  private PVector vel;
  private DNA dna;
  private int step; // Variable which controls the array of genes(PVectors)
  private float fitness;
  private boolean dead;
  private boolean reachedTarget;
  private boolean hitObstacle;

  public Dot(int w, int h, DNA dna) {
    this.pos = new PVector(w, h);
    this.vel = new PVector(0, 0);
    this.dna = dna;
    this.step = 0;
    this.fitness = 0;
    this.dead = false;
    this.reachedTarget = false;
    this.hitObstacle = false;
  }

  public void calculateFitness() {
    if (this.reachedTarget)
      this.fitness = 10000.0/(this.step * this.step); // If the Dot reached the target, then the fitness is based on the amount steps it took
    else {
      float distTarget = dist(this.pos.x, this.pos.y, target.x, target.y); // If didn't reach the target, then the fitness is based on the distance from the target
      this.fitness = 1.0/(distTarget * distTarget);
      if (this.hitObstacle)
        this.fitness *= 0.1; // If a dot hit an Obstacle, it loses 90% of his fitness
    }
  }

  private void checkLife() {
    if (dist(this.pos.x, this.pos.y, target.x, target.y) < 6) {
      this.reachedTarget = true;
      this.dead = true;
    }
    if (this.step >= this.dna.getSize()) // Checking if the Dot has already taken more steps than possible
      this.dead = true;
    else if (this.pos.x > width || this.pos.y > height || this.pos.x < 0 || this.pos.y < 0)
      this.dead = true;
    else {
      for (Obstacle o : obstacles) {
        if (o.checkCollision(this.pos)) { // Cheking all Obstacles
          this.hitObstacle = true;
          this.dead = true;
          return;
        }
      }
    }
  }

  public void update() {
    checkLife();
    if (!this.dead) {
      PVector acc = this.dna.getPVector(this.step++); // Getting a direction
      this.vel.add(acc);
      this.vel.limit(6);
      this.pos.add(vel);
    }
  }

  public void show() {
    fill(0);
    ellipse(this.pos.x, this.pos.y, 8, 8);
  }

  public DNA getDNA() {
    return this.dna;
  }

  public int getStep() {
    return this.step;
  }

  public boolean isDead() {
    return this.dead;
  }

  public float getFitness() {
    return this.fitness;
  }
}
