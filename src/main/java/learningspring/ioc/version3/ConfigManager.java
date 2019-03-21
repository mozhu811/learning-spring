package learningspring.ioc.version3;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-11 上午12:20
 **/
public class ConfigManager {

    private static HashMap<String, Bean> map = new HashMap<>();

    /**
     * 读取配置文件并返回读取结果
     * @return HashMap<String, Bean>
     */
    public static HashMap<String, Bean> getConfig(String path) {
        /*
         * dom4j实现
         *  1.创建解析器
         *  2.加载配置文件,得到document对象
         *  3.定义xpath表达式,取出所有Bean元素
         *  4.对Bean元素继续遍历
         *      4.1将Bean元素的name/class属性封装到bean类属性中
         *      4.2获得bean下的所有property子元素
         *      4.3将属性name/value/ref分装到类Property类中
         *  5.将property对象封装到bean对象中
         *  6.将bean对象封装到Map集合中,返回map
         */
        // 创建解析器
        SAXReader reader = new SAXReader();
        Document document = null;
        try{
            // 加载配置文件，得到document对象
            document = reader.read(new File(path));
        } catch (DocumentException e){
            System.err.println("请检查xml文件是否正确");
        }

        // 定义xpath表达式，取出所有bean元素
        String xpath = "//bean";

        // 对bean元素进行遍历
        List<Element> list = document.selectNodes(xpath);
        if (list!=null){
            // 将Bean元素的id/class属性封装到bean类属性中
            for (Element bean : list){
                Bean b = new Bean();
                String beanId = bean.attributeValue("id");
                String beanClassName = bean.attributeValue("class");
                String beanScope =bean.attributeValue("scope");
                b.setBeanId(beanId);
                b.setBeadClassName(beanClassName);
                if (beanScope != null){
                    b.setBeanScope(beanScope);
                }

                List<Element> pros = bean.elements("property");
                // 将属性name/value/ref分装到类Property类中
                if (pros != null){
                    for (Element pro : pros){
                        Property property = new Property();
                        String pName=pro.attributeValue("name");
                        String pValue=pro.attributeValue("value");
                        String pRef=pro.attributeValue("ref");
                        property.setName(pName);
                        property.setRef(pRef);
                        property.setValue(pValue);
                        // 将property对象封装到bean对象中
                        b.getBeanProperties().add(property);
                    }
                }

                // 将bean对象封装到map中
                map.put(beanId, b);
            }
        }
        return map;
    }

}
