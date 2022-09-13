package com.springboot.fxTrading.serviceImpl;

import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.fxTrading.model.TradingDataModel;
import com.springboot.fxTrading.repository.TradingDataRepository;
import com.springboot.fxTrading.service.TradeService;

@Service
public class BookTradeServiceImpl implements TradeService {
	@Autowired
	private TradingDataRepository tradingDataRepository;
	private TradingDataModel data;

	@Override
	public LinkedHashMap<String, Object> bookTrade(TradingDataModel data) {
		this.data = data;
		amountCalculator();
		CurrencypairChecker();
		TradingDataModel model = tradingDataRepository.save(data);
		String printRate, bookTrade, CancelTrade;
		printRate = "http://localhost:8082/printrate";
		bookTrade = "http://localhost:8082/confirmtrade";
		CancelTrade = "http://localhost:8082/canceltrade";

		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("trade", model);
		map.put("Print Rate click here", printRate);
		map.put("Book Trade Click here", bookTrade);
		map.put("Cancel Trade Click here", CancelTrade);
		return map;
	}

	@Override
	public List<TradingDataModel> printTrade() {
		return tradingDataRepository.findAll();
	}

	

	@Override
	public String cancelTrade() {
		tradingDataRepository.delete(data);
		return "Trade is Canceled..";
	}

	@Override
	public String getRate() {
		String rate;
		rate = "You are transferring INR " + displayAmount() + " to " + data.getCustomerName();
		return rate;

	}

	@Override
	public void amountCalculator() {
		if (data.getAmount() <= 0) {
			throw new IllegalArgumentException("Cannot accept value " + data.getAmount() + " Enter positive value");
		} else {
			double amount = data.getAmount() * data.getRate();
			data.setAmount(amount);
		}
	}

	@Override
	public void CurrencypairChecker() {
		if (data.getCurrencyPair().toUpperCase().equals("USDINR")) {
			data.setCurrencyPair("USDINR");
		} else {

			throw new IllegalArgumentException(data.getCurrencyPair() + " is not allowed Only USDINR is allowed");
		}
	}

	@Override
	public String displayAmount() {
		NumberFormat nf = NumberFormat.getInstance(new Locale("en", "IN"));
		String displayAmount = nf.format(data.getAmount()).trim();
		return displayAmount;
	}

	@Override
	public LinkedHashMap<String, String> homepage() {

		String bookTrade, printTrade, exitTrade;
		bookTrade = "http://localhost:8082/booktrade";
		printTrade = "http://localhost:8082/printtrade";
		exitTrade = "link http://localhost:8082/exit";
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("To Book Trade click here", bookTrade);
		map.put("To Print Trade click here", printTrade);
		map.put("To exit click here", exitTrade);

		return map;
	}

	@Override
	public String confirmTrade(Long id) {
		TradingDataModel trade = tradingDataRepository.findById(id).get();
		trade.setStatus("Booked");
		TradingDataModel datas = tradingDataRepository.save(trade);
		NumberFormat nf = NumberFormat.getInstance(new Locale("en", "IN"));
		String displayAmount = nf.format(trade.getAmount()).trim();
		String msg = "Trade for " + datas.getCurrencyPair() + " has been booked with rate " + datas.getRate() + " , "
				+ "The amount of Rs " + displayAmount + " will  be transferred in 2 working days to "
				+ datas.getCustomerName() + "..";
		return msg;

	}

	@Override
	public String cancelTrades(Long id) {
		TradingDataModel trade = tradingDataRepository.findById(id).get();
		tradingDataRepository.delete(trade);
		return "Trade is Canceled..";
	}

}
