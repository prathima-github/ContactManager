package com;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactService {

    Map<String, Contact> contactList = new ConcurrentHashMap<>();

    public void addContact(String firstName, String lastName, String phoneNumber){
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        validateContact(contact);
        String key = generateKey(firstName,lastName);
        if(!contactList.containsKey(key)){
            contactList.put(key,contact);
        }else{
            throw new RuntimeException("Contact already exists");
        }

    }
    private void validateContact(Contact contact){
        contact.validateFirstName();
        contact.validateLastName();
        contact.validatePhoneNumber();
    }
    private String generateKey(String firstName, String lastName){
        return String.format("%s-%s",firstName,lastName);
    }



}
