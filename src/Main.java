import processing.core.PApplet;

public class Main extends PApplet {


    public static final int tileWidth = 300;
    public static final int tilesPerRow = 5, tilesPerCol = 3;
    public static final Tile[][] tiles = new Tile[tilesPerCol][tilesPerRow];


    boolean isPlaying;
    Agent a;

    public void setup() {
        for (int i = 0; i < tilesPerRow; i++) {
            for (int j = 0; j < tilesPerCol; j++) {
                tiles[j][i] = new Tile(i * tileWidth, tileWidth * j, -1);
            }
        }

        tiles[tiles.length-1][tiles[0].length-1].setColor(color(207,181,59));
        size(tilesPerRow * tileWidth, tilesPerCol * tileWidth);

        a =  new Agent(this);
    }

    public void draw() {

        drawLogistics();
        if(isPlaying){
            a.simulate();
            if (a.dead) {
                a = new Agent(this);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void drawLogistics() {
        background(255);

        for (int i = 0; i < tilesPerRow; i++) {
            for (int j = 0; j < tilesPerCol; j++) {
                this.rect(i * tileWidth, tileWidth * j, tileWidth, tileWidth);
                tiles[j][i].draw(this);
            }
        }
    }


    public void mouseDragged() {
        if (isPlaying){
            return;
        }
        int x, y;

        x = mouseX / tileWidth;
        y = mouseY / tileWidth;
        tiles[y][x].setColor(color(255, 0, 0));

        tiles[tiles.length-1][tiles[0].length-1].setColor(color(207,181,59));
        tiles[0][0].setColor(-1);
    }


    public void keyPressed(){
        if(keyCode == ENTER){
            isPlaying = !isPlaying;
        }
    }


    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
