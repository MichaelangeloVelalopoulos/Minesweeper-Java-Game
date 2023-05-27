import java.util.ArrayList;

public class Handler {

    private ArrayList<Cell> current = new ArrayList<Cell>();
    private ArrayList<Cell> queue = new ArrayList<Cell>();

    private static int flaggedCells = 0;

    public void click(Cell cell) {
        int discoveredCells = 0;
        if(!cell.isFlagged()) {
            cell.setEnabled(false);
            cell.setDiscovered(true);

            int position = cell.getPosition();
            if(cell.getType() == 0) { //empty cells
                if(position < Game.GRIDSIZE) { //position between 0-9 meaning 1st row
                    if(position % Game.GRIDSIZE == 0) { //check if top left corner
                        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
                        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                    } else if(position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
                        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
                        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    } else {
                        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
                        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
                        queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    }
                } else if(position >= (Game.GRIDSIZE * (Game.GRIDSIZE - 1))) { //bottom row
                    if(position % Game.GRIDSIZE == 0) {
                        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
                        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                    } else if(position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
                        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
                        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    } else {
                        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
                        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
                        queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
                        queue.add(Grid.cellGrid.get((position + 1)));
                        queue.add(Grid.cellGrid.get((position - 1)));
                    }
                } else if(position % Game.GRIDSIZE == 0) { //most left position
                    queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
                    queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
                    queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
                    queue.add(Grid.cellGrid.get((position + 1)));
                } else if(position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
                    queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
                    queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
                    queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
                    queue.add(Grid.cellGrid.get((position - 1)));
                } else {
                    queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE)));
                    queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE)));
                    queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)));
                    queue.add(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)));
                    queue.add(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)));
                    queue.add(Grid.cellGrid.get((position - 1)));
                    queue.add(Grid.cellGrid.get((position + 1)));
                }
            } else if(cell.getType() == 2) { //number cells
                int dangerCount = 0;
                if(position < Game.GRIDSIZE) {
                    if(position % Game.GRIDSIZE == 0) {
                        if(Grid.cellGrid.get(position + Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRIDSIZE + 1).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + 1).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                    } else if(position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
                        if(Grid.cellGrid.get(position + Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRIDSIZE - 1).getType() == 1 || Grid.cellGrid.get((position - 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1 || Grid.cellGrid.get((position - 1)).getType() == 3) dangerCount++;
                    } else {
                        if(Grid.cellGrid.get(position + Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRIDSIZE + 1).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + Game.GRIDSIZE - 1).getType() == 1 || Grid.cellGrid.get((position - 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + 1).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1 || Grid.cellGrid.get((position - 1)).getType() == 3) dangerCount++;
                        System.out.println(dangerCount);
                    }
                } else if(position >= (Game.GRIDSIZE * (Game.GRIDSIZE - 1))) {
                    if(position % Game.GRIDSIZE == 0) {
                        if(Grid.cellGrid.get(position - Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRIDSIZE + 1).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + 1).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                    } else if(position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
                        if(Grid.cellGrid.get(position - Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRIDSIZE - 1).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE -1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getType() == 3) dangerCount++;
                    } else {
                        if(Grid.cellGrid.get(position - Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRIDSIZE + 1).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - Game.GRIDSIZE - 1).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position + 1).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                        if(Grid.cellGrid.get(position - 1).getType() == 1 || Grid.cellGrid.get((position - 1)).getType() == 3) dangerCount++;
                    }
                } else if(position % Game.GRIDSIZE == 0) {
                    if(Grid.cellGrid.get(position - Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get(position + Game.GRIDSIZE).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get(position - Game.GRIDSIZE + 1).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get(position + Game.GRIDSIZE + 1).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get(position + 1).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                } else if(position % Game.GRIDSIZE == Game.GRIDSIZE - 1) {
                    if(Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRIDSIZE)).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position - 1)).getType() == 1 || Grid.cellGrid.get((position - 1)).getType() == 3) dangerCount++;
                } else {
                    if(Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRIDSIZE)).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE - 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRIDSIZE - 1)).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE - 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getType() == 1 || Grid.cellGrid.get((position - Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position + Game.GRIDSIZE + 1)).getType() == 1 || Grid.cellGrid.get((position + Game.GRIDSIZE + 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position - 1)).getType() == 1 || Grid.cellGrid.get((position - 1)).getType() == 3) dangerCount++;
                    if(Grid.cellGrid.get((position + 1)).getType() == 1 || Grid.cellGrid.get((position + 1)).getType() == 3) dangerCount++;
                }
                cell.setText(String.valueOf(dangerCount));
            } else if(cell.getType() == 1 || cell.getType() == 3) { //mine cells
                for(int x = 0; x < Grid.cellGrid.size(); x++) {
                    Grid.cellGrid.get(x).setEnabled(false);
                    Grid.cellGrid.get(x).setText("");
                    if(Grid.cellGrid.get(x).getType() == 1) Grid.cellGrid.get(x).setText("X");
                    if(Grid.cellGrid.get(x).getType() == 3) Grid.cellGrid.get(x).setText("sX");
                }
                cell.setText("*");
            }

            for(int x = 0; x < queue.size(); x++) { //2 different array lists cause our queue is going to populate the other one
                if(!queue.get(x).isDiscovered()) {
                    current.add(queue.get(x));
                    queue.get(x).setDiscovered(true);
                }
            }
            queue.clear();
            while(!current.isEmpty()) {
                Cell temp = current.get(0);
                current.remove(0);
                temp.clickButton();
            }

            for(int x = 0; x < Grid.cellGrid.size(); x++) {
                if(Grid.cellGrid.get(x).isDiscovered()) {
                    discoveredCells++;
                }
            }

            if(discoveredCells == Grid.cellGrid.size() - Game.MINCOUNT) {
                Game.showAllCells();
            }
        }
    }



    public void rightClick(Cell cell) {
        if(!cell.isDiscovered()) {
            if(!cell.isFlagged()) {
                cell.setFlagged(true);
                cell.setText("F");
                flaggedCells++;
                WindowProvider.incrementFlags(+1);
                WindowProvider.updateTitle();
                if (cell.getType()==3) Game.flaggedSuperMine(cell);
            } else {
                cell.setFlagged(false);
                cell.setText("");
                flaggedCells--;
                WindowProvider.incrementFlags(-1);
                WindowProvider.updateTitle();
            }
        }
    }
}
