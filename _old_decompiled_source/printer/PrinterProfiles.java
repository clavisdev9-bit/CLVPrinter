package com.clvprinter.smartprint.printer;

import kotlin.Metadata;

/* compiled from: PrinterProfile.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\bÃ†\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0004\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007Â¨\u0006\r"}, d2 = {"Lcom/rapprinter/smartprint/printer/PrinterProfiles;", "", "<init>", "()V", "STANDARD_58", "Lcom/rapprinter/smartprint/printer/PrinterProfile;", "getSTANDARD_58", "()Lcom/rapprinter/smartprint/printer/PrinterProfile;", "WIDE_58", "getWIDE_58", "forDotWidth", "dotWidth", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PrinterProfiles {
    public static final PrinterProfiles INSTANCE = new PrinterProfiles();
    private static final PrinterProfile STANDARD_58 = new PrinterProfile(PrinterConfig.DEFAULT_DOT_WIDTH, 203, "384 dot / 203 dpi", 0, 8, null);
    private static final PrinterProfile WIDE_58 = new PrinterProfile(PrinterConfig.WIDE_DOT_WIDTH, 300, "576 dot / 300 dpi", 0, 8, null);

    private PrinterProfiles() {
    }

    public final PrinterProfile getSTANDARD_58() {
        return STANDARD_58;
    }

    public final PrinterProfile getWIDE_58() {
        return WIDE_58;
    }

    public final PrinterProfile forDotWidth(int dotWidth) {
        return dotWidth == WIDE_58.getDotWidth() ? WIDE_58 : STANDARD_58;
    }
}

