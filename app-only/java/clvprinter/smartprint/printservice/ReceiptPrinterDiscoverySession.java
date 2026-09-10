package com.clvprinter.smartprint.printservice;

import android.print.PrintAttributes;
import android.print.PrinterCapabilitiesInfo;
import android.print.PrinterId;
import android.print.PrinterInfo;
import android.printservice.PrinterDiscoverySession;
import com.clvprinter.smartprint.bluetooth.BondedPrinter;
import com.clvprinter.smartprint.bluetooth.BondedPrinterProvider;
import com.clvprinter.smartprint.bluetooth.BondedPrinterState;
import com.clvprinter.smartprint.printer.PrinterProfile;
import com.clvprinter.smartprint.storage.AppPreferences;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: ReceiptPrinterDiscoverySession.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003Â¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0016\u0010\u000f\u001a\u00020\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\rH\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\nH\u0002J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J \u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bH\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u0006%"}, d2 = {"Lcom/rapprinter/smartprint/printservice/ReceiptPrinterDiscoverySession;", "Landroid/printservice/PrinterDiscoverySession;", "service", "Lcom/rapprinter/smartprint/printservice/ReceiptPrintService;", "<init>", "(Lcom/rapprinter/smartprint/printservice/ReceiptPrintService;)V", "publishedIds", "", "", "onStartPrinterDiscovery", "", "priorityList", "", "Landroid/print/PrinterId;", "onStopPrinterDiscovery", "onValidatePrinters", "printerIds", "onStartPrinterStateTracking", "printerId", "onStopPrinterStateTracking", "onDestroy", "publishAll", "publishState", "state", "Lcom/rapprinter/smartprint/bluetooth/BondedPrinterState;", "addStatusPrinter", "localId", "name", "description", "buildPrinter", "Landroid/print/PrinterInfo;", "printer", "Lcom/rapprinter/smartprint/bluetooth/BondedPrinter;", "capabilities", "Landroid/print/PrinterCapabilitiesInfo;", "id", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ReceiptPrinterDiscoverySession extends PrinterDiscoverySession {
    private static final String MEDIA_ID = "RAPPRINT_ODOO_80_TO_58_305MM";
    private static final int ODOO_SOURCE_WIDTH_MILS = 3150;
    private final Set<String> publishedIds;
    private final ReceiptPrintService service;

    public ReceiptPrinterDiscoverySession(ReceiptPrintService service) {
        Intrinsics.checkNotNullParameter(service, "service");
        this.service = service;
        this.publishedIds = new LinkedHashSet();
    }

    @Override // android.printservice.PrinterDiscoverySession
    public void onStartPrinterDiscovery(List<PrinterId> priorityList) {
        Intrinsics.checkNotNullParameter(priorityList, "priorityList");
        publishAll();
    }

    @Override // android.printservice.PrinterDiscoverySession
    public void onStopPrinterDiscovery() {
    }

    @Override // android.printservice.PrinterDiscoverySession
    public void onValidatePrinters(List<PrinterId> printerIds) {
        Intrinsics.checkNotNullParameter(printerIds, "printerIds");
        BondedPrinterState state = BondedPrinterProvider.INSTANCE.read(this.service);
        if (!(state instanceof BondedPrinterState.Ready)) {
            if (!printerIds.isEmpty()) {
                removePrinters(printerIds);
                Set<String> set = this.publishedIds;
                List<PrinterId> list = printerIds;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((PrinterId) it.next()).getLocalId());
                }
                set.removeAll(CollectionsKt.toSet((List) arrayList));
            }
            publishState(state);
            return;
        }
        Iterable printers = ((BondedPrinterState.Ready) state).getPrinters();
        Map byAddress = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(printers, 10)), 16));
        for (Object obj : printers) {
            byAddress.put(((BondedPrinter) obj).getAddress(), obj);
        }
        Collection arrayList2 = new ArrayList();
        Iterator it2 = printerIds.iterator();
        while (it2.hasNext()) {
            BondedPrinter bondedPrinter = (BondedPrinter) byAddress.get(((PrinterId) it2.next()).getLocalId());
            PrinterInfo buildPrinter = bondedPrinter != null ? buildPrinter(bondedPrinter) : null;
            if (buildPrinter != null) {
                arrayList2.add(buildPrinter);
            }
        }
        List updates = (List) arrayList2;
        Collection arrayList3 = new ArrayList();
        for (Object obj2 : printerIds) {
            if (!byAddress.containsKey(((PrinterId) obj2).getLocalId())) {
                arrayList3.add(obj2);
            }
        }
        List stale = (List) arrayList3;
        if (!updates.isEmpty()) {
            addPrinters(updates);
        }
        if (!stale.isEmpty()) {
            removePrinters(stale);
        }
        Set<String> set2 = this.publishedIds;
        List list2 = updates;
        Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            String localId = ((PrinterInfo) it3.next()).getId().getLocalId();
            Intrinsics.checkNotNullExpressionValue(localId, "getLocalId(...)");
            arrayList4.add(localId);
            updates = updates;
        }
        CollectionsKt.addAll(set2, (List) arrayList4);
        Set<String> set3 = this.publishedIds;
        List list3 = stale;
        Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        Iterator it4 = list3.iterator();
        while (it4.hasNext()) {
            arrayList5.add(((PrinterId) it4.next()).getLocalId());
        }
        set3.removeAll(CollectionsKt.toSet((List) arrayList5));
    }

    @Override // android.printservice.PrinterDiscoverySession
    public void onStartPrinterStateTracking(PrinterId printerId) {
        Object obj;
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        BondedPrinterState state = BondedPrinterProvider.INSTANCE.read(this.service);
        if (!(state instanceof BondedPrinterState.Ready)) {
            publishState(state);
            return;
        }
        Iterator it = ((BondedPrinterState.Ready) state).getPrinters().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((BondedPrinter) obj).getAddress(), printerId.getLocalId())) {
                    break;
                }
            }
        }
        BondedPrinter bondedPrinter = (BondedPrinter) obj;
        if (bondedPrinter != null) {
            addPrinters(CollectionsKt.listOf(buildPrinter(bondedPrinter)));
        }
    }

    @Override // android.printservice.PrinterDiscoverySession
    public void onStopPrinterStateTracking(PrinterId printerId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
    }

    @Override // android.printservice.PrinterDiscoverySession
    public void onDestroy() {
        this.publishedIds.clear();
    }

    private final void publishAll() {
        BondedPrinterState state = BondedPrinterProvider.INSTANCE.read(this.service);
        if (state instanceof BondedPrinterState.Ready) {
            Iterable printers = ((BondedPrinterState.Ready) state).getPrinters();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(printers, 10));
            Iterator it = printers.iterator();
            while (it.hasNext()) {
                arrayList.add(((BondedPrinter) it.next()).getAddress());
            }
            Set activeAddresses = CollectionsKt.toSet((List) arrayList);
            Iterable iterable = this.publishedIds;
            Collection arrayList2 = new ArrayList();
            for (Object obj : iterable) {
                String str = (String) obj;
                if (StringsKt.startsWith$default(str, "status:", false, 2, (Object) null) || !activeAddresses.contains(str)) {
                    arrayList2.add(obj);
                }
            }
            Iterable staleLocalIds = (List) arrayList2;
            if (!((Collection) staleLocalIds).isEmpty()) {
                Iterable iterable2 = staleLocalIds;
                ReceiptPrintService receiptPrintService = this.service;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                Iterator it2 = iterable2.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(receiptPrintService.generatePrinterId((String) it2.next()));
                }
                removePrinters((List) arrayList3);
                this.publishedIds.removeAll(CollectionsKt.toSet(staleLocalIds));
            }
            if (((BondedPrinterState.Ready) state).getPrinters().isEmpty()) {
                addStatusPrinter("status:no-paired", "Belum ada printer terpasang", "Pasangkan printer lewat pengaturan Bluetooth.");
                return;
            }
            Iterable printers2 = ((BondedPrinterState.Ready) state).getPrinters();
            Collection arrayList4 = new ArrayList();
            for (Object obj2 : printers2) {
                if (this.publishedIds.add(((BondedPrinter) obj2).getAddress())) {
                    arrayList4.add(obj2);
                }
            }
            Iterable iterable3 = (List) arrayList4;
            Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
            Iterator it3 = iterable3.iterator();
            while (it3.hasNext()) {
                arrayList5.add(buildPrinter((BondedPrinter) it3.next()));
            }
            List printers3 = (List) arrayList5;
            if (!printers3.isEmpty()) {
                addPrinters(printers3);
                return;
            }
            return;
        }
        Iterable iterable4 = this.publishedIds;
        Collection arrayList6 = new ArrayList();
        for (Object obj3 : iterable4) {
            if (!StringsKt.startsWith$default((String) obj3, "status:", false, 2, (Object) null)) {
                arrayList6.add(obj3);
            }
        }
        Iterable actualPrinters = (List) arrayList6;
        if (!((Collection) actualPrinters).isEmpty()) {
            Iterable iterable5 = actualPrinters;
            ReceiptPrintService receiptPrintService2 = this.service;
            Collection arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable5, 10));
            Iterator it4 = iterable5.iterator();
            while (it4.hasNext()) {
                arrayList7.add(receiptPrintService2.generatePrinterId((String) it4.next()));
            }
            removePrinters((List) arrayList7);
            this.publishedIds.removeAll(CollectionsKt.toSet(actualPrinters));
        }
        publishState(state);
    }

    private final void publishState(BondedPrinterState state) {
        if (!Intrinsics.areEqual(state, BondedPrinterState.PermissionRequired.INSTANCE)) {
            if (!Intrinsics.areEqual(state, BondedPrinterState.BluetoothDisabled.INSTANCE)) {
                if (!Intrinsics.areEqual(state, BondedPrinterState.HardwareUnavailable.INSTANCE)) {
                    if (!(state instanceof BondedPrinterState.Error)) {
                        if (!(state instanceof BondedPrinterState.Ready)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        return;
                    } else {
                        addStatusPrinter("status:error", "Printer tidak dapat dimuat", ((BondedPrinterState.Error) state).getMessage());
                        return;
                    }
                }
                addStatusPrinter("status:no-hardware", "Bluetooth tidak tersedia", "Perangkat ini tidak mendukung Bluetooth Classic.");
                return;
            }
            addStatusPrinter("status:off", "Bluetooth mati", "Nyalakan Bluetooth untuk melihat printer.");
            return;
        }
        addStatusPrinter("status:permission", "Izin Bluetooth diperlukan", "Buka RapPrint untuk memberikan izin.");
    }

    private final void addStatusPrinter(String localId, String name, String description) {
        if (this.publishedIds.add(localId)) {
            PrinterId id = this.service.generatePrinterId(localId);
            Intrinsics.checkNotNullExpressionValue(id, "generatePrinterId(...)");
            addPrinters(CollectionsKt.listOf(new PrinterInfo.Builder(id, name, 3).setDescription(description).setCapabilities(capabilities(id)).build()));
        }
    }

    private final PrinterInfo buildPrinter(BondedPrinter printer) {
        PrinterId id = this.service.generatePrinterId(printer.getAddress());
        Intrinsics.checkNotNullExpressionValue(id, "generatePrinterId(...)");
        PrinterProfile profile = new AppPreferences(this.service).loadConfig().getProfile();
        PrinterInfo.Builder builder = new PrinterInfo.Builder(id, printer.getName(), 1);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ROOT, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(profile.getActiveWidthMillimeters())}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        PrinterInfo build = builder.setDescription("Fisik 58 mm â€¢ sumber Odoo 80 mm â†’ head " + format + " mm â€¢ " + profile.getLabel() + " â€¢ " + printer.getAddress() + " â€¢ Bluetooth SPP").setCapabilities(capabilities(id)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    private final PrinterCapabilitiesInfo capabilities(PrinterId id) {
        PrinterProfile profile = new AppPreferences(this.service).loadConfig().getProfile();
        PrintAttributes.MediaSize media = new PrintAttributes.MediaSize(MEDIA_ID, "Odoo 80 mm â†’ cetak fisik 58 mm", ODOO_SOURCE_WIDTH_MILS, 12000);
        PrintAttributes.Resolution resolution = new PrintAttributes.Resolution("rapprint_" + profile.getDpi() + "dpi_" + profile.getDotWidth() + "dot", profile.getLabel(), profile.getDpi(), profile.getDpi());
        PrinterCapabilitiesInfo build = new PrinterCapabilitiesInfo.Builder(id).addMediaSize(media, true).addResolution(resolution, true).setMinMargins(PrintAttributes.Margins.NO_MARGINS).setColorModes(1, 1).setDuplexModes(1, 1).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }
}

