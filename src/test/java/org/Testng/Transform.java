package org.Testng;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transform implements IAnnotationTransformer{

	public void transform(ITestAnnotation a, Class testClass, Constructor testConstructor, Method testMethod) {
		
		a.setRetryAnalyzer(Retry.class);
		
		
	}

}