package br.com.dio.service;

import br.com.dio.model.Board;
import br.com.dio.model.Cell;
import java.util.ArrayList;
import java.util.List;

public class BoardService {
    
    public static Board parseBoard(String seed) {
        List<Cell> cells = new ArrayList<>();
        // Default empty board
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                cells.add(new Cell(x, y, 0, false));
            }
        }

        if (seed != null && !seed.isEmpty()) {
            String[] parts = seed.split(" ");
            for (String part : parts) {
                try {
                    String[] data = part.split(";");
                    String[] coords = data[0].split(",");
                    String[] info = data[1].split(",");

                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    int value = Integer.parseInt(info[0]);
                    boolean fixed = Boolean.parseBoolean(info[1]);

                    // Update existing cell in list
                    for (Cell c : cells) {
                        if (c.getX() == x && c.getY() == y) {
                            c.setValue(fixed ? value : 0);
                            // We need to re-instantiate or just update if we allow it.
                            // The challenge repo might have a slightly different way.
                            // I'll re-instantiate to match the 'fixed' status.
                             cells.set(cells.indexOf(c), new Cell(x, y, fixed ? value : 0, fixed));
                             break;
                        }
                    }
                } catch (Exception ignored) {}
            }
        }
        return new Board(cells);
    }

    public static boolean hasConflict(Board board, int x, int y, int value) {
        if (value == 0) return false;
        
        for (Cell cell : board.getCells()) {
            if (cell.getX() == x && cell.getY() == y) continue;
            
            if (cell.getValue() == value) {
                // Row or Col
                if (cell.getX() == x || cell.getY() == y) return true;
                
                // Block 3x3
                if ((cell.getX() / 3 == x / 3) && (cell.getY() / 3 == y / 3)) return true;
            }
        }
        return false;
    }

    public static boolean isComplete(Board board) {
        for (Cell cell : board.getCells()) {
            if (cell.getValue() == 0 || hasConflict(board, cell.getX(), cell.getY(), cell.getValue())) {
                return false;
            }
        }
        return true;
    }
}
