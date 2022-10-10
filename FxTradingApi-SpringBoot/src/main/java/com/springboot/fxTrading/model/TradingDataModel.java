package com.springboot.fxTrading.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "tradedata")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradingDataModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long tradeNo;
	private String customerName;
	private String currencyPair;
	private double amount;
	private float rate = 66.0f;
	private String status = "processing";

}
