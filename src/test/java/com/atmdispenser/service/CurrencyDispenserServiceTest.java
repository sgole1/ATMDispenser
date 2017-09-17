package com.atmdispenser.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.atmdispenser.constant.Denomination;
import com.atmdispenser.service.impl.CurrencyDispenserServiceImpl;
import com.atmdispenser.vo.ATM;

@ContextConfiguration
@RunWith(MockitoJUnitRunner.class)
public class CurrencyDispenserServiceTest {
	
	private CurrencyDispenserService currencyDispenserService;
	
	
	public Map<Denomination, Integer> currencyMap() {
		Map<Denomination, Integer> currencyMap = new HashMap<Denomination, Integer>();
		currencyMap.put(Denomination.FIFTY, 30);
		currencyMap.put(Denomination.TWENTY, 20);
		return currencyMap;
	}

	
	public ATM getATMInstance(){
		return new ATM(currencyMap());
	}
	
	@Before
	public void setup() throws Exception {
		List<Denomination> denominationsList = getATMInstance().getAvailableDenominations();
		currencyDispenserService = new CurrencyDispenserServiceImpl(denominationsList, getATMInstance());
	}

	@Test
	public void testDispense() {
		Map<Denomination, Integer> result = currencyDispenserService.dispense(200);
		assertEquals(result.get(Denomination.FIFTY), new Integer(4));
	}

	@Test
	public void testGetBalance() {
		Map<Denomination, Integer> result = currencyDispenserService.getBalance();
		assertEquals(result.get(Denomination.FIFTY), new Integer(30));
		assertEquals(result.get(Denomination.TWENTY), new Integer(20));

	}

}
