package com.clvprinter.smartprint.bluetooth;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BondedPrinterProvider.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinter;", "", "name", "", "address", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getAddress", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BondedPrinter {
    private final String address;
    private final String name;

    public static /* synthetic */ BondedPrinter copy$default(BondedPrinter bondedPrinter, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bondedPrinter.name;
        }
        if ((i & 2) != 0) {
            str2 = bondedPrinter.address;
        }
        return bondedPrinter.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    public final BondedPrinter copy(String name, String address) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        return new BondedPrinter(name, address);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BondedPrinter)) {
            return false;
        }
        BondedPrinter bondedPrinter = (BondedPrinter) other;
        return Intrinsics.areEqual(this.name, bondedPrinter.name) && Intrinsics.areEqual(this.address, bondedPrinter.address);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.address.hashCode();
    }

    public String toString() {
        return "BondedPrinter(name=" + this.name + ", address=" + this.address + ")";
    }

    public BondedPrinter(String name, String address) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        this.name = name;
        this.address = address;
    }

    public final String getName() {
        return this.name;
    }

    public final String getAddress() {
        return this.address;
    }
}
