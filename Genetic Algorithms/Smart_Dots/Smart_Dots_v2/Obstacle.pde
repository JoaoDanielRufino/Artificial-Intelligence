class Obstacle {
  private int x;
  private int y;
  private int w;
  private int h;

  public Obstacle(int x, int y, int w, int h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }

  // Checking all the possible ways to draw an Obstacle
  public boolean checkCollision(PVector pos) {
    if (this.x <= (this.x + this.w) && this.y <= (this.y + this.h)) {
      if (pos.x >= this.x && pos.y >= this.y && pos.x <= (this.x + this.w) && pos.y <= (this.y + this.h))
        return true;
    } 
    else if (this.x <= (this.x + this.w) && this.y >= (this.y + this.h)) {
      if (pos.x >= this.x && pos.y <= this.y && pos.x <= (this.x + this.w) && pos.y >= (this.y + this.h))
        return true;
    }
    else if (this.x >= (this.x + this.w) && this.y <= (this.y + this.h)) {
      if (pos.x <= this.x && pos.y >= this.y && pos.x >= (this.x + this.w) && pos.y <= (this.y + this.h))
        return true;
    }
    else if (this.x >= (this.x + this.w) && this.y >= (this.y + this.h)) {
      if (pos.x <= this.x && pos.y <= this.y && pos.x >= (this.x + this.w) && pos.y >= (this.y + this.h))
        return true;
    }
    return false;
  }

  public void show() {
    rect(this.x, this.y, this.w, this.h);
  }
}
