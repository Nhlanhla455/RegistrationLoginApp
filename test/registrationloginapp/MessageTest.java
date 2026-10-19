package registrationloginapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MessageTest {

    @Before
    public void setUp() {
        Message.resetSession();
    }

    @Test
    public void testMessageLengthTooLong() {
        String longText = "a".repeat(260);
        Message m = new Message("0012345678", 0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                m.checkMessageLength());
    }

    @Test
    public void testMessageLengthOk() {
        Message m = new Message("0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", m.checkMessageLength());
    }

    @Test
    public void testRecipientCorrectlyFormatted() {
        Message m = new Message("0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message m = new Message("0112345678", 1, "08575975889",
                "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.",
                m.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message m = new Message("0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }

    @Test
    public void testCreateMessageHashLoop() {
        String[] ids = {"0012345678", "0112345678"};
        int[] numbers = {0, 1};
        String[] texts = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you receive the payment?"
        };
        String[] expected = {"00:0:HITONIGHT", "01:1:HIPAYMENT"};

        for (int i = 0; i < texts.length; i++) {
            Message m = new Message(ids[i], numbers[i], "+27718693002", texts[i]);
            assertEquals(expected[i], m.createMessageHash());
        }
    }

    @Test
    public void testMessageIDGeneratedText() {
        Message m = new Message("0012345678", 0, "+27718693002", "Test");
        assertEquals("Message ID generated: 0012345678", m.getMessageIDGeneratedText());
        assertTrue(m.checkMessageID());
    }

    @Test
    public void testSentMessageSend() {
        Message m = new Message("0012345678", 0, "+27718693002", "Test");
        assertEquals("Message successfully sent.", m.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message m = new Message("0112345678", 1, "+27718693002", "Test");
        assertEquals("Press 0 to delete the message.", m.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        Message m = new Message("0212345678", 2, "+27718693002", "Test");
        m.createMessageHash();
        assertEquals("Message successfully stored.", m.SentMessage(3));
    }

    @Test
    public void testTotalMessagesSent() {
        Message m1 = new Message("0012345678", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");
        m1.SentMessage(1);

        Message m2 = new Message("0112345678", 1, "+27838884567",
                "Where are you? You are late! I have asked you to be on time.");
        m2.SentMessage(2);

        assertEquals(1, Message.returnTotalMessages());
    }
}