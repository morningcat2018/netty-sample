package morning.cat.protocol.serializer;

import java.io.IOException;

/**
 * @describe: 序列化接口
 * @author: morningcat.zhang
 * @date: 2019/9/26 2:32 PM
 */
public interface Serializer {

    Serializer DEFAULT = new FastJsonSerializer();

    /**
     * 获取具体的序列化算法标识
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object) throws IOException;

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException;
}
