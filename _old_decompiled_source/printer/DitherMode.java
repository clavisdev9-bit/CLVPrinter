package com.clvprinter.smartprint.printer;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: PrinterConfig.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005Â¨\u0006\u0006"}, d2 = {"Lcom/rapprinter/smartprint/printer/DitherMode;", "", "<init>", "(Ljava/lang/String;I)V", "FLOYD_STEINBERG", "THRESHOLD", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum DitherMode {
    FLOYD_STEINBERG,
    THRESHOLD;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<DitherMode> getEntries() {
        return $ENTRIES;
    }
}

