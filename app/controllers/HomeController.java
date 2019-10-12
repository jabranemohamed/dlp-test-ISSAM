package controllers;

import com.google.inject.Inject;
import com.typesafe.config.Config;
import play.cache.SyncCacheApi;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import services.ContentAPIService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests  ACHOURI ISSAM
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Config config;
    private final SyncCacheApi cache;
    private final WSClient wsClient;

    @Inject
    private ContentAPIService contentAPIService;

    @Inject
    public HomeController(Config config, SyncCacheApi cache, WSClient wsClient) {
        this.config = config;
        this.cache = cache;
        this.wsClient = wsClient;

    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    /**
     * This is where the test will be coded
     *
     * @return Result
     */
    public CompletionStage<Result> test() {
        String urlPicture = "";
        // get the cache time from config file
        int cacheTime = Integer.valueOf(config.getString("randomfox.cache"));
        // get the endpoint url  from config file
        String endPointUrl = config.getString("randompicture.url");
        // call the service layer
        List listofImage = new ArrayList<>();
        listofImage = ContentAPIService.getRandomPicture(endPointUrl, wsClient, cacheTime, cache);
        return CompletableFuture.completedFuture(ok(views.html.images.render(listofImage)));


    }

    public CompletionStage<Result> testMultiple() {
        // get the endpoint url  from config file
        String endPointUrl = config.getString("randommultiple.picture.url");

        List listofImage = new ArrayList<>();
        // call the service layer
        listofImage = ContentAPIService.getMultiplePicture(endPointUrl, wsClient);

        return CompletableFuture.completedFuture(ok(views.html.images.render(listofImage)));
    }

}
