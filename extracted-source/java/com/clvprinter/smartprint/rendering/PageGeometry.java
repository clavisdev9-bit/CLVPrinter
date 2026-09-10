package com.clvprinter.smartprint.rendering;

import kotlin.Metadata;

/* compiled from: PdfReceiptRenderer.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/clvprinter/smartprint/rendering/PageGeometry;", "", "sourceLeft", "", "sourceWidth", "targetWidth", "", "targetHeight", "<init>", "(DDII)V", "getSourceLeft", "()D", "getSourceWidth", "getTargetWidth", "()I", "getTargetHeight", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class PageGeometry {
    private final double sourceLeft;
    private final double sourceWidth;
    private final int targetHeight;
    private final int targetWidth;

    public static /* synthetic */ PageGeometry copy$default(PageGeometry pageGeometry, double d, double d2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            d = pageGeometry.sourceLeft;
        }
        double d3 = d;
        if ((i3 & 2) != 0) {
            d2 = pageGeometry.sourceWidth;
        }
        double d4 = d2;
        if ((i3 & 4) != 0) {
            i = pageGeometry.targetWidth;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            i2 = pageGeometry.targetHeight;
        }
        return pageGeometry.copy(d3, d4, i4, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final double getSourceLeft() {
        return this.sourceLeft;
    }

    /* renamed from: component2, reason: from getter */
    public final double getSourceWidth() {
        return this.sourceWidth;
    }

    /* renamed from: component3, reason: from getter */
    public final int getTargetWidth() {
        return this.targetWidth;
    }

    /* renamed from: component4, reason: from getter */
    public final int getTargetHeight() {
        return this.targetHeight;
    }

    public final PageGeometry copy(double sourceLeft, double sourceWidth, int targetWidth, int targetHeight) {
        return new PageGeometry(sourceLeft, sourceWidth, targetWidth, targetHeight);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PageGeometry)) {
            return false;
        }
        PageGeometry pageGeometry = (PageGeometry) other;
        return Double.compare(this.sourceLeft, pageGeometry.sourceLeft) == 0 && Double.compare(this.sourceWidth, pageGeometry.sourceWidth) == 0 && this.targetWidth == pageGeometry.targetWidth && this.targetHeight == pageGeometry.targetHeight;
    }

    public int hashCode() {
        return (((((Double.hashCode(this.sourceLeft) * 31) + Double.hashCode(this.sourceWidth)) * 31) + Integer.hashCode(this.targetWidth)) * 31) + Integer.hashCode(this.targetHeight);
    }

    public String toString() {
        return "PageGeometry(sourceLeft=" + this.sourceLeft + ", sourceWidth=" + this.sourceWidth + ", targetWidth=" + this.targetWidth + ", targetHeight=" + this.targetHeight + ")";
    }

    public PageGeometry(double sourceLeft, double sourceWidth, int targetWidth, int targetHeight) {
        this.sourceLeft = sourceLeft;
        this.sourceWidth = sourceWidth;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    public final double getSourceLeft() {
        return this.sourceLeft;
    }

    public final double getSourceWidth() {
        return this.sourceWidth;
    }

    public final int getTargetWidth() {
        return this.targetWidth;
    }

    public final int getTargetHeight() {
        return this.targetHeight;
    }
}
