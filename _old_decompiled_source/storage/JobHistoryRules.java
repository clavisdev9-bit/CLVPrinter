package com.clvprinter.smartprint.storage;

import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: JobHistoryRules.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\bÃ†\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tJ \u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\tJ\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tÂ¨\u0006\u0013"}, d2 = {"Lcom/rapprinter/smartprint/storage/JobHistoryRules;", "", "<init>", "()V", "tombstoneKey", "", "sourceJobId", "fingerprint", "mayRetry", "", "status", "Lcom/rapprinter/smartprint/storage/JobStatus;", "hasCachedPdf", "requiresDuplicateWarning", "bytesSent", "", "deliveryAttempted", "failureStatus", "payloadMayHaveStarted", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class JobHistoryRules {
    public static final JobHistoryRules INSTANCE = new JobHistoryRules();

    private JobHistoryRules() {
    }

    public final String tombstoneKey(String sourceJobId, String fingerprint) {
        Intrinsics.checkNotNullParameter(sourceJobId, "sourceJobId");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        String str = fingerprint;
        if (StringsKt.isBlank(str)) {
            str = "pending";
        }
        return sourceJobId + ":" + ((Object) str);
    }

    public final boolean mayRetry(JobStatus status, boolean hasCachedPdf) {
        Intrinsics.checkNotNullParameter(status, "status");
        return hasCachedPdf && SetsKt.setOf((Object[]) new JobStatus[]{JobStatus.FAILED, JobStatus.CANCELED, JobStatus.UNKNOWN}).contains(status);
    }

    public static /* synthetic */ boolean requiresDuplicateWarning$default(JobHistoryRules jobHistoryRules, JobStatus jobStatus, long j, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return jobHistoryRules.requiresDuplicateWarning(jobStatus, j, z);
    }

    public final boolean requiresDuplicateWarning(JobStatus status, long bytesSent, boolean deliveryAttempted) {
        Intrinsics.checkNotNullParameter(status, "status");
        return status == JobStatus.UNKNOWN || deliveryAttempted || bytesSent > 0;
    }

    public final JobStatus failureStatus(boolean payloadMayHaveStarted) {
        return payloadMayHaveStarted ? JobStatus.UNKNOWN : JobStatus.FAILED;
    }
}

