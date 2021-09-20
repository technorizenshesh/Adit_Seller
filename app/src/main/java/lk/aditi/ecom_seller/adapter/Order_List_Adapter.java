package lk.aditi.ecom_seller.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;

import lk.aditi.ecom_seller.ui.activity.order.Order_Detail_Activity;

public class Order_List_Adapter extends RecyclerView.Adapter<Order_List_Adapter.Order_List_Adapter_View> {

    @Override
    public Order_List_Adapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_list, parent, false);
        return new Order_List_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder( Order_List_Adapter.Order_List_Adapter_View holder, int position) {

        holder.cv_order.setOnClickListener(v -> {
            holder.cv_order.getContext().startActivity(new Intent(holder.cv_order.getContext(), Order_Detail_Activity.class));
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class Order_List_Adapter_View extends RecyclerView.ViewHolder {
      private CardView cv_order;
        public Order_List_Adapter_View( View itemView) {
            super(itemView);
            cv_order=itemView.findViewById(R.id.cv_order);
        }
    }
}
