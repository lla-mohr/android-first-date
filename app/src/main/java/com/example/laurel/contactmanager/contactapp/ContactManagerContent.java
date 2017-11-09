package com.example.laurel.contactmanager.contactapp;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContactManagerContent {

    public static List<Person> ITEMS = new ArrayList<Person>();
    public static Map<String, Person> ITEM_MAP = new LinkedHashMap<String, Person>();

    public ContactManagerContent(BufferedReader br) {
        try {
            //Parse each input line from the internal file into a tokenizer
            String inputString = "";
            String[] token;
            while ((inputString = br.readLine()) != null) {
                token = inputString.split(",");
                //Statically call addItem(Person)
                addItem(new ContactManagerContent.Person(token[0], token[1], token[2], token[3], token[4]));
            }
        } catch (Exception e) {
        }

    }


    public static void addItem(Person item) {
        //add Person object to corresponding data structures
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void removeItem(Person mItem) {
        //remove Person object from corresponding data structures
        ITEMS.remove(mItem);
        ITEM_MAP.remove(mItem.id);
    }

    public static class Person {

        //Person object
        public String id;
        public String firstName;
        public String lastName;
        public String phoneNumber;
        public String email;

        public Person(String id, String firstName, String lastName, String phoneNumber, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + ", " + phoneNumber;
        }
    }
}
