package com.trascender.framework.util.anotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={java.lang.annotation.ElementType.TYPE,java.lang.annotation.ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface NoAuditable {
	
}
