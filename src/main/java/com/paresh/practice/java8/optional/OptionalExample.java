package com.paresh.practice.java8.optional;

import java.util.Optional;

public class OptionalExample {

    public static void main(String ... args){

        Optional<String> str = Optional.of("asas");
        getStudentName("fdgfd");

    }

    private static void getStudentName(String a) {
        Optional<String> a1 = Optional.ofNullable(a);
        a1.ifPresent(System.out::println);

        //ifPresent vs isPresent

        a1.orElse("missing");
        a1.orElseGet(String::new);
        a1.orElseThrow(RuntimeException::new);
    }

    public String getStreetName(User user) {
        return Optional.ofNullable(user) // Wrap user in Optional
                .map(User::getAddress) // Get the address, if user is not null
                .map(Address::getStreet) // Get the street name, if address is not null
                .orElse("Unknown"); // Default to "Unknown" if any of the above are null
    }

    public class User {
        private Address address;

        public User(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

    public class Address {
        private String street;

        public Address(String street) {
            this.street = street;
        }

        public String getStreet() {
            return street;
        }
    }
}
