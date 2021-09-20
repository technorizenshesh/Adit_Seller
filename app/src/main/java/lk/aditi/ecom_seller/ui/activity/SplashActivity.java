package lk.aditi.ecom_seller.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.models.signup.RegisterResponse;
import lk.aditi.ecom_seller.ui.activity.authentication.SignInActivity;
import lk.aditi.ecom_seller.utils.SharePrefrenceConstraint;
import lk.aditi.ecom_seller.utils.SharedPrefsManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            RegisterResponse model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.seller, RegisterResponse.class);

            if (model!=null){
                startActivity(new Intent(this, MainActivity.class));

            }else {
                startActivity(new Intent(this, SignInActivity.class));

            }
        },3000);

    }

}