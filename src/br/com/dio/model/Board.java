package br.com.dio.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Cell> cells;

    public Board(List<Cell> cells) {
        this.cells = new ArrayList<>(cells);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        return cells.stream()
                .filter(c -> c.getX() == x && c.getY() == y)
                .findFirst()
                .orElse(null);
    }
}
