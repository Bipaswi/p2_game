import processing.core.PApplet;
import processing.core.PVector;

public class Bullet {

        public PVector position;
        public PVector start;
        public PVector velocity;
        public float xS;
        public float yS;
        public float x;
        public float y;
        public float size = 10;
        public int color;
        public boolean transparencyOn;
        public int transparency;
        public int timer = 60;
        public int magnitude = 10;
        PApplet applet;

        Bullet(float x, float y, boolean transparencyOn, int xS, int yS, PApplet applet) {
            start = new PVector(x, y);
            position = new PVector(xS, yS);
            this.transparencyOn = transparencyOn;
            color = applet.color(169, 169, 169);
            transparency = 255;
            this.applet = applet;
        }

        public void project() {
            velocity = new PVector(position.x - start.x, position.y - start.y);
            velocity.normalize();
            velocity.mult(magnitude);
            start.add(velocity);
            applet.noStroke();
            applet.fill(color);
            applet.ellipse(start.x, start.y,size,size);
        }

        public void explode() {
            if (timer > 0){
                timer-=1.5;
                size+=2.5;
                transparency-=2;
            }
            applet.noStroke();
            applet.fill(color,transparency);
            applet.ellipse(position.x, position.y,size,size);
        }

}
