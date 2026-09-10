package com.clvprinter.smartprint.printer;

import android.content.Context;
import com.clvprinter.smartprint.bluetooth.BluetoothTransport;
import com.clvprinter.smartprint.bluetooth.SendResult;
import com.clvprinter.smartprint.bluetooth.TransportStatus;
import com.clvprinter.smartprint.escpos.EscPosEncoder;
import com.clvprinter.smartprint.rendering.PdfReceiptRenderer;
import com.clvprinter.smartprint.storage.JobStatus;
import java.io.File;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReceiptPipeline.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003Â¢\u0006\u0004\b\u0004\u0010\u0005J\u009a\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001a0\u00192\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001c28\b\u0002\u0010\u001d\u001a2\u0012\u0013\u0012\u00110\u001fÂ¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\u001fÂ¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u001a0\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u0006$"}, d2 = {"Lcom/rapprinter/smartprint/printer/ReceiptPipeline;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "renderer", "Lcom/rapprinter/smartprint/rendering/PdfReceiptRenderer;", "transport", "Lcom/rapprinter/smartprint/bluetooth/BluetoothTransport;", "printPdf", "Lcom/rapprinter/smartprint/printer/PipelineResult;", "pdfFile", "Ljava/io/File;", "printerAddress", "", "config", "Lcom/rapprinter/smartprint/printer/PrinterConfig;", "canceled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "copies", "", "wideOdooSource", "", "onStage", "Lkotlin/Function1;", "", "onPayloadStart", "Lkotlin/Function0;", "onProgress", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "sent", "total", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReceiptPipeline {
    private final PdfReceiptRenderer renderer;
    private final BluetoothTransport transport;

    /* compiled from: ReceiptPipeline.kt */
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

    public ReceiptPipeline(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.renderer = new PdfReceiptRenderer();
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.transport = new BluetoothTransport(applicationContext);
    }

    static final Unit printPdf$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final PipelineResult printPdf(File pdfFile, String printerAddress, PrinterConfig config, AtomicBoolean canceled, int copies, boolean wideOdooSource, final Function1<? super String, Unit> onStage, Function0<Unit> onPayloadStart, Function2<? super Long, ? super Long, Unit> onProgress) {
        JobStatus jobStatus;
        Intrinsics.checkNotNullParameter(pdfFile, "pdfFile");
        Intrinsics.checkNotNullParameter(printerAddress, "printerAddress");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(canceled, "canceled");
        Intrinsics.checkNotNullParameter(onStage, "onStage");
        Intrinsics.checkNotNullParameter(onPayloadStart, "onPayloadStart");
        Intrinsics.checkNotNullParameter(onProgress, "onProgress");
        try {
            onStage.invoke("Merender dokumen");
            List pages = this.renderer.render(pdfFile, config, canceled, wideOdooSource, new Function2() { // from class: com.clvprinter.smartprint.printer.ReceiptPipeline$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ReceiptPipeline.printPdf$lambda$3(Function1.this, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            });
            if (canceled.get()) {
                throw new CancellationException("Pencetakan dibatalkan");
            }
            onStage.invoke("Menyiapkan data ESC/POS");
            byte[] payload = EscPosEncoder.INSTANCE.encodeDocument(pages, config);
            PrintLimits.INSTANCE.requirePayloadBytes(payload.length);
            PrintLimits.INSTANCE.transmissionBytes(payload.length, copies);
            onStage.invoke(copies == 1 ? "Mengirim ke printer" : "Mengirim " + copies + " salinan");
            SendResult result = this.transport.sendCopies(printerAddress, payload, copies, config, canceled, onPayloadStart, onProgress);
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
            return new PipelineResult(jobStatus, result.getBytesSent(), result.getTotalBytes(), result.getError(), result.getHelp());
        } catch (IllegalArgumentException error) {
            JobStatus jobStatus2 = JobStatus.FAILED;
            String message = error.getMessage();
            if (message == null) {
                message = "Dokumen melewati batas aman";
            }
            return new PipelineResult(jobStatus2, 0L, 0L, message, "Kurangi ukuran, jumlah halaman, atau jumlah salinan sesuai petunjuk lalu coba lagi.");
        } catch (SecurityException e) {
            return new PipelineResult(JobStatus.FAILED, 0L, 0L, "Akses dokumen atau Bluetooth ditolak", "Periksa izin Bluetooth lalu kirim ulang dari preview.");
        } catch (CancellationException e2) {
            return new PipelineResult(JobStatus.CANCELED, 0L, 0L, "Pencetakan dibatalkan", null, 16, null);
        } catch (Exception error2) {
            JobStatus jobStatus3 = JobStatus.FAILED;
            String message2 = error2.getMessage();
            if (message2 == null) {
                message2 = "Dokumen tidak dapat diproses";
            }
            return new PipelineResult(jobStatus3, 0L, 0L, message2, "Pastikan PDF valid dan ruang penyimpanan perangkat masih tersedia.");
        }
    }

    static final Unit printPdf$lambda$3(Function1 $onStage, int page, int total) {
        $onStage.invoke("Merender halaman " + page + "/" + total);
        return Unit.INSTANCE;
    }
}

