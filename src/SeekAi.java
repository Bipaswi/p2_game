import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;
import static processing.core.PConstants.PI;

public class SeekAi {

    final float MAX_SPEED = 1f;
    final float MAX_ACCEL = 0.1f;
    final float MAX_ROTATION = PI / 4;
    final float width = 600;
    final float height = 600;
    final int size = 20;


    // I'm allowing public access to keep things snappy
    // Static Data
    public PVector position;
    public float orientation;
    // Kinematic Data
    public PVector velocity;
    public float rotation;
    // Accel is calculated at each integration
    private PVector linear;
    PApplet applet;

    SeekAi(int x, int y, float or,
           float xVel, float yVel, float rot, PApplet applet) {
        position = new PVector(x, y);
        orientation = or;
        velocity = new PVector(xVel, yVel);
        rotation = rot;
        linear = new PVector(0, 0);
        this.applet = applet;
    }

    // update position, orientation, velocity and rotation
    public void integrate(PVector targetPos, float angular) {
        float xe = position.x, ye = position.y;
        applet.fill(255, 0, 0);
        applet.ellipse(xe, ye, 30, 30);
        // Show orientation
        int newxe = (int) (xe + 10 * cos(orientation));
        int newye = (int) (ye + 10 * sin(orientation));
        applet.fill(0);
        applet.ellipse(newxe, newye, size, size);

        position.add(velocity);
        // Apply an impulse to bounce off the edge of the screen
        if ((position.x < 0) || (position.x > width)) velocity.x = -velocity.x;
        if ((position.y < 0) || (position.y > height)) velocity.y = -velocity.y;

        orientation += rotation;
        if (orientation > PI) orientation -= 2 * PI;
        else if (orientation < -PI) orientation += 2 * PI;

        linear.x = targetPos.x - position.x;
        linear.y = targetPos.y - position.y;

        linear.normalize();
        linear.mult(MAX_ACCEL);
        velocity.add(linear);
        if (velocity.mag() > MAX_SPEED) {
            velocity.normalize();
            velocity.mult(MAX_SPEED);
        }

        rotation += angular;
        if (rotation > MAX_ROTATION) rotation = MAX_ROTATION;
        else if (rotation < -MAX_ROTATION) rotation = -MAX_ROTATION;
    }
}

