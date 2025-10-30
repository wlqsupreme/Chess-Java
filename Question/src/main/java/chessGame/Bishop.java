package chessGame;

import chessLib.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Represents a Bishop chess piece.
 * The Bishop moves diagonally in any direction, for any distance within board boundaries.
 * According to game rules, it can jump over occupied positions but cannot land on them.
 */
public class Bishop implements ChessPiece {
    
    private Position position;
    
    /**
     * Creates a new Bishop at the specified initial position.
     * 
     * @param initialPosition the starting position of the bishop
     */
    public Bishop(Position initialPosition) {
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
        
        // Four diagonal directions: (dx, dy)
        // Top-right, Top-left, Bottom-right, Bottom-left
        int[][] directions = {
            {1, 1},   // Top-right
            {-1, 1},  // Top-left
            {1, -1},  // Bottom-right
            {-1, -1}  // Bottom-left
        };
        
        // Check each diagonal direction
        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];
            
            // Move along this diagonal until we hit the board boundary
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
                if (!isPositionOccupied(newPos, occupiedPositions)) {
                    validMoves.add(newPos);
                }
            }
        }
        
        return validMoves;
    }
    
    /**
     * Helper method to check if a position is occupied.
     * Uses manual comparison to avoid relying on hashCode().
     */
    private boolean isPositionOccupied(Position pos, Set<Position> occupiedPositions) {
        for (Position occupied : occupiedPositions) {
            if (occupied.equals(pos)) {
                return true;
            }
        }
        return false;
    }
}

