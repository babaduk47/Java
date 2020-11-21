package com.stasiuk.program;

public class Services {
    public int id_servis;
    public String name_servis;
    public int price_servis;

    public Services(String name_servis, int price_servis) {
        this.name_servis = name_servis;
        this.price_servis = price_servis;
    }

    public Services(int id_servis, String name_servis, int price_servis) {
        this.id_servis = id_servis;
        this.name_servis = name_servis;
        this.price_servis = price_servis;
    }

    @Override
    public String toString() {
        return "Services{" +
                "id_servis=" + id_servis +
                ", name_servis='" + name_servis + '\'' +
                ", price_servis=" + price_servis +
                '}';
    }
}
