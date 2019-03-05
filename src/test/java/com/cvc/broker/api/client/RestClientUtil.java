package com.cvc.broker.api.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cvc.broker.api.domain.Hotel;

public class RestClientUtil {

	public void getPricesCity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest/v1/prices/city?checkin={checkin}&checkout={checkout}&cityCode={cityCode}&qtdeAdultos={qtdeAdultos}&qtdeCriancas={qtdeCriancas}";
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		try {
			
			ResponseEntity<Hotel[]> responseEntity = restTemplate.exchange(url,
					HttpMethod.GET,
					requestEntity,
					Hotel[].class,
					"02/03/2019",
					"03/03/2019",
					1032,
					2,
					1
			);
			
			Hotel[] list = responseEntity.getBody();
			System.out.println("=================getPricesCity==========================");      
			System.out.println(list.length);
			
		}catch (HttpClientErrorException e) {
			System.out.println("================= ERRO: getPricesCity==========================");
			System.out.println(e.getResponseBodyAsString());
		}
	}
	
	
	public void getPricesHotel() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/rest/v1/prices/hotel?checkin={checkin}&checkout={checkout}&hotelCode={hotelCode}&qtdeAdultos={qtdeAdultos}&qtdeCriancas={qtdeCriancas}";
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		
		try {
			
			ResponseEntity<Hotel> responseEntity = restTemplate.exchange(url,
					HttpMethod.GET,
					requestEntity,
					Hotel.class,
					"02/03/2019",
					"03/03/2019",
					1,
					2,
					1
			);
			
			Hotel obj = responseEntity.getBody();
			System.out.println("=================getPricesHotel==========================");      
			System.out.println(obj);
			
		}catch (HttpClientErrorException e) {
			System.out.println("================= ERRO: getPricesHotel==========================");
			System.out.println(e.getResponseBodyAsString());
		}
	}
	
	
	public static void main(String args[]) {
		RestClientUtil util = new RestClientUtil();
		util.getPricesCity();
		util.getPricesHotel();
	}
	
}
