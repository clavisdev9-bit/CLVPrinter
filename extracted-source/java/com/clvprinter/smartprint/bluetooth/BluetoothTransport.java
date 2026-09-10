package com.clvprinter.smartprint.bluetooth;

import android.content.Context;
import com.clvprinter.smartprint.printer.PrintLimits;
import com.clvprinter.smartprint.printer.PrinterConfig;
import com.clvprinter.smartprint.printer.PrinterJobCoordinator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* compiled from: BluetoothTransport.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 +2\u00020\u0001:\u0001+B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Jp\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u001128\b\u0002\u0010\u0013\u001a2\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00120\u0014Jx\u0010\u001a\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u001128\b\u0002\u0010\u0013\u001a2\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00120\u0014Jr\u0010\u001d\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u001128\b\u0002\u0010\u0013\u001a2\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00120\u0014H\u0003J$\u0010\u001e\u001a\u00020\u00122\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\u0006\u0010!\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\"\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020\tH\u0002J(\u0010%\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010&\u001a\u00020'2\u0006\u0010#\u001a\u00020\tH\u0002J\u0010\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BluetoothTransport;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "send", "Lcom/clvprinter/smartprint/bluetooth/SendResult;", "address", "", "payload", "", "config", "Lcom/clvprinter/smartprint/printer/PrinterConfig;", "canceled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onPayloadStart", "Lkotlin/Function0;", "", "onProgress", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "sent", "total", "sendCopies", "copies", "", "sendLocked", "await", "future", "Ljava/util/concurrent/Future;", "timeoutMs", "failed", "message", "help", "uncertainOrFailed", "payloadMayHaveStarted", "", "bluetoothMessage", "error", "", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BluetoothTransport {
    private static final int CHUNK_SIZE = 4096;
    private static final String DELIVERY_ACCEPTED_HELP = "Data diterima transport Bluetooth; printer generic tidak memberi konfirmasi bahwa kertas benar-benar tercetak.";
    private static final long POLL_MS = 200;
    private final Context context;

    public BluetoothTransport(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public static /* synthetic */ SendResult send$default(BluetoothTransport bluetoothTransport, String str, byte[] bArr, PrinterConfig printerConfig, AtomicBoolean atomicBoolean, Function0 function0, Function2 function2, int i, Object obj) {
        Function0 function02;
        Function2 function22;
        if ((i & 16) == 0) {
            function02 = function0;
        } else {
            function02 = new Function0() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit unit;
                    unit = Unit.INSTANCE;
                    return unit;
                }
            };
        }
        if ((i & 32) == 0) {
            function22 = function2;
        } else {
            function22 = new Function2() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    Unit unit;
                    ((Long) obj2).longValue();
                    ((Long) obj3).longValue();
                    unit = Unit.INSTANCE;
                    return unit;
                }
            };
        }
        return bluetoothTransport.send(str, bArr, printerConfig, atomicBoolean, function02, function22);
    }

    public final SendResult send(final String address, final byte[] payload, final PrinterConfig config, final AtomicBoolean canceled, final Function0<Unit> onPayloadStart, final Function2<? super Long, ? super Long, Unit> onProgress) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(canceled, "canceled");
        Intrinsics.checkNotNullParameter(onPayloadStart, "onPayloadStart");
        Intrinsics.checkNotNullParameter(onProgress, "onProgress");
        try {
            PrintLimits.INSTANCE.requirePayloadBytes(payload.length);
            PrintLimits.INSTANCE.transmissionBytes(payload.length, 1);
            return (SendResult) PrinterJobCoordinator.INSTANCE.withDevice(address, canceled, new Function0() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    SendResult sendLocked;
                    sendLocked = BluetoothTransport.this.sendLocked(address, payload, config, canceled, onPayloadStart, onProgress);
                    return sendLocked;
                }
            });
        } catch (IllegalArgumentException error) {
            String message = error.getMessage();
            if (message == null) {
                message = "Data cetak melewati batas aman";
            }
            return failed(payload, message, "Kurangi isi dokumen sebelum mengirim ulang.");
        } catch (CancellationException e) {
            return new SendResult(TransportStatus.CANCELED, 0L, payload.length, "Pencetakan dibatalkan", null, 16, null);
        }
    }

    public static /* synthetic */ SendResult sendCopies$default(BluetoothTransport bluetoothTransport, String str, byte[] bArr, int i, PrinterConfig printerConfig, AtomicBoolean atomicBoolean, Function0 function0, Function2 function2, int i2, Object obj) {
        Function0 function02;
        Function2 function22;
        if ((i2 & 32) == 0) {
            function02 = function0;
        } else {
            function02 = new Function0() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit unit;
                    unit = Unit.INSTANCE;
                    return unit;
                }
            };
        }
        if ((i2 & 64) == 0) {
            function22 = function2;
        } else {
            function22 = new Function2() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    Unit unit;
                    ((Long) obj2).longValue();
                    ((Long) obj3).longValue();
                    unit = Unit.INSTANCE;
                    return unit;
                }
            };
        }
        return bluetoothTransport.sendCopies(str, bArr, i, printerConfig, atomicBoolean, function02, function22);
    }

    public final SendResult sendCopies(final String address, final byte[] payload, final int copies, final PrinterConfig config, final AtomicBoolean canceled, final Function0<Unit> onPayloadStart, final Function2<? super Long, ? super Long, Unit> onProgress) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(canceled, "canceled");
        Intrinsics.checkNotNullParameter(onPayloadStart, "onPayloadStart");
        Intrinsics.checkNotNullParameter(onProgress, "onProgress");
        long reportedTotal = payload.length * RangesKt.coerceAtLeast(copies, 1);
        try {
            PrintLimits.INSTANCE.requirePayloadBytes(payload.length);
            final long total = PrintLimits.INSTANCE.transmissionBytes(payload.length, copies);
            return (SendResult) PrinterJobCoordinator.INSTANCE.withDevice(address, canceled, new Function0() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BluetoothTransport.sendCopies$lambda$8(copies, total, this, address, payload, config, canceled, onProgress, onPayloadStart);
                }
            });
        } catch (IllegalArgumentException error) {
            TransportStatus transportStatus = TransportStatus.FAILED;
            String message = error.getMessage();
            if (message == null) {
                message = "Pengiriman melewati batas aman";
            }
            return new SendResult(transportStatus, 0L, reportedTotal, message, "Kurangi isi dokumen atau jumlah salinan lalu coba lagi.");
        } catch (CancellationException e) {
            return new SendResult(TransportStatus.CANCELED, 0L, reportedTotal, "Pencetakan dibatalkan", null, 16, null);
        }
    }

    static final SendResult sendCopies$lambda$8(int $copies, final long $total, BluetoothTransport this$0, String $address, byte[] $payload, PrinterConfig $config, AtomicBoolean $canceled, final Function2 $onProgress, final Function0 $onPayloadStart) {
        String help;
        final Ref.LongRef completedBytes = new Ref.LongRef();
        final Ref.BooleanRef payloadStartRecorded = new Ref.BooleanRef();
        for (int i = 0; i < $copies; i++) {
            int i2 = i;
            SendResult sendLocked = this$0.sendLocked($address, $payload, $config, $canceled, new Function0() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BluetoothTransport.sendCopies$lambda$8$lambda$7$lambda$5(Ref.BooleanRef.this, $onPayloadStart);
                }
            }, new Function2() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BluetoothTransport.sendCopies$lambda$8$lambda$7$lambda$6(Function2.this, completedBytes, $total, ((Long) obj).longValue(), ((Long) obj2).longValue());
                }
            });
            if (sendLocked.getStatus() != TransportStatus.SUCCESS) {
                long bytesSent = completedBytes.element + sendLocked.getBytesSent();
                TransportStatus status = completedBytes.element > 0 ? TransportStatus.UNKNOWN : sendLocked.getStatus();
                String error = sendLocked.getError();
                if (completedBytes.element > 0) {
                    help = i2 + " salinan selesai sebelum gangguan. Periksa semua struk sebelum mengulang tugas cetak.";
                } else {
                    help = sendLocked.getHelp();
                }
                return new SendResult(status, bytesSent, $total, error, help);
            }
            completedBytes.element += $payload.length;
            $onProgress.invoke(Long.valueOf(completedBytes.element), Long.valueOf($total));
            if (i + 1 < $copies) {
                int length = $payload.length / 12;
                if (length < 1200) {
                    length = 1200;
                }
                if (length > 15000) {
                    length = 15000;
                }
                while (length > 0 && !$canceled.get()) {
                    try {
                        Thread.sleep(POLL_MS);
                        length -= 200;
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        return new SendResult(TransportStatus.SUCCESS, completedBytes.element, $total, null, DELIVERY_ACCEPTED_HELP, 8, null);
    }

    static final Unit sendCopies$lambda$8$lambda$7$lambda$5(Ref.BooleanRef $payloadStartRecorded, Function0 $onPayloadStart) {
        if (!$payloadStartRecorded.element) {
            $onPayloadStart.invoke();
            $payloadStartRecorded.element = true;
        }
        return Unit.INSTANCE;
    }

    static final Unit sendCopies$lambda$8$lambda$7$lambda$6(Function2 $onProgress, Ref.LongRef $completedBytes, long $total, long sent, long j) {
        $onProgress.invoke(Long.valueOf($completedBytes.element + sent), Long.valueOf($total));
        return Unit.INSTANCE;
    }

    static /* synthetic */ SendResult sendLocked$default(BluetoothTransport bluetoothTransport, String str, byte[] bArr, PrinterConfig printerConfig, AtomicBoolean atomicBoolean, Function0 function0, Function2 function2, int i, Object obj) {
        Function0 function02;
        Function2 function22;
        if ((i & 16) == 0) {
            function02 = function0;
        } else {
            function02 = new Function0() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit unit;
                    unit = Unit.INSTANCE;
                    return unit;
                }
            };
        }
        if ((i & 32) == 0) {
            function22 = function2;
        } else {
            function22 = new Function2() { // from class: com.clvprinter.smartprint.bluetooth.BluetoothTransport$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    Unit unit;
                    ((Long) obj2).longValue();
                    ((Long) obj3).longValue();
                    unit = Unit.INSTANCE;
                    return unit;
                }
            };
        }
        return bluetoothTransport.sendLocked(str, bArr, printerConfig, atomicBoolean, function02, function22);
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to apply debug info
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:35)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchDebugInfoByOffset(DebugInfoApplyVisitor.java:107)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:83)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0398: MOVE (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('payloadMayHaveStarted' boolean)]), block:B:280:0x0398 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 7 */
    public final com.clvprinter.smartprint.bluetooth.SendResult sendLocked(java.lang.String r30, byte[] r31, com.clvprinter.smartprint.printer.PrinterConfig r32, java.util.concurrent.atomic.AtomicBoolean r33, kotlin.jvm.functions.Function0<kotlin.Unit> r34, kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> r35) {
        /*
            Method dump skipped, instructions count: 1054
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clvprinter.smartprint.bluetooth.BluetoothTransport.sendLocked(java.lang.String, byte[], com.clvprinter.smartprint.printer.PrinterConfig, java.util.concurrent.atomic.AtomicBoolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2):com.clvprinter.smartprint.bluetooth.SendResult");
    }

    static final Thread sendLocked$lambda$12(Runnable runnable) {
        Thread thread = new Thread(runnable, "CLVPrinter-BluetoothOperation");
        thread.setDaemon(true);
        return thread;
    }

    static final void sendLocked$lambda$14(OutputStream $stream, byte[] $payload, int $start, int $length) {
        $stream.write($payload, $start, $length);
        $stream.flush();
    }

    private final void await(Future<?> future, int timeoutMs, AtomicBoolean canceled) {
        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(timeoutMs);
        while (!canceled.get()) {
            long remaining = deadline - System.nanoTime();
            if (remaining <= 0) {
                future.cancel(true);
                throw new TimeoutException("Batas waktu terlampaui");
            }
            try {
                future.get(Math.min(RangesKt.coerceAtLeast(TimeUnit.NANOSECONDS.toMillis(remaining), 1L), POLL_MS), TimeUnit.MILLISECONDS);
                return;
            } catch (TimeoutException e) {
            }
        }
        future.cancel(true);
        throw new CancellationException("Operasi dibatalkan");
    }

    private final SendResult failed(byte[] payload, String message, String help) {
        return new SendResult(TransportStatus.FAILED, 0L, payload.length, message, help);
    }

    private final SendResult uncertainOrFailed(byte[] payload, long sent, boolean payloadMayHaveStarted, String message) {
        if (payloadMayHaveStarted) {
            return new SendResult(TransportStatus.UNKNOWN, sent, payload.length, message, "Status fisik tidak dapat dipastikan. Periksa struk sebelum mencetak ulang agar tidak ganda.");
        }
        return new SendResult(TransportStatus.FAILED, 0L, payload.length, message, "Pastikan printer menyala, masih terpasang, dan berada dalam jangkauan.");
    }

    private final String bluetoothMessage(Throwable error) {
        if (!(error instanceof IOException)) {
            String message = error.getMessage();
            return message == null ? "Pengiriman Bluetooth gagal" : message;
        }
        String message2 = error.getMessage();
        if (message2 == null) {
            message2 = "koneksi ditutup";
        }
        return "Printer tidak dapat dihubungi: " + message2;
    }
}
