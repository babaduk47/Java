package com.vlad;

public class heater extends smart_devices {

    private double temp;

    public heater() {
        super();
        this.temp=25;
    }

    public heater(String _name) {
        super(_name,devices.heater);
        this.temp=25;
    }

    public heater(String _name, boolean _state) {
        super(_name,devices.heater,_state);
        this.temp=25;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        if(temp<=25)
            this.temp=25;
        else if(temp>=40)
            this.temp=40;
        else
            this.temp = temp;
    }

    public void upTemp() {
        setTemp(getTemp()+1);
    }

    public void downTemp() {
        setTemp(getTemp()-1);
    }
}