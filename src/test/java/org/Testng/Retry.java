package org.Testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int i =1;
	int j =2;
	
	public boolean retry(ITestResult result) {
		
		if(j >i){
			i++;
			return true;
		}
		
		System.out.println(result.getName());
		
		return false;
		
	}
	

}
