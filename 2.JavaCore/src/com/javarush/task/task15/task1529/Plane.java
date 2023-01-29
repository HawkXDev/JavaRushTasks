package com.javarush.task.task15.task1529;

public class Plane implements CanFly {
    private int numberOfPassengers;

    @Override
    public void fly() {

    }

    public Plane(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }
}
