package com;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void validateFirstName(){
        if(this.firstName==null || this.firstName.trim().isEmpty())
            throw new RuntimeException("First name cannot be null or blank");
    }
    public void validateLastName(){
         if(this.lastName == null || lastName.trim().isEmpty())
             throw new RuntimeException("Last name cannot be null or blank");
    }
    public void validatePhoneNumber(){
        if(this.phoneNumber == null || this.phoneNumber.trim().isEmpty())
            throw new RuntimeException("Phone number cannot be null or blank");
        if(!this.phoneNumber.matches("\\d+"))
            throw new RuntimeException("Phone number should contain only digits");
        if(this.phoneNumber.trim().length()!=10)
            throw new RuntimeException("Phone number length should be 10");
        if(!this.phoneNumber.trim().startsWith("0"))
            throw new RuntimeException("Phone number should start with 0");

    }
}
