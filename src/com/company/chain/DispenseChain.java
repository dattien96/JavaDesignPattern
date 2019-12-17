package com.company.chain;

/**
 * Mỗi bộ xử lý 50-20-10 đều impl cái này.
 * Mỗi thằng sẽ có 1 fun xử lý. Và ref đến 1 bộ xử lý khác sẽ chạy khi nó k xử lý được
 */
public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}
