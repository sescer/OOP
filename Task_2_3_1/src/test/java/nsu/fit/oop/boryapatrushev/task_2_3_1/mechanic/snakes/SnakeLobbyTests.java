package nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.snakes;

import org.junit.Assert;
import org.junit.Test;

import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Playfield;
import nsu.fit.oop.boryapatrushev.task_2_3_1.mechanic.Point;

public class SnakeLobbyTests {
    private Playfield playfield;
    private SnakeLobby snakeLobby;

    /**
     * Check tail
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test1() {

        playfield = new Playfield(1000, 1000, true);
        init();

        snakeLobby.update();
        snakeLobby.update();

        Assert.assertEquals(snakeLobby.getUserSnake().getPoints().size(), 3);
    }

    /**
     * Check kill
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public void test2() {
        playfield = new Playfield(880, 1000, true);
        init();

        snakeLobby.update();
        snakeLobby.update();

        Assert.assertNotEquals(snakeLobby.getSnakes().get(1).getHead(), new Point(27, 26));
    }

    private void init() {
        snakeLobby = new SnakeLobby(playfield, 1, 1);
        snakeLobby.reset();

        snakeLobby.getUserSnake().setRight();

        snakeLobby.getUserSnake().extend();
        snakeLobby.getUserSnake().extend();
        snakeLobby.getUserSnake().extend();
        snakeLobby.getUserSnake().extend();
    }
}
