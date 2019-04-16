import processing.core.PApplet;
import processing.core.PVector;

import java.util.Arrays;
import java.util.HashMap;

public class Agent {

    private final Main environment;
    private PVector pos;
    private HashMap<PVector, float[]> table;// State, actionValues (indecis: up down left right)
    boolean dead;

    public static final int up = 0, down = 1, left = 2, right = 3;
    public static final PVector moveUp = new PVector(0, -Main.tileWidth), moveDown = new PVector(0, +Main.tileWidth), moveLeft = new PVector(-Main.tileWidth, 0), moveRight = new PVector(Main.tileWidth, 0);
    private float percentIncomplete = 1;
    private float decay = 0.0001f;
    private float learningRate = 0.01f;

    public Agent(Main environment) {
        pos = new PVector(Main.tiles[0][0].getX(), Main.tiles[0][0].getX());
        table = new HashMap<>();
        this.environment = environment;

        for (Tile[] tiles : Main.tiles) {
            for (Tile t : tiles) {
                table.put(new PVector(t.getX(), t.getY()), new float[4]);
            }
        }
    }

    public void move(int action) {
        if (dead) {
            return;
        }
        if (action == up) {
            pos.add(moveUp);
        } else if (action == down) {
            pos.add(moveDown);
        } else if (action == left) {
            pos.add(moveLeft);
        } else if (action == right) {
            pos.add(moveRight);
        }


        if (pos.x >= Main.tileWidth * Main.tilesPerRow || pos.x < 0 || pos.y >= Main.tileWidth * Main.tilesPerCol || pos.y < 0 || Main.tiles[(int) (pos.y / Main.tileWidth)][(int) (pos.x / Main.tileWidth)].isRed()) {
            dead = true;

        }
    }

    public boolean simulate() {

        int move = chooseMove();
        move(move);
        draw();

        return dead;
    }

    private int chooseMove() {

        if (Math.random() > percentIncomplete) {
            return (int) (Math.random() * 4);
        } else {

            int maxIndex = 0;
            float[] actionsAtCurrentState = table.get(pos);

            for (int i = 0; i < actionsAtCurrentState.length; i++) {
                if(actionsAtCurrentState[i]>actionsAtCurrentState[maxIndex]){
                    maxIndex = i;
                }
            }

            if(actionsAtCurrentState[maxIndex]==0){
                return (int) (Math.random() * 4);
            }

            return maxIndex;
        }

    }

    public void draw() {
        if (dead) {
            return;
        }

        environment.fill(0, 255, 0);
        environment.rect(pos.x, pos.y, Main.tileWidth, Main.tileWidth);

    }
}
