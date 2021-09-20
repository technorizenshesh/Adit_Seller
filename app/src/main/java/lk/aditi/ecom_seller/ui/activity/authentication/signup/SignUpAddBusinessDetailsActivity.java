package lk.aditi.ecom_seller.ui.activity.authentication.signup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.aditi.ecom_seller.R;
import com.aditi.ecom_seller.databinding.ActivitySignUpAddBusinessDetailsBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import lk.aditi.ecom_seller.models.ResponseAuthError;
import lk.aditi.ecom_seller.models.signup.RegisterResponse;
import lk.aditi.ecom_seller.network.NetworkConstraint;
import lk.aditi.ecom_seller.network.RequestAuthentication;
import lk.aditi.ecom_seller.network.RetrofitClient;
import lk.aditi.ecom_seller.ui.activity.authentication.OtpVerificationActivity;
import lk.aditi.ecom_seller.utils.RealPathUtil;
import lk.aditi.ecom_seller.utils.SharePrefrenceConstraint;
import lk.aditi.ecom_seller.utils.SharedPrefsManager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpAddBusinessDetailsActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 10;
    final int PERMISSION_ALL = 100;
    final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };
    private String mStringLatitude, mStringLongitude;
    private ActivitySignUpAddBusinessDetailsBinding binding;
    private Bitmap bitmap;
    private File file;
    private int currentIndex = 0;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySignUpAddBusinessDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Places.initialize(getApplicationContext(), getResources().getString(R.string.api_key));
        init();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.i("xzvcbc", "onActivityResult: " + 32);
            if (requestCode == PICK_IMAGE) {
                Log.i("xzvcbc", "onActivityResult: " + 327);
                Uri imageUri = data.getData();
                currentIndex = 1;
                String s = RealPathUtil.getRealPath(this, imageUri);
                file = new File(s);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                binding.ivImage.setImageBitmap(bitmap);
            }
        }

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                if (place != null) {
                    LatLng latLng = place.getLatLng();
                    mStringLatitude = String.valueOf(latLng.latitude);
                    mStringLongitude = String.valueOf(latLng.longitude);
                    binding.tvLocation.setText(place.getName());
                    Log.i("dfvdcdcvf", "onActivityResult: "+mStringLatitude);
                    Log.i("dfvdcdcvf", "onActivityResult: "+mStringLatitude);
                }

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("sfsfcfefd", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }


    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void init() {

        binding.llLocation.setOnClickListener(v -> {
            List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG);
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this);
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
        });

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

        binding.tvNext.setOnClickListener(v -> {
            if (validate()) {
                getregister();
            }

        });
        binding.ivAddImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (!hasPermissions(SignUpAddBusinessDetailsActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(SignUpAddBusinessDetailsActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

                }

            }
        });

    }

    private MultipartBody.Part getPartFromFile(String name, File file) {
        if (file == null)
            return null;
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part profile_image = MultipartBody.Part.createFormData(name, file.getPath(), requestBody);
        Log.i("sxzfdsf", "getPartFromFile: " + profile_image);
        return profile_image;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissionsList, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissionsList, grantResults);
        switch (requestCode) {
            case PERMISSION_ALL: {
                if (grantResults.length > 0) {
                    boolean b = true;
                    for (int per : grantResults) {
                        if (per == PackageManager.PERMISSION_DENIED) {
                            b = false;
                        }
                    }
                    if (b) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

                    }

                }
                return;
            }
        }
    }


    private boolean validate() {
        if (TextUtils.isEmpty(binding.etBusinessName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_busi,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.tvLocation.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_loc,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
//        else if (TextUtils.isEmpty(binding.etDeliveryInfo.getText().toString().replace(" ", ""))) {
//            Snackbar.make(findViewById(android.R.id.content), "Please enter delivery info", Snackbar.LENGTH_SHORT).show();
//            return false;
//        }
        else {
            return true;
        }
    }

    public void getregister() {

        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String no = getIntent().getStringExtra("no");
        String password = getIntent().getStringExtra("password");
        String ids = getIntent().getStringExtra("ids");

        Log.i("xscvdvcd", "getregister: " + name + email + no + ids);

        MultipartBody.Part pro_name = MultipartBody.Part.createFormData("user_name", name);
        MultipartBody.Part pro_email = MultipartBody.Part.createFormData("email", email);
        MultipartBody.Part pro_password = MultipartBody.Part.createFormData("password", password);
        MultipartBody.Part pro_no = MultipartBody.Part.createFormData("mobile", no);
        MultipartBody.Part register_id = MultipartBody.Part.createFormData("register_id", "");
        MultipartBody.Part category_id = MultipartBody.Part.createFormData("category_id", ids);
        MultipartBody.Part business_name = MultipartBody.Part.createFormData("business_name", binding.etBusinessName.getText().toString());
        MultipartBody.Part location = MultipartBody.Part.createFormData("location", binding.tvLocation.getText().toString());
        MultipartBody.Part type = MultipartBody.Part.createFormData("type", NetworkConstraint.type);
        MultipartBody.Part delivery_info = MultipartBody.Part.createFormData("delivery_info", binding.etDeliveryInfo.getText().toString());
        MultipartBody.Part image = getPartFromFile("image", file);

        Log.i("xsxvdgfv", "getregister: " + binding.etBusinessName.getText().toString());
         Log.i("xsxvdgfv", "getregister: " + file);
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(RequestAuthentication.class)
                .SignUp(pro_name, pro_email,
                        pro_password, pro_no
                        , register_id, category_id, business_name, location
                        , type, delivery_info, image)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);

                        Log.i("xsxvdgfv", "onResponse: " + response.toString());
                        Log.i("xsxvdgfv", "onResponse: " + response.body());

                        if (response != null) {
                            if (response.isSuccessful()) {

                                JsonObject object = response.body().getAsJsonObject();
                                int status = object.get("status").getAsInt();
                                if (status == 1) {
                                    SharedPrefsManager.getInstance().setObject(SharePrefrenceConstraint.seller, response.body());
                                    RegisterResponse model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.seller, RegisterResponse.class);
                                    Log.i("xsxvdgfv", "onResponse: " + model.toString());

                                    startActivity(new Intent(SignUpAddBusinessDetailsActivity.this, OtpVerificationActivity.class));

                                } else {

                                    ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);

                                    Snackbar.make(findViewById(android.R.id.content),
                                            authentication.getResult(),
                                            Snackbar.LENGTH_SHORT).show();
                                }


                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        Toast.makeText(SignUpAddBusinessDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("xsxvdgfv", "onFailure: " + t.getMessage());
                    }
                });

    }

}