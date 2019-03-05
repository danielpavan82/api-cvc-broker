package com.cvc.broker.api.rest.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvc.broker.api.domain.Hotel;
import com.cvc.broker.api.domain.InputRest;
import com.cvc.broker.api.exception.FieldsValidationException;
import com.cvc.broker.api.service.PriceService;
import com.cvc.broker.api.to.ErrorDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(basePath="/rest/v1/prices", value="Price controller", description="Controller para listar o valor de uma viagem")
@RestController
@RequestMapping("/rest/v1/prices")
@CrossOrigin(origins = "*")
public class PriceRestController {
	
	@Autowired
	private PriceService priceService;
	
	
	@ApiOperation(
			value 		= "Lista o valor da viagem de todos os hoteis de uma determinada cidade",
			produces 	= "application/json"
	)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Validação dos campos", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Cidade não encontrada", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class)
			}
	)
	@GetMapping("/city")
	public ResponseEntity<List<Hotel>> getPricesCity(
			@Valid @ApiParam(value="Classe para entrada da API", required=true) InputRest inputRest,
			BindingResult result
			) {
		
		if(result.hasErrors()) {
			throw new FieldsValidationException("Dados inválidos!", result);
		}
		
		//Chama o servico para retornar uma lista com os valores da viagem de uma determinada cidade
		List<Hotel> list = priceService.getPricesCity(inputRest);
		
		return new ResponseEntity<List<Hotel>>(list, HttpStatus.OK);
	}
	
	
	@ApiOperation(
			httpMethod 	= "GET",
			value 		= "Lista o valor da viagem de um determinado hotel",
			produces 	= "application/json"
	)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 400, message = "Validação dos campos", response = ErrorDetails.class),
			@ApiResponse(code = 404, message = "Hotel não encontrado", response = ErrorDetails.class),
			@ApiResponse(code = 500, message = "Internal server error", response = ErrorDetails.class)
			}
	)
	@GetMapping("/hotel")
	public ResponseEntity<Hotel> getPricesHotel(
			@Valid @ApiParam(value="Classe para entrada da API", required=true) InputRest inputRest,
			BindingResult result
			) {
		
		if(result.hasErrors()) {
			throw new FieldsValidationException("Dados inválidos!", result);
		}
		
		//Chama o servico para retornar o valor da viagem de um determinado hotel
		Hotel obj = priceService.getPricesHotel(inputRest);
		
		return new ResponseEntity<Hotel>(obj, HttpStatus.OK);
	}
	
}
