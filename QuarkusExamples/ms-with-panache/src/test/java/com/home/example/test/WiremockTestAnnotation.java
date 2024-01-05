package com.home.example.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.quarkus.test.common.QuarkusTestResource;


@QuarkusTestResource(value = WiremockResourceConfigurable.class, restrictToAnnotatedClass = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WiremockTestAnnotation {
	 String port() default "57005";
}
