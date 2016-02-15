package com.mrussek.edittextleak;
import android.content.Context;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.reflect.Field;
import android.content.res.Resources;
public class __fresh__EditTextWrapper extends EditText {
    public __fresh__EditTextWrapper(Context context) {
        super(context, null);
    }
    public __fresh__EditTextWrapper(Context context, AttributeSet attrs) {
        super(context, attrs, Resources.getSystem().getIdentifier("editTextStyle", "attr", "android"));
    }
    public __fresh__EditTextWrapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
     public Parcelable onSaveInstanceState() {
        Class<TextView> c = TextView.class;
        try {
            Field fieldMText = c.getDeclaredField("mText");
            fieldMText.setAccessible(true);
            CharSequence text = (CharSequence) fieldMText.get(this);
            if (text instanceof Spanned) {
                setText(new SpannableStringBuilder(text));
            }
        } catch (Exception e) {
 // ignore
    }
        return super.onSaveInstanceState();
    }
}
