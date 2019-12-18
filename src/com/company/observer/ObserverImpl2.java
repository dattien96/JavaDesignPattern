package com.company.observer;

public class ObserverImpl2 implements Observer {
    @Override
    public void update(String sUpdate) {
        System.out.println("Ob 2 receive update string");
    }
}
