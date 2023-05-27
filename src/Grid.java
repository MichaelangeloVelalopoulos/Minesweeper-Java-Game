import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Grid extends JPanel {
    private int bound = Game.GRIDSIZE * Game.GRIDSIZE;

    private boolean picked = false;

    private ArrayList<Integer> mines = new ArrayList<Integer>();

    public static ArrayList<Cell> cellGrid = new ArrayList<Cell>();

    public Grid(GridLayout g, Handler h) {
        super(g);
        createCells(h);
        addCells();
    }

    public void createCells(Handler h) {
        String mineloc="";
        int hei;
        int dis;
        boolean first = true;
        for(int i = 1; i <= Game.MINCOUNT; i++) {
            while(!picked) {
                int minePosition = (int) (Math.random() * bound);
                if (!mines.contains(minePosition)) {
                    mines.add(minePosition);
                    picked = true;
                    hei= minePosition / Game.GRIDSIZE;
                    dis= minePosition % Game.GRIDSIZE;
                    if (Game.gamevars[3]==1 && first) {
                        first = false;
                        mineloc = mineloc + hei + "," + dis + ",1 \n";
                    }else{
                        mineloc = mineloc + hei + "," + dis + ",0 \n";
                    }
                }
            }
            picked = false;
        }

        for(int i = 0; i < bound; i++) {
            if(mines.contains(i)) {
                cellGrid.add(new Cell(1, i, false, false, h));

            } else if(i % Game.GRIDSIZE == 0){ // sthles
                if(mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - Game.GRIDSIZE + 1) ||
                        mines.contains(i + 1) ||
                        mines.contains(i + Game.GRIDSIZE) ||
                        mines.contains(i + Game.GRIDSIZE + 1)) {
                    cellGrid.add(new Cell(2, i, false, false, h));
                } else {
                    cellGrid.add(new Cell(0, i, false, false, h));
                }
            } else if(i % Game.GRIDSIZE == Game.GRIDSIZE - 1){
                if(mines.contains(i - Game.GRIDSIZE - 1) ||
                        mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - 1) ||
                        mines.contains(i + Game.GRIDSIZE - 1) ||
                        mines.contains(i + Game.GRIDSIZE)) {
                    cellGrid.add(new Cell(2, i, false, false, h));
                } else {
                    cellGrid.add(new Cell(0, i, false, false, h));
                }
            }else {
                if(mines.contains(i - Game.GRIDSIZE - 1) ||
                        mines.contains(i - Game.GRIDSIZE) ||
                        mines.contains(i - Game.GRIDSIZE + 1) ||
                        mines.contains(i - 1) ||
                        mines.contains(i + 1) ||
                        mines.contains(i + Game.GRIDSIZE - 1) ||
                        mines.contains(i + Game.GRIDSIZE) ||
                        mines.contains(i + Game.GRIDSIZE + 1)) {
                    cellGrid.add(new Cell(2, i, false, false, h));
                } else {
                    cellGrid.add(new Cell(0, i, false, false, h));
                }
            }
        }

        if (Game.gamevars[3]==1) {
            Integer superBomb = mines.get(0);
            cellGrid.get(superBomb).setType(3);
            System.out.println(superBomb);
        }
        FileCreator.createTextFile("src/mines","mines",mineloc);
    }

    private void addCells() {
        for(int i = 0; i < cellGrid.size(); i++) {
            add(cellGrid.get(i));
        }
    }
}
