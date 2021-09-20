package lk.aditi.ecom_seller.ui.fragments.product;

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

import lk.aditi.ecom_seller.adapter.MyProductAdapter;
import lk.aditi.ecom_seller.ui.activity.AddProductActivity;

public class ProductFragment extends Fragment {

    private RecyclerView rv_products;
    private View view;
    private ImageView iv_add_product;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_product, container, false);

    rv_products=view.findViewById(R.id.rv_products);
    iv_add_product=view.findViewById(R.id.iv_add_product);
        rv_products.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_products.setAdapter(new MyProductAdapter());

        iv_add_product.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AddProductActivity.class));
        });

        return view;
    }
}