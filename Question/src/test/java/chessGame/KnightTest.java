package chessGame;

import chessLib.Position;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    
    @Test
    void testKnightAtCenterWithNoObstacles() {
        // Knight at center (4, 4) with no obstacles
        Knight knight = new Knight(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = knight.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // Knight has 8 possible L-shaped moves from center
        assertEquals(8, validMoves.size());
        
        // Verify all 8 L-shaped positions
        assertTrue(validMoves.contains(new Position(5, 6))); // +1, +2
        assertTrue(validMoves.contains(new Position(5, 2))); // +1, -2
        assertTrue(validMoves.contains(new Position(3, 6))); // -1, +2
        assertTrue(validMoves.contains(new Position(3, 2))); // -1, -2
        assertTrue(validMoves.contains(new Position(6, 5))); // +2, +1
        assertTrue(validMoves.contains(new Position(6, 3))); // +2, -1
        assertTrue(validMoves.contains(new Position(2, 5))); // -2, +1
        assertTrue(validMoves.contains(new Position(2, 3))); // -2, -1
    }
    
    @Test
    void testKnightAtCornerWithNoObstacles() {
        // Knight at corner (1, 1) with no obstacles
        Knight knight = new Knight(new Position(1, 1));
        Set<Position> occupiedPositions = new HashSet<>();
        
        Collection<Position> validMoves = knight.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // From corner, only 2 moves are valid (within bounds)
        assertEquals(2, validMoves.size());
        
        assertTrue(validMoves.contains(new Position(2, 3))); // +1, +2
        assertTrue(validMoves.contains(new Position(3, 2))); // +2, +1
        
        // Verify out-of-bounds moves are not included
        assertFalse(validMoves.contains(new Position(-1, 2)));
        assertFalse(validMoves.contains(new Position(2, -1)));
    }
    
    @Test
    void testKnightFiltersOccupiedPositions() {
        // Knight at (4, 4) with some occupied positions
        Knight knight = new Knight(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        // Add occupied positions where knight could potentially move
        occupiedPositions.add(new Position(5, 6));
        occupiedPositions.add(new Position(6, 5));
        occupiedPositions.add(new Position(2, 3));
        
        Collection<Position> validMoves = knight.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Should have 8 - 3 = 5 valid moves
        assertEquals(5, validMoves.size());
        
        // Verify occupied positions are filtered out
        assertFalse(validMoves.contains(new Position(5, 6)), 
                    "Knight should not include occupied position (5, 6)");
        assertFalse(validMoves.contains(new Position(6, 5)), 
                    "Knight should not include occupied position (6, 5)");
        assertFalse(validMoves.contains(new Position(2, 3)), 
                    "Knight should not include occupied position (2, 3)");
        
        // Verify non-occupied positions are still included
        assertTrue(validMoves.contains(new Position(5, 2)));
        assertTrue(validMoves.contains(new Position(3, 6)));
        assertTrue(validMoves.contains(new Position(3, 2)));
        assertTrue(validMoves.contains(new Position(6, 3)));
        assertTrue(validMoves.contains(new Position(2, 5)));
    }
    
    @Test
    void testKnightWithAllPositionsOccupied() {
        // Knight at (4, 4) with all possible moves occupied
        Knight knight = new Knight(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        // Occupy all 8 possible knight moves
        occupiedPositions.add(new Position(5, 6));
        occupiedPositions.add(new Position(5, 2));
        occupiedPositions.add(new Position(3, 6));
        occupiedPositions.add(new Position(3, 2));
        occupiedPositions.add(new Position(6, 5));
        occupiedPositions.add(new Position(6, 3));
        occupiedPositions.add(new Position(2, 5));
        occupiedPositions.add(new Position(2, 3));
        
        Collection<Position> validMoves = knight.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // No valid moves should remain
        assertEquals(0, validMoves.size(), 
                    "Knight should have no valid moves when all positions are occupied");
    }
    
    @Test
    void testKnightFilteringDoesNotAffectNonKnightMoves() {
        // Knight at (4, 4) with occupied positions that are NOT valid knight moves
        Knight knight = new Knight(new Position(4, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        
        // Add positions that are not valid knight moves
        occupiedPositions.add(new Position(4, 5)); // Vertical adjacent
        occupiedPositions.add(new Position(5, 4)); // Horizontal adjacent
        occupiedPositions.add(new Position(5, 5)); // Diagonal
        
        Collection<Position> validMoves = knight.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        // All 8 knight moves should still be valid
        assertEquals(8, validMoves.size(), 
                    "Occupying non-knight positions should not affect knight's valid moves");
        
        // Verify all knight moves are still present
        assertTrue(validMoves.contains(new Position(5, 6)));
        assertTrue(validMoves.contains(new Position(5, 2)));
        assertTrue(validMoves.contains(new Position(3, 6)));
        assertTrue(validMoves.contains(new Position(3, 2)));
    }
    
    @Test
    void testKnightAtEdgeWithOccupiedPositions() {
        // Knight at edge (1, 4) with some occupied positions
        Knight knight = new Knight(new Position(1, 4));
        Set<Position> occupiedPositions = new HashSet<>();
        occupiedPositions.add(new Position(2, 6)); // One of the valid moves
        
        Collection<Position> validMoves = knight.getValidMoves(occupiedPositions);
        
        assertNotNull(validMoves);
        
        // Should not contain the occupied position
        assertFalse(validMoves.contains(new Position(2, 6)));
        
        // Should still contain other valid moves
        assertTrue(validMoves.contains(new Position(2, 2)));
        assertTrue(validMoves.contains(new Position(3, 5)));
        assertTrue(validMoves.contains(new Position(3, 3)));
    }
    
    @Test
    void testKnightPositionGetterAndSetter() {
        // Test that position getter and setter work correctly
        Position initialPos = new Position(3, 3);
        Knight knight = new Knight(initialPos);
        
        // Test getter
        assertEquals(initialPos, knight.getPosition());
        
        // Test setter
        Position newPos = new Position(5, 5);
        knight.setPosition(newPos);
        assertEquals(newPos, knight.getPosition());
        
        // Verify the new position affects getValidMoves
        Set<Position> occupiedPositions = new HashSet<>();
        Collection<Position> validMoves = knight.getValidMoves(occupiedPositions);
        
        // All moves should be relative to new position (5, 5)
        assertTrue(validMoves.contains(new Position(6, 7)));
        assertTrue(validMoves.contains(new Position(7, 6)));
        assertFalse(validMoves.contains(new Position(4, 5))); // Would be valid from (3,3) but not from (5,5)
    }
}

