import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game {
    public static final int WIDTH = 720, HEIGHT = 720;
    public static int GRIDSIZE = 16;
    public static int MINCOUNT = 10;
    public Handler handler = new Handler();
    private JFrame menuFrame;
    public static int[] gamevars;
    private CountdownTimer timer;

    public static void flaggedSuperMine(Cell mine){
        int row = mine.getPosition() / GRIDSIZE;
        int column = mine.getPosition() % GRIDSIZE;
        showRow(row);
        showColumn(column);
    }
    private static void showColumn(int column){
        int startPos = column;
        for (int pos=startPos; pos<GRIDSIZE*GRIDSIZE; pos+=GRIDSIZE){
            showCell(pos);
        }
    }
    private static void showRow(int row) {
        int startPos = row * GRIDSIZE;
        for (int pos = startPos; pos < startPos + GRIDSIZE; pos++) {
            showCell(pos);
        }
    }
    private static void showCell(int pos){
        Cell cell = Grid.cellGrid.get(pos);


        cell.setEnabled(false);
        cell.setDiscovered(true);

        if (cell.getType() == 1){
            cell.setText("X");
        }
        if (cell.getType() == 0){
            cell.setText("");
        }

        if (cell.getType() == 3){
            cell.setText("sX");
        }

    }
    public static void showAllCells(){
        Grid.cellGrid.forEach(cell ->
        {
            cell.setEnabled(false);
            if (cell.getType() == 1) cell.setText("X");
            if (cell.getType() == 0) cell.setText(":)");
            if (cell.getType() == 0) cell.setText("(:");
        });
    }
    public Game() {
        menuFrame = new JFrame("Minesweeper Menu");
        new MenuBarCreator(Game.this, handler, menuFrame);

        // Create the exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0, 0); // add some padding at the top
        c.gridy = 3;
        panel.add(exitButton, c);

        menuFrame.add(panel);

        // Set the menu frame visible
        menuFrame.setVisible(true);


    }


    public static void main(String[] args) {
        Game game = new Game();
    }
}