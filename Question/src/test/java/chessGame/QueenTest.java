package chessGame;

import chessLib.Position;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    
    @Test
    void testQueenAtCenterWithNoObstacles() {
        // Queen at center of board (4, 4) with no obstacles
        Queen queen = new Queen(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = queen.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // From (4,4), queen can move:
        // Diagonally: 13 positions (same as bishop)
        // Horizontally: 7 positions (1,4), (2,4), (3,4), (5,4), (6,4), (7,4), (8,4)
        // Vertically: 7 positions (4,1), (4,2), (4,3), (4,5), (4,6), (4,7), (4,8)
        // Total: 13 + 7 + 7 = 27 positions
        assertEquals(27, validMoves.size());
        
        // Test diagonal moves
        assertTrue(validMoves.contains(new Position(5, 5)));
        assertTrue(validMoves.contains(new Position(3, 3)));
        assertTrue(validMoves.contains(new Position(1, 1)));
        assertTrue(validMoves.contains(new Position(8, 8)));
        
        // Test horizontal moves
        assertTrue(validMoves.contains(new Position(1, 4)));
        assertTrue(validMoves.contains(new Position(8, 4)));
        assertTrue(validMoves.contains(new Position(5, 4)));
        
        // Test vertical moves
        assertTrue(validMoves.contains(new Position(4, 1)));
        assertTrue(validMoves.contains(new Position(4, 8)));
        assertTrue(validMoves.contains(new Position(4, 5)));
    }
    
    @Test
    void testQueenAtCornerWithNoObstacles() {
        // Queen at corner (1, 1) with no obstacles
        Queen queen = new Queen(new Position(1, 1));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = queen.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // From (1,1), queen can move:
        // Diagonally: 7 positions along (2,2), (3,3), ..., (8,8)
        // Horizontally: 7 positions (2,1), (3,1), ..., (8,1)
        // Vertically: 7 positions (1,2), (1,3), ..., (1,8)
        // Total: 7 + 7 + 7 = 21 positions
        assertEquals(21, validMoves.size());
        
        // Test diagonal
        assertTrue(validMoves.contains(new Position(2, 2)));
        assertTrue(validMoves.contains(new Position(8, 8)));
        
        // Test horizontal
        assertTrue(validMoves.contains(new Position(2, 1)));
        assertTrue(validMoves.contains(new Position(8, 1)));
        
        // Test vertical
        assertTrue(validMoves.contains(new Position(1, 2)));
        assertTrue(validMoves.contains(new Position(1, 8)));
    }
    
    @Test
    void testQueenDiagonalMovementWithObstacle() {
        // Queen at (4, 4) with diagonal obstacle at (5, 5)
        Queen queen = new Queen(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Position(5, 5)); // Diagonal obstacle
        
        Collection<Position> validMoves = queen.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Cannot land on (5, 5)
        assertFalse(validMoves.contains(new Position(5, 5)), 
                    "Queen should not be able to land on occupied position");
        
        // But can jump over and land beyond on diagonal
        assertTrue(validMoves.contains(new Position(6, 6)), 
                   "Queen should be able to jump over occupied position diagonally");
        assertTrue(validMoves.contains(new Position(7, 7)));
        
        // Horizontal and vertical moves should still be available
        assertTrue(validMoves.contains(new Position(5, 4)));
        assertTrue(validMoves.contains(new Position(4, 5)));
    }
    
    @Test
    void testQueenHorizontalMovementWithObstacle() {
        // Queen at (4, 4) with horizontal obstacle at (6, 4)
        Queen queen = new Queen(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Position(6, 4)); // Horizontal obstacle
        
        Collection<Position> validMoves = queen.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Cannot land on (6, 4)
        assertFalse(validMoves.contains(new Position(6, 4)), 
                    "Queen should not be able to land on occupied position");
        
        // But can jump over and land beyond horizontally
        assertTrue(validMoves.contains(new Position(7, 4)), 
                   "Queen should be able to jump over occupied position horizontally");
        assertTrue(validMoves.contains(new Position(8, 4)));
        
        // Can still move in the opposite horizontal direction
        assertTrue(validMoves.contains(new Position(3, 4)));
        assertTrue(validMoves.contains(new Position(1, 4)));
        
        // Vertical and diagonal moves should still be available
        assertTrue(validMoves.contains(new Position(4, 5)));
        assertTrue(validMoves.contains(new Position(5, 5)));
    }
    
    @Test
    void testQueenVerticalMovementWithObstacle() {
        // Queen at (4, 4) with vertical obstacle at (4, 6)
        Queen queen = new Queen(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Position(4, 6)); // Vertical obstacle
        
        Collection<Position> validMoves = queen.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Cannot land on (4, 6)
        assertFalse(validMoves.contains(new Position(4, 6)), 
                    "Queen should not be able to land on occupied position");
        
        // But can jump over and land beyond vertically
        assertTrue(validMoves.contains(new Position(4, 7)), 
                   "Queen should be able to jump over occupied position vertically");
        assertTrue(validMoves.contains(new Position(4, 8)));
        
        // Can still move in the opposite vertical direction
        assertTrue(validMoves.contains(new Position(4, 3)));
        assertTrue(validMoves.contains(new Position(4, 1)));
        
        // Horizontal and diagonal moves should still be available
        assertTrue(validMoves.contains(new Position(5, 4)));
        assertTrue(validMoves.contains(new Position(5, 5)));
    }
    
    @Test
    void testQueenWithMultipleObstaclesInDifferentDirections() {
        // Queen at (4, 4) with obstacles in different directions
        Queen queen = new Queen(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Position(5, 5)); // Diagonal
        occupiedPositions.add(new Position(6, 4)); // Horizontal
        occupiedPositions.add(new Position(4, 2)); // Vertical
        
        Collection<Position> validMoves = queen.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Cannot land on any occupied positions
        assertFalse(validMoves.contains(new Position(5, 5)));
        assertFalse(validMoves.contains(new Position(6, 4)));
        assertFalse(validMoves.contains(new Position(4, 2)));
        
        // But can jump over and land beyond
        assertTrue(validMoves.contains(new Position(6, 6))); // Beyond diagonal obstacle
        assertTrue(validMoves.contains(new Position(7, 4))); // Beyond horizontal obstacle
        assertTrue(validMoves.contains(new Position(4, 1))); // Beyond vertical obstacle
        
        // Other directions should still be clear
        assertTrue(validMoves.contains(new Position(3, 3)));
        assertTrue(validMoves.contains(new Position(1, 4)));
        assertTrue(validMoves.contains(new Position(4, 8)));
    }
    
    @Test
    void testQueenAtEdge() {
        // Queen at edge (8, 4)
        Queen queen = new Queen(new Position(8, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = queen.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Verify positions outside the board are not included
        assertFalse(validMoves.contains(new Position(9, 4)));
        assertFalse(validMoves.contains(new Position(9, 5)));
        
        // Verify valid moves
        assertTrue(validMoves.contains(new Position(7, 4))); // Horizontal left
        assertTrue(validMoves.contains(new Position(8, 5))); // Vertical up
        assertTrue(validMoves.contains(new Position(7, 5))); // Diagonal
    }
}

