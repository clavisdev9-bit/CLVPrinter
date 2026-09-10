package com.clvprinter.smartprint.printer;

import com.clvprinter.smartprint.printservice.PrintJobProcessor;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.io.ConstantsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.uuid.Uuid;

/* compiled from: PrinterConfig.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\b\u0018\u0000 82\u00020\u0001:\u00018Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010 \u001a\u00020\u0000J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u000eHÆ\u0003J\t\u00102\u001a\u00020\u000eHÆ\u0003Jm\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eHÆ\u0001J\u0013\u00104\u001a\u00020\u000e2\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u0003HÖ\u0001J\t\u00107\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010!\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u00069"}, d2 = {"Lcom/clvprinter/smartprint/printer/PrinterConfig;", "", "dotWidth", "", "ditherMode", "Lcom/clvprinter/smartprint/printer/DitherMode;", "threshold", "feedLines", "cutMode", "Lcom/clvprinter/smartprint/printer/CutMode;", "codePage", "connectTimeoutMs", "sendTimeoutMs", "trimWhitespace", "", "autoFitContent", "<init>", "(ILcom/clvprinter/smartprint/printer/DitherMode;IILcom/clvprinter/smartprint/printer/CutMode;IIIZZ)V", "getDotWidth", "()I", "getDitherMode", "()Lcom/clvprinter/smartprint/printer/DitherMode;", "getThreshold", "getFeedLines", "getCutMode", "()Lcom/clvprinter/smartprint/printer/CutMode;", "getCodePage", "getConnectTimeoutMs", "getSendTimeoutMs", "getTrimWhitespace", "()Z", "getAutoFitContent", "normalized", "densityLabel", "", "getDensityLabel", "()Ljava/lang/String;", "profile", "Lcom/clvprinter/smartprint/printer/PrinterProfile;", "getProfile", "()Lcom/clvprinter/smartprint/printer/PrinterProfile;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "toString", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PrinterConfig {
    public static final int DEFAULT_DOT_WIDTH = 384;
    public static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    public static final int WIDE_DOT_WIDTH = 576;
    private final boolean autoFitContent;
    private final int codePage;
    private final int connectTimeoutMs;
    private final CutMode cutMode;
    private final DitherMode ditherMode;
    private final int dotWidth;
    private final int feedLines;
    private final int sendTimeoutMs;
    private final int threshold;
    private final boolean trimWhitespace;

    public PrinterConfig() {
        this(0, null, 0, 0, null, 0, 0, 0, false, false, 1023, null);
    }

    public static /* synthetic */ PrinterConfig copy$default(PrinterConfig printerConfig, int i, DitherMode ditherMode, int i2, int i3, CutMode cutMode, int i4, int i5, int i6, boolean z, boolean z2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i = printerConfig.dotWidth;
        }
        if ((i7 & 2) != 0) {
            ditherMode = printerConfig.ditherMode;
        }
        if ((i7 & 4) != 0) {
            i2 = printerConfig.threshold;
        }
        if ((i7 & 8) != 0) {
            i3 = printerConfig.feedLines;
        }
        if ((i7 & 16) != 0) {
            cutMode = printerConfig.cutMode;
        }
        if ((i7 & 32) != 0) {
            i4 = printerConfig.codePage;
        }
        if ((i7 & 64) != 0) {
            i5 = printerConfig.connectTimeoutMs;
        }
        if ((i7 & Uuid.SIZE_BITS) != 0) {
            i6 = printerConfig.sendTimeoutMs;
        }
        if ((i7 & 256) != 0) {
            z = printerConfig.trimWhitespace;
        }
        if ((i7 & ConstantsKt.MINIMUM_BLOCK_SIZE) != 0) {
            z2 = printerConfig.autoFitContent;
        }
        boolean z3 = z;
        boolean z4 = z2;
        int i8 = i5;
        int i9 = i6;
        CutMode cutMode2 = cutMode;
        int i10 = i4;
        return printerConfig.copy(i, ditherMode, i2, i3, cutMode2, i10, i8, i9, z3, z4);
    }

    /* renamed from: component1, reason: from getter */
    public final int getDotWidth() {
        return this.dotWidth;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getAutoFitContent() {
        return this.autoFitContent;
    }

    /* renamed from: component2, reason: from getter */
    public final DitherMode getDitherMode() {
        return this.ditherMode;
    }

    /* renamed from: component3, reason: from getter */
    public final int getThreshold() {
        return this.threshold;
    }

    /* renamed from: component4, reason: from getter */
    public final int getFeedLines() {
        return this.feedLines;
    }

    /* renamed from: component5, reason: from getter */
    public final CutMode getCutMode() {
        return this.cutMode;
    }

    /* renamed from: component6, reason: from getter */
    public final int getCodePage() {
        return this.codePage;
    }

    /* renamed from: component7, reason: from getter */
    public final int getConnectTimeoutMs() {
        return this.connectTimeoutMs;
    }

    /* renamed from: component8, reason: from getter */
    public final int getSendTimeoutMs() {
        return this.sendTimeoutMs;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getTrimWhitespace() {
        return this.trimWhitespace;
    }

    public final PrinterConfig copy(int dotWidth, DitherMode ditherMode, int threshold, int feedLines, CutMode cutMode, int codePage, int connectTimeoutMs, int sendTimeoutMs, boolean trimWhitespace, boolean autoFitContent) {
        Intrinsics.checkNotNullParameter(ditherMode, "ditherMode");
        Intrinsics.checkNotNullParameter(cutMode, "cutMode");
        return new PrinterConfig(dotWidth, ditherMode, threshold, feedLines, cutMode, codePage, connectTimeoutMs, sendTimeoutMs, trimWhitespace, autoFitContent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrinterConfig)) {
            return false;
        }
        PrinterConfig printerConfig = (PrinterConfig) other;
        return this.dotWidth == printerConfig.dotWidth && this.ditherMode == printerConfig.ditherMode && this.threshold == printerConfig.threshold && this.feedLines == printerConfig.feedLines && this.cutMode == printerConfig.cutMode && this.codePage == printerConfig.codePage && this.connectTimeoutMs == printerConfig.connectTimeoutMs && this.sendTimeoutMs == printerConfig.sendTimeoutMs && this.trimWhitespace == printerConfig.trimWhitespace && this.autoFitContent == printerConfig.autoFitContent;
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.dotWidth) * 31) + this.ditherMode.hashCode()) * 31) + Integer.hashCode(this.threshold)) * 31) + Integer.hashCode(this.feedLines)) * 31) + this.cutMode.hashCode()) * 31) + Integer.hashCode(this.codePage)) * 31) + Integer.hashCode(this.connectTimeoutMs)) * 31) + Integer.hashCode(this.sendTimeoutMs)) * 31) + Boolean.hashCode(this.trimWhitespace)) * 31) + Boolean.hashCode(this.autoFitContent);
    }

    public String toString() {
        return "PrinterConfig(dotWidth=" + this.dotWidth + ", ditherMode=" + this.ditherMode + ", threshold=" + this.threshold + ", feedLines=" + this.feedLines + ", cutMode=" + this.cutMode + ", codePage=" + this.codePage + ", connectTimeoutMs=" + this.connectTimeoutMs + ", sendTimeoutMs=" + this.sendTimeoutMs + ", trimWhitespace=" + this.trimWhitespace + ", autoFitContent=" + this.autoFitContent + ")";
    }

    public PrinterConfig(int dotWidth, DitherMode ditherMode, int threshold, int feedLines, CutMode cutMode, int codePage, int connectTimeoutMs, int sendTimeoutMs, boolean trimWhitespace, boolean autoFitContent) {
        Intrinsics.checkNotNullParameter(ditherMode, "ditherMode");
        Intrinsics.checkNotNullParameter(cutMode, "cutMode");
        this.dotWidth = dotWidth;
        this.ditherMode = ditherMode;
        this.threshold = threshold;
        this.feedLines = feedLines;
        this.cutMode = cutMode;
        this.codePage = codePage;
        this.connectTimeoutMs = connectTimeoutMs;
        this.sendTimeoutMs = sendTimeoutMs;
        this.trimWhitespace = trimWhitespace;
        this.autoFitContent = autoFitContent;
    }

    public /* synthetic */ PrinterConfig(int i, DitherMode ditherMode, int i2, int i3, CutMode cutMode, int i4, int i5, int i6, boolean z, boolean z2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? DEFAULT_DOT_WIDTH : i, (i7 & 2) != 0 ? DitherMode.FLOYD_STEINBERG : ditherMode, (i7 & 4) != 0 ? 150 : i2, (i7 & 8) != 0 ? 4 : i3, (i7 & 16) != 0 ? CutMode.PARTIAL : cutMode, (i7 & 32) != 0 ? 0 : i4, (i7 & 64) != 0 ? 10000 : i5, (i7 & Uuid.SIZE_BITS) != 0 ? 15000 : i6, (i7 & 256) != 0 ? true : z, (i7 & ConstantsKt.MINIMUM_BLOCK_SIZE) != 0 ? false : z2);
    }

    public final int getDotWidth() {
        return this.dotWidth;
    }

    public final DitherMode getDitherMode() {
        return this.ditherMode;
    }

    public final int getThreshold() {
        return this.threshold;
    }

    public final int getFeedLines() {
        return this.feedLines;
    }

    public final CutMode getCutMode() {
        return this.cutMode;
    }

    public final int getCodePage() {
        return this.codePage;
    }

    public final int getConnectTimeoutMs() {
        return this.connectTimeoutMs;
    }

    public final int getSendTimeoutMs() {
        return this.sendTimeoutMs;
    }

    public final boolean getTrimWhitespace() {
        return this.trimWhitespace;
    }

    public final boolean getAutoFitContent() {
        return this.autoFitContent;
    }

    public final PrinterConfig normalized() {
        int i = this.dotWidth;
        int i2 = WIDE_DOT_WIDTH;
        if (i != 576) {
            i2 = DEFAULT_DOT_WIDTH;
        }
        return copy$default(this, i2, null, RangesKt.coerceIn(this.threshold, 64, 224), RangesKt.coerceIn(this.feedLines, 0, 12), null, RangesKt.coerceIn(this.codePage, 0, KotlinVersion.MAX_COMPONENT_VALUE), RangesKt.coerceIn(this.connectTimeoutMs, PrintJobProcessor.ODOO_WIDE_SOURCE_MIN_MILS, 30000), RangesKt.coerceIn(this.sendTimeoutMs, PrintJobProcessor.ODOO_WIDE_SOURCE_MIN_MILS, 60000), false, false, 786, null);
    }

    public final String getDensityLabel() {
        return this.threshold < 120 ? "Ringan" : this.threshold < 176 ? "Seimbang" : "Pekat";
    }

    public final PrinterProfile getProfile() {
        return PrinterProfiles.INSTANCE.forDotWidth(this.dotWidth);
    }
}
