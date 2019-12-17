package com.company.chain;

/**
 * Đứnh thứ 2 trong chain: 50-20-10
 * Chỉ chạy khi thằng 50 k xử lý đc và pass cho nó
 * Nếu thằng này cũng k xử lý đc thì pass cho 10
 */
public class Dollar20Dispenser implements DispenseChain{

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    /**
     * Nếu < 20 thì pass luôn cho 10
     * Nếu chia cho 20 dư khác 0 -> pass phần dư cho 10 xử lý
     * @param cur
     */
    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 20){
            int num = cur.getAmount()/20;
            int remainder = cur.getAmount() % 20;
            System.out.println("Dispensing "+num+" 20$ note");
            if(remainder !=0) this.chain.dispense(new Currency(remainder));
        }else{
            this.chain.dispense(cur);
        }
    }

}