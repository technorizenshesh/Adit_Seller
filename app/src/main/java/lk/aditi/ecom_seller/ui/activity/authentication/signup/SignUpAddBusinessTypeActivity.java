package lk.aditi.ecom_seller.ui.activity.authentication.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.GridLayoutManager;

import com.aditi.ecom_seller.databinding.ActivitySignUpAddBusinessTypeBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lk.aditi.ecom_seller.models.categories.CategoriesResponse;
import lk.aditi.ecom_seller.models.categories.ResultItem;
import lk.aditi.ecom_seller.network.NetworkConstraint;
import lk.aditi.ecom_seller.network.RetrofitClient;
import lk.aditi.ecom_seller.network.categories.Category_Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpAddBusinessTypeActivity extends AppCompatActivity {


    private final Set<String> ids = new ArraySet<>();
    private final List<ResultItem> list = new ArrayList<>();
    private final int ALL_API_COUNT = 1;
    String s;
    private int API_COUNT;
    private Select_Business_Type_Adapter adapter;
    private ActivitySignUpAddBusinessTypeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySignUpAddBusinessTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String no = getIntent().getStringExtra("no");
        String password = getIntent().getStringExtra("password");

        binding.tvNext.setOnClickListener(v -> {

            if (s != null && !s.isEmpty()) {
                Intent intent = new Intent(this, SignUpAddBusinessDetailsActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("no", no);
                intent.putExtra("password", password);
                intent.putExtra("ids", s);
                startActivity(intent);

            }
            else {
                Snackbar.make(findViewById(android.R.id.content),
                        "Please Select atleast one Category",
                        Snackbar.LENGTH_SHORT).show();
            }


        });


    }


    private void getCategories() {

        binding.Main.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(Category_Request.class)
                .getCategory()
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.Main.setVisibility(View.VISIBLE);
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                        }
                        if (response != null) {
                            if (response.isSuccessful()) {
                                JsonObject object = response.body().getAsJsonObject();
                                int status = object.get("status").getAsInt();
                                if (status == 1) {
                                    CategoriesResponse authentication = new Gson().fromJson(object, CategoriesResponse.class);
                                    list.addAll(authentication.getResult());
                                    adapter.notifyDataSetChanged();
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        ++API_COUNT;
                        if (API_COUNT == ALL_API_COUNT) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                        }
                    }
                });
    }


    private void init() {

        getCategories();

        binding.rvBusinessType.setLayoutManager(new GridLayoutManager(this, 3));


        adapter = new Select_Business_Type_Adapter(list, (position, isChecked) -> {
            String id = list.get(position).getId();
            if (isChecked)
                ids.add(id);
            else {
                ids.remove(id);
            }
            getIds();

        });
        binding.rvBusinessType.setAdapter(adapter);


        binding.ivBack.setOnClickListener(v -> {
            finish();
        });


    }

    void getIds() {
        s = "";
        for (String id : ids) {
            s = s + "," + id;
        }
        if (s.length() > 0) {
            s = s.substring(1);
        }
        Log.i("sdhcvsd", "onCreate: " + s);
    }
}