package com.clvprinter.smartprint.rendering;

import com.clvprinter.smartprint.printer.DitherMode;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.uuid.Uuid;

/* compiled from: MonochromeRasterizer.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\bÃ†\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bJ8\u0010\u000f\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J8\u0010\u0018\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002J(\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082TÂ¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082TÂ¢\u0006\u0002\n\u0000Â¨\u0006 "}, d2 = {"Lcom/rapprinter/smartprint/rendering/MonochromeRasterizer;", "", "<init>", "()V", "SAFETY_MARGIN_ROWS", "", "MIN_BLANK_HEIGHT", "rasterize", "Lcom/rapprinter/smartprint/rendering/PackedRaster;", "argb", "", "width", "height", "mode", "Lcom/rapprinter/smartprint/printer/DitherMode;", "threshold", "trimWhitespace", "", "trimWhiteRows", "raster", "", "output", "", "stride", "dither", "trim", "setBlack", "x", "y", "luminance", "", "color", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class MonochromeRasterizer {
    public static final MonochromeRasterizer INSTANCE = new MonochromeRasterizer();
    private static final int MIN_BLANK_HEIGHT = 24;
    private static final int SAFETY_MARGIN_ROWS = 8;

    /* compiled from: MonochromeRasterizer.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DitherMode.values().length];
            try {
                iArr[DitherMode.THRESHOLD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DitherMode.FLOYD_STEINBERG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private MonochromeRasterizer() {
    }

    public final PackedRaster rasterize(int[] argb, int width, int height, DitherMode mode, int threshold, boolean trimWhitespace) {
        int width2;
        int height2;
        Intrinsics.checkNotNullParameter(argb, "argb");
        Intrinsics.checkNotNullParameter(mode, "mode");
        if (!(width > 0 && height > 0)) {
            throw new IllegalArgumentException("Dimensi gambar tidak valid".toString());
        }
        if (!(argb.length >= width * height)) {
            throw new IllegalArgumentException("Buffer piksel terlalu kecil".toString());
        }
        int stride = (width + 7) / 8;
        byte[] output = new byte[stride * height];
        int safeThreshold = RangesKt.coerceIn(threshold, 0, KotlinVersion.MAX_COMPONENT_VALUE);
        switch (WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
            case 1:
                width2 = width;
                height2 = height;
                threshold(argb, width2, height2, safeThreshold, output, stride);
                break;
            case 2:
                width2 = width;
                height2 = height;
                dither(argb, width2, height2, safeThreshold, output, stride);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        PackedRaster raster = new PackedRaster(width2, height2, output);
        return trimWhitespace ? trimWhiteRows(raster) : raster;
    }

    public final PackedRaster trimWhiteRows(PackedRaster raster) {
        Intrinsics.checkNotNullParameter(raster, "raster");
        return trim(raster);
    }

    private final void threshold(int[] argb, int width, int height, int threshold, byte[] output, int stride) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (luminance(argb[(y * width) + x]) < threshold) {
                    setBlack(output, stride, x, y);
                }
            }
        }
    }

    private final void dither(int[] argb, int width, int height, int threshold, byte[] output, int stride) {
        float[] currentError = new float[width + 2];
        float[] nextError = new float[width + 2];
        for (int y = 0; y < height; y++) {
            ArraysKt.fill$default(nextError, 0.0f, 0, 0, 6, (Object) null);
            for (int x = 0; x < width; x++) {
                float corrected = RangesKt.coerceIn(luminance(argb[(y * width) + x]) + currentError[x + 1], 0.0f, 255.0f);
                float quantized = corrected < ((float) threshold) ? 0.0f : 255.0f;
                if (quantized == 0.0f) {
                    setBlack(output, stride, x, y);
                }
                float error = corrected - quantized;
                int i = x + 2;
                currentError[i] = currentError[i] + ((7.0f * error) / 16.0f);
                nextError[x] = nextError[x] + ((3.0f * error) / 16.0f);
                int i2 = x + 1;
                nextError[i2] = nextError[i2] + ((5.0f * error) / 16.0f);
                int i3 = x + 2;
                nextError[i3] = nextError[i3] + (error / 16.0f);
            }
            float[] swap = currentError;
            currentError = nextError;
            nextError = swap;
        }
    }

    private final PackedRaster trim(PackedRaster raster) {
        int stride = raster.getBytesPerRow();
        int firstInk = -1;
        int lastInk = -1;
        int height = raster.getHeight();
        for (int row = 0; row < height; row++) {
            boolean hasInk = false;
            int offset = row * stride;
            int index = offset;
            int i = offset + stride;
            while (true) {
                if (index >= i) {
                    break;
                }
                if (raster.getData()[index] == 0) {
                    index++;
                } else {
                    hasInk = true;
                    break;
                }
            }
            if (hasInk) {
                if (firstInk < 0) {
                    firstInk = row;
                }
                lastInk = row;
            }
        }
        if (firstInk < 0) {
            int blankHeight = Math.min(raster.getHeight(), MIN_BLANK_HEIGHT);
            return new PackedRaster(raster.getWidth(), blankHeight, new byte[stride * blankHeight]);
        }
        int start = RangesKt.coerceAtLeast(firstInk - 8, 0);
        int end = RangesKt.coerceAtMost(lastInk + 8 + 1, raster.getHeight());
        return raster.cropRows(start, end);
    }

    private final void setBlack(byte[] output, int stride, int x, int y) {
        int index = (y * stride) + (x / 8);
        output[index] = (byte) (output[index] | (Uuid.SIZE_BITS >>> (x % 8)));
    }

    private final float luminance(int color) {
        int alpha = (color >>> MIN_BLANK_HEIGHT) & KotlinVersion.MAX_COMPONENT_VALUE;
        int red = (color >>> 16) & KotlinVersion.MAX_COMPONENT_VALUE;
        int green = (color >>> 8) & KotlinVersion.MAX_COMPONENT_VALUE;
        int blue = color & KotlinVersion.MAX_COMPONENT_VALUE;
        int source = MathKt.roundToInt((red * 0.299f) + (green * 0.587f) + (blue * 0.114f));
        return ((source * alpha) + ((255 - alpha) * KotlinVersion.MAX_COMPONENT_VALUE)) / 255.0f;
    }
}

