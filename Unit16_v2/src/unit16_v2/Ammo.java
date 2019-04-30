package unit16_v2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing {

    private int speed;

    public Ammo() {
        this(0, 0, 0);
    }

    public Ammo(int x, int y) {
        this(x, y, 0);
    }

    public Ammo(int x, int y, int s) {
        super(x, y);
        speed = s;
    }

    public void setSpeed(int s) {
        //add code
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void draw(Graphics window) {
        window.setColor(Color.WHITE);
        window.fillOval(this.getX(), this.getY(), 5, 5);
    }

    public void move(String direction) {
        
        this.setPos(this.getX(), this.getY() - this.getSpeed());
        
    }
    
    public void move() {
        
        this.setPos(this.getX(), this.getY() - this.getSpeed());
        
    }

    public String toString() {
        return "ammo";
    }
}
