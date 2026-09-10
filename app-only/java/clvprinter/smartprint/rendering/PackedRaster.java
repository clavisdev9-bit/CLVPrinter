package com.clvprinter.smartprint.rendering;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: PackedRaster.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006Â¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003J\t\u0010\u0013\u001a\u00020\u0003HÃ†\u0003J\t\u0010\u0014\u001a\u00020\u0003HÃ†\u0003J\t\u0010\u0015\u001a\u00020\u0006HÃ†\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÃ†\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÃ–\u0003J\t\u0010\u001a\u001a\u00020\u0003HÃ–\u0001J\t\u0010\u001b\u001a\u00020\u001cHÃ–\u0001R\u0011\u0010\u0002\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006Â¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nÂ¨\u0006\u001d"}, d2 = {"Lcom/rapprinter/smartprint/rendering/PackedRaster;", "", "width", "", "height", "data", "", "<init>", "(II[B)V", "getWidth", "()I", "getHeight", "getData", "()[B", "bytesPerRow", "getBytesPerRow", "cropRows", "startInclusive", "endExclusive", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class PackedRaster {
    private final int bytesPerRow;
    private final byte[] data;
    private final int height;
    private final int width;

    public static /* synthetic */ PackedRaster copy$default(PackedRaster packedRaster, int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = packedRaster.width;
        }
        if ((i3 & 2) != 0) {
            i2 = packedRaster.height;
        }
        if ((i3 & 4) != 0) {
            bArr = packedRaster.data;
        }
        return packedRaster.copy(i, i2, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* renamed from: component2, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: component3, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    public final PackedRaster copy(int width, int height, byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new PackedRaster(width, height, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PackedRaster)) {
            return false;
        }
        PackedRaster packedRaster = (PackedRaster) other;
        return this.width == packedRaster.width && this.height == packedRaster.height && Intrinsics.areEqual(this.data, packedRaster.data);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.width) * 31) + Integer.hashCode(this.height)) * 31) + Arrays.hashCode(this.data);
    }

    public String toString() {
        return "PackedRaster(width=" + this.width + ", height=" + this.height + ", data=" + Arrays.toString(this.data) + ")";
    }

    public PackedRaster(int width, int height, byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.width = width;
        this.height = height;
        this.data = data;
        this.bytesPerRow = (int) RangesKt.coerceAtMost((this.width + 7) / 8, 2147483647L);
        if (!(this.width > 0)) {
            throw new IllegalArgumentException("Lebar raster harus positif".toString());
        }
        if (!(this.height > 0)) {
            throw new IllegalArgumentException("Tinggi raster harus positif".toString());
        }
        long expectedBytes = this.bytesPerRow * this.height;
        if (expectedBytes <= 2147483647L && ((long) this.data.length) == expectedBytes) {
        } else {
            throw new IllegalArgumentException("Ukuran data raster tidak sesuai atau melampaui batas array".toString());
        }
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final int getBytesPerRow() {
        return this.bytesPerRow;
    }

    public final PackedRaster cropRows(int startInclusive, int endExclusive) {
        int safeStart = RangesKt.coerceIn(startInclusive, 0, this.height - 1);
        int safeEnd = RangesKt.coerceIn(endExclusive, safeStart + 1, this.height);
        return new PackedRaster(this.width, safeEnd - safeStart, ArraysKt.copyOfRange(this.data, this.bytesPerRow * safeStart, this.bytesPerRow * safeEnd));
    }
}

