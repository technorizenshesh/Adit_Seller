package lk.aditi.ecom_seller.ui.activity.authentication.signup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aditi.ecom_seller.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import lk.aditi.ecom_seller.models.categories.ResultItem;
import lk.aditi.ecom_seller.utils.AppUtils;

public class Select_Business_Type_Adapter extends RecyclerView.Adapter<Select_Business_Type_Adapter.Select_Business_Type_Adapter_View>{

    private List<ResultItem> list;
    private Callback  callback;

    public Select_Business_Type_Adapter(List<ResultItem> list,Callback  callback) {
        this.list=list;
        this.callback=callback;
    }

    @Override
    public Select_Business_Type_Adapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.business_type, parent, false);
        view.getLayoutParams().width = (int) ((AppUtils.getScreenWidth(view.getContext()) - AppUtils.dpToPx(view.getContext(),16) )/ 3); /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS

        return new Select_Business_Type_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder( Select_Business_Type_Adapter.Select_Business_Type_Adapter_View holder, int position) {

        holder.tv_Name.setText(list.get(position).getCategoryName());
        Picasso.get().load(list.get(position).getImage()).placeholder(R.drawable.user_placeholder).into(holder.iv_Image);

        holder.cv_main.setOnClickListener(v -> callback.selectedposition(position,holder.cv_main.isChecked()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Select_Business_Type_Adapter_View extends RecyclerView.ViewHolder {
        private ImageView iv_Image;
        private TextView tv_Name;
        private CheckBox cv_main;
        public Select_Business_Type_Adapter_View( View itemView) {
            super(itemView);

            iv_Image=itemView.findViewById(R.id.iv_Image);
            tv_Name=itemView.findViewById(R.id.tv_Name);
            cv_main=itemView.findViewById(R.id.cv_main);
        }
    }

    public interface Callback{
        void selectedposition(int position,boolean isChecked);
    }
}
