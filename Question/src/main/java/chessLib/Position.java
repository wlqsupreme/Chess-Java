package chessLib;

public class Position {

    private final int _x;

    private final int _y;

    public Position(final int x, final int y) {
        this._x = x;
        this._y = y;
    }

    public int x() {
        return this._x;
    }

    public int y() {
        return this._y;
    }

    @Override
    public String toString() {
        return "(" + this._x + ", " + this._y + ")";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Position) {
            final Position pos = (Position) obj;
            return _x == pos.x() && _y == pos.y();
        }

        return false;
    }
}