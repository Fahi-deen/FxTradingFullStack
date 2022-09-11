package com.springboot.fxTrading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fxTrading.model.TradingDataModel;
@Repository
public interface TradingDataRepository extends JpaRepository<TradingDataModel, Long>{

}
