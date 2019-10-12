package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.typesafe.config.ConfigFactory;
import models.ImageDto;
import play.Logger;
import play.cache.SyncCacheApi;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class ContentAPIService {

    public static final String BASE_URL = ConfigFactory.load().getString("randomfox.baseUrl");

    /**
     * return a list of image containing just One Random Image
     *
     * @param endPointUrl
     * @param wsClient
     * @param cacheTime
     **/
    public static List<ImageDto> getRandomPicture(String endPointUrl, WSClient wsClient, int cacheTime, SyncCacheApi cache) {
        String urlPicture = "";
        ImageDto result = new ImageDto();
        List listofImage = new ArrayList<>();
        try {
            urlPicture = cache.get("pictureCahe");
            if(Objects.isNull(urlPicture)) {
                WSRequest request = wsClient.url(endPointUrl);
                CompletionStage<? extends WSResponse> responsePromise = request.get();
                WSResponse wsResponse = responsePromise.toCompletableFuture().get();
                String imageLocation = wsResponse.getHeaderValues("Location").get(0);
                urlPicture = BASE_URL + imageLocation;
                // create the cache
                cache.set("pictureCahe", urlPicture, cacheTime);
                result.setDownload_url(urlPicture);
                listofImage.add(result);
            }else{
                result.setDownload_url(urlPicture);
                listofImage.add(result);
            }

        } catch (Exception e) {
            Logger.error("Error happen during reading the API result");
        }
        return listofImage;

    }


    /**
     * return a list of image retrieved using the API
     *
     * @param endPointUrl
     * @param wsClient
     **/
    public static List<ImageDto> getMultiplePicture(String endPointUrl, WSClient wsClient) {
        List listofImage = new ArrayList<>();
        try {
            WSRequest request = wsClient.url(endPointUrl);
            CompletionStage<? extends WSResponse> responsePromise = request.get();
            WSResponse wsResponse = responsePromise.toCompletableFuture().get();
            JsonNode jsonNode = wsResponse.asJson();
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.readerFor(new TypeReference<List<ImageDto>>() {
            });
            List<ImageDto> list = reader.readValue(jsonNode);
            listofImage = list.stream().filter(image -> image.getId() % 2 == 0).collect(Collectors.toList());

        } catch (Exception e) {
            Logger.error("Error happen during reading the API result");
        }
        return listofImage;
    }
}
