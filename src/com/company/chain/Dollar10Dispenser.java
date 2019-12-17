package com.company.chain;

/**
 * Thằng này là cuối chuỗi, nên ở chỗ impl sẽ  k pass chain khác cho nó.
 * Đây chỉ là ví dụ. CÒn thực tế phải tính cả những case data vào đên đây r mà k xử lý đc (cũng k còn thằng khác xử lý
 * hộ nữa vì đã cuối chain)
 */
public class Dollar10Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 10){
            int num = cur.getAmount()/10;
            int remainder = cur.getAmount() % 10;
            System.out.println("Dispensing "+num+" 10$ note");
            if(remainder !=0) this.chain.dispense(new Currency(remainder));
        }else{
            this.chain.dispense(cur);
        }
    }

}
