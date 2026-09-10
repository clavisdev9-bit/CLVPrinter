package com.clvprinter.smartprint.bluetooth;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BondedPrinterProvider.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006\u0082\u0001\u0005\u0007\b\t\n\u000b¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState;", "", "Ready", "PermissionRequired", "BluetoothDisabled", "HardwareUnavailable", "Error", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$BluetoothDisabled;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$Error;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$HardwareUnavailable;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$PermissionRequired;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$Ready;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface BondedPrinterState {

    /* compiled from: BondedPrinterProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$Ready;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState;", "printers", "", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinter;", "<init>", "(Ljava/util/List;)V", "getPrinters", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Ready implements BondedPrinterState {
        private final List<BondedPrinter> printers;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Ready copy$default(Ready ready, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = ready.printers;
            }
            return ready.copy(list);
        }

        public final List<BondedPrinter> component1() {
            return this.printers;
        }

        public final Ready copy(List<BondedPrinter> printers) {
            Intrinsics.checkNotNullParameter(printers, "printers");
            return new Ready(printers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Ready) && Intrinsics.areEqual(this.printers, ((Ready) other).printers);
        }

        public int hashCode() {
            return this.printers.hashCode();
        }

        public String toString() {
            return "Ready(printers=" + this.printers + ")";
        }

        public Ready(List<BondedPrinter> printers) {
            Intrinsics.checkNotNullParameter(printers, "printers");
            this.printers = printers;
        }

        public final List<BondedPrinter> getPrinters() {
            return this.printers;
        }
    }

    /* compiled from: BondedPrinterProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$PermissionRequired;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class PermissionRequired implements BondedPrinterState {
        public static final PermissionRequired INSTANCE = new PermissionRequired();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PermissionRequired)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 213804833;
        }

        public String toString() {
            return "PermissionRequired";
        }

        private PermissionRequired() {
        }
    }

    /* compiled from: BondedPrinterProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$BluetoothDisabled;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BluetoothDisabled implements BondedPrinterState {
        public static final BluetoothDisabled INSTANCE = new BluetoothDisabled();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BluetoothDisabled)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return -733305769;
        }

        public String toString() {
            return "BluetoothDisabled";
        }

        private BluetoothDisabled() {
        }
    }

    /* compiled from: BondedPrinterProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$HardwareUnavailable;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class HardwareUnavailable implements BondedPrinterState {
        public static final HardwareUnavailable INSTANCE = new HardwareUnavailable();

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HardwareUnavailable)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 745675541;
        }

        public String toString() {
            return "HardwareUnavailable";
        }

        private HardwareUnavailable() {
        }
    }

    /* compiled from: BondedPrinterProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState$Error;", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Error implements BondedPrinterState {
        private final String message;

        public static /* synthetic */ Error copy$default(Error error, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = error.message;
            }
            return error.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final Error copy(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new Error(message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Error) && Intrinsics.areEqual(this.message, ((Error) other).message);
        }

        public int hashCode() {
            return this.message.hashCode();
        }

        public String toString() {
            return "Error(message=" + this.message + ")";
        }

        public Error(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }

        public final String getMessage() {
            return this.message;
        }
    }
}
