package com.stasiuk.program;

import dnl.utils.text.table.TextTable;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        while (true) {
            System.out.println("Выберете таблицу:");
            System.out.println("[1] Hotel");
            System.out.println("[2] Services");
            System.out.println("[3] Hotel-Services");
            System.out.println("[0] Выход");

            switch (getMenu()) {
                case 0:
                    System.exit(0);
                    break;
                case 1: {
                    ControlHotel();
                    break;
                }
                case 2: {
                    ControlServices();
                    break;
                }
                case 3: {
                    ControlHotelServices();
                    break;
                }
                default: {
                    System.out.println("Неверный ввод, повторите попытку:");
                    break;
                }
            }
        }
    }

    private static void menuTable(){
        System.out.println("Выберете действие:");
        System.out.println("[1] Добавить добавить");
        System.out.println("[2] Удадить запись по id");
        System.out.println("[0] Меню выбора таблиц");
    }

    private static void ControlServices() {
        boolean exit=false;
        while (!exit){
            DB db =new DB();
            db.getAllHotel();
            ArrayList<Services>  allServices = db.getAllServices();
            System.out.format("%10s|%30s|%20s\n", "ID", "Name", "Price");

            for(Services s : allServices) {
                System.out.format("%10d|%30s|%20d\n", s.id_servis, s.name_servis,s.price_servis);
            }

            menuTable();

            switch (getMenu()) {
                case 0:
                    exit=true;
                    break;
                case 1: {
                    Services s = AddServices();
                    if(s!=null){
                        if(db.addServices(s))
                            System.out.println("Запись добавлена" );
                        else System.out.println("Неудалось добавить запись");
                    }
                    else System.out.println("Неудалось создать сервис");
                    break;
                }
                case 2: {
                    in = new Scanner(System.in);
                    System.out.print("Введите ID записи, которую хотите удалить : ");
                    int id = in.nextInt();
                    if(db.deleteServices(id)){
                        System.out.println("Запись с ID "+ id + " Удалена" );
                    }else{
                        System.out.println("Неудалось удалить запись");
                    }
                    break;
                }
                default: {
                    System.out.println("Неверный ввод, повторите попытку:");
                    break;
                }
            }
        }
    }

    private static Services AddServices() {
        in = new Scanner(System.in);
        System.out.print("Названия Сервиса : ");
        String name = in.next();
        System.out.print("Цена : ");
        int price = in.nextInt();
        Services result =new Services(name,price);
        return result;
    }

    private static int getMenu() {
        in = new Scanner(System.in);
        System.out.println("Выбор : ");
        return in.nextInt();
    }

    private static void ControlHotelServices() {
        boolean exit=false;
        while (!exit){
            DB db =new DB();
            db.getAllHotelServices();
            ArrayList<HotelServices>  allHotelServices = db.getAllHotelServices();
            System.out.format("%10s|%30s|%10s|%30s|%10s\n", "ID", "Hotel", "id_h", "service","id_s");

            for(HotelServices hs : allHotelServices) {
                System.out.format("%10d|%30s|%10d|%30s|%10d\n", hs.id, hs.hotel,hs.id_hotel, hs.services,hs.id_services);
            }

            menuTable();

            switch (getMenu()) {
                case 0:
                    exit=true;
                    break;
                case 1: {
                    HotelServices hs = AddHotelServices();
                    if(hs!=null){
                        if(db.addHotelServices(hs))
                            System.out.println("Запись добавлена" );
                        else System.out.println("Неудалось добавить запись");
                    }
                    else System.out.println("Неудалось создать отель");
                    break;
                }
                case 2: {
                    in = new Scanner(System.in);
                    System.out.print("Введите ID записи, которую хотите удалить : ");
                    int id = in.nextInt();
                    if(db.deleteHotelServices(id)){
                        System.out.println("Запись с ID "+ id + " Удалена" );
                    }else{
                        System.out.println("Неудалось удалить запись");
                    }
                    break;
                }
                default: {
                    System.out.println("Неверный ввод, повторите попытку:");
                    break;
                }
            }
        }
    }

    private static HotelServices AddHotelServices() {
        in = new Scanner(System.in);
        System.out.print("ID отеля : ");
        int id_h = in.nextInt();
        System.out.print("ID сервиса : ");
        int id_s = in.nextInt();
        HotelServices result =new HotelServices(id_h,id_s);
        return result;
    }

    private static void ControlHotel() {
        boolean exit=false;
        while (!exit){
            DB db =new DB();
            db.getAllHotel();
            ArrayList<Hotel>  allHotel = db.getAllHotel();
            System.out.format("%10s|%30s|%20s|%20s|%20s\n", "ID", "Name", "CountFloor", "Stars","CountRooms");

            for(Hotel h : allHotel) {
                System.out.format("%10d|%30s|%20d|%20d|%20d\n", h.id, h.name,h.CountFloor, h.Stars,h.CountRooms);
            }

            menuTable();

            switch (getMenu()) {
                case 0:
                    exit=true;
                    break;
                case 1: {
                    Hotel h = AddHotel();
                    if(h!=null){
                        if(db.addHotel(h))
                            System.out.println("Запись добавлена" );
                        else System.out.println("Неудалось добавить запись");
                    }
                    else System.out.println("Неудалось создать отель");
                    break;
                }
                case 2: {
                    in = new Scanner(System.in);
                    System.out.print("Введите ID записи, которую хотите удалить : ");
                    int id = in.nextInt();
                    if(db.deleteHotel(id)){
                        System.out.println("Запись с ID "+ id + " Удалена" );
                    }else{
                        System.out.println("Неудалось удалить запись");
                    }
                    break;
                }
                default: {
                    System.out.println("Неверный ввод, повторите попытку:");
                    break;
                }
            }
        }
    }

    private static Hotel AddHotel(){
        in = new Scanner(System.in);
        System.out.print("Названия отеля : ");
        String name = in.next();
        System.out.print("Количество этажей : ");
        int CountFloor = in.nextInt();
        System.out.print("Количество звезд : ");
        int Stars = in.nextInt();
        System.out.print("Количество комнат : ");
        int CountRooms = in.nextInt();
        Hotel result =new Hotel(name,CountFloor,Stars,CountRooms);
        return result;
    }
}
