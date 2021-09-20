package lk.aditi.ecom_seller.ui.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aditi.ecom_seller.R;
import com.aditi.ecom_seller.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import lk.aditi.ecom_seller.models.signup.RegisterResponse;
import lk.aditi.ecom_seller.ui.activity.authentication.SignInActivity;
import lk.aditi.ecom_seller.ui.activity.profile.EditProfileActivity;
import lk.aditi.ecom_seller.ui.activity.profile.InvocesActivity;
import lk.aditi.ecom_seller.ui.activity.profile.OrderHistoryActivity;
import lk.aditi.ecom_seller.utils.SharePrefrenceConstraint;
import lk.aditi.ecom_seller.utils.SharedPrefsManager;

public class ProfileFragment extends Fragment {


    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);


        binding.llEditProfile.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), EditProfileActivity.class));
        });

        binding.llOrderHistory.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), OrderHistoryActivity.class));
        });


        binding.llInvoces.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), InvocesActivity.class));
        });


        binding.llLogout.setOnClickListener(v -> {
            SharedPrefsManager.getInstance().clearPrefs();
            Intent intent=new Intent(getContext(), SignInActivity.class);
            startActivity(intent);
         });


        init();

        return binding.getRoot();
    }

    private void init() {
        RegisterResponse model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.seller, RegisterResponse.class);

        if (model != null) {
            binding.tvName.setText(model.getResult().getUserName());
            binding.tvNo.setText(model.getResult().getMobile());
            Picasso.get().load(model.getResult().getImage()).placeholder(R.drawable.user_placeholder).into(binding.cvImage);

        }
    }
}