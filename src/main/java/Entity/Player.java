package Entity;

import TileMap.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

    //stats
    private int health;
    private final int maxHealth;
    private final int fire;
    private final int maxFire;

    private boolean dead;
    private boolean flinching;
    private long flinchTime;

    private boolean firing;
    private final int fireCost;
    private final int fireBallDamage;

    private boolean scratching;
    private final int scratchDamage;
    private final int scratchRange;

    private boolean gliding;

    //Anamations

    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {2, 8, 1, 2, 4, 2, 5};

    //actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCH = 6;

    public Player(TileMap tm) {
        super(tm);

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        fallSpeed = 0.15;
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;

        facingRight = true;

        health = maxHealth = 5;
        fire = maxFire = 2500;

        fireCost = 200;
        fireBallDamage = 5;

        scratchDamage = 8;
        scratchRange = 40;

        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/playersprites.gif"));
            sprites = new ArrayList<BufferedImage[]>();

            for (int i = 0; i < 7; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    if (i != 6) {
                        bi[j] = spriteSheet.getSubimage(j * width, i * height, width, height);
                    } else {
                        bi[j] = spriteSheet.getSubimage(j * width * 2, i * height, width , height);
                    }
                }
                sprites.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        animation = new Animation();
        currAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);
    }

    public void setFiring() {
        firing = true;
    }

    public void setScratching() {
        scratching = true;
    }

    public void setGliding(boolean b) {
        gliding = b;
    }

    private void getNextPosition() {
        if(left) {
            dx -= moveSpeed;
            if(dx < -maxSpeed){
                dx = -maxSpeed;
            }
        }
        else if(right) {
            dx += moveSpeed;
            if(dx > maxSpeed){
                dx = maxSpeed;
            }
        }
       else{
            if(dx>0) {
                dx -= stopSpeed;
                if(dx < 0){
                    dx = 0;
                }
            }else if(dx<0){
                dx += stopSpeed;
                if(dx>0){
                    dx = 0;
                }
            }
       }

       if((currAction == SCRATCH || currAction == FIREBALL) && !(jumping || falling)){
            dx = 0;
       }

       if(jumping && !falling) {
           dy = jumpStart;
           falling = true;
       }

       if(falling) {
           if(dy > 0 && gliding) dy += fallSpeed * 0.1;
           else dy += fallSpeed;

           if(dy > 0) jumping = false;
           if(dy < 0 && !jumping) dy += stopJumpSpeed;
           if(dy > maxFallSpeed) dy = maxFallSpeed;
       }
    }

    public void update() {

        //position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //animation

        if (scratching) {
            if (currAction != SCRATCH) {
                currAction = SCRATCH;
                animation.setFrames(sprites.get(SCRATCH));
                animation.setDelay(50);
                width = 60;
            }
            if(this.animation.hasPlayed()){
                scratching = false;
            }
        } else if (firing) {
            if (currAction != FIREBALL) {
                currAction = FIREBALL;
                animation.setFrames(sprites.get(FIREBALL));
                animation.setDelay(100);
                width = 30;
            }
            if(this.animation.hasPlayed()){
                firing = false;
            }

        } else if (dy < 0) {
            if (gliding) {
                if (currAction != GLIDING) {
                    currAction = GLIDING;
                    animation.setFrames(sprites.get(GLIDING));
                    animation.setDelay(100);
                    width = 30;
                }
            } else if (currAction != FALLING) {
                currAction = FALLING;
                animation.setFrames(sprites.get(FALLING));
                animation.setDelay(100);
                width = 30;
            }

        } else if (dy > 0) {
            if (currAction != JUMPING) {
                currAction = JUMPING;
                animation.setFrames(sprites.get(JUMPING));
                animation.setDelay(-1);
                width = 30;
            }

        } else if (left || right) {
            if (currAction != WALKING) {
                currAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(40);
                width = 30;
            }

        } else {
            if (currAction != IDLE) {
                currAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(400);
                width = 30;
            }
        }
        animation.update();
        if (currAction != SCRATCH && currAction != FIREBALL) {
            if(right) facingRight = true;
            if(left) facingRight = false;
        }
    }

    public void draw(Graphics2D g) {
        setMapPosition();

        if (flinching) {
            long elasped = (System.nanoTime() - flinchTime) / 1000000;
            if (elasped / 100 % 2 == 0) {
                return;
            }
        }

        if (facingRight) {
            g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
        } else {
            g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2), -width, height, null);
        }
    }


}
