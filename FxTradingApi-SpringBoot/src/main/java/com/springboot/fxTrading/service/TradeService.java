package com.springboot.fxTrading.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.springboot.fxTrading.model.TradingDataModel;

public interface TradeService {
	LinkedHashMap<String, String> homepage();

	LinkedHashMap<String, Object> bookTrade(TradingDataModel data);

	List<TradingDataModel> printTrade();

	String getRate(Long id);

	String cancelTrade(Long id);

	void amountCalculator(TradingDataModel data);

	void CurrencypairChecker(TradingDataModel data);

	String displayAmount(TradingDataModel data);

	String confirmTrade(Long id);

	LinkedHashMap<String,Object> cancelTrades(Long id);

}
