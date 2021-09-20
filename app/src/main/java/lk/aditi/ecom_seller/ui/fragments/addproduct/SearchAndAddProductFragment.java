package lk.aditi.ecom_seller.ui.fragments.addproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.adapter.Search_Product_Adapter;
import lk.aditi.ecom_seller.ui.activity.AddProductActivity;


public class SearchAndAddProductFragment extends Fragment {

    private RecyclerView rv_search_product;
    private View view;
    private TextView tv_Submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search_and_add_product, container, false);

        rv_search_product=view.findViewById(R.id.rv_search_product);
        tv_Submit=view.findViewById(R.id.tv_Submit);

        tv_Submit.setOnClickListener(v -> {
            ((AddProductActivity)getActivity()).finish();
        });
        rv_search_product.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_search_product.setAdapter(new Search_Product_Adapter());
        return view;
    }
}