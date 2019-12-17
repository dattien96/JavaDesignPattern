package com.company.proxy;

import java.io.IOException;

/**
 * final để class này k bị kế thừa.
 * Vì nếu có subclass, nó vẫn sẽ truy cập được constructor protec
 * Mục đích của ta là chặn k cho khởi tạo trực tiếp obj này, bắt buộc dùng qua proxy
 */
final public class CommandExecutorImpl implements CommandExecutor {

    /**
     * Để class này là protect.
     * Chỉ có thể truy cập nó trong cùng 1 package (class proxy sẽ dùng)
     */
    protected CommandExecutorImpl() {

    }

    @Override
    public void runCommand(String cmd) throws IOException {
        //some heavy implementation
        Runtime.getRuntime().exec(cmd);
        System.out.println("'" + cmd + "' command executed.");
    }

}