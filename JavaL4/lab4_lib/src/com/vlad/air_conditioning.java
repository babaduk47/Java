package com.vlad;

public class air_conditioning extends smart_devices {

    private double temp;

    public air_conditioning() {
        super();
        this.temp=20;
    }

    public air_conditioning(String _name) {
        super(_name,devices.air_conditioning);
        this.temp=20;
    }

    public air_conditioning(String _name, boolean _state) {
        super(_name,devices.air_conditioning,_state);
        this.temp=20;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        if(temp<=10)
            this.temp=10;
        else if(temp>=30)
            this.temp=30;
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

