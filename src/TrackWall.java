import processing.core.PApplet;
import processing.core.PVector;

public class TrackWall extends TrackWorld{

    //public PVector position;
    public float width;
    public float height;
    PVector centre;
    public PApplet applet;
    public boolean switchOn;
    public int wallNumber = 0;
    public int color;

    TrackWall(float x, float y, float xVel, float yVel, float width, float height, boolean switchOn, PApplet applet) {
        super(x, y, xVel, yVel, applet);
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        this.width = width;
        this.height = height;
        this.applet = applet;
        this.switchOn = switchOn;
        this.color = applet.color(255, 255, 255);
    }

    public PVector getCentre(){
        PVector center = new PVector(position.x + this.width/2, position.y + this.height/2);
        return center;
    }

    public void displayWall(PVector camera) {
        if(switchOn) {
            applet.fill(this.color);
            applet.rect(position.x - camera.x, position.y - camera.y, width, height);
        }
    }

    public void setColor(int r, int g, int b){
        this.color = applet.color(r, g, b);
    }

}
