package morning.cat.protocol.serializer;

/**
 * @describe: 序列化方式
 * @author: morningcat.zhang
 * @date: 2019/9/26 2:33 PM
 */
public interface SerializerAlgorithm {

    byte JDK = 0;
    /**
     * json 序列化标识
     */
    byte FASTJSON = 1;

    byte GSON = 2;

    byte JACKSON = 3;

    byte KRYO = 4;

    byte FST = 5;

    byte PROTOSTUFF = 6;

    byte HESSIAN = 7;


}
