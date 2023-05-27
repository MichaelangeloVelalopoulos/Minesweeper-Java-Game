import javax.swing.*;
import java.awt.event.*;


/**
 * The Cell class represents a single cell in the game board.
 * It extends the JButton class and implements the MouseListener interface
 * to allow for user interaction with the game board.
 */
public class Cell extends JButton {

    /**
     * The type of the cell:
     * 0: Empty
     * 1: Mine
     * 2: Number
     * 3: Super bomb (only in special game mode)
     */
    private int type;

    /**
     * The position of the cell in the grid.
     */
    private int position;

    /**
     * Indicates whether the cell has been discovered by the player.
     */
    private boolean discovered;

    /**
     * Indicates whether the cell has been flagged by the player.
     */
    private boolean flagged;

    /**
     * The handler object that handles the game logic.
     */
    private Handler handler;

    /**
     * Creates a new Cell object with the specified parameters.
     * @param type The type of the cell.
     * @param position The position of the cell in the grid.
     * @param discovered Indicates whether the cell has been discovered by the player.
     * @param flagged Indicates whether the cell has been flagged by the player.
     * @param handler The handler object that handles the game logic.
     */
    public Cell(int type, int position, boolean discovered, boolean flagged, Handler handler) {
        this.type = type;
        this.position = position;
        this.discovered = discovered;
        this.flagged = flagged;
        this.handler = handler;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    rightClickButton();
                } else {
                    clickButton();
                }
            }

            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
    }

    /**
     * Sets the type of the cell.
     * @param type The type of the cell.
     */
    public void setType(int type){
        this.type = type;
    }

    /**
     * Returns the type of the cell.
     * @return The type of the cell.
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the position of the cell in the grid.
     * @return The position of the cell in the grid.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns whether the cell has been discovered by the player.
     * @return True if the cell has been discovered by the player, false otherwise.
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     * Sets whether the cell has been discovered by the player.
     * @param d True if the cell has been discovered by the player, false otherwise.
     */
    public void setDiscovered(boolean d) {
        this.discovered = d;
    }

    /**
     * Returns whether the cell has been flagged by the player.
     * @return True if the cell has been flagged by the player, false otherwise.
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     * Sets whether the cell has been flagged by the player.
     * @param f True if the cell has been flagged by the player, false otherwise.
     */
    public void setFlagged(boolean f) {
        this.flagged = f;
    }

    /**
     * Calls the click method of the handler object.
     */
    public void clickButton() {
        handler.click(this);
    }

    /**
     * Calls the rightClick method of the handler object.
     */
    public void rightClickButton() {
        handler.rightClick(this);
    }
}

/**The Cell class is now complete. It represents a single cell in the game board, and extends the JButton class to allow for user interaction. It also implements the MouseListener interface to handle mouse events. The class has several instance variables to represent the type of the cell, its position in the grid, and whether it has been discovered or flagged by the player. It also has a Handler object that handles the game logic.
 The class has several methods to get and set the type, position, discovered and flagged properties of the cell. It also has methods to handle the click and right-click events on the cell, which call the appropriate methods in the Handler object to update the game state.*/

