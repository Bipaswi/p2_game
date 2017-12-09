import processing.core.PApplet;
import processing.core.PVector;

public class TrackWorld {

    public PVector position;
    public PVector velocity;
    public PApplet applet;
    //public ArrayList<TrackWall> trackWalls = new ArrayList<>();
    public int worldSize = 100;
    public int size = 40;
    public TrackWall[][] gameTrack = new TrackWall[worldSize][worldSize];

    TrackWorld(float x, float y, float xVel, float yVel, PApplet applet) {
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        this.applet = applet;
    }

    public void createTrack(){
       for(int x = 0; x < worldSize; x++) {
           for(int y = 0; y < worldSize; y++) {
               gameTrack[x][y] = new TrackWall(x * size, y * size, 0, 0, size, size, true,  applet);
           }
       }
       for (int i = 4; i < 11; i++) {
           gameTrack[4][i].switchOn = false;
           gameTrack[7][i].switchOn = false;
       }
        gameTrack[7][5].switchOn = true;
        //gameTrack[3][3].switchOn = false;

       for (int j = 5; j < 7; j++) {
            gameTrack[j][4].switchOn = false;
            gameTrack[j][10].switchOn = false;
       }
       for (int k = 3; k < 7; k++) {
            gameTrack[9][k].switchOn = false;
       }
       gameTrack[8][3].switchOn = false;
       gameTrack[7][3].switchOn = false;
       gameTrack[8][6].switchOn = false;
    }

    public void updateTrack() {
        for(int x = 0; x < worldSize; x++) {
            for (int y = 0; y < worldSize; y++) {
                gameTrack[x][y].position.add(velocity);
            }
        }
    }

    public void displayTrack() {
        for(int x = 0; x < worldSize; x++) {
            for (int y = 0; y < worldSize; y++) {
               gameTrack[x][y].displayWall();
            }
        }
    }
}
