import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Game extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Game");
    }

    final public int MY_WIDTH = 600;
    final public int MY_HEIGHT = 600;
    static final int FPS = 60;
    boolean[] isKeyPressed = new boolean[256];
    Player player;
    TrackWorld track;
    CircuitAi testAi;
    public ArrayList<String> blocked = new ArrayList<>();
    boolean blocking;
    float speed = 0.2f;
    float initialVel = 0.9f;
    public PVector camera = new PVector(0,0);

    public void settings() {
        size(MY_WIDTH, MY_HEIGHT);
        smooth(4);
    }

    public void setup() {
        frameRate(FPS);
        player = new Player(300, 300, 0f, 0f, 0f, 10, this);
        track = new TrackWorld(300, 300, 0f, 0f, camera, this);
        testAi = new CircuitAi(285, 300, 1f, 1f, 0.2f, 10, camera,this);
        track.createTrack();
    }

    public void draw() {
        background(128);
        //player.displayPlayer(255, 255, 255);
        testAi.integrate(camera);
        moveAi();
        collision();
        movePlayer();
        track.displayTrack();
    }

    //update ai target
    public void moveAi() {
//        if (testAi.currentCell+1 >= track.trackWalls.size()) {
//            testAi.currentCell = 0;
//            testAi.target = track.trackWalls.get(testAi.currentCell).getCentre();
//            System.out.println("here");
//        }
        if (testAi.targetReached() && testAi.currentCell+1 < track.trackWalls.size()) {
                testAi.target = track.trackWalls.get(testAi.currentCell+1).getCentre();
                System.out.println(testAi.target);
                testAi.currentCell++;
        }
    }

    public void collision() {
        for (int x = 0; x < track.worldSize; x++) {
            for (int y = 0; y < track.worldSize; y++) {
                if (track.gameTrack[x][y].switchOn) {
                    int width = track.gameTrack[x][y].width;
                    int height = track.gameTrack[x][y].height;
                    PVector position = track.gameTrack[x][y].position;
                    CollisionDetected(position.x, position.y, width, height, player.position.x, player.position.y, player.size, player.size, x, y);
                }
            }
        }
    }

    // adapted from 130006099
    public void CollisionDetected(float rPosx, float rPosy, float rWidth, float rHeight, float pPosx, float pPosy, float pWidth, float pHeight, int x, int y) {
        // boolean hasCollided = false;
        if (pPosy <= rPosy + rHeight &&
                pPosy + pHeight >= rPosy + rHeight &&
                (pPosx + pWidth >= rPosx && pPosx <= rPosx + rWidth) &&
                !isNeighbour(x, y, "s")) {
            track.gameTrack[x][y].setColor(0, 255, 0);
            //System.out.println("collision top");
            //track.velocity.y = -speed / initialVel;
            player.velocity.y =+ speed / initialVel;
            //return track.gameTrack[x][y];
        }
        if (pPosy + pHeight >= rPosy &&
                pPosy <= rPosy &&
                (pPosx + pWidth >= rPosx && pPosx <= rPosx + rWidth) &&
                !isNeighbour(x, y, "w")) {
            track.gameTrack[x][y].setColor(0, 0, 255);
            //System.out.println("collision bottom");
            //track.velocity.y = speed / initialVel;
            player.velocity.y =- speed / initialVel;
            //hasCollided = true;
        }
        if (pPosx + pWidth >= rPosx &&
                pPosx <= rPosx &&
                (pPosy + pHeight >= rPosy && pPosy <= rPosy + rHeight) &&
                !isNeighbour(x, y, "l")) {
            track.gameTrack[x][y].setColor(255, 0, 0);
            //System.out.println("collision right");
            //track.velocity.x = speed / initialVel;
            player.velocity.x =- speed / initialVel;
            //hasCollided = true;
        }
        if (pPosx <= rPosx + rWidth &&
                pPosx + pWidth >= rPosx + rWidth &&
                (pPosy + pHeight >= rPosy && pPosy <= rPosy + rHeight) &&
                !isNeighbour(x, y, "r")) {
            track.gameTrack[x][y].setColor(1, 1, 1);
            //System.out.println("collision left");
            //track.velocity.x = -speed / initialVel;
            player.velocity.x =+ speed / initialVel;
            //hasCollided = true;
        }

    }

    // adapted from 130006099
    public boolean isNeighbour(int x, int y, String dir) {
        if (y < 1 || y >= track.worldSize - 1 || x < 1 || x >= track.worldSize - 1) {
            return false;
        }
        if (dir == "s" && track.gameTrack[x][y + 1].switchOn) {
            return true;
        }
        if (dir == "w" && track.gameTrack[x][y - 1].switchOn) {
            return true;
        }
        if (dir == "r" && track.gameTrack[x + 1][y].switchOn) {
            return true;
        }
        if (dir == "l" && track.gameTrack[x - 1][y].switchOn) {
            return true;
        }
        return false;
    }

    public void movePlayer() {
        player.velocity.x *= initialVel;
        player.velocity.y *= initialVel;

        if (isKeyPressed[LEFT]) {
            player.velocity.x -= speed;
        }
        if (isKeyPressed[RIGHT]) {
            player.velocity.x += speed;
        }
        if (isKeyPressed[UP]) {
            player.velocity.y -= speed;
        }
        if (isKeyPressed[DOWN]) {
            player.velocity.y += speed;
        }
        //adding the velocity here
//        track.updateTrack();
//        track.displayTrack();
        player.updatePlayer();
        camera.x = (player.position.x - MY_WIDTH/2);
        camera.y = (player.position.y - MY_HEIGHT/2);
        player.displayPlayer(255, 255, 40, camera);
    }

    public void keyPressed() {
        isKeyPressed[keyCode] = true;
    }

    public void keyReleased() {
        isKeyPressed[keyCode] = false;
    }
}