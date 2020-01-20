package net.explodingbush.ksoftapi.entities.actions;

public class KumoAction {

    private String token;

    public KumoAction(String token) {
        this.token = token;
    }

    /**
     * Gets an IP action
     *
     * @return A new {@link IPAction IPAction} instance.
     */
    public IPAction getIPAction() {
        return new IPAction(token);
    }

    /**
     * Gets a weather action
     *
     * @return A new {@link WeatherAction WeatherAction} instance.
     */
    public WeatherAction getWeatherAction() {
        return new WeatherAction(token);
    }
    
    /**
     * Gets a currency action
     *
     * @return A new {@link WeatherAction WeatherAction} instance.
     */
    public KCurrencyAction getCurrencyAction() {
        return new KCurrencyAction(token);
    }
}
