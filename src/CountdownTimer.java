import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
public class CountdownTimer {
    public int secondsRemaining;
    private Timer timer = new Timer();
    private JLabel label;

    public CountdownTimer(int seconds) {
        this.secondsRemaining = seconds;
    }
    public void timerStop(){
        timer.cancel();
    }
    public void timerStart(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsRemaining--;
                WindowProvider.setTime(secondsRemaining);
                WindowProvider.updateTitle();
                if (Objects.equals(secondsRemaining, 0)) {
                    Game.showAllCells();
                    JOptionPane.showMessageDialog(null, "Sorry, you lost the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    this.cancel();
                    System.exit(0);
                }
                ;
            }
        }, 0, 1000);

    }

}
