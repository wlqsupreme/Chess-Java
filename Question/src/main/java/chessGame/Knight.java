package chessGame;

import chessLib.KnightMove;
import chessLib.Position;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a Knight chess piece.
 * The Knight moves in an L-shape: 2 squares in one direction and 1 square perpendicular to that.
 */
public class Knight implements ChessPiece {
    
    private Position position;
    private final KnightMove knightMove;
    
    /**
     * Creates a new Knight at the specified initial position.
     * 
     * @param initialPosition the starting position of the knight
     */
    public Knight(Position initialPosition) {
        this.position = initialPosition;
        this.knightMove = new KnightMove();
    }
    
    @Override
    public Position getPosition() {
        return position;
    }
    
    @Override
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }
    
    @Override
    public Collection<Position> getValidMoves(Set<Position> occupiedPositions) {
        // Get all possible moves from KnightMove
        Collection<Position> allMoves = knightMove.validMovesFor(position);
        
        // Filter out occupied positions
        return allMoves.stream()
                .filter(pos -> !occupiedPositions.contains(pos))
                .collect(Collectors.toList());
    }
}

