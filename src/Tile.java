import processing.core.PApplet;

import java.awt.*;

public class Tile {

    private int x,y;
    private int color;
    private float qVal;

    public Tile(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() { return x; }
    public int getColor() { return color; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setColor(int color) { this.color = color; }

    public void draw(PApplet window){
        window.fill(color);
        window.rect(x,y,Main.tileWidth,Main.tileWidth);
    }


    public boolean isRed() {
        if(color==(new Color(255,0,0)).getRGB()){
            return true;
        }
        return false;
    }

    public String toString(){
        return x + ", " + y;
    }
}
