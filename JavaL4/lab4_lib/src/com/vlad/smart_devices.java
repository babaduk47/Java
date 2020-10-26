package com.vlad;

public abstract class smart_devices {

    private String name;
    private final devices TypeDevices;
    private boolean state;

    public String getName() {
        return name;
    }

    public devices getTypeDevices() {
        return TypeDevices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isState() {
        return state;
    }

    public String getState(){
        if(state) return "Включен";
        else return "Выключен";
    }

    public void changeState() {
        state=!state;
    }

    public smart_devices(){
        this.name="uninitialized";
        this.state=false;
        this.TypeDevices=devices.noType;
    }

    public smart_devices(String _name, devices _TypeDevices){
        this.name=_name;
        this.state=false;
        this.TypeDevices=_TypeDevices;
    }

    public smart_devices(String _name, devices _TypeDevices, boolean _state){
        this.name=_name;
        this.state=_state;
        this.TypeDevices=_TypeDevices;
    }
}
