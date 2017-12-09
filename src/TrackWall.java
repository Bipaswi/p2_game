import processing.core.PApplet;
import processing.core.PVector;

public class TrackWall extends TrackWorld{

    //public PVector position;
    public int width;
    public int height;
    public PApplet applet;
    public boolean switchOn;
    public int wallNumber = 0;
    public int color;

    TrackWall(float x, float y, float xVel, float yVel, int width, int height, boolean switchOn, PApplet applet) {
        super(x, y, xVel, yVel, applet);
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        this.width = width;
        this.height = height;
        this.applet = applet;
        this.switchOn = switchOn;
        this.color = applet.color(255, 255, 255);
       // this.wallNumber = wallNumber;
    }

    public void displayWall() {
        if(switchOn) {
            applet.fill(this.color);
            applet.rect(position.x, position.y, width, height);
        }
    }

    public void setColor(int r, int g, int b){
        this.color = applet.color(r, g, b);
    }

}
