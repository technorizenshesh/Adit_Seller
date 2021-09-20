package lk.aditi.ecom_seller.custom_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aditi.ecom_seller.R;

public class Custom_Order_Status_Dialog extends Dialog implements android.view.View.OnClickListener{
    public Activity c;
    public Dialog d;
    private TextView tv_submit_data;
    private ImageView iv_cross;

    public Custom_Order_Status_Dialog(@NonNull Context context) {
        super(context);
    }

    public Custom_Order_Status_Dialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected Custom_Order_Status_Dialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order_status);
         iv_cross = (ImageView) findViewById(R.id.iv_cross);

        iv_cross.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           case R.id.iv_cross:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}

