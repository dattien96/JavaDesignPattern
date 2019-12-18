package com.company.observer;

import java.util.ArrayList;
import java.util.List;

public class SourceImpl implements Source {

    private List<Observer> observers = new ArrayList<>();

    @java.lang.Override
    public void attach(Observer observer) {
        if(observer == null) throw new NullPointerException("Null Observer");
        synchronized (this) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }
    }

    @java.lang.Override
    public void detach(Observer observer) {
        synchronized (this) {
            observers.remove(observer);
        }
    }

    /**
     * Ở đây dùng synch vì như sau.
     * Có thể có 1 ob subcribe cùng lúc với fun này đc gọi.
     * Đảm bảo chỉ noti đến các ob đã sub trước time này.
     */
    @java.lang.Override
    public void notifyAllObserver() {
        List<Observer> tmp = null;
        synchronized (this) {
            tmp = new ArrayList<>(observers);
        }
        tmp.forEach(ob -> ob.update("Update all ob"));
    }
}
