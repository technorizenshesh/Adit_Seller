package lk.aditi.ecom_seller.network.categories;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Category_Request {

    @GET("get_category")
    Call<JsonElement> getCategory();
}
