package lk.aditi.ecom_seller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;

public class Search_Product_Adapter extends RecyclerView.Adapter<Search_Product_Adapter.Search_Product_Adapter_View> {


    @Override
    public Search_Product_Adapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_product, parent, false);
        return new Search_Product_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder( Search_Product_Adapter.Search_Product_Adapter_View holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class Search_Product_Adapter_View extends RecyclerView.ViewHolder {
        public Search_Product_Adapter_View( View itemView) {
            super(itemView);
        }
    }
}
