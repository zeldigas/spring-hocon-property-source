package com.github.zeldigas.spring.env;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A utility meta annotation to allow for easier use of HOCON files as property sources;
 * the annotation allows consumers to use @{@link HoconPropertySource} with .conf files in the same way
 * they would use {@link PropertySource} with .properties files by extending the {@link PropertySource}
 * annotation with the default factory class of {@link HoconPropertySourceFactory}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@PropertySource(value = "", factory = HoconPropertySourceFactory.class)
public @interface HoconPropertySource {

    /**
     * @see PropertySource#name()
     */
    @AliasFor(annotation = PropertySource.class)
    String name() default "";

    /**
     * @see PropertySource#value()
     */
    @AliasFor(annotation = PropertySource.class)
    String[] value();

    /**
     * @see PropertySource#ignoreResourceNotFound()
     */
    @AliasFor(annotation = PropertySource.class)
    boolean ignoreResourceNotFound() default false;

    /**
     * @see PropertySource#encoding()
     */
    @AliasFor(annotation = PropertySource.class)
    String encoding() default "";

}