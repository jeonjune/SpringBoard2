package com.itwillbs.aop;

public class Calculator {

	public void add(int a, int b) {
//		System.out.println( " 로그 출력 (주기능) " );
		System.out.println(a + " + " + b + " = " + ( a + b ));
//		System.out.println( " 로그 출력 (주기능) " );
	}
	public void sub(int a, int b) {
		System.out.println(a + " - " + b + " = " + ( a - b ));
	}
	public void mul(int a, int b) {
		System.out.println( a + " * " + b + " = " + ( a * b ));
	}
	public void div(int a, int b) {
		System.out.println( a + " / " + b + " = " + ( a / b ));
	}
	
}
