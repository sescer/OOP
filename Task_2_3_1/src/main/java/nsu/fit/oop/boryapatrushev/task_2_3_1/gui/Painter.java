package nsu.fit.oop.boryapatrushev.task_2_3_1.gui;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes.Snake;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes.SnakeLobby;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Playfield;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

import java.util.ArrayList;

import static nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Playfield.SIZE;

/**
 * Class which is used for updating canvas graphics and info GUI,
 * paints snakes, food, obstacles
 */
public class Painter {

    private static final Color USERSNAKECOLOR = new Color(0.349, 0.509, 0.203, 0.8);
    private static final Color BOTSNAKECOLOR = new Color(0.474, 0.243, 0.145, 0.8);
    private static final Color DEADSNAKECOLOR = new Color(1, 0, 0, 0.8);
    private static final Color FOODCOLOR = new Color(0.9, 0.5, 0, 0.8);
    private static final Color BACKGROUNDCOLOR = new Color(0.682, 0.741, 0.219, 1);
    private static final Color OBSTACLECOLOR = new Color(0.313, 0.317, 0.376, 1);
    private static final Color PLAYFIELDCOLOR = new Color(0.301, 0.509, 0.151, 0.5);

    private static final int SCORE_COEFFICIENT = 100;
    private static final int ARC = 10;

    private final Label score;
    private final Label info;
    private final GraphicsContext gc;
    private Playfield playfield;
    private SnakeLobby snakeLobby;

    /**
     * Constructor
     * @param score score
     * @param info info
     * @param gc graphics canvas
     */
    public Painter(Label score, Label info, GraphicsContext gc) {
        this.score = score;
        this.info = info;
        this.gc = gc;
    }

    /**
     * Set the playfield
     * @param playfield to set
     */
    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }

    /**
     * Set the snakeLobby
     * @param snakeLobby to set
     */
    public void setSnakeLobby(SnakeLobby snakeLobby) {
        this.snakeLobby = snakeLobby;
    }

    /**
     * Main method of the class, displays background,
     * obstacles, food, snakes...
     * @param isPaused pause flag
     */
    public void paint(boolean isPaused) {

        Platform.runLater(() -> {

            paintBackground();
            paintObstacles();
            paintFood();
            paintSnakes();
            paintGUI(isPaused);
            paintPlayfield();

        });
    }

    /**
     * Background painter
     */
    private void paintBackground() {
        gc.setFill(BACKGROUNDCOLOR);
        gc.fillRect(0, 0, playfield.getWidth(), playfield.getHeight());
    }

    /**
     * Obstacles painter
     */
    private void paintObstacles() {
        gc.setFill(OBSTACLECOLOR);
        playfield.getObstacles().forEach(point -> paintPoint(point, false));
    }

    /**
     * Food painter
     */
    private void paintFood() {

        gc.setFill(FOODCOLOR);
        playfield.getFood().forEach(f -> paintPoint(f, true));
    }

    /**
     * Snakes painter
     */
    private void paintSnakes() {
        ArrayList<Snake> snakes = snakeLobby.getSnakes();

        for (Snake s: snakes) {

            if (!s.isSafe()) {
                gc.setFill(DEADSNAKECOLOR);
            } else if (snakes.indexOf(s) == 0) {
                gc.setFill(USERSNAKECOLOR);
            } else {
                gc.setFill(BOTSNAKECOLOR);
            }

            s.getPoints().forEach(point -> paintPoint(point, false));
        }
    }

    /**
     * GUI painter
     * @param isPaused flag if game is paused
     */
    private void paintGUI(boolean isPaused) {

        Snake snake = snakeLobby.getUserSnake();

        // start message
        if (snake.isStill()) {
            info.setText("Press WASD to start the game");
        } else if (!isPaused) {
            info.setText("");
        }

        // pause message
        if (isPaused) {
            info.setText("PAUSE");
        } else if (!snake.isStill()) {
            info.setText("");
        }

        // restart message
        if (!snake.isSafe()) {
            info.setText("Press ENTER to start new game.");
        }

        score.setText("Score : " + SCORE_COEFFICIENT * snake.getPoints().size());
    }

    /**
     * Method for single point paint
     * @param point point to draw
     * @param oval flag if oval shape is required, otherwise - rectangle with smooth arcs
     */
    private void paintPoint(Point point, boolean oval) {
        if (oval) {
            gc.fillOval(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
        } else {
            gc.fillRoundRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE, ARC, ARC);
        }
    }

    /**
     * Paint playfield
     */
    private void paintPlayfield() {

        gc.setStroke(PLAYFIELDCOLOR);

        for (double row = 0; row < playfield.getWidth(); row += SIZE) {
            double y = (int) row + SIZE;
            gc.strokeLine(0, y, playfield.getWidth(), y);
        }

        for (double col = 0; col < playfield.getWidth(); col += SIZE) {
            double x = (int) col + SIZE;
            gc.strokeLine(x, 0, x, playfield.getWidth());
        }
    }
}
