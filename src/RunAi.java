import processing.core.PVector;
import processing.core.PApplet;

import static processing.core.PApplet.abs;
import static processing.core.PApplet.atan2;
import static processing.core.PConstants.PI;


// A representation of a kinematic character
final class RunAi {

    // I'm allowing public access to keep things snappy
    // Static Data
    final float width = 600;
    final float height = 600;
    final float MAX_ACCEL=10f;
    final float MAX_ROTATION=PI/4;
    final float ORIENTATION_INCREMENT=PI/32;
    final float MAX_SPEED = 1f ;
    public PVector position;
    public float orientation;
    // Kinematic Data
    public PVector velocity;
    PApplet applet;

    RunAi(int x, int y, float or,
          float xVel, float yVel, PApplet applet) {
        position = new PVector(x, y);
        orientation = or;
        velocity = new PVector(xVel, yVel);
        this.applet = applet;
    }

    // update position, orientation, velocity and rotation
    void integrate(PVector linear) {
        position.add(velocity);
        // Apply an impulse to bounce off the edge of the screen
        if ((position.x < 0) || (position.x > width)) velocity.x = -velocity.x;
        if ((position.y < 0) || (position.y > height)) velocity.y = -velocity.y;

        if (linear.mag() > MAX_ACCEL) {
            linear.normalize();
            linear.mult(MAX_ACCEL);
        }
        velocity.add(linear);
        if (velocity.mag() > MAX_SPEED) {
            velocity.normalize();
            velocity.mult(MAX_SPEED);
        }

        //move a bit towards velocity:
        // turn vel into orientation
        float targetOrientation = atan2(velocity.y, velocity.x);

        // Will take a frame extra at the PI boundary
        if (abs(targetOrientation - orientation) <= ORIENTATION_INCREMENT) {
            orientation = targetOrientation;
            return;
        }

        // if it's less than me, then how much if up to PI less, decrease otherwise increase
        if (targetOrientation < orientation) {
            if (orientation - targetOrientation < PI) orientation -= ORIENTATION_INCREMENT;
            else orientation += ORIENTATION_INCREMENT;
        } else {
            if (targetOrientation - orientation < PI) orientation += ORIENTATION_INCREMENT;
            else orientation -= ORIENTATION_INCREMENT;
        }

        // Keep in bounds
        if (orientation > PI) orientation -= 2 * PI;
        else if (orientation < -PI) orientation += 2 * PI;
    }
}