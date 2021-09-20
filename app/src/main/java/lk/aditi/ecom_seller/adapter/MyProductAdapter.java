package lk.aditi.ecom_seller.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.ui.activity.EditProductActivity;
import lk.aditi.ecom_seller.ui.activity.UpdateStatusActivity;

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.MyProductAdapter_View> {


    @Override
    public MyProductAdapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.my_product, parent, false);
        return new MyProductAdapter_View(view);
    }

    @Override
    public void onBindViewHolder( MyProductAdapter.MyProductAdapter_View holder, int position) {

        holder.tv_Edit.setOnClickListener(v -> {
            holder.cv_main.getContext().startActivity(new Intent( holder.cv_main.getContext(), EditProductActivity.class));
        });

        holder.cv_main.setOnClickListener(v -> {
            holder.cv_main.getContext().startActivity(new Intent( holder.cv_main.getContext(), UpdateStatusActivity.class));
        });
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyProductAdapter_View extends RecyclerView.ViewHolder {

        private CardView cv_main;
        private TextView tv_Edit;
        public MyProductAdapter_View( View itemView) {
            super(itemView);
            cv_main=itemView.findViewById(R.id.cv_main);
            tv_Edit=itemView.findViewById(R.id.tv_Edit);
        }
    }
}
