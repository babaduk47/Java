package com.stasiuk.program;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    Connection con;

    private boolean ConOpen() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/lab_bd";
            String login = "root";
            String password = "";
            con = DriverManager.getConnection(url, login, password);
            if (con != null  && con.isValid(0)) {
                return true;
            }
            else
                return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<HotelServices> getAllHotelServices()  {
        ArrayList<HotelServices> result = new ArrayList<HotelServices>();
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT hs.ID, h.Name, hs.`id_hotel`, s.name_servis, hs.`id_serves` FROM `hotel` h, `services` s, `hotel-services` hs WHERE h.ID=hs.id_hotel and s.id_servis=hs.`id_serves` ORDER BY hs.ID");
                    while (rs.next()) {
                        HotelServices hotelServices = new HotelServices(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
                        result.add(hotelServices);
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ArrayList<Services> getAllServices()  {
        ArrayList<Services> result = new ArrayList<Services>();
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `services`");
                    while (rs.next()) {
                        Services services = new Services(rs.getInt(1),rs.getString(2),rs.getInt(3));
                        result.add(services);
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ArrayList<Hotel> getAllHotel()  {
        ArrayList<Hotel> result = new ArrayList<>();
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `hotel`");
                    while (rs.next()) {
                        Hotel hotel = new Hotel(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
                        result.add(hotel);
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
             throwables.printStackTrace();
        }
        return result;
    }

    public boolean addHotelServices(HotelServices _hotelServices)  {
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    boolean rs = stmt.execute("INSERT INTO `hotel-services` (`id_hotel`, `id_serves`) VALUES ('"+_hotelServices.id_hotel+"', '"+_hotelServices.id_services+"')");
                    stmt.close();
                    return rs;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean addServices(Services _services)  {
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    boolean rs = stmt.execute("INSERT INTO `services` (`name_servis`, `price_servis`) VALUES ('"+_services.name_servis+"', '"+_services.price_servis+"')");
                    stmt.close();
                    return rs;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean addHotel(Hotel _hotel)  {
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    boolean rs = stmt.execute("INSERT INTO `hotel` (`Name`, `CountFloor`, `Stars`, `CountRooms`) VALUES ('"+_hotel.name+"', '"+_hotel.CountFloor+"', '"+_hotel.Stars+"', '"+_hotel.CountRooms+"')");
                    stmt.close();
                    return rs;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteServices(int _id)  {
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    boolean rs = stmt.execute("DELETE FROM `services` where id_servis="+_id);
                    stmt.close();
                    return rs;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteHotel(int _id) {
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    boolean rs = stmt.execute("DELETE FROM `hotel` where id=" + _id);
                    stmt.close();
                    return rs;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean deleteHotelServices(int _id)  {
        try {
            if (ConOpen()) {
                try {
                    Statement stmt = con.createStatement();
                    boolean rs = stmt.execute("DELETE FROM `hotel-services` where ID="+_id);
                    stmt.close();
                    return rs;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    con.close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
