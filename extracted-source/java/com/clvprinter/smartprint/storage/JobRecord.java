package com.clvprinter.smartprint.storage;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.ConstantsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.uuid.Uuid;
import org.json.JSONObject;

/* compiled from: JobRecord.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0086\b\u0018\u0000 G2\u00020\u0001:\u0001GB¡\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u00100\u001a\u000201J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\fHÆ\u0003J\t\u0010:\u001a\u00020\fHÆ\u0003J\t\u0010;\u001a\u00020\u000fHÆ\u0003J\t\u0010<\u001a\u00020\u0007HÆ\u0003J\t\u0010=\u001a\u00020\u0007HÆ\u0003J\t\u0010>\u001a\u00020\u0013HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¯\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010C\u001a\u00020\u000f2\b\u0010D\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010E\u001a\u00020\fHÖ\u0001J\t\u0010F\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001aR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001aR\u0011\u0010.\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b/\u0010#¨\u0006H"}, d2 = {"Lcom/clvprinter/smartprint/storage/JobRecord;", "", "id", "", "sourceJobId", "fingerprint", "timestamp", "", "printerName", "printerAddress", "documentName", "attempt", "", "requestedCopies", "deliveryAttempted", "", "bytesSent", "totalBytes", "status", "Lcom/clvprinter/smartprint/storage/JobStatus;", "error", "help", "cachedPdfPath", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZJJLcom/clvprinter/smartprint/storage/JobStatus;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getSourceJobId", "getFingerprint", "getTimestamp", "()J", "getPrinterName", "getPrinterAddress", "getDocumentName", "getAttempt", "()I", "getRequestedCopies", "getDeliveryAttempted", "()Z", "getBytesSent", "getTotalBytes", "getStatus", "()Lcom/clvprinter/smartprint/storage/JobStatus;", "getError", "getHelp", "getCachedPdfPath", "progressPercent", "getProgressPercent", "toJson", "Lorg/json/JSONObject;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "equals", "other", "hashCode", "toString", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final /* data */ class JobRecord {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int attempt;
    private final long bytesSent;
    private final String cachedPdfPath;
    private final boolean deliveryAttempted;
    private final String documentName;
    private final String error;
    private final String fingerprint;
    private final String help;
    private final String id;
    private final String printerAddress;
    private final String printerName;
    private final int requestedCopies;
    private final String sourceJobId;
    private final JobStatus status;
    private final long timestamp;
    private final long totalBytes;

    public static /* synthetic */ JobRecord copy$default(JobRecord jobRecord, String str, String str2, String str3, long j, String str4, String str5, String str6, int i, int i2, boolean z, long j2, long j3, JobStatus jobStatus, String str7, String str8, String str9, int i3, Object obj) {
        String str10 = (i3 & 1) != 0 ? jobRecord.id : str;
        String str11 = (i3 & 2) != 0 ? jobRecord.sourceJobId : str2;
        String str12 = (i3 & 4) != 0 ? jobRecord.fingerprint : str3;
        long j4 = (i3 & 8) != 0 ? jobRecord.timestamp : j;
        String str13 = (i3 & 16) != 0 ? jobRecord.printerName : str4;
        String str14 = (i3 & 32) != 0 ? jobRecord.printerAddress : str5;
        String str15 = (i3 & 64) != 0 ? jobRecord.documentName : str6;
        int i4 = (i3 & Uuid.SIZE_BITS) != 0 ? jobRecord.attempt : i;
        int i5 = (i3 & 256) != 0 ? jobRecord.requestedCopies : i2;
        boolean z2 = (i3 & ConstantsKt.MINIMUM_BLOCK_SIZE) != 0 ? jobRecord.deliveryAttempted : z;
        long j5 = (i3 & 1024) != 0 ? jobRecord.bytesSent : j2;
        String str16 = str10;
        String str17 = str11;
        long j6 = (i3 & 2048) != 0 ? jobRecord.totalBytes : j3;
        return jobRecord.copy(str16, str17, str12, j4, str13, str14, str15, i4, i5, z2, j5, j6, (i3 & ConstantsKt.DEFAULT_BLOCK_SIZE) != 0 ? jobRecord.status : jobStatus, (i3 & ConstantsKt.DEFAULT_BUFFER_SIZE) != 0 ? jobRecord.error : str7, (i3 & 16384) != 0 ? jobRecord.help : str8, (i3 & 32768) != 0 ? jobRecord.cachedPdfPath : str9);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getDeliveryAttempted() {
        return this.deliveryAttempted;
    }

    /* renamed from: component11, reason: from getter */
    public final long getBytesSent() {
        return this.bytesSent;
    }

    /* renamed from: component12, reason: from getter */
    public final long getTotalBytes() {
        return this.totalBytes;
    }

    /* renamed from: component13, reason: from getter */
    public final JobStatus getStatus() {
        return this.status;
    }

    /* renamed from: component14, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* renamed from: component15, reason: from getter */
    public final String getHelp() {
        return this.help;
    }

    /* renamed from: component16, reason: from getter */
    public final String getCachedPdfPath() {
        return this.cachedPdfPath;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSourceJobId() {
        return this.sourceJobId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFingerprint() {
        return this.fingerprint;
    }

    /* renamed from: component4, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPrinterName() {
        return this.printerName;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPrinterAddress() {
        return this.printerAddress;
    }

    /* renamed from: component7, reason: from getter */
    public final String getDocumentName() {
        return this.documentName;
    }

    /* renamed from: component8, reason: from getter */
    public final int getAttempt() {
        return this.attempt;
    }

    /* renamed from: component9, reason: from getter */
    public final int getRequestedCopies() {
        return this.requestedCopies;
    }

    public final JobRecord copy(String id, String sourceJobId, String fingerprint, long timestamp, String printerName, String printerAddress, String documentName, int attempt, int requestedCopies, boolean deliveryAttempted, long bytesSent, long totalBytes, JobStatus status, String error, String help, String cachedPdfPath) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(sourceJobId, "sourceJobId");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(printerName, "printerName");
        Intrinsics.checkNotNullParameter(printerAddress, "printerAddress");
        Intrinsics.checkNotNullParameter(documentName, "documentName");
        Intrinsics.checkNotNullParameter(status, "status");
        return new JobRecord(id, sourceJobId, fingerprint, timestamp, printerName, printerAddress, documentName, attempt, requestedCopies, deliveryAttempted, bytesSent, totalBytes, status, error, help, cachedPdfPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JobRecord)) {
            return false;
        }
        JobRecord jobRecord = (JobRecord) other;
        return Intrinsics.areEqual(this.id, jobRecord.id) && Intrinsics.areEqual(this.sourceJobId, jobRecord.sourceJobId) && Intrinsics.areEqual(this.fingerprint, jobRecord.fingerprint) && this.timestamp == jobRecord.timestamp && Intrinsics.areEqual(this.printerName, jobRecord.printerName) && Intrinsics.areEqual(this.printerAddress, jobRecord.printerAddress) && Intrinsics.areEqual(this.documentName, jobRecord.documentName) && this.attempt == jobRecord.attempt && this.requestedCopies == jobRecord.requestedCopies && this.deliveryAttempted == jobRecord.deliveryAttempted && this.bytesSent == jobRecord.bytesSent && this.totalBytes == jobRecord.totalBytes && this.status == jobRecord.status && Intrinsics.areEqual(this.error, jobRecord.error) && Intrinsics.areEqual(this.help, jobRecord.help) && Intrinsics.areEqual(this.cachedPdfPath, jobRecord.cachedPdfPath);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((this.id.hashCode() * 31) + this.sourceJobId.hashCode()) * 31) + this.fingerprint.hashCode()) * 31) + Long.hashCode(this.timestamp)) * 31) + this.printerName.hashCode()) * 31) + this.printerAddress.hashCode()) * 31) + this.documentName.hashCode()) * 31) + Integer.hashCode(this.attempt)) * 31) + Integer.hashCode(this.requestedCopies)) * 31) + Boolean.hashCode(this.deliveryAttempted)) * 31) + Long.hashCode(this.bytesSent)) * 31) + Long.hashCode(this.totalBytes)) * 31) + this.status.hashCode()) * 31) + (this.error == null ? 0 : this.error.hashCode())) * 31) + (this.help == null ? 0 : this.help.hashCode())) * 31) + (this.cachedPdfPath != null ? this.cachedPdfPath.hashCode() : 0);
    }

    public String toString() {
        return "JobRecord(id=" + this.id + ", sourceJobId=" + this.sourceJobId + ", fingerprint=" + this.fingerprint + ", timestamp=" + this.timestamp + ", printerName=" + this.printerName + ", printerAddress=" + this.printerAddress + ", documentName=" + this.documentName + ", attempt=" + this.attempt + ", requestedCopies=" + this.requestedCopies + ", deliveryAttempted=" + this.deliveryAttempted + ", bytesSent=" + this.bytesSent + ", totalBytes=" + this.totalBytes + ", status=" + this.status + ", error=" + this.error + ", help=" + this.help + ", cachedPdfPath=" + this.cachedPdfPath + ")";
    }

    public JobRecord(String id, String sourceJobId, String fingerprint, long timestamp, String printerName, String printerAddress, String documentName, int attempt, int requestedCopies, boolean deliveryAttempted, long bytesSent, long totalBytes, JobStatus status, String error, String help, String cachedPdfPath) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(sourceJobId, "sourceJobId");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        Intrinsics.checkNotNullParameter(printerName, "printerName");
        Intrinsics.checkNotNullParameter(printerAddress, "printerAddress");
        Intrinsics.checkNotNullParameter(documentName, "documentName");
        Intrinsics.checkNotNullParameter(status, "status");
        this.id = id;
        this.sourceJobId = sourceJobId;
        this.fingerprint = fingerprint;
        this.timestamp = timestamp;
        this.printerName = printerName;
        this.printerAddress = printerAddress;
        this.documentName = documentName;
        this.attempt = attempt;
        this.requestedCopies = requestedCopies;
        this.deliveryAttempted = deliveryAttempted;
        this.bytesSent = bytesSent;
        this.totalBytes = totalBytes;
        this.status = status;
        this.error = error;
        this.help = help;
        this.cachedPdfPath = cachedPdfPath;
    }

    public /* synthetic */ JobRecord(String str, String str2, String str3, long j, String str4, String str5, String str6, int i, int i2, boolean z, long j2, long j3, JobStatus jobStatus, String str7, String str8, String str9, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i3 & 4) != 0 ? "" : str3, j, str4, str5, str6, (i3 & Uuid.SIZE_BITS) != 0 ? 1 : i, (i3 & 256) != 0 ? 1 : i2, (i3 & ConstantsKt.MINIMUM_BLOCK_SIZE) != 0 ? false : z, (i3 & 1024) != 0 ? 0L : j2, (i3 & 2048) != 0 ? 0L : j3, (i3 & ConstantsKt.DEFAULT_BLOCK_SIZE) != 0 ? JobStatus.QUEUED : jobStatus, (i3 & ConstantsKt.DEFAULT_BUFFER_SIZE) != 0 ? null : str7, (i3 & 16384) != 0 ? null : str8, (i3 & 32768) != 0 ? null : str9);
    }

    public final String getId() {
        return this.id;
    }

    public final String getSourceJobId() {
        return this.sourceJobId;
    }

    public final String getFingerprint() {
        return this.fingerprint;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getPrinterName() {
        return this.printerName;
    }

    public final String getPrinterAddress() {
        return this.printerAddress;
    }

    public final String getDocumentName() {
        return this.documentName;
    }

    public final int getAttempt() {
        return this.attempt;
    }

    public final int getRequestedCopies() {
        return this.requestedCopies;
    }

    public final boolean getDeliveryAttempted() {
        return this.deliveryAttempted;
    }

    public final long getBytesSent() {
        return this.bytesSent;
    }

    public final long getTotalBytes() {
        return this.totalBytes;
    }

    public final JobStatus getStatus() {
        return this.status;
    }

    public final String getError() {
        return this.error;
    }

    public final String getHelp() {
        return this.help;
    }

    public final String getCachedPdfPath() {
        return this.cachedPdfPath;
    }

    public final int getProgressPercent() {
        if (this.totalBytes <= 0) {
            return 0;
        }
        return RangesKt.coerceIn((int) ((this.bytesSent * 100) / this.totalBytes), 0, 100);
    }

    public final JSONObject toJson() {
        JSONObject put = new JSONObject().put("id", this.id).put("sourceJobId", this.sourceJobId).put("fingerprint", this.fingerprint).put("timestamp", this.timestamp).put("printerName", this.printerName).put("printerAddress", this.printerAddress).put("documentName", this.documentName).put("attempt", this.attempt).put("requestedCopies", this.requestedCopies).put("deliveryAttempted", this.deliveryAttempted).put("bytesSent", this.bytesSent).put("totalBytes", this.totalBytes).put("status", this.status.name());
        Object obj = this.error;
        if (obj == null) {
            obj = JSONObject.NULL;
        }
        JSONObject put2 = put.put("error", obj);
        Object obj2 = this.help;
        if (obj2 == null) {
            obj2 = JSONObject.NULL;
        }
        JSONObject put3 = put2.put("help", obj2);
        Object obj3 = this.cachedPdfPath;
        if (obj3 == null) {
            obj3 = JSONObject.NULL;
        }
        JSONObject put4 = put3.put("cachedPdfPath", obj3);
        Intrinsics.checkNotNullExpressionValue(put4, "put(...)");
        return put4;
    }

    /* compiled from: JobRecord.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¨\u0006\u000b"}, d2 = {"Lcom/clvprinter/smartprint/storage/JobRecord$Companion;", "", "<init>", "()V", "fromJson", "Lcom/clvprinter/smartprint/storage/JobRecord;", "json", "Lorg/json/JSONObject;", "optNullableString", "", "key", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x00b3  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final JobRecord fromJson(JSONObject json) {
            long j;
            Object m8constructorimpl;
            Intrinsics.checkNotNullParameter(json, "json");
            String string = json.getString("id");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String optString = json.optString("sourceJobId", json.getString("id"));
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            String optString2 = json.optString("fingerprint", "");
            Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
            long optLong = json.optLong("timestamp", 0L);
            String optString3 = json.optString("printerName", "Printer");
            Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
            String optString4 = json.optString("printerAddress", "—");
            Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
            String optString5 = json.optString("documentName", "Dokumen");
            Intrinsics.checkNotNullExpressionValue(optString5, "optString(...)");
            int optInt = json.optInt("attempt", 1);
            int coerceAtLeast = RangesKt.coerceAtLeast(json.optInt("requestedCopies", 1), 1);
            boolean optBoolean = json.optBoolean("deliveryAttempted", false);
            long optLong2 = json.optLong("bytesSent", 0L);
            long optLong3 = json.optLong("totalBytes", 0L);
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                j = optLong3;
                try {
                    String optString6 = json.optString("status");
                    Intrinsics.checkNotNullExpressionValue(optString6, "optString(...)");
                    m8constructorimpl = Result.m8constructorimpl(JobStatus.valueOf(optString6));
                } catch (Throwable th) {
                    th = th;
                    Result.Companion companion3 = Result.INSTANCE;
                    m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th));
                    JobStatus jobStatus = JobStatus.FAILED;
                    if (Result.m14isFailureimpl(m8constructorimpl)) {
                    }
                    return new JobRecord(string, optString, optString2, optLong, optString3, optString4, optString5, optInt, coerceAtLeast, optBoolean, optLong2, j, (JobStatus) m8constructorimpl, optNullableString(json, "error"), optNullableString(json, "help"), optNullableString(json, "cachedPdfPath"));
                }
            } catch (Throwable th2) {
                th = th2;
                j = optLong3;
            }
            JobStatus jobStatus2 = JobStatus.FAILED;
            if (Result.m14isFailureimpl(m8constructorimpl)) {
                m8constructorimpl = jobStatus2;
            }
            return new JobRecord(string, optString, optString2, optLong, optString3, optString4, optString5, optInt, coerceAtLeast, optBoolean, optLong2, j, (JobStatus) m8constructorimpl, optNullableString(json, "error"), optNullableString(json, "help"), optNullableString(json, "cachedPdfPath"));
        }

        private final String optNullableString(JSONObject $this$optNullableString, String key) {
            if ($this$optNullableString.isNull(key)) {
                return null;
            }
            String optString = $this$optNullableString.optString(key);
            Intrinsics.checkNotNull(optString);
            if (StringsKt.isBlank(optString)) {
                return null;
            }
            return optString;
        }
    }
}
