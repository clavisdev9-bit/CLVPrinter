package com.clvprinter.smartprint.printer;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: PrinterProfile.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003Â¢\u0006\u0004\b\b\u0010\tJ\t\u0010$\u001a\u00020\u0003HÃ†\u0003J\t\u0010%\u001a\u00020\u0003HÃ†\u0003J\t\u0010&\u001a\u00020\u0006HÃ†\u0003J\t\u0010'\u001a\u00020\u0003HÃ†\u0003J1\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÃ†\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÃ–\u0003J\t\u0010,\u001a\u00020\u0003HÃ–\u0001J\t\u0010-\u001a\u00020\u0006HÃ–\u0001R\u0011\u0010\u0002\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006Â¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\u00118FÂ¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118FÂ¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00038FÂ¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\u00038FÂ¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\u00038FÂ¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000bR\u0011\u0010\u001c\u001a\u00020\u00118FÂ¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001e\u001a\u00020\u00118FÂ¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010 \u001a\u00020\u00118FÂ¢\u0006\u0006\u001a\u0004\b!\u0010\u0013R\u0011\u0010\"\u001a\u00020\u00118FÂ¢\u0006\u0006\u001a\u0004\b#\u0010\u0013Â¨\u0006/"}, d2 = {"Lcom/rapprinter/smartprint/printer/PrinterProfile;", "", "dotWidth", "", "dpi", "label", "", "nominalPaperWidthMils", "<init>", "(IILjava/lang/String;I)V", "getDotWidth", "()I", "getDpi", "getLabel", "()Ljava/lang/String;", "getNominalPaperWidthMils", "activeWidthMillimeters", "", "getActiveWidthMillimeters", "()D", "nominalPaperWidthMillimeters", "getNominalPaperWidthMillimeters", "activeWidthMils", "getActiveWidthMils", "leftUnprintableMarginMils", "getLeftUnprintableMarginMils", "rightUnprintableMarginMils", "getRightUnprintableMarginMils", "leftUnprintableMarginMillimeters", "getLeftUnprintableMarginMillimeters", "rightUnprintableMarginMillimeters", "getRightUnprintableMarginMillimeters", "printableWidthFraction", "getPrintableWidthFraction", "leftMarginFraction", "getLeftMarginFraction", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PrinterProfile {
    public static final int NOMINAL_58_MM_WIDTH_MILS = 2283;
    private final int dotWidth;
    private final int dpi;
    private final String label;
    private final int nominalPaperWidthMils;

    public static /* synthetic */ PrinterProfile copy$default(PrinterProfile printerProfile, int i, int i2, String str, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = printerProfile.dotWidth;
        }
        if ((i4 & 2) != 0) {
            i2 = printerProfile.dpi;
        }
        if ((i4 & 4) != 0) {
            str = printerProfile.label;
        }
        if ((i4 & 8) != 0) {
            i3 = printerProfile.nominalPaperWidthMils;
        }
        return printerProfile.copy(i, i2, str, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getDotWidth() {
        return this.dotWidth;
    }

    /* renamed from: component2, reason: from getter */
    public final int getDpi() {
        return this.dpi;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component4, reason: from getter */
    public final int getNominalPaperWidthMils() {
        return this.nominalPaperWidthMils;
    }

    public final PrinterProfile copy(int dotWidth, int dpi, String label, int nominalPaperWidthMils) {
        Intrinsics.checkNotNullParameter(label, "label");
        return new PrinterProfile(dotWidth, dpi, label, nominalPaperWidthMils);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterProfile)) {
            return false;
        }
        PrinterProfile printerProfile = (PrinterProfile) other;
        return this.dotWidth == printerProfile.dotWidth && this.dpi == printerProfile.dpi && Intrinsics.areEqual(this.label, printerProfile.label) && this.nominalPaperWidthMils == printerProfile.nominalPaperWidthMils;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.dotWidth) * 31) + Integer.hashCode(this.dpi)) * 31) + this.label.hashCode()) * 31) + Integer.hashCode(this.nominalPaperWidthMils);
    }

    public String toString() {
        return "PrinterProfile(dotWidth=" + this.dotWidth + ", dpi=" + this.dpi + ", label=" + this.label + ", nominalPaperWidthMils=" + this.nominalPaperWidthMils + ")";
    }

    public PrinterProfile(int dotWidth, int dpi, String label, int nominalPaperWidthMils) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.dotWidth = dotWidth;
        this.dpi = dpi;
        this.label = label;
        this.nominalPaperWidthMils = nominalPaperWidthMils;
        if (!(this.dotWidth > 0 && this.dpi > 0)) {
            throw new IllegalArgumentException("Resolusi printer harus positif".toString());
        }
        if (!(this.nominalPaperWidthMils > 0)) {
            throw new IllegalArgumentException("Lebar kertas nominal harus positif".toString());
        }
        if (getActiveWidthMils() <= this.nominalPaperWidthMils) {
        } else {
            throw new IllegalArgumentException("Lebar head aktif tidak boleh melebihi lebar kertas nominal".toString());
        }
    }

    public /* synthetic */ PrinterProfile(int i, int i2, String str, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, str, (i4 & 8) != 0 ? NOMINAL_58_MM_WIDTH_MILS : i3);
    }

    public final int getDotWidth() {
        return this.dotWidth;
    }

    public final int getDpi() {
        return this.dpi;
    }

    public final String getLabel() {
        return this.label;
    }

    public final int getNominalPaperWidthMils() {
        return this.nominalPaperWidthMils;
    }

    public final double getActiveWidthMillimeters() {
        return (this.dotWidth * 25.4d) / this.dpi;
    }

    public final double getNominalPaperWidthMillimeters() {
        return (this.nominalPaperWidthMils * 25.4d) / 1000.0d;
    }

    public final int getActiveWidthMils() {
        return MathKt.roundToInt((this.dotWidth * 1000.0d) / this.dpi);
    }

    public final int getLeftUnprintableMarginMils() {
        return (this.nominalPaperWidthMils - getActiveWidthMils()) / 2;
    }

    public final int getRightUnprintableMarginMils() {
        return (this.nominalPaperWidthMils - getActiveWidthMils()) - getLeftUnprintableMarginMils();
    }

    public final double getLeftUnprintableMarginMillimeters() {
        return (getLeftUnprintableMarginMils() * 25.4d) / 1000.0d;
    }

    public final double getRightUnprintableMarginMillimeters() {
        return (getRightUnprintableMarginMils() * 25.4d) / 1000.0d;
    }

    public final double getPrintableWidthFraction() {
        return getActiveWidthMils() / this.nominalPaperWidthMils;
    }

    public final double getLeftMarginFraction() {
        return getLeftUnprintableMarginMils() / this.nominalPaperWidthMils;
    }
}

