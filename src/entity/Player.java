package entity;

import graphics.Sprite;
import states.PlayState;
import utils.KeyHandler;
import utils.MouseHandler;
import utils.Vector2f;

import java.awt.*;

import static utils.KeyHandler.keys;

public class Player extends Entity{
    public Player(Sprite sprite, Vector2f origin, int size) {

        super(sprite, origin, size);
        acc =3f;
        maxSpeed =4.15f;
        bounds.setWidth(50);
        bounds.setHeight(50);
        bounds.setxOffset(35);
        bounds.setyOffset(60);
    }

    public void move() {
        if (up) {
            dy -= acc;
            if( dy < -maxSpeed) dy = -maxSpeed;
        } else {
            if (dy < 0) {
                dy += deacc;
                if(dy > 0) dy = 0;
            }
        }    if (down) {
            dy += acc;
            if( dy > maxSpeed) dy = maxSpeed;
        } else {
            if (dy > 0) {
                dy -= deacc;
                if(dy < 0) dy = 0;
            }
        }   if (left) {
            dx -= acc;
            if( dx < -maxSpeed) dx = -maxSpeed;
        } else {
            if (dx < 0) {
                dx += deacc;
                if(dx > 0) dx = 0;
            }
        }   if (right) {
            dx += acc;
            if( dx > -maxSpeed) dx = maxSpeed;
        } else {
            if (dx > 0) {
                dx -= deacc;
                if(dx < 0) dx = 0;
            }
        }


    }
    public void update() {
        super.update();
        move();
        if(!bounds.collisionTiles(dx, 0)) {// collisionTile
            PlayState.map.x += dx;
            pos.x += dx;


        }
        if(!bounds.collisionTiles(0,dy)) {
            PlayState.map.y += dy;
            pos.y += dy;

        }
    }
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.yellow);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), (int) (pos.getWorldVar().y + bounds.getYOffset()), (int )bounds.getWidth(), (int )bounds.getHeight());
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);

    }
    public void input(MouseHandler mouse, KeyHandler key) {

        if (mouse.getButton() == 1) {
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }
        if(key.up.down) {
            up = true;
        } else {
            up = false;
        }
        if(key.down.down) {
            down = true;
        }  else {
            down = false;
        }
        if(key.left.down) {
            left = true;
        }  else {
            left = false;
        }
        if(key.right.down) {
            right = true;
        }   else {
            right = false;
        }
        if(key.attack.down) {
            attack = true;
        }   else {
            attack = false;
        }
    }
}
