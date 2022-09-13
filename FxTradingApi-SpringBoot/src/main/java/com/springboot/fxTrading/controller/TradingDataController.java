package com.springboot.fxTrading.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fxTrading.model.TradingDataModel;
import com.springboot.fxTrading.service.TradeService;

@RestController("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class TradingDataController {
	@Autowired
	private TradeService tradeService;

	@GetMapping("/index")
	public LinkedHashMap<String, String> homePage() {

		return tradeService.homepage();
	}

	@PostMapping("/booktrade")
	public ResponseEntity<LinkedHashMap<String, Object>> bookTrades(@RequestBody TradingDataModel data) {

		return new ResponseEntity<LinkedHashMap<String, Object>>(tradeService.bookTrade(data), HttpStatus.ACCEPTED);
	}

	


	@PutMapping("/confirmtrade")
	public ResponseEntity<LinkedHashMap<String, Object>> confirmTrades(@RequestBody TradingDataModel data) {
		return new ResponseEntity<LinkedHashMap<String, Object>>(tradeService.confirmTrade(data.getTradeNo()), HttpStatus.OK);
	}

	@DeleteMapping("/canceltrade")
	public ResponseEntity<String> cancelTrade(TradingDataModel data) {
		return new ResponseEntity<String>(tradeService.cancelTrade(), HttpStatus.OK);
	}

	@DeleteMapping("/canceltrades")
	public ResponseEntity<LinkedHashMap<String,Object>> cancelTrades(@RequestParam(value = "id") Long id) {
		
		return new ResponseEntity<>(tradeService.cancelTrades(id), HttpStatus.OK);
	}

	@GetMapping("/printtrade")
	public List<TradingDataModel> printTrade() {
		return tradeService.printTrade();

	}

	@GetMapping("/printrate")
	public String printRate(TradingDataModel data) {
		return tradeService.getRate();
	}

	@GetMapping("/exit")
	public String exitTrade() {
		return "Bye - have a good day\n";
	}

}


