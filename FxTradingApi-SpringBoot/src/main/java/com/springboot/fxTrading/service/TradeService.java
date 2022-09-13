package com.springboot.fxTrading.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.springboot.fxTrading.model.TradingDataModel;

public interface TradeService {
	LinkedHashMap<String, String> homepage();

	LinkedHashMap<String, Object> bookTrade(TradingDataModel data);

	List<TradingDataModel> printTrade();

	String getRate();

	String confirmTrade();

	String cancelTrade();

	void amountCalculator();

	void CurrencypairChecker();

	String displayAmount();

	String confirmTrades(Long id);

	String cancelTrades(Long id);

}
