package com.springboot.fxTrading.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fxTrading.model.TradingDataModel;
import com.springboot.fxTrading.service.TradeService;

@RestController("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class TradingDataController {
	@Autowired
	private TradeService tradeService;

	@PostMapping("/booktrade")
	public LinkedHashMap<String, Object> bookTrades(@Valid @RequestBody TradingDataModel data) {

		return new LinkedHashMap<String, Object>(tradeService.bookTrade(data));
	}

	@PutMapping("/confirmtrade/{id}")
	public HashMap<String, String> confirmTrades(@PathVariable(name = "id") Long id) {
		HashMap<String, String> map = new HashMap<>();
		map.put("msg", tradeService.confirmTrade(id));
		return map;
	}

	@DeleteMapping("/canceltrade/{id}")
	public String cancelTrade(@PathVariable(name = "id") Long id) {
		return tradeService.cancelTrade(id);
	}

	@GetMapping("/printtrade")
	public List<TradingDataModel> printTrade() {
		return tradeService.printTrade();

	}

	@GetMapping("/printrate/{id}")
	public String printRate(@PathVariable(name = "id") Long id) {
		return tradeService.getRate(id);
	}

	@GetMapping("/exit")
	public String exitTrade() {
		return "Bye - have a good day";
	}

}
