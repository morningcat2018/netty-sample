package morning.cat.netty4.server.util;

import javax.naming.Context;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 5:21 PM
 * @Version 1.0
 */
public class ClassUtils {
    public static List<Class> getAllClass() {
        String url = null;
        try {
            url = URLDecoder.decode(Context.class.getResource("/").getPath(), "UTF-8");
            File file = new File(url);
            List<Class> classes = getAllClass(url, file);
            //System.out.println(classes);
            return classes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static List<Class> getAllClass(String url, File file) {
        List<Class> ret = new ArrayList<>();
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (File i : list) {
                List<Class> j = getAllClass(url, i);
                ret.addAll(j);
            }
        } else {
            String classFilePath = file.getAbsolutePath();
            if (classFilePath.contains(".class")) {
                try {
                    String classFile = classFilePath.replace(url, "").replace(".class", "").replace("/", ".");
                    ret.add(Class.forName(classFile));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
