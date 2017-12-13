import processing.core.PApplet;
import processing.core.PVector;

public class example extends PApplet {

    public static void main(String[] args) {
        PApplet.main("example");
    }

    PVector position, velocity;
    float angle, steerAngle;

    boolean[] keys = new boolean[256];

    //

    public void settings() {
        size(440, 440);
        smooth();
    }

    public void setup() {
        velocity = new PVector();
        position = new PVector(width * 0.5f, height * 0.5f);
        angle = 0.0f;
        steerAngle = 0.0f;
    }

    //
    public void draw() {
        //
        background(250);
        //
        PVector force = new PVector();
        //
        PVector vector1 = new PVector(sin(angle), cos(angle));
        PVector vector2 = new PVector(vector1.y, -vector1.x);
        //
        float forwardVelocity = vector1.dot(velocity);
        float sidewaysVelocity = vector2.dot(velocity);

        //
        steerAngle = 0.0f;
        float factor = constrain(forwardVelocity * 0.3f, -1.0f, 1.0f);
        if (keys[37]) {
            angle += 0.04 * factor;
            steerAngle = -PI * 0.10f;
        }
        if (keys[39]) {
            angle -= 0.04 * factor;
            steerAngle = PI * 0.10f;
        }

        //
        force.add(PVector.mult(vector1, -forwardVelocity * 0.1f));
        if (keys[38]) {
            force.add(PVector.mult(vector1, 1.0f));
        }
        if (keys[40]) {
            force.add(PVector.mult(vector1, -1.0f));
        }

        //
        float sidewaysFriction = -constrain(sidewaysVelocity * 10.0f, -2.0f, 2.0f);
        force.add(PVector.mult(vector2, sidewaysFriction));

        //
        force.div(10.0f);
        velocity.add(force);
        position.add(velocity);

        //
        position.x = (position.x + width) % width;
        position.y = (position.y + height) % height;

        //
        fill(0, 100);
        noStroke();
        rectMode(CENTER);
        //
        pushMatrix();
        translate(position.x, position.y);
        rotate(-angle);
        rect(0, 0, 10, 25);
        pushMatrix();
        translate(10, 17);
        rotate(steerAngle);
        popMatrix();
        popMatrix();
        //System.out.println(position);
    }

    //
    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }
}
