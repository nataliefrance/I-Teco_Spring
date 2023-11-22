package ru.shipova.ITeco_Spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.shipova.ITeco_Spring.model.annotation.InjectRandom;

import java.lang.reflect.Field;

//добавляет в наш объект Logger, альтернатива:
//private static final Logger LOGGER = LoggerFactory.getLogger(InjectRandomBeanPostProcessor.class);
@Slf4j
@Component
public class InjectRandomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //log.info();
        System.out.println("beanName: " + beanName + "; beanClass: " + bean.getClass().getSimpleName());

        //Для каждого bean получаем все его Field и проходим по ним циклом
        for (Field declaredField : bean.getClass().getDeclaredFields()) {
            //надо найти те Field, которые аннотированы аннотацией @InjectRandom
            if (declaredField.isAnnotationPresent(InjectRandom.class)) {
                //если аннотирован, то надо выполнит inject в этот Field рандомного значения
                declaredField.setAccessible(true);//этот флаг определяет нужно ли генерировать искл-е при попытке изменить наш Field
                InjectRandom annotation = declaredField.getAnnotation(InjectRandom.class); //получаем нашу аннотацию
                int random = getRandom(annotation.min(), annotation.max());
                System.out.println("Set random value in " + declaredField.getName() + "; Value: " + random);
                ReflectionUtils.setField(declaredField, bean, random);//Field, объект, куда мы устанвалиаем значение и само значение
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private static int getRandom(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
