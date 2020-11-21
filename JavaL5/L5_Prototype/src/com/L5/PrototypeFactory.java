package com.L5;

public class PrototypeFactory {
    private Auto prototype;

    public PrototypeFactory(Auto _prototype) {
        this.prototype=_prototype;
    }

    public void setPrototype(Auto prototype) {
        this.prototype = prototype;
    }

    public Auto clonePrototype() throws CloneNotSupportedException {
        return (Auto) prototype.clone();
    }
}
