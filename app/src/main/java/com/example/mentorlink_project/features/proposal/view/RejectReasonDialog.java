package com.example.mentorlink_project.features.proposal.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import com.example.mentorlink_project.R;

public class RejectReasonDialog extends Dialog {
    public interface OnReasonSubmittedListener {
        void onReasonSubmitted(String reason);
    }

    public RejectReasonDialog(Context context, OnReasonSubmittedListener listener) {
        super(context);
        setContentView(R.layout.dialog_reject_reason);

        EditText edtReason = findViewById(R.id.edtReason);
        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnCancel.setOnClickListener(v -> dismiss());

        btnSubmit.setOnClickListener(v -> {
            String reason = edtReason.getText().toString().trim();
            if (!reason.isEmpty()) {
                listener.onReasonSubmitted(reason);
                dismiss();
            } else {
                edtReason.setError("Vui lòng nhập lý do từ chối");
            }
        });
    }
}
