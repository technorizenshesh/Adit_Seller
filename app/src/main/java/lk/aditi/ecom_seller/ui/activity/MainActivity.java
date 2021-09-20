package lk.aditi.ecom_seller.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.aditi.ecom_seller.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import lk.aditi.ecom_seller.ui.fragments.home.HomeFragment;
import lk.aditi.ecom_seller.ui.fragments.product.ProductFragment;
import lk.aditi.ecom_seller.ui.fragments.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout=findViewById(R.id.frame);
        bottomNavigationView=findViewById(R.id.bottom);


        replace(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        replace(new HomeFragment(),"ExploreFragment");
                        return true;
                    case R.id.product:
                        replace(new ProductFragment(),"CartFragment");
                        return true;

                    case R.id.profile:
                        replace(new ProfileFragment(),"ProfileFragment");
                        return true;

                }
                return false;
            }
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

}