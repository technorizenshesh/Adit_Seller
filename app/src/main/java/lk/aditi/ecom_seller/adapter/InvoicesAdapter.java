package lk.aditi.ecom_seller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;

public class InvoicesAdapter extends RecyclerView.Adapter<InvoicesAdapter.InvoicesAdapter_View> {

    @Override
    public InvoicesAdapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.invoices, parent, false);
        return new InvoicesAdapter_View(view);
    }

    @Override
    public void onBindViewHolder( InvoicesAdapter.InvoicesAdapter_View holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class InvoicesAdapter_View extends RecyclerView.ViewHolder {
        public InvoicesAdapter_View( View itemView) {
            super(itemView);
        }
    }
}
