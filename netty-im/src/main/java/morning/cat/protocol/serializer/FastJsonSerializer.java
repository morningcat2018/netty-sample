package morning.cat.protocol.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @describe: FastJson 序列化
 * @author: morningcat.zhang
 * @date: 2019/9/26 2:34 PM
 */
public class FastJsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.FASTJSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
