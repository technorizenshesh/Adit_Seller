package lk.aditi.ecom_seller.ui.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.custom_dialog.CustomDialogOrderDecline;
import lk.aditi.ecom_seller.custom_dialog.CustomDialogOrderUpdate;

public class Order_Detail_Activity extends AppCompatActivity {

    private RecyclerView rv_order_items_det;
    private TextView tv_update,tv_decline,tv_accept;
    private LinearLayout ll_detail;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        rv_order_items_det=findViewById(R.id.rv_order_items_det);
        tv_accept=findViewById(R.id.tv_accept);
        tv_decline=findViewById(R.id.tv_decline);
        tv_update=findViewById(R.id.tv_update);
        ll_detail=findViewById(R.id.ll_detail);
        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(v -> {
            finish();
        });

        tv_accept.setOnClickListener(v -> {
            finish();
        });


        CustomDialogOrderUpdate cdu=new CustomDialogOrderUpdate(this);
        CustomDialogOrderDecline cdd=new CustomDialogOrderDecline(this);

        tv_update.setOnClickListener(v -> {
            cdu.show();
        });
        tv_decline.setOnClickListener(v -> {
            cdd.show();
        });

        ll_detail.setOnClickListener(v -> {
            startActivity(new Intent(this,CustomerDetailActivity.class));
        });

        rv_order_items_det.setLayoutManager(new LinearLayoutManager(this));
        rv_order_items_det.setAdapter(new Order_Items_Adapter());
    }
}