package inc.talentedinc.singleton;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import inc.talentedinc.API.APIUrls;
import inc.talentedinc.API.ApiCreateCourse;
import inc.talentedinc.API.ApiHomeEndpoint;
import inc.talentedinc.API.ApiLogin;
import inc.talentedinc.utilitis.ActionUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.FacebookSdk.getCacheDir;

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
    private OkHttpClient httpClient;
    private Retrofit.Builder builder;
    private ApiLogin apiLogin ;
    private ApiCreateCourse apiCreateCourse;


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

        int cacheSize = 50 * 1024 * 1024; // 50 MiB
        Cache cache = new Cache(getCacheDir(), cacheSize);

        httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {

                        if (!ActionUtils.isInternetConnected(getApplicationContext())) {
                            CacheControl.Builder cacheBuilder = new CacheControl.Builder();

                            cacheBuilder.maxStale(365, TimeUnit.DAYS);
                            cacheBuilder.onlyIfCached();
                            return chain.proceed(chain.request().newBuilder().cacheControl(cacheBuilder.build()).build());
                        }

                        return chain.proceed(chain.request());
                    }
                })
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        CacheControl.Builder cacheBuilder = new CacheControl.Builder();

                        if (ActionUtils.isInternetConnected(getApplicationContext())) {

                            cacheBuilder.maxAge(10, TimeUnit.SECONDS);
                            okhttp3.Response response = chain.proceed(chain.request());
                            return response.newBuilder()
                                    .removeHeader("Pragma")
                                    .removeHeader("Cache-Control")
                                    .header("Cache-Control", cacheBuilder.build().toString())
                                    .build();
                        }

                        return chain.proceed(chain.request());
                    }
                }).build();


        builder = new Retrofit.Builder()
                .baseUrl(APIUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient).build();

    }

    public ApiHomeEndpoint getHomeApi() {
        if(apiHomeEndpoint == null) {
            initialization();
            apiHomeEndpoint = retrofit.create(ApiHomeEndpoint.class);

        }
        return apiHomeEndpoint;
    }
//alaa----------------------------------------------------
    public ApiLogin getApiLogin (){
        if (apiLogin == null){
            initialization();
            apiLogin = retrofit.create(ApiLogin.class);
        }
        return apiLogin ;
    }


    public Retrofit getRetrofitInstance(){
        initialization();
        return retrofit;
    }

    public ApiCreateCourse getApiCreateCourse() {
        if (apiCreateCourse == null){
            initialization();
            apiCreateCourse = retrofit.create(ApiCreateCourse.class);
        }
        return apiCreateCourse ;
    }

}
