package lk.aditi.ecom_seller.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.ui.fragments.addproduct.AddCustomProductFragment;
import lk.aditi.ecom_seller.ui.fragments.addproduct.SearchAndAddProductFragment;

public class AddProductActivity extends AppCompatActivity {

    private ImageView iv_serach_add_custom_pro, iv_serach_add_pro;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        iv_serach_add_pro = findViewById(R.id.iv_serach_add_pro);
        iv_serach_add_custom_pro = findViewById(R.id.iv_serach_add_custom_pro);
        frameLayout = findViewById(R.id.frame);
        replace(new SearchAndAddProductFragment());


        iv_serach_add_custom_pro.setOnClickListener(v -> {
            replace(new AddCustomProductFragment(),"");
            iv_serach_add_pro.setImageResource(R.drawable.ic_process_blank);
            iv_serach_add_custom_pro.setImageResource(R.drawable.ic_process_filled1);

        });

        iv_serach_add_pro.setOnClickListener(v -> {
            replace(new SearchAndAddProductFragment(),"");
            iv_serach_add_custom_pro.setImageResource(R.drawable.ic_process_blank);
            iv_serach_add_pro.setImageResource(R.drawable.ic_process_filled1);

        });

    }

    public void replace(Fragment fragment, String tag){
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.addToBackStack(tag);
        ft.commit();
    }


    public void replace(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
         ft.commit();
    }

    public void back(){
        finish();
    }

}