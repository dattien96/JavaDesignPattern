package com.company.observer;

public class Observerimpl1 implements Observer {
    @Override
    public void update(String sUpdate) {
        System.out.println("Ob 1 receive update string");
    }
}
