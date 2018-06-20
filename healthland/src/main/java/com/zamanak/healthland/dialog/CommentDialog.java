package com.zamanak.healthland.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.WindowManager;
import com.zamanak.healthland.interfaces.CommentDialogListener;
import com.zamanak.landofhealth.R;

import butterknife.ButterKnife;

/**
 * Created by a.Raghibdoust on 11/5/2017.
 */

public class CommentDialog extends Dialog implements View.OnClickListener {


    AppCompatEditText edtInputComment;
    AppCompatButton btnRegisterComment;
    AppCompatButton btnCancelComment;
    TextInputLayout tilInputComment;

    private CommentDialogListener listener;

    public CommentDialogListener getListener() {
        return listener;
    }

    public void setListener(CommentDialogListener listener) {
        this.listener = listener;
    }

    public CommentDialog(@NonNull Context context) {

        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(1);
        View view = View.inflate(getContext(), R.layout.dialog_comment, null);
        setContentView(view);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = -1;
        getWindow().setAttributes(params);
        //ButterKnife.bind(this, view);
        setCancelable(false);

        edtInputComment = findViewById(R.id.edt_input_comment);
        btnRegisterComment = findViewById(R.id.btn_register_comment);
        btnCancelComment = findViewById(R.id.btn_cancel_comment);
        tilInputComment = findViewById(R.id.til_input_comment);

        btnRegisterComment.setOnClickListener(this);
        btnCancelComment.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btnRegisterComment.getId()) {
            String commentText = edtInputComment.getText().toString();
            if (commentText.isEmpty()) {
                tilInputComment.setError("نظری درج نشده است!!!");
                tilInputComment.setErrorEnabled(true);
            } else {
                listener.onCommentSuccess(commentText);
                tilInputComment.setErrorEnabled(false);
            }
        } else if (v.getId() == btnCancelComment.getId()) {
            listener.onCommentFail();

        }
    }
}
