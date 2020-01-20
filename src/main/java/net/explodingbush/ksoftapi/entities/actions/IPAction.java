package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.IP;
import net.explodingbush.ksoftapi.entities.impl.IPImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import okhttp3.Response;
import org.json.JSONObject;

public class IPAction extends KSoftActionAdapter<IP> {

    private String token;
    private String ip;

    public IPAction(String token) {
        this.token = token;
    }

    /**
     * Sets the IP address to use
     * @param ipAddress
     *  The IP address to use to execute with
     * @return IPAction instance. Useful for chaining.
     */
    public IPAction setIP(String ipAddress) {
        this.ip = ipAddress;
        return this;
    }
    /**
     * Executes the provided request synchronously.
     *
     * <p><b>This method is synchronous</b></p>
     *
     * @return A not-null {@link IP IP} instance.
     * @throws LoginException           If the token is not provided or incorrect.
     */
    @Override
    public IP execute() {
        Response response = new JSONBuilder().requestKsoftResponse(String.format(Routes.KUMO_IP.toString(), ip), token);
        if(response.code() == 500) {
            throw new NotFoundException("NANI broke something. Everything is currently being exploded");
        }
        JSONObject json = new JSONBuilder().getJSONResponse(response);
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        return new IPImpl(json);
    }
}
