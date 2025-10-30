package chessGame;

import chessLib.Position;

import java.util.Collection;
import java.util.Set;

/**
 * Interface representing a chess piece in the game.
 * All chess pieces must implement this interface to participate in the game.
 */
public interface ChessPiece {
    
    /**
     * Gets the current position of this chess piece on the board.
     * 
     * @return the current position of the piece
     */
    Position getPosition();
    
    /**
     * Sets the position of this chess piece to a new position.
     * 
     * @param newPosition the new position for the piece
     */
    void setPosition(Position newPosition);
    
    /**
     * Calculates all valid moves for this chess piece given the currently occupied positions.
     * The piece should not be able to move to an occupied position, but can jump over them
     * according to game rules.
     * 
     * @param occupiedPositions a set of all currently occupied positions on the board
     * @return a collection of valid positions this piece can move to
     */
    Collection<Position> getValidMoves(Set<Position> occupiedPositions);
}

