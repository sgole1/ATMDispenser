package com.atmdispenser.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.atmdispenser.constant.Denomination;
import com.atmdispenser.service.CurrencyDispenserService;
import com.atmdispenser.service.impl.CurrencyDispenserServiceImpl;
import com.atmdispenser.vo.ATM;

/**
 * The config file to load the spring context with desired beans and their injections.
 * @author sumit
 *
 */
@SpringBootApplication(scanBasePackages = { "com.atmdispenser" })
public class ATMDispenserApiAppConfig {

	public static void main(String[] args) {
		SpringApplication.run(ATMDispenserApiAppConfig.class, args);
	}

	@Bean
	public Map<Denomination, Integer> currencyMap() {
		Map<Denomination, Integer> currencyMap = new HashMap<Denomination, Integer>();
		currencyMap.put(Denomination.FIFTY, 30);
		currencyMap.put(Denomination.TWENTY, 20);
		return currencyMap;
	}
	
	@Autowired
	ATM atmInstance;
	
	@Bean
	public ATM getATMInstance(){
		
		 if(atmInstance == null){
		        synchronized (ATMDispenserApiAppConfig.class) {
		            if(atmInstance == null){
		            	atmInstance = new ATM(currencyMap());
		            }
		        }
		    }
		return atmInstance;
	}
	@Bean(name = "currencyDispenser")
	public CurrencyDispenserService getCurrencyDispenserServiceInstance() {
		  List<Denomination> availableDenominations = getATMInstance().getAvailableDenominations();
		return new CurrencyDispenserServiceImpl(availableDenominations, atmInstance);
	}

}
