package nsu.fit.oop.boryapatrushev.task_2_3_1.engine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.util.Duration;

/**
 * The game engine is necessary for the correct call
 * of the update functions of the playground.
 */
public class Engine {

    private final int frameRate;
    private final Game game;
    private final Timeline gameLoop;

    /**
     * Constructor
     * @param frameRate frame rate of the game (speed)
     * @param game game instance
     */
    public Engine(int frameRate, Game game) {
        this.frameRate = frameRate;
        this.game = game;
        game.setEngine(this);
        gameLoop = createLoop();
    }

    /**
     * Method for updates (used in keyframe)
     */
    private void run(Event e) {
        game.update();
        game.display();
    }

    /**
     * Start game from the beginning
     */
    public void start() {
        gameLoop.playFromStart();
        game.setPause(false);
    }

    /**
     * Stop and exit
     */
    public void stop() {
        gameLoop.stop();
    }

    /**
     * Pause the game
     */
    public void pause() {
        gameLoop.pause();
        game.setPause(true);
        game.display();
    }

    /**
     * Reset the game
     */
    public void reset() {
        this.stop();
        game.reset();
        this.start();
    }

    /**
     * Creation of the game loop according to specified frame rate
     * @return gameloop
     */
    private Timeline createLoop() {
        final Duration d = Duration.millis(1000 / frameRate);
        final KeyFrame oneFrame = new KeyFrame(d, this::run);
        Timeline t = new Timeline(frameRate, oneFrame);
        t.setCycleCount(Animation.INDEFINITE);
        return t;
    }
}
