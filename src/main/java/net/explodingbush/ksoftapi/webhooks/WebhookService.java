package net.explodingbush.ksoftapi.webhooks;

import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.handle.BanEventHandler;
import net.explodingbush.ksoftapi.handle.UnbanEventHandler;
import net.explodingbush.ksoftapi.handle.VoteEventHandler;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class WebhookService {

    private  KSoftAPI api;

    public WebhookService(KSoftAPI ksoft) {
        api = ksoft;
    }

    private Logger logger = LoggerFactory.getLogger(WebhookService.class);


    Runnable runnable = () -> {


        port(new WebhookManager(null).getPort());


        post("/webhook", (req, res) ->

        {
            res.type("application/json");
            String token = (req.headers("Authorization") != null ? req.headers("Authorization").split("\\s+")[1] : "");
            JSONObject data = (!req.body().isEmpty() ? new JSONObject(req.body()) : null);
            JSONObject json = new JSONObject();
            if (!token.equals(new WebhookManager(null).getToken())) {
                json.put("error", true).put("message", "Authorization header does not match the specified token in KSoft4J");
                halt(401, json.toString());
            }
            if (data.isNull("event")) {
                json.put("error", true).put("message", "The event doesn't match an event in this wrapper");
                halt(404, json.toString());
            } else if(data.getString("event").equalsIgnoreCase("vote")) {
                new VoteEventHandler(api).handleInternally(data);
            } else if (data.getString("event").equalsIgnoreCase("ban")) {
                new BanEventHandler(api).handleInternally(data);
            } else if(data.getString("event").equalsIgnoreCase("unban")) {
                new UnbanEventHandler(api).handleInternally(data);
            } else {
                json.put("error", true).put("message", "The event doesn't match an event in this wrapper");
                halt(404, json.toString());
            }
            json.put("error", false).put("message", "Successfully dispatched");
            return json.toString();
        });
        internalServerError((req, res) -> {
            res.type("application/json");
            JSONObject json = new JSONObject();
            json.put("error", true).put("message", "Internal Server Error: 500");
            return json.toString();
        });
        awaitInitialization();
        logger.info("Webhook Server is now listening on " + port());
    };
    public Logger getLogger() {
        return logger;
    }
}
