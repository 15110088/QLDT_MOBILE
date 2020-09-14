package stnmt.ttcntt.qldt_mobile.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSupport<T> {
    private static Retrofit retrofit;

    // Private constructor to avoid client applications to use constructor
    public RetrofitSupport() {
         retrofit = new Retrofit.Builder()
                .baseUrl("http://192.169.3.197/DTBienHoa/servicesvitri.svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiService createService(){
        ApiService service= retrofit.create(ApiService.class);
        return service;

    }
}
