package com.clvprinter.smartprint.printer;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrinterJobCoordinator.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\t0\u000e¢\u0006\u0002\u0010\u000fR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/clvprinter/smartprint/printer/PrinterJobCoordinator;", "", "<init>", "()V", "locks", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Ljava/util/concurrent/locks/ReentrantLock;", "withDevice", "T", "address", "canceled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Ljava/util/concurrent/atomic/AtomicBoolean;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "POLL_MS", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PrinterJobCoordinator {
    private static final long POLL_MS = 200;
    public static final PrinterJobCoordinator INSTANCE = new PrinterJobCoordinator();
    private static final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    private PrinterJobCoordinator() {
    }

    static final ReentrantLock withDevice$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new ReentrantLock(true);
    }

    static final ReentrantLock withDevice$lambda$1(Function1 $tmp0, Object p0) {
        return (ReentrantLock) $tmp0.invoke(p0);
    }

    public final <T> T withDevice(String address, AtomicBoolean canceled, Function0<? extends T> block) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(canceled, "canceled");
        Intrinsics.checkNotNullParameter(block, "block");
        ConcurrentHashMap<String, ReentrantLock> concurrentHashMap = locks;
        String upperCase = address.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        final Function1 function1 = new Function1() { // from class: com.clvprinter.smartprint.printer.PrinterJobCoordinator$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PrinterJobCoordinator.withDevice$lambda$0((String) obj);
            }
        };
        ReentrantLock computeIfAbsent = concurrentHashMap.computeIfAbsent(upperCase, new Function() { // from class: com.clvprinter.smartprint.printer.PrinterJobCoordinator$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PrinterJobCoordinator.withDevice$lambda$1(Function1.this, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(computeIfAbsent, "computeIfAbsent(...)");
        ReentrantLock lock = computeIfAbsent;
        while (!lock.tryLock(POLL_MS, TimeUnit.MILLISECONDS)) {
            if (canceled.get()) {
                throw new CancellationException("Dibatalkan saat menunggu printer");
            }
        }
        try {
            if (canceled.get()) {
                throw new CancellationException("Dibatalkan saat menunggu printer");
            }
            return block.invoke();
        } finally {
            lock.unlock();
        }
    }
}
