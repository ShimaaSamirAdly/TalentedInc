package inc.talentedinc.singleton;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiUpComingEndpoint;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asmaa on 05/17/2018.
 */

/**
 * singleton class to manage my app and don't create more objects from my connection api
 */
public class App {


    public static App App = null;
    private Retrofit retrofit;
    private ApiUpComingEndpoint apiUpComingEndpoint;
    private OkHttpClient.Builder httpClient;
    private Retrofit.Builder builder;

    private App() {
        initialization();

    }

    public static synchronized App getInstance() {

        if (App == null) {
            App = new App();
        }
        return App;
    }

    private void initialization() {
        // to cache request

        httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
//                .cache(cache)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });


        builder = new Retrofit.Builder()
                .baseUrl(APIUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();

        apiUpComingEndpoint = retrofit.create(ApiUpComingEndpoint.class);

    }

    public ApiUpComingEndpoint getUpComingApi() {
        if(apiUpComingEndpoint == null) {
            initialization();
        }
        return apiUpComingEndpoint;
    }




}
