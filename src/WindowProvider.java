import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Optional;


public class WindowProvider {
    private static JFrame frame;
    private static String title;
    private static int flagged = 0;
    private static int time = 0;
    private static CountdownTimer timer;
    public WindowProvider(int width, int height, int gridSize, String title, Game game, Handler handler){
        WindowProvider.title = title;
        frame = new JFrame(title);
        new MenuBarCreator(game,handler,frame);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new Grid(new GridLayout(gridSize, gridSize), handler);

        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);


        timer = new CountdownTimer(game.gamevars[2]);
        timer.timerStart();

    }
    public static void incrementFlags(int amount) {
        flagged+=amount;
    }
    public static void setTime(int seconds){
        time=seconds;
    }
    public static void updateTitle(){
        frame.setTitle(title + "Mines: " + Game.MINCOUNT + " - Flags: " + flagged + " Time Remaining: "+ Integer.toString(time));
    }
    public static Optional<CountdownTimer> getTimer(){
        return Optional.ofNullable(timer);
    }
}
