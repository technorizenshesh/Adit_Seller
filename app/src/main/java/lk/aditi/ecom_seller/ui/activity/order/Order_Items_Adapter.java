package lk.aditi.ecom_seller.ui.activity.order;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;

public class Order_Items_Adapter extends RecyclerView.Adapter<Order_Items_Adapter.Order_Items_Adapter_View> {

    @Override
    public Order_Items_Adapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_item, parent, false);
        return new Order_Items_Adapter_View(view);    }

    @Override
    public void onBindViewHolder( Order_Items_Adapter.Order_Items_Adapter_View holder, int position) {

        holder.cv_main.setOnClickListener(v -> {
            holder.cv_main.getContext().startActivity(new Intent(holder.cv_main.getContext(),No_Order_Activity.class));
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class Order_Items_Adapter_View extends RecyclerView.ViewHolder {
      private CardView cv_main;
        public Order_Items_Adapter_View( View itemView) {
            super(itemView);
            cv_main=itemView.findViewById(R.id.cv_main);
        }
    }
}
