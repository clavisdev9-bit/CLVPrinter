package com.clvprinter.smartprint.rendering;

import kotlin.Metadata;

/* compiled from: PdfReceiptRenderer.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003Â¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÃ†\u0003J\t\u0010\u000b\u001a\u00020\u0003HÃ†\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÃ†\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÃ–\u0003J\t\u0010\u0010\u001a\u00020\u0011HÃ–\u0001J\t\u0010\u0012\u001a\u00020\u0013HÃ–\u0001R\u0011\u0010\u0002\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bÂ¨\u0006\u0014"}, d2 = {"Lcom/rapprinter/smartprint/rendering/HorizontalInkBounds;", "", "left", "", "right", "<init>", "(DD)V", "getLeft", "()D", "getRight", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
final /* data */ class HorizontalInkBounds {
    private final double left;
    private final double right;

    public static /* synthetic */ HorizontalInkBounds copy$default(HorizontalInkBounds horizontalInkBounds, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = horizontalInkBounds.left;
        }
        if ((i & 2) != 0) {
            d2 = horizontalInkBounds.right;
        }
        return horizontalInkBounds.copy(d, d2);
    }

    /* renamed from: component1, reason: from getter */
    public final double getLeft() {
        return this.left;
    }

    /* renamed from: component2, reason: from getter */
    public final double getRight() {
        return this.right;
    }

    public final HorizontalInkBounds copy(double left, double right) {
        return new HorizontalInkBounds(left, right);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HorizontalInkBounds)) {
            return false;
        }
        HorizontalInkBounds horizontalInkBounds = (HorizontalInkBounds) other;
        return Double.compare(this.left, horizontalInkBounds.left) == 0 && Double.compare(this.right, horizontalInkBounds.right) == 0;
    }

    public int hashCode() {
        return (Double.hashCode(this.left) * 31) + Double.hashCode(this.right);
    }

    public String toString() {
        return "HorizontalInkBounds(left=" + this.left + ", right=" + this.right + ")";
    }

    public HorizontalInkBounds(double left, double right) {
        this.left = left;
        this.right = right;
    }

    public final double getLeft() {
        return this.left;
    }

    public final double getRight() {
        return this.right;
    }
}

