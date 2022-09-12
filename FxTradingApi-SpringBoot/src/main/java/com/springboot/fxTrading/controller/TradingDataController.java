package com.springboot.fxTrading.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String homePage() {
    	
    	return tradeService.homepage();
    }
	@PostMapping("/booktrade")
	public ResponseEntity<String> bookTrade(@RequestBody TradingDataModel data){
		
		return new ResponseEntity<String>(tradeService.bookTrade(data), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/booktrades")
	public ResponseEntity<HashMap<String,Object>> bookTrades(@RequestBody TradingDataModel data){
		
		return new ResponseEntity<HashMap<String,Object>>(tradeService.bookTrades(data), HttpStatus.ACCEPTED);
	}

	@PutMapping("/confirmtrade")
	public ResponseEntity<String> confirmTrade(TradingDataModel data) {
		return new ResponseEntity<String>(tradeService.confirmTrade(), HttpStatus.OK);
	}
	@PutMapping("/confirmtrades")
	public ResponseEntity<String> confirmTrades(@RequestBody UpdateStatus data) {
		return new ResponseEntity<String>(tradeService.confirmTrades(data.id), HttpStatus.OK);
	}

	@DeleteMapping("/canceltrade")
	public ResponseEntity<String> cancelTrade(TradingDataModel data) {
		return new ResponseEntity<String>(tradeService.cancelTrade(), HttpStatus.OK);
	}
	
	@DeleteMapping("/canceltrades")
	public ResponseEntity<String> cancelTrades(@RequestParam(name = "id") Long id) {
		return new ResponseEntity<String>(tradeService.cancelTrades(id), HttpStatus.OK);
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
class UpdateStatus{
	 public Long id;
}