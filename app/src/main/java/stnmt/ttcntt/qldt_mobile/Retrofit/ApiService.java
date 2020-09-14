package stnmt.ttcntt.qldt_mobile.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import stnmt.ttcntt.qldt_mobile.RetrofitDemo.ResponseCurrency;
import stnmt.ttcntt.qldt_mobile.clsThuaDat;

public interface ApiService {
    @GET("layThongTinQuyHoach")//url api
    Call<List<clsThuaDat>> LayThongTinQuyHoach(@Query("thamSo") String thamSo);  // tham số trên url
}
