package lk.aditi.ecom_seller.ui.fragments.addproduct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.ui.activity.AddProductActivity;


public class AddCustomProductFragment extends Fragment {

    private View view;
    private TextView tv_Submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_custom_product, container, false);

        tv_Submit=view.findViewById(R.id.tv_Submit);

        tv_Submit.setOnClickListener(v -> {
            ((AddProductActivity)getActivity()).finish();
        });

        return view;
    }
}