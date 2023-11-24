package ru.shipova.ITeco_Spring.config;

import lombok.SneakyThrows;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import ru.shipova.ITeco_Spring.model.annotation.EncryptResult;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.lang.reflect.Method;
import java.util.Base64;

@Component
public class EncryptResultBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //Если мы пишем постпроцеесор, который не настраивает бин, а меняет его логику,
        //то это пишется в afterInitialisation, чтобы наш прокси поал в контекст
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Encrypt process for bean: " + beanName);
        //надо проверить все методы всех бинов и найти метод, который аннотирован нужной нам аннотацией
        for (Method method : bean.getClass().getMethods()) {
            //проверяем содержит ли bean методы с аннотацией @EncryptResult
            if (method.isAnnotationPresent(EncryptResult.class)) {
                System.out.println("Bean is need proxy");
                ProxyFactory proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvice((MethodInterceptor) invocation -> { //перехватчик метода
                    System.out.println("Before call method in bean: " + beanName);

                    Object proceed = invocation.proceed();
                    //Получили все методы класса ObjectValue
                    for (Method declaredMethod : invocation.getThis().getClass().getDeclaredMethods()) {
                        //теперь на методах ObjectValue проверяем аннтоацию @EncryptResult
                        if ((invocation.getMethod().getName().equals(declaredMethod.getName())
                                && AnnotationUtils.findAnnotation(declaredMethod, EncryptResult.class) != null)) {
                            System.out.println("Call " + invocation.getMethod().getName() + ", result of which need to be encrypted");
                            return encrypt(proceed);
                        }
                    }
                    //проверяем, аннотирован ли вызванный метод аннотацией @EncryptResult
//                    if (invocation.getMethod().isAnnotationPresent(EncryptResult.class)) {
//                        System.out.println("Call " + invocation.getMethod().getName() + ", result of which need to be encrypted");
//                        return encrypt(proceed);
//                    }
                    return proceed;
                });
                //на этапе если наш bean содержит аннотацию @EncryptedResult
                return proxyFactory.getProxy();
            }
        }
        return bean;
    }

    @SneakyThrows//на этапе компиляции кода помещает его в try-catch, что позволяет не обрабатывать исключения
    private Object encrypt(Object proceed) {
        //базовый код для шифрования
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        cipher.init(Cipher.ENCRYPT_MODE, keyGenerator.generateKey());
        //используем чтобы сериализовать наш объект
        byte[] bytes = cipher.doFinal(SerializationUtils.serialize(proceed));
        return Base64.getEncoder().encodeToString(bytes);
    }
}