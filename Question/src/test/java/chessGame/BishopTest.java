package chessGame;

import chessLib.Position;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {
    
    @Test
    void testBishopAtCenterWithNoObstacles() {
        // Bishop at center of board (4, 4) with no obstacles
        Bishop bishop = new Bishop(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = bishop.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // From (4,4), bishop can move to:
        // Top-right diagonal: (5,5), (6,6), (7,7), (8,8) = 4 positions
        // Top-left diagonal: (3,5), (2,6), (1,7) = 3 positions
        // Bottom-right diagonal: (5,3), (6,2), (7,1) = 3 positions
        // Bottom-left diagonal: (3,3), (2,2), (1,1) = 3 positions
        // Total: 13 positions
        assertEquals(13, validMoves.size());
        
        // Verify some specific positions
        assertTrue(validMoves.contains(new Position(5, 5)));
        assertTrue(validMoves.contains(new Position(6, 6)));
        assertTrue(validMoves.contains(new Position(3, 3)));
        assertTrue(validMoves.contains(new Position(1, 1)));
        assertTrue(validMoves.contains(new Position(8, 8)));
        
        // Verify non-diagonal positions are not included
        assertFalse(validMoves.contains(new Position(4, 5))); // vertical
        assertFalse(validMoves.contains(new Position(5, 4))); // horizontal
    }
    
    @Test
    void testBishopAtCornerWithNoObstacles() {
        // Bishop at corner (1, 1) with no obstacles
        Bishop bishop = new Bishop(new Position(1, 1));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = bishop.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // From (1,1), bishop can only move along one diagonal: (2,2), (3,3), ..., (8,8)
        // Total: 7 positions
        assertEquals(7, validMoves.size());
        
        // Verify the diagonal positions
        assertTrue(validMoves.contains(new Position(2, 2)));
        assertTrue(validMoves.contains(new Position(3, 3)));
        assertTrue(validMoves.contains(new Position(4, 4)));
        assertTrue(validMoves.contains(new Position(8, 8)));
        
        // Verify other directions are not included
        assertFalse(validMoves.contains(new Position(1, 2)));
        assertFalse(validMoves.contains(new Position(2, 1)));
    }
    
    @Test
    void testBishopWithObstacleCanJumpOver() {
        // Bishop at (4, 4) with obstacle at (5, 5)
        // According to Rule 2: pieces can jump over occupied positions
        Bishop bishop = new Bishop(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Position(5, 5)); // Obstacle
        
        Collection<Position> validMoves = bishop.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Bishop cannot land on (5, 5) but CAN land on (6, 6), (7, 7), (8, 8)
        assertFalse(validMoves.contains(new Position(5, 5)), 
                    "Bishop should not be able to land on occupied position");
        
        // But can jump over it and land beyond
        assertTrue(validMoves.contains(new Position(6, 6)), 
                   "Bishop should be able to jump over occupied position and land at (6,6)");
        assertTrue(validMoves.contains(new Position(7, 7)), 
                   "Bishop should be able to jump over occupied position and land at (7,7)");
        assertTrue(validMoves.contains(new Position(8, 8)), 
                   "Bishop should be able to jump over occupied position and land at (8,8)");
        
        // Other diagonals should still be accessible
        assertTrue(validMoves.contains(new Position(3, 3)));
        assertTrue(validMoves.contains(new Position(2, 2)));
    }
    
    @Test
    void testBishopWithMultipleObstacles() {
        // Bishop at (4, 4) with multiple obstacles
        Bishop bishop = new Bishop(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Position(5, 5));
        occupiedPositions.add(new Position(3, 3));
        occupiedPositions.add(new Position(5, 3));
        
        Collection<Position> validMoves = bishop.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Cannot land on occupied positions
        assertFalse(validMoves.contains(new Position(5, 5)));
        assertFalse(validMoves.contains(new Position(3, 3)));
        assertFalse(validMoves.contains(new Position(5, 3)));
        
        // But can jump over and land beyond
        assertTrue(validMoves.contains(new Position(6, 6)));
        assertTrue(validMoves.contains(new Position(2, 2)));
        assertTrue(validMoves.contains(new Position(6, 2)));
    }
    
    @Test
    void testBishopAtEdge() {
        // Bishop at edge (1, 4) 
        Bishop bishop = new Bishop(new Position(1, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = bishop.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Verify some valid diagonal moves
        assertTrue(validMoves.contains(new Position(2, 5)));
        assertTrue(validMoves.contains(new Position(2, 3)));
        assertTrue(validMoves.contains(new Position(3, 6)));
        
        // Verify positions outside the board are not included
        assertFalse(validMoves.contains(new Position(0, 3)));
        assertFalse(validMoves.contains(new Position(0, 5)));
    }
}

