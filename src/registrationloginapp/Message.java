package registrationloginapp;

import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a single QuickChat message.
 *
 * @author Student
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static int totalMessages = 0;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();

    public Message(String recipient, String messageText) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = totalMessages;

        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        this.messageID = id.toString();
    }

    public Message(String messageID, int messageNumber,
            String recipient, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    public static void resetSession() {
        sentMessages.clear();
        storedMessages.clear();
        totalMessages = 0;
    }

    // ---------- Step 4 ----------

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String getMessageIDGeneratedText() {
        return "Message ID generated: " + messageID;
    }

    // ---------- Step 5 ----------

    public String checkRecipientCell() {
        String pattern = "^\\+27\\d{1,10}$";
        if (recipient != null && java.util.regex.Pattern.matches(pattern, recipient)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.";
    }

    // ---------- Step 6 ----------

    public String checkMessageLength() {
        if (messageText == null) {
            return "Please enter a message of less than 250 characters.";
        }
        if (messageText.length() > 250) {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    public String createMessageHash() {
        String idPrefix = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

        messageHash = (idPrefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
        return messageHash;
    }

    // ---------- Step 7 ----------

    // Choice: 1 = Send, 2 = Disregard, 3 = Store
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                sentMessages.add(this);
                totalMessages++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }

    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append("Message ID: ").append(m.messageID).append(System.lineSeparator());
            sb.append("Message Hash: ").append(m.messageHash).append(System.lineSeparator());
            sb.append("Recipient: ").append(m.recipient).append(System.lineSeparator());
            sb.append("Message: ").append(m.messageText).append(System.lineSeparator());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    // Brief spells this returnTotalMessagess() with a double "s" in one place;
    // using the single-s spelling here since it matches the method table.
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // ---------- Step 8 ----------

    public void storeMessage() {
        storedMessages.add(this);

        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < storedMessages.size(); i++) {
            Message m = storedMessages.get(i);
            json.append("{")
                .append("\"messageID\": \"").append(m.messageID).append("\", ")
                .append("\"messageNumber\": ").append(m.messageNumber).append(", ")
                .append("\"recipient\": \"").append(m.recipient).append("\", ")
                .append("\"message\": \"").append(escapeJson(m.messageText)).append("\", ")
                .append("\"hash\": \"").append(m.messageHash).append("\"")
                .append("}");
            if (i < storedMessages.size() - 1) {
                json.append(", ");
            }
        }
        json.append("]");

        try (FileWriter writer = new FileWriter("messages.json")) {
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    private String escapeJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    // ---------- Getters ----------

    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
}