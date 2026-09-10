package com.clvprinter.smartprint.printer;

import com.clvprinter.smartprint.storage.JobStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReceiptPipeline.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bÂ¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÃ†\u0003J\t\u0010\u0015\u001a\u00020\u0005HÃ†\u0003J\t\u0010\u0016\u001a\u00020\u0005HÃ†\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÃ†\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\bHÃ†\u0003J?\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bHÃ†\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÃ–\u0003J\t\u0010\u001d\u001a\u00020\u001eHÃ–\u0001J\t\u0010\u001f\u001a\u00020\bHÃ–\u0001R\u0011\u0010\u0002\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\bÂ¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\bÂ¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012Â¨\u0006 "}, d2 = {"Lcom/rapprinter/smartprint/printer/PipelineResult;", "", "status", "Lcom/rapprinter/smartprint/storage/JobStatus;", "bytesSent", "", "totalBytes", "error", "", "help", "<init>", "(Lcom/rapprinter/smartprint/storage/JobStatus;JJLjava/lang/String;Ljava/lang/String;)V", "getStatus", "()Lcom/rapprinter/smartprint/storage/JobStatus;", "getBytesSent", "()J", "getTotalBytes", "getError", "()Ljava/lang/String;", "getHelp", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PipelineResult {
    private final long bytesSent;
    private final String error;
    private final String help;
    private final JobStatus status;
    private final long totalBytes;

    public static /* synthetic */ PipelineResult copy$default(PipelineResult pipelineResult, JobStatus jobStatus, long j, long j2, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            jobStatus = pipelineResult.status;
        }
        if ((i & 2) != 0) {
            j = pipelineResult.bytesSent;
        }
        if ((i & 4) != 0) {
            j2 = pipelineResult.totalBytes;
        }
        if ((i & 8) != 0) {
            str = pipelineResult.error;
        }
        if ((i & 16) != 0) {
            str2 = pipelineResult.help;
        }
        long j3 = j2;
        return pipelineResult.copy(jobStatus, j, j3, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final JobStatus getStatus() {
        return this.status;
    }

    /* renamed from: component2, reason: from getter */
    public final long getBytesSent() {
        return this.bytesSent;
    }

    /* renamed from: component3, reason: from getter */
    public final long getTotalBytes() {
        return this.totalBytes;
    }

    /* renamed from: component4, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* renamed from: component5, reason: from getter */
    public final String getHelp() {
        return this.help;
    }

    public final PipelineResult copy(JobStatus status, long bytesSent, long totalBytes, String error, String help) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new PipelineResult(status, bytesSent, totalBytes, error, help);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PipelineResult)) {
            return false;
        }
        PipelineResult pipelineResult = (PipelineResult) other;
        return this.status == pipelineResult.status && this.bytesSent == pipelineResult.bytesSent && this.totalBytes == pipelineResult.totalBytes && Intrinsics.areEqual(this.error, pipelineResult.error) && Intrinsics.areEqual(this.help, pipelineResult.help);
    }

    public int hashCode() {
        return (((((((this.status.hashCode() * 31) + Long.hashCode(this.bytesSent)) * 31) + Long.hashCode(this.totalBytes)) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31) + (this.help != null ? this.help.hashCode() : 0);
    }

    public String toString() {
        return "PipelineResult(status=" + this.status + ", bytesSent=" + this.bytesSent + ", totalBytes=" + this.totalBytes + ", error=" + this.error + ", help=" + this.help + ")";
    }

    public PipelineResult(JobStatus status, long bytesSent, long totalBytes, String error, String help) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
        this.bytesSent = bytesSent;
        this.totalBytes = totalBytes;
        this.error = error;
        this.help = help;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ PipelineResult(JobStatus jobStatus, long j, long j2, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(jobStatus, j, j2, str, r9);
        String str3;
        str = (i & 8) != 0 ? null : str;
        if ((i & 16) == 0) {
            str3 = str2;
        } else {
            str3 = null;
        }
    }

    public final JobStatus getStatus() {
        return this.status;
    }

    public final long getBytesSent() {
        return this.bytesSent;
    }

    public final long getTotalBytes() {
        return this.totalBytes;
    }

    public final String getError() {
        return this.error;
    }

    public final String getHelp() {
        return this.help;
    }
}

