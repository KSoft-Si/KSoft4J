package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.Weather;
import net.explodingbush.ksoftapi.entities.impl.WeatherImpl;
import net.explodingbush.ksoftapi.enums.Language;
import net.explodingbush.ksoftapi.enums.ReportType;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.enums.Units;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.MissingArgumentException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import okhttp3.Response;
import org.json.JSONObject;

public class WeatherAction extends KSoftActionAdapter<Weather> {

    private String token;
    private String locationQuery;
    private String unit = "auto";
    private String language = "en";
    private ReportType reportType = ReportType.CURRENTLY;

    public WeatherAction(String token) {
        this.token = token;
    }

    /**
     * Declare location query to get the weather for
     *
     * @param query
     * The location query
     *
     * @return The WeatherAction instance. Useful for chaining.
     */
    public WeatherAction setLocationQuery(String query) {
        this.locationQuery = query;
        return this;
    }

    /**
     * Declare the unit of measurement to use
     * <p>Default: <b>auto</b></p>
     * @param unit
     * The unit of measurement
     *
     * @return The WeatherAction instance. Useful for chaining.
     */
    public WeatherAction setUnits(Units unit) {
        this.unit = unit.toString().toLowerCase();
        return this;
    }

    /**
     * Declare the language to get the weather with or whatever
     *
     * <p>I'm not sure this does anything.</p>
     *
     * <p>Default: <b>en</b></p>
     * @param language
     * The language to get the weather with or whatever
     *
     * @return The WeatherAction instance. Useful for chaining.
     */
    public WeatherAction setLanguage(Language language) {
        this.language = language.toString().toLowerCase();
        return this;
    }

    /**
     * Declare the weather report type to use
     * <p>Default: <b>{@link ReportType#CURRENTLY ReportType#CURRENTLY}</b></p>
     * @param reportType
     * The type of weather report to return
     *
     *     If the {@link ReportType ReportType} is one of the following, you will need to use {@link Weather#getAll() Weather#getAll()} The following {@link ReportType ReportTypes} return an array of weather objects.
     *     You need to use {@link Weather#getAll() Weather#getAll()} to retreive all of them.
     *     <ul>
     *         <li>{@link ReportType#MINUTELY ReportType#MINUTELY}</li>
     *         <li>{@link ReportType#HOURLY ReportType#HOURLY}</li>
     *         <li>{@link ReportType#DAILY ReportType#DAILY}</li>
     *     </ul>
     *
     * <p>If the {@link ReportType ReportType} is {@link ReportType#CURRENTLY ReportType#CURRENTLY}, then {@link Weather#getAll() Weather#getAll()} will return an empty arraylist.</p>
     *
     * <p>If any of the {@link Weather Weather} methods return {@code null}, then that method is not supported by the declared {@link ReportType ReportType}</p>
     *
     * @return The WeatherAction instance. Useful for chaining.
     */
    public WeatherAction setReportType(ReportType reportType) {
        this.reportType = reportType;
        return this;
    }

    /**
     * Executes the provided request synchronously.
     *
     * <p><b>This method is synchronous</b></p>
     *
     * @return A not-null {@link Weather Weather} instance.
     * @throws LoginException If the token is not provided or incorrect.
     * @throws MissingArgumentException If there is no location declared
     */
    @Override
    public Weather execute() {
        Response response = new JSONBuilder().requestKsoftResponse(String.format(Routes.KUMO_WEATHER.toString(), reportType.toString().toLowerCase(), locationQuery, unit, language), token);
        if(response.code() == 500) {
            throw new NotFoundException("NANI broke something. Everything is currently being exploded");
        }
        JSONObject json = new JSONBuilder().getJSONResponse(response);
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        if(locationQuery == null) {
            throw new MissingArgumentException("No location has been defined");
        }
        return new WeatherImpl(json, reportType);
    }
}
