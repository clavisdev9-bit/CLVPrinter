package com.clvprinter.smartprint.setup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.clvprinter.smartprint.R;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReceiptStatusView.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005Â¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020!H\u0014J\u0010\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020/H\u0014J\b\u00100\u001a\u00020\u0018H\u0002J\b\u00101\u001a\u00020*H\u0002J0\u00102\u001a\u00020*2\u0006\u0010.\u001a\u00020/2\u0006\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u000205H\u0002R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0012\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR$\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\u0018@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020!X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020!X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020!X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020!X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u00068"}, d2 = {"Lcom/rapprinter/smartprint/setup/ReceiptStatusView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "permissionReady", "getPermissionReady", "()Z", "setPermissionReady", "(Z)V", "printerReady", "getPrinterReady", "setPrinterReady", "serviceReady", "getServiceReady", "setServiceReady", "batteryReady", "getBatteryReady", "setBatteryReady", "", "profileLabel", "getProfileLabel", "()Ljava/lang/String;", "setProfileLabel", "(Ljava/lang/String;)V", "paint", "Landroid/graphics/Paint;", "ink", "", "paper", "cobalt", "muted", "rule", "success", "printerBody", "Landroid/graphics/RectF;", "onMeasure", "", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "headline", "updateDescription", "drawFittedText", "text", "x", "", "baseline", "maxWidth", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ReceiptStatusView extends View {
    private boolean batteryReady;
    private final int cobalt;
    private final int ink;
    private final int muted;
    private final Paint paint;
    private final int paper;
    private boolean permissionReady;
    private final RectF printerBody;
    private boolean printerReady;
    private String profileLabel;
    private final int rule;
    private boolean serviceReady;
    private final int success;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ReceiptStatusView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ ReceiptStatusView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReceiptStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.profileLabel = "384 DOT â€¢ 203 DPI";
        this.paint = new Paint(1);
        this.ink = context.getColor(R.color.ink);
        this.paper = context.getColor(R.color.paper_white);
        this.cobalt = context.getColor(R.color.cobalt);
        this.muted = context.getColor(R.color.muted);
        this.rule = context.getColor(R.color.rule);
        this.success = context.getColor(R.color.success);
        this.printerBody = new RectF();
        setImportantForAccessibility(1);
        updateDescription();
    }

    public final boolean getPermissionReady() {
        return this.permissionReady;
    }

    public final void setPermissionReady(boolean value) {
        this.permissionReady = value;
        updateDescription();
        invalidate();
    }

    public final boolean getPrinterReady() {
        return this.printerReady;
    }

    public final void setPrinterReady(boolean value) {
        this.printerReady = value;
        updateDescription();
        invalidate();
    }

    public final boolean getServiceReady() {
        return this.serviceReady;
    }

    public final void setServiceReady(boolean value) {
        this.serviceReady = value;
        updateDescription();
        invalidate();
    }

    public final boolean getBatteryReady() {
        return this.batteryReady;
    }

    public final void setBatteryReady(boolean value) {
        this.batteryReady = value;
        updateDescription();
        invalidate();
    }

    public final String getProfileLabel() {
        return this.profileLabel;
    }

    public final void setProfileLabel(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.profileLabel = value;
        updateDescription();
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int desiredWidth = UiKitKt.dp(context, 320);
        int width = View.resolveSize(desiredWidth, widthMeasureSpec);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        float receiptTop = UiKitKt.dp(context2, 12);
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        float sp = UiKitKt.sp(context3, 9.0f) + receiptTop;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float eyebrowBaseline = sp + UiKitKt.dp(r6, 10);
        Context context4 = getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
        float sp2 = UiKitKt.sp(context4, 18.0f) + eyebrowBaseline;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float headlineBaseline = sp2 + UiKitKt.dp(r7, 8);
        Context context5 = getContext();
        Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
        float sp3 = UiKitKt.sp(context5, 10.0f) + headlineBaseline;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float profileBaseline = sp3 + UiKitKt.dp(r8, 9);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float receiptBottom = UiKitKt.dp(r8, 18) + profileBaseline;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float printerBottom = UiKitKt.dp(r9, 32) + receiptBottom;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float railY = UiKitKt.dp(r10, 31) + printerBottom;
        Context context6 = getContext();
        Intrinsics.checkNotNullExpressionValue(context6, "getContext(...)");
        float sp4 = UiKitKt.sp(context6, 9.0f) + railY;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float labelBaseline = sp4 + UiKitKt.dp(r11, 20);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        int desiredHeight = (int) Math.ceil(UiKitKt.dp(r11, 20) + labelBaseline);
        setMeasuredDimension(width, View.resolveSize(desiredHeight, heightMeasureSpec));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        float railY;
        String[] labels;
        float f;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        float width = getWidth();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        float left = UiKitKt.dp(context, 10);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float right = width - UiKitKt.dp(r2, 10);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        float receiptTop = UiKitKt.dp(context2, 12);
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        float sp = UiKitKt.sp(context3, 9.0f) + receiptTop;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float eyebrowBaseline = sp + UiKitKt.dp(r4, 10);
        Context context4 = getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
        float sp2 = UiKitKt.sp(context4, 18.0f) + eyebrowBaseline;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float headlineBaseline = sp2 + UiKitKt.dp(r4, 8);
        Context context5 = getContext();
        Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
        float sp3 = headlineBaseline + UiKitKt.sp(context5, 10.0f);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float profileBaseline = sp3 + UiKitKt.dp(r6, 9);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float receiptBottom = profileBaseline + UiKitKt.dp(r2, 18);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float printerTop = UiKitKt.dp(r2, 31) + receiptTop;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float printerBottom = receiptBottom + UiKitKt.dp(r4, 32);
        this.paint.setColor(this.ink);
        this.printerBody.set(left, printerTop, right, printerBottom);
        RectF rectF = this.printerBody;
        Context context6 = getContext();
        Intrinsics.checkNotNullExpressionValue(context6, "getContext(...)");
        float dp = UiKitKt.dp(context6, 7);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(rectF, dp, UiKitKt.dp(r15, 7), this.paint);
        this.paint.setColor(Color.rgb(38, 55, 78));
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float dp2 = UiKitKt.dp(r6, 12) + printerTop;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRect(UiKitKt.dp(r5, 10) + left, dp2, right - UiKitKt.dp(r6, 10), printerBottom - UiKitKt.dp(r13, 17), this.paint);
        float receiptLeft = width * 0.1f;
        float receiptRight = width * 0.9f;
        this.paint.setColor(this.paper);
        canvas.drawRect(receiptLeft, receiptTop, receiptRight, receiptBottom, this.paint);
        this.paint.setColor(this.rule);
        Paint paint = this.paint;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        paint.setStrokeWidth(UiKitKt.dp(r2, 1));
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        int i3 = 6;
        float dashX = receiptLeft + UiKitKt.dp(r1, 6);
        while (true) {
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            if (dashX >= receiptRight - UiKitKt.dp(r4, i3)) {
                break;
            }
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            float dashX2 = UiKitKt.dp(r2, 4) + dashX;
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            float dashX3 = dashX;
            canvas.drawLine(dashX3, receiptBottom - UiKitKt.dp(r4, 9), dashX2, receiptBottom - UiKitKt.dp(r6, 9), this.paint);
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            dashX = dashX3 + UiKitKt.dp(r1, 8);
            i3 = 6;
        }
        this.paint.setTypeface(Typeface.create("monospace", 1));
        Paint paint2 = this.paint;
        Context context7 = getContext();
        Intrinsics.checkNotNullExpressionValue(context7, "getContext(...)");
        paint2.setTextSize(UiKitKt.sp(context7, 9.0f));
        this.paint.setColor(this.cobalt);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        drawFittedText(canvas, "STATUS / RAPPRINT", receiptLeft + UiKitKt.dp(r1, 13), eyebrowBaseline, (receiptRight - receiptLeft) - UiKitKt.dp(r5, 26));
        this.paint.setTypeface(Typeface.create("sans-serif", 1));
        Paint paint3 = this.paint;
        Context context8 = getContext();
        Intrinsics.checkNotNullExpressionValue(context8, "getContext(...)");
        paint3.setTextSize(UiKitKt.sp(context8, 18.0f));
        this.paint.setColor(this.ink);
        String headline = headline();
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float dp3 = receiptLeft + UiKitKt.dp(r1, 13);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        drawFittedText(canvas, headline, dp3, headlineBaseline, (receiptRight - receiptLeft) - UiKitKt.dp(r1, 26));
        this.paint.setTypeface(Typeface.create("monospace", 0));
        Paint paint4 = this.paint;
        Context context9 = getContext();
        Intrinsics.checkNotNullExpressionValue(context9, "getContext(...)");
        paint4.setTextSize(UiKitKt.sp(context9, 10.0f));
        this.paint.setColor(this.muted);
        String upperCase = this.profileLabel.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        drawFittedText(canvas, "58 MM â€¢ " + upperCase, receiptLeft + UiKitKt.dp(r3, 13), profileBaseline, (receiptRight - receiptLeft) - UiKitKt.dp(r5, 26));
        boolean allReady = this.permissionReady && this.printerReady && this.serviceReady && this.batteryReady;
        this.paint.setColor(allReady ? this.success : this.cobalt);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float f2 = (printerTop + printerBottom) / 2.0f;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawCircle(right - UiKitKt.dp(r2, 22), f2, UiKitKt.dp(r4, 6), this.paint);
        int i4 = 1;
        boolean[] states = {this.permissionReady, this.printerReady, this.serviceReady, this.batteryReady};
        Context context10 = getContext();
        Intrinsics.checkNotNullExpressionValue(context10, "getContext(...)");
        float railLeft = UiKitKt.dp(context10, 34);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float railRight = width - UiKitKt.dp(r4, 34);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float railY2 = UiKitKt.dp(r4, 31) + printerBottom;
        Paint paint5 = this.paint;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        paint5.setStrokeWidth(UiKitKt.dp(r14, 2));
        this.paint.setColor(this.rule);
        float railY3 = railY2;
        String[] labels2 = {"IZIN", "PRINTER", "LAYANAN", "LATAR"};
        canvas.drawLine(railLeft, railY3, railRight, railY2, this.paint);
        Canvas canvas2 = canvas;
        String[] strArr = labels2;
        int length = strArr.length;
        int i5 = 0;
        int i6 = 0;
        while (i6 < length) {
            String str = strArr[i6];
            int i7 = i5 + 1;
            String[] labels3 = labels2;
            String[] labels4 = strArr;
            float length2 = railLeft + (((railRight - railLeft) * i5) / (labels3.length - 1));
            int i8 = i6;
            Paint paint6 = this.paint;
            if (states[i5]) {
                i = length;
                i2 = this.success;
            } else {
                i = length;
                i2 = this.paper;
            }
            paint6.setColor(i2);
            this.paint.setStyle(Paint.Style.FILL);
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            canvas2.drawCircle(length2, railY3, UiKitKt.dp(r3, 10), this.paint);
            this.paint.setStyle(Paint.Style.STROKE);
            Paint paint7 = this.paint;
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            int i9 = i5;
            paint7.setStrokeWidth(UiKitKt.dp(r4, 2));
            this.paint.setColor(states[i9] ? this.success : this.muted);
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            canvas2.drawCircle(length2, railY3, UiKitKt.dp(r3, 10), this.paint);
            this.paint.setStyle(Paint.Style.FILL);
            if (!states[i9]) {
                railY = railY3;
                labels = labels3;
                f = length2;
            } else {
                this.paint.setColor(-1);
                Paint paint8 = this.paint;
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                labels = labels3;
                paint8.setStrokeWidth(UiKitKt.dp(r5, 2));
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                float dp4 = length2 - UiKitKt.dp(r5, i4);
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                f = length2;
                canvas.drawLine(length2 - UiKitKt.dp(r3, 4), railY3, dp4, UiKitKt.dp(r5, 3) + railY3, this.paint);
                railY = railY3;
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                float dp5 = f - UiKitKt.dp(r0, 1);
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                float dp6 = railY + UiKitKt.dp(r0, 3);
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                float dp7 = f + UiKitKt.dp(r0, 5);
                Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
                canvas.drawLine(dp5, dp6, dp7, railY - UiKitKt.dp(r0, 4), this.paint);
                canvas2 = canvas;
            }
            this.paint.setTypeface(Typeface.create("monospace", 1));
            Paint paint9 = this.paint;
            Context context11 = getContext();
            Intrinsics.checkNotNullExpressionValue(context11, "getContext(...)");
            paint9.setTextSize(UiKitKt.sp(context11, 9.0f));
            this.paint.setColor(states[i9] ? this.success : this.muted);
            this.paint.setTextAlign(Paint.Align.CENTER);
            Context context12 = getContext();
            Intrinsics.checkNotNullExpressionValue(context12, "getContext(...)");
            float sp4 = railY + UiKitKt.sp(context12, 9.0f);
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            canvas2.drawText(str, f, sp4 + UiKitKt.dp(r3, 20), this.paint);
            i6 = i8 + 1;
            i4 = 1;
            strArr = labels4;
            i5 = i7;
            length = i;
            railY3 = railY;
            labels2 = labels;
        }
        this.paint.setTextAlign(Paint.Align.LEFT);
    }

    private final String headline() {
        return !this.permissionReady ? "Perlu izin" : !this.printerReady ? "Pilih printer" : !this.serviceReady ? "Aktifkan layanan" : !this.batteryReady ? "Amankan dari latar" : "Siap mencetak";
    }

    private final void updateDescription() {
        int readyCount = 0;
        Iterable listOf = CollectionsKt.listOf((Object[]) new Boolean[]{Boolean.valueOf(this.permissionReady), Boolean.valueOf(this.printerReady), Boolean.valueOf(this.serviceReady), Boolean.valueOf(this.batteryReady)});
        if (!(listOf instanceof Collection) || !((Collection) listOf).isEmpty()) {
            readyCount = 0;
            Iterator it = listOf.iterator();
            while (it.hasNext()) {
                if (((Boolean) it.next()).booleanValue() && (readyCount = readyCount + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        setContentDescription(readyCount + " dari 4 langkah aktif. " + headline() + ". Profil " + this.profileLabel + ".");
    }

    private final void drawFittedText(Canvas canvas, String text, float x, float baseline, float maxWidth) {
        float requestedSize = this.paint.getTextSize();
        float measured = this.paint.measureText(text);
        if (measured > maxWidth && measured > 0.0f) {
            this.paint.setTextSize((maxWidth / measured) * requestedSize);
        }
        canvas.drawText(text, x, baseline, this.paint);
        this.paint.setTextSize(requestedSize);
    }
}

