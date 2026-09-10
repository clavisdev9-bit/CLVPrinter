package com.clvprinter.smartprint.printservice;

import android.os.ParcelFileDescriptor;
import android.print.PrintAttributes;
import android.print.PrinterId;
import android.printservice.PrintDocument;
import android.printservice.PrintJob;
import android.printservice.PrintService;
import android.printservice.PrinterDiscoverySession;
import com.clvprinter.smartprint.bluetooth.BondedPrinter;
import com.clvprinter.smartprint.bluetooth.BondedPrinterProvider;
import com.clvprinter.smartprint.bluetooth.BondedPrinterState;
import com.clvprinter.smartprint.storage.AppPreferences;
import com.clvprinter.smartprint.storage.JobRecord;
import com.clvprinter.smartprint.storage.JobRepository;
import com.clvprinter.smartprint.storage.JobStatus;
import com.clvprinter.smartprint.storage.PrintJobIdentity;
import com.clvprinter.smartprint.storage.PrintJobRecoveryRules;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: ReceiptPrintService.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0012H\u0014J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u001b\u001a\u00020\u0012H\u0016J\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\tH\u0002J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\tH\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/clvprinter/smartprint/printservice/ReceiptPrintService;", "Landroid/printservice/PrintService;", "<init>", "()V", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "cancellations", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Ljava/util/concurrent/atomic/AtomicBoolean;", "repository", "Lcom/clvprinter/smartprint/storage/JobRepository;", "preferences", "Lcom/clvprinter/smartprint/storage/AppPreferences;", "processor", "Lcom/clvprinter/smartprint/printservice/PrintJobProcessor;", "onCreate", "", "onCreatePrinterDiscoverySession", "Landroid/printservice/PrinterDiscoverySession;", "onPrintJobQueued", "printJob", "Landroid/printservice/PrintJob;", "onConnected", "enqueue", "onRequestCancelPrintJob", "onDestroy", "ensureDurableTag", "recoveryRecord", "Lcom/clvprinter/smartprint/storage/JobRecord;", "id", "failFrameworkJob", "message", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ReceiptPrintService extends PrintService {
    private AppPreferences preferences;
    private PrintJobProcessor processor;
    private JobRepository repository;
    private final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.clvprinter.smartprint.printservice.ReceiptPrintService$$ExternalSyntheticLambda2
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return ReceiptPrintService.executor$lambda$1(runnable);
        }
    });
    private final ConcurrentHashMap<String, AtomicBoolean> cancellations = new ConcurrentHashMap<>();

    static final Thread executor$lambda$1(Runnable runnable) {
        Thread thread = new Thread(runnable, "CLVPrinter-JobQueue");
        thread.setDaemon(true);
        return thread;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.repository = new JobRepository(this);
        this.preferences = new AppPreferences(this);
        JobRepository jobRepository = this.repository;
        AppPreferences appPreferences = null;
        if (jobRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            jobRepository = null;
        }
        AppPreferences appPreferences2 = this.preferences;
        if (appPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        } else {
            appPreferences = appPreferences2;
        }
        this.processor = new PrintJobProcessor(this, jobRepository, appPreferences);
    }

    @Override // android.printservice.PrintService
    protected PrinterDiscoverySession onCreatePrinterDiscoverySession() {
        return new ReceiptPrinterDiscoverySession(this);
    }

    @Override // android.printservice.PrintService
    protected void onPrintJobQueued(PrintJob printJob) {
        Intrinsics.checkNotNullParameter(printJob, "printJob");
        enqueue(printJob);
    }

    @Override // android.printservice.PrintService
    protected void onConnected() {
        Object m8constructorimpl;
        JobRecord jobRecord;
        super.onConnected();
        Iterable<PrintJob> activePrintJobs = getActivePrintJobs();
        Intrinsics.checkNotNullExpressionValue(activePrintJobs, "getActivePrintJobs(...)");
        for (PrintJob printJob : activePrintJobs) {
            try {
                Result.Companion companion = Result.INSTANCE;
                Intrinsics.checkNotNull(printJob);
                m8constructorimpl = Result.m8constructorimpl(ensureDurableTag(printJob));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m14isFailureimpl(m8constructorimpl)) {
                m8constructorimpl = null;
            }
            String str = (String) m8constructorimpl;
            if (str != null) {
                JobRepository jobRepository = this.repository;
                if (jobRepository == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("repository");
                    jobRepository = null;
                }
                JobRecord find = jobRepository.find(str);
                if ((find != null ? find.getStatus() : null) == JobStatus.COMPLETED) {
                    if (printJob.isQueued()) {
                        printJob.start();
                    }
                    printJob.complete();
                } else if (printJob.isStarted() && PrintJobRecoveryRules.INSTANCE.activeJobIsAmbiguous(find)) {
                    if (find == null) {
                        Intrinsics.checkNotNull(printJob);
                        jobRecord = recoveryRecord(printJob, str);
                    } else {
                        jobRecord = find;
                    }
                    JobRecord jobRecord2 = jobRecord;
                    JobRepository jobRepository2 = this.repository;
                    if (jobRepository2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("repository");
                        jobRepository2 = null;
                    }
                    JobRepository.upsert$default(jobRepository2, JobRecord.copy$default(jobRecord2, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, JobStatus.UNKNOWN, "Layanan dimulai ulang saat tugas cetak aktif", "Pengiriman sebelumnya tidak dapat dipastikan. Periksa kertas lalu cetak ulang manual dari Riwayat.", null, 36863, null), false, 2, null);
                    printJob.fail("Status pengiriman tidak pasti — periksa kertas sebelum mencetak ulang");
                } else if (printJob.isStarted()) {
                    Intrinsics.checkNotNull(printJob);
                    enqueue(printJob);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x0168, code lost:
    
        if (r5 == null) goto L80;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void enqueue(PrintJob printJob) {
        Object m8constructorimpl;
        Object m8constructorimpl2;
        String localId;
        String str;
        String str2;
        String str3;
        int coerceAtLeast;
        int widthMils;
        boolean isStarted;
        ParcelFileDescriptor data;
        Object obj;
        try {
            Result.Companion companion = Result.INSTANCE;
            m8constructorimpl = Result.m8constructorimpl(ensureDurableTag(printJob));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m11exceptionOrNullimpl = Result.m11exceptionOrNullimpl(m8constructorimpl);
        AppPreferences appPreferences = null;
        if (m11exceptionOrNullimpl != null) {
            String message = m11exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "Identitas tugas cetak tidak dapat disimpan";
            }
            AppPreferences appPreferences2 = this.preferences;
            if (appPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferences");
            } else {
                appPreferences = appPreferences2;
            }
            appPreferences.setLastError(message);
            failFrameworkJob(printJob, message);
            return;
        }
        final String id = (String) m8constructorimpl;
        JobRepository jobRepository = this.repository;
        if (jobRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            jobRepository = null;
        }
        JobRecord previous = jobRepository.find(id);
        if ((previous != null ? previous.getStatus() : null) == JobStatus.COMPLETED) {
            if (printJob.isQueued()) {
                printJob.start();
            }
            printJob.complete();
            return;
        }
        if (PrintJobRecoveryRules.INSTANCE.mustHoldBeforeResend(previous)) {
            if (previous == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            JobRepository jobRepository2 = this.repository;
            if (jobRepository2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                jobRepository2 = null;
            }
            JobStatus jobStatus = JobStatus.UNKNOWN;
            String error = previous.getError();
            if (error == null) {
                error = "Tugas cetak aktif diterima kembali setelah pengiriman dimulai";
            }
            JobRepository.upsert$default(jobRepository2, JobRecord.copy$default(previous, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, jobStatus, error, "CLVPrinter menahan pengiriman otomatis. Periksa kertas lalu cetak ulang manual dari Riwayat.", null, 36863, null), false, 2, null);
            failFrameworkJob(printJob, "Status sebelumnya tidak pasti — periksa kertas sebelum mencetak ulang");
            return;
        }
        if ((previous != null ? previous.getStatus() : null) == JobStatus.CANCELED) {
            printJob.cancel();
            return;
        }
        final AtomicBoolean flag = new AtomicBoolean(false);
        if (this.cancellations.putIfAbsent(id, flag) != null) {
            return;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            ReceiptPrintService receiptPrintService = this;
            PrinterId printerId = printJob.getInfo().getPrinterId();
            localId = printerId != null ? printerId.getLocalId() : null;
            if (localId == null) {
                localId = "";
            }
            BondedPrinterState read = BondedPrinterProvider.INSTANCE.read(receiptPrintService);
            if (read instanceof BondedPrinterState.Ready) {
                Iterator it = ((BondedPrinterState.Ready) read).getPrinters().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    } else {
                        obj = it.next();
                        if (Intrinsics.areEqual(((BondedPrinter) obj).getAddress(), localId)) {
                            break;
                        }
                    }
                }
                BondedPrinter bondedPrinter = (BondedPrinter) obj;
                str = bondedPrinter != null ? bondedPrinter.getName() : null;
            } else {
                str = null;
            }
            if (str == null) {
                String str4 = localId;
                if (StringsKt.isBlank(str4)) {
                    str4 = "Printer Bluetooth";
                }
                str2 = str4;
            } else {
                str2 = str;
            }
            PrintDocument document = printJob.getDocument();
            Intrinsics.checkNotNullExpressionValue(document, "getDocument(...)");
            String name = document.getInfo().getName();
            if (name != null) {
                if (StringsKt.isBlank(name)) {
                    name = null;
                }
            }
            name = "Dokumen cetak";
            str3 = name;
            coerceAtLeast = RangesKt.coerceAtLeast(printJob.getInfo().getCopies(), 1);
            PrintAttributes.MediaSize mediaSize = printJob.getInfo().getAttributes().getMediaSize();
            widthMils = mediaSize != null ? mediaSize.getWidthMils() : 0;
            isStarted = printJob.isStarted();
            data = document.getData();
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            m8constructorimpl2 = Result.m8constructorimpl(ResultKt.createFailure(th2));
        }
        if (data == null) {
            throw new IllegalArgumentException("Spool PDF tidak tersedia".toString());
        }
        m8constructorimpl2 = Result.m8constructorimpl(new QueuedPrintJob(printJob, id, localId, str2, str3, coerceAtLeast, widthMils, isStarted, data));
        Throwable m11exceptionOrNullimpl2 = Result.m11exceptionOrNullimpl(m8constructorimpl2);
        if (m11exceptionOrNullimpl2 == null) {
            final QueuedPrintJob snapshot = (QueuedPrintJob) m8constructorimpl2;
            this.executor.execute(new Runnable() { // from class: com.clvprinter.smartprint.printservice.ReceiptPrintService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ReceiptPrintService.enqueue$lambda$11(ReceiptPrintService.this, snapshot, flag, id);
                }
            });
            return;
        }
        this.cancellations.remove(id, flag);
        String message2 = m11exceptionOrNullimpl2.getMessage();
        if (message2 == null) {
            message2 = "Spool cetak tidak dapat dibuka";
        }
        AppPreferences appPreferences3 = this.preferences;
        if (appPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        } else {
            appPreferences = appPreferences3;
        }
        appPreferences.setLastError(message2);
        failFrameworkJob(printJob, message2);
    }

    static final void enqueue$lambda$11(ReceiptPrintService this$0, QueuedPrintJob $snapshot, AtomicBoolean $flag, String $id) {
        try {
            PrintJobProcessor printJobProcessor = this$0.processor;
            if (printJobProcessor == null) {
                Intrinsics.throwUninitializedPropertyAccessException("processor");
                printJobProcessor = null;
            }
            printJobProcessor.process($snapshot, $flag);
        } finally {
            this$0.cancellations.remove($id, $flag);
        }
    }

    @Override // android.printservice.PrintService
    protected void onRequestCancelPrintJob(PrintJob printJob) {
        Intrinsics.checkNotNullParameter(printJob, "printJob");
        final String id = PrintJobIdentity.INSTANCE.existingTagOrNull(printJob.getTag());
        if (id == null) {
            printJob.cancel();
            return;
        }
        final AtomicBoolean flag = this.cancellations.get(id);
        Function0 updateCancellation = new Function0() { // from class: com.clvprinter.smartprint.printservice.ReceiptPrintService$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ReceiptPrintService.onRequestCancelPrintJob$lambda$12(flag, this, id);
            }
        };
        if (flag != null) {
            synchronized (flag) {
                updateCancellation.invoke();
                Unit unit = Unit.INSTANCE;
            }
        } else {
            updateCancellation.invoke();
        }
        printJob.cancel();
    }

    static final Unit onRequestCancelPrintJob$lambda$12(AtomicBoolean $flag, ReceiptPrintService this$0, String $id) {
        if ($flag != null) {
            $flag.set(true);
        }
        JobRepository jobRepository = this$0.repository;
        if (jobRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("repository");
            jobRepository = null;
        }
        JobRecord existing = jobRepository.find($id);
        if (existing != null && !SetsKt.setOf((Object[]) new JobStatus[]{JobStatus.FAILED, JobStatus.UNKNOWN}).contains(existing.getStatus())) {
            JobRepository jobRepository2 = this$0.repository;
            if (jobRepository2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repository");
                jobRepository2 = null;
            }
            JobRepository.upsert$default(jobRepository2, JobRecord.copy$default(existing, null, null, null, 0L, null, null, null, 0, 0, false, 0L, 0L, existing.getDeliveryAttempted() ? JobStatus.UNKNOWN : JobStatus.CANCELED, "Dibatalkan oleh pengguna", existing.getDeliveryAttempted() ? "Data mungkin sudah diterima printer; periksa struk sebelum mengulang." : null, null, 36863, null), false, 2, null);
        }
        return Unit.INSTANCE;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Iterable values = this.cancellations.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        Iterator it = values.iterator();
        while (it.hasNext()) {
            ((AtomicBoolean) it.next()).set(true);
        }
        this.executor.shutdownNow();
        super.onDestroy();
    }

    private final String ensureDurableTag(PrintJob printJob) {
        String existingTagOrNull = PrintJobIdentity.INSTANCE.existingTagOrNull(printJob.getTag());
        if (existingTagOrNull != null) {
            return existingTagOrNull;
        }
        String proposed = PrintJobIdentity.newTag$default(PrintJobIdentity.INSTANCE, null, 1, null);
        if (!printJob.setTag(proposed)) {
            throw new IllegalStateException("Layanan cetak Android menolak identitas tugas".toString());
        }
        String existingTagOrNull2 = PrintJobIdentity.INSTANCE.existingTagOrNull(printJob.getTag());
        if (existingTagOrNull2 != null) {
            return existingTagOrNull2;
        }
        throw new IllegalArgumentException("Identitas tugas tidak tersimpan di layanan cetak Android".toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0057, code lost:
    
        if (r1 == null) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final JobRecord recoveryRecord(PrintJob printJob, String id) {
        String str;
        PrinterId printerId = printJob.getInfo().getPrinterId();
        String address = printerId != null ? printerId.getLocalId() : null;
        if (address == null) {
            address = "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = address;
        if (StringsKt.isBlank(str2)) {
            str2 = "Printer Bluetooth";
        }
        String str3 = str2;
        String str4 = address;
        if (StringsKt.isBlank(str4)) {
            str4 = "—";
        }
        String str5 = str4;
        String name = printJob.getDocument().getInfo().getName();
        if (name != null) {
            str = StringsKt.isBlank(name) ? null : name;
        }
        str = "Dokumen cetak";
        return new JobRecord(id, id, null, currentTimeMillis, str3, str5, str, 0, RangesKt.coerceAtLeast(printJob.getInfo().getCopies(), 1), false, 0L, 0L, JobStatus.UNKNOWN, null, null, null, 61060, null);
    }

    private final void failFrameworkJob(PrintJob printJob, String message) {
        if (printJob.isQueued()) {
            printJob.start();
        }
        printJob.fail(message);
    }
}
