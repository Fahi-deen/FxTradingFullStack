package com.springboot.fxTrading.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotEmpty
    @Size(min = 4,message = "Customer Name should have atleast 4 Characters")
	private String customerName;
	@NotNull(message = "CurrencyPair must not be empty")
	private String currencyPair;
	@NotNull(message = "Amount must not be empty")
	private double amount;
	private float rate = 66.0f;
	private String status = "processing";

}
