package com.cvc.broker.api.service.test;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cvc.broker.api.domain.Hotel;
import com.cvc.broker.api.domain.InputRest;
import com.cvc.broker.api.exception.RecordNotFound;
import com.cvc.broker.api.exception.ValidationException;
import com.cvc.broker.api.service.PriceService;
import com.cvc.broker.api.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {
	
	@Autowired
	PriceService service;
	
	//Variaveis com valores default para os testes
	int cityCode = 1032;
	int hotelCode = 1;
	LocalDate checkin = DateUtils.convertDate("02/03/2019");
	LocalDate checkout = DateUtils.convertDate("05/03/2019");
	int qtdeAdultos = 2;
	int qtdeCriancas = 1;
	
	
	@Test
	public void getPricesCityTest() {
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		List<Hotel> list = service.getPricesCity(inputRest);
		
		System.out.println("getPricesCityTest -> list.size() =  " + list.size());
		
		Assert.assertTrue(list.size() > 0);
		
	}
	
	
	@Test(expected = RecordNotFound.class)
	public void getPricesCityRecordNotFoundTest() {
		
		//Seta codigo da cidade para nao encontrar
		cityCode = 10321;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		List<Hotel> list = service.getPricesCity(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesCityValidationIdTest() {
		
		cityCode = 0;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		List<Hotel> list = service.getPricesCity(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesCityValidationCheckinTest() {
		
		checkin = null;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		List<Hotel> list = service.getPricesCity(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesCityValidationCheckoutTest() {
		
		checkout = null;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		List<Hotel> list = service.getPricesCity(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesCityValidationQtdeAdultoTest() {
		
		qtdeAdultos = 0;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		List<Hotel> list = service.getPricesCity(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesCityValidationQtdeCriancaTest() {
		
		qtdeCriancas = -1;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		List<Hotel> list = service.getPricesCity(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesCityValidationQtdeDiasTest() {
		
		checkin = DateUtils.convertDate("05/03/2019");;
		checkout = DateUtils.convertDate("02/03/2019");
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(cityCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		List<Hotel> list = service.getPricesCity(inputRest);
		
	}
	
	
	@Test
	public void getPricesHotelTest() {
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		Hotel obj = service.getPricesHotel(inputRest);
		
		System.out.println("getPricesHotelTest -> "+ obj);
		
		Assert.assertNotNull(obj.getId());
		
	}
	
	
	@Test(expected = RecordNotFound.class)
	public void getPricesHotelRecordNotFoundTest() {
		
		hotelCode = 547675;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		Hotel obj = service.getPricesHotel(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesHotelValidationIdTest() {
		
		hotelCode = 0;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		Hotel obj = service.getPricesHotel(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesHotelValidationCheckinTest() {
		
		checkin = null;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		Hotel obj = service.getPricesHotel(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesHotelValidationCheckoutTest() {
		
		checkout = null;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		Hotel obj = service.getPricesHotel(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesHotelValidationQtdeAdultoTest() {
		
		qtdeAdultos = 0;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		Hotel obj = service.getPricesHotel(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesHotelValidationQtdeCriancaTest() {
		
		qtdeCriancas = -1;
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		Hotel obj = service.getPricesHotel(inputRest);
		
	}
	
	
	@Test(expected = ValidationException.class)
	public void getPricesHotelValidationQtdeDiasTest() {
		
		checkin = DateUtils.convertDate("05/03/2019");;
		checkout = DateUtils.convertDate("02/03/2019");
		
		//Seta o objeto de entrada
		InputRest inputRest = new InputRest();
		inputRest.setId(hotelCode);
		inputRest.setCheckIn(checkin);
		inputRest.setCheckOut(checkout);
		inputRest.setQtdeAdultos(qtdeAdultos);
		inputRest.setQtdeCriancas(qtdeCriancas);
		
		@SuppressWarnings("unused")
		Hotel obj = service.getPricesHotel(inputRest);
		
	}
	
	
}
