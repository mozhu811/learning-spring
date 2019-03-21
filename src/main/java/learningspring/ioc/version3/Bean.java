package learningspring.ioc.version3;

import java.util.ArrayList;
import java.util.List;

/**
 * 佛祖保佑，此代码无bug，
 * 就算有，也一眼看出。
 *
 * @author ray
 * @version 1.0
 * @date 2018-06-11 上午12:51
 **/
public class Bean {

    private String beanId;
    private String beadClassName;
    private String beanScope = "singleton";
    private List<Property> beanProperties = new ArrayList<>();

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getBeadClassName() {
        return beadClassName;
    }

    public void setBeadClassName(String beadClassName) {
        this.beadClassName = beadClassName;
    }

    public String getBeanScope() {
        return beanScope;
    }

    public void setBeanScope(String beanScope) {
        this.beanScope = beanScope;
    }

    public List<Property> getBeanProperties() {
        return beanProperties;
    }

    public void setBeanProperties(List<Property> beanProperties) {
        this.beanProperties = beanProperties;
    }
}
