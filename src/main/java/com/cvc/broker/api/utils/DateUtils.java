package com.cvc.broker.api.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateUtils {
	
	
	//Metodo para converter uma data do tipo String para LocalDate
	public static LocalDate convertDate(String strDate) {
		String dateFormat = "dd/MM/yyyy";
	    
	    try {
	        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(dateFormat));
	    } catch (DateTimeParseException e) {
	       return null;
	    } 
	}
	
	
	//Metodo para calcular a qtde de dias entre duas datas
	public static int calcularDias(LocalDate dataInicio, LocalDate dataFim) {
		return (int) ChronoUnit.DAYS.between(dataInicio, dataFim);
	}
	
	
}
