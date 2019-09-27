package morning.cat.protocol.packet;

import lombok.Data;

/**
 * @describe: 通信过程中 Java 对象的抽象类
 * @author: morningcat.zhang
 * @date: 2019/9/26 2:25 PM
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

/**
 * 指令
 */
 public abstract Byte getCommand();
}
