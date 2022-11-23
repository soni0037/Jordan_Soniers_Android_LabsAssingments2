package alongquin.CST2335.soni0037;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class represents a single chat message
 * @author Jordan Sonier
 * @version 1.0
 */
@Entity
public class ChatMessage {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Message")
    private String message;
    @ColumnInfo(name = "TimeSent")
    private String timeSent;
    @ColumnInfo(name = "SendOrRecieve")
    private boolean isSentButton;

    /**
     * Default Constructors
     * @param message user message
     * @param timeSent time message sent
     * @param isSentButton button sent or not
     */
    ChatMessage(String message, String timeSent, boolean isSentButton)
    {
        this.message = message;
        this.timeSent = timeSent;
        this.isSentButton = isSentButton;
    }

    /**
     * returns message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * sets message
     * @param message text
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * returns time sent
     * @return timeSent
     */
    public String getTimeSent() {
        return timeSent;
    }

    /**
     * sets time set
     * @param timeSent time message was sent
     */
    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    /**
     * button sent
     * @return isSentButton
     */
    public boolean getSentButton() {
        return isSentButton;
    }

    /**
     * set button sent
     * @param sentButton
     */
    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }
}