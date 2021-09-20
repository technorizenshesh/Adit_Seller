package lk.aditi.ecom_seller.ui.activity.profile;

import static lk.aditi.ecom_seller.utils.AppUtils.NUMBER_PATTERN;

import android.Manifest;
import android.content.ContentValues;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.aditi.ecom_seller.R;
import com.aditi.ecom_seller.databinding.ActivityEditProfileBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lk.aditi.ecom_seller.models.signup.RegisterResponse;
import lk.aditi.ecom_seller.utils.SharePrefrenceConstraint;
import lk.aditi.ecom_seller.utils.SharedPrefsManager;

public class EditProfileActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    public static final int CAPTURE_IMAGE = 3;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 10;
    final int PERMISSION_ALL = 100;
    final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };
    private final Calendar selectedDateTime = Calendar.getInstance();
    private ActivityEditProfileBinding binding;
    private Bitmap bitmap;
    private int currentIndex = 0;
    private String called_from;


    private Uri uri;
    private String mStringLatitude,mStringLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        init();

        dataset();
        setUpDatePicker();

        Places.initialize(getApplicationContext(), getResources().getString(R.string.api_key));
        PlacesClient placesClient = Places.createClient(this);

        if (called_from != null && called_from.equalsIgnoreCase("add"))
            binding.ccp.registerPhoneNumberTextView(binding.etNo);
        binding.ccp.setCountryForPhoneCode(94);

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

        binding.tvSave.setOnClickListener(v -> {
            if (validate()) {
                finish();
            }
        });


        binding.ivCameraProfileEdit.setOnClickListener(v -> {
            if (!hasPermissions(EditProfileActivity.this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(EditProfileActivity.this, PERMISSIONS, PERMISSION_ALL);
            } else {
                String fileName = "new-photo-name.jpg";
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                uri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values
                );
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, CAPTURE_IMAGE);
            }

        });

        binding.llLocation.setOnClickListener(v -> {
            List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG);
             Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this);
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
        });

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

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                binding.ivProfile.setImageBitmap(bitmap);
            } else if (requestCode == CAPTURE_IMAGE) {
                currentIndex = 1;
                binding.ivProfile.setImageURI(uri);
            }
        }

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                if (place != null) {
                    LatLng latLng = place.getLatLng();
                     mStringLatitude = String.valueOf(latLng.latitude);
                      mStringLongitude = String.valueOf(latLng.longitude);
                    binding.etLocation.setText(place.getName());
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
        super.onActivityResult(requestCode, resultCode, data);
    }



    private void dataset() {

        RegisterResponse model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.seller, RegisterResponse.class);

        if (model != null) {
            binding.etName.setText(model.getResult().getUserName());
            binding.etEmail.setText(model.getResult().getEmail());
            binding.etNo.setText(model.getResult().getMobile());
            binding.etBusinessName.setText(model.getResult().getBusinessName());
            binding.etDeliveryInfo.setText(model.getResult().getDeliveryInfo());
            binding.etLocation.setText(model.getResult().getLocation());
            Picasso.get().load(model.getResult().getImage()).placeholder(R.drawable.user_placeholder).into(binding.ivProfile);

        }
    }

    private void setUpDatePicker() {
        MaterialDatePicker picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setTheme(R.style.ThemeMaterialCalendar)
                .build();

        binding.etDob.setOnClickListener(v -> {
            picker.show(getSupportFragmentManager(), "tag");
        });

        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                selectedDateTime.setTime(new Date(selection));
                binding.etDob.setText(new SimpleDateFormat("dd-MMM-yyyy").format(selectedDateTime.getTimeInMillis()));
            }
        });

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
        binding.ivProfile.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (!hasPermissions(EditProfileActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(EditProfileActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

                }

            }
        });

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
//        if (currentIndex == 0) {
//            Snackbar.make(findViewById(android.R.id.content), "Please  select image", Snackbar.LENGTH_SHORT).show();
//            return false;
//        } else
        if (TextUtils.isEmpty(binding.etName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.enter_name, Snackbar.LENGTH_SHORT).show();
            return false;
        }
//        else if (TextUtils.isEmpty(binding.etEmail.getText().toString().replace(" ", ""))) {
//            Snackbar.make(findViewById(android.R.id.content), R.string.enter_email, Snackbar.LENGTH_SHORT).show();
//            return false;
//        } else if (!AppUtils.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText().toString().replace(" ", "")).matches()
//        ) {
//            Snackbar.make(findViewById(android.R.id.content),
//                    R.string.text_register_correct_email,
//                    Snackbar.LENGTH_SHORT).show();
//            return false;
//        }
        else if (TextUtils.isEmpty(binding.etNo.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.text_register_no, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etNo.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.text_register_no, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.etNo.getText().toString().replace(" ", "").length() != 10) {
            Snackbar.make(findViewById(android.R.id.content), R.string.digitofno_10, Snackbar.LENGTH_SHORT).show();
            return false;

        } else if (!NUMBER_PATTERN.matcher(binding.etNo.getText().toString().replace(" ", "")).matches()) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etBusinessName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_busi,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etLocation.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_loc,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


}

