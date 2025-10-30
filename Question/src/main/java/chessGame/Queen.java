package chessGame;

import chessLib.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Represents a Queen chess piece.
 * The Queen moves diagonally, horizontally, or vertically in any direction,
 * for any distance within board boundaries.
 * According to game rules, it can jump over occupied positions but cannot land on them.
 */
public class Queen implements ChessPiece {
    
    private Position position;
    
    /**
     * Creates a new Queen at the specified initial position.
     * 
     * @param initialPosition the starting position of the queen
     */
    public Queen(Position initialPosition) {
        this.position = initialPosition;
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
        Collection<Position> validMoves = new ArrayList<>();
        
        // Eight directions: 4 diagonal + 4 straight (horizontal and vertical)
        int[][] directions = {
            // Diagonal directions (like Bishop)
            {1, 1},   // Top-right diagonal
            {-1, 1},  // Top-left diagonal
            {1, -1},  // Bottom-right diagonal
            {-1, -1}, // Bottom-left diagonal
            
            // Straight directions (horizontal and vertical)
            {1, 0},   // Right (horizontal)
            {-1, 0},  // Left (horizontal)
            {0, 1},   // Up (vertical)
            {0, -1}   // Down (vertical)
        };
        
        // Check each direction
        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            
            // Move along this direction until we hit the board boundary
            for (int step = 1; step <= 8; step++) {
                int newX = position.x() + (dx * step);
                int newY = position.y() + (dy * step);
                Position newPos = new Position(newX, newY);
                
                // Check bounds FIRST before checking occupiedPositions
                if (!BoardUtils.isWithinBounds(newPos)) {
                    break; // Stop in this direction when we hit the boundary
                }
                
                // Skip occupied positions (can jump over them, but can't land on them)
                // Continue to check further positions in this direction
                if (!occupiedPositions.contains(newPos)) {
                    validMoves.add(newPos);
                }
            }
        }
        
        return validMoves;
    }
}

