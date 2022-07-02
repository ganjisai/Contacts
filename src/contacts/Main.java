package contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file;
        Scanner scanner = new Scanner(System.in);
        PhoneBook contacts = new PhoneBook();

        if (args.length != 0) {
            file = new File(args[1]);
            if (file.exists()) try {
                contacts = (PhoneBook) SerializationUtils.deserialize(args[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        boolean exit = false;
        do {

            System.out.println("Enter action (add, list, search, count, exit):");
            String action = scanner.next();
            switch (action) {
                case "add" -> contacts.addContact();
                case "count" -> System.out.printf("The Phone Book has %d records.\n", contacts.count());
                case "list" -> {
                    contacts.getList();
                    if (contacts.count() > 0) {
                        int record;
                        System.out.println();
                        System.out.println("Enter action ([number], back):");
                        action = scanner.next();
                        if ("back".equals(action)) {
                            break;
                        }
                        try {
                            record = Integer.parseInt(action);
                            if (record <= 0 || record > contacts.count()) {
                                System.out.println("Wrong number!");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong number entered");
                            break;
                        }
                        contacts.getInfo(record);
                        System.out.println();
                        System.out.println("Enter action (edit, delete, menu):");
                        action = scanner.next();
                        action(contacts, action, record);
                    }
                }
                case "search" -> {
                    boolean searchExit = false;
                    int record;
                    do {
                        System.out.println("Enter search query:");
                        String searchQuery = scanner.next().trim();
                        ArrayList<Contact> matchingContacts = contacts.search(searchQuery);
                        if (matchingContacts.isEmpty()) {
                            System.out.println("No matching records!");
                        }
                        int i = 1;
                        for (Contact contact : matchingContacts) {
                            System.out.printf("%d. %s", i, contact.getName());
                            if (contact instanceof Person)
                                System.out.printf(" %s", ((Person) contact).getSurname());
                            i++;
                            System.out.println();
                        }
                        System.out.println();
                        System.out.println("Enter action ([number], back, again):");
                        action = scanner.next();
                        if ("back".equals(action)) {
                            return;
                        }
                        if ("again".equals(action)) {
                            searchExit = true;
                        }
                    } while (searchExit);
                    record = Integer.parseInt(action);
                    try {
                        if (record <= 0 || record > contacts.count()) {
                            System.out.println("Wrong number!");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        break;
                    }
                    contacts.getInfo(record);
                    System.out.println();
                    System.out.println("Enter action (edit, delete, menu):");
                    action = scanner.nextLine().trim();
                    action(contacts, action, record);
                }
                case "exit" -> exit = true;
            }
            System.out.println();
        } while (!exit);

        if (args.length != 0) try {
            SerializationUtils.serialize(contacts, args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void action(PhoneBook contacts, String action, int record) {
        switch (action) {
            case "edit":
                contacts.edit(record);
                break;

            case "delete":
                contacts.remove(record);
                break;

            case "menu":
                break;

            default:
                System.out.println("Wrong action!");
        }
    }
}
