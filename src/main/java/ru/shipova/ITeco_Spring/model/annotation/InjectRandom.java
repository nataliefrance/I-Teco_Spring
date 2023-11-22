package ru.shipova.ITeco_Spring.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//область видимости аннотации, её жизненный цикл
@Retention(RetentionPolicy.RUNTIME)
//то, к чему наша аннотация может применяться
@Target(ElementType.FIELD) //FIELD - прменение к параметрам
public @interface InjectRandom {
    int min() default 0;
    int max() default 10;
}
