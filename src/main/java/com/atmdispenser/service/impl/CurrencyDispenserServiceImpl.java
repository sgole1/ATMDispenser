package com.atmdispenser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atmdispenser.constant.Denomination;
import com.atmdispenser.service.CurrencyDispenserService;
import com.atmdispenser.vo.ATM;

@Service
public class CurrencyDispenserServiceImpl implements CurrencyDispenserService {
	
	public static final Logger logger = LoggerFactory
			.getLogger(CurrencyDispenserServiceImpl.class);

	public CurrencyDispenserServiceImpl() {
		super();
	}


	private List<Denomination> availableSortedDenominations;
	
	private ATM atm;

	public CurrencyDispenserServiceImpl(List<Denomination> availableDenominations, ATM atm) {
		super();
		this.availableSortedDenominations = availableDenominations;
		this.atm = atm;
	}
	
	@Override
	public Map<Denomination, Integer> dispense(int amountToBeDispersed){
		Map<Denomination, Integer> dispersedCurrencies = new HashMap<>();
		boolean isAmountDispersedSuccessfully = false;
		for(Denomination denomination : availableSortedDenominations){
			if (amountToBeDispersed >= denomination.getValue()
					&& isCurrencyAvailable(denomination, atm.getCurrencies())){
				int dispersedCurrencyNumber = amountToBeDispersed
						/ denomination.getValue();
				dispersedCurrencies.put(denomination, dispersedCurrencyNumber);
				
				int remainingCurrencyToBeWithdraw = amountToBeDispersed % denomination.getValue();
				if (remainingCurrencyToBeWithdraw != 0) {
					amountToBeDispersed = remainingCurrencyToBeWithdraw;
				}else{
					amountToBeDispersed = 0;
					isAmountDispersedSuccessfully = atm.updateCurrency(dispersedCurrencies);
					logger.info("isAmountDispersedSuccessfully :"+isAmountDispersedSuccessfully);
					break;
				}
			}
		}
		return isAmountDispersedSuccessfully ? dispersedCurrencies : null;
	}
	
	private boolean isCurrencyAvailable(Denomination denomination,
			Map<Denomination, Integer> map) {
		int totalAvailableCurrency = atm.getCurrencies().get(denomination);
		return (totalAvailableCurrency > 0 ? true : false);
	}

	@Override
	public Map<Denomination, Integer> getBalance() {
		return atm.getCurrencies();
	}

}
