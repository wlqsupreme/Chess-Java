package chessGame;

import chessLib.Position;

/**
 * Utility class for board-related operations.
 */
public class BoardUtils {
    
    /**
     * Checks if a position is within the valid bounds of the chess board.
     * Valid positions have both X and Y coordinates between 1 and 8 (inclusive).
     * 
     * @param pos the position to check
     * @return true if the position is within bounds, false otherwise
     */
    public static boolean isWithinBounds(Position pos) {
        return pos.x() >= 1 && pos.x() <= 8 && pos.y() >= 1 && pos.y() <= 8;
    }
}

