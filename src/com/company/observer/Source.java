package com.company.observer;

public interface Source {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyAllObserver();
}
