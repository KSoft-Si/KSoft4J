package net.explodingbush.ksoftapi.entities;

import java.util.Currency;

public interface KCurrency {

	public Currency to();
	public Currency from();
	public double originalAmount();
	public double convertedAmount();
	public String convertedAmountPretty();
	
}