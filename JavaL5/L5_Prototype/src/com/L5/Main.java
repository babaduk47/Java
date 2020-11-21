package com.L5;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        Auto prototypePassenger_car =new Auto(AutoType.passenger_car,1);
        Auto prototypeBus =new Auto(AutoType.bus,1);
        Auto prototypeTruck =new Auto(AutoType.truck,1);

        PrototypeFactory factory= new PrototypeFactory(prototypePassenger_car);
        ArrayList<Auto> prototypes=new ArrayList<>();

        for(int i=0;i<15;i++) {
            if(i==5) factory= new PrototypeFactory(prototypeBus);
            else if(i==10) factory= new PrototypeFactory(prototypeTruck);
            prototypes.add(factory.clonePrototype());
            prototypes.get(i).setNumber(i+1);
        }

        for(Auto prototype : prototypes){
            System.out.println(prototype);
        }
    }
}
