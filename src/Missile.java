import processing.core.PVector;

/**
 * Created by Bipaswi on 01/11/2017.
 */
public class Missile {

    PVector position; //where is it
    PVector velocity;

    public Missile(PVector position, PVector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public void integrate(){
        position.add(velocity);
    }



}
