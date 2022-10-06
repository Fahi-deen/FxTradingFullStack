package com.springboot.fxTrading;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.fxTrading.model.TradingDataModel;
import com.springboot.fxTrading.repository.TradingDataRepository;
import com.springboot.fxTrading.service.TradeService;

@SpringBootTest
//@TestMethodOrder(OrderAnnotation.class)
class FxTradingApiApplicationTests {

	@Autowired
	TradingDataRepository testDataRepository;
	@Autowired
	TradeService testTradeService;

	@Test
//	@Order(1)
	public void testBookTrade() {
		TradingDataModel book = TradingDataModel.builder().customerName("Test Fahi").currencyPair("USDINR").amount(1000).status("processing").build();
		testTradeService.bookTrade(book);
		testTradeService.printTrade();
		System.out.println(testTradeService.printTrade());
		assertNotNull(testDataRepository.findById(1L).get());
	}

	@Test
	public void testprintTrades() {
		List<TradingDataModel> allTrades = testTradeService.printTrade();
		System.out.println(allTrades);
		assertThat(allTrades).size().isGreaterThan(0);
	}

	@Test
	public void testSingleTrade() {
		TradingDataModel data = testDataRepository.findById(11L).get();
		assertEquals("Test1", data.getCustomerName());
	}

}
