package com.example.projeto_1_jeovana_;

import android.text.InputFilter;
import android.text.Spanned;

public class DecimalDigitsInputFilter implements InputFilter {

    private int digitsBeforeZero;
    private int digitsAfterZero;

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        this.digitsBeforeZero = digitsBeforeZero;
        this.digitsAfterZero = digitsAfterZero;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        StringBuilder builder = new StringBuilder(dest);
        builder.replace(dstart, dend, source.subSequence(start, end).toString());
        if (!builder.toString().matches(
                "^\\d{0," + digitsBeforeZero + "}\\.\\d{0," + digitsAfterZero + "}$")) {
            if (source.length() == 0)
                return dest.subSequence(dstart, dend);
            return "";
        }
        return null;
    }
}