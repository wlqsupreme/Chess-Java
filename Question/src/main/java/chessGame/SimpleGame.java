package chessGame;

import java.util.Random;
import java.util.Collection;

import chessLib.KnightMove;
import chessLib.Position;

public class SimpleGame extends BaseGame {

    private Position position;

    private Random random;

    public SimpleGame() {
        this.position = null;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void play(int moves) {
        KnightMove knight = new KnightMove();
        Position pos = this.position;
        System.out.printf("0: My Position is %1$s\r\n", pos.toString());

        for (int i = 0; i < moves; i++) {
            Position[] possibles = knight.validMovesFor(pos).toArray(new Position[0]);
            int r = random.nextInt(possibles.length);
            pos = possibles[r];
            System.out.printf("%1$d: My position is %2$s\r\n", i, pos.toString());
        }
    }

    @Override
    public void setup() {
        this.position = new Position(3, 3);
    }
}