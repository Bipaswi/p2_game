import processing.core.PApplet;
import processing.core.PVector;

final class CircuitAi {

    public PVector position;
    public PVector velocity;
    private PVector forceAccumulator;
    public PVector linear;
    private float invMass;
    final float MAX_ACCEL = 0.1f;
    private int timer;
    private int size;
    PApplet applet;
    public PVector target;
    public int currentCell;
    public final float MAX_SPEED = 1f;
    public float currentSpeed;

    CircuitAi(int x, int y, float xVel, float yVel, float invM, int size, PApplet applet) {
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        forceAccumulator = new PVector(0, 0);
        invMass = invM;
        this.timer = timer;
        this.size = size;
        this.applet = applet;
        linear = new PVector(0, 0);
        target = this.position;
        this.currentCell = 0;
        currentSpeed = MAX_SPEED; // change this to change speed difference
        //c = color(random(255),random(255),random(255));
    }

    public boolean targetReached(){
        if(this.position.equals(target))
            return true;
        return false;
    }

    // Add a force to the accumulator
    void addForce(PVector force) {
        forceAccumulator.add(force);
    }

    public void integrate() {

        //change varibalese
        position.add(velocity);

        PVector pos = target.copy();
        PVector distance = pos.sub(position);
        velocity = distance.copy();
        if(distance.mag() > currentSpeed){
            velocity.normalize();
            velocity.mult(currentSpeed);
        }





//
//        float xe = position.x, ye = position.y;
//
//        linear.x = target.x - position.x;
//        linear.y = target.y - position.y;
//
//        linear.normalize();
//        linear.mult(MAX_ACCEL);
//        velocity.add(linear);
//        if (velocity.mag() > 2f) {
//            velocity.normalize();
//            velocity.mult(MAX_SPEED);
//        }
//        position.add(velocity);
//
//
        applet.fill(192, 192, 192);
        applet.ellipse(position.x, position.y, size, size);
    }


//    void move() {
//        //if (invMass <= 0f) return;
//        position.add(velocity);
//
//        applet.fill(138, 160, 166);
//        applet.noStroke();
//        applet.rect(position.x, position.y, size, size);
//    }
}