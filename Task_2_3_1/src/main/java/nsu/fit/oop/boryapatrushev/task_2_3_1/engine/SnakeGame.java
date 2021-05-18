package nsu.fit.oop.boryapatrushev.task_2_3_1.engine;

import javafx.scene.canvas.GraphicsContext;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Playfield;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes.SnakeLobby;

import nsu.fit.oop.boryapatrushev.task_2_3_1.gui.Painter;


/**
 * Game class designed specifically for the Snake game
 * extended from {@code Game}
 */
public class SnakeGame extends Game {

    private static final int BOT_CNT = 3;

    private final Playfield playfield;
    private final Painter painter;
    private boolean keyIsPressed;
    private final SnakeLobby snakeLobby;

    /**
     * Constructor
     * @param width width of the playground
     * @param height height of the playground
     * @param graphicsContext graphicsContext of canvas
     * @param painter painter for canvas updating
     */
    public SnakeGame(double width, double height, GraphicsContext graphicsContext, Painter painter) {
        super(width, height, graphicsContext);
        this.playfield = new Playfield(width, height, false);
        this.snakeLobby = new SnakeLobby(this.playfield, BOT_CNT, 0);
        this.painter = painter;
        painter.setPlayfield(this.playfield);
        painter.setSnakeLobby(this.snakeLobby);
        reset();
    }

    /**
     * Extended from {@link Game}
     */
    @Override
    public void update() {
        keyIsPressed = false;
        snakeLobby.update();
    }

    /**
     * Extended from {@link Game}
     */
    @Override
    public void display() {
        painter.paint(isPaused);
    }

    /**
     * Get snakeLobby
     * @return snakeLobby
     */
    public SnakeLobby getSnakeLobby() {
        return snakeLobby;
    }

    /**
     * Extended from {@link Game}
     */
    @Override
    public void reset() {
        playfield.reset();
        snakeLobby.reset();
    }

    /**
     * Get keyPress flag
     * @return true - key was already pressed, otherwise - nope
     */
    public boolean isKeyPressed() {
        return keyIsPressed;
    }

    /**
     * Set keyPressed flag to true
     */
    public void setKeyPressed() {
        keyIsPressed = true;
    }
}
