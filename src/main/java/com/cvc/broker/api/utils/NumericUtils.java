package com.cvc.broker.api.utils;

public class NumericUtils {
	
	//Metodo para validar de uma string é numerica
	public static boolean isNumeric(String strNum) {
	    return strNum.matches("-?\\d+(\\d+)?");
	}
	
}
