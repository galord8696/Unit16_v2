package unit16_v2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {

    private List<Alien> aliens;
    private int yp;
    private int xp;
    private String dirc;

    public AlienHorde(int size) {
        int est = size / 10;
        xp = 10;
        dirc = "RIGHT";
        yp = 10;
        if (est == 0) {
            est = 1;
        }

        aliens = new ArrayList<Alien>();
        for (int z = 0; z < est; z++) {
            xp = 10;
            for (int i = 0; i < 10; i++) {
                aliens.add(new Alien(xp, yp, 50, 55, 1));
                xp += 55;

            }
            yp += 60;
        }
    }

    public void add(Alien al) {
        aliens.add(al);
    }

    public void drawEmAll(Graphics window) {
        for (Alien x : aliens) {
            x.draw(window);
        }
    }

    public void moveEmAll() {
        for (Alien x : aliens) {
            if (x.getX() <= 0){
                x.jump("DOWN");
                x.direction = "RIGHT";
            }
            if (x.getX() >= 600){
                x.jump("DOWN");
                x.direction = "LEFT";
            }
            x.move(x.direction);
        }
    }

    public void removeDeadOnes(List<Ammo> shots, Graphics window) {
        List<Alien> temp = new ArrayList<Alien>();
        boolean been = false;
        boolean clear = true;
        for (Alien x : aliens) {
            been = false;
            for (Ammo a : shots) {
                if (x.collide(a)) {
                    a.setX(1000);

                    x.setWidth(0);
                    x.setHeight(0);
                    x.draw(window);
                    been = true;
                    break;
                }
            }
            if (been == false) {
                temp.add(x);
            }
        }

        aliens.clear();
        for (Alien v : temp) {
            aliens.add(v);
            v.draw(window);
        }
    }

    public String toString() {
        return "";
    }
}
