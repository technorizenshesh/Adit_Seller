package lk.aditi.ecom_seller.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.aditi.ecom_seller.R;
import com.aditi.ecom_seller.databinding.ActivityEditProductBinding;

public class EditProductActivity extends AppCompatActivity {
     private ActivityEditProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditProductBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());

       init();
    }

    private void init(){
        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
        binding.tvSubmit.setOnClickListener(v -> {
            finish();
        });



    }
}