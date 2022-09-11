package com.springboot.fxTrading.service;

import java.util.HashMap;
import java.util.List;

import com.springboot.fxTrading.model.TradingDataModel;

public interface TradeService {
	String bookTrade(TradingDataModel data);
	

	List<TradingDataModel> printTrade();

	String getRate();

	String confirmTrade();

	String cancelTrade();

	void amountCalculator();

	void CurrencypairChecker();

	String displayAmount();
	
	String homepage();
	
	HashMap<String,Object> bookTrades(TradingDataModel data);
	 String confirmTrades(Long id);
	 String cancelTrades(Long id);
	
}
