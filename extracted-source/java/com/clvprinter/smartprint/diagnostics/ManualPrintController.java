package com.clvprinter.smartprint.diagnostics;

import android.content.Context;
import com.clvprinter.smartprint.bluetooth.BluetoothTransport;
import com.clvprinter.smartprint.bluetooth.SendResult;
import com.clvprinter.smartprint.bluetooth.TransportStatus;
import com.clvprinter.smartprint.escpos.DiagnosticReceiptBuilder;
import com.clvprinter.smartprint.printer.PipelineResult;
import com.clvprinter.smartprint.printer.PrinterConfig;
import com.clvprinter.smartprint.printer.ReceiptPipeline;
import com.clvprinter.smartprint.storage.AppPreferences;
import com.clvprinter.smartprint.storage.JobRecord;
import com.clvprinter.smartprint.storage.JobRepository;
import com.clvprinter.smartprint.storage.JobStatus;
import com.clvprinter.smartprint.storage.ManualJobRecoveryRules;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.uuid.Uuid;

/* compiled from: ManualPrintController.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J>\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00192\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019J6\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001a2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00192\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019J\u0006\u0010\u001f\u001a\u00020\u001bJ\u0006\u0010 \u001a\u00020\u001bR\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013¨\u0006!"}, d2 = {"Lcom/clvprinter/smartprint/diagnostics/ManualPrintController;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "repository", "Lcom/clvprinter/smartprint/storage/JobRepository;", "preferences", "Lcom/clvprinter/smartprint/storage/AppPreferences;", "executor", "Ljava/util/concurrent/ExecutorService;", "activeCancellation", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isBusy", "", "()Z", "printDiagnostic", "printerName", "", "printerAddress", "onChanged", "Lkotlin/Function1;", "Lcom/clvprinter/smartprint/storage/JobRecord;", "", "onFinished", "retry", "original", "cancelActive", "close", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ManualPrintController {
    private final AtomicReference<AtomicBoolean> activeCancellation;
    private final Context appContext;
    private final ExecutorService executor;
    private final AppPreferences preferences;
    private final JobRepository repository;

    /* compiled from: ManualPrintController.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TransportStatus.values().length];
            try {
                iArr[TransportStatus.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TransportStatus.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TransportStatus.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TransportStatus.CANCELED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.clvprinter.smartprint.diagnostics.ManualPrintController, java.lang.Object] */
    public ManualPrintController(Context context) {
        String str;
        String str2;
        List<JobRecord> list;
        Intrinsics.checkNotNullParameter(context, "context");
        ?? obj = new Object();
        obj.appContext = context.getApplicationContext();
        Context appContext = obj.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        obj.repository = new JobRepository(appContext);
        Context appContext2 = obj.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
        obj.preferences = new AppPreferences(appContext2);
        obj.executor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.clvprinter.smartprint.diagnostics.ManualPrintController$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return ManualPrintController.executor$lambda$1(runnable);
            }
        });
        obj.activeCancellation = new AtomicReference<>(null);
        List<JobRecord> records = obj.repository.records();
        ManualPrintController manualPrintController = obj;
        for (JobRecord jobRecord : records) {
            JobStatus interruptedStatus = ManualJobRecoveryRules.INSTANCE.interruptedStatus(jobRecord);
            if (interruptedStatus == null) {
                list = records;
            } else {
                boolean z = interruptedStatus == JobStatus.UNKNOWN;
                JobRepository jobRepository = manualPrintController.repository;
                if (z) {
                    str = "Aplikasi berhenti setelah pengiriman mungkin dimulai";
                } else {
                    str = "Tugas manual terputus sebelum pengiriman dimulai";
                }
                if (z) {
                    str2 = "Periksa kertas sebelum mencetak ulang agar nota tidak ganda.";
                } else {
                    str2 = "Mulai kembali tugas dari CLVPrinter.";
                }
                list = records;
                jobRepository.upsert(JobRecord.copy$default(jobRecord, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, interruptedStatus, str, str2, null, 36863, null), true);
            }
            manualPrintController = this;
            records = list;
        }
    }

    static final Thread executor$lambda$1(Runnable runnable) {
        Thread thread = new Thread(runnable, "CLVPrinter-ManualJob");
        thread.setDaemon(true);
        return thread;
    }

    public final boolean isBusy() {
        return this.activeCancellation.get() != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    public final boolean printDiagnostic(String printerName, final String printerAddress, final Function1<? super JobRecord, Unit> onChanged, final Function1<? super JobRecord, Unit> onFinished) {
        Intrinsics.checkNotNullParameter(printerName, "printerName");
        Intrinsics.checkNotNullParameter(printerAddress, "printerAddress");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        final AtomicBoolean cancellation = new AtomicBoolean(false);
        if (!ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this.activeCancellation, null, cancellation)) {
            return false;
        }
        final PrinterConfig config = this.preferences.loadConfig();
        String id = "test-" + UUID.randomUUID();
        final Ref.ObjectRef record = new Ref.ObjectRef();
        record.element = new JobRecord(id, id, "diagnostic-" + System.currentTimeMillis(), System.currentTimeMillis(), printerName, printerAddress, "Tes diagnostik CLVPrinter", 0, 1, false, 0L, 0L, JobStatus.QUEUED, null, null, null, 61056, null);
        JobRepository.upsert$default(this.repository, (JobRecord) record.element, false, 2, null);
        onChanged.invoke(record.element);
        this.executor.execute(new Runnable() { // from class: com.clvprinter.smartprint.diagnostics.ManualPrintController$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ManualPrintController.printDiagnostic$lambda$5(Ref.ObjectRef.this, this, onChanged, config, printerAddress, cancellation, onFinished);
            }
        });
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r4v11, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final void printDiagnostic$lambda$5(final Ref.ObjectRef $record, final ManualPrintController this$0, final Function1 $onChanged, PrinterConfig $config, String $printerAddress, AtomicBoolean $cancellation, Function1 $onFinished) {
        AtomicBoolean atomicBoolean;
        JobStatus jobStatus;
        try {
            $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.PRINTING, null, null, null, 61439, null);
            JobRepository.upsert$default(this$0.repository, (JobRecord) $record.element, false, 2, null);
            $onChanged.invoke($record.element);
            byte[] payload = DiagnosticReceiptBuilder.INSTANCE.build($config);
            Context appContext = this$0.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            atomicBoolean = $cancellation;
            try {
                SendResult result = new BluetoothTransport(appContext).send($printerAddress, payload, $config, atomicBoolean, new Function0() { // from class: com.clvprinter.smartprint.diagnostics.ManualPrintController$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ManualPrintController.printDiagnostic$lambda$5$lambda$3(Ref.ObjectRef.this, this$0, $onChanged);
                    }
                }, new Function2() { // from class: com.clvprinter.smartprint.diagnostics.ManualPrintController$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ManualPrintController.printDiagnostic$lambda$5$lambda$4(Ref.ObjectRef.this, this$0, $onChanged, ((Long) obj).longValue(), ((Long) obj2).longValue());
                    }
                });
                JobRecord jobRecord = (JobRecord) $record.element;
                try {
                    switch (WhenMappings.$EnumSwitchMapping$0[result.getStatus().ordinal()]) {
                        case 1:
                            jobStatus = JobStatus.COMPLETED;
                            break;
                        case 2:
                            jobStatus = JobStatus.FAILED;
                            break;
                        case 3:
                            jobStatus = JobStatus.UNKNOWN;
                            break;
                        case 4:
                            jobStatus = JobStatus.CANCELED;
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    $record.element = JobRecord.copy$default(jobRecord, null, null, null, 0L, null, null, null, 0, 0, false, result.getBytesSent(), result.getTotalBytes(), jobStatus, result.getError(), result.getHelp(), null, 33791, null);
                    JobRepository.upsert$default(this$0.repository, (JobRecord) $record.element, false, 2, null);
                    this$0.preferences.setLastError(result.getError());
                    $onFinished.invoke($record.element);
                    ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this$0.activeCancellation, atomicBoolean, null);
                } catch (Throwable th) {
                    th = th;
                    ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this$0.activeCancellation, atomicBoolean, null);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this$0.activeCancellation, atomicBoolean, null);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            atomicBoolean = $cancellation;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final Unit printDiagnostic$lambda$5$lambda$3(Ref.ObjectRef $record, ManualPrintController this$0, Function1 $onChanged) {
        $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, true, 0L, 0L, null, null, null, null, 65023, null);
        this$0.repository.upsert((JobRecord) $record.element, true);
        $onChanged.invoke($record.element);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final Unit printDiagnostic$lambda$5$lambda$4(Ref.ObjectRef $record, ManualPrintController this$0, Function1 $onChanged, long sent, long total) {
        $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, false, sent, total, null, null, null, null, 62463, null);
        JobRepository.upsert$default(this$0.repository, (JobRecord) $record.element, false, 2, null);
        $onChanged.invoke($record.element);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v4, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    public final boolean retry(JobRecord original, final Function1<? super JobRecord, Unit> onChanged, final Function1<? super JobRecord, Unit> onFinished) {
        Intrinsics.checkNotNullParameter(original, "original");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        String cachedPdfPath = original.getCachedPdfPath();
        if (cachedPdfPath == null) {
            return false;
        }
        File file = new File(cachedPdfPath);
        if (!file.isFile()) {
            file = null;
        }
        if (file == null) {
            return false;
        }
        final File source = file;
        final AtomicBoolean cancellation = new AtomicBoolean(false);
        if (!ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this.activeCancellation, null, cancellation)) {
            return false;
        }
        String id = "retry-" + UUID.randomUUID();
        final Ref.ObjectRef record = new Ref.ObjectRef();
        record.element = JobRecord.copy$default(original, id, original.getSourceJobId(), null, System.currentTimeMillis(), null, null, null, original.getAttempt() + 1, 0, false, 0L, 0L, JobStatus.QUEUED, null, "Percobaan ulang dibuat secara manual dari riwayat.", null, 33140, null);
        JobRepository.upsert$default(this.repository, (JobRecord) record.element, false, 2, null);
        onChanged.invoke(record.element);
        this.executor.execute(new Runnable() { // from class: com.clvprinter.smartprint.diagnostics.ManualPrintController$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                ManualPrintController.retry$lambda$10(Ref.ObjectRef.this, this, onChanged, source, cancellation, onFinished);
            }
        });
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    /* JADX WARN: Type inference failed for: r5v11, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final void retry$lambda$10(final Ref.ObjectRef $record, final ManualPrintController this$0, final Function1 $onChanged, File $source, AtomicBoolean $cancellation, Function1 $onFinished) {
        AtomicBoolean atomicBoolean;
        Object obj;
        Context appContext;
        PipelineResult result;
        try {
            $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.PRINTING, null, null, null, 61439, null);
            JobRepository.upsert$default(this$0.repository, (JobRecord) $record.element, false, 2, null);
            $onChanged.invoke($record.element);
            try {
                appContext = this$0.appContext;
                Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
                obj = null;
                atomicBoolean = $cancellation;
            } catch (Throwable th) {
                th = th;
                atomicBoolean = $cancellation;
                obj = null;
            }
        } catch (Throwable th2) {
            th = th2;
            atomicBoolean = $cancellation;
            obj = null;
        }
        try {
            result = new ReceiptPipeline(appContext).printPdf($source, ((JobRecord) $record.element).getPrinterAddress(), this$0.preferences.loadConfig(), atomicBoolean, (r22 & 16) != 0 ? 1 : ((JobRecord) $record.element).getRequestedCopies(), (r22 & 32) != 0 ? false : false, (r22 & 64) != 0 ? new Function1() { // from class: com.clvprinter.smartprint.printer.ReceiptPipeline$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ReceiptPipeline.printPdf$lambda$0((String) obj2);
                }
            } : null, (r22 & Uuid.SIZE_BITS) != 0 ? new Function0() { // from class: com.clvprinter.smartprint.printer.ReceiptPipeline$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit unit;
                    unit = Unit.INSTANCE;
                    return unit;
                }
            } : new Function0() { // from class: com.clvprinter.smartprint.diagnostics.ManualPrintController$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ManualPrintController.retry$lambda$10$lambda$7(Ref.ObjectRef.this, this$0, $onChanged);
                }
            }, (r22 & 256) != 0 ? new Function2() { // from class: com.clvprinter.smartprint.printer.ReceiptPipeline$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    Unit unit;
                    ((Long) obj2).longValue();
                    ((Long) obj3).longValue();
                    unit = Unit.INSTANCE;
                    return unit;
                }
            } : new Function2() { // from class: com.clvprinter.smartprint.diagnostics.ManualPrintController$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ManualPrintController.retry$lambda$10$lambda$8(Ref.ObjectRef.this, this$0, $onChanged, ((Long) obj2).longValue(), ((Long) obj3).longValue());
                }
            });
            if (result.getStatus() == JobStatus.COMPLETED) {
                $source.delete();
            }
            $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, false, result.getBytesSent(), result.getTotalBytes(), result.getStatus(), result.getError(), result.getHelp(), $source.exists() ? $source.getAbsolutePath() : null, 1023, null);
            JobRepository.upsert$default(this$0.repository, (JobRecord) $record.element, false, 2, null);
            this$0.preferences.setLastError(result.getError());
            try {
                $onFinished.invoke($record.element);
                ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this$0.activeCancellation, atomicBoolean, null);
            } catch (Throwable th3) {
                th = th3;
                ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this$0.activeCancellation, atomicBoolean, obj);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            ManualPrintController$$ExternalSyntheticBackportWithForwarding0.m(this$0.activeCancellation, atomicBoolean, obj);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final Unit retry$lambda$10$lambda$7(Ref.ObjectRef $record, ManualPrintController this$0, Function1 $onChanged) {
        $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, true, 0L, 0L, null, null, null, null, 65023, null);
        this$0.repository.upsert((JobRecord) $record.element, true);
        $onChanged.invoke($record.element);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.clvprinter.smartprint.storage.JobRecord] */
    static final Unit retry$lambda$10$lambda$8(Ref.ObjectRef $record, ManualPrintController this$0, Function1 $onChanged, long sent, long total) {
        $record.element = JobRecord.copy$default((JobRecord) $record.element, null, null, null, 0L, null, null, null, 0, 0, false, sent, total, null, null, null, null, 62463, null);
        JobRepository.upsert$default(this$0.repository, (JobRecord) $record.element, false, 2, null);
        $onChanged.invoke($record.element);
        return Unit.INSTANCE;
    }

    public final void cancelActive() {
        AtomicBoolean atomicBoolean = this.activeCancellation.get();
        if (atomicBoolean != null) {
            atomicBoolean.set(true);
        }
    }

    public final void close() {
        cancelActive();
        this.executor.shutdownNow();
    }
}
