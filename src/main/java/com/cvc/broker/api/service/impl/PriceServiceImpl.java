package com.cvc.broker.api.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvc.broker.api.domain.Hotel;
import com.cvc.broker.api.domain.InputRest;
import com.cvc.broker.api.domain.PriceDetail;
import com.cvc.broker.api.domain.Room;
import com.cvc.broker.api.exception.RecordNotFound;
import com.cvc.broker.api.exception.ValidationException;
import com.cvc.broker.api.service.PriceService;
import com.cvc.broker.api.utils.DateUtils;
import com.cvc.broker.client.domain.HotelBroker;
import com.cvc.broker.client.service.BrokerService;

@Service
public class PriceServiceImpl implements PriceService{
	
	@Autowired
	private BrokerService brokerService;
	
	//Valor da comissao
	BigDecimal comissao = new BigDecimal("0.7");
	
	
	//Metodo para retornar o valor da viagem de todos os hoteis de uma determinada cidade 
	@Override
	public List<Hotel> getPricesCity(
			InputRest inputRest
		) {
		
		//Chama metodo para validar os parametros
		validar(inputRest);
		
		//Chama o servico para listar todos os hoteis de acordo com a cidade
		List<HotelBroker> lstHoteis = brokerService.getAllHotel(inputRest.getId());
		if(lstHoteis == null || lstHoteis.isEmpty()) {
			throw new RecordNotFound("Cidade não encontrada");
		}
		
		//Chama funcao para calcular os dias
		int qtdeDias = DateUtils.calcularDias(inputRest.getCheckIn(), inputRest.getCheckOut());
		
		//Lista que sera retornada pela API
		List<Hotel> lstRetorno = new ArrayList<Hotel>();
		
		lstHoteis.forEach(hotel -> {
			
			//Adiciona na lista do retorno o hotel calculado
			lstRetorno.add(montarHotelRetorno(hotel, qtdeDias, inputRest.getQtdeAdultos(), inputRest.getQtdeCriancas()));
			
		});
		
		return lstRetorno;
	}
	
	
	//Metodo para retornar o valor da viagem de um determinado hotel
	@Override
	public Hotel getPricesHotel(
			InputRest inputRest
			) {
		
		//Chama metodo para validar os parametros
		validar(inputRest);
		
		//Chama o servico para retorna o hotel pelo id
		HotelBroker hotelBroker = brokerService.getDetailsHotel(inputRest.getId());
		if(hotelBroker == null) {
			throw new RecordNotFound("Hotel não encontrado");
		}
		
		//Chama funcao para calcular os dias
		int qtdeDias = DateUtils.calcularDias(inputRest.getCheckIn(), inputRest.getCheckOut());
		
		return montarHotelRetorno(hotelBroker, qtdeDias, inputRest.getQtdeAdultos(), inputRest.getQtdeCriancas());
	}
	
	
	//Metodo para validar os parametros
	private void validar(
			InputRest inputRest
			) {
		if(inputRest == null) {
			throw new ValidationException("O objeto de entrada está nulo");
		}
		if(inputRest.getId() <= 0) {
			throw new ValidationException("O ID deve ser maior que zero");
		}
		if (inputRest.getCheckIn() == null) {
			throw new ValidationException("A Data Checkin é obrigatória");
		}
		if (inputRest.getCheckOut() == null) {
			throw new ValidationException("A Data Checkout é obrigatória");
		}
		if (inputRest.getQtdeAdultos() <= 0) {
			throw new ValidationException("A quantidade mínima para adulto é 1");
		}
		if (inputRest.getQtdeCriancas() < 0) {
			throw new ValidationException("A quantidade para criança não pode ser negativa");
		}
		if (DateUtils.calcularDias(inputRest.getCheckIn(), inputRest.getCheckOut()) <= 0) {
			throw new ValidationException("A Data do Checkin deve ser menor que a Data Checkout");
		}
	}
	
	
	//Metodo para calcular o valor da viagem
	private Hotel montarHotelRetorno(HotelBroker hotelBroker, int qtdeDias, int qtdeAdultos, int qtdeCriancas) {
		Hotel hotel = new Hotel();
		hotel.setId(hotelBroker.getId());
		hotel.setCityName(hotelBroker.getCityName());
		
		List<Room> lstRoom = new ArrayList<Room>();
		hotelBroker.getRooms().forEach(roomBroker -> {
			Room room = new Room();
				room.setRoomID(roomBroker.getRoomID());
				room.setCategoryName(roomBroker.getCategoryName());
				room.setTotalPrice(calcularTotalPrice(qtdeDias, roomBroker.getPrice().getAdult(), roomBroker.getPrice().getChild(), qtdeAdultos, qtdeCriancas));
				PriceDetail priceDetail = new PriceDetail();
					priceDetail.setPricePerDayAdult(calcularComissao(roomBroker.getPrice().getAdult()));
					priceDetail.setPricePerDayChild(calcularComissao(roomBroker.getPrice().getChild()));
				room.setPriceDetail(priceDetail);
			lstRoom.add(room);
		});
		hotel.setRooms(lstRoom);
		return hotel;
	}
	
	
	//Metodo para calcular o valor total da viagem
	private BigDecimal calcularTotalPrice(int dias, BigDecimal valorAdulto, BigDecimal valorCrianca, int qtdeAdultos, int qtdeCriancas) {
		
		//Calcular o valor por adulto (quantidade de dias * valor do adulto)
		valorAdulto = valorAdulto.multiply(new BigDecimal(dias));
		
		//Calcular a comissao no valor por adulto
		valorAdulto = calcularComissao(valorAdulto);
		
		//Calcular o valor pela qtde de adultos
		//Na descricao teste nao fala nada de multiplicar pela qtde de adultos, mas coloquei o codigo comentado caso seja necessario fazer esse calculo
		//valorAdulto = valorAdulto.multiply(new BigDecimal(qtdeAdultos));
		
		//Caso a qtde de criancas seja 0, nao somar no totalPrice
		if (qtdeCriancas > 0) {
			//Calcular o valor por crianca (quantidade de dias * valor da crianca)
			valorCrianca = valorCrianca.multiply(new BigDecimal(dias));
			
			//Calcular a comissao no valor por crianca
			valorCrianca = calcularComissao(valorCrianca);
			
			//Calcular o valor pela qtde de criancas
			//Na descricao teste nao fala nada de multiplicar pela qtde de criancas, mas coloquei o codigo comentado caso seja necessario fazer esse calculo
			//valorCrianca = valorCrianca.multiply(new BigDecimal(qtdeCriancas));
		}
		
		return (qtdeCriancas > 0 ? valorAdulto.add(valorCrianca) : valorAdulto);
	}
	
	
	//Metodo para calcular a comissao
	private BigDecimal calcularComissao(BigDecimal valor) {
		valor = valor.divide(comissao, MathContext.DECIMAL128);
		valor = valor.setScale(2, BigDecimal.ROUND_HALF_UP);
		return valor;
	}
	
}
