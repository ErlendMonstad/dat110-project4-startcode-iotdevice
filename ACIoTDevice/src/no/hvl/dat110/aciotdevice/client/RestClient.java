package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    // final public static String BASEURL = "http://localhost:8080/accessdevice/";
    HttpClient httpClient = HttpClients.createDefault();

    public RestClient() {

    }

    private final static String host = "http://localhost:8080";
    private final static String logpath = "/accessdevice/log/";

    public void doPostAccessEntry(String message) {
        try{
            String json = new AccessMessage(message).toJson();
            HttpPost httpPost = new HttpPost(host + logpath);
            httpPost.setEntity(new StringEntity(json));
            httpClient.execute(httpPost);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String codepath = "/accessdevice/code";
    public AccessCode doGetAccessCode() {
        try{
            HttpGet httpGet = new HttpGet(host + codepath);
            HttpResponse response = httpClient.execute(httpGet);
            AccessCode code = AccessCode.fromJson(EntityUtils.toString(response.getEntity()));
            return code;
        }catch (Exception e){
            e.printStackTrace();;
        }
        // TODO: Gjør optional
        return null;
    }
}
