package com.atmdispenser.service;

import java.util.Map;

import com.atmdispenser.constant.Denomination;

/**
 * @author sumit
 *The service class defining the method to withdraw and show the remiaining amount in the ATM.
 */
public interface CurrencyDispenserService {

	Map<Denomination, Integer> dispense(int amountToBeDispersed);
	
	Map<Denomination, Integer> getBalance();

}
