package learningspring.ioc.version3;


import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-11 上午1:07
 **/
public class ClassPathXmlApplicationContext implements BeanFactory {

    /**
     * 获取配置文件中的map信息
     */
    private Map<String, Bean> map;

    /**
     * 作为IOC容器使用，放置管理对象
     */
    private Map<String, Object> context = new HashMap<>();

    public ClassPathXmlApplicationContext(String path) {
        // 读取配置文件得到需要初始化的Bean信息
        map = ConfigManager.getConfig(path);
        // 遍历配置，初始化Bean
        for (Map.Entry<String, Bean> entry : map.entrySet()){
            String beanId = entry.getKey();
            Bean bean = entry.getValue();

            Object existBean = context.get(beanId);
            if (existBean == null && "singleton".equals(bean.getBeanScope())){
                Object beanObj = createBean(bean);
                context.put(beanId, beanObj);
            }
        }
    }

    private Object createBean(Bean bean) {
        Class clazz = null;
        try {
            clazz = Class.forName(bean.getBeadClassName());
        } catch (ClassNotFoundException e) {
            System.err.println("没有找到类" + bean.getBeadClassName());
        }
        Object beanObj = null;
        try {
            beanObj = clazz.newInstance();
        } catch (IllegalAccessException e) {
            System.err.println("没有提供无参构造器");
        } catch (InstantiationException e1) {
            System.err.println("实例化异常");
        }

        if (bean.getBeanProperties() != null) {
            for (Property pro : bean.getBeanProperties()) {
                String name = pro.getName();
                String value = pro.getValue();
                String ref = pro.getRef();

                if (value != null) {
                    Map<String, String[]> paramMap = new HashMap<>();
                    paramMap.put(name, new String[]{value});
                    try {
                        // MyBeanUtils.populate(beanObj, parmMap);
                        // 标记此处,后续使用自己的BeanUtils
                        BeanUtils.populate(beanObj, paramMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (ref != null) {
                    // 根据属性名获得一个注入属性对应的set方法
                    // Method setMethod = BeanUtil.getWriteMethod(beanObj,
                    // name);

                    // 看一看当前IOC容器中是否已存在该bean,有的话直接设置没有的话使用递归,创建该bean对象
                    Object existBean = context.get(pro.getRef());
                    if (existBean == null) {
                        // 递归的创建一个bean
                        existBean = createBean(map.get(pro.getRef()));
                        // 放置到context容器中
                        // 只有当scope="singleton"时才往容器中放
                        if (map.get(pro.getRef()).getBeanScope()
                                .equals("singleton")) {
                            context.put(pro.getRef(), existBean);
                        }
                    }
                    try {
                        // setMethod.invoke(beanObj, existBean);
                        // 通过BeanUtils为beanObj设置属性
                        // 标记此处,后续使用自己的BeanUtils
                        BeanUtils.setProperty(beanObj, name,existBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("您的bean的属性" + name
                                + "没有对应的set方法");
                    }
                }
            }
        }

        return beanObj;
    }

    @Override
    public Object getBean(String id) {
        Object bean = context.get(id);
        if (bean == null){
            bean = createBean(map.get(id));
        }
        return bean;
    }
}

