package com.company;

import com.company.adapter.AdapterNewSystem;
import com.company.adapter.NewSystem;
import com.company.adapter.OldSystemImpl;
import com.company.bridge.*;
import com.company.chain.*;
import com.company.decorator.BasicCar;
import com.company.decorator.Car;
import com.company.decorator.LuxuryCar;
import com.company.decorator.SportsCar;
import com.company.iterator.CollectionBox;
import com.company.proxy.CommandExecutor;
import com.company.proxy.CommandExecutorProxy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        iteratorDemo();
    }

    /**
     * Có hệ thống mới và cũ (hoặc tương tự các bài toán khác cần convert từ A -> B)
     * Để muốn thằng new chạy code của old trước r mới đến code mới của nó.
     * THì dùng 1 class adapter chứa 1 obj old là xong.
     *
     * Nếu từ nay muốn dùng New riêng thì gọi NewSystem. CÒn nếu muốn dùng new có kèm theo old thì dùng AdapterNew
     *
     * Về sau, nếu giả sử có thêm new2System nữa mà cũng muốn connect vào old thì chỉ cần tạo 1 adapter2 tương tự.
     *
     *Tương tự nếu sau này có thêm subclass của old, thì thằng code ở đây không phải thay đổi gì mà chỉ cần gọi
     * new AdapterNewSystem(new Old2SystemImpl()); do bản chất nó nhận vào Old interface, mà bọn kia lại kế thừa từ interface đó.
     */
    private static void adapterDpDemo() {
        // New system convert and connect to old system
        NewSystem newSystemByOldWay = new AdapterNewSystem(new OldSystemImpl());
        newSystemByOldWay.doSomethingsNews();
    }

    /**
     * Với cách này, chỉ có thể duyệt list thông qua MyIterator.
     * Và cũng chỉ có MyIterator mới có quyền truy cập list trong Box do nó là innerclass và list kia lại là private
     *
     * Bh bắt buộc duyệt qua MyIterator, mà k truy cập trực tiếp để có thể làm ảnh huongr đến
     * data
     *
     * Với cách này có thể có nhiều client duyệt = iterator cùng 1 lúc
     */
    private static void iteratorDemo() {
        CollectionBox integerBox = new CollectionBox();
        for (int i = 9; i > 0; --i) {
            integerBox.add(i);
        }
        // getData() has been removed.
        // Client has to use Iterator.
        CollectionBox.MyIterator firstItr = integerBox.getIterator();
        CollectionBox.MyIterator secondItr = integerBox.getIterator();
        for (firstItr.first(); !firstItr.isDone(); firstItr.next()) {
            System.out.print(firstItr.currentValue() + "  ");
        }
        System.out.println();
        // Two simultaneous iterations
        for (firstItr.first(), secondItr.first(); !firstItr.isDone(); firstItr.next(), secondItr.next()) {
            System.out.print(firstItr.currentValue() + " " + secondItr.currentValue() + "  ");
        }
    }

    /**
     * Sau có thêm color nào cũng chỉ cần impl Color interface là có thể pass vào shape mà k cần change code shape
     */
    private static void bridgeDemo() {
        Shape tri = new Triangle(new RedColor());
        tri.applyColor();

        Shape pent = new Pentagon(new GreenColor());
        pent.applyColor();
    }

    /**
     * Với decorator, dễ dàng thêm các tính năng khác nhau cho obj. Ví dụ 1 ô tô vừa là xe sang, vừa là xe thể thao.
     */
    private static void decoratorDemo() {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }

    private static void proxyDemo() {
        CommandExecutor executor = new CommandExecutorProxy("Pankaj", "wrong_pwd");
        try {
            executor.runCommand("ls -ltr");
            executor.runCommand(" rm -rf abc.pdf");
        } catch (Exception e) {
            System.out.println("Exception Message::"+e.getMessage());
        }
    }

    private static void chainDemo() {
        // initialize the chain
        DispenseChain c1 = new Dollar50Dispenser();
        DispenseChain c2 = new Dollar20Dispenser();
        DispenseChain c3 = new Dollar10Dispenser();

        // set the chain of responsibility
        c1.setNextChain(c2);
        c2.setNextChain(c3);

        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            // process the request
            c1.dispense(new Currency(amount));
        }
    }
}
