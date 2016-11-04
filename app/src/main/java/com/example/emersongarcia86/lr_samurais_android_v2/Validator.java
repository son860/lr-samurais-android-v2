package com.example.emersongarcia86.lr_samurais_android_v2;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by emersongarcia86 on 03/11/16.
 */

public abstract class Validator implements TextWatcher {
    private final EditText edtext_codigo;

    public Validator(EditText editText) {
        this.edtext_codigo = editText;
    }

    public abstract void validate(EditText editText, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = edtext_codigo.getText().toString();
        validate(edtext_codigo, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
}
