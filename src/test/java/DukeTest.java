import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2,2);
    }
    @Test
    public void TestIcon() {
        assertEquals("✘", new Deadline("do CS2113", "1/1/2019").getStatusIcon());
    }
    @Test
    public void TestTodo() {
        assertEquals("[T][✘] assignments CS2113", new ToDos("assignments CS2113").toString() );
    }
    @Test
    public void TestDeadline() {
        assertEquals("[D][✘] CS2113 project (by:Mon Sep 09 12:00:00 SGT 2019)",
                new Deadline("CS2113 project", "Mon Sep 09 12:00:00 SGT 2019").toString());
    }
}
