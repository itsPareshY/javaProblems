package com.paresh.practice.design.patterns.creational;

/**
 * Telescopic Constructors is a design pattern
 * where you have multiple constructors with different number of arguments.
 * It becomes difficult if too many Optional arguments are there.
 */
public class TelescopicConstructor {

    private final int id;
    private final String name;
    private final String address;
    private final String phone;

    public TelescopicConstructor(int id) {
        this(id, "DefaultName");
    }

    public TelescopicConstructor(int id, String name) {
        this(id, name, "DefaultAddress");
    }

    public TelescopicConstructor(int id, String name, String address) {
        this(id, name, address, "DefaultPhone");
    }

    public TelescopicConstructor(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public static void main(String[] args) {
        TelescopicConstructor telescopicConstructorInst = new TelescopicConstructor(1);
        System.out.println(telescopicConstructorInst);
    }

    @Override
    public String toString() {
        return "TelescopicConstructors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
