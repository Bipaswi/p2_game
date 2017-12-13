import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.*;


// A representation of a kinematic character
final class Player {
    final float width = 10;
    final float height = 10;
    public int size = 30;

    public PVector position;
    public PVector velocity;
    public float angle = 0.0f;
    public float steerAngle = 0.0f;

    public PApplet applet;

    public PVector force;
    public PVector vector1;
    public PVector vector2;
    public float forwardVelocity;
    public float sidewaysVelocity;
    public float sidewaysFriction;
    public float factor;

    private static final float DAMPING = .995f ;


    Player(int x, int y, int size, PApplet applet) {
        position = new PVector(x, y);
        this.velocity = new PVector();
        this.size = size;
        this.applet = applet;
    }

    public void initPlayer(){
        force = new PVector();
        //
        vector1 = new PVector(sin(angle), cos(angle));
        vector2 = new PVector(vector1.y, -vector1.x);
        //
        forwardVelocity = vector1.dot(velocity);
        sidewaysVelocity = vector2.dot(velocity);
        //
        steerAngle = 0.0f;
        factor = constrain(forwardVelocity * 0.3f, -1.0f, 1.0f);
    }

    public void updatePlayer(PVector camera) {
        sidewaysFriction =- constrain(sidewaysVelocity * 10.0f, -2.0f, 2.0f);
        force.add(PVector.mult(vector2, sidewaysFriction));

        force.div(10.0f);
        velocity.add(force);
        velocity.mult(DAMPING) ;
        position.sub(velocity);

        //
       // position.x = (position.x + width) % width;
       // position.y = (position.y + height) % height;

        //
        //applet.fill(1);
        //applet.fill(0);
        applet.noStroke();


        applet.pushMatrix();
        applet.translate(position.x + width/2 - camera.x, position.y + height/2- camera.y);
        applet.rotate(-angle);
        applet.fill(1);
        applet.rect(-width/2, -height/2, 10, 10);
        applet.fill(255);
        applet.rect(-width/2, -height/2, 10, 4);
//        applet.pushMatrix();
//        applet.translate(10,17);
//        applet.rotate(-steerAngle);
//        applet.popMatrix();
        applet.popMatrix();

        //applet.rect(position.x - camera.x, position.y - camera.y, 10, 10);
        //System.out.println(position);
    }
       // position.add(velocity);
   // }

//    public void displayPlayer(int r, int g, int b, PVector camera) {
//        applet.fill(r, g, b);
//        applet.rect(position.x - camera.x, position.y - camera.y, size, size);
//    }
}