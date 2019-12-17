package com.company.chain;

/**
 * Class này sẽ có logic check tiền chia 50.
 * Khi impl, nó sẽ đc trỏ đến thằng XuLy20 để chạy khi chính nó k xử lý đc.
 */
public class Dollar50Dispenser implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    /**
     * Nếu chia 50 còn dư thì pass số dư đó cho 20 xử lý
     * Nếu <50 thì pass luôn cho 20.
     * @param cur
     */
    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 50){
            int num = cur.getAmount()/50;
            int remainder = cur.getAmount() % 50;
            System.out.println("Dispensing "+num+" 50$ note");
            if(remainder !=0) this.chain.dispense(new Currency(remainder));
        }else{
            this.chain.dispense(cur);
        }
    }
}
