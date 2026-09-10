package com.clvprinter.smartprint.printer;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: PrintLimits.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0006\bÃ†\u0002\u0018\u00002\u00020\u0001:\u0001(B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0007J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0007J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007J\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0007J \u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#H\u0002J \u0010$\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010%\u001a\u00020#2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086TÂ¢\u0006\u0002\n\u0000Â¨\u0006)"}, d2 = {"Lcom/rapprinter/smartprint/printer/PrintLimits;", "", "<init>", "()V", "MAX_PDF_BYTES", "", "MAX_PAGE_COUNT", "", "MAX_PAGE_HEIGHT_DOTS", "MAX_TOTAL_PIXELS", "MAX_TOTAL_RASTER_BYTES", "MAX_PAYLOAD_BYTES", "MAX_TRANSMISSION_BYTES", "MAX_COPIES", "requirePdfSize", "", "bytes", "requirePageCount", "pageCount", "requirePageHeight", "heightDots", "addRenderedPage", "Lcom/rapprinter/smartprint/printer/PrintLimits$RenderBudget;", "current", "width", "height", "requireRasterBytes", "requirePayloadBytes", "transmissionBytes", "payloadBytes", "copies", "checkedMultiply", "left", "right", "label", "", "checkedAdd", "formatMiB", "formatMillions", "value", "RenderBudget", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PrintLimits {
    public static final PrintLimits INSTANCE = new PrintLimits();
    public static final int MAX_COPIES = 99;
    public static final int MAX_PAGE_COUNT = 40;
    public static final int MAX_PAGE_HEIGHT_DOTS = 100000;
    public static final long MAX_PAYLOAD_BYTES = 16777216;
    public static final long MAX_PDF_BYTES = 67108864;
    public static final long MAX_TOTAL_PIXELS = 96000000;
    public static final long MAX_TOTAL_RASTER_BYTES = 12582912;
    public static final long MAX_TRANSMISSION_BYTES = 268435456;

    private PrintLimits() {
    }

    /* compiled from: PrintLimits.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003Â¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÃ†\u0003J\t\u0010\u000b\u001a\u00020\u0003HÃ†\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÃ†\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÃ–\u0003J\t\u0010\u0010\u001a\u00020\u0011HÃ–\u0001J\t\u0010\u0012\u001a\u00020\u0013HÃ–\u0001R\u0011\u0010\u0002\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bÂ¨\u0006\u0014"}, d2 = {"Lcom/rapprinter/smartprint/printer/PrintLimits$RenderBudget;", "", "pixels", "", "rasterBytes", "<init>", "(JJ)V", "getPixels", "()J", "getRasterBytes", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RenderBudget {
        private final long pixels;
        private final long rasterBytes;

        public RenderBudget() {
            this(0L, 0L, 3, null);
        }

        public static /* synthetic */ RenderBudget copy$default(RenderBudget renderBudget, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = renderBudget.pixels;
            }
            if ((i & 2) != 0) {
                j2 = renderBudget.rasterBytes;
            }
            return renderBudget.copy(j, j2);
        }

        /* renamed from: component1, reason: from getter */
        public final long getPixels() {
            return this.pixels;
        }

        /* renamed from: component2, reason: from getter */
        public final long getRasterBytes() {
            return this.rasterBytes;
        }

        public final RenderBudget copy(long pixels, long rasterBytes) {
            return new RenderBudget(pixels, rasterBytes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RenderBudget)) {
                return false;
            }
            RenderBudget renderBudget = (RenderBudget) other;
            return this.pixels == renderBudget.pixels && this.rasterBytes == renderBudget.rasterBytes;
        }

        public int hashCode() {
            return (Long.hashCode(this.pixels) * 31) + Long.hashCode(this.rasterBytes);
        }

        public String toString() {
            return "RenderBudget(pixels=" + this.pixels + ", rasterBytes=" + this.rasterBytes + ")";
        }

        public RenderBudget(long pixels, long rasterBytes) {
            this.pixels = pixels;
            this.rasterBytes = rasterBytes;
        }

        public /* synthetic */ RenderBudget(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? 0L : j2);
        }

        public final long getPixels() {
            return this.pixels;
        }

        public final long getRasterBytes() {
            return this.rasterBytes;
        }
    }

    public final void requirePdfSize(long bytes) {
        String str;
        boolean z = false;
        if (1 <= bytes && bytes < 67108865) {
            z = true;
        }
        if (!z) {
            if (bytes <= 0) {
                str = "PDF spool tidak tersedia atau kosong";
            } else {
                str = "PDF terlalu besar (" + INSTANCE.formatMiB(bytes) + " MiB; maksimal " + INSTANCE.formatMiB(MAX_PDF_BYTES) + " MiB). Kurangi gambar atau pecah dokumen lalu coba lagi.";
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    public final void requirePageCount(int pageCount) {
        String str;
        boolean z = false;
        if (1 <= pageCount && pageCount < 41) {
            z = true;
        }
        if (!z) {
            if (pageCount <= 0) {
                str = "PDF tidak memiliki halaman";
            } else {
                str = "PDF memiliki " + pageCount + " halaman; maksimal 40 halaman per tugas cetak. Pecah dokumen menjadi beberapa tugas cetak.";
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    public final void requirePageHeight(int heightDots) {
        boolean z = false;
        if (1 <= heightDots && heightDots < 100001) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("Halaman terlalu panjang untuk diproses dengan aman (" + heightDots + " dot; maksimal 100000 dot). Pecah nota menjadi beberapa halaman.").toString());
        }
    }

    public final RenderBudget addRenderedPage(RenderBudget current, int width, int height) {
        Intrinsics.checkNotNullParameter(current, "current");
        if (!(width > 0 && height > 0)) {
            throw new IllegalArgumentException("Dimensi halaman hasil render tidak valid".toString());
        }
        requirePageHeight(height);
        long pagePixels = checkedMultiply(width, height, "jumlah piksel");
        long pageRasterBytes = checkedMultiply((width + 7) / 8, height, "ukuran raster");
        long totalPixels = checkedAdd(current.getPixels(), pagePixels, "jumlah piksel");
        long totalRasterBytes = checkedAdd(current.getRasterBytes(), pageRasterBytes, "ukuran raster");
        if (!(totalPixels <= MAX_TOTAL_PIXELS)) {
            throw new IllegalArgumentException(("Dokumen membutuhkan terlalu banyak piksel (" + INSTANCE.formatMillions(totalPixels) + " juta; maksimal " + INSTANCE.formatMillions(MAX_TOTAL_PIXELS) + " juta). Kurangi panjang/resolusi atau pecah PDF.").toString());
        }
        if (!(totalRasterBytes <= MAX_TOTAL_RASTER_BYTES)) {
            throw new IllegalArgumentException(("Data gambar cetak terlalu besar (" + INSTANCE.formatMiB(totalRasterBytes) + " MiB; maksimal " + INSTANCE.formatMiB(MAX_TOTAL_RASTER_BYTES) + " MiB). Pecah PDF menjadi beberapa tugas cetak.").toString());
        }
        return new RenderBudget(totalPixels, totalRasterBytes);
    }

    public final void requireRasterBytes(long bytes) {
        boolean z = false;
        if (0 <= bytes && bytes < 12582913) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException(("Data raster terlalu besar (" + INSTANCE.formatMiB(RangesKt.coerceAtLeast(bytes, 0L)) + " MiB; maksimal " + INSTANCE.formatMiB(MAX_TOTAL_RASTER_BYTES) + " MiB). Pecah dokumen sebelum mencetak.").toString());
        }
    }

    public final void requirePayloadBytes(long bytes) {
        String str;
        boolean z = false;
        if (1 <= bytes && bytes < 16777217) {
            z = true;
        }
        if (!z) {
            if (bytes <= 0) {
                str = "Data ESC/POS kosong";
            } else {
                str = "Data ESC/POS terlalu besar (" + INSTANCE.formatMiB(bytes) + " MiB; maksimal " + INSTANCE.formatMiB(MAX_PAYLOAD_BYTES) + " MiB). Kurangi isi atau pecah dokumen sebelum mencetak.";
            }
            throw new IllegalArgumentException(str.toString());
        }
    }

    public final long transmissionBytes(long payloadBytes, int copies) {
        if (!(payloadBytes > 0)) {
            throw new IllegalArgumentException("Data cetak kosong; buat ulang dokumen dari pratinjau cetak.".toString());
        }
        if (!(1 <= copies && copies < 100)) {
            throw new IllegalArgumentException("Jumlah salinan harus antara 1 dan 99.".toString());
        }
        long total = checkedMultiply(payloadBytes, copies, "ukuran pengiriman");
        if (!(total <= MAX_TRANSMISSION_BYTES)) {
            throw new IllegalArgumentException(("Total pengiriman terlalu besar (" + INSTANCE.formatMiB(total) + " MiB; maksimal " + INSTANCE.formatMiB(MAX_TRANSMISSION_BYTES) + " MiB). Kurangi jumlah salinan atau pecah tugas cetak.").toString());
        }
        return total;
    }

    private final long checkedMultiply(long left, long right, String label) {
        try {
            return Math.multiplyExact(left, right);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(label + " dokumen melampaui batas aman; kecilkan atau pecah PDF.");
        }
    }

    private final long checkedAdd(long left, long right, String label) {
        try {
            return Math.addExact(left, right);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(label + " dokumen melampaui batas aman; kecilkan atau pecah PDF.");
        }
    }

    private final String formatMiB(long bytes) {
        String format = String.format(Locale.ROOT, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1048576.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final String formatMillions(long value) {
        String format = String.format(Locale.ROOT, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(value / 1000000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}

