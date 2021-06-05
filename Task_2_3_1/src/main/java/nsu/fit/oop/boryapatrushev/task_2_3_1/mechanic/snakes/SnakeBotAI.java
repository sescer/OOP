package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes;


import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Playfield;

/**
 * Template class for custom snake bots AI
 * Provides all methods for interaction with game engine
 */
public abstract  class SnakeBotAI {
    Snake snake;
    Playfield playfield;

    /**
     * Constructor
     * @param snake snake to control
     * @param playfield  playfield to get info from
     */
    public SnakeBotAI(Snake snake, Playfield playfield) {
        this.snake = snake;
        this.playfield = playfield;
    }

    /**
     * The method in which snake controlling should happen (rotations, etc.),
     * it should provide acceptable snake behaviour
     */
    public abstract void update();
}

