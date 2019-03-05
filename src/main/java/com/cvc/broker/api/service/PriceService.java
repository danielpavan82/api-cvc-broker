package com.cvc.broker.api.service;

import java.util.List;

import com.cvc.broker.api.domain.Hotel;
import com.cvc.broker.api.domain.InputRest;

public interface PriceService {
	
	List<Hotel> getPricesCity(
			InputRest inputRest
	);
	
	Hotel getPricesHotel(
			InputRest inputRest
	);
	
}
