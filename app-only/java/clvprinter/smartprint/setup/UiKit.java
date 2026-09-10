package com.clvprinter.smartprint.setup;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.clvprinter.smartprint.R;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiKit.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÃ€\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tJ>\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0012J\u0016\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\tJ\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\tJ,\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 J&\u0010\"\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\tÂ¨\u0006%"}, d2 = {"Lcom/rapprinter/smartprint/setup/UiKit;", "", "<init>", "()V", "column", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "spacingDp", "", "text", "Landroid/widget/TextView;", "value", "", "sizeSp", "", "color", "bold", "", "mono", "eyebrow", "", "spacer", "Landroid/view/View;", "heightDp", "panel", "paddingDp", "button", "Landroid/widget/Button;", "label", "primary", "onClick", "Lkotlin/Function0;", "", "pill", "foreground", "backgroundColor", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UiKit {
    public static final UiKit INSTANCE = new UiKit();

    private UiKit() {
    }

    public static /* synthetic */ LinearLayout column$default(UiKit uiKit, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return uiKit.column(context, i);
    }

    public final LinearLayout column(Context context, int spacingDp) {
        Intrinsics.checkNotNullParameter(context, "context");
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        if (spacingDp > 0) {
            linearLayout.setShowDividers(2);
        }
        return linearLayout;
    }

    public final TextView text(Context context, CharSequence value, float sizeSp, int color, boolean bold, boolean mono) {
        Typeface create;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(value, "value");
        TextView textView = new TextView(context);
        textView.setText(value);
        textView.setTextSize(sizeSp);
        textView.setTextColor(color);
        textView.setIncludeFontPadding(false);
        textView.setLineSpacing(0.0f, 1.15f);
        if (mono && bold) {
            create = Typeface.create("monospace", 1);
        } else if (mono) {
            create = Typeface.create("monospace", 0);
        } else {
            create = bold ? Typeface.create("sans-serif-medium", 1) : Typeface.create("sans-serif", 0);
        }
        textView.setTypeface(create);
        return textView;
    }

    public final TextView eyebrow(Context context, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(value, "value");
        String upperCase = value.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        TextView text = text(context, upperCase, 11.0f, context.getColor(R.color.cobalt), true, true);
        text.setLetterSpacing(0.1f);
        return text;
    }

    public final View spacer(Context context, int heightDp) {
        Intrinsics.checkNotNullParameter(context, "context");
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, UiKitKt.dp(context, heightDp)));
        return view;
    }

    public static /* synthetic */ LinearLayout panel$default(UiKit uiKit, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 16;
        }
        return uiKit.panel(context, i);
    }

    public final LinearLayout panel(Context context, int paddingDp) {
        Intrinsics.checkNotNullParameter(context, "context");
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setBackground(context.getDrawable(R.drawable.bg_panel));
        linearLayout.setPadding(UiKitKt.dp(context, paddingDp), UiKitKt.dp(context, paddingDp), UiKitKt.dp(context, paddingDp), UiKitKt.dp(context, paddingDp));
        return linearLayout;
    }

    public final Button button(Context context, String label, boolean primary, final Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Button button = new Button(context);
        button.setText(label);
        button.setAllCaps(false);
        button.setTextSize(14.0f);
        button.setMinHeight(UiKitKt.dp(context, 48));
        button.setMinimumHeight(UiKitKt.dp(context, 48));
        button.setTextColor(context.getColor(primary ? R.color.paper_white : R.color.ink));
        button.setBackground(context.getDrawable(primary ? R.drawable.bg_primary_button : R.drawable.bg_secondary_button));
        button.setStateListAnimator(null);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.clvprinter.smartprint.setup.UiKit$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Function0.this.invoke();
            }
        });
        return button;
    }

    public final TextView pill(Context context, String label, int foreground, int backgroundColor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(label, "label");
        TextView text = text(context, label, 11.0f, foreground, true, true);
        text.setGravity(17);
        text.setPadding(UiKitKt.dp(context, 9), UiKitKt.dp(context, 5), UiKitKt.dp(context, 9), UiKitKt.dp(context, 5));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(UiKitKt.dp(context, 4));
        gradientDrawable.setColor(backgroundColor);
        text.setBackground(gradientDrawable);
        return text;
    }
}

