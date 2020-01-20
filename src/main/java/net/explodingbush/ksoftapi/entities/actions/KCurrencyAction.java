package net.explodingbush.ksoftapi.entities.actions;

import java.util.Currency;

import org.json.JSONObject;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.KCurrency;
import net.explodingbush.ksoftapi.entities.impl.KCurrencyImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;

public class KCurrencyAction extends KSoftActionAdapter<KCurrency>{

	private String token;
	private Currency from, to;
	private double amount;
	
	protected KCurrencyAction(String token){
		this.token = token;
		this.amount = 0.0;
		this.to = Currency.getInstance("USD");
		this.from = Currency.getInstance("USD");
	}
	public KCurrencyAction from(String code){
		return this.from(Currency.getInstance(code));
	}
	public KCurrencyAction from(Currency currency){
		Checks.notNull(currency, "currency");
		this.from = currency;
		return this;
	}
	public KCurrencyAction to(String code){
		return this.to(Currency.getInstance(code));
	}
	public KCurrencyAction to(Currency currency){
		Checks.notNull(currency, "currency");
		this.to = currency;
		return this;
	}
	public KCurrencyAction amount(double amount){
		Checks.positive((int) amount, "amount");
		this.amount = amount;
		return this;
	}
	@Override
	public KCurrency execute() {
		if(to.equals(from)){
			throw new IllegalStateException("Cannot convert a currency to itself!");
		}
		JSONObject json = new JSONBuilder().requestKsoft(String.format(Routes.KUMO_CURRENCY.toString(), from.getCurrencyCode(), to.getCurrencyCode(), amount), token);
		return new KCurrencyImpl(json, to, from, amount);
	}

}
