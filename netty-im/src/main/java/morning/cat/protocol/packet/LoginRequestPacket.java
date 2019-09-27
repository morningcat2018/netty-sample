package morning.cat.protocol.packet;

import lombok.Data;

import static morning.cat.protocol.command.Command.LOGIN_REQUEST;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/9/26 2:31 PM
 */
@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
