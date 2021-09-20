package lk.aditi.ecom_seller.custom_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aditi.ecom_seller.R;

import java.util.List;

public class CustomDialogOrderUpdate extends Dialog implements android.view.View.OnClickListener {

        public Activity c;
        public Dialog d;
        private TextView tv_submit_data;
        private ImageView iv_cross;

        public CustomDialogOrderUpdate(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_dialog_order_update);
            tv_submit_data = (TextView) findViewById(R.id.tv_submit_data);
            iv_cross = (ImageView) findViewById(R.id.iv_cross);

            tv_submit_data.setOnClickListener(this);
            iv_cross.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_submit_data:
                   c.finish();

                    break; case R.id.iv_cross:
                   dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }
    }

