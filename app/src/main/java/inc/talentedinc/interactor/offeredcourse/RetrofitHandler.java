package inc.talentedinc.interactor.offeredcourse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {

    private static volatile Retrofit retrofit = null;

    private static Retrofit getRetrofitClient(String baseUrl) {

        if (retrofit == null) {
            synchronized (RetrofitHandler.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static GetOfferedCourses getOfferedCoursesService(String baseUrl) {

        return getRetrofitClient(baseUrl).create(GetOfferedCourses.class);
    }
}
