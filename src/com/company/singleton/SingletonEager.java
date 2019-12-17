package com.company.singleton;

/**
 * Cách này là eager. Sẽ tạo luôn single instance ngay khi class load
 * Ưu: Cái này an toàn với môi trường đa luồng.
 * Nhược: Luôn tạo sẵn instance, mặc dù có thể k có thằng nào dùng đến nó
 */
public class SingletonEager {
    private static SingletonEager instance = new SingletonEager();

    /**
     * Để là private, cả biến instance cũng vậy
     * Bắt buộc phải láy instance qua static function
     */
    private SingletonEager() {
    }

    public static SingletonEager getInstance() {
        return instance;
    }
}
