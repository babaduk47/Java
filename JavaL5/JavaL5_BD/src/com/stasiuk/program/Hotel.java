package com.stasiuk.program;

public class Hotel {
    public int id;
    public String name;
    public int CountFloor;
    public int Stars;
    public int CountRooms;

    public Hotel(String name, int countFloor, int stars, int countRooms) {
        this.name = name;
        CountFloor = countFloor;
        Stars = stars;
        CountRooms = countRooms;
    }

    public Hotel(int id, String name, int countFloor, int stars, int countRooms) {
        this.id = id;
        this.name = name;
        CountFloor = countFloor;
        Stars = stars;
        CountRooms = countRooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", CountFloor=" + CountFloor +
                ", Stars=" + Stars +
                ", CountRooms=" + CountRooms +
                '}';
    }
}
