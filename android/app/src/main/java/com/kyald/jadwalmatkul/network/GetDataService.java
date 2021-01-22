package com.kyald.jadwalmatkul.network;

import com.google.gson.annotations.SerializedName;
import com.kyald.jadwalmatkul.model.BaseResponse;
import com.kyald.jadwalmatkul.model.MatkulDataResponse;
import com.kyald.jadwalmatkul.model.MatkulHari;
import com.kyald.jadwalmatkul.model.MatkulHariResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {

    @GET("api/matkul.php")
    Call<MatkulDataResponse> getAllMatkul();

    @GET("api/hari.php")
    Call<MatkulHariResponse> getHari();

    @POST("api/create_matkul.php")
    @FormUrlEncoded
    Call<BaseResponse> postCreateMatkul(@Field("id_hari") String id_hari, @Field("matkul") String matkul );

    @POST("api/update_matkul.php")
    @FormUrlEncoded
    Call<BaseResponse> postUpdateMatkul(@Field("id") String id, @Field("id_hari") String id_hari, @Field("matkul") String matkul);

    @POST("api/delete_matkul.php")
    @FormUrlEncoded
    Call<BaseResponse> postDeleteMatkul(@Field("id") String id);

    @POST("api/login.php")
    @FormUrlEncoded
    Call<BaseResponse> postLogin(@Field("username") String username,@Field("password") String password);
}
