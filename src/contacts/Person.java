package contacts;

import java.util.ArrayList;
import java.util.Arrays;

public class Person extends Contact{
    private String surname;
    private String birthDate;
    private String gender;

    public Person(String name, String phoneNumber, String surname, String birthDate, String gender) {
        super(name, phoneNumber);
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public void printInfo() {
        System.out.print("Name: " + this.getName() + "\n" +
                "Surname: " + this.getSurname() + "\n" +
                "Birth date: " + this.getBirthDate() + "\n" +
                "Gender: " + this.getGender() + "\n" +
                "Number: " + this.getPhoneNumber() + "\n" +
                "Time created: " + this.getCreatedAt() + "\n" +
                "Time last edit: " + this.getEditedAt() + "\n") ;
    }
    @Override
    public ArrayList<String> getFields() {
        return new ArrayList<>(Arrays.asList("name", "surname", "birth", "gender", "number"));
    }

    @Override
    public void setField(String field, String value) {
        switch (field) {
            case "name" -> this.setName(value);
            case "surname" -> this.setSurname(value);
            case "birth" -> this.setBirthDate(value);
            case "gender" -> this.setGender(value);
            default -> this.setPhoneNumber(value);
        }
    }

    public static class Builder extends Contact.Builder {
        protected String surname;
        protected String birthDate;
        protected String gender;

        public Builder() {
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }
        public void setGender(String gender) {
            this.gender = gender;
        }
        public Person build() {
            return new Person(name, phoneNumber, surname, birthDate, gender);
        }
    }
}
