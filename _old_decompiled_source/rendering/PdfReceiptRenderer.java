package com.clvprinter.smartprint.rendering;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.clvprinter.smartprint.printer.PrintLimits;
import com.clvprinter.smartprint.printer.PrinterConfig;
import com.clvprinter.smartprint.printer.PrinterProfile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: PdfReceiptRenderer.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007Â¢\u0006\u0004\b\u0002\u0010\u0003Jh\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e28\b\u0002\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0011Â¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u0011Â¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00160\u0010J,\u0010\u0017\u001a\u00020\u00062\n\u0010\u0014\u001a\u00060\u0018R\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J3\u0010\u001c\u001a\u00020\u001b2\n\u0010\u0014\u001a\u00060\u0018R\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u000b\u001a\u00020\fH\u0002Â¢\u0006\u0002\u0010!J\u001e\u0010\"\u001a\u0004\u0018\u00010#2\n\u0010\u0014\u001a\u00060\u0018R\u00020\u00192\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u0011H\u0002Â¨\u0006'"}, d2 = {"Lcom/rapprinter/smartprint/rendering/PdfReceiptRenderer;", "", "<init>", "()V", "render", "", "Lcom/rapprinter/smartprint/rendering/PackedRaster;", "pdfFile", "Ljava/io/File;", "config", "Lcom/rapprinter/smartprint/printer/PrinterConfig;", "canceled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "wideOdooSource", "", "onPageRendered", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "page", "pageCount", "", "renderPageInBands", "Landroid/graphics/pdf/PdfRenderer$Page;", "Landroid/graphics/pdf/PdfRenderer;", "geometry", "Lcom/rapprinter/smartprint/rendering/PageGeometry;", "resolvePageGeometry", "profile", "Lcom/rapprinter/smartprint/printer/PrinterProfile;", "fitMaxScale", "", "(Landroid/graphics/pdf/PdfRenderer$Page;Lcom/rapprinter/smartprint/printer/PrinterProfile;Ljava/lang/Double;Ljava/util/concurrent/atomic/AtomicBoolean;)Lcom/rapprinter/smartprint/rendering/PageGeometry;", "findHorizontalInkBounds", "Lcom/rapprinter/smartprint/rendering/HorizontalInkBounds;", "isInk", "color", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class PdfReceiptRenderer {
    private static final float AUTO_FIT_INK_LUMINANCE = 245.0f;
    private static final double AUTO_FIT_MAX_CONTENT_WIDTH_FRACTION = 0.86d;
    private static final double AUTO_FIT_MAX_SCALE = 1.25d;
    private static final double AUTO_FIT_MIN_SIDE_INSET_RATIO = 0.08d;
    private static final double AUTO_FIT_PADDING_RATIO = 0.025d;
    private static final int AUTO_FIT_SCAN_WIDTH = 240;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final double ODOO_80_TO_58_MAX_SCALE = 1.75d;
    private static final int RENDER_BAND_HEIGHT = 1024;
    private static final String TAG = "RapPrintRenderer";

    public static /* synthetic */ List render$default(PdfReceiptRenderer pdfReceiptRenderer, File file, PrinterConfig printerConfig, AtomicBoolean atomicBoolean, boolean z, Function2 function2, int i, Object obj) {
        boolean z2;
        Function2 function22;
        if ((i & 8) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        if ((i & 16) == 0) {
            function22 = function2;
        } else {
            function22 = new Function2() { // from class: com.clvprinter.smartprint.rendering.PdfReceiptRenderer$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    Unit unit;
                    ((Integer) obj2).intValue();
                    ((Integer) obj3).intValue();
                    unit = Unit.INSTANCE;
                    return unit;
                }
            };
        }
        return pdfReceiptRenderer.render(file, printerConfig, atomicBoolean, z2, function22);
    }

    public final List<PackedRaster> render(File pdfFile, PrinterConfig config, AtomicBoolean canceled, boolean wideOdooSource, Function2<? super Integer, ? super Integer, Unit> onPageRendered) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Double valueOf;
        Intrinsics.checkNotNullParameter(pdfFile, "pdfFile");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(canceled, "canceled");
        Intrinsics.checkNotNullParameter(onPageRendered, "onPageRendered");
        if (!pdfFile.isFile()) {
            throw new IllegalArgumentException("PDF spool tidak tersedia".toString());
        }
        PrintLimits.INSTANCE.requirePdfSize(pdfFile.length());
        List result = new ArrayList();
        ParcelFileDescriptor open = ParcelFileDescriptor.open(pdfFile, 268435456);
        try {
            try {
                ParcelFileDescriptor parcelFileDescriptor = open;
                int i = 0;
                PdfRenderer pdfRenderer = new PdfRenderer(parcelFileDescriptor);
                try {
                    PdfRenderer pdfRenderer2 = pdfRenderer;
                    PrintLimits.INSTANCE.requirePageCount(pdfRenderer2.getPageCount());
                    PrintLimits.RenderBudget renderBudget = new PrintLimits.RenderBudget(0L, 0L, 3, null);
                    int pageCount = pdfRenderer2.getPageCount();
                    PrintLimits.RenderBudget renderBudget2 = renderBudget;
                    int i2 = 0;
                    while (i2 < pageCount) {
                        if (canceled.get()) {
                            throw new CancellationException("Pencetakan dibatalkan");
                        }
                        PdfRenderer.Page openPage = pdfRenderer2.openPage(i2);
                        try {
                            PdfRenderer.Page page = openPage;
                            PrinterProfile profile = config.getProfile();
                            if (wideOdooSource) {
                                try {
                                    valueOf = Double.valueOf(ODOO_80_TO_58_MAX_SCALE);
                                } catch (Throwable th4) {
                                    th3 = th4;
                                    try {
                                        throw th3;
                                    } catch (Throwable th5) {
                                        AutoCloseableKt.closeFinally(openPage, th3);
                                        throw th5;
                                    }
                                }
                            } else {
                                valueOf = config.getAutoFitContent() ? Double.valueOf(AUTO_FIT_MAX_SCALE) : null;
                            }
                            Double d = valueOf;
                            Intrinsics.checkNotNull(page);
                            List result2 = result;
                            ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
                            try {
                                PageGeometry resolvePageGeometry = resolvePageGeometry(page, profile, d, canceled);
                                int i3 = i;
                                try {
                                    renderBudget2 = PrintLimits.INSTANCE.addRenderedPage(renderBudget2, profile.getDotWidth(), resolvePageGeometry.getTargetHeight());
                                    result2.add(renderPageInBands(page, resolvePageGeometry, config, canceled));
                                    Unit unit = Unit.INSTANCE;
                                    try {
                                        AutoCloseableKt.closeFinally(openPage, null);
                                        onPageRendered.invoke(Integer.valueOf(i2 + 1), Integer.valueOf(pdfRenderer2.getPageCount()));
                                        i2++;
                                        result = result2;
                                        parcelFileDescriptor = parcelFileDescriptor2;
                                        i = i3;
                                    } catch (Throwable th6) {
                                        th2 = th6;
                                        try {
                                            throw th2;
                                        } catch (Throwable th7) {
                                            AutoCloseableKt.closeFinally(pdfRenderer, th2);
                                            throw th7;
                                        }
                                    }
                                } catch (Throwable th8) {
                                    th3 = th8;
                                    throw th3;
                                }
                            } catch (Throwable th9) {
                                th3 = th9;
                            }
                        } catch (Throwable th10) {
                            th3 = th10;
                        }
                    }
                    List result3 = result;
                    Unit unit2 = Unit.INSTANCE;
                    AutoCloseableKt.closeFinally(pdfRenderer, null);
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(open, null);
                    return result3;
                } catch (Throwable th11) {
                    th2 = th11;
                }
            } catch (Throwable th12) {
                th = th12;
                try {
                    throw th;
                } catch (Throwable th13) {
                    CloseableKt.closeFinally(open, th);
                    throw th13;
                }
            }
        } catch (Throwable th14) {
            th = th14;
            throw th;
        }
    }

    private final PackedRaster renderPageInBands(PdfRenderer.Page page, PageGeometry geometry, PrinterConfig config, AtomicBoolean canceled) {
        Bitmap bitmap;
        int width = geometry.getTargetWidth();
        int targetHeight = geometry.getTargetHeight();
        char c = '\b';
        int stride = (width + 7) / 8;
        ByteArrayOutputStream packed = new ByteArrayOutputStream(stride * targetHeight);
        float scale = width / ((float) geometry.getSourceWidth());
        int bandTop = 0;
        while (bandTop < targetHeight) {
            if (canceled.get()) {
                throw new CancellationException("Pencetakan dibatalkan");
            }
            int bandHeight = Math.min(RENDER_BAND_HEIGHT, targetHeight - bandTop);
            Bitmap bitmap2 = Bitmap.createBitmap(width, bandHeight, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(bitmap2, "createBitmap(...)");
            try {
                bitmap2.eraseColor(-1);
                Matrix matrix = new Matrix();
                char c2 = c;
                int stride2 = stride;
                float f = (float) ((-geometry.getSourceLeft()) * scale);
                float f2 = -bandTop;
                try {
                    float[] fArr = new float[9];
                    fArr[0] = scale;
                    fArr[1] = 0.0f;
                    fArr[2] = f;
                    fArr[3] = 0.0f;
                    fArr[4] = scale;
                    fArr[5] = f2;
                    fArr[6] = 0.0f;
                    fArr[7] = 0.0f;
                    fArr[c2] = 1.0f;
                    matrix.setValues(fArr);
                    page.render(bitmap2, null, matrix, 2);
                    int[] pixels = new int[width * bandHeight];
                    int width2 = width;
                    try {
                        bitmap2.getPixels(pixels, 0, width2, 0, 0, width, bandHeight);
                        bitmap = bitmap2;
                        width = width2;
                        try {
                            try {
                                PackedRaster raster = MonochromeRasterizer.INSTANCE.rasterize(pixels, width, bandHeight, config.getDitherMode(), config.getThreshold(), false);
                                packed.write(raster.getData());
                                bitmap.recycle();
                                bandTop += bandHeight;
                                c = c2;
                                stride = stride2;
                            } catch (Throwable th) {
                                th = th;
                                bitmap.recycle();
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bitmap = bitmap2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bitmap = bitmap2;
                }
            } catch (Throwable th5) {
                th = th5;
                bitmap = bitmap2;
            }
        }
        byte[] byteArray = packed.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        PackedRaster fullPage = new PackedRaster(width, targetHeight, byteArray);
        return config.getTrimWhitespace() ? MonochromeRasterizer.INSTANCE.trimWhiteRows(fullPage) : fullPage;
    }

    private final PageGeometry resolvePageGeometry(PdfRenderer.Page page, PrinterProfile profile, Double fitMaxScale, AtomicBoolean canceled) {
        HorizontalInkBounds ink;
        PageGeometry base = INSTANCE.pageGeometry(page.getWidth(), page.getHeight(), profile);
        if (fitMaxScale == null || (ink = findHorizontalInkBounds(page, canceled)) == null) {
            return base;
        }
        PageGeometry fitted = INSTANCE.autoFitGeometry(base, page.getHeight(), ink.getLeft(), ink.getRight(), fitMaxScale.doubleValue());
        if (!Intrinsics.areEqual(fitted, base)) {
            String format = String.format("Auto-fit aktif: sumber %.1fâ†’%.1f px, tinggi %dâ†’%d dot", Arrays.copyOf(new Object[]{Double.valueOf(base.getSourceWidth()), Double.valueOf(fitted.getSourceWidth()), Integer.valueOf(base.getTargetHeight()), Integer.valueOf(fitted.getTargetHeight())}, 4));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Log.i(TAG, format);
        }
        return fitted;
    }

    private final HorizontalInkBounds findHorizontalInkBounds(PdfRenderer.Page page, AtomicBoolean canceled) {
        int scanWidth = RangesKt.coerceAtLeast(Math.min(AUTO_FIT_SCAN_WIDTH, page.getWidth()), 1);
        float scale = scanWidth / page.getWidth();
        int scanHeight = RangesKt.coerceAtLeast((int) Math.ceil(page.getHeight() * scale), 1);
        int firstInk = scanWidth;
        int lastInk = -1;
        int bandTop = 0;
        while (bandTop < scanHeight) {
            if (canceled.get()) {
                throw new CancellationException("Pencetakan dibatalkan");
            }
            int bandHeight = Math.min(RENDER_BAND_HEIGHT, scanHeight - bandTop);
            Bitmap bitmap = Bitmap.createBitmap(scanWidth, bandHeight, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(bitmap, "createBitmap(...)");
            try {
                bitmap.eraseColor(-1);
                Matrix matrix = new Matrix();
                matrix.setScale(scale, scale);
                matrix.postTranslate(0.0f, -bandTop);
                try {
                    page.render(bitmap, null, matrix, 2);
                    int[] pixels = new int[scanWidth * bandHeight];
                    bitmap.getPixels(pixels, 0, scanWidth, 0, 0, scanWidth, bandHeight);
                    for (int y = 0; y < bandHeight; y++) {
                        int offset = y * scanWidth;
                        for (int x = 0; x < scanWidth; x++) {
                            try {
                                if (isInk(pixels[offset + x])) {
                                    firstInk = Math.min(firstInk, x);
                                    lastInk = Math.max(lastInk, x);
                                }
                            } catch (Throwable th) {
                                th = th;
                                bitmap.recycle();
                                throw th;
                            }
                        }
                    }
                    bitmap.recycle();
                    bandTop += bandHeight;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (lastInk < firstInk) {
            return null;
        }
        return new HorizontalInkBounds(firstInk / scale, (lastInk + 1) / scale);
    }

    private final boolean isInk(int color) {
        int alpha = (color >>> 24) & KotlinVersion.MAX_COMPONENT_VALUE;
        if (alpha < 24) {
            return false;
        }
        int red = (color >>> 16) & KotlinVersion.MAX_COMPONENT_VALUE;
        int green = (color >>> 8) & KotlinVersion.MAX_COMPONENT_VALUE;
        int blue = color & KotlinVersion.MAX_COMPONENT_VALUE;
        float luminance = (red * 0.299f) + (green * 0.587f) + (blue * 0.114f);
        return luminance < AUTO_FIT_INK_LUMINANCE;
    }

    /* compiled from: PdfReceiptRenderer.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J&\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bJ \u0010\f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000bH\u0002J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011J0\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000bX\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bX\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000bX\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000bX\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082TÂ¢\u0006\u0002\n\u0000Â¨\u0006!"}, d2 = {"Lcom/rapprinter/smartprint/rendering/PdfReceiptRenderer$Companion;", "", "<init>", "()V", "RENDER_BAND_HEIGHT", "", "scaledHeight", "pageWidth", "pageHeight", "targetWidth", "printableWidthFraction", "", "scaledHeightForSourceWidth", "sourceWidth", "pageGeometry", "Lcom/rapprinter/smartprint/rendering/PageGeometry;", "profile", "Lcom/rapprinter/smartprint/printer/PrinterProfile;", "autoFitGeometry", "base", "inkLeft", "inkRight", "maxScale", "AUTO_FIT_SCAN_WIDTH", "AUTO_FIT_INK_LUMINANCE", "", "AUTO_FIT_PADDING_RATIO", "AUTO_FIT_MIN_SIDE_INSET_RATIO", "AUTO_FIT_MAX_CONTENT_WIDTH_FRACTION", "AUTO_FIT_MAX_SCALE", "ODOO_80_TO_58_MAX_SCALE", "TAG", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int scaledHeight(int pageWidth, int pageHeight, int targetWidth) {
            return scaledHeight(pageWidth, pageHeight, targetWidth, 1.0d);
        }

        public final int scaledHeight(int pageWidth, int pageHeight, int targetWidth, double printableWidthFraction) {
            if (!(pageWidth > 0 && pageHeight > 0 && targetWidth > 0)) {
                throw new IllegalArgumentException("Dimensi halaman PDF tidak valid".toString());
            }
            if (!(printableWidthFraction > 0.0d && printableWidthFraction <= 1.0d)) {
                throw new IllegalArgumentException("Area cetak aktif printer tidak valid".toString());
            }
            double sourceWidth = pageWidth * printableWidthFraction;
            return scaledHeightForSourceWidth(pageHeight, targetWidth, sourceWidth);
        }

        private final int scaledHeightForSourceWidth(int pageHeight, int targetWidth, double sourceWidth) {
            double exactHeight = (targetWidth * pageHeight) / sourceWidth;
            if (!(((Math.abs(exactHeight) > Double.MAX_VALUE ? 1 : (Math.abs(exactHeight) == Double.MAX_VALUE ? 0 : -1)) <= 0) && exactHeight <= 100000.0d)) {
                throw new IllegalArgumentException(("Halaman terlalu panjang untuk diproses dengan aman (" + (Math.abs(exactHeight) <= Double.MAX_VALUE ? Integer.valueOf(MathKt.roundToInt(exactHeight)) : "tak terhingga") + " dot); pecah nota menjadi beberapa halaman").toString());
            }
            int height = RangesKt.coerceAtLeast(MathKt.roundToInt(exactHeight), 1);
            PrintLimits.INSTANCE.requirePageHeight(height);
            return height;
        }

        public final PageGeometry pageGeometry(int pageWidth, int pageHeight, PrinterProfile profile) {
            Intrinsics.checkNotNullParameter(profile, "profile");
            int height = scaledHeight(pageWidth, pageHeight, profile.getDotWidth());
            return new PageGeometry(0.0d, pageWidth, profile.getDotWidth(), height);
        }

        public static /* synthetic */ PageGeometry autoFitGeometry$default(Companion companion, PageGeometry pageGeometry, int i, double d, double d2, double d3, int i2, Object obj) {
            double d4;
            if ((i2 & 16) == 0) {
                d4 = d3;
            } else {
                d4 = 1.25d;
            }
            return companion.autoFitGeometry(pageGeometry, i, d, d2, d4);
        }

        public final PageGeometry autoFitGeometry(PageGeometry base, int pageHeight, double inkLeft, double inkRight, double maxScale) {
            double fittedRight;
            Intrinsics.checkNotNullParameter(base, "base");
            if (inkRight <= inkLeft || pageHeight <= 0) {
                return base;
            }
            if (!(maxScale >= 1.0d)) {
                throw new IllegalArgumentException("Skala auto-fit harus minimal 1".toString());
            }
            double baseRight = base.getSourceLeft() + base.getSourceWidth();
            double padding = base.getSourceWidth() * PdfReceiptRenderer.AUTO_FIT_PADDING_RATIO;
            double candidateLeft = Math.max(base.getSourceLeft(), inkLeft - padding);
            double candidateRight = Math.min(baseRight, inkRight + padding);
            double contentWidth = candidateRight - candidateLeft;
            double requiredInset = base.getSourceWidth() * PdfReceiptRenderer.AUTO_FIT_MIN_SIDE_INSET_RATIO;
            if (contentWidth > 0.0d && contentWidth < base.getSourceWidth() * PdfReceiptRenderer.AUTO_FIT_MAX_CONTENT_WIDTH_FRACTION && candidateLeft > base.getSourceLeft() + requiredInset && candidateRight < baseRight - requiredInset) {
                double targetWidth = Math.max(contentWidth, base.getSourceWidth() / maxScale);
                double contentCenter = (candidateLeft + candidateRight) / 2.0d;
                double fittedLeft = contentCenter - (targetWidth / 2.0d);
                double fittedRight2 = contentCenter + (targetWidth / 2.0d);
                if (fittedLeft < base.getSourceLeft()) {
                    fittedRight2 += base.getSourceLeft() - fittedLeft;
                    fittedLeft = base.getSourceLeft();
                }
                if (fittedRight2 <= baseRight) {
                    fittedRight = fittedRight2;
                } else {
                    fittedLeft -= fittedRight2 - baseRight;
                    fittedRight = baseRight;
                }
                double contentWidth2 = fittedLeft;
                double fittedLeft2 = fittedRight - contentWidth2;
                return PageGeometry.copy$default(base, contentWidth2, fittedLeft2, 0, scaledHeightForSourceWidth(pageHeight, base.getTargetWidth(), fittedLeft2), 4, null);
            }
            return base;
        }
    }
}

