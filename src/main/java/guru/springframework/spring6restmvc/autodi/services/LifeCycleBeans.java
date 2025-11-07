package guru.springframework.spring6restmvc.autodi.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class LifeCycleBeans implements InitializingBean, DisposableBean,
        BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor {

    private String javaVersion;

    public LifeCycleBeans() {
        System.out.println("-----> Inside LifeCycleBeans Constructor");
    }

    @Value("${java.specification.version}")
    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
        System.out.println("-----> Java Version set to: " + this.javaVersion);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("-----> Bean Name is: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("-----> Bean Factory has been set.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("-----> Application Context has been set.");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("-----> @PostConstruct method called.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("-----> afterPropertiesSet populate properties the LifeCycleBean has its property set.");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("-----> @PreDestroy method called.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("-----> destroy method called.");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----> BeanPostProcessor before initialization of bean: " + beanName);

        if (bean instanceof LifeCycleBeans) {
            LifeCycleBeans myBean = (LifeCycleBeans) bean;
            System.out.println("-----> Calling beforeInit method for bean: " + beanName);
            myBean.beforeInit();
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("-----> BeanPostProcessor after initialization of bean: " + beanName);

        if (bean instanceof LifeCycleBeans) {
            LifeCycleBeans myBean = (LifeCycleBeans) bean;
            System.out.println("-----> Calling afterInit method for bean: " + beanName);
            myBean.afterInit();
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    public void beforeInit() {
        System.out.println("-----> Before Init - Called by Bean Post Processor");
    }

    public void afterInit() {
        System.out.println("-----> After Init - Called by Bean Post Processor");
    }
}
