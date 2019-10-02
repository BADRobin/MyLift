package main.java.transaction;
import main.java.basic.Action;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import javax.swing.JButton;

public class ActionButtonAppender extends WriterAppender {
    private JButton actionButton;
    private static final String LOG_PATTERN = "%m%n";
    private final String buttonText;

    public ActionButtonAppender(JButton actionButton, String buttonText) {
        super();
        this.actionButton = actionButton;
        this.buttonText = buttonText;
        setThreshold(Level.INFO);
        setLayout(new PatternLayout(LOG_PATTERN));
    }


    @Override
    public void append(LoggingEvent event) {
        // TODO Auto-generated method stub
        if(Action.COMPLETION_TRANSPORTATION.toString().equals(layout.format(event).trim())){
            actionButton.setText(buttonText);
        }
    }


}