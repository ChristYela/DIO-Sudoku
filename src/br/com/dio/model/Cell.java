package br.com.dio.model;

import java.util.Objects;

public class Cell {
    private final int x;
    private final int y;
    private int value;
    private final boolean fixed;

    public Cell(int x, int y, int value, boolean fixed) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.fixed = fixed;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public boolean isFixed() { return fixed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
