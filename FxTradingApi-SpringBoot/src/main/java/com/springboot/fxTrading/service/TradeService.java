package com.springboot.fxTrading.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.springboot.fxTrading.model.TradingDataModel;

public interface TradeService {
	LinkedHashMap<String, String> homepage();

	LinkedHashMap<String, Object> bookTrade(TradingDataModel data);

	List<TradingDataModel> printTrade();

	String getRate();

	String cancelTrade();

	void amountCalculator();

	void CurrencypairChecker();

	String displayAmount();

	LinkedHashMap<String, Object> confirmTrade(Long id);

	LinkedHashMap<String,Object> cancelTrades(Long id);

}
