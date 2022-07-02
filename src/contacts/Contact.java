package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
        this.editedAt = LocalDateTime.now();
    }
    public Contact(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(LocalDateTime editedAt) {
        this.editedAt = editedAt;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkPhoneValidity(phoneNumber) && hasNumber()) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");
        }
    }
    private boolean checkPhoneValidity(String number) {
        return phoneNumber.matches("\\+?((\\([0-9a-zA-Z]+\\))([ -][0-9a-zA-Z]{2,})*|" +
                "([0-9a-zA-Z]+[ -]\\([0-9a-zA-Z]{2,}\\))([ -][0-9a-zA-Z]{2,})*|" +
                "[0-9a-zA-Z]+([ -][0-9a-zA-Z]{2,})*)");
    }
    public boolean hasNumber() {
        return !this.getPhoneNumber().equals("");
    }
    public void printInfo() {

    }

    public ArrayList<String> getFields() {
        return new ArrayList<>(Arrays.asList("name", "number"));
    }

    public void setField(String field, String value) {
        if (field.equals("name"))
            this.setName(value);
        else
            this.setPhoneNumber(value);
    }
    public static class Builder {
        protected String name;
        protected String phoneNumber;

        public Builder() {
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
