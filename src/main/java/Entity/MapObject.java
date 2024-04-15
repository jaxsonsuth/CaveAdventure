package Entity;

import GMain.GamePanel;
import GameState.GameState;
import TileMap.Tile;
import TileMap.TileMap;

import java.awt.*;

public abstract class MapObject{
    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;

    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    protected int width;
    protected int height;

    // collision box
    protected int cwidth;
    protected int cheight;

    protected int currRow;
    protected int currCol;

    protected double xdest;
    protected double ydest;
    protected double xtemp;
    protected double ytemp;

    protected boolean topLeft;
    protected boolean topRight;
    protected boolean botLeft;
    protected boolean botRight;

    protected Animation animation;
    protected int currAction;
    protected int prevAction;
    protected boolean facingRight;

    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;

    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;

    public MapObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

//    public Rectangle getRectangle() {
//        return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);
//    }

//    public Boolean intersects(MapObject o) {
//        Rectangle r1 = getRectangle();
//        Rectangle r2 = o.getRectangle();
//        return r1.intersects(r2);
//    }

    public void calculateCorners(double x, double y) {
        int lTile = (int) (x - cwidth / 2) / tileSize;
        int rTile = (int) (x + cwidth / 2 - 1) / tileSize;
        int tTile = (int) (y - cheight / 2) / tileSize;
        int bTile = (int) (y + cheight / 2 - 1) / tileSize;

        int tl = tileMap.getTileType(tTile, lTile);
        int tr = tileMap.getTileType(tTile, rTile);
        int bl = tileMap.getTileType(bTile, lTile);
        int br = tileMap.getTileType(bTile, rTile);

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        botLeft = bl == Tile.BLOCKED;
        botRight = br == Tile.BLOCKED;
    }

    public void checkTileMapCollision() {
        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        calculateCorners(x, ydest);
        if (dy < 0) {
            if (topLeft || topRight) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
            } else {
                ytemp += dy;
            }
        }
        if (dy > 0) {
            if (botLeft || botRight) {
                dy = 0;
                falling = false;
                ytemp = (currRow + 1) * tileSize - cheight / 2;
            } else {
                ytemp += dy;
            }
        }
        calculateCorners(xdest, y);
        if (dx < 0) {
            if (topLeft || botLeft) {
                dx = 0;
                xtemp = currCol * tileSize + cwidth / 2;
            } else {
                xtemp += dx;
            }
        }
        if (dx > 0) {
            if (topRight || botRight) {
                dx = 0;
                xtemp = (currCol + 1) * tileSize - cwidth / 2;
            } else {
                xtemp += dx;
            }
        }
        if (!falling) {
            calculateCorners(x, ydest + 1);
            if (!botLeft && !botRight) {
                falling = true;
            }
        }
    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCwidth() {
        return cwidth;
    }

    public int getCheight() {
        return cheight;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition() {
        xmap = tileMap.getX();
        ymap = tileMap.getY();
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        jumping = b;
    }

    public boolean notOnScreen() {
        return x + xmap + width < 0 ||
                x + xmap - width > GamePanel.WIDTH ||
                y + ymap + height < 0 ||
                y + ymap - height > GamePanel.HEIGHT;

    }

}
