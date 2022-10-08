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

	@Override
	public LinkedHashMap<String, Object> bookTrade(TradingDataModel data) {
		amountCalculator(data);
		CurrencypairChecker(data);
		TradingDataModel model = tradingDataRepository.save(data);
		String printRate, bookTrade, CancelTrade;
		printRate = "http://localhos t:8080/printrate/{tradeNo}";
		bookTrade = "http://localhost:8080/confirmtrade/{tradeNo}";
		CancelTrade = "http://localhost:8080/canceltrade/{tradeNo}";

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
	public String cancelTrade(Long id) {
		tradingDataRepository.deleteById(id);
		return "Trade is Canceled..";
	}

	@Override
	public String getRate(Long id) {
		TradingDataModel currentTrade=tradingDataRepository.findById(id).get();
		String rate;
		rate = "You are transferring INR " + displayAmount(currentTrade) + " to " + currentTrade.getCustomerName();
		return rate;

	}

	@Override
	public void amountCalculator(TradingDataModel data) {
		if (data.getAmount() <= 0) {
			throw new IllegalArgumentException("Cannot accept value " + data.getAmount() + " Enter positive value");
		} else {
			double amount = data.getAmount() * data.getRate();
			data.setAmount(amount);
		}
	}

	@Override
	public void CurrencypairChecker(TradingDataModel data) {
		if (data.getCurrencyPair().toUpperCase().equals("USDINR")) {
			data.setCurrencyPair("USDINR");
		} else {

			throw new IllegalArgumentException(data.getCurrencyPair() + " is not allowed Only USDINR is allowed");
		}
	}

	@Override
	public String displayAmount(TradingDataModel data) {
		NumberFormat nf = NumberFormat.getInstance(new Locale("en", "IN"));
		String displayAmount = nf.format(data.getAmount()).trim();
		return displayAmount;
	}



	@Override
	public String confirmTrade(Long id) {
		
		TradingDataModel trade = tradingDataRepository.findById(id).get();
		if(trade.getStatus().trim().equals("Booked"))
			return "Your Trade is Already Booked";
		trade.setStatus("Booked");
		TradingDataModel bookedTrade = tradingDataRepository.save(trade);

		String msg = "Trade for " + bookedTrade.getCurrencyPair() + " has been booked with rate " + bookedTrade.getRate() + " , "
				+ "The amount of Rs " + displayAmount(trade) + " will  be transferred in 2 working days to "
				+ bookedTrade.getCustomerName();
		return msg;

	}

	@Override
	public LinkedHashMap<String, Object> cancelTrades(Long id) {
		TradingDataModel trade = tradingDataRepository.findById(id).get();
		trade.setStatus("Cancelled");
		tradingDataRepository.delete(trade);
		LinkedHashMap<String, Object> map = new LinkedHashMap<>();
		map.put("details", trade);
		map.put("msg", "Trade is Cancelled");
		return map;
	}

}
