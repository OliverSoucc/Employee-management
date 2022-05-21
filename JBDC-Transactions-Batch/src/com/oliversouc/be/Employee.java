package com.oliversouc.be;

public class Employee {
    private int ID;
    private String name;
    private double personalBonus;
    private byte onLeave;
    private String phoneNumber;
    private double realSalary;

    public Employee(int ID, String name, double personalBonus, byte onLeave, String phoneNumber, double realSalary) {
        this.ID = ID;
        this.name = name;
        this.personalBonus = personalBonus;
        this.onLeave = onLeave;
        this.phoneNumber = phoneNumber;
        this.realSalary = realSalary;
    }

    public Employee(String name, double personalBonus, byte onLeave, String phoneNumber, double realSalary) {
        this.name = name;
        this.personalBonus = personalBonus;
        this.onLeave = onLeave;
        this.phoneNumber = phoneNumber;
        this.realSalary = realSalary;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPersonalBonus() {
        return personalBonus;
    }

    public byte getOnLeave() {
        return onLeave;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonalBonus(double personalBonus) {
        this.personalBonus = personalBonus;
    }

    public void setOnLeave(byte onLeave) {
        this.onLeave = onLeave;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return ID + "  " + name + "  " + personalBonus + "  "
                + onLeave + "  " + phoneNumber;
    }

    public double getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(double realSalary) {
        this.realSalary = realSalary;
    }

}
