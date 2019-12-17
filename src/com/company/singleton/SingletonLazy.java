package com.company.singleton;

/**
 * https://viblo.asia/p/cac-loai-design-pattern-co-ban-4P856Oe1KY3
 * Cách này là lazy -> Chỉ khởi tạo khi client yêu cầu instance
 * Ưu điểm: hơn thằng eager ở chỗ khi nào client gọi thì instance mới đc tạo và return
 * Nhược: lại dính vấn đề về đa luồng
 */
public class SingletonLazy {

    private static SingletonLazy instance = null;

    /**
     * Để là private, cả biến instance cũng vậy
     * Bắt buộc phải láy instance qua static function
     */
    private SingletonLazy() {
    }

    /**
     * Cái này ok với các case thông thường
     * Nhưng trong môi trường đa luồng có thể sai.
     * KHi mà 2 luồng cùng getInstance 1 lúc, thì nó nếu check ra là var = null và sẽ tạo mới instance
     * @return
     */
    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
