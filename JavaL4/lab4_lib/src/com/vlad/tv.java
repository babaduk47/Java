package com.vlad;

import java.util.ArrayList;

public class tv extends smart_devices {

    private String cur_channel;

    private ArrayList<String> channels=new ArrayList<String>();

    private int curI;

    public tv() {
        super();
        channelInitialization();
    }

    void channelInitialization(){
        channels.add("ТНТ");
        channels.add("1+1");
        channels.add("5 канал");
        this.cur_channel=channels.get(0);
        curI=0;
    }

    public tv(String _name) {
        super(_name,devices.TV);
        channelInitialization();

    }

    public tv(String _name, boolean _state) {
        super(_name,devices.TV,_state);
        channelInitialization();
    }


    public ArrayList<String> getChannels() {
        return channels;
    }

    public String getChannel() {
        return this.cur_channel;
    }

    public void addChannel(String _newRoom){
        channels.add(_newRoom);
    }

    public boolean deleteChannel(int _index){
        if(_index>=channels.size())
            return false;
        if(_index==curI)
            return false;
        channels.remove(_index);
        return true;
    }

    public boolean setChannel(int index){
        if(index>=0 && index<channels.size()) {
            this.cur_channel=channels.get(index);
            curI=index;
            return true;
        }
        return false;
    }

    public String nextChannel() {
        curI++;
        if(curI>=channels.size())
            curI=0;
        if(setChannel(curI))
            return getChannel();
        return "";
    }

    public String prevChannel() {
        curI--;
        if(curI<0)
            curI=channels.size()-1;
        if(setChannel(curI))
            return getChannel();
        return "";
    }
}