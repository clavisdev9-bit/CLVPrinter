package com.clvprinter.smartprint.storage;

import kotlin.Metadata;

/* compiled from: PrintJobRecoveryRules.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ†\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007Â¨\u0006\t"}, d2 = {"Lcom/rapprinter/smartprint/storage/PrintJobRecoveryRules;", "", "<init>", "()V", "mustHoldBeforeResend", "", "previous", "Lcom/rapprinter/smartprint/storage/JobRecord;", "activeJobIsAmbiguous", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class PrintJobRecoveryRules {
    public static final PrintJobRecoveryRules INSTANCE = new PrintJobRecoveryRules();

    private PrintJobRecoveryRules() {
    }

    public final boolean mustHoldBeforeResend(JobRecord previous) {
        return (previous != null ? previous.getStatus() : null) == JobStatus.UNKNOWN || (previous != null && (previous.getDeliveryAttempted() || previous.getBytesSent() > 0));
    }

    public final boolean activeJobIsAmbiguous(JobRecord previous) {
        return previous == null || mustHoldBeforeResend(previous);
    }
}

