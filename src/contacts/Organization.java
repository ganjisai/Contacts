package contacts;

import java.util.ArrayList;
import java.util.Arrays;

public class Organization extends Contact{
    protected String address;

    public Organization(String name, String phoneNumber, String address) {
        super(name, phoneNumber);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public void printInfo() {
        System.out.print("Organization name: " + this.getName() + "\n" +
                "Address: " + this.getAddress() + "\n" +
                "Number: " + this.getPhoneNumber() + "\n" +
                "Time created: " + this.getCreatedAt() + "\n" +
                "Time last edit: " + this.getEditedAt() + "\n");
    }
    @Override
    public ArrayList<String> getFields() {
        return new ArrayList<>(Arrays.asList("name", "address", "number"));
    }

    @Override
    public void setField(String field, String value) {
        if (field.equals("name"))
            this.setName(value);
        else if (field.equals("address"))
            this.setAddress(value);
        else
            this.setPhoneNumber(value);
    }

    public static class Builder extends Contact.Builder {
        protected String address;
        Builder () {}

        public void setAddress(String address) {
            this.address = address;
        }

        public Organization build() {
            return new Organization(name, phoneNumber, address);
        }
    }
}
