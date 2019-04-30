package unit16_v2;

//(c) A+ Computer Science
//www.apluscompsci.com
//Name -
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OuterSpace extends Canvas implements KeyListener, Runnable {

    private Ship ship;
    private Alien alienOne;
    private Alien alienTwo;
    private boolean shoot = true;
    private List<Ammo> ammo;
    private AlienHorde horde;

    /* uncomment once you are ready for this part
     *
     private AlienHorde horde;
     private Bullets shots;
     */
    private boolean[] keys;
    private BufferedImage back;

    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];

        //instantiate other instance variables
        //Ship, Alien
        ammo = new ArrayList<Ammo>();
        ship = new Ship(300, 300, 80, 50, 5);
        horde = new AlienHorde(20);
//        alienOne = new Alien(150, 150, 50, 60, 5);
//        alienTwo = new Alien(120, 120, 50, 60, 5);
        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shop of the current screen and same it as an image
        //that is the exact same width and height as the current screen
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();

        
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);
        graphToBack.setColor(Color.BLUE);
        graphToBack.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        graphToBack.drawString("StarFighter ", 25, 50);
//        graphToBack.setColor(Color.GREEN);
//        graphToBack.fillRect(0, 0, 100, 100);
        
        ship.draw(graphToBack);
        horde.moveEmAll();
//        alienOne.draw(graphToBack);
//        alienTwo.draw(graphToBack);
        
        if (keys[0] == true) {
            ship.move("LEFT");
        }
        if (keys[1] == true) {
            ship.move("RIGHT");
        }
        if (keys[2] == true) {
            ship.move("UP");
        }
        if (keys[3] == true) {
            ship.move("DOWN");
        }
        
        if (keys[4] == true) {
            if (shoot == true) {
                ammo.add(new Ammo(ship.getX() + ship.getWidth() / 2, ship.getY(), 5));
            }
            shoot = false;
        }

        boolean clear = true;
        for (Ammo h : ammo) {
            if (inscreenX(h.getX()) && inscreenY(h.getY())) {
                h.move();
                h.draw(graphToBack);
                clear = false;
                
            }
        }
        
        if (clear){
            ammo.clear();
        }
        
        horde.removeDeadOnes(ammo, graphToBack);
        //add code to move Ship, Alien, etc.
        

        //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship
        twoDGraph.drawImage(back, null, 0, 0);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
            shoot = true;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
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

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}
