package com.clvprinter.smartprint.printservice;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.printservice.PrintJob;
import com.clvprinter.smartprint.printer.PipelineResult;
import com.clvprinter.smartprint.printer.ReceiptPipeline;
import com.clvprinter.smartprint.storage.AppPreferences;
import com.clvprinter.smartprint.storage.JobRecord;
import com.clvprinter.smartprint.storage.JobRepository;
import com.clvprinter.smartprint.storage.JobStatus;
import com.clvprinter.smartprint.storage.PrintJobRecoveryRules;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: PrintJobProcessor.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ,2\u00020\u0001:\u0001,B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007Â¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0002J(\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u001aH\u0002J\"\u0010#\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u001aH\u0002J#\u0010'\u001a\u0004\u0018\u0001H(\"\u0004\b\u0000\u0010(2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0*H\u0002Â¢\u0006\u0002\u0010+R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u0006-"}, d2 = {"Lcom/rapprinter/smartprint/printservice/PrintJobProcessor;", "", "service", "Lcom/rapprinter/smartprint/printservice/ReceiptPrintService;", "repository", "Lcom/rapprinter/smartprint/storage/JobRepository;", "preferences", "Lcom/rapprinter/smartprint/storage/AppPreferences;", "<init>", "(Lcom/rapprinter/smartprint/printservice/ReceiptPrintService;Lcom/rapprinter/smartprint/storage/JobRepository;Lcom/rapprinter/smartprint/storage/AppPreferences;)V", "mainHandler", "Landroid/os/Handler;", "pipeline", "Lcom/rapprinter/smartprint/printer/ReceiptPipeline;", "process", "", "snapshot", "Lcom/rapprinter/smartprint/printservice/QueuedPrintJob;", "canceled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "copySpool", "descriptor", "Landroid/os/ParcelFileDescriptor;", "target", "Ljava/io/File;", "sha256", "", "file", "failBeforeStart", "job", "Landroid/printservice/PrintJob;", "record", "Lcom/rapprinter/smartprint/storage/JobRecord;", "message", "help", "finishFrameworkJob", "status", "Lcom/rapprinter/smartprint/storage/JobStatus;", "error", "onMain", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PrintJobProcessor {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int ODOO_WIDE_SOURCE_MIN_MILS = 3000;
    private final Handler mainHandler;
    private final ReceiptPipeline pipeline;
    private final AppPreferences preferences;
    private final JobRepository repository;
    private final ReceiptPrintService service;

    /* compiled from: PrintJobProcessor.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JobStatus.values().length];
            try {
                iArr[JobStatus.COMPLETED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[JobStatus.CANCELED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[JobStatus.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PrintJobProcessor(ReceiptPrintService service, JobRepository repository, AppPreferences preferences) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(preferences, "preferences");
        this.service = service;
        this.repository = repository;
        this.preferences = preferences;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.pipeline = new ReceiptPipeline(this.service);
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x03ac, code lost:
    
        if (r4.exists() != false) goto L127;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:165:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01d1  */
    /* JADX WARN: Type inference failed for: r0v38, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r0v44, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r0v52, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r14v0, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r3v12, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r6v12, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void process(final QueuedPrintJob snapshot, AtomicBoolean canceled) {
        boolean z;
        boolean started;
        Throwable th;
        int i;
        Ref.ObjectRef record;
        File retryFile;
        String message;
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        Intrinsics.checkNotNullParameter(canceled, "canceled");
        final PrintJob job = snapshot.getFrameworkJob();
        String sourceJobId = snapshot.getSourceJobId();
        JobRecord previous = this.repository.find(sourceJobId);
        if ((previous != null ? previous.getStatus() : null) == JobStatus.COMPLETED) {
            onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PrintJobProcessor.process$lambda$0(QueuedPrintJob.this, job);
                }
            });
            try {
                Result.Companion companion = Result.INSTANCE;
                PrintJobProcessor printJobProcessor = this;
                snapshot.getDocumentData().close();
                Result.m8constructorimpl(Unit.INSTANCE);
                return;
            } catch (Throwable th2) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8constructorimpl(ResultKt.createFailure(th2));
                return;
            }
        }
        if (PrintJobRecoveryRules.INSTANCE.mustHoldBeforeResend(previous)) {
            if (previous == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            JobStatus jobStatus = JobStatus.UNKNOWN;
            String error = previous.getError();
            if (error == null) {
                error = "Tugas cetak diterima kembali setelah pengiriman dimulai";
            }
            JobRecord held = JobRecord.copy$default(previous, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, jobStatus, error, "Periksa kertas dan gunakan cetak ulang manual agar risiko duplikasi terlihat.", null, 36863, null);
            JobRepository.upsert$default(this.repository, held, false, 2, null);
            onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(PrintJobProcessor.process$lambda$2(QueuedPrintJob.this, job));
                }
            });
            try {
                Result.Companion companion3 = Result.INSTANCE;
                PrintJobProcessor printJobProcessor2 = this;
                snapshot.getDocumentData().close();
                Result.m8constructorimpl(Unit.INSTANCE);
                return;
            } catch (Throwable th3) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m8constructorimpl(ResultKt.createFailure(th3));
                return;
            }
        }
        String printerAddress = snapshot.getPrinterAddress();
        String printerName = snapshot.getPrinterName();
        String documentName = snapshot.getDocumentName();
        final Ref.ObjectRef record2 = new Ref.ObjectRef();
        long currentTimeMillis = System.currentTimeMillis();
        String str = printerAddress;
        if (StringsKt.isBlank(str)) {
            str = "â€”";
        }
        record2.element = new JobRecord(sourceJobId, sourceJobId, null, currentTimeMillis, printerName, str, documentName, 0, snapshot.getCopies(), false, 0L, 0L, JobStatus.QUEUED, null, null, null, 61060, null);
        JobRepository.upsert$default(this.repository, (JobRecord) record2.element, false, 2, null);
        if (!BluetoothAdapter.checkBluetoothAddress(printerAddress)) {
            failBeforeStart(job, (JobRecord) record2.element, "Printer tujuan dari dialog cetak tidak valid", "Pilih printer Bluetooth asli di dialog cetak Android.");
            try {
                Result.Companion companion5 = Result.INSTANCE;
                PrintJobProcessor printJobProcessor3 = this;
                snapshot.getDocumentData().close();
                Result.m8constructorimpl(Unit.INSTANCE);
                return;
            } catch (Throwable th4) {
                Result.Companion companion6 = Result.INSTANCE;
                Result.m8constructorimpl(ResultKt.createFailure(th4));
                return;
            }
        }
        if (!snapshot.getWasStarted()) {
            Boolean bool = (Boolean) onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    boolean start;
                    start = job.start();
                    return Boolean.valueOf(start);
                }
            });
            if (!(bool != null ? bool.booleanValue() : false)) {
                z = false;
                started = z;
                if (started) {
                    JobStatus status = canceled.get() ? JobStatus.CANCELED : JobStatus.FAILED;
                    JobRepository.upsert$default(this.repository, JobRecord.copy$default((JobRecord) record2.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, status, "Tugas cetak tidak dapat dimulai", null, null, 53247, null), false, 2, null);
                    try {
                        Result.Companion companion7 = Result.INSTANCE;
                        PrintJobProcessor printJobProcessor4 = this;
                        snapshot.getDocumentData().close();
                        Result.m8constructorimpl(Unit.INSTANCE);
                        return;
                    } catch (Throwable th5) {
                        Result.Companion companion8 = Result.INSTANCE;
                        Result.m8constructorimpl(ResultKt.createFailure(th5));
                        return;
                    }
                }
                File retryFile2 = this.repository.retryFile(sourceJobId);
                try {
                    try {
                        record2.element = JobRecord.copy$default((JobRecord) record2.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.PRINTING, null, null, null, 28671, null);
                        JobRepository.upsert$default(this.repository, (JobRecord) record2.element, false, 2, null);
                        onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PrintJobProcessor.process$lambda$8(job);
                            }
                        });
                        copySpool(snapshot.getDocumentData(), retryFile2, canceled);
                        String fingerprint = sha256(retryFile2);
                        record2.element = JobRecord.copy$default((JobRecord) record2.element, null, null, fingerprint, 0L, null, null, null, 0, 0, false, 0L, 0L, null, null, null, retryFile2.getAbsolutePath(), 32763, null);
                        JobRepository.upsert$default(this.repository, (JobRecord) record2.element, false, 2, null);
                        if (this.repository.isCompleted(sourceJobId, fingerprint)) {
                            try {
                                retryFile2.delete();
                                record2.element = JobRecord.copy$default((JobRecord) record2.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.COMPLETED, null, "Tugas yang sama sudah tercatat selesai; data tidak dikirim ulang.", null, 12287, null);
                                JobRepository.upsert$default(this.repository, (JobRecord) record2.element, false, 2, null);
                                onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda7
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        boolean complete;
                                        complete = job.complete();
                                        return Boolean.valueOf(complete);
                                    }
                                });
                                try {
                                    Result.Companion companion9 = Result.INSTANCE;
                                    PrintJobProcessor printJobProcessor5 = this;
                                    snapshot.getDocumentData().close();
                                    Result.m8constructorimpl(Unit.INSTANCE);
                                    return;
                                } catch (Throwable th6) {
                                    Result.Companion companion10 = Result.INSTANCE;
                                    Result.m8constructorimpl(ResultKt.createFailure(th6));
                                    return;
                                }
                            } catch (CancellationException e) {
                                i = 2;
                                record = record2;
                                record.element = JobRecord.copy$default((JobRecord) record.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.CANCELED, "Pencetakan dibatalkan", null, null, 53247, null);
                                JobRepository.upsert$default(this.repository, (JobRecord) record.element, false, i, null);
                                onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda11
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        boolean cancel;
                                        cancel = job.cancel();
                                        return Boolean.valueOf(cancel);
                                    }
                                });
                                try {
                                    Result.Companion companion11 = Result.INSTANCE;
                                    snapshot.getDocumentData().close();
                                    Result.m8constructorimpl(Unit.INSTANCE);
                                    return;
                                } catch (Throwable th7) {
                                    th = th7;
                                    Result.Companion companion12 = Result.INSTANCE;
                                    Result.m8constructorimpl(ResultKt.createFailure(th));
                                    return;
                                }
                            } catch (Exception e2) {
                                error = e2;
                                i = 2;
                                record = record2;
                                retryFile = retryFile2;
                                message = error.getMessage();
                                if (message == null) {
                                }
                                final String message2 = message;
                                record.element = JobRecord.copy$default((JobRecord) record.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.FAILED, message2, "Kirim ulang dokumen dari preview Android. RapPrint tidak mengakses isi nota di log.", !retryFile.exists() ? retryFile.getAbsolutePath() : null, 4095, null);
                                JobRepository.upsert$default(this.repository, (JobRecord) record.element, false, i, null);
                                this.preferences.setLastError(message2);
                                onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda12
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        boolean fail;
                                        fail = job.fail(message2);
                                        return Boolean.valueOf(fail);
                                    }
                                });
                                try {
                                    Result.Companion companion13 = Result.INSTANCE;
                                    snapshot.getDocumentData().close();
                                    Result.m8constructorimpl(Unit.INSTANCE);
                                    return;
                                } catch (Throwable th8) {
                                    th = th8;
                                    Result.Companion companion122 = Result.INSTANCE;
                                    Result.m8constructorimpl(ResultKt.createFailure(th));
                                    return;
                                }
                            } catch (Throwable th9) {
                                th = th9;
                                try {
                                    Result.Companion companion14 = Result.INSTANCE;
                                    snapshot.getDocumentData().close();
                                    Result.m8constructorimpl(Unit.INSTANCE);
                                    throw th;
                                } catch (Throwable th10) {
                                    Result.Companion companion15 = Result.INSTANCE;
                                    Result.m8constructorimpl(ResultKt.createFailure(th10));
                                    throw th;
                                }
                            }
                        }
                        try {
                            try {
                                retryFile = retryFile2;
                                try {
                                } catch (CancellationException e3) {
                                    record = record2;
                                    i = 2;
                                } catch (Exception e4) {
                                    error = e4;
                                    record = record2;
                                    i = 2;
                                } catch (Throwable th11) {
                                    th = th11;
                                }
                            } catch (CancellationException e5) {
                                record = record2;
                                i = 2;
                            } catch (Exception e6) {
                                error = e6;
                                record = record2;
                                retryFile = retryFile2;
                                i = 2;
                            } catch (Throwable th12) {
                                th = th12;
                            }
                        } catch (CancellationException e7) {
                            i = 2;
                            record = record2;
                        } catch (Exception e8) {
                            error = e8;
                            i = 2;
                            record = record2;
                            retryFile = retryFile2;
                        } catch (Throwable th13) {
                            th = th13;
                        }
                        try {
                            record = record2;
                            i = 2;
                            boolean z2 = true;
                            try {
                                PipelineResult result = this.pipeline.printPdf(retryFile, printerAddress, this.preferences.loadConfig(), canceled, snapshot.getCopies(), snapshot.getSourcePaperWidthMils() >= 3000, new Function1() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda8
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return PrintJobProcessor.process$lambda$11(PrintJobProcessor.this, job, (String) obj);
                                    }
                                }, new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda9
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return PrintJobProcessor.process$lambda$12(Ref.ObjectRef.this, this);
                                    }
                                }, new Function2() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda10
                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(Object obj, Object obj2) {
                                        return PrintJobProcessor.process$lambda$14(PrintJobProcessor.this, record2, job, ((Long) obj).longValue(), ((Long) obj2).longValue());
                                    }
                                });
                                synchronized (canceled) {
                                    try {
                                        JobStatus status2 = (canceled.get() && result.getStatus() == JobStatus.COMPLETED && ((JobRecord) record.element).getDeliveryAttempted()) ? JobStatus.UNKNOWN : result.getStatus();
                                        try {
                                            boolean contains = SetsKt.setOf((Object[]) new JobStatus[]{JobStatus.FAILED, JobStatus.CANCELED, JobStatus.UNKNOWN}).contains(status2);
                                            if (!contains) {
                                                retryFile.delete();
                                            }
                                            boolean z3 = status2 == JobStatus.UNKNOWN && result.getStatus() == JobStatus.COMPLETED;
                                            JobRecord jobRecord = (JobRecord) record.element;
                                            long bytesSent = result.getBytesSent();
                                            long totalBytes = result.getTotalBytes();
                                            String error2 = z3 ? "Dibatalkan setelah pengiriman mungkin dimulai" : result.getError();
                                            String help = z3 ? "Data mungkin sudah tercetak seluruhnya; periksa kertas sebelum mengulang." : result.getHelp();
                                            String absolutePath = retryFile.getAbsolutePath();
                                            if (contains) {
                                            }
                                            z2 = false;
                                            try {
                                                try {
                                                    record.element = JobRecord.copy$default(jobRecord, null, null, null, 0L, null, null, null, 0, 0, false, bytesSent, totalBytes, status2, error2, help, Boolean.valueOf(z2).booleanValue() ? absolutePath : null, 1023, null);
                                                    JobRepository.upsert$default(this.repository, (JobRecord) record.element, false, 2, null);
                                                    this.preferences.setLastError(((JobRecord) record.element).getError());
                                                    Unit unit = Unit.INSTANCE;
                                                    finishFrameworkJob(job, status2, ((JobRecord) record.element).getError());
                                                    try {
                                                        Result.Companion companion16 = Result.INSTANCE;
                                                        PrintJobProcessor printJobProcessor6 = this;
                                                        snapshot.getDocumentData().close();
                                                        Result.m8constructorimpl(Unit.INSTANCE);
                                                        return;
                                                    } catch (Throwable th14) {
                                                        th = th14;
                                                        Result.Companion companion1222 = Result.INSTANCE;
                                                        Result.m8constructorimpl(ResultKt.createFailure(th));
                                                        return;
                                                    }
                                                } catch (Throwable th15) {
                                                    th = th15;
                                                    throw th;
                                                }
                                            } catch (Throwable th16) {
                                                th = th16;
                                            }
                                        } catch (Throwable th17) {
                                            th = th17;
                                        }
                                    } catch (Throwable th18) {
                                        th = th18;
                                    }
                                }
                            } catch (CancellationException e9) {
                                record.element = JobRecord.copy$default((JobRecord) record.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.CANCELED, "Pencetakan dibatalkan", null, null, 53247, null);
                                JobRepository.upsert$default(this.repository, (JobRecord) record.element, false, i, null);
                                onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda11
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        boolean cancel;
                                        cancel = job.cancel();
                                        return Boolean.valueOf(cancel);
                                    }
                                });
                                Result.Companion companion112 = Result.INSTANCE;
                                snapshot.getDocumentData().close();
                                Result.m8constructorimpl(Unit.INSTANCE);
                                return;
                            } catch (Exception e10) {
                                error = e10;
                                message = error.getMessage();
                                if (message == null) {
                                    message = "Spool cetak tidak dapat dibaca";
                                }
                                final String message22 = message;
                                record.element = JobRecord.copy$default((JobRecord) record.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.FAILED, message22, "Kirim ulang dokumen dari preview Android. RapPrint tidak mengakses isi nota di log.", !retryFile.exists() ? retryFile.getAbsolutePath() : null, 4095, null);
                                JobRepository.upsert$default(this.repository, (JobRecord) record.element, false, i, null);
                                this.preferences.setLastError(message22);
                                onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda12
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        boolean fail;
                                        fail = job.fail(message22);
                                        return Boolean.valueOf(fail);
                                    }
                                });
                                Result.Companion companion132 = Result.INSTANCE;
                                snapshot.getDocumentData().close();
                                Result.m8constructorimpl(Unit.INSTANCE);
                                return;
                            }
                        } catch (CancellationException e11) {
                            record = record2;
                            i = 2;
                        } catch (Exception e12) {
                            error = e12;
                            record = record2;
                            i = 2;
                        } catch (Throwable th19) {
                            th = th19;
                            Result.Companion companion142 = Result.INSTANCE;
                            snapshot.getDocumentData().close();
                            Result.m8constructorimpl(Unit.INSTANCE);
                            throw th;
                        }
                    } catch (Throwable th20) {
                        th = th20;
                    }
                } catch (CancellationException e13) {
                    i = 2;
                    record = record2;
                } catch (Exception e14) {
                    error = e14;
                    i = 2;
                    record = record2;
                    retryFile = retryFile2;
                } catch (Throwable th21) {
                    th = th21;
                }
            }
        }
        z = true;
        started = z;
        if (started) {
        }
    }

    static final Unit process$lambda$0(QueuedPrintJob $snapshot, PrintJob $job) {
        if ($snapshot.getWasStarted() || $job.start()) {
            $job.complete();
        }
        return Unit.INSTANCE;
    }

    static final boolean process$lambda$2(QueuedPrintJob $snapshot, PrintJob $job) {
        if (!$snapshot.getWasStarted()) {
            $job.start();
        }
        return $job.fail("Status pengiriman sebelumnya tidak pasti");
    }

    static final Unit process$lambda$8(PrintJob $job) {
        $job.setStatus("Menyalin dokumen dengan aman");
        return Unit.INSTANCE;
    }

    static final Unit process$lambda$11(PrintJobProcessor this$0, final PrintJob $job, final String status) {
        Intrinsics.checkNotNullParameter(status, "status");
        this$0.onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PrintJobProcessor.process$lambda$11$lambda$10($job, status);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit process$lambda$11$lambda$10(PrintJob $job, String $status) {
        $job.setStatus($status);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final Unit process$lambda$12(Ref.ObjectRef $record, PrintJobProcessor this$0) {
        $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, true, 0L, 0L, null, null, null, null, 65023, null);
        this$0.repository.upsert((JobRecord) $record.element, true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final Unit process$lambda$14(PrintJobProcessor this$0, Ref.ObjectRef $record, final PrintJob $job, long sent, long total) {
        long j;
        final float progress;
        if (total == 0) {
            progress = 0.0f;
            j = sent;
        } else {
            j = sent;
            progress = j / total;
        }
        this$0.onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PrintJobProcessor.process$lambda$14$lambda$13($job, progress);
            }
        });
        $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, false, j, total, null, null, null, null, 62463, null);
        JobRepository.upsert$default(this$0.repository, (JobRecord) $record.element, false, 2, null);
        return Unit.INSTANCE;
    }

    static final Unit process$lambda$14$lambda$13(PrintJob $job, float $progress) {
        $job.setProgress(RangesKt.coerceIn($progress, 0.0f, 1.0f));
        return Unit.INSTANCE;
    }

    private final void copySpool(ParcelFileDescriptor descriptor, File target, AtomicBoolean canceled) {
        File parentFile = target.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        File temporary = new File(target.getParentFile(), target.getName() + "." + System.nanoTime() + ".part");
        try {
            FileOutputStream fileInputStream = new FileInputStream(descriptor.getFileDescriptor());
            try {
                FileInputStream fileInputStream2 = fileInputStream;
                boolean z = false;
                fileInputStream = new FileOutputStream(temporary, false);
                try {
                    FileOutputStream fileOutputStream = fileInputStream;
                    byte[] bArr = new byte[32768];
                    while (!canceled.get()) {
                        int read = fileInputStream2.read(bArr);
                        if (read >= 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.getFD().sync();
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileInputStream, null);
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileInputStream, null);
                            if (!(temporary.length() > 0)) {
                                throw new IllegalArgumentException("Dokumen spool kosong".toString());
                            }
                            try {
                                Files.move(temporary.toPath(), target.toPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                            } catch (AtomicMoveNotSupportedException e) {
                                Files.move(temporary.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            }
                            temporary.delete();
                            if (target.isFile() && target.length() > 0) {
                                z = true;
                            }
                            if (!z) {
                                throw new IllegalArgumentException("PDF spool gagal disimpan".toString());
                            }
                            return;
                        }
                    }
                    throw new CancellationException("Dibatalkan saat menyalin spool");
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            temporary.delete();
            throw th;
        }
    }

    private final String sha256(File file) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            byte[] bArr = new byte[32768];
            while (true) {
                int read = fileInputStream2.read(bArr);
                if (read < 0) {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    byte[] digest2 = digest.digest();
                    Intrinsics.checkNotNullExpressionValue(digest2, "digest(...)");
                    return ArraysKt.joinToString$default(digest2, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return PrintJobProcessor.sha256$lambda$26(((Byte) obj).byteValue());
                        }
                    }, 30, (Object) null);
                }
                digest.update(bArr, 0, read);
            }
        } finally {
        }
    }

    static final CharSequence sha256$lambda$26(byte it) {
        String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final void failBeforeStart(final PrintJob job, JobRecord record, final String message, String help) {
        JobRepository.upsert$default(this.repository, JobRecord.copy$default(record, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.FAILED, message, help, null, 36863, null), false, 2, null);
        this.preferences.setLastError(message);
        onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PrintJobProcessor.failBeforeStart$lambda$27(job, message);
            }
        });
    }

    static final Unit failBeforeStart$lambda$27(PrintJob $job, String $message) {
        if ($job.start()) {
            $job.fail($message);
        }
        return Unit.INSTANCE;
    }

    private final void finishFrameworkJob(final PrintJob job, final JobStatus status, final String error) {
        onMain(new Function0() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(PrintJobProcessor.finishFrameworkJob$lambda$28(JobStatus.this, job, error));
            }
        });
    }

    static final boolean finishFrameworkJob$lambda$28(JobStatus $status, PrintJob $job, String $error) {
        switch (WhenMappings.$EnumSwitchMapping$0[$status.ordinal()]) {
            case 1:
                return $job.complete();
            case 2:
                return $job.cancel();
            case 3:
                return $job.fail("Status cetak tidak pasti â€” periksa kertas sebelum mengulang");
            default:
                return $job.fail($error == null ? "Pencetakan gagal" : $error);
        }
    }

    private final <T> T onMain(final Function0<? extends T> block) {
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            return block.invoke();
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mainHandler.post(new Runnable() { // from class: com.clvprinter.smartprint.printservice.PrintJobProcessor$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                PrintJobProcessor.onMain$lambda$29(Ref.ObjectRef.this, block, countDownLatch);
            }
        });
        countDownLatch.await();
        Result result = (Result) objectRef.element;
        if (result == null) {
            return null;
        }
        T t = (T) result.getValue();
        if (Result.m14isFailureimpl(t)) {
            return null;
        }
        return t;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, kotlin.Result] */
    static final void onMain$lambda$29(Ref.ObjectRef $result, Function0 $block, CountDownLatch $latch) {
        Object m8constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            m8constructorimpl = Result.m8constructorimpl($block.invoke());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th));
        }
        $result.element = Result.m7boximpl(m8constructorimpl);
        $latch.countDown();
    }

    /* compiled from: PrintJobProcessor.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086TÂ¢\u0006\u0002\n\u0000Â¨\u0006\u0006"}, d2 = {"Lcom/rapprinter/smartprint/printservice/PrintJobProcessor$Companion;", "", "<init>", "()V", "ODOO_WIDE_SOURCE_MIN_MILS", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}

