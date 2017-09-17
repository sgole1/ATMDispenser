package com.atmdispenser.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.atmdispenser.constant.Denomination;
import com.atmdispenser.service.CurrencyDispenserService;

@ContextConfiguration
@RunWith(MockitoJUnitRunner.class)
public class ATMDispenserApiControllerTest {
	
	
	@InjectMocks
	private ATMDipenserApiController atmDipenserApiController;


	@Mock
	private CurrencyDispenserService currencyDispenserMock;
	
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() throws Exception {
		
		mockMvc = MockMvcBuilders.standaloneSetup(this.atmDipenserApiController).build();
	}

	@Test
	public void testWithdrawSuccess() throws Exception {
		Map<Denomination, Integer> availableCurrencyMap = new HashMap<Denomination, Integer>();
		availableCurrencyMap.put(Denomination.FIFTY, 4);
		when(currencyDispenserMock.dispense(200)).thenReturn(availableCurrencyMap);
		mockMvc.perform(get("/api/atm/200")).andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
		 .andExpect(content().string("{FIFTY=4}"));
	}
	
	@Test
	public void testWithdrawFailure() throws Exception {
		Map<Denomination, Integer> availableCurrencyMap = new HashMap<Denomination, Integer>();
		when(currencyDispenserMock.dispense(210)).thenReturn(availableCurrencyMap);
		 mockMvc.perform(get("/api/atm/220")).andExpect(status().isOk()).andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
		 .andExpect(content().string("Denominations are available in the multiple of 50 & 20$ only"));
		
		 
	}

	@Test
	public void testShowBalance() throws Exception {
		 mockMvc.perform(get("/api/atm/")).andExpect(status().isOk());
	}

}
