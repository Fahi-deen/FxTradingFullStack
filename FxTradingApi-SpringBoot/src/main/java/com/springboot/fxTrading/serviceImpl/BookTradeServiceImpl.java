package com.springboot.fxTrading.serviceImpl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
	public String bookTrade(TradingDataModel data){
		this.data = data;
		amountCalculator();
		CurrencypairChecker();
		tradingDataRepository.save(data);
		String displayText;
		displayText = "Trade is processing,please confirm your trade\n"
				+ "Do you want to get Rate click this link http://localhost:8082/printrate\n"
				+ "To Book click this link http://localhost:8082/confirmtrade\n"
				+ "To Cancel click this link http://localhost:8082/canceltrade";
		return displayText;
	}
	@Override
	public HashMap<String,Object> bookTrades(TradingDataModel data){
		this.data = data;
		amountCalculator();
		CurrencypairChecker();
		TradingDataModel model = tradingDataRepository.save(data);
		String displayText;
		displayText = "Trade is processing,please confirm your trade   "
				+ "Do you want to get Rate click this link http://localhost:8082/printrate   "
				+ "To Book click this link http://localhost:8082/confirmtrade   "
				+ "To Cancel click this link http://localhost:8082/canceltrade   ";
		 HashMap<String, Object> map = new HashMap<>();
		 map.put("trade",model);
		 map.put("details", displayText);
		return map;
	}

	@Override
	public List<TradingDataModel> printTrade() {
		return tradingDataRepository.findAll();
	}

	@Override
	public String confirmTrade() {
		data.setStatus("booked");
		tradingDataRepository.save(data);
		String msg = "Trade for " + data.getCurrencyPair() + " has been booked with rate " + data.getRate() + " , "
				+ "The amount of Rs " + displayAmount() + " will  be transferred in 2 working days to "
				+ data.getCustomerName() + "..";
		return msg;
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
		DecimalFormat df = new DecimalFormat("###,###,###");
		String displayAmount = df.format(data.getAmount()).trim();	
		return displayAmount;
	}

	@Override
	public String homepage() {
		String msg="To book Trade clik this link http://localhost:8082/booktrade\n"
    			+ "To Print Trade click this link http://localhost:8082/printtrade\n"
    			+ "To Exit click this link http://localhost:8082/exit\n";
		return msg;
	}

	@Override
	public String confirmTrades(Long id) {
	     TradingDataModel trade = tradingDataRepository.findById(id).get();
		trade.setStatus("Booked");
		TradingDataModel datas = tradingDataRepository.save(trade);
		String msg = "Trade for " + datas.getCurrencyPair() + " has been booked with rate " + datas.getRate() + " , "
				+ "The amount of Rs " + displayAmount() + " will  be transferred in 2 working days to "
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
