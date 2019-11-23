int POP_SIZE = 400;
PVector target;
Population pop;

ArrayList<Obstacle> obstacles;
int firstX;
int firstY;
int w;
int h;

void setup() {
  size(800, 600);
  target = new PVector(width/2, 10);
  pop = new Population(POP_SIZE);
  obstacles = new ArrayList();
  firstX = -1;
  firstY = -1;
}

void draw() {
  background(255);

  fill(255, 0, 0);
  ellipse(target.x, target.y, 10, 10);

  fill(0);
  for (Obstacle o : obstacles) { // Drawing the Obstacles
    o.show();
  }

  if (mousePressed) {
    if (firstX == -1 || firstY == -1) {
      firstX = mouseX;
      firstY = mouseY;
    }
    w = mouseX - firstX;
    h = mouseY - firstY;
    rect(firstX, firstY, w, h);
  }

  if (!pop.getAllDead()) {
    pop.show();
    pop.update();
  } else {
    pop.calculateFitness();
    pop.updateDots();
    println("Generation: " + pop.getGeneration());
  }
}

void mouseReleased() {
  obstacles.add(new Obstacle(firstX, firstY, w, h)); // Adding the new Obstacle
  firstX = -1;
  firstY = -1;
}
