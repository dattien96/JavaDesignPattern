package com.company.adapter;

public class AdapterNewSystem implements NewSystem {

    private OldSystem oldSystem;

    public AdapterNewSystem(OldSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    /**
     * Nếu client muốn vẫn dùng đối tượng NewSystem và impl của nó lại có cả Old thì dùng adapter này.
     * Sẽ có fun impl convert cụ thể
     * Còn về bản chất do là impl interface NewSystem, nên những đoạn code dùng NewSystem cũ k bị ảnh hưởng, chỉ cần truyền vào impl là đc
     */
    @Override
    public void doSomethingsNews() {
        oldSystem.doSomethingsOld();
        System.out.println(" do somethings by new way");
    }
}
