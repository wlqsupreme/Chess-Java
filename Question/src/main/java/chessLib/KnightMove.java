package chessLib;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMove {
    public final static int[][] MOVES = { {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};

    public Collection<Position> validMovesFor(Position pos) {
        ArrayList<Position> result = new ArrayList<Position>();
        for (int[] ms : MOVES) {
            int newX = pos.x() + ms[0];
            int newY = pos.y() + ms[1];

            if (newX > 8 || newX < 1 || newY > 8 || newY < 1)
                continue;

            result.add(new Position(newX, newY));
        }

        return result;
    }
}