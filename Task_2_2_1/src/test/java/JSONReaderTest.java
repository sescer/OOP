import nsu.fit.oop.boryapatrushev.task_2_2_1.Utils.JSONReader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JSONReaderTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test1() {

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("file is not found!");

        JSONReader.readJsonBakerList("fileeee");
        JSONReader.readJsonCourierList("fileeee");
        JSONReader.readJsonWarehouseConfig("fileeee");
    }
}
