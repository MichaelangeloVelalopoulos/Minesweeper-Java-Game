import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class MenuBarCreator {
    public MenuBarCreator(Game game,Handler handler,JFrame menuFrame) {

        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(250, 250);
        menuFrame.setLocationRelativeTo(null); // center the window

        JMenuBar menuBar = new JMenuBar();
        JMenu applicationMenu = new JMenu("Application");
        JMenu detailsMenu = new JMenu("Details");

        JMenuItem createItem = new JMenuItem("Create");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem startItem = new JMenuItem("Start");
        JMenuItem roundsItem = new JMenuItem("Rounds");
        JMenuItem solutionItem = new JMenuItem("Solution");

        createItem.addActionListener(e -> {
            JTextField input1 = new JTextField(10);
            JTextField input2 = new JTextField(10);
            JTextField input3 = new JTextField(10);
            JTextField input4 = new JTextField(10);
            JTextField input5 = new JTextField(10);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Scenario name:"));
            myPanel.add(input1);
            myPanel.add(new JLabel("Difficulty:"));
            myPanel.add(input2);
            myPanel.add(new JLabel("Mine number:"));
            myPanel.add(input3);
            myPanel.add(new JLabel("Time:"));
            myPanel.add(input4);
            myPanel.add(new JLabel("Supermine:"));
            myPanel.add(input5);

            int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String value1 = input1.getText();
                String value2 = input2.getText();
                String value3 = input3.getText();
                String value4 = input4.getText();
                String value5 = input5.getText();

                if (!value1.isBlank() && !value2.isBlank() && !value3.isBlank() && !value4.isBlank() && !value5.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Values successfully submitted.");
                    FileCreator.createTextFile(
                            "src/medialab",
                            value1,
                            value2 + "\n" + value3 + "\n" + value4 + "\n" + value5
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "All input fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loadItem.addActionListener(e -> {
            JTextField input1 = new JTextField(10);
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Scenario name:"));
            myPanel.add(input1);

            int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String value1 = input1.getText();
                if (!value1.isBlank()) {
                    try {
                        Game.gamevars = TextFileParser.parseTextFile("src/medialab/" + value1 + ".txt");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (NullPointerException ex) {
                        System.out.println("Invalid File Name");
                        JOptionPane.showMessageDialog(null, "Incorrect file name");
                    } catch (InvalidValueException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Values");
                    } catch (InvalidDescriptionException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Description");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Scenario name required", "Error", JOptionPane.ERROR_MESSAGE);
                }

                Game.MINCOUNT = Game.gamevars[1];
                if (Game.gamevars[0] == 1) {
                    Game.GRIDSIZE = 9;
                } else {
                    Game.GRIDSIZE = 16;
                }
            }
        });

        startItem.addActionListener(e -> {
            menuFrame.dispose();
            Grid.cellGrid= new ArrayList<Cell>();
            if(WindowProvider.getTimer().isPresent()) WindowProvider.getTimer().get().timerStop();
            new WindowProvider(Game.WIDTH, Game.HEIGHT, Game.GRIDSIZE, "MediaLab Minesweeper ", game, handler);
        });

        roundsItem.addActionListener(e -> {
            int[] roundsitems;
            String roundstring="";
            for(int i=1; i<6; i++) {
                try {
                    roundsitems = Roundsdev.Roundsreadline(i);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                roundstring+= " Number of Mines: "+roundsitems[0]+" Number of Clicks: "+roundsitems[1]+" Time left: "+roundsitems[2]+" Success: "+roundsitems[3]+"\n";
            }
            JOptionPane.showMessageDialog(null, roundstring);
        });

        solutionItem.addActionListener(e -> {
            Game.showAllCells();
            try {
                Roundsdev.Roundsedit(game.MINCOUNT, 0 , 50, 0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, "No game running , so no solution available.");
        });

        applicationMenu.add(createItem);
        applicationMenu.add(loadItem);
        applicationMenu.add(startItem);

        detailsMenu.add(roundsItem);
        detailsMenu.add(solutionItem);

        menuBar.add(applicationMenu);
        menuBar.add(detailsMenu);
        menuFrame.setJMenuBar(menuBar);
    }
}