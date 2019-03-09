package com.gk.questionbank.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.gk.questionbank.R;
import com.gk.questionbank.callbacks.DialogClickListener;
import com.gk.questionbank.enums.RVClickType;
import com.gk.questionbank.view_model.Questions;

public class CommonDialogs {

    public static void showDialog(Context mContext, String message, final RVClickType clickType, final Questions model, final DialogClickListener<Questions> mListener){
        final Dialog mDialog=new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_common);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView txtMsg=mDialog.findViewById(R.id.txt_message);
        TextView txtYes=mDialog.findViewById(R.id.txt_yes);
        TextView txtNo=mDialog.findViewById(R.id.txt_no);

        txtMsg.setText(message);
        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null)
                    mListener.onDialogClick(model,clickType);
                mDialog.dismiss();
            }
        });

        txtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        mDialog.show();

    }
}
