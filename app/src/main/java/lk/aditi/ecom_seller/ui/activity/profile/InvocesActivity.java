package lk.aditi.ecom_seller.ui.activity.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.adapter.InvoicesAdapter;

public class InvocesActivity extends AppCompatActivity {

    private ImageView iv_back;

    private RecyclerView rv_Invoices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoces);

        rv_Invoices=findViewById(R.id.rv_Invoices);
        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(v -> {
            finish();
        });

        rv_Invoices.setLayoutManager(new LinearLayoutManager(this));
        rv_Invoices.setAdapter(new InvoicesAdapter() );
    }
}