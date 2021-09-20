package lk.aditi.ecom_seller.network;

import com.google.gson.JsonElement;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RequestAuthentication {

    @Multipart
    @POST("signup")
    Call<JsonElement> SignUp(@Part MultipartBody.Part user_name,
                             @Part MultipartBody.Part email,
                             @Part MultipartBody.Part password,
                             @Part MultipartBody.Part mobile,
                             @Part MultipartBody.Part register_id,
                             @Part MultipartBody.Part category_id,
                             @Part MultipartBody.Part business_name,
                             @Part MultipartBody.Part location,
                             @Part MultipartBody.Part type,
                             @Part MultipartBody.Part delivery_info,
                             @Part MultipartBody.Part image );

    @POST("check_otp")
    Call<JsonElement> Otp(@Query("otp") String otp,
                          @Query("user_id") String user_id);

}
