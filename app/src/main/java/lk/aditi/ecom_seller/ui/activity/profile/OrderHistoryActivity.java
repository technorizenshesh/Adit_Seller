package lk.aditi.ecom_seller.ui.activity.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.aditi.ecom_seller.R;
import com.aditi.ecom_seller.databinding.ActivityOrderHistoryBinding;

import lk.aditi.ecom_seller.adapter.OrderHistoryAdapter;

public class OrderHistoryActivity extends AppCompatActivity {

    private ActivityOrderHistoryBinding  binding;
    private RecyclerView rv_orderhistory;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rv_orderhistory=findViewById(R.id.rv_orderhistory);
        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(v -> {
            finish();
        });

        rv_orderhistory.setLayoutManager(new LinearLayoutManager(this));
        rv_orderhistory.setAdapter(new OrderHistoryAdapter());
    }
}