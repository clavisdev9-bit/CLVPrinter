package com.clvprinter.smartprint.setup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.clvprinter.smartprint.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: WorkbenchProgressView.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005Â¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0014R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u0006\u001a"}, d2 = {"Lcom/rapprinter/smartprint/setup/WorkbenchProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "progressPercent", "getProgressPercent", "()I", "setProgressPercent", "(I)V", "paint", "Landroid/graphics/Paint;", "bar", "Landroid/graphics/RectF;", "onMeasure", "", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WorkbenchProgressView extends View {
    private final RectF bar;
    private final Paint paint;
    private int progressPercent;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WorkbenchProgressView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ WorkbenchProgressView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkbenchProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.paint = new Paint(1);
        this.bar = new RectF();
    }

    public final int getProgressPercent() {
        return this.progressPercent;
    }

    public final void setProgressPercent(int value) {
        this.progressPercent = RangesKt.coerceIn(value, 0, 100);
        setContentDescription("Kemajuan pengiriman " + this.progressPercent + " persen");
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int resolveSize = View.resolveSize(UiKitKt.dp(context, 180), widthMeasureSpec);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        setMeasuredDimension(resolveSize, View.resolveSize(UiKitKt.dp(context2, 12), heightMeasureSpec));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        float inset = UiKitKt.dp(context, 1);
        this.bar.set(inset, inset, getWidth() - inset, getHeight() - inset);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(getContext().getColor(R.color.rule));
        RectF rectF = this.bar;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        float dp = UiKitKt.dp(context2, 2);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(rectF, dp, UiKitKt.dp(r6, 2), this.paint);
        this.bar.right = (((getWidth() - (2.0f * inset)) * this.progressPercent) / 100.0f) + inset;
        this.paint.setColor(getContext().getColor(R.color.cobalt));
        RectF rectF2 = this.bar;
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        float dp2 = UiKitKt.dp(context3, 2);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(rectF2, dp2, UiKitKt.dp(r6, 2), this.paint);
        this.paint.setStyle(Paint.Style.STROKE);
        Paint paint = this.paint;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        paint.setStrokeWidth(UiKitKt.dp(r4, 1));
        this.paint.setColor(getContext().getColor(R.color.ink));
        this.bar.set(inset, inset, getWidth() - inset, getHeight() - inset);
        RectF rectF3 = this.bar;
        Context context4 = getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
        float dp3 = UiKitKt.dp(context4, 2);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(rectF3, dp3, UiKitKt.dp(r4, 2), this.paint);
        this.paint.setStyle(Paint.Style.FILL);
    }
}

