package entity;

import graphics.Animation;
import graphics.Sprite;
import utils.AABB;
import utils.KeyHandler;
import utils.MouseHandler;
import utils.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private int directie;
     private final int IDLE_RIGHT = 0;
    private final int IDLE_LEFT = 1;
    private final int IDLE_UP = 2;
    private final int IDLE_DOWN = 3;
    private final int RIGHT = 4;
    private final int LEFT = 5;
    private final int UP = 6;
    //private final int HURT = 5;
    //private final int DEAD = 6;
    //private final int BUFF = 8;
    private final int DOWN = 7;
    //private final int BLOCK = 10;

    protected Sprite sprite;
    protected Animation ani;
    protected Vector2f pos;
    protected int size;
    protected int currentAnimation;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx, dy;
    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = 1f;

    protected AABB hitBounds;
    public AABB bounds;
    public int getSize() { return size;}
    public Animation getAnimation() { return ani;}
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void setMaxSpeed(float speed) {
        this.maxSpeed = speed;
    }
    public void setAcc(float acc) {
        this.acc = acc;
    }
    public void setDeacc( float deacc) {
        this.deacc = deacc;
    }
    public AABB getBounds() {return bounds;}
    public Entity(Sprite sprite, Vector2f origin, int size ){
        this.sprite = sprite;
        this.pos = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2), origin.y), size, size);
        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }
    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);

    }
    public void animate() {
        if (up) {

            if(currentAnimation != UP || ani.getDelay() == -1)
            {
                setAnimation(UP, sprite.getSpriteArray(UP),5 );
                directie = IDLE_UP;
            }

        } else if (down) {
            if(currentAnimation != DOWN || ani.getDelay() == -1)
            {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN),5 );
                directie = IDLE_DOWN;
            }
        }else if (left) {
            if(currentAnimation != LEFT || ani.getDelay() == -1)
            {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT),5 );
                directie = IDLE_LEFT;
            }
        }else if (right) {
            if(currentAnimation != RIGHT || ani.getDelay() == -1)
            {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT),5 );
                directie = IDLE_RIGHT;
            }
        } else {
            setAnimation(directie, sprite.getSpriteArray(directie), 5);
        }
    }

    private void setHitBoxDirection() {
        if(up) {
            hitBounds.setyOffset(-size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if(down) {
            hitBounds.setyOffset(size / 2);
            hitBounds.setxOffset(-size / 2);
        }
        else if(left) {
            hitBounds.setxOffset(-size);
            hitBounds.setyOffset(0);
        }
        else if(right) {
            hitBounds.setxOffset(0);
            hitBounds.setyOffset(0);
        }
    }
    public void update() {
        animate();
        setHitBoxDirection();
        ani.update();
    }
    public abstract void render(Graphics2D g);
    public void input(KeyHandler key, MouseHandler mouse) {

    }



}
