package com.vlad;

public class dishwasher extends smart_devices {

    private String cur_mode;
    private double temp;
    private boolean child_protection=true;

    public String[] washing_modes = {
            "standard", //стандартная
            "fast", //быстрая
            "delicate", //деликатная
            "soak", //замачивание
            "intense" //интесиваная
    };


    public dishwasher() {
        super();
        this.cur_mode=washing_modes[0];
        this.temp =50;
    }

    public dishwasher(String _name) {
        super(_name,devices.dishwasher);
        this.cur_mode=washing_modes[0];
        this.temp =50;
    }

    public dishwasher(String _name, boolean _state) {
        super(_name,devices.dishwasher,_state);
        this.cur_mode=washing_modes[0];
        this.temp =50;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        if(temp<=40)
            this.temp=40;
        else if(temp>=70)
            this.temp=70;
        else
            this.temp = temp;
    }

    public String getMode() {
        return cur_mode;
    }

    public boolean setMode(int index) {
        if(index>=0 && index<washing_modes.length) {
            this.cur_mode=washing_modes[index];
            return true;
        }
        return false;
    }

    public boolean isChild_protection() {
        return child_protection;
    }

    public void changeChild_protection() {
        this.child_protection = !this.child_protection;
    }
}
