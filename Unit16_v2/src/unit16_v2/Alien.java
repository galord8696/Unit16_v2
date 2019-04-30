package unit16_v2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {

    private int speed;
    private Image image;
    public String direction;

    public Alien() {
        super(0, 0, 30, 30);
        speed = 0;
    }

    public Alien(int x, int y) {
        super(x, y, 30, 30);
        speed = 0;
    }

    public Alien(int x, int y, int s) {
        super(x, y, 30, 30);
        speed = s;
    }

    public Alien(int x, int y, int w, int h, int s) {
        
        super(x, y, w, h);
        speed = s;
        direction = "RIGHT";
        try {
            URL url = getClass().getResource("alien.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
        }
    }

    public void setSpeed(int s) {
        speed = s;
    }
    

    public int getSpeed() {
        return speed;
    }

    public void move(String direction) {
        if (direction.equals("UP")) {
            this.setPos(this.getX(), this.getY() - this.getSpeed());
        }
        if (direction.equals("DOWN")) {
            this.setPos(this.getX(), this.getY() + this.getSpeed());
        }
        if (direction.equals("LEFT")) {
            this.setPos(this.getX() - this.getSpeed(), this.getY());
        }
        if (direction.equals("RIGHT")) {
            this.setPos(this.getX() + this.getSpeed(), this.getY());
        }
    }
    
    public void jump(String direction) {
        if (direction.equals("UP")) {
            this.setPos(this.getX(), this.getY() - 30);
        }
        if (direction.equals("DOWN")) {
            this.setPos(this.getX(), this.getY() + 30);
        }
        if (direction.equals("LEFT")) {
            this.setPos(this.getX() - 30, this.getY());
        }
        if (direction.equals("RIGHT")) {
            this.setPos(this.getX() + 30, this.getY());
        }
    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
//        window.drawOval(50, 50, 50, 50);
    }

    public boolean collide(MovingThing ob) {
        if (ob.getX() > this.getX() && (ob.getX() < this.getX() +this.getWidth())){
            if (ob.getY() > this.getY() && (ob.getY() < this.getY() +this.getHeight())){
                return true;
            }
        }
        return false;
    }

    public boolean inscreen() {
        if (inscreenX(this.getX()) && inscreenY(this.getY())){
            return true;
        }
        return false;
    }
    public boolean inscreenX(int in) {
        if (in < 900 && in > 0) {
            return true;
        }
        return false;
    }
    


    public boolean inscreenY(int in) {
        if (in < 600 && in > 0) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return "";
    }
}
