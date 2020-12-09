package com.example.hw_a_4;

import android.app.Dialog;
import android.os.Bundle;

public interface OnClick {
    void openActivity(int position);

    void onDelete(int position);

    Dialog onCreateDialog(Bundle savedInstanceState);
}
