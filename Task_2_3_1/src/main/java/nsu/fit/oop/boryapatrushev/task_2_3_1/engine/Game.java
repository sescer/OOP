package nsu.fit.oop.boryapatrushev.task_2_3_1.engine;

import javafx.scene.canvas.GraphicsContext;

/**
 * An abstract class of a game.
 */
public abstract class Game {

    GraphicsContext gc;
    Engine engine;
    boolean isPaused;

    double w;
    double h;

    /**
     * Constructor
     * @param width width of the playground
     * @param height height of the playground
     * @param gc graphicContext of the canvas
     */
    public Game(double width, double height, GraphicsContext gc) {
        w = width;
        h = height;
        this.gc = gc;
    }

    /**
     * Set game engine
     * @param engine engine to set
     */
    final void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Set pause flag
     * @param pause if true - pause, false - nope
     */
    public void setPause(boolean pause) {
        this.isPaused = pause;
    }

    /**
     * Get pause flag
     * @return pause true  - pause, false - nope
     */
    public boolean getPause() {
        return isPaused;
    }

    /**
     * The method in which all game events should be updated.
     */
    public abstract void update();

    /**
     * The method in which all visual events should display on the user screen.
     */
    public abstract void display();

    /**
     * The method in which all game events should be reset.
     */
    public abstract void reset();
}
