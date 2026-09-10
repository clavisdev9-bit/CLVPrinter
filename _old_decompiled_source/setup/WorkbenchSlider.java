package com.clvprinter.smartprint.setup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import com.clvprinter.smartprint.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: WorkbenchSlider.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005Â¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0014J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*H\u0014J\u0010\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020\u0016H\u0016J\u0018\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020\t2\u0006\u0010,\u001a\u000201H\u0016J\"\u00102\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u00162\u0006\u00104\u001a\u00020\t2\b\u00105\u001a\u0004\u0018\u000106H\u0014J\u0010\u00107\u001a\u00020\u00182\u0006\u00108\u001a\u000209H\u0016J\u001a\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\t2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0010\u0010>\u001a\u00020\u00182\u0006\u0010?\u001a\u00020@H\u0002J\u0010\u0010A\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010B\u001a\u00020\tH\u0002R$\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@FX\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eRJ\u0010\u0012\u001a2\u0012\u0013\u0012\u00110\tÂ¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0016Â¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0013X\u0086\u000eÂ¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\tX\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\tX\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\tX\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u0006C"}, d2 = {"Lcom/rapprinter/smartprint/setup/WorkbenchSlider;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "maximum", "getMaximum", "()I", "setMaximum", "(I)V", "currentValue", "getCurrentValue", "setCurrentValue", "onValueChanged", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "", "fromUser", "", "getOnValueChanged", "()Lkotlin/jvm/functions/Function2;", "setOnValueChanged", "(Lkotlin/jvm/functions/Function2;)V", "paint", "Landroid/graphics/Paint;", "track", "Landroid/graphics/RectF;", "ink", "cobalt", "rule", "paperWhite", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "performClick", "onKeyDown", "keyCode", "Landroid/view/KeyEvent;", "onFocusChanged", "gainFocus", "direction", "previouslyFocusedRect", "Landroid/graphics/Rect;", "onInitializeAccessibilityNodeInfo", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "performAccessibilityAction", "action", "arguments", "Landroid/os/Bundle;", "updateFromPosition", "x", "", "setFromUser", "keyboardStep", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WorkbenchSlider extends View {
    private final int cobalt;
    private int currentValue;
    private final int ink;
    private int maximum;
    private Function2<? super Integer, ? super Boolean, Unit> onValueChanged;
    private final Paint paint;
    private final int paperWhite;
    private final int rule;
    private final RectF track;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WorkbenchSlider(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ WorkbenchSlider(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkbenchSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        this.maximum = 100;
        this.onValueChanged = new Function2() { // from class: com.clvprinter.smartprint.setup.WorkbenchSlider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit unit;
                ((Integer) obj).intValue();
                ((Boolean) obj2).booleanValue();
                unit = Unit.INSTANCE;
                return unit;
            }
        };
        this.paint = new Paint(1);
        this.track = new RectF();
        this.ink = context.getColor(R.color.ink);
        this.cobalt = context.getColor(R.color.cobalt);
        this.rule = context.getColor(R.color.rule);
        this.paperWhite = context.getColor(R.color.paper_white);
        setFocusable(true);
        setClickable(true);
        setImportantForAccessibility(1);
        setMinimumHeight(UiKitKt.dp(context, 48));
    }

    public final int getMaximum() {
        return this.maximum;
    }

    public final void setMaximum(int value) {
        this.maximum = RangesKt.coerceAtLeast(value, 1);
        setCurrentValue(RangesKt.coerceIn(this.currentValue, 0, this.maximum));
        invalidate();
    }

    public final int getCurrentValue() {
        return this.currentValue;
    }

    public final void setCurrentValue(int value) {
        int adjusted = RangesKt.coerceIn(value, 0, this.maximum);
        if (this.currentValue == adjusted) {
            return;
        }
        this.currentValue = adjusted;
        invalidate();
    }

    public final Function2<Integer, Boolean, Unit> getOnValueChanged() {
        return this.onValueChanged;
    }

    public final void setOnValueChanged(Function2<? super Integer, ? super Boolean, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.onValueChanged = function2;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int desiredWidth = UiKitKt.dp(context, 220);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        int desiredHeight = UiKitKt.dp(context2, 48);
        setMeasuredDimension(View.resolveSize(desiredWidth, widthMeasureSpec), View.resolveSize(desiredHeight, heightMeasureSpec));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Paint paint;
        float dp;
        float dp2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        float paddingLeft = getPaddingLeft();
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float railLeft = paddingLeft + UiKitKt.dp(r3, 9);
        float width = getWidth() - getPaddingRight();
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float railRight = width - UiKitKt.dp(r3, 9);
        float railY = getHeight() / 2.0f;
        float ratio = this.currentValue / this.maximum;
        float thumbX = railLeft + ((railRight - railLeft) * ratio);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.rule);
        RectF rectF = this.track;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        rectF.set(railLeft, railY - UiKitKt.dp(r3, 2), railRight, UiKitKt.dp(r4, 2) + railY);
        RectF rectF2 = this.track;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        float dp3 = UiKitKt.dp(context, 2);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(rectF2, dp3, UiKitKt.dp(r4, 2), this.paint);
        this.paint.setColor(this.cobalt);
        this.track.right = thumbX;
        RectF rectF3 = this.track;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        float dp4 = UiKitKt.dp(context2, 2);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(rectF3, dp4, UiKitKt.dp(r4, 2), this.paint);
        Paint paint2 = this.paint;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        paint2.setStrokeWidth(UiKitKt.dp(r3, 1));
        int i = 8 + 1;
        int i2 = 0;
        while (true) {
            paint = this.paint;
            if (i2 >= i) {
                break;
            }
            float f = railLeft + (((railRight - railLeft) * i2) / 8);
            paint.setColor(f <= thumbX ? this.cobalt : this.rule);
            Context context3 = getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
            dp = WorkbenchSliderKt.dp(context3, 0.5f);
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            float dp5 = UiKitKt.dp(r10, 7) + railY;
            Context context4 = getContext();
            Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
            dp2 = WorkbenchSliderKt.dp(context4, 0.5f);
            Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
            canvas.drawRect(f - dp, dp5, dp2 + f, UiKitKt.dp(r10, 11) + railY, this.paint);
            i2++;
            i = i;
        }
        paint.setColor(this.paperWhite);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Context context5 = getContext();
        Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
        float dp6 = UiKitKt.dp(context5, 3);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(thumbX - UiKitKt.dp(r1, 9), railY - UiKitKt.dp(r3, 9), UiKitKt.dp(r4, 9) + thumbX, railY + UiKitKt.dp(r5, 9), dp6, UiKitKt.dp(r2, 3), this.paint);
        this.paint.setStyle(Paint.Style.STROKE);
        Paint paint3 = this.paint;
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        paint3.setStrokeWidth(UiKitKt.dp(r2, 2));
        this.paint.setColor(isFocused() ? this.ink : this.cobalt);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        Context context6 = getContext();
        Intrinsics.checkNotNullExpressionValue(context6, "getContext(...)");
        float dp7 = UiKitKt.dp(context6, 3);
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        canvas.drawRoundRect(thumbX - UiKitKt.dp(r1, 9), railY - UiKitKt.dp(r3, 9), UiKitKt.dp(r4, 9) + thumbX, railY + UiKitKt.dp(r5, 9), dp7, UiKitKt.dp(r7, 3), this.paint);
        this.paint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!isEnabled()) {
            return false;
        }
        switch (event.getActionMasked()) {
            case 0:
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                requestFocus();
                updateFromPosition(event.getX());
                return true;
            case 1:
                updateFromPosition(event.getX());
                ViewParent parent2 = getParent();
                if (parent2 != null) {
                    parent2.requestDisallowInterceptTouchEvent(false);
                }
                performClick();
                return true;
            case 2:
                updateFromPosition(event.getX());
                return true;
            case 3:
                ViewParent parent3 = getParent();
                if (parent3 != null) {
                    parent3.requestDisallowInterceptTouchEvent(false);
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        switch (keyCode) {
            case 21:
            case 69:
                setFromUser(this.currentValue - keyboardStep());
                return true;
            case 22:
            case 70:
            case 81:
                setFromUser(this.currentValue + keyboardStep());
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        invalidate();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(SeekBar.class.getName());
        info.setScrollable(true);
        info.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, 0.0f, this.maximum, this.currentValue));
        info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
        info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
        info.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (action == 8192) {
            setFromUser(this.currentValue - keyboardStep());
            return true;
        }
        if (action == 4096) {
            setFromUser(this.currentValue + keyboardStep());
            return true;
        }
        if (action == AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS.getId()) {
            int i = this.currentValue;
            float requested = arguments != null ? arguments.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE", i) : i;
            setFromUser(MathKt.roundToInt(requested));
            return true;
        }
        return super.performAccessibilityAction(action, arguments);
    }

    private final void updateFromPosition(float x) {
        float paddingLeft = getPaddingLeft();
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float left = paddingLeft + UiKitKt.dp(r1, 9);
        float width = getWidth() - getPaddingRight();
        Intrinsics.checkNotNullExpressionValue(getContext(), "getContext(...)");
        float right = width - UiKitKt.dp(r4, 9);
        if (right <= left) {
            return;
        }
        float ratio = RangesKt.coerceIn((x - left) / (right - left), 0.0f, 1.0f);
        setFromUser(MathKt.roundToInt(this.maximum * ratio));
    }

    private final void setFromUser(int value) {
        int adjusted = RangesKt.coerceIn(value, 0, this.maximum);
        if (adjusted == this.currentValue) {
            return;
        }
        setCurrentValue(adjusted);
        this.onValueChanged.invoke(Integer.valueOf(adjusted), true);
        sendAccessibilityEvent(4);
    }

    private final int keyboardStep() {
        return RangesKt.coerceAtLeast(this.maximum / 20, 1);
    }
}

