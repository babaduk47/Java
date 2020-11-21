package com.L5;

public class Auto implements Cloneable{

    private AutoType type;
    private int number;

    public Auto(AutoType _type, int _number){
        this.type = _type;
        this.number = _number;
    }

    @Override
    public Auto clone() throws CloneNotSupportedException{
        Auto copy = new Auto(type, number);
        return copy;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public AutoType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "type=" + type +
                ", number=" + number +
                '}';
    }
}
