package com.clvprinter.smartprint.printservice;

import android.os.ParcelFileDescriptor;
import android.printservice.PrintJob;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.uuid.Uuid;

/* compiled from: QueuedPrintJob.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000fÂ¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÃ†\u0003J\t\u0010!\u001a\u00020\u0005HÃ†\u0003J\t\u0010\"\u001a\u00020\u0005HÃ†\u0003J\t\u0010#\u001a\u00020\u0005HÃ†\u0003J\t\u0010$\u001a\u00020\u0005HÃ†\u0003J\t\u0010%\u001a\u00020\nHÃ†\u0003J\t\u0010&\u001a\u00020\nHÃ†\u0003J\t\u0010'\u001a\u00020\rHÃ†\u0003J\t\u0010(\u001a\u00020\u000fHÃ†\u0003Jc\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÃ†\u0001J\u0013\u0010*\u001a\u00020\r2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÃ–\u0003J\t\u0010,\u001a\u00020\nHÃ–\u0001J\t\u0010-\u001a\u00020\u0005HÃ–\u0001R\u0011\u0010\u0002\u001a\u00020\u0003Â¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\t\u001a\u00020\nÂ¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\nÂ¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\f\u001a\u00020\rÂ¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u000fÂ¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fÂ¨\u0006."}, d2 = {"Lcom/rapprinter/smartprint/printservice/QueuedPrintJob;", "", "frameworkJob", "Landroid/printservice/PrintJob;", "sourceJobId", "", "printerAddress", "printerName", "documentName", "copies", "", "sourcePaperWidthMils", "wasStarted", "", "documentData", "Landroid/os/ParcelFileDescriptor;", "<init>", "(Landroid/printservice/PrintJob;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZLandroid/os/ParcelFileDescriptor;)V", "getFrameworkJob", "()Landroid/printservice/PrintJob;", "getSourceJobId", "()Ljava/lang/String;", "getPrinterAddress", "getPrinterName", "getDocumentName", "getCopies", "()I", "getSourcePaperWidthMils", "getWasStarted", "()Z", "getDocumentData", "()Landroid/os/ParcelFileDescriptor;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class QueuedPrintJob {
    private final int copies;
    private final ParcelFileDescriptor documentData;
    private final String documentName;
    private final PrintJob frameworkJob;
    private final String printerAddress;
    private final String printerName;
    private final String sourceJobId;
    private final int sourcePaperWidthMils;
    private final boolean wasStarted;

    public static /* synthetic */ QueuedPrintJob copy$default(QueuedPrintJob queuedPrintJob, PrintJob printJob, String str, String str2, String str3, String str4, int i, int i2, boolean z, ParcelFileDescriptor parcelFileDescriptor, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            printJob = queuedPrintJob.frameworkJob;
        }
        if ((i3 & 2) != 0) {
            str = queuedPrintJob.sourceJobId;
        }
        if ((i3 & 4) != 0) {
            str2 = queuedPrintJob.printerAddress;
        }
        if ((i3 & 8) != 0) {
            str3 = queuedPrintJob.printerName;
        }
        if ((i3 & 16) != 0) {
            str4 = queuedPrintJob.documentName;
        }
        if ((i3 & 32) != 0) {
            i = queuedPrintJob.copies;
        }
        if ((i3 & 64) != 0) {
            i2 = queuedPrintJob.sourcePaperWidthMils;
        }
        if ((i3 & Uuid.SIZE_BITS) != 0) {
            z = queuedPrintJob.wasStarted;
        }
        if ((i3 & 256) != 0) {
            parcelFileDescriptor = queuedPrintJob.documentData;
        }
        boolean z2 = z;
        ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptor;
        int i4 = i;
        int i5 = i2;
        String str5 = str4;
        String str6 = str2;
        return queuedPrintJob.copy(printJob, str, str6, str3, str5, i4, i5, z2, parcelFileDescriptor2);
    }

    /* renamed from: component1, reason: from getter */
    public final PrintJob getFrameworkJob() {
        return this.frameworkJob;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSourceJobId() {
        return this.sourceJobId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPrinterAddress() {
        return this.printerAddress;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPrinterName() {
        return this.printerName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDocumentName() {
        return this.documentName;
    }

    /* renamed from: component6, reason: from getter */
    public final int getCopies() {
        return this.copies;
    }

    /* renamed from: component7, reason: from getter */
    public final int getSourcePaperWidthMils() {
        return this.sourcePaperWidthMils;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getWasStarted() {
        return this.wasStarted;
    }

    /* renamed from: component9, reason: from getter */
    public final ParcelFileDescriptor getDocumentData() {
        return this.documentData;
    }

    public final QueuedPrintJob copy(PrintJob frameworkJob, String sourceJobId, String printerAddress, String printerName, String documentName, int copies, int sourcePaperWidthMils, boolean wasStarted, ParcelFileDescriptor documentData) {
        Intrinsics.checkNotNullParameter(frameworkJob, "frameworkJob");
        Intrinsics.checkNotNullParameter(sourceJobId, "sourceJobId");
        Intrinsics.checkNotNullParameter(printerAddress, "printerAddress");
        Intrinsics.checkNotNullParameter(printerName, "printerName");
        Intrinsics.checkNotNullParameter(documentName, "documentName");
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        return new QueuedPrintJob(frameworkJob, sourceJobId, printerAddress, printerName, documentName, copies, sourcePaperWidthMils, wasStarted, documentData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueuedPrintJob)) {
            return false;
        }
        QueuedPrintJob queuedPrintJob = (QueuedPrintJob) other;
        return Intrinsics.areEqual(this.frameworkJob, queuedPrintJob.frameworkJob) && Intrinsics.areEqual(this.sourceJobId, queuedPrintJob.sourceJobId) && Intrinsics.areEqual(this.printerAddress, queuedPrintJob.printerAddress) && Intrinsics.areEqual(this.printerName, queuedPrintJob.printerName) && Intrinsics.areEqual(this.documentName, queuedPrintJob.documentName) && this.copies == queuedPrintJob.copies && this.sourcePaperWidthMils == queuedPrintJob.sourcePaperWidthMils && this.wasStarted == queuedPrintJob.wasStarted && Intrinsics.areEqual(this.documentData, queuedPrintJob.documentData);
    }

    public int hashCode() {
        return (((((((((((((((this.frameworkJob.hashCode() * 31) + this.sourceJobId.hashCode()) * 31) + this.printerAddress.hashCode()) * 31) + this.printerName.hashCode()) * 31) + this.documentName.hashCode()) * 31) + Integer.hashCode(this.copies)) * 31) + Integer.hashCode(this.sourcePaperWidthMils)) * 31) + Boolean.hashCode(this.wasStarted)) * 31) + this.documentData.hashCode();
    }

    public String toString() {
        return "QueuedPrintJob(frameworkJob=" + this.frameworkJob + ", sourceJobId=" + this.sourceJobId + ", printerAddress=" + this.printerAddress + ", printerName=" + this.printerName + ", documentName=" + this.documentName + ", copies=" + this.copies + ", sourcePaperWidthMils=" + this.sourcePaperWidthMils + ", wasStarted=" + this.wasStarted + ", documentData=" + this.documentData + ")";
    }

    public QueuedPrintJob(PrintJob frameworkJob, String sourceJobId, String printerAddress, String printerName, String documentName, int copies, int sourcePaperWidthMils, boolean wasStarted, ParcelFileDescriptor documentData) {
        Intrinsics.checkNotNullParameter(frameworkJob, "frameworkJob");
        Intrinsics.checkNotNullParameter(sourceJobId, "sourceJobId");
        Intrinsics.checkNotNullParameter(printerAddress, "printerAddress");
        Intrinsics.checkNotNullParameter(printerName, "printerName");
        Intrinsics.checkNotNullParameter(documentName, "documentName");
        Intrinsics.checkNotNullParameter(documentData, "documentData");
        this.frameworkJob = frameworkJob;
        this.sourceJobId = sourceJobId;
        this.printerAddress = printerAddress;
        this.printerName = printerName;
        this.documentName = documentName;
        this.copies = copies;
        this.sourcePaperWidthMils = sourcePaperWidthMils;
        this.wasStarted = wasStarted;
        this.documentData = documentData;
    }

    public final PrintJob getFrameworkJob() {
        return this.frameworkJob;
    }

    public final String getSourceJobId() {
        return this.sourceJobId;
    }

    public final String getPrinterAddress() {
        return this.printerAddress;
    }

    public final String getPrinterName() {
        return this.printerName;
    }

    public final String getDocumentName() {
        return this.documentName;
    }

    public final int getCopies() {
        return this.copies;
    }

    public final int getSourcePaperWidthMils() {
        return this.sourcePaperWidthMils;
    }

    public final boolean getWasStarted() {
        return this.wasStarted;
    }

    public final ParcelFileDescriptor getDocumentData() {
        return this.documentData;
    }
}

