package morning.cat.netty4.server.dispatcher;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import morning.cat.netty4.server.annotation.NettyMapper;
import morning.cat.netty4.server.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 3:41 PM
 * @Version 1.0
 */
public class DefaultDispatcher implements Dispatcher {

    private static Map<String, Method> mappers;
    private static Map<String, Object> mappersObject;

    static {
        mappers = new HashMap<>();
        mappersObject = new HashMap<>();
        List<Class> classes = ClassUtils.getAllClass();
        for (Class clazz : classes) {
            if (clazz.isInterface()) {
                continue;
            }

            String classMapper = null;
            boolean hasMapper = false;
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof NettyMapper) {
                    hasMapper = true;
                    classMapper = ((NettyMapper) annotation).value();
                }
            }

            if (hasMapper) {
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    Annotation[] methodAnnotations = method.getAnnotations();
                    for (Annotation annotation : methodAnnotations) {
                        if (annotation instanceof NettyMapper) {
                            String key = classMapper + ((NettyMapper) annotation).value();
                            mappers.put(key, method);
                            try {
                                mappersObject.put(key, clazz.newInstance());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public HttpResponse dispatcher(HttpRequest httpRequest) {

        String uri = httpRequest.uri();
        String[] paths = uri.split("\\?");
        Method method = mappers.getOrDefault(paths[0], null);
        if (method != null) {
            try {
                String[] pathParas = paths[1].split("&");
                String[] ps = new String[pathParas.length];
                for (int i = 0; i < pathParas.length; i++) {
                    ps[i] = pathParas[i].substring(pathParas[i].indexOf("=") + 1);
                }
                Object object = method.invoke(mappersObject.get(paths[0]), ps);
                if (object instanceof String) {
                    return getHttpResponse((String) object);
                }
                return getHttpResponse(new Gson().toJson(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        HttpResponse response = getHttpResponse("出现异常");
        return response;
    }

    private HttpResponse getHttpResponse(String msg) {
        ByteBuf context = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, context);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, context.readableBytes());
        return response;
    }

}
