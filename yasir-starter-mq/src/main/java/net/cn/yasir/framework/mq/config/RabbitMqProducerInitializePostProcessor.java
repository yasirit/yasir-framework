package net.cn.yasir.framework.mq.config;

import net.cn.yasir.framework.mq.annotation.Producer;
import net.cn.yasir.framework.mq.annotation.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMq容器初始化
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/4/30
 */
public class RabbitMqProducerInitializePostProcessor implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware, PriorityOrdered {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducerInitializePostProcessor.class);
    private BeanDefinitionRegistry beanDefinitionRegistry;
    private ResourceLoader resourceLoader;
    private ResourcePatternResolver resolver;
    private MetadataReaderFactory metadataReaderFactory;
    private ConfigurableListableBeanFactory beanFactory;
    private Map<Class, Map<Method, ProducerInfo>> producerInfos = new HashMap<>(16);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        this.beanFactory = configurableListableBeanFactory;
        this.beanDefinitionRegistry = (BeanDefinitionRegistry) this.beanFactory;

        //扫描客户端下的class，读取注解
        doScan("net/cn");
        //注册容器工厂
        doRegister();
    }

    private void doRegister() {
        this.producerInfos.entrySet().forEach(entry -> {
            Class targetClass = entry.getKey();
            Map<Method, ProducerInfo> producerInfoMethods = entry.getValue();
//            String beanName = targetClass.getName();
            String beanClassName = targetClass.getName();
            String beanName = "$" + targetClass.getSimpleName();
            BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(ProducerFactoryBean.class);
            definition.addPropertyValue("type", beanClassName);
            definition.addPropertyValue("producerInfoMethods", producerInfoMethods);
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            BeanDefinition beanDefinition = definition.getBeanDefinition();
            beanDefinition.setPrimary(false);
            BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, beanName);
            BeanDefinitionReaderUtils.registerBeanDefinition(holder, this.beanDefinitionRegistry);
        });
    }

    /**
     * 扫描class
     * @param path
     */
    private void doScan(String path) {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                .concat(ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(path))
                        .concat("/**/*.class"));
        Resource[] resources = null;
        MetadataReader metadataReader = null;
        try {
            resources = resolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    if (metadataReader.getClassMetadata().isInterface()) {
                        Class c = Class.forName(metadataReader.getClassMetadata().getClassName());
                        if(c.isAnnotationPresent(Producer.class)) {
                            Method[] methods = c.getDeclaredMethods();
                            Map<Method, ProducerInfo> producerInfoMethods = new HashMap<>(16);
                            for(Method method : methods) {
                                if(method.isAnnotationPresent(Router.class)) {
                                    Router router = AnnotationUtils.findAnnotation(method, Router.class);
                                    ProducerInfo producerInfo = new ProducerInfo();
                                    producerInfo.setExchange(router.exchange());
                                    producerInfo.setKey(router.key());
                                    producerInfoMethods.put(method, producerInfo);
                                }
                            }
                            producerInfos.put(c, producerInfoMethods);
                        }
                    }
                }
            }
        } catch (IOException e1) {
            LOGGER.error(e1.getMessage());
        } catch (ClassNotFoundException e2) {
            LOGGER.error(e2.getMessage());
        }


    }


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.resolver = ResourcePatternUtils.getResourcePatternResolver(this.resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(this.resourceLoader);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
