import processing.core.PApplet;
import processing.core.PVector;

final class CircuitAi {

    public PVector position;
    public PVector velocity;
    private PVector forceAccumulator;
    private float invMass;
    private int timer;
    public int explosionTimer = 60;
    public int transparency = 255;
    public int explosionSize = 50;
    private int size;
    PApplet applet;
    //color c;

    public float getMass() {
        return 1 / invMass;
    }

    CircuitAi(int x, int y, float xVel, float yVel, float invM, int size, PApplet applet) {
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        forceAccumulator = new PVector(0, 0);
        invMass = invM;
        this.timer = timer;
        this.size = size;
        this.applet = applet;
        //c = color(random(255),random(255),random(255));

    }

    // Add a force to the accumulator
    void addForce(PVector force) {
        forceAccumulator.add(force);
    }

    void move() {
        //if (invMass <= 0f) return;
        position.add(velocity);

//        PVector resultingAcceleration = forceAccumulator.get();
//        resultingAcceleration.mult(invMass);
//        velocity.add(resultingAcceleration);
//        forceAccumulator.x = 0;
//        forceAccumulator.y = 0;

        applet.fill(138, 160, 166);
        applet.noStroke();
        applet.rect(position.x, position.y, size, size);
    }
}