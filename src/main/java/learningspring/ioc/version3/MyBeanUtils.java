package learningspring.ioc.version3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-11 上午1:24
 **/
public class MyBeanUtils {
    public static void populate(Object object, Map<String, String[]> map) throws Exception{
        Field[] flds = object.getClass().getDeclaredFields();
        for (Field fld : flds){
            // 获取属性名
            String fldName = fld.getName();
            // 根据属性名去map中找对应的值
            String[] values = map.get(fldName);
            for (String value : values){
                // 获取setter方法
                String methodName = "set" + fldName.substring(0,1).toUpperCase()+fldName.substring(1);
                // 根据方法名和参数的属性类型，获得Method对象
                Method method = object.getClass().getDeclaredMethod(methodName,fld.getType());
                // 调用该method对象所代表的方法
                method.invoke(object, value);
            }
        }
    }
}
