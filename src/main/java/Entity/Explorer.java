package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Explorer extends MapObject {

    //stats
    private int health;
    private final int maxHealth;

    private boolean dead;
    private boolean flinching;
    private long flinchTime;


    private boolean attacking;
    private final int attackDamage;
    private final int attackRange;

    private boolean gliding;

    //Anamations

    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {5, 8, 5, 8, 10, 10, 10};

    //actions
    private static final int PIDLE = 0;
    private static final int PWALKING = 1;
    private static final int HIDLE = 2;
    private static final int HWALKING = 3;
    private static final int ATTACK = 4;
    private static final int WIELD = 5;
    private static final int HOLSTER = 6;

    public Explorer(TileMap tm) {
        super(tm);

        width = 48;
        height = 48;
        cwidth = 30;
        cheight = 30;

        fallSpeed = 0.15;
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;

        facingRight = true;

        health = maxHealth = 5;

        attackDamage = 8;
        attackRange = 40;

        try {
            BufferedImage spriteSheetPI = ImageIO.read(getClass().getResourceAsStream("/Sprites/Explorer/PassiveIdleReaper-Sheet.png"));
            BufferedImage spriteSheetPW = ImageIO.read(getClass().getResourceAsStream("/Sprites/Explorer/PassiveRunningReaper-Sheet.png"));
            BufferedImage spriteSheetHI = ImageIO.read(getClass().getResourceAsStream("/Sprites/Explorer/HostileIdleReaper-Sheet.png"));
            BufferedImage spriteSheetHW = ImageIO.read(getClass().getResourceAsStream("/Sprites/Explorer/HostileRunningReaper-Sheet.png"));
            BufferedImage spriteSheetA = ImageIO.read(getClass().getResourceAsStream("/Sprites/Explorer/HostileAttackReaper-Sheet.png"));
            BufferedImage spriteSheetW = ImageIO.read(getClass().getResourceAsStream("/Sprites/Explorer/WieldWeaponReaper-Sheet.png"));
            BufferedImage spriteSheetH = ImageIO.read(getClass().getResourceAsStream("/Sprites/Explorer/HolsterWeaponReaper-Sheet.png"));
            sprites = new ArrayList<BufferedImage[]>();

            BufferedImage[] bi = new BufferedImage[numFrames[0]];
            for (int j = 0; j < numFrames[0]; j++) {
                bi[j] = spriteSheetPI.getSubimage(j * width, 0, width, height);
            }
            sprites.add(bi);

            BufferedImage[] bi1 = new BufferedImage[numFrames[1]];
            for (int j = 0; j < numFrames[1]; j++) {
                bi1[j] = spriteSheetPW.getSubimage(j * width, 0, width, height);
            }
            sprites.add(bi1);

            BufferedImage[] bi2 = new BufferedImage[numFrames[2]];
            for (int j = 0; j < numFrames[2]; j++) {
                bi2[j] = spriteSheetHI.getSubimage(j * width, 0, width, height);
            }
            sprites.add(bi2);
            BufferedImage[] bi3 = new BufferedImage[numFrames[3]];
            for (int j = 0; j < numFrames[3]; j++) {
                bi3[j] = spriteSheetHW.getSubimage(j * width, 0, width, height);
            }
            sprites.add(bi3);
            BufferedImage[] bi4 = new BufferedImage[numFrames[4]];
            for (int j = 0; j < numFrames[4]; j++) {
                bi4[j] = spriteSheetA.getSubimage(j * width, 0, width, height);
            }
            sprites.add(bi4);

            BufferedImage[] bi5 = new BufferedImage[numFrames[5]];
            for (int j = 0; j < numFrames[5]; j++) {
                bi5[j] = spriteSheetW.getSubimage(j * width, 0, width, height);
            }
            sprites.add(bi5);
            BufferedImage[] bi6 = new BufferedImage[numFrames[6]];
            for (int j = 0; j < numFrames[6]; j++) {
                bi6[j] = spriteSheetH.getSubimage(j * width, 0, width, height);
            }
            sprites.add(bi6);

        } catch (Exception e) {
            e.printStackTrace();
        }
        animation = new Animation();
        currAction = PIDLE;
        animation.setFrames(sprites.get(PIDLE));
        animation.setDelay(400);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setAttacking() {
        attacking = true;
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

       if((currAction == ATTACK) && !(jumping || falling)){
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

        if (attacking) {
            if (currAction != ATTACK) {
                currAction = ATTACK;
                animation.setFrames(sprites.get(ATTACK));
                animation.setDelay(50);
                width = 60;
            }
            if(this.animation.hasPlayed()){
                attacking = false;
            }

        } else if (dy < 0) {
//            if (gliding) {
//                if (currAction != GLIDING) {
//                    currAction = GLIDING;
//                    animation.setFrames(sprites.get(GLIDING));
//                    animation.setDelay(100);
//                    width = 30;
//                }
//            } else if (currAction != FALLING) {
//                currAction = FALLING;
//                animation.setFrames(sprites.get(FALLING));
//                animation.setDelay(100);
//                width = 30;
//            }

        } else if (dy > 0) {
//            if (currAction != JUMPING) {
//                currAction = JUMPING;
//                animation.setFrames(sprites.get(JUMPING));
//                animation.setDelay(-1);
//                width = 30;
//            }

        } else if (left || right) {
            if (currAction != PWALKING) {
                currAction = PWALKING;
                animation.setFrames(sprites.get(PWALKING));
                animation.setDelay(40);
                width = 30;
            }

        } else {
            if (currAction != PIDLE) {
                currAction = PIDLE;
                animation.setFrames(sprites.get(PIDLE));
                animation.setDelay(400);
                width = 30;
            }
        }
        animation.update();
        if (currAction != ATTACK ) {
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
            g.drawImage(animation.getImage(), (int) (x + xmap - (3*width/4)), (int) (y + ymap - height / 2), null);
        } else {
            g.drawImage(animation.getImage(), (int) (x + xmap), (int) (y + ymap - height / 2), -width, height, null);
        }
    }


}
