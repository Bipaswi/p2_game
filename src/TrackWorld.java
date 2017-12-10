import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class TrackWorld {

    public PVector position;
    public PVector velocity;
    public PApplet applet;
    //public ArrayList<TrackWall> trackWalls = new ArrayList<>();
    public int worldSize = 100;
    public int size = 40;
    public TrackWall[][] gameTrack = new TrackWall[worldSize][worldSize];
    public int count = 1;
    ArrayList<TrackWall> trackWalls = new ArrayList<>();

    TrackWorld(float x, float y, float xVel, float yVel, PApplet applet) {
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        this.applet = applet;
    }

    public void createTrack(){
        for(int x = 0; x < worldSize; x++) {
           for(int y = 0; y < worldSize; y++) {
               gameTrack[x][y] = new TrackWall(x * size, y * size, 0, 0, size, size, true, applet);
           }
       }
        //gameTrack[6][7] = new TrackWall(x * size, y * size, 0, 0, size, size, true, 0, applet);
//
//
//       for (int i = 4; i < 11; i++) {
//           gameTrack[4][i].switchOn = false;
//           gameTrack[7][i].switchOn = false;
//       }
//        gameTrack[7][5].switchOn = true;
//        //gameTrack[3][3].switchOn = false;
//
//       for (int j = 5; j < 7; j++) {
//            gameTrack[j][4].switchOn = false;
//            gameTrack[j][10].switchOn = false;
//       }
//       for (int k = 3; k < 7; k++) {
//            gameTrack[9][k].switchOn = false;
//       }
//       gameTrack[8][3].switchOn = false;
//       gameTrack[7][3].switchOn = false;
//       gameTrack[8][6].switchOn = false;
//        for (int i = 7; i < 11; i++) {
//            gameTrack[7][i] = new TrackWall(7 * size, 7 * size, 0, 0, size, size, false, count, applet);
//            count++;
//            //System.out.println(count);
//        }
        gameTrack[7][7] = new TrackWall(7 * size, 7 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[7][7]);
        gameTrack[7][6] = new TrackWall(7 * size, 6 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[7][6]);
        gameTrack[7][5] = new TrackWall(7 * size, 5 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[7][5]);
        gameTrack[7][4] = new TrackWall(7 * size, 4 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[7][4]);
        gameTrack[7][3] = new TrackWall(7 * size, 3 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[7][3]);

        gameTrack[6][3] = new TrackWall(6 * size, 3 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[6][3]);
        gameTrack[5][3] = new TrackWall(5 * size, 3 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[5][3]);
        gameTrack[4][3] = new TrackWall(4 * size, 3 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[4][3]);

        gameTrack[4][4] = new TrackWall(4 * size, 4 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[4][4]);
        gameTrack[4][5] = new TrackWall(4 * size, 5 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[4][5]);
        gameTrack[4][6] = new TrackWall(4 * size, 6 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[4][6]);
        gameTrack[4][7] = new TrackWall(4 * size, 7 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[4][7]);

        gameTrack[5][7] = new TrackWall(5 * size, 7 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[5][7]);
        gameTrack[6][7] = new TrackWall(6 * size, 7 * size, 0, 0, size, size, false, applet);
        trackWalls.add(gameTrack[6][7]);




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
