package com.stasiuk.program;

public class HotelServices {
    public int id;
    public String hotel;
    public int id_hotel;
    public String services;
    public int id_services;

    public HotelServices(int id, String hotel, int id_hotel, String services, int id_services) {
        this.id = id;
        this.hotel = hotel;
        this.id_hotel = id_hotel;
        this.services = services;
        this.id_services = id_services;
    }

    public HotelServices(int id_hotel, int id_services) {
        this.id_hotel = id_hotel;
        this.id_services = id_services;
    }

    @Override
    public String toString() {
        return "HotelServices{" +
                "id=" + id +
                ", hotel='" + hotel + '\'' +
                ", id_hotel=" + id_hotel +
                ", services='" + services + '\'' +
                ", id_services=" + id_services +
                '}';
    }
}
