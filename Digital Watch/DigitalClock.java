import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JFrame {

    private JLabel timeLabel, dateLabel, dayLabel;
    private JButton themeButton;
    private boolean isDarkTheme = false;
    private Font sevenSegFont;

    public DigitalClock() {
        setTitle("Digital Clock");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        sevenSegFont = loadSevenSegmentFont(50f);

        timeLabel = new JLabel();
        timeLabel.setBounds(50, 20, 300, 60);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(sevenSegFont);

        dateLabel = new JLabel();
        dateLabel.setBounds(50, 90, 300, 30);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setFont(sevenSegFont.deriveFont(22f));

        dayLabel = new JLabel();
        dayLabel.setBounds(50, 120, 300, 30);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setFont(sevenSegFont.deriveFont(22f));

        themeButton = new JButton("Switch Theme");
        themeButton.setBounds(125, 170, 140, 30);
        themeButton.setFont(sevenSegFont.deriveFont(16f));

        // Button style (fixed color, no border)
        themeButton.setBackground(new Color(30, 30, 30));
        themeButton.setForeground(Color.WHITE);
        themeButton.setBorderPainted(false);
        themeButton.setFocusPainted(false);
        themeButton.setContentAreaFilled(true);

        themeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDarkTheme = !isDarkTheme;
                applyTheme();
            }
        });

        add(timeLabel);
        add(dateLabel);
        add(dayLabel);
        add(themeButton);

        applyTheme();
        updateTime();

        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();

        setVisible(true);
    }

    private void updateTime() {
        Date now = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");

        timeLabel.setText(timeFormat.format(now));
        dateLabel.setText(dateFormat.format(now));
        dayLabel.setText(dayFormat.format(now));
    }

    private void applyTheme() {
        Color bg = isDarkTheme ? Color.BLACK : Color.WHITE;
        Color fg = isDarkTheme ? Color.CYAN : Color.BLACK;

        getContentPane().setBackground(bg);
        timeLabel.setForeground(fg);
        dateLabel.setForeground(fg);
        dayLabel.setForeground(fg);
        // themeButton color stays same as initialized
    }

    private Font loadSevenSegmentFont(float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("DS-DIGIT.TTF"));
            return font.deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("Monospaced", Font.PLAIN, (int) size);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClock::new);
    }
}