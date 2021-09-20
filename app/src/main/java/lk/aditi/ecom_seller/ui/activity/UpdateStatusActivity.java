package lk.aditi.ecom_seller.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.aditi.ecom_seller.R;

import java.util.ArrayList;
import java.util.List;

import lk.aditi.ecom_seller.adapter.TimeLineAdapter;
import lk.aditi.ecom_seller.models.TimeLineModel;

public class UpdateStatusActivity extends AppCompatActivity {

    private RecyclerView rv_TrackOrder;
    private TimeLineAdapter timeLineAdapter;
    private List<TimeLineModel> timeLineList = new ArrayList<>();
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);


        rv_TrackOrder = findViewById(R.id.rv_TrackOrder);
        iv_back=findViewById(R.id.iv_back);

        iv_back.setOnClickListener(v -> {
            finish();
        });

        rv_TrackOrder.setLayoutManager(new LinearLayoutManager(this));
        timeLineAdapter = new TimeLineAdapter(timeLineList);
        rv_TrackOrder.setAdapter(timeLineAdapter);
        String status="Ready for Pickup";
        adddata(status);
    }

    private void adddata(String status) {

        switch (status) {
            case "Order Signed":
                timeLineList.add(new TimeLineModel("Order Signed", "Lagos State, Nigeria", 1,"20/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Order Processed", "Lagos State, Nigeria", 0,"20/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Ready for Pickup", "Lagos State, Nigeria", 0,"20/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("In Transit", "Lagos State, Nigeria", 0,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Delivered", "Lagos State, Nigeria", 0,"21/18","10:00 AM"));
                break;

            case "Order Processed":
                timeLineList.add(new TimeLineModel("Order Signed", "Lagos State, Nigeria", 1,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Order Processed", "Lagos State, Nigeria", 1,"20/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Ready for Pickup", "Lagos State, Nigeria", 0,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("In Transit", "Lagos State, Nigeria", 0,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Delivered", "Lagos State, Nigeria", 0,"21/18","10:00 AM"));
                break;

            case "Ready for Pickup":
                timeLineList.add(new TimeLineModel("Order Signed", "Lagos State, Nigeria", 1,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Order Processed", "Lagos State, Nigeria", 1,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Ready for Pickup", "Lagos State, Nigeria", 1,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("In Transit", "Lagos State, Nigeria", 0,"21/18","10:00 AM"));
                timeLineList.add(new TimeLineModel("Delivered", "Lagos State, Nigeria", 0,"21/18","10:00 AM"));
                break;

            case "In Transit":
                timeLineList.add(new TimeLineModel("Order Signed", "Lagos State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("Order Processed", "Lagos State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("Ready for Pickup", "Lagos State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("In Transit", "Edo State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("Delivered", "Edo State, Nigeria", 0,"",""));
                break;

            case "Delivered":
                timeLineList.add(new TimeLineModel("Order Signed", "Lagos State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("Order Processed", "Lagos State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("Ready for Pickup", "Lagos State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("In Transit", "Lagos State, Nigeria", 1,"",""));
                timeLineList.add(new TimeLineModel("Delivered", "Edo State, Nigeria", 1,"",""));
                break;
        }


    }


}