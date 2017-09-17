package com.atmdispenser.constant;

/**
 * @author sumit
 * Constant class to show the all possible denominations available in the ATM. 
 * We can add more denominations here to include in the ATM.
 */
public enum Denomination {
 FIFTY(50), TWENTY(20) ;

	private int value;

	private Denomination(int value) {
		this.value = value;
	}
	
	public Integer getValue(){
		return value;
	}
	
	

}
