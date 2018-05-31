package inc.talentedinc.singleton;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiHomeEndpoint;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asmaa on 05/17/2018.
 */

/**
 * singleton class to manage my app and don't create more objects from my connection api
 */
public class AppRetrofit {


    public static AppRetrofit App = null;
    private Retrofit retrofit;
    private ApiHomeEndpoint apiHomeEndpoint;
    private OkHttpClient.Builder httpClient;
    private Retrofit.Builder builder;

    private AppRetrofit() {
        initialization();

    }

    public static synchronized AppRetrofit getInstance() {

        if (App == null) {
            App = new AppRetrofit();
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

        apiHomeEndpoint = retrofit.create(ApiHomeEndpoint.class);

    }

    public ApiHomeEndpoint getHomeApi() {
        if(apiHomeEndpoint == null) {
            initialization();
        }
        return apiHomeEndpoint;
    }




}
