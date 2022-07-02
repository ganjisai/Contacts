package contacts;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Contact> contacts;

    private static final Scanner scanner = new Scanner(System.in);

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact() {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();
        if (!type.matches("(person|organization)")) {
            System.out.println("wrong contact type");
            return;
        }
        Contact contact =new Contact();

        if ("person".equals(type)) {
            Person.Builder builder = new Person.Builder();
            System.out.println("Enter the name:");
            builder.setName(scanner.nextLine());

            System.out.println("Enter the surname:");
            builder.setSurname(scanner.nextLine());

            System.out.println("Enter birth date:");
            try {
                //LocalDate date = LocalDate.parse(scanner.nextLine());
                String date = scanner.nextLine();
                if (!date.isEmpty()) {
                    builder.setBirthDate(date);
                }
                else {
                    date = "[no data]";
                    builder.setBirthDate(date);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Wrong format type!");
            }

            System.out.println("Enter the gender (M, F):");
            String gender = scanner.nextLine();
            if (!gender.isEmpty()) {
                if (gender.charAt(0) == 'M' || gender.charAt(0) == 'F') {
                    builder.setGender(gender);
                }
            }
            else {
                gender = "[no data]";
                builder.setGender(gender);
            }

            System.out.println("Enter the number:");
            String number = scanner.nextLine();
            builder.setPhoneNumber(number);
            contact = builder.build();
        }
        else if ("organization".equals(type)) {
            Organization.Builder builder = new Organization.Builder();

            System.out.println("Enter the organization name:");
            builder.setName(scanner.nextLine());

            System.out.println("Enter the address:");
            builder.setAddress(scanner.nextLine());

            System.out.println("Enter the number:");
            builder.setPhoneNumber(scanner.nextLine());
            contact = builder.build();
        }
        this.contacts.add(contact);
        System.out.println("Record added.");
    }
    public void remove(int id) {
        if (this.contacts.get(id) != null) {
            this.contacts.remove(id - 1);
            System.out.println("Record removed.");
        }
    }
    public void edit(int record) {
        Contact contact = this.contacts.get(record - 1);
        if (contact instanceof Person) {
            System.out.println("Select a field (name, surname, birth, gender, number):");
        }
        if (contact instanceof Organization) {
            System.out.println("Select a field (name, address, number):");
        }
        String field = scanner.nextLine();
        ArrayList<String> fields = contact.getFields();
        if (!fields.contains(field)) {
            System.out.println("Wrong field!");
            return;
        }
        System.out.printf("Enter %s:", field);
        String newValue = scanner.nextLine();

        contact.setField(field, newValue);
        contact.setEditedAt(LocalDateTime.now());
        System.out.println("Record updated.");
    }

    public int count() {
        return this.contacts.isEmpty() ? 0 : this.contacts.size();
    }

    public void getList() {
        if (!this.contacts.isEmpty()) {
            int i = 1;
            for (Contact contact : this.contacts) {
                System.out.printf("%d. %s", i, contact.getName());
                if (contact instanceof Person)
                    System.out.printf(" %s", ((Person) contact).getSurname());
                i++;
                System.out.println();
            }
        }
        else {
            System.out.println("No records!");
        }
    }
    public void getInfo(int record) {
        Contact contact = contacts.get(record - 1);
        if (contact instanceof Person) {
            contact.printInfo();
        }
        else if (contact instanceof Organization) {
            contact.printInfo();
        }
    }

    public ArrayList<Contact> search(String searchQuery) {
        searchQuery = searchQuery.toLowerCase();
        Pattern pattern = Pattern.compile(".*" + searchQuery + ".*");
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : this.contacts) {
            if (pattern.matcher(contact.getName().toLowerCase()).matches() ||
                    pattern.matcher(contact.getPhoneNumber().toLowerCase()).matches() ||
                    (contact instanceof Person && pattern.matcher(((Person) contact).getSurname().toLowerCase()).matches())) {
                contacts.add(contact);
            }
        }
        return contacts;
    }
}
