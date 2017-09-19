package com.atmdispenser.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.atmdispenser.constant.Denomination;


/**
 * @author sumit
 * The value object containing the all available currencies and their count in ATM. It exposes the method to
 * get the sorted(descending) list of all available denominations in the ATM. In addition, it also update the
 * ATM amount after the successful withdrawal.  
 */
public class ATM {

	private Map<Denomination, Integer> availableCurrencies;
	
		public ATM(Map<Denomination, Integer> currencies) {
		this.availableCurrencies = currencies;
	}

	public Map<Denomination, Integer> getCurrencies() {
		return availableCurrencies;
	}
	
	public List<Denomination> getAvailableDenominations() {
		List<Denomination> availableDenominations = new ArrayList(availableCurrencies.keySet());
		Collections.sort(availableDenominations, new Comparator<Denomination>() {
		    @Override
		    public int compare(Denomination d1, Denomination d2) {
		        return d2.getValue().compareTo(d1.getValue());
		    }

		
		});
		return availableDenominations;
	}
	
	public boolean updateCurrency(Map<Denomination, Integer> dispersedCurrency) {
		boolean currencyUpdateFlag = false;
		for(Denomination denomination : dispersedCurrency.keySet()){
			int totalAvailableCurrency = availableCurrencies.get(denomination);
			totalAvailableCurrency = totalAvailableCurrency - dispersedCurrency.get(denomination);
			if (totalAvailableCurrency > 0) {
				availableCurrencies.put(denomination, totalAvailableCurrency);
				currencyUpdateFlag = true;
			}else{
				currencyUpdateFlag = false;
				break;
			}
			
		}
		return currencyUpdateFlag;
		
	}
	


}
