package clock;

import java.awt.*;
import static java.lang.Math.round;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author uross
 */
public class Clock extends JPanel {

    private Timer timer;

    private int r;
    private int hour, min, sec;
    private int angleHour, angleMin, angleSec;
    private Calendar cal = new GregorianCalendar();

    public Clock() {
        setBackground(Color.black);

        timer = new Timer();
        timer.schedule(new TickTimerTask(), 0, 1000);
    }

    private class TickTimerTask extends TimerTask {

        @Override
        public void run() {
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.translate(getWidth() / 2, getHeight() / 2);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10)); //deblja linija

        cal = new GregorianCalendar();
        hour = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
        sec = cal.get(Calendar.SECOND);

        r = 400;
        angleSec = -sec * 6;
        g.setColor(new Color(255, 100, 150));
        g.drawArc(0 - r / 2, 0 - r / 2, r, r, 90, angleSec);

        r = 360;
        angleMin = -(min * 60 + sec) / 10;
        g.setColor(new Color(150, 100, 255));
        g.drawArc(0 - r / 2, 0 - r / 2, r, r, 90, angleMin);

        r = 320;
        angleHour = -((hour % 12) * 3600 + min * 60 + sec) / 120;
        g.setColor(new Color(150, 255, 100));
        g.drawArc(0 - r / 2, 0 - r / 2, r, r, 90, angleHour);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("Clock");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 600);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().add(new Clock());
                //frame.setUndecorated(true);
                //frame.setOpacity((float) 0.9);
                frame.setVisible(true);
            }
        });
    }
}
