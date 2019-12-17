package com.company.singleton;

/**
 * Class này sẽ impl lazy + thread - safe
 */
public class SingletonLazyThreadSafe {

    private static SingletonLazyThreadSafe instance = null;

    private SingletonLazyThreadSafe() {
    }


    /**
     * Cách này sẽ thread-safe.
     * Tuy nhiên hiệu suất kém. Mỗi lần gọi lại phải chạy 1 lần đồng bộ.
     * Trong khi nó chỉ có ý nghĩa lần đầu tiên. Từ những lần sau đã khác null rồi thì có đồng boojj hay không thì cũng
     * return luôn chứ k tạo mới
     * @return
     */
    public static SingletonLazyThreadSafe getInstance() {
        synchronized (SingletonLazyThreadSafe.class) {
            if (instance == null) {
                instance = new SingletonLazyThreadSafe();
            }
        }
        return instance;
    }

    /**
     * Check null 2 lần. giải quyêt vấn đề trên
     * @return
     */
    public static SingletonLazyThreadSafe getInstance2() {
        if (instance == null) {
            synchronized (SingletonLazyThreadSafe.class) {
                if (instance == null) {
                    instance = new SingletonLazyThreadSafe();
                }
            }
        }
        return instance;
    }
}
