package alongquin.CST2335.soni0037;

/**
 * @author Jordan Sonier
 * @version 1.0
 */
public class ChatMessage {
    private String message;
    private String timeSent;
    private boolean isSentButton;

    /**
     * Constructor for message, timeSent, and button pressed
     * @param m message from user
     * @param t time message was sent
     * @param sent if button was clicked to send
     */
    ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    /**
     * Obtains message from user
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * sets message from user
     * @param message sets message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtains time message was sent
     * @return time message was sent
     */
    public String getTimeSent() {
        return timeSent;
    }

    /**
     * sets time message was sent
     * @param timeSent sets time message was sent
     */
    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    /**
     * If button is clicked
     * @return button clicked
     */
    public boolean isSentButton() {
        return isSentButton;
    }

    /**
     * sets if button is clicked
     * @param sentButton boolean for if button is clicked
     */
    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }
}
