package nsu.fit.oop.boryapatrushev.task_2_3_1.gui;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import nsu.fit.oop.boryapatrushev.task_2_3_1.engine.Engine;
import nsu.fit.oop.boryapatrushev.task_2_3_1.engine.SnakeGame;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes.Snake;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class which is used to initialize
 * all the necessary objects and load nodes from FXML file
 */
public final class Controller implements Initializable {

    private static final int FPS = 10;

    public Canvas upperCanvas;
    public Label scoreLabel;
    public Label infoLabel;

    private SnakeGame loop;
    private Engine gameEngine;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double width = upperCanvas.getWidth();
        double height = upperCanvas.getHeight();

        GraphicsContext context = upperCanvas.getGraphicsContext2D();

        upperCanvas.setFocusTraversable(true);
        upperCanvas.setOnKeyPressed(this::handler);

        Painter painter = new Painter(scoreLabel, infoLabel, context);

        loop = new SnakeGame(width, height, context, painter);
        gameEngine = new Engine(FPS, loop);

        gameEngine.start();
    }

    /**
     * Handler of the game, used to control the user snake
     * @param e some key is pressed
     */
    private void handler(KeyEvent e) {

        Snake snake = loop.getSnakeLobby().getUserSnake();

        if (loop.isKeyPressed()) {
            return;
        }

        switch (e.getCode()) {
            case W:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setUp();
                }
                break;
            case S:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setDown();
                }
                break;
            case A:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setLeft();
                }
                break;
            case D:
                if (!loop.getPause()) {
                    loop.setKeyPressed();
                    snake.setRight();
                }
                break;
            case ENTER:
                if (!snake.isSafe()) {
                    gameEngine.reset();
                }
                break;
            case ESCAPE:
                if (loop.getPause()) {
                    gameEngine.start();
                } else {
                    gameEngine.pause();
                }
                break;
            default:
                break;
        }
    }
}

