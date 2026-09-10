package com.clvprinter.smartprint.escpos;

import com.clvprinter.smartprint.printer.CutMode;
import com.clvprinter.smartprint.printer.PrintLimits;
import com.clvprinter.smartprint.printer.PrinterConfig;
import com.clvprinter.smartprint.rendering.PackedRaster;
import java.io.ByteArrayOutputStream;
import java.util.List;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: EscPosEncoder.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÃ†\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0005J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\u0005J\u001c\u0010\u0012\u001a\u00020\u00132\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\u0005J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u000e\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001bJ\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0082TÂ¢\u0006\u0002\n\u0000Â¨\u0006\""}, d2 = {"Lcom/rapprinter/smartprint/escpos/EscPosEncoder;", "", "<init>", "()V", "DEFAULT_BAND_ROWS", "", "encodeDocument", "", "pages", "", "Lcom/rapprinter/smartprint/rendering/PackedRaster;", "config", "Lcom/rapprinter/smartprint/printer/PrinterConfig;", "initialize", "codePage", "rasterCommands", "raster", "bandRows", "estimatedDocumentSize", "", "estimatedRasterCommandSize", "writeRasterCommands", "", "output", "Ljava/io/ByteArrayOutputStream;", "cut", "mode", "Lcom/rapprinter/smartprint/printer/CutMode;", "safeAdd", "left", "right", "safeFeedLines", "RASTER_HEADER_BYTES", "PAGE_SEPARATOR_BYTES", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EscPosEncoder {
    private static final int DEFAULT_BAND_ROWS = 256;
    public static final EscPosEncoder INSTANCE = new EscPosEncoder();
    private static final int PAGE_SEPARATOR_BYTES = 3;
    private static final int RASTER_HEADER_BYTES = 8;

    /* compiled from: EscPosEncoder.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CutMode.values().length];
            try {
                iArr[CutMode.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CutMode.FULL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CutMode.PARTIAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private EscPosEncoder() {
    }

    public final byte[] encodeDocument(List<PackedRaster> pages, PrinterConfig config) {
        Intrinsics.checkNotNullParameter(pages, "pages");
        Intrinsics.checkNotNullParameter(config, "config");
        if (pages.isEmpty()) {
            throw new IllegalArgumentException("Tidak ada halaman raster".toString());
        }
        long estimatedSize = estimatedDocumentSize(pages, config);
        ByteArrayOutputStream output = new ByteArrayOutputStream((int) estimatedSize);
        output.write(initialize(config.getCodePage()));
        int i = 0;
        for (Object obj : pages) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            INSTANCE.writeRasterCommands(output, (PackedRaster) obj, DEFAULT_BAND_ROWS);
            if (i < CollectionsKt.getLastIndex(pages)) {
                output.write(new byte[]{27, 100, 1});
            }
            i = i2;
        }
        int safeFeedLines = safeFeedLines(config);
        for (int i3 = 0; i3 < safeFeedLines; i3++) {
            output.write(10);
        }
        output.write(cut(config.getCutMode()));
        byte[] byteArray = output.toByteArray();
        PrintLimits.INSTANCE.requirePayloadBytes(byteArray.length);
        Intrinsics.checkNotNullExpressionValue(byteArray, "also(...)");
        return byteArray;
    }

    public final byte[] initialize(int codePage) {
        return new byte[]{27, 64, 27, 116, (byte) RangesKt.coerceIn(codePage, 0, KotlinVersion.MAX_COMPONENT_VALUE), 27, 51, 24};
    }

    public static /* synthetic */ byte[] rasterCommands$default(EscPosEncoder escPosEncoder, PackedRaster packedRaster, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = DEFAULT_BAND_ROWS;
        }
        return escPosEncoder.rasterCommands(packedRaster, i);
    }

    public final byte[] rasterCommands(PackedRaster raster, int bandRows) {
        Intrinsics.checkNotNullParameter(raster, "raster");
        boolean z = false;
        if (1 <= bandRows && bandRows < 2049) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("Tinggi band tidak valid".toString());
        }
        long estimatedSize = estimatedRasterCommandSize(raster, bandRows);
        PrintLimits.INSTANCE.requireRasterBytes(raster.getData().length);
        PrintLimits.INSTANCE.requirePayloadBytes(estimatedSize);
        ByteArrayOutputStream output = new ByteArrayOutputStream((int) estimatedSize);
        writeRasterCommands(output, raster, bandRows);
        byte[] byteArray = output.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    public final long estimatedDocumentSize(List<PackedRaster> pages, PrinterConfig config) {
        Intrinsics.checkNotNullParameter(pages, "pages");
        Intrinsics.checkNotNullParameter(config, "config");
        if (pages.isEmpty()) {
            throw new IllegalArgumentException("Tidak ada halaman raster".toString());
        }
        PrintLimits.INSTANCE.requirePageCount(pages.size());
        long rasterBytes = 0;
        long payloadBytes = initialize(config.getCodePage()).length;
        List<PackedRaster> list = pages;
        int i = 0;
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Iterable iterable = list;
            int i4 = i;
            rasterBytes = INSTANCE.safeAdd(rasterBytes, r13.getData().length);
            PrintLimits.INSTANCE.requireRasterBytes(rasterBytes);
            payloadBytes = INSTANCE.safeAdd(payloadBytes, INSTANCE.estimatedRasterCommandSize((PackedRaster) obj, DEFAULT_BAND_ROWS));
            if (i2 < CollectionsKt.getLastIndex(pages)) {
                payloadBytes = INSTANCE.safeAdd(payloadBytes, 3L);
            }
            i2 = i3;
            list = iterable;
            i = i4;
        }
        long payloadBytes2 = safeAdd(safeAdd(payloadBytes, safeFeedLines(config)), cut(config.getCutMode()).length);
        PrintLimits.INSTANCE.requirePayloadBytes(payloadBytes2);
        return payloadBytes2;
    }

    public static /* synthetic */ long estimatedRasterCommandSize$default(EscPosEncoder escPosEncoder, PackedRaster packedRaster, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = DEFAULT_BAND_ROWS;
        }
        return escPosEncoder.estimatedRasterCommandSize(packedRaster, i);
    }

    public final long estimatedRasterCommandSize(PackedRaster raster, int bandRows) {
        Intrinsics.checkNotNullParameter(raster, "raster");
        boolean z = false;
        if (!(1 <= bandRows && bandRows < 2049)) {
            throw new IllegalArgumentException("Tinggi band tidak valid".toString());
        }
        int bytesPerRow = raster.getBytesPerRow();
        if (1 <= bytesPerRow && bytesPerRow < 65536) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("Raster terlalu lebar untuk perintah ESC/POS; gunakan profil printer 384 atau 576 dot.".toString());
        }
        long bandCount = ((raster.getHeight() + bandRows) - 1) / bandRows;
        return safeAdd(raster.getData().length, 8 * bandCount);
    }

    private final void writeRasterCommands(ByteArrayOutputStream output, PackedRaster raster, int bandRows) {
        int widthBytes = raster.getBytesPerRow();
        int top = 0;
        while (top < raster.getHeight()) {
            int rows = Math.min(bandRows, raster.getHeight() - top);
            output.write(29);
            output.write(118);
            output.write(48);
            output.write(0);
            output.write(widthBytes & KotlinVersion.MAX_COMPONENT_VALUE);
            output.write((widthBytes >>> 8) & KotlinVersion.MAX_COMPONENT_VALUE);
            output.write(rows & KotlinVersion.MAX_COMPONENT_VALUE);
            output.write((rows >>> 8) & KotlinVersion.MAX_COMPONENT_VALUE);
            output.write(raster.getData(), top * widthBytes, rows * widthBytes);
            top += rows;
        }
    }

    public final byte[] cut(CutMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        switch (WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
            case 1:
                return new byte[0];
            case 2:
                return new byte[]{29, 86, 0};
            case 3:
                return new byte[]{29, 86, 1};
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final long safeAdd(long left, long right) {
        try {
            return Math.addExact(left, right);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Ukuran data ESC/POS melampaui batas aman; pecah dokumen sebelum mencetak.");
        }
    }

    private final int safeFeedLines(PrinterConfig config) {
        return RangesKt.coerceIn(config.getFeedLines(), 0, 12);
    }
}

