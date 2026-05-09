package br.com.dio.ui;

import br.com.dio.model.Board;
import br.com.dio.model.Cell;
import br.com.dio.service.BoardService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SudokuUI extends JFrame {
    private final Board board;
    private final JButton[][] buttons = new JButton[9][9];
    private Cell selectedCell;

    public SudokuUI(Board board) {
        this.board = board;
        setTitle("DIO Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 244));

        setupUI();
        setSize(600, 700);
        setLocationRelativeTo(null);
    }

    private void setupUI() {
        // Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Cell cell = board.getCell(x, y);
                JButton btn = new JButton(cell.getValue() == 0 ? "" : String.valueOf(cell.getValue()));
                btn.setFont(new Font("SansSerif", cell.isFixed() ? Font.BOLD : Font.PLAIN, 20));
                btn.setFocusPainted(false);
                btn.setBackground(Color.WHITE);
                btn.setForeground(cell.isFixed() ? Color.BLACK : new Color(59, 130, 246));
                
                // 3x3 Block borders
                int top = 1, left = 1, bottom = 1, right = 1;
                if (y % 3 == 0) top = 2;
                if (x % 3 == 0) left = 2;
                if (y == 8) bottom = 2;
                if (x == 8) right = 2;
                btn.setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));

                btn.addActionListener(e -> selectCell(cell));
                buttons[y][x] = btn;
                boardPanel.add(btn);
            }
        }

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        boardPanel.setPreferredSize(new Dimension(500, 500));
        wrapper.add(boardPanel);
        add(wrapper, BorderLayout.CENTER);

        // Control Panel
        JPanel controls = new JPanel(new GridLayout(1, 10, 5, 5));
        controls.setOpaque(false);
        controls.setBorder(new EmptyBorder(10, 20, 20, 20));

        for (int i = 1; i <= 9; i++) {
            int num = i;
            JButton numBtn = new JButton(String.valueOf(num));
            numBtn.addActionListener(e -> updateValue(num));
            controls.add(numBtn);
        }

        JButton clearBtn = new JButton("X");
        clearBtn.setForeground(Color.RED);
        clearBtn.addActionListener(e -> updateValue(0));
        controls.add(clearBtn);

        add(controls, BorderLayout.SOUTH);
        
        updateDisplay();
    }

    private void selectCell(Cell cell) {
        this.selectedCell = cell;
        updateDisplay();
    }

    private void updateValue(int value) {
        if (selectedCell == null || selectedCell.isFixed()) return;
        selectedCell.setValue(value);
        updateDisplay();
        
        if (BoardService.isComplete(board)) {
            JOptionPane.showMessageDialog(this, "Victory! Puzzle Solved.");
        }
    }

    private void updateDisplay() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Cell cell = board.getCell(x, y);
                JButton btn = buttons[y][x];
                btn.setText(cell.getValue() == 0 ? "" : String.valueOf(cell.getValue()));
                
                // Styles
                btn.setBackground(Color.WHITE);
                if (selectedCell != null) {
                    if (cell.getX() == selectedCell.getX() && cell.getY() == selectedCell.getY()) {
                        btn.setBackground(new Color(200, 230, 255));
                    } else if (cell.getX() == selectedCell.getX() || cell.getY() == selectedCell.getY() || 
                              (cell.getX()/3 == selectedCell.getX()/3 && cell.getY()/3 == selectedCell.getY()/3)) {
                        btn.setBackground(new Color(240, 240, 240));
                    }
                }

                if (cell.isFixed()) {
                    btn.setForeground(Color.BLACK);
                } else {
                    if (BoardService.hasConflict(board, x, y, cell.getValue())) {
                        btn.setForeground(Color.RED);
                    } else {
                        btn.setForeground(new Color(59, 130, 246));
                    }
                }
            }
        }
    }
}
