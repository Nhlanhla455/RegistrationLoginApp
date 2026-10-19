package registrationloginapp;

import java.util.Scanner;

/** Console app: register, log in, then send QuickChat messages. */
public class LoginApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login user;

        // ---------- Registration ----------
        do {
            System.out.println("=== Registration ===");
            System.out.print("First name: ");
            String first = input.nextLine().trim();
            System.out.print("Last name: ");
            String last = input.nextLine().trim();
            System.out.print("Username: ");
            String username = input.nextLine().trim();
            System.out.print("Password: ");
            String password = input.nextLine();
            System.out.print("Cell number (e.g. +27831234567): ");
            String cell = input.nextLine().trim();

            user = new Login(first, last, username, password, cell);

            System.out.println(user.getUsernameMessage());
            System.out.println(user.getPasswordMessage());
            System.out.println(user.getCellMessage());
            System.out.println(user.registerUser());
            System.out.println();
        } while (!user.isRegistered());

        // ---------- Login ----------
        boolean loggedIn = false;
        do {
            System.out.println("=== Login ===");
            System.out.print("Username: ");
            String enteredUser = input.nextLine().trim();
            System.out.print("Password: ");
            String enteredPass = input.nextLine();

            String status = user.returnLoginStatus(enteredUser, enteredPass);
            System.out.println(status);

            loggedIn = status.startsWith("Welcome");
        } while (!loggedIn);

        // ---------- QuickChat menu ----------
        System.out.println();
        System.out.println("Welcome to QuickChat.");

        int limit = 0;
        boolean validLimit = false;
        do {
            System.out.print("How many messages do you want to send? ");
            try {
                limit = Integer.parseInt(input.nextLine().trim());
                validLimit = limit > 0;
                if (!validLimit) {
                    System.out.println("Please enter a number greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (!validLimit);

        int entered = 0;
        boolean quit = false;

        while (!quit) {
            System.out.println();
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    if (entered >= limit) {
                        System.out.println("You have reached your message limit.");
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                        break;
                    }

                    String recipient;
                    Message tempCheck;
                    String recipientResult;
                    do {
                        System.out.print("Enter recipient cell number: ");
                        recipient = input.nextLine().trim();
                        tempCheck = new Message(recipient, "placeholder");
                        recipientResult = tempCheck.checkRecipientCell();
                        System.out.println(recipientResult);
                    } while (!recipientResult.equals("Cell phone number successfully captured."));

                    String messageText;
                    String lengthResult;
                    Message message;
                    do {
                        System.out.print("Enter your message: ");
                        messageText = input.nextLine();
                        message = new Message(recipient, messageText);
                        lengthResult = message.checkMessageLength();
                        System.out.println(lengthResult);
                    } while (!lengthResult.equals("Message ready to send."));

                    message.createMessageHash();
                    System.out.println(message.getMessageIDGeneratedText());
                    System.out.println("Message Hash: " + message.getMessageHash());

                    System.out.println("Choose an option: 1) Send  2) Disregard  3) Store");
                    String sendChoice = input.nextLine().trim();
                    int sendOption;
                    switch (sendChoice) {
                        case "2": sendOption = 2; break;
                        case "3": sendOption = 3; break;
                        default: sendOption = 1;
                    }

                    System.out.println(message.SentMessage(sendOption));

                    if (sendOption != 2) {
                        System.out.println("Message ID: " + message.getMessageID());
                        System.out.println("Message Hash: " + message.getMessageHash());
                        System.out.println("Recipient: " + message.getRecipient());
                        System.out.println("Message: " + message.getMessageText());
                    }

                    entered++;

                    if (entered >= limit) {
                        System.out.println();
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    }
                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    quit = true;
                    System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        input.close();
    }
}