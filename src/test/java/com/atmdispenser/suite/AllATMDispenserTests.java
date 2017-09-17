package com.atmdispenser.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.atmdispenser.controller.ATMDispenserApiControllerTest;
import com.atmdispenser.service.CurrencyDispenserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ATMDispenserApiControllerTest.class, CurrencyDispenserServiceTest.class})
public class AllATMDispenserTests {

}
