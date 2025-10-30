package chessLib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test void PositionTest() {
        Position pos = new Position(1, 1);
        assertEquals(1, pos.x());
        assertEquals(1, pos.y());

        Position pos2 = new Position(1, 1);
        assertEquals(pos, pos2);
    }
}