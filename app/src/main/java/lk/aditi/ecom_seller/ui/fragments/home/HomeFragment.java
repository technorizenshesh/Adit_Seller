package lk.aditi.ecom_seller.ui.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.adapter.Order_List_Adapter;
import lk.aditi.ecom_seller.ui.activity.AddProductActivity;


public class HomeFragment extends Fragment {

    private RecyclerView rv_Order;
    private ImageView iv_add_product;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        rv_Order=view.findViewById(R.id.rv_Order);
        iv_add_product=view.findViewById(R.id.iv_add_product);
        rv_Order.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_Order.setAdapter(new Order_List_Adapter());

        iv_add_product.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddProductActivity.class));
        });

        return view;
    }
}