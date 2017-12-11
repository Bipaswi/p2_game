import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class TrackWorld {

    public PVector position;
    public PVector velocity;
    public PApplet applet;
    public PVector camera;
    //public ArrayList<TrackWall> trackWalls = new ArrayList<>();
    public int worldSize = 100;
    public int size = 40;
    public TrackWall[][] gameTrack = new TrackWall[worldSize][worldSize];
    public int count = 1;
    ArrayList<TrackWall> trackWalls = new ArrayList<>();

    TrackWorld(float x, float y, float xVel, float yVel, PVector camera, PApplet applet) {
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        this.applet = applet;
        this.camera = camera;
    }

    public void createTrack() {
        //trackWalls = trackGen.trackWalls;
        //gameTrack = trackGen.newTrack;
        for (int x = 0; x < worldSize; x++) {
            for (int y = 0; y < worldSize; y++) {
                gameTrack[x][y] = new TrackWall(x * size, y * size, 0, 0, camera, size, size, true, applet);
            }
        }
        //trackDown(7, 12, 7);
        //trackRight(7, 12, 11);
        //trackUp(11, 6, 11);
        //trackLeft(11, 7, 7);
        trackUp(7, 3, 7);
        trackRight(7, 10, 3);
        trackUp(3, 1, 10);
        trackRight(10, 13, 1);
        trackDown(1, 4, 13);
        trackLeft(13, 11, 4);
        trackDown(4, 7, 11);
        trackLeft(11, 6, 7);
    }

    public void trackDown(int start, int length, int plane) {
        for (int i = start; i < length; i++) {
            //for (int y = start; y < length; y++) {
                gameTrack[plane][i] = new TrackWall(plane * size, i * size, 0, 0, camera, size, size, false, applet);
                trackWalls.add(gameTrack[plane][i]);
            //}
        }
    }

    public void trackUp(int start, int length, int plane) {
        for (int i = start; i > length; i--) {
            gameTrack[plane][i] = new TrackWall(plane * size, i * size, 0, 0, camera, size, size, false, applet);
            trackWalls.add(gameTrack[plane][i]);
        }
    }

    public void trackLeft(int start, int length, int plane) {
        for (int i = start; i > length; i--) {
            gameTrack[i][plane] = new TrackWall(i * size, plane * size, 0, 0, camera,size, size, false, applet);
            trackWalls.add(gameTrack[i][plane]);
        }
    }

    public void trackRight(int start, int length, int plane) {
        for (int i = start; i < length; i++) {
            gameTrack[i][plane] = new TrackWall(i * size, plane * size, 0, 0, camera, size, size, false, applet);
            trackWalls.add(gameTrack[i][plane]);
        }
    }

//        gameTrack[7][7] = new TrackWall(7 * size, 7 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[7][7]);
//        gameTrack[7][6] = new TrackWall(7 * size, 6 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[7][6]);
//        gameTrack[7][5] = new TrackWall(7 * size, 5 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[7][5]);
//        gameTrack[7][4] = new TrackWall(7 * size, 4 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[7][4]);
//        gameTrack[7][3] = new TrackWall(7 * size, 3 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[7][3]);
//
//        gameTrack[6][3] = new TrackWall(6 * size, 3 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[6][3]);
//        gameTrack[5][3] = new TrackWall(5 * size, 3 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[5][3]);
//        gameTrack[4][3] = new TrackWall(4 * size, 3 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[4][3]);
//
//        gameTrack[4][4] = new TrackWall(4 * size, 4 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[4][4]);
//        gameTrack[4][5] = new TrackWall(4 * size, 5 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[4][5]);
//        gameTrack[4][6] = new TrackWall(4 * size, 6 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[4][6]);
//        gameTrack[4][7] = new TrackWall(4 * size, 7 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[4][7]);
//
//        gameTrack[5][7] = new TrackWall(5 * size, 7 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[5][7]);
//        gameTrack[6][7] = new TrackWall(6 * size, 7 * size, 0, 0, size, size, false, applet);
//        trackWalls.add(gameTrack[6][7]);


//    public void updateTrack() {
//        for(int x = 0; x < worldSize; x++) {
//            for (int y = 0; y < worldSize; y++) {
//                gameTrack[x][y].position.add(velocity);
//            }
//        }
//    }

    public void displayTrack() {
        for(int x = 0; x < worldSize; x++) {
            for (int y = 0; y < worldSize; y++) {
               gameTrack[x][y].displayWall(camera);
            }
        }
    }
}
