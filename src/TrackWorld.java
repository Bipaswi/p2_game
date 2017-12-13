import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class TrackWorld {

    public PVector position;
    public PVector velocity;
    public PApplet applet;
    //public ArrayList<TrackWall> trackWalls = new ArrayList<>();
    public ArrayList<Obstacle> obstacles = new ArrayList<>();
    public int worldSize = 100;
    public int size = 150;
    public TrackWall[][] gameTrack = new TrackWall[worldSize][worldSize];
    public int count = 1;
    ArrayList<TrackWall> trackWalls = new ArrayList<>();

    TrackWorld(float x, float y, float xVel, float yVel, PApplet applet) {
        position = new PVector(x, y);
        velocity = new PVector(xVel, yVel);
        this.applet = applet;
    }

    public void createTrack() {
        //trackWalls = trackGen.trackWalls;
        //gameTrack = trackGen.newTrack;
        for (int x = 0; x < worldSize; x++) {
            for (int y = 0; y < worldSize; y++) {
                gameTrack[x][y] = new TrackWall(x * size, y * size, 0, 0, size, size, true, applet);
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
        
        createObstacles(1);
    }

    public void trackDown(int start, int length, int plane) {
        for (int i = start; i < length; i++) {
            //for (int y = start; y < length; y++) {
                gameTrack[plane][i] = new TrackWall(plane * size, i * size, 0, 0, size, size, false, applet);
                trackWalls.add(gameTrack[plane][i]);
            //}
        }
    }

    public void trackUp(int start, int length, int plane) {
        for (int i = start; i > length; i--) {
            gameTrack[plane][i] = new TrackWall(plane * size, i * size, 0, 0, size, size, false, applet);
            trackWalls.add(gameTrack[plane][i]);
        }
    }

    public void trackLeft(int start, int length, int plane) {
        for (int i = start; i > length; i--) {
            gameTrack[i][plane] = new TrackWall(i * size, plane * size, 0, 0, size, size, false, applet);
            trackWalls.add(gameTrack[i][plane]);
        }
    }

    public void trackRight(int start, int length, int plane) {
        for (int i = start; i < length; i++) {
            gameTrack[i][plane] = new TrackWall(i * size, plane * size, 0, 0, size, size, false, applet);
            trackWalls.add(gameTrack[i][plane]);
        }
    }

    public void createObstacles(int amount) {
        for (int i = 1; i < trackWalls.size(); i++) {
            float x_ = applet.random(trackWalls.get(i).position.x, trackWalls.get(i).position.x + trackWalls.get(i).width);
            float y_ = applet.random(trackWalls.get(i).position.y, trackWalls.get(i).position.y + trackWalls.get(i).height);
            obstacles.add(new Obstacle(x_, y_, 10, applet));
        }
    }

    public void displayTrack(PVector camera) {
        for(int x = 0; x < worldSize; x++) {
            for (int y = 0; y < worldSize; y++) {
               gameTrack[x][y].displayWall(camera);
            }
        }
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).display(camera);
        }
    }
}
