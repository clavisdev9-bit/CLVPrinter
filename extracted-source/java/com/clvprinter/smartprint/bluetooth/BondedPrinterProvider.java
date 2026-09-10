package com.clvprinter.smartprint.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import com.clvprinter.smartprint.bluetooth.BondedPrinterState;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: BondedPrinterProvider.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/clvprinter/smartprint/bluetooth/BondedPrinterProvider;", "", "<init>", "()V", "read", "Lcom/clvprinter/smartprint/bluetooth/BondedPrinterState;", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BondedPrinterProvider {
    public static final BondedPrinterProvider INSTANCE = new BondedPrinterProvider();

    private BondedPrinterProvider() {
    }

    public final BondedPrinterState read(Context context) {
        BluetoothAdapter adapter;
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 31 && context.checkSelfPermission("android.permission.BLUETOOTH_CONNECT") != 0) {
            return BondedPrinterState.PermissionRequired.INSTANCE;
        }
        try {
            BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(BluetoothManager.class);
            if (bluetoothManager != null && (adapter = bluetoothManager.getAdapter()) != null) {
                if (!adapter.isEnabled()) {
                    return BondedPrinterState.BluetoothDisabled.INSTANCE;
                }
                Iterable bondedDevices = adapter.getBondedDevices();
                Intrinsics.checkNotNullExpressionValue(bondedDevices, "getBondedDevices(...)");
                Iterable<BluetoothDevice> iterable = bondedDevices;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (BluetoothDevice bluetoothDevice : iterable) {
                    String name = bluetoothDevice.getName();
                    if (name != null) {
                        if (StringsKt.isBlank(name)) {
                            name = null;
                        }
                        if (name != null) {
                            String address = bluetoothDevice.getAddress();
                            Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
                            arrayList.add(new BondedPrinter(name, address));
                        }
                    }
                    name = "Printer Bluetooth";
                    String address2 = bluetoothDevice.getAddress();
                    Intrinsics.checkNotNullExpressionValue(address2, "getAddress(...)");
                    arrayList.add(new BondedPrinter(name, address2));
                }
                final Comparator<String> case_insensitive_order = StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE);
                List devices = CollectionsKt.sortedWith((List) arrayList, new Comparator() { // from class: com.clvprinter.smartprint.bluetooth.BondedPrinterProvider$read$$inlined$compareBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return case_insensitive_order.compare(((BondedPrinter) t).getName(), ((BondedPrinter) t2).getName());
                    }
                });
                return new BondedPrinterState.Ready(devices);
            }
            return BondedPrinterState.HardwareUnavailable.INSTANCE;
        } catch (SecurityException e) {
            return BondedPrinterState.PermissionRequired.INSTANCE;
        } catch (Exception error) {
            String message = error.getMessage();
            if (message == null) {
                message = "Daftar printer tidak dapat dibaca";
            }
            return new BondedPrinterState.Error(message);
        }
    }
}
