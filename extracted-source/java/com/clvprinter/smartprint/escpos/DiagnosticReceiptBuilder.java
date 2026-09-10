package com.clvprinter.smartprint.escpos;

import com.clvprinter.smartprint.printer.PrinterConfig;
import com.clvprinter.smartprint.rendering.PackedRaster;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlin.uuid.Uuid;

/* compiled from: DiagnosticReceiptBuilder.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0002J&\u0010\u000f\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002J\u0014\u0010\u001a\u001a\u00020\u0015*\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u001c\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001f¨\u0006 "}, d2 = {"Lcom/clvprinter/smartprint/escpos/DiagnosticReceiptBuilder;", "", "<init>", "()V", "build", "", "config", "Lcom/clvprinter/smartprint/printer/PrinterConfig;", "qrRaster", "Lcom/clvprinter/smartprint/rendering/PackedRaster;", "dotWidth", "", "code128Raster", "value", "", "matrixRaster", "rows", "", "scale", "quietModules", "setBlack", "", "data", "stride", "x", "y", "writeText", "Ljava/io/ByteArrayOutputStream;", "QR_MATRIX", "CODE_128_PATTERNS", "", "[Ljava/lang/String;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiagnosticReceiptBuilder {
    private static final String QR_MATRIX = "\n1111111010011010001111111\n1000001001100100001000001\n1011101000101100001011101\n1011101000001101101011101\n1011101001010110001011101\n1000001000101110101000001\n1111111010101010101111111\n0000000010110100100000000\n1101101001111011001000001\n1100100011001011000111110\n0011001100111111011001001\n1011010011110011001001111\n0011111001010000101000001\n1010100101010111110010010\n1110011010000101001011111\n1010000100011000101101101\n1011111010010100111110110\n0000000010000100100010110\n1111111001010100101010001\n1000001000100111100010010\n1011101010101001111110000\n1011101010110110011000011\n1011101000111101000011111\n1000001010010011011110111\n1111111011010000111001001\n";
    public static final DiagnosticReceiptBuilder INSTANCE = new DiagnosticReceiptBuilder();
    private static final String[] CODE_128_PATTERNS = {"212222", "222122", "222221", "121223", "121322", "131222", "122213", "122312", "132212", "221213", "221312", "231212", "112232", "122132", "122231", "113222", "123122", "123221", "223211", "221132", "221231", "213212", "223112", "312131", "311222", "321122", "321221", "312212", "322112", "322211", "212123", "212321", "232121", "111323", "131123", "131321", "112313", "132113", "132311", "211313", "231113", "231311", "112133", "112331", "132131", "113123", "113321", "133121", "313121", "211331", "231131", "213113", "213311", "213131", "311123", "311321", "331121", "312113", "312311", "332111", "314111", "221411", "431111", "111224", "111422", "121124", "121421", "141122", "141221", "112214", "112412", "122114", "122411", "142112", "142211", "241211", "221114", "413111", "241112", "134111", "111242", "121142", "121241", "114212", "124112", "124211", "411212", "421112", "421211", "212141", "214121", "412121", "111143", "111341", "131141", "114113", "114311", "411113", "411311", "113141", "114131", "311141", "411131", "211412", "211214", "211232", "2331112"};

    private DiagnosticReceiptBuilder() {
    }

    public final byte[] build(PrinterConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        output.write(EscPosEncoder.INSTANCE.initialize(config.getCodePage()));
        output.write(new byte[]{27, 97, 1, 27, 69, 1, 29, 33, 17});
        writeText(output, "CLVPRINTER\n");
        output.write(new byte[]{29, 33, 0, 27, 69, 0});
        writeText(output, "DIAGNOSTIK 58 MM\n");
        writeText(output, "--------------------------------\n");
        output.write(new byte[]{27, 97, 0});
        writeText(output, "Latin : ABC xyz\n");
        writeText(output, "Angka : 0123456789\n");
        writeText(output, "Profil: " + config.getProfile().getLabel() + "\n");
        writeText(output, "Mode  : " + config.getDitherMode().name() + "\n");
        writeText(output, "Waktu : " + new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.forLanguageTag("id-ID")).format(new Date()) + "\n");
        writeText(output, "--------------------------------\n");
        output.write(new byte[]{27, 97, 1});
        writeText(output, "QR TARGET\n");
        output.write(EscPosEncoder.INSTANCE.rasterCommands(qrRaster(config.getDotWidth()), 256));
        writeText(output, "clvprint.local/test\n\n");
        writeText(output, "CODE 128\n");
        output.write(EscPosEncoder.INSTANCE.rasterCommands(code128Raster("CLVPRINT123", config.getDotWidth()), 256));
        writeText(output, "CLVPRINT123\n");
        output.write(new byte[]{27, 97, 0});
        writeText(output, "--------------------------------\n");
        writeText(output, "[ ] teks tajam  [ ] QR terbaca\n");
        writeText(output, "[ ] barcode     [ ] potong/feed\n");
        int feedLines = config.getFeedLines();
        for (int i = 0; i < feedLines; i++) {
            output.write(10);
        }
        output.write(EscPosEncoder.INSTANCE.cut(config.getCutMode()));
        byte[] byteArray = output.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    private final PackedRaster qrRaster(int dotWidth) {
        Iterable lines = StringsKt.lines(QR_MATRIX);
        Collection arrayList = new ArrayList();
        for (Object obj : lines) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        List rows = (List) arrayList;
        int moduleScale = dotWidth >= 576 ? 8 : 5;
        return matrixRaster(rows, moduleScale, 4);
    }

    private final PackedRaster code128Raster(String value, int dotWidth) {
        CharSequence charSequence;
        String modules;
        String str = value;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= str.length()) {
                charSequence = 1;
                break;
            }
            char charAt = str.charAt(i2);
            if (((' ' > charAt || charAt >= 127) ? (char) 0 : (char) 1) == 0) {
                charSequence = null;
                break;
            }
            i2++;
        }
        if (charSequence == null) {
            throw new IllegalArgumentException("Code 128 diagnostik hanya mendukung ASCII".toString());
        }
        List codes = CollectionsKt.mutableListOf(104);
        String str2 = value;
        for (int i3 = 0; i3 < str2.length(); i3++) {
            codes.add(Integer.valueOf(str2.charAt(i3) - ' '));
        }
        int checksum = 104;
        int i4 = 0;
        for (Object obj : CollectionsKt.drop(codes, 1)) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            checksum += (i4 + 1) * ((Number) obj).intValue();
            i4 = i5;
        }
        codes.add(Integer.valueOf(checksum % 103));
        codes.add(106);
        StringBuilder sb = new StringBuilder();
        sb.append(StringsKt.repeat("0", 10));
        Iterator it = codes.iterator();
        while (it.hasNext()) {
            boolean z = true;
            CharSequence charSequence2 = CODE_128_PATTERNS[((Number) it.next()).intValue()];
            while (i < charSequence2.length()) {
                List codes2 = codes;
                sb.append(StringsKt.repeat(z ? "1" : "0", CharsKt.digitToInt(charSequence2.charAt(i))));
                z = !z;
                i++;
                codes = codes2;
            }
            i = 0;
        }
        sb.append(StringsKt.repeat("0", 10));
        String modules2 = sb.toString();
        int scale = dotWidth >= 576 ? 3 : 2;
        int width = modules2.length() * scale;
        int height = dotWidth >= 576 ? 104 : 72;
        int stride = (width + 7) / 8;
        byte[] data = new byte[stride * height];
        String str3 = modules2;
        int i6 = 0;
        int i7 = 0;
        while (i7 < str3.length()) {
            int i8 = i6 + 1;
            if (str3.charAt(i7) == '1') {
                int i9 = (i6 + 1) * scale;
                modules = modules2;
                int i10 = i6 * scale;
                while (i10 < i9) {
                    int i11 = i9;
                    int i12 = 0;
                    while (i12 < height) {
                        INSTANCE.setBlack(data, stride, i10, i12);
                        i12++;
                        scale = scale;
                    }
                    i10++;
                    i9 = i11;
                }
            } else {
                modules = modules2;
            }
            i7++;
            i6 = i8;
            modules2 = modules;
            scale = scale;
        }
        return new PackedRaster(width, height, data);
    }

    private final PackedRaster matrixRaster(List<String> rows, int scale, int quietModules) {
        Iterable iterable;
        int modulesWide = ((String) CollectionsKt.first((List) rows)).length() + (quietModules * 2);
        int width = modulesWide * scale;
        int height = (rows.size() + (quietModules * 2)) * scale;
        int stride = (width + 7) / 8;
        byte[] data = new byte[stride * height];
        List<String> list = rows;
        int i = 0;
        int i2 = 0;
        for (Object obj : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            CharSequence charSequence = (String) obj;
            int i4 = 0;
            int modulesWide2 = modulesWide;
            int modulesWide3 = 0;
            while (true) {
                iterable = list;
                if (modulesWide3 < charSequence.length()) {
                    int i5 = i4 + 1;
                    int i6 = modulesWide3;
                    if (charSequence.charAt(modulesWide3) == '1') {
                        int i7 = (i4 + quietModules) * scale;
                        int i8 = (i2 + quietModules) * scale;
                        int i9 = i8 + scale;
                        int i10 = i8;
                        while (i10 < i9) {
                            int i11 = i9;
                            int i12 = i;
                            int i13 = i7;
                            for (int i14 = i7 + scale; i13 < i14; i14 = i14) {
                                INSTANCE.setBlack(data, stride, i13, i10);
                                i13++;
                            }
                            i10++;
                            i9 = i11;
                            i = i12;
                        }
                    }
                    modulesWide3 = i6 + 1;
                    list = iterable;
                    i4 = i5;
                    i = i;
                }
            }
            i2 = i3;
            modulesWide = modulesWide2;
            list = iterable;
        }
        return new PackedRaster(width, height, data);
    }

    private final void setBlack(byte[] data, int stride, int x, int y) {
        int index = (y * stride) + (x / 8);
        data[index] = (byte) (data[index] | (Uuid.SIZE_BITS >>> (x % 8)));
    }

    private final void writeText(ByteArrayOutputStream $this$writeText, String value) {
        Charset forName = Charset.forName("CP437");
        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
        byte[] bytes = value.getBytes(forName);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        $this$writeText.write(bytes);
    }
}
