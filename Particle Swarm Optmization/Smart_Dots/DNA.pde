class DNA {
  private PVector[] position;
  private PVector[] velocity;
  private final int SIZE = 800;

  public DNA() {
    this.position = new PVector[SIZE];
    this.velocity = new PVector[SIZE];
    for (int i = 0; i < SIZE; i++) {
      this.position[i] = PVector.random2D();
      this.velocity[i] = PVector.random2D();
    }
  }

  public void setVelocity(PVector[] newVelocity) {
    this.velocity = newVelocity;
  }

  public void setPosition(PVector[] newPosition) {
    this.position = newPosition;
  }

  public PVector[] getPosition() {
    return this.position;
  }

  public PVector getPVector(int i) {
    return this.position[i];
  }

  public PVector[] getVelocity() {
    return this.velocity;
  }

  public int getSize() {
    return SIZE;
  }
}
