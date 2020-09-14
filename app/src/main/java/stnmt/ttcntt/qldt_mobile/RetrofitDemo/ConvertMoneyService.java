package stnmt.ttcntt.qldt_mobile.RetrofitDemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConvertMoneyService {
    @GET("api/live")//url api
    Call<ResponseCurrency> convertVNDtoUSD(@Query("access_key") String access_key,
                                           @Query("currencies") String currencies,
                                           @Query("source") String source,
                                           @Query("format") int format);  // tham số trên url
}