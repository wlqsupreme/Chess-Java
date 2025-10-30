package chessGame;

import chessLib.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ComplexGame extends BaseGame {

    private List<ChessPiece> pieces;
    private Random random;

    public ComplexGame() {
        this.pieces = new ArrayList<>();
        this.random = new Random();
    }

    @Override
    public void setup() {
        // Initialize the list and add several pieces at different positions
        pieces.clear();
        
        // Add Knights
        pieces.add(new Knight(new Position(2, 1)));
        pieces.add(new Knight(new Position(7, 1)));
        
        // Add Bishops
        pieces.add(new Bishop(new Position(3, 1)));
        pieces.add(new Bishop(new Position(6, 1)));
        
        // Add Queens
        pieces.add(new Queen(new Position(4, 1)));
        pieces.add(new Queen(new Position(5, 8)));
        
        System.out.println("Game setup complete with " + pieces.size() + " pieces.");
        printBoard();
    }

    @Override
    public void play(int moves) {
        for (int moveCount = 1; moveCount <= moves; moveCount++) {
            System.out.println("\n=== Move " + moveCount + " ===");
            
            // Get all occupied positions
            Set<Position> occupiedPositions = getOccupiedPositions();
            
            // Try to find a piece with valid moves
            ChessPiece selectedPiece = null;
            Collection<Position> validMoves = null;
            int attempts = 0;
            int maxAttempts = pieces.size() * 3; // Avoid infinite loop
            
            while (attempts < maxAttempts) {
                // Randomly select a piece
                selectedPiece = pieces.get(random.nextInt(pieces.size()));
                
                // Get valid moves for this piece
                validMoves = selectedPiece.getValidMoves(occupiedPositions);
                
                // If there are valid moves, we found our piece
                if (!validMoves.isEmpty()) {
                    break;
                }
                
                attempts++;
            }
            
            // If no piece has valid moves, skip this turn
            if (validMoves == null || validMoves.isEmpty()) {
                System.out.println("No valid moves available for any piece. Skipping turn.");
                continue;
            }
            
            // Randomly select a valid move
            Position oldPosition = selectedPiece.getPosition();
            Position newPosition = selectRandomMove(validMoves);
            
            // Update the piece position
            selectedPiece.setPosition(newPosition);
            
            // Print log
            String pieceName = selectedPiece.getClass().getSimpleName();
            System.out.println(pieceName + " moved from " + oldPosition + " to " + newPosition);
            System.out.println("Valid moves available: " + validMoves.size());
        }
        
        System.out.println("\n=== Game Complete ===");
        printBoard();
    }
    
    /**
     * Gets a set of all currently occupied positions on the board.
     */
    private Set<Position> getOccupiedPositions() {
        Set<Position> occupied = new HashSet<>();
        for (ChessPiece piece : pieces) {
            occupied.add(piece.getPosition());
        }
        return occupied;
    }
    
    /**
     * Randomly selects a position from the collection of valid moves.
     */
    private Position selectRandomMove(Collection<Position> validMoves) {
        List<Position> movesList = new ArrayList<>(validMoves);
        return movesList.get(random.nextInt(movesList.size()));
    }
    
    /**
     * Prints the current state of the board.
     */
    private void printBoard() {
        System.out.println("\nCurrent board state:");
        for (int i = 0; i < pieces.size(); i++) {
            ChessPiece piece = pieces.get(i);
            String pieceName = piece.getClass().getSimpleName();
            System.out.println("  " + (i + 1) + ". " + pieceName + " at " + piece.getPosition());
        }
    }
}