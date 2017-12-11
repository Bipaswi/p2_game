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
    public final float MAX_SPEED = 2f;
    public float currentSpeed;
    PVector camera;

    CircuitAi(int x, int y, float xVel, float yVel, float invM, int size, PVector camera, PApplet applet) {
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
        this.camera = camera;
        currentSpeed = MAX_SPEED; // change this to change speed difference
        //c = color(random(255),random(255),random(255));
    }

    public boolean targetReached(){
        if(this.position.equals(target))
            return true;
        return false;
    }


    public void integrate(PVector camera) {
        //position.add(worldVel);
        //target.add(worldVel);
        position.add(velocity);

        PVector pos = target.copy();
        PVector distance = pos.sub(position);
        velocity = distance.copy();
        if(distance.mag() > currentSpeed){
            velocity.normalize();
            velocity.mult(currentSpeed);
        }

        applet.fill(192, 192, 192);
        applet.rect(position.x - camera.x, position.y - camera.y, size, size);
    }
}