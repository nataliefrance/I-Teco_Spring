package ru.shipova.ITeco_Spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Slf4j //Добавляет логирование в код
@Component
//Создание классов, реализующих BeanFactoryPostProcessor, позволит повлиять на то, каким
//будет бин еще до его создания
public class PrintBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // Получение имен BeanDefinition всех бинов, объявленных пользователем
        // и перебор массива для получения доступа к каждому имени
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            // Получение BeanDefinition по имени
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            System.out.println(beanDefinitionName);
            // Вывод информации о BeanDefinition
            System.out.println(beanDefinition.toString());
        }
    }
}
