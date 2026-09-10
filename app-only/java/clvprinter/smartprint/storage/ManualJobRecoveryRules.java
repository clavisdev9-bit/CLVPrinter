package com.clvprinter.smartprint.storage;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ManualJobRecoveryRules.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ†\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007Â¨\u0006\b"}, d2 = {"Lcom/rapprinter/smartprint/storage/ManualJobRecoveryRules;", "", "<init>", "()V", "interruptedStatus", "Lcom/rapprinter/smartprint/storage/JobStatus;", "record", "Lcom/rapprinter/smartprint/storage/JobRecord;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class ManualJobRecoveryRules {
    public static final ManualJobRecoveryRules INSTANCE = new ManualJobRecoveryRules();

    private ManualJobRecoveryRules() {
    }

    public final JobStatus interruptedStatus(JobRecord record) {
        Intrinsics.checkNotNullParameter(record, "record");
        boolean isManual = StringsKt.startsWith$default(record.getId(), "test-", false, 2, (Object) null) || StringsKt.startsWith$default(record.getId(), "retry-", false, 2, (Object) null);
        boolean wasActive = record.getStatus() == JobStatus.QUEUED || record.getStatus() == JobStatus.PRINTING;
        if (isManual && wasActive) {
            return (record.getDeliveryAttempted() || record.getBytesSent() > 0) ? JobStatus.UNKNOWN : JobStatus.FAILED;
        }
        return null;
    }
}

