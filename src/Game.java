import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

public class Game extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Game");
    }

    final public int MY_WIDTH = 600;
    final public int MY_HEIGHT = 600;
    final public static int STARTING_BULLETS = 5;
    public static int numBullets = 10;
    static final int PLAYER_VEL = 3;
    final int SEP_THRESHOLD = 50 ;
    final float MAX_ACCEL = 10f ;
    int currentWave = 0;
    int waveTimer;
    int interval;

    Character player;
    ArrayList<SeekAi> chaseAIs;
    RunAi runAi;
    PVector pursueTarget, direction, runAccel;

    private int numSeekAis = 10;

    private int targetX = MY_WIDTH / 2;
    private int targetY = MY_HEIGHT / 2;

    PVector targetVel;
    ArrayList<Bullet> bullet;
    static final int DIAM = 48, SPD = 4, FPS = 60;


    ArrayList<Missile> missiles;

    public void settings() {
        size(MY_WIDTH, MY_HEIGHT);
        smooth(4);
    }

    public void setup() {
        frameRate(FPS);
        player = new Character(MY_WIDTH / 2, MY_HEIGHT / 2, 0f, 0f, 0f, this);
        //chaseAI = new SeekAi(0, 0, 0f, 1f, 1f, 0f, this);
        chaseAIs = new ArrayList<>();
//        this.chaseAIs = createChaseAi();
        runAi = new RunAi(MY_WIDTH / 2, MY_HEIGHT / 2, 0f, 1f, 1f, this);
        runAccel = new PVector(0, 0);
        pursueTarget = new PVector(0, 0);
        direction = new PVector(0, 0);
        targetVel = new PVector(0, 0);
        bullet = new ArrayList<>();
        missiles = new ArrayList<>();
    }

    // Example of how the character might be depicted.
    public void draw() {
        background(128);

        // Draw running ai
        float xr = runAi.position.x, yr = runAi.position.y;
        fill(255, 0, 0);
        ellipse(xr, yr, 30, 30);
        // Show orientation
        int newxr = (int) (xr + 10 * cos(runAi.orientation));
        int newyr = (int) (yr + 10 * sin(runAi.orientation));
        fill(0);
        ellipse(newxr, newyr, 10, 10);

        runAccel.x = 0;
        runAccel.y = 0;
        PVector fe = new PVector(runAi.position.x - player.position.x, runAi.position.y - player.position.y);
        float feDistance = fe.mag();
        if (feDistance < SEP_THRESHOLD) {
            fe.normalize();
            fe.mult(MAX_ACCEL * (SEP_THRESHOLD - feDistance) / SEP_THRESHOLD);
            runAccel.add(fe);
        }
        runAi.integrate(runAccel);


//        for (int i = 0; i < bullet.size(); i++) {
//            if (bullet.get(i).timer <= 0) {
//                bullet.remove(i);
//            }
//        }

//        for (int i = 0; i < bullet.size(); i++) {
//            Bullet missile = bullet.get(i);
//            float d = dist(bullet.get(i).position.x, bullet.get(i).position.y, bullet.get(i).start.x, bullet.get(i).start.y);
//            //if ((bullet.get(i).size > d)) {
//            //    missile.explode();
////                for (int j =0; j < numBullets; j++){
////                    detectBulletCollision(chaseAIs.get(j), missile);
////                }
//            //} else {
//               missile.project();
//            //}
//
//        }

        for (Missile missile: missiles){
            missile.integrate();
            ellipse(missile.position.x, missile.position.y, 2, 2);
        }

        player.updatePlayer();
        player.confinePlayer();
        player.displayPlayer();

        //float prediction = distance / speed ;
        for (int i = 0; i < chaseAIs.size(); i++) {
            chaseAIs.get(i).integrate(player.position, 0);
        }
//        updateWave();
    }

    public void detectBulletCollision(SeekAi ai, Bullet bullet){
        float d = dist(ai.position.x, ai.position.y, bullet.position.x, bullet.position.y);
        if ((ai.size/2 + bullet.size/2 > d)) {
            //chaseAIs.;
            System.out.println("Here");
        }
    }

    public ArrayList<SeekAi> createChaseAi(){
        ArrayList<SeekAi> seekAi = new ArrayList<>();
        for (int i = 0; i < numSeekAis; i++){
            Random generator = new Random();
            int x = generator.nextInt(MY_WIDTH) + 1;
            int y = generator.nextInt(MY_HEIGHT) + 1;
            seekAi.add(new SeekAi(x, y, 0f, 1f, 1f, 0f, this));
        }
        return seekAi;
    }

    public boolean waveFinished(){
        if (waveTimer == 0) {
            return true;
        } else {
            waveTimer--;
            return false;
        }
    }

    public void updateWave(){
        if (waveFinished()){
            if (interval > 0){
                background(0);
                textSize(20);
                text("Wave " + currentWave + " End", 350, 300);
                interval--;
            } else {
                numBullets += 10;
                currentWave++;
                numSeekAis += 5;
                this.chaseAIs = createChaseAi();
                waveTimer = 1000;
                interval = 200;
            }
        }
    }


    //as you progress through levels, different ai come after you
    //create a trapping ai?
    //add health to player, if ai touches player, subtract health
    //need to add collisions
    //enemies start in random places, preferably in corners


    // Update targetVel
    public void mousePressed() {
        if (mouseButton == LEFT) {

            shootMissile(mouseX, mouseY);

        } else if (mouseButton == RIGHT) {
            bullet.add(new Bullet(player.position.x, player.position.y, true, mouseX, mouseY, this));
            numBullets--;
        } else {
            fill(126);
        }
    }

    private void shootMissile(int mouseX, int mouseY) {
        PVector missileVelocity = new PVector(mouseX, mouseY);
        missileVelocity.sub(player.position.copy()).normalize();

        missileVelocity.mult(100);
        missiles.add(new Missile(player.position.copy(), missileVelocity));
        // need to remove bullets once they are out of bounds
    }

    public void keyPressed() {
        final int k = keyCode;
        if (k == LEFT | k == 'A') player.velocity.x = -PLAYER_VEL;
        else if (k == RIGHT | k == 'D') player.velocity.x = PLAYER_VEL;
        else if (k == UP | k == 'W') player.velocity.y = -PLAYER_VEL;
        else if (k == DOWN | k == 'S') player.velocity.y = PLAYER_VEL;
    }

    public void keyReleased() {
        final int k = keyCode;
        if (k == LEFT | k == 'A' && player.velocity.x < 0
                || k == RIGHT | k == 'D' && player.velocity.x > 0)
            player.velocity.x = 0;

        else if (k == UP | k == 'W' && player.velocity.y < 0
                || k == DOWN | k == 'S' && player.velocity.y > 0)
            player.velocity.y = 0;
    }
}