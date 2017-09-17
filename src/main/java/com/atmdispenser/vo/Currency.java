package com.atmdispenser.vo;

import com.atmdispenser.constant.Denomination;


/**
 * @author sumit
 * The value object containing the denomination type and its count.
 */
public class Currency {

	public Currency(Denomination denomination, int denominationCount) {
		super();
		this.denomination = denomination;
		this.denominationCount = denominationCount;
	}

	private Denomination denomination;
	private int denominationCount;

	public Denomination getDenomination() {
		return denomination;
	}

	public int getDenominationCount() {
		return denominationCount;
	}

	@Override
	public String toString() {
		return "Currency Detail : denomination = [" + denomination
				+ "], Count= [" + denominationCount + "]";
	}
	
	

}
