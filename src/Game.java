import processing.core.PApplet;
import processing.core.PVector;

public class Game extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Game");
    }

    final public int MY_WIDTH = 600;
    final public int MY_HEIGHT = 600;
    static final int FPS = 60;
    boolean[] isKeyPressed = new boolean[256];

    //storing game objects here
    Player player;
    TrackWorld track;
    CircuitAi testAi;
    Obstacle testObstacle;
    float speed = 0.3f;
    float initialVel = 0.9f;
    public PVector camera = new PVector(0, 0);
    int laps = 3;
    int lapCount = 0;


    public void settings() {
        size(MY_WIDTH, MY_HEIGHT);
        smooth(4);
    }

    public void setup() {
        frameRate(FPS);
        player = new Player(1100, 1100, 10, this);
        track = new TrackWorld(300, 300, 0f, 0f, this);
        testAi = new CircuitAi(1100, 1100, 1f, 1f, 0.2f, 10, this);
        //testObstacle = new Obstacle(300, 210, 10, this);
        rectMode(CORNER);
        track.createTrack();

    }

    public void draw() {
        background(128);
        testAi.integrate(camera);
        moveAi();
        collision();
        movePlayer();
//        testObstacle.display(camera);
        track.displayTrack(camera);

        //finishGame();
    }

    //update ai target
    public void moveAi() {
        if (testAi.currentCell + 1 >= track.trackWalls.size()) {
            testAi.currentCell = 0;
            testAi.target = track.trackWalls.get(testAi.currentCell).getCentre();
        }
        if (testAi.targetReached() && testAi.currentCell + 1 < track.trackWalls.size()) {
            testAi.target = track.trackWalls.get(testAi.currentCell + 1).getCentre();
            testAi.currentCell++;
        }
    }

    public boolean finishGame(){
        boolean one = false;
        boolean two = false;
        if (lapCount == laps) {
            return true;
        }

        //checking if player corssed first track and half way track before completing round
       //need to draw two lines and then check collisions for them

        if (one && two){
            lapCount++;
            one = false;
            two = false;
            System.out.println(lapCount);
        }


        return false;
    }


    public void collision() {
        for (int x = 0; x < track.worldSize; x++) {
            for (int y = 0; y < track.worldSize; y++) {
                if (track.gameTrack[x][y].switchOn) {
                    float width = track.gameTrack[x][y].width;
                    float height = track.gameTrack[x][y].height;
                    PVector position = track.gameTrack[x][y].position;
                    CollisionDetected(position.x, position.y, width, height, player.position.x, player.position.y, player.size, player.size, x, y);
                }
            }
        }

        for (int i = 0; i < track.obstacles.size(); i++) {
            CollisionDetected(track.obstacles.get(i).position.x, track.obstacles.get(i).position.y, track.obstacles.get(i).size, track.obstacles.get(i).size,
                    player.position.x, player.position.y, player.size, player.size, 0, 0);
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
           // System.out.println("top");
            player.velocity.y = -speed / initialVel;
        }
        if (pPosy + pHeight >= rPosy &&
                pPosy <= rPosy &&
                (pPosx + pWidth >= rPosx && pPosx <= rPosx + rWidth) &&
                !isNeighbour(x, y, "w")) {
            track.gameTrack[x][y].setColor(0, 0, 255);
            player.velocity.y = +speed / initialVel;
        }
        if (pPosx + pWidth >= rPosx &&
                pPosx <= rPosx &&
                (pPosy + pHeight >= rPosy && pPosy <= rPosy + rHeight) &&
                !isNeighbour(x, y, "l")) {
            track.gameTrack[x][y].setColor(255, 0, 0);
            player.velocity.x = +speed / initialVel;
        }
        if (pPosx <= rPosx + rWidth &&
                pPosx + pWidth >= rPosx + rWidth &&
                (pPosy + pHeight >= rPosy && pPosy <= rPosy + rHeight) &&
                !isNeighbour(x, y, "r")) {
            track.gameTrack[x][y].setColor(1, 1, 1);
            player.velocity.x = -speed / initialVel;
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
        //player.velocity.x *= initialVel;
        //player.velocity.y *= initialVel;

        player.initPlayer();
        if (isKeyPressed[LEFT]) {
            //player.velocity.x -= speed;
            player.angle += 0.04 * player.factor;
            player.steerAngle = - PI * 0.3f;
        }
        if (isKeyPressed[RIGHT]) {
            //player.velocity.x += speed;
            player.angle -= 0.04 * player.factor;
            player.steerAngle = PI * 0.3f;
        }
        player.force.add(PVector.mult(player.vector1, -player.forwardVelocity * 0.1f));
        if (isKeyPressed[UP]) {
           // player.velocity.y -= speed;
            player.force.add(PVector.mult(player.vector1, 1.0f));
        }
        if (isKeyPressed[DOWN]) {
           // player.velocity.y += speed;
            player.force.add(PVector.mult(player.vector1, -0.5f));
        }

        camera.x = (player.position.x - MY_WIDTH / 2);
        camera.y = (player.position.y - MY_HEIGHT / 2);
        player.updatePlayer(camera);

        //player.displayPlayer(255, 255, 40, camera);
    }

    public void keyPressed() {
        isKeyPressed[keyCode] = true;
    }

    public void keyReleased() {
        isKeyPressed[keyCode] = false;
    }
}