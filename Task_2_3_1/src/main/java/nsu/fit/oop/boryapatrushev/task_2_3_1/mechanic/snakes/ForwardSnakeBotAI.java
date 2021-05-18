package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes;


import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Playfield;

/**
 * Forward snake bot AI based on {@link SnakeBotAI} specification.
 * Snake always move forward.
 */
public class ForwardSnakeBotAI extends SnakeBotAI {

    /**
     * Constructor
     *
     * @param snake snake to control
     * @param playfield playfield to get info from
     */
    public ForwardSnakeBotAI(Snake snake, Playfield playfield) {
        super(snake, playfield);
        snake.setDown();
    }

    @Override
    public void update() {

    }
}
