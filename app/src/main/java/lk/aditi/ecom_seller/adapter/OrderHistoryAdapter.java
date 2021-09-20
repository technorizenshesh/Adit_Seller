package lk.aditi.ecom_seller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.custom_dialog.Custom_Order_Status_Dialog;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryAdapter_View> {

    @Override
    public OrderHistoryAdapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_history, parent, false);
        return new OrderHistoryAdapter_View(view);    }

    @Override
    public void onBindViewHolder( OrderHistoryAdapter.OrderHistoryAdapter_View holder, int position) {

        Custom_Order_Status_Dialog dialog =new Custom_Order_Status_Dialog(holder.ll_main.getContext());
        holder.ll_main.setOnClickListener(v -> {
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class OrderHistoryAdapter_View extends RecyclerView.ViewHolder {
        private LinearLayout ll_main;
        public OrderHistoryAdapter_View( View itemView) {
            super(itemView);
            ll_main=itemView.findViewById(R.id.ll_main);
        }
    }

}
