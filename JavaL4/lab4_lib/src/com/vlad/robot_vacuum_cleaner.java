package com.vlad;

import java.util.ArrayList;

public class robot_vacuum_cleaner extends smart_devices {

    private ArrayList<String> rooms;

    public robot_vacuum_cleaner() {
        super();
        this.rooms= new ArrayList<>();
    }

    public robot_vacuum_cleaner(String _name) {
        super(_name,devices.robot_vacuum_cleaner);
        this.rooms= new ArrayList<>();
    }

    public robot_vacuum_cleaner(String _name, boolean _state) {
        super(_name,devices.robot_vacuum_cleaner,_state);
        this.rooms= new ArrayList<>();
    }

    public ArrayList<String> getRooms() {
        return rooms;
    }

    public void addRoom(String _newRoom){
        rooms.add(_newRoom);
    }

    public boolean deleteRoom(int _index){
        if(_index>=rooms.size())
            return false;
        rooms.remove(_index);
        return true;
    }
}
