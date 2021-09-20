package lk.aditi.ecom_seller.ui.activity.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.aditi.ecom_seller.R;
import com.aditi.ecom_seller.databinding.ActivityOtpVerificationBinding;
import com.google.android.material.snackbar.Snackbar;

import lk.aditi.ecom_seller.ui.activity.MainActivity;
import lk.aditi.ecom_seller.ui.activity.authentication.signup.SignUpActivity;

public class OtpVerificationActivity extends AppCompatActivity {
    private ActivityOtpVerificationBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

        SetUpUi();



    }

    private void SetUpUi(){

        binding.tvContinue.setOnClickListener(v -> {
            if (validate()){
//                binding.loaderLayout.loader.setVisibility(View.VISIBLE);
                startActivity(new Intent(this, MainActivity.class));

            }
        });

    }

    private boolean validate() {
        if (TextUtils.isEmpty(binding.pvOtp.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.enter_otp, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.pvOtp.getText().toString().replace(" ", "").length() != 6) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_6_digit_otp,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}