package lk.aditi.ecom_seller.ui.activity.authentication.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aditi.ecom_seller.R;
import com.aditi.ecom_seller.databinding.ActivitySignUpBinding;
import com.google.android.material.snackbar.Snackbar;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import static lk.aditi.ecom_seller.utils.AppUtils.NUMBER_PATTERN;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private String called_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

        SetupUI();
        if (called_from != null && called_from.equalsIgnoreCase("add"))
            binding.ccp.registerPhoneNumberTextView(binding.etNo);
        binding.ccp.setCountryForPhoneCode(94);

    }

    private void SetupUI() {
        binding.tvSignUp.setOnClickListener(v -> {
            if (validate()) {

                Intent intent = new Intent(SignUpActivity.this, SignUpAddBusinessTypeActivity.class);
                intent.putExtra("name", binding.etName.getText().toString());
                intent.putExtra("email", binding.etEmail.getText().toString());
                intent.putExtra("no", binding.etNo.getText().toString());
                intent.putExtra("password", binding.etPassword.getText().toString());
                startActivity(intent);

            }


        });
    }


    private boolean validate() {
        if (TextUtils.isEmpty(binding.etName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.enter_name, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(binding.etNo.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.text_register_no, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.etNo.getText().toString().replace(" ", "").length() != 10) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.digitofno_10,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!NUMBER_PATTERN.matcher(binding.etNo.getText().toString().replace(" ", "")).matches()) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
//        else if (!AppUtils.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText().toString().replace(" ", "")).matches()
//        ) {
//            Snackbar.make(findViewById(android.R.id.content),
//                    R.string.text_register_correct_email,
//                    Snackbar.LENGTH_SHORT).show();
//            return false;
//        }
        else if (TextUtils.isEmpty(binding.etPassword.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.enter_pass, Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.etPassword.getText().toString().replace(" ", "").length() <= 5) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_password,
                    Snackbar.LENGTH_SHORT).show();
            return false;

        } else {
            return true;
        }
    }

}