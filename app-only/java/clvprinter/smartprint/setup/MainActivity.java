package com.clvprinter.smartprint.setup;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Insets;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintManager;
import android.provider.Settings;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.clvprinter.smartprint.R;
import com.clvprinter.smartprint.bluetooth.BondedPrinter;
import com.clvprinter.smartprint.bluetooth.BondedPrinterProvider;
import com.clvprinter.smartprint.bluetooth.BondedPrinterState;
import com.clvprinter.smartprint.diagnostics.ManualPrintController;
import com.clvprinter.smartprint.power.BatteryOptimizationHelper;
import com.clvprinter.smartprint.printer.CutMode;
import com.clvprinter.smartprint.printer.DitherMode;
import com.clvprinter.smartprint.printer.PrinterConfig;
import com.clvprinter.smartprint.printservice.ReceiptPrintService;
import com.clvprinter.smartprint.setup.MainActivity;
import com.clvprinter.smartprint.storage.AppPreferences;
import com.clvprinter.smartprint.storage.JobHistoryRules;
import com.clvprinter.smartprint.storage.JobRecord;
import com.clvprinter.smartprint.storage.JobRepository;
import com.clvprinter.smartprint.storage.JobStatus;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000Ã˜\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u0000 \u0089\u00012\u00020\u0001:\u0006\u0088\u0001\u0089\u0001\u008a\u0001B\u0007Â¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0018H\u0014J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001aH\u0014J\b\u0010\u001e\u001a\u00020\u0018H\u0014J\b\u0010\u001f\u001a\u00020\u0018H\u0002J\b\u0010 \u001a\u00020\u0018H\u0002J-\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#2\u000e\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0%2\u0006\u0010'\u001a\u00020(H\u0016Â¢\u0006\u0002\u0010)J\u0010\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u0016H\u0002J\b\u0010,\u001a\u00020\u0018H\u0002J\b\u0010-\u001a\u00020\u0018H\u0002J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020\u0018H\u0002J\b\u00101\u001a\u00020\u0018H\u0002J\u0018\u00102\u001a\u00020/2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020\u00182\u0006\u00103\u001a\u000204H\u0002J\b\u00108\u001a\u00020\u0018H\u0002J\u0010\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010<\u001a\u00020\u00182\u0006\u0010:\u001a\u00020;H\u0002J\u0010\u0010=\u001a\u00020\u00182\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010>\u001a\u00020\u0018H\u0002J\b\u0010?\u001a\u00020\u0018H\u0002J\u0010\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020BH\u0002JY\u0010C\u001a\u00020/\"\u0004\b\u0000\u0010D2\u0006\u0010E\u001a\u00020&2\u0006\u0010F\u001a\u00020&2\u0018\u0010G\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u0002HD0I0H2\u0006\u00105\u001a\u0002HD2\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u0002HD\u0012\u0004\u0012\u00020\u00180KH\u0002Â¢\u0006\u0002\u0010LJ<\u0010M\u001a\u00020/2\u0006\u0010E\u001a\u00020&2\u0006\u0010N\u001a\u00020&2\u0006\u0010O\u001a\u00020#2\u0006\u0010P\u001a\u00020#2\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00180KH\u0002J4\u0010R\u001a\u00020/2\u0006\u0010E\u001a\u00020&2\u0006\u0010F\u001a\u00020&2\u0006\u0010S\u001a\u0002062\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00180KH\u0002J\u001c\u0010T\u001a\u00020\u00182\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020B0KH\u0002J\u0010\u0010V\u001a\u00020\n2\u0006\u0010W\u001a\u00020XH\u0002J\u0010\u0010Y\u001a\u00020#2\u0006\u0010W\u001a\u00020XH\u0002J\"\u0010Z\u001a\u00020/2\u0006\u0010E\u001a\u00020&2\u0006\u0010[\u001a\u00020&2\b\b\u0002\u0010\\\u001a\u000206H\u0002J\u0018\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020#2\u0006\u0010`\u001a\u00020#H\u0002J\u0010\u0010a\u001a\u00020^2\u0006\u0010b\u001a\u000206H\u0002J\u0010\u0010c\u001a\u00020^2\u0006\u0010b\u001a\u000206H\u0002J\u0010\u0010d\u001a\u00020^2\u0006\u0010b\u001a\u000206H\u0002J\u0014\u0010e\u001a\u000e\u0012\u0004\u0012\u00020f\u0012\u0004\u0012\u00020\u00070IH\u0002J\u0010\u0010g\u001a\u00020\u00182\u0006\u0010h\u001a\u00020/H\u0002J \u0010i\u001a\u00020\u00182\u0006\u0010j\u001a\u00020\u00072\u0006\u0010k\u001a\u00020/2\u0006\u0010l\u001a\u00020#H\u0002J\b\u0010m\u001a\u00020nH\u0002J\b\u0010o\u001a\u00020\u0018H\u0002J6\u0010p\u001a\u00020\u00182\u0006\u0010E\u001a\u00020&2\u0006\u0010q\u001a\u00020&2\u0006\u0010r\u001a\u00020&2\u0006\u0010s\u001a\u00020&2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020\u00180uH\u0002J\b\u0010v\u001a\u00020\u0018H\u0002J\b\u0010w\u001a\u00020\u0018H\u0002J\u0018\u0010x\u001a\u00020\u00182\u0006\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020&H\u0002J\b\u0010|\u001a\u000206H\u0002J*\u0010}\u001a\u00020&2\u0006\u0010~\u001a\u0002062\u0006\u0010\u007f\u001a\u0002062\u0007\u0010\u0080\u0001\u001a\u0002062\u0007\u0010\u0081\u0001\u001a\u000206H\u0002J-\u0010\u0082\u0001\u001a\u00020&2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0006\u0010\u007f\u001a\u0002062\u0007\u0010\u0080\u0001\u001a\u0002062\u0007\u0010\u0081\u0001\u001a\u000206H\u0002J\u0013\u0010\u0085\u0001\u001a\u00020&2\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.Â¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000eÂ¢\u0006\u0002\n\u0000Â¨\u0006\u008b\u0001"}, d2 = {"Lcom/rapprinter/smartprint/setup/MainActivity;", "Landroid/app/Activity;", "<init>", "()V", "contentContainer", "Landroid/widget/FrameLayout;", "topBar", "Landroid/widget/LinearLayout;", "bottomNav", "topTitle", "Landroid/widget/TextView;", "topStatus", "navSetup", "navHistory", "navSettings", "preferences", "Lcom/rapprinter/smartprint/storage/AppPreferences;", "jobs", "Lcom/rapprinter/smartprint/storage/JobRepository;", "manualPrint", "Lcom/rapprinter/smartprint/diagnostics/ManualPrintController;", "currentScreen", "Lcom/rapprinter/smartprint/setup/MainActivity$Screen;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onSaveInstanceState", "outState", "onDestroy", "configureEdgeToEdgeWindow", "applySystemInsets", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "selectScreen", "screen", "renderCurrentScreen", "showSetup", "batteryPanel", "Landroid/view/View;", "requestIgnoreBatteryOptimizations", "openOemAutoStartSettings", "printerRow", "printer", "Lcom/rapprinter/smartprint/bluetooth/BondedPrinter;", "selected", "", "startDiagnostic", "showHistory", "jobCard", "record", "Lcom/rapprinter/smartprint/storage/JobRecord;", "requestRetry", "startRetry", "confirmClearHistory", "showSettings", "densityPanel", "config", "Lcom/rapprinter/smartprint/printer/PrinterConfig;", "choicePanel", "T", "title", "caption", "choices", "", "Lkotlin/Pair;", "onSelected", "Lkotlin/Function1;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "seekPanel", "initialLabel", "progress", "max", "onChanged", "switchPanel", "checked", "updateConfig", "transform", "statusPill", "status", "Lcom/rapprinter/smartprint/storage/JobStatus;", "statusColor", "messagePanel", "copy", "danger", "borderedPanel", "Landroid/graphics/drawable/GradientDrawable;", "stroke", "fill", "selectionSurface", "active", "markerSurface", "toggleSurface", "screenBody", "Landroid/widget/ScrollView;", "setContent", "view", "add", "parent", "child", "topMarginDp", "matchWrap", "Landroid/widget/LinearLayout$LayoutParams;", "requestBluetoothPermission", "showWorkbenchDialog", "message", "negativeLabel", "positiveLabel", "onPositive", "Lkotlin/Function0;", "openBluetoothSettings", "openPrintSettings", "openSettings", "intent", "Landroid/content/Intent;", "failure", "isPrintServiceEnabled", "nextActionTitle", "permissionReady", "printerReady", "serviceReady", "batteryReady", "nextActionCopy", "state", "Lcom/rapprinter/smartprint/bluetooth/BondedPrinterState;", "formatDate", "timestamp", "", "Screen", "Companion", "ChoiceVisual", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MainActivity extends Activity {
    private static final int BOTTOM_NAV_HEIGHT_DP = 68;
    private static final String ENABLED_PRINT_SERVICES_SETTING = "enabled_print_services";
    private static final String KEY_SCREEN = "current_screen";
    private static final int REQUEST_BLUETOOTH = 4101;
    private static final int TOP_BAR_HEIGHT_DP = 64;
    private LinearLayout bottomNav;
    private FrameLayout contentContainer;
    private Screen currentScreen = Screen.SETUP;
    private JobRepository jobs;
    private ManualPrintController manualPrint;
    private TextView navHistory;
    private TextView navSettings;
    private TextView navSetup;
    private AppPreferences preferences;
    private LinearLayout topBar;
    private TextView topStatus;
    private TextView topTitle;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006Â¨\u0006\u0007"}, d2 = {"Lcom/rapprinter/smartprint/setup/MainActivity$Screen;", "", "<init>", "(Ljava/lang/String;I)V", "SETUP", "HISTORY", "SETTINGS", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    enum Screen {
        SETUP,
        HISTORY,
        SETTINGS;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Screen> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: MainActivity.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Screen.values().length];
            try {
                iArr[Screen.SETUP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Screen.HISTORY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Screen.SETTINGS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[JobStatus.values().length];
            try {
                iArr2[JobStatus.QUEUED.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[JobStatus.PRINTING.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[JobStatus.COMPLETED.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[JobStatus.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[JobStatus.CANCELED.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[JobStatus.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00fd, code lost:
    
        if (r1 == null) goto L26;
     */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onCreate(Bundle savedInstanceState) {
        Screen screen;
        String string;
        Object obj;
        super.onCreate(savedInstanceState);
        configureEdgeToEdgeWindow();
        setContentView(R.layout.activity_main);
        this.preferences = new AppPreferences(this);
        this.jobs = new JobRepository(this);
        this.manualPrint = new ManualPrintController(this);
        View findViewById = findViewById(R.id.contentContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.contentContainer = (FrameLayout) findViewById;
        View findViewById2 = findViewById(R.id.topBar);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.topBar = (LinearLayout) findViewById2;
        View findViewById3 = findViewById(R.id.bottomNav);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.bottomNav = (LinearLayout) findViewById3;
        View findViewById4 = findViewById(R.id.topTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.topTitle = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.topStatus);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.topStatus = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.navSetup);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.navSetup = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.navHistory);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.navHistory = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.navSettings);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.navSettings = (TextView) findViewById8;
        applySystemInsets();
        TextView textView = this.navSetup;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navSetup");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.selectScreen(MainActivity.Screen.SETUP);
            }
        });
        TextView textView2 = this.navHistory;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navHistory");
            textView2 = null;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.selectScreen(MainActivity.Screen.HISTORY);
            }
        });
        TextView textView3 = this.navSettings;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navSettings");
            textView3 = null;
        }
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.this.selectScreen(MainActivity.Screen.SETTINGS);
            }
        });
        if (savedInstanceState != null && (string = savedInstanceState.getString(KEY_SCREEN)) != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                MainActivity mainActivity = this;
                obj = Result.m8constructorimpl(Screen.valueOf(string));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                obj = Result.m8constructorimpl(ResultKt.createFailure(th));
            }
            screen = (Screen) (Result.m14isFailureimpl(obj) ? null : obj);
        }
        screen = Screen.SETUP;
        selectScreen(screen);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        renderCurrentScreen();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        outState.putString(KEY_SCREEN, this.currentScreen.name());
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        ManualPrintController manualPrintController = this.manualPrint;
        if (manualPrintController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manualPrint");
            manualPrintController = null;
        }
        manualPrintController.close();
        super.onDestroy();
    }

    private final void configureEdgeToEdgeWindow() {
        getWindow().getDecorView();
        getWindow().setStatusBarColor(0);
        getWindow().setNavigationBarColor(0);
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(false);
            WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.setSystemBarsAppearance(16, 16);
            }
        } else {
            getWindow().getDecorView().setSystemUiVisibility(1808);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            getWindow().setNavigationBarDividerColor(0);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            getWindow().setNavigationBarContrastEnforced(false);
            getWindow().setStatusBarContrastEnforced(false);
        }
    }

    private final void applySystemInsets() {
        View root = findViewById(R.id.appRoot);
        root.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda25
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return MainActivity.applySystemInsets$lambda$7(MainActivity.this, view, windowInsets);
            }
        });
        root.requestApplyInsets();
    }

    static final WindowInsets applySystemInsets$lambda$7(MainActivity this$0, View view, WindowInsets insets) {
        int cutoutTop;
        int topInset;
        int bottomInset;
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Intrinsics.checkNotNullParameter(insets, "insets");
        if (Build.VERSION.SDK_INT >= 30) {
            Insets safe = insets.getInsets(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
            Intrinsics.checkNotNullExpressionValue(safe, "getInsets(...)");
            topInset = safe.top;
            bottomInset = safe.bottom;
        } else {
            if (Build.VERSION.SDK_INT >= 28) {
                DisplayCutout displayCutout = insets.getDisplayCutout();
                cutoutTop = displayCutout != null ? displayCutout.getSafeInsetTop() : 0;
            } else {
                cutoutTop = 0;
            }
            topInset = Math.max(insets.getSystemWindowInsetTop(), cutoutTop);
            bottomInset = insets.getSystemWindowInsetBottom();
        }
        float fontScaleExtra = RangesKt.coerceAtLeast(this$0.getResources().getConfiguration().fontScale - 1.0f, 0.0f);
        int scaledTopHeight = ((int) (36.0f * fontScaleExtra)) + 64;
        int scaledBottomHeight = ((int) (32.0f * fontScaleExtra)) + BOTTOM_NAV_HEIGHT_DP;
        LinearLayout linearLayout = this$0.topBar;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topBar");
            linearLayout = null;
        }
        LinearLayout linearLayout3 = this$0.topBar;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topBar");
            linearLayout3 = null;
        }
        ViewGroup.LayoutParams layoutParams = linearLayout3.getLayoutParams();
        layoutParams.height = UiKitKt.dp(this$0, scaledTopHeight) + topInset;
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout linearLayout4 = this$0.topBar;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topBar");
            linearLayout4 = null;
        }
        linearLayout4.setPadding(UiKitKt.dp(this$0, 18), topInset, UiKitKt.dp(this$0, 18), 0);
        LinearLayout linearLayout5 = this$0.bottomNav;
        if (linearLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomNav");
            linearLayout5 = null;
        }
        LinearLayout linearLayout6 = this$0.bottomNav;
        if (linearLayout6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomNav");
            linearLayout6 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = linearLayout6.getLayoutParams();
        layoutParams2.height = UiKitKt.dp(this$0, scaledBottomHeight) + bottomInset;
        linearLayout5.setLayoutParams(layoutParams2);
        LinearLayout linearLayout7 = this$0.bottomNav;
        if (linearLayout7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bottomNav");
        } else {
            linearLayout2 = linearLayout7;
        }
        linearLayout2.setPadding(UiKitKt.dp(this$0, 8), UiKitKt.dp(this$0, 5), UiKitKt.dp(this$0, 8), UiKitKt.dp(this$0, 5) + bottomInset);
        return insets;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_BLUETOOTH) {
            Integer firstOrNull = ArraysKt.firstOrNull(grantResults);
            boolean granted = firstOrNull != null && firstOrNull.intValue() == 0;
            Toast.makeText(this, granted ? "Izin Bluetooth diberikan" : "Izin Bluetooth belum diberikan", 0).show();
            renderCurrentScreen();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [int] */
    /* JADX WARN: Type inference failed for: r15v6 */
    public final void selectScreen(Screen screen) {
        this.currentScreen = screen;
        Pair[] pairArr = new Pair[3];
        TextView textView = this.navSetup;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navSetup");
            textView = null;
        }
        boolean z = false;
        pairArr[0] = TuplesKt.to(textView, Screen.SETUP);
        TextView textView2 = this.navHistory;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navHistory");
            textView2 = null;
        }
        pairArr[1] = TuplesKt.to(textView2, Screen.HISTORY);
        TextView textView3 = this.navSettings;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navSettings");
            textView3 = null;
        }
        pairArr[2] = TuplesKt.to(textView3, Screen.SETTINGS);
        Iterable<Pair> items = CollectionsKt.listOf((Object[]) pairArr);
        for (Pair pair : items) {
            TextView textView4 = (TextView) pair.component1();
            boolean z2 = ((Screen) pair.component2()) == screen ? true : z;
            textView4.setBackground(z2 ? getDrawable(R.drawable.bg_nav_selected) : null);
            int color = getColor(z2 ? R.color.cobalt : R.color.muted);
            textView4.setTextColor(color);
            textView4.setCompoundDrawableTintList(ColorStateList.valueOf(color));
            textView4.setTypeface(Typeface.create("sans-serif", (int) (z2 ? 1 : z)));
            textView4.setSelected(z2);
            textView4.setContentDescription(((Object) textView4.getText()) + ", " + (z2 ? "dipilih" : "tidak dipilih"));
            z = false;
        }
        renderCurrentScreen();
    }

    private final void renderCurrentScreen() {
        switch (WhenMappings.$EnumSwitchMapping$0[this.currentScreen.ordinal()]) {
            case 1:
                showSetup();
                return;
            case 2:
                showHistory();
                return;
            case 3:
                showSettings();
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x038d, code lost:
    
        if (r18.isBusy() == false) goto L101;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void showSetup() {
        Object obj;
        int i;
        TextView text;
        TextView text2;
        String str;
        LinearLayout body;
        ScrollView scroll;
        boolean z;
        TextView text3;
        ManualPrintController manualPrintController;
        TextView textView = this.topTitle;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topTitle");
            textView = null;
        }
        textView.setText("Siapkan printer");
        BondedPrinterState state = BondedPrinterProvider.INSTANCE.read(this);
        BondedPrinterState.Ready ready = state instanceof BondedPrinterState.Ready ? (BondedPrinterState.Ready) state : null;
        List printers = ready != null ? ready.getPrinters() : null;
        if (printers == null) {
            printers = CollectionsKt.emptyList();
        }
        final List printers2 = printers;
        boolean permissionReady = !(state instanceof BondedPrinterState.PermissionRequired);
        boolean serviceReady = isPrintServiceEnabled();
        Iterator it = printers2.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                String address = ((BondedPrinter) obj).getAddress();
                AppPreferences appPreferences = this.preferences;
                if (appPreferences == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("preferences");
                    appPreferences = null;
                }
                if (Intrinsics.areEqual(address, appPreferences.getSelectedPrinterAddress())) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        BondedPrinter selected = (BondedPrinter) obj;
        boolean printerReady = selected != null;
        boolean batteryReady = BatteryOptimizationHelper.INSTANCE.isIgnoringBatteryOptimizations(this);
        TextView textView2 = this.topStatus;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topStatus");
            textView2 = null;
        }
        Iterable listOf = CollectionsKt.listOf((Object[]) new Boolean[]{Boolean.valueOf(permissionReady), Boolean.valueOf(printerReady), Boolean.valueOf(serviceReady), Boolean.valueOf(batteryReady)});
        if ((listOf instanceof Collection) && ((Collection) listOf).isEmpty()) {
            i = 0;
        } else {
            i = 0;
            Iterator it2 = listOf.iterator();
            while (it2.hasNext()) {
                if (((Boolean) it2.next()).booleanValue() && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        textView2.setText(i + "/4 AKTIF");
        Pair<ScrollView, LinearLayout> screenBody = screenBody();
        ScrollView scroll2 = screenBody.component1();
        LinearLayout body2 = screenBody.component2();
        ReceiptStatusView receiptStatusView = new ReceiptStatusView(this, null, 2, null);
        receiptStatusView.setPermissionReady(permissionReady);
        receiptStatusView.setPrinterReady(printerReady);
        receiptStatusView.setServiceReady(serviceReady);
        receiptStatusView.setBatteryReady(batteryReady);
        AppPreferences appPreferences2 = this.preferences;
        if (appPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
            appPreferences2 = null;
        }
        receiptStatusView.setProfileLabel(appPreferences2.loadConfig().getProfile().getLabel());
        body2.addView(receiptStatusView, matchWrap());
        add(body2, UiKit.INSTANCE.eyebrow(this, "Langkah berikutnya"), 4);
        text = UiKit.INSTANCE.text(r20, nextActionTitle(permissionReady, printerReady, serviceReady, batteryReady), (r14 & 4) != 0 ? 16.0f : 22.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        add(body2, text, 6);
        text2 = UiKit.INSTANCE.text(r20, nextActionCopy(state, printerReady, serviceReady, batteryReady), (r14 & 4) != 0 ? 16.0f : 15.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        add(body2, text2, 7);
        if (!permissionReady) {
            str = "preferences";
            add(body2, UiKit.INSTANCE.button(this, "Izinkan akses Bluetooth", true, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showSetup$lambda$12(MainActivity.this);
                }
            }), 14);
        } else {
            str = "preferences";
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(0);
        linearLayout.setWeightSum(2.0f);
        Button button = UiKit.INSTANCE.button(this, "Pasangkan printer", false, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showSetup$lambda$14(MainActivity.this);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, UiKitKt.dp(this, 50), 1.0f);
        layoutParams.setMarginEnd(UiKitKt.dp(this, 5));
        Unit unit = Unit.INSTANCE;
        linearLayout.addView(button, layoutParams);
        Button button2 = UiKit.INSTANCE.button(this, serviceReady ? "Layanan aktif" : "Aktifkan layanan", false, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showSetup$lambda$16(MainActivity.this);
            }
        });
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, UiKitKt.dp(this, 50), 1.0f);
        layoutParams2.setMarginStart(UiKitKt.dp(this, 5));
        Unit unit2 = Unit.INSTANCE;
        linearLayout.addView(button2, layoutParams2);
        add(body2, linearLayout, 12);
        if (!batteryReady) {
            add(body2, batteryPanel(), 12);
        }
        add(body2, UiKit.INSTANCE.eyebrow(this, "Printer terpasang"), 26);
        if (state instanceof BondedPrinterState.Ready) {
            if (((BondedPrinterState.Ready) state).getPrinters().isEmpty()) {
                scroll = scroll2;
                body = body2;
                z = false;
                add(body, messagePanel$default(this, "Belum ada printer terpasang", "Buka pengaturan Bluetooth, pasangkan printer termal, lalu kembali ke RapPrint.", false, 4, null), 10);
            } else {
                body = body2;
                scroll = scroll2;
                z = false;
                Iterable<BondedPrinter> printers3 = ((BondedPrinterState.Ready) state).getPrinters();
                int i2 = 0;
                for (BondedPrinter bondedPrinter : printers3) {
                    Iterable iterable = printers3;
                    String address2 = bondedPrinter.getAddress();
                    int i3 = i2;
                    AppPreferences appPreferences3 = this.preferences;
                    if (appPreferences3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str);
                        appPreferences3 = null;
                    }
                    add(body, printerRow(bondedPrinter, Intrinsics.areEqual(address2, appPreferences3.getSelectedPrinterAddress())), 8);
                    printers3 = iterable;
                    i2 = i3;
                }
            }
        } else {
            body = body2;
            scroll = scroll2;
            z = false;
            if (Intrinsics.areEqual(state, BondedPrinterState.PermissionRequired.INSTANCE)) {
                add(body, messagePanel$default(this, "Daftar dikunci oleh izin", "RapPrint hanya membaca perangkat yang sudah dipasangkan; tidak memindai perangkat baru.", false, 4, null), 10);
            } else if (Intrinsics.areEqual(state, BondedPrinterState.BluetoothDisabled.INSTANCE)) {
                add(body, messagePanel$default(this, "Bluetooth sedang mati", "Nyalakan Bluetooth agar printer terpasang dapat dibaca.", false, 4, null), 10);
            } else if (Intrinsics.areEqual(state, BondedPrinterState.HardwareUnavailable.INSTANCE)) {
                add(body, messagePanel$default(this, "Bluetooth tidak tersedia", "Emulator atau perangkat ini tidak memiliki radio Bluetooth Classic.", false, 4, null), 10);
            } else if (state instanceof BondedPrinterState.Error) {
                add(body, messagePanel$default(this, "Printer tidak dapat dimuat", ((BondedPrinterState.Error) state).getMessage(), false, 4, null), 10);
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        UiKit uiKit = UiKit.INSTANCE;
        MainActivity mainActivity = this;
        ManualPrintController manualPrintController2 = this.manualPrint;
        if (manualPrintController2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manualPrint");
            manualPrintController2 = null;
        }
        boolean z2 = true;
        Button button3 = uiKit.button(mainActivity, manualPrintController2.isBusy() ? "Tes sedang dikirimâ€¦" : "Cetak struk diagnostik", true, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showSetup$lambda$20(printers2, this);
            }
        });
        if (selected != null) {
            ManualPrintController manualPrintController3 = this.manualPrint;
            if (manualPrintController3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("manualPrint");
                manualPrintController = null;
            } else {
                manualPrintController = manualPrintController3;
            }
        }
        z2 = z;
        button3.setEnabled(z2);
        add(body, button3, 18);
        text3 = UiKit.INSTANCE.text(r24, "Tes mengirim teks Latin, angka, QR, Code 128, feed, dan cut sesuai pengaturan aktif.", (r14 & 4) != 0 ? 16.0f : 13.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        add(body, text3, 8);
        setContent(scroll);
    }

    static final Unit showSetup$lambda$12(MainActivity this$0) {
        this$0.requestBluetoothPermission();
        return Unit.INSTANCE;
    }

    static final Unit showSetup$lambda$14(MainActivity this$0) {
        this$0.openBluetoothSettings();
        return Unit.INSTANCE;
    }

    static final Unit showSetup$lambda$16(MainActivity this$0) {
        this$0.openPrintSettings();
        return Unit.INSTANCE;
    }

    static final Unit showSetup$lambda$20(List list, MainActivity mainActivity) {
        Object obj;
        Iterator it = list.iterator();
        while (true) {
            obj = null;
            AppPreferences appPreferences = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            String address = ((BondedPrinter) next).getAddress();
            AppPreferences appPreferences2 = mainActivity.preferences;
            if (appPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferences");
            } else {
                appPreferences = appPreferences2;
            }
            if (Intrinsics.areEqual(address, appPreferences.getSelectedPrinterAddress())) {
                obj = next;
                break;
            }
        }
        BondedPrinter bondedPrinter = (BondedPrinter) obj;
        if (bondedPrinter == null) {
            Toast.makeText(mainActivity, "Pilih printer terpasang terlebih dahulu", 0).show();
        } else {
            mainActivity.startDiagnostic(bondedPrinter);
        }
        return Unit.INSTANCE;
    }

    private final View batteryPanel() {
        TextView text;
        TextView text2;
        int accent = getColor(R.color.danger);
        LinearLayout panel = UiKit.INSTANCE.panel(this, 15);
        panel.setBackground(borderedPanel(accent, getColor(R.color.soft_red)));
        text = UiKit.INSTANCE.text(r3, "Layanan bisa dibekukan sistem", (r14 & 4) != 0 ? 16.0f : 15.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : accent, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        panel.addView(text);
        text2 = UiKit.INSTANCE.text(r14, "Beberapa HP (MIUI, ColorOS, Funtouch/OriginOS, EMUI) membekukan aplikasi di latar setelah tidak dipakai, sehingga layanan cetak baru aktif lagi setelah RapPrint dibuka manual. Kecualikan RapPrint dari penghemat baterai dan izinkan mulai otomatis agar layanan tetap dapat dibangunkan sistem setelah ponsel di-restart.", (r14 & 4) != 0 ? 16.0f : 13.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 6);
        Unit unit = Unit.INSTANCE;
        panel.addView(text2, matchWrap);
        LinearLayout actions = new LinearLayout(this);
        actions.setOrientation(0);
        actions.setWeightSum(2.0f);
        Button button = UiKit.INSTANCE.button(this, "Kecualikan dari baterai", true, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.batteryPanel$lambda$25(MainActivity.this);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, UiKitKt.dp(this, 50), 1.0f);
        layoutParams.setMarginEnd(UiKitKt.dp(this, 5));
        Unit unit2 = Unit.INSTANCE;
        actions.addView(button, layoutParams);
        Button button2 = UiKit.INSTANCE.button(this, "Izinkan mulai otomatis", false, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.batteryPanel$lambda$27(MainActivity.this);
            }
        });
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, UiKitKt.dp(this, 50), 1.0f);
        layoutParams2.setMarginStart(UiKitKt.dp(this, 5));
        Unit unit3 = Unit.INSTANCE;
        actions.addView(button2, layoutParams2);
        LinearLayout.LayoutParams matchWrap2 = matchWrap();
        matchWrap2.topMargin = UiKitKt.dp(this, 13);
        Unit unit4 = Unit.INSTANCE;
        panel.addView(actions, matchWrap2);
        return panel;
    }

    static final Unit batteryPanel$lambda$25(MainActivity this$0) {
        this$0.requestIgnoreBatteryOptimizations();
        return Unit.INSTANCE;
    }

    static final Unit batteryPanel$lambda$27(MainActivity this$0) {
        this$0.openOemAutoStartSettings();
        return Unit.INSTANCE;
    }

    private final void requestIgnoreBatteryOptimizations() {
        try {
            startActivity(BatteryOptimizationHelper.INSTANCE.requestIgnoreBatteryOptimizationsIntent(this));
        } catch (Exception e) {
            Toast.makeText(this, "Pengaturan penghemat baterai tidak tersedia di perangkat ini", 1).show();
        }
    }

    private final void openOemAutoStartSettings() {
        Intent intent = BatteryOptimizationHelper.INSTANCE.oemAutoStartIntent(this);
        if (intent == null) {
            Toast.makeText(this, "Perangkat ini tidak memiliki pengaturan mulai otomatis khusus", 1).show();
            return;
        }
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Pengaturan mulai otomatis tidak dapat dibuka", 1).show();
        }
    }

    private final View printerRow(final BondedPrinter printer, boolean selected) {
        TextView text;
        TextView text2;
        TextView text3;
        LinearLayout panel = UiKit.INSTANCE.panel(this, 14);
        panel.setClickable(true);
        panel.setFocusable(true);
        panel.setContentDescription(printer.getName() + ", " + printer.getAddress() + (selected ? ", dipilih" : ""));
        if (selected) {
            panel.setBackground(borderedPanel(getColor(R.color.cobalt), getColor(R.color.paper_white)));
        }
        panel.setOnClickListener(new View.OnClickListener() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda40
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.printerRow$lambda$31$lambda$30(MainActivity.this, printer, view);
            }
        });
        LinearLayout top = new LinearLayout(this);
        top.setOrientation(0);
        top.setGravity(16);
        LinearLayout names = new LinearLayout(this);
        names.setOrientation(1);
        text = UiKit.INSTANCE.text(r9, printer.getName(), (r14 & 4) != 0 ? 16.0f : 16.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        names.addView(text);
        text2 = UiKit.INSTANCE.text(r9, printer.getAddress(), (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : true);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 3);
        Unit unit = Unit.INSTANCE;
        names.addView(text2, matchWrap);
        top.addView(names, new LinearLayout.LayoutParams(0, -2, 1.0f));
        top.addView(UiKit.INSTANCE.pill(this, selected ? "DIPILIH" : "TERPASANG", getColor(selected ? R.color.cobalt : R.color.muted), getColor(selected ? R.color.soft_blue : R.color.paper)));
        panel.addView(top);
        text3 = UiKit.INSTANCE.text(r8, "Bluetooth Classic / SPP", (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap2 = matchWrap();
        matchWrap2.topMargin = UiKitKt.dp(this, 9);
        Unit unit2 = Unit.INSTANCE;
        panel.addView(text3, matchWrap2);
        return panel;
    }

    static final void printerRow$lambda$31$lambda$30(MainActivity this$0, BondedPrinter $printer, View it) {
        AppPreferences appPreferences = this$0.preferences;
        AppPreferences appPreferences2 = null;
        if (appPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
            appPreferences = null;
        }
        appPreferences.setSelectedPrinterAddress($printer.getAddress());
        AppPreferences appPreferences3 = this$0.preferences;
        if (appPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        } else {
            appPreferences2 = appPreferences3;
        }
        appPreferences2.setSelectedPrinterName($printer.getName());
        this$0.showSetup();
    }

    private final void startDiagnostic(BondedPrinter printer) {
        ManualPrintController manualPrintController = this.manualPrint;
        if (manualPrintController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manualPrint");
            manualPrintController = null;
        }
        boolean started = manualPrintController.printDiagnostic(printer.getName(), printer.getAddress(), new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.startDiagnostic$lambda$37(MainActivity.this, (JobRecord) obj);
            }
        }, new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.startDiagnostic$lambda$39(MainActivity.this, (JobRecord) obj);
            }
        });
        if (started) {
            showSetup();
        } else {
            Toast.makeText(this, "Satu tugas cetak lain masih berjalan", 0).show();
        }
    }

    static final Unit startDiagnostic$lambda$37(final MainActivity this$0, JobRecord it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.runOnUiThread(new Runnable() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda43
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startDiagnostic$lambda$37$lambda$36(MainActivity.this);
            }
        });
        return Unit.INSTANCE;
    }

    static final void startDiagnostic$lambda$37$lambda$36(MainActivity this$0) {
        if (this$0.currentScreen == Screen.SETUP) {
            this$0.showSetup();
        }
    }

    static final Unit startDiagnostic$lambda$39(final MainActivity this$0, final JobRecord record) {
        Intrinsics.checkNotNullParameter(record, "record");
        this$0.runOnUiThread(new Runnable() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startDiagnostic$lambda$39$lambda$38(MainActivity.this, record);
            }
        });
        return Unit.INSTANCE;
    }

    static final void startDiagnostic$lambda$39$lambda$38(MainActivity this$0, JobRecord $record) {
        String error;
        MainActivity mainActivity = this$0;
        if ($record.getStatus() == JobStatus.COMPLETED) {
            error = "Data tes diterima transport Bluetooth";
        } else {
            error = $record.getError();
            if (error == null) {
                error = "Tes gagal";
            }
        }
        Toast.makeText(mainActivity, error, 1).show();
        if (this$0.currentScreen == Screen.SETUP) {
            this$0.showSetup();
        }
    }

    private final void showHistory() {
        TextView text;
        TextView text2;
        TextView text3;
        TextView text4;
        TextView textView = this.topTitle;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topTitle");
            textView = null;
        }
        textView.setText("Riwayat cetak");
        JobRepository jobRepository = this.jobs;
        if (jobRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jobs");
            jobRepository = null;
        }
        List records = jobRepository.records();
        TextView textView3 = this.topStatus;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topStatus");
        } else {
            textView2 = textView3;
        }
        textView2.setText(records.size() + " TUGAS");
        Pair<ScrollView, LinearLayout> screenBody = screenBody();
        ScrollView scroll = screenBody.component1();
        LinearLayout body = screenBody.component2();
        LinearLayout heading = new LinearLayout(this);
        heading.setOrientation(0);
        heading.setGravity(16);
        LinearLayout headingText = new LinearLayout(this);
        headingText.setOrientation(1);
        headingText.addView(UiKit.INSTANCE.eyebrow(this, "Log lokal"));
        text = UiKit.INSTANCE.text(r9, "Jejak pengiriman", (r14 & 4) != 0 ? 16.0f : 23.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 5);
        Unit unit = Unit.INSTANCE;
        headingText.addView(text, matchWrap);
        heading.addView(headingText, new LinearLayout.LayoutParams(0, -2, 1.0f));
        if (!records.isEmpty()) {
            Button clear = UiKit.INSTANCE.button(this, "Hapus", false, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.showHistory$lambda$43(MainActivity.this);
                }
            });
            heading.addView(clear, new LinearLayout.LayoutParams(-2, UiKitKt.dp(this, 48)));
        }
        body.addView(heading);
        text2 = UiKit.INSTANCE.text(r8, "Status tersimpan tanpa merekam isi struk. Penanda selesai tetap dipertahankan untuk mencegah pengiriman ganda.", (r14 & 4) != 0 ? 16.0f : 13.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        add(body, text2, 9);
        if (records.isEmpty()) {
            LinearLayout empty = UiKit.INSTANCE.panel(this, 20);
            empty.addView(UiKit.INSTANCE.text(this, "BELUM ADA TUGAS CETAK", 12.0f, getColor(R.color.cobalt), true, true));
            text3 = UiKit.INSTANCE.text(r9, "Riwayat akan muncul setelah tes diagnostik atau cetak dari preview Android.", (r14 & 4) != 0 ? 16.0f : 17.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
            LinearLayout.LayoutParams matchWrap2 = matchWrap();
            matchWrap2.topMargin = UiKitKt.dp(this, 10);
            Unit unit2 = Unit.INSTANCE;
            empty.addView(text3, matchWrap2);
            text4 = UiKit.INSTANCE.text(r10, "Buka Siapkan untuk memastikan izin, printer, dan layanan sudah aktif.", (r14 & 4) != 0 ? 16.0f : 14.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
            LinearLayout.LayoutParams matchWrap3 = matchWrap();
            matchWrap3.topMargin = UiKitKt.dp(this, 8);
            Unit unit3 = Unit.INSTANCE;
            empty.addView(text4, matchWrap3);
            add(body, empty, 24);
        } else {
            Iterator it = records.iterator();
            while (it.hasNext()) {
                add(body, jobCard((JobRecord) it.next()), 12);
            }
        }
        setContent(scroll);
    }

    static final Unit showHistory$lambda$43(MainActivity this$0) {
        this$0.confirmClearHistory();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x023e, code lost:
    
        if (new java.io.File(r6).isFile() == true) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final View jobCard(final JobRecord record) {
        TextView text;
        TextView text2;
        TextView text3;
        TextView text4;
        boolean z;
        TextView text5;
        TextView text6;
        LinearLayout panel = UiKit.INSTANCE.panel(this, 15);
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(0);
        header.setGravity(16);
        header.addView(statusPill(record.getStatus()));
        text = UiKit.INSTANCE.text(r7, formatDate(record.getTimestamp()), (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : true);
        text.setGravity(8388613);
        header.addView(text, new LinearLayout.LayoutParams(0, -2, 1.0f));
        panel.addView(header);
        text2 = UiKit.INSTANCE.text(r7, record.getDocumentName(), (r14 & 4) != 0 ? 16.0f : 17.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 13);
        Unit unit = Unit.INSTANCE;
        panel.addView(text2, matchWrap);
        text3 = UiKit.INSTANCE.text(r11, record.getPrinterName() + "  /  " + record.getPrinterAddress(), (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : true);
        LinearLayout.LayoutParams matchWrap2 = matchWrap();
        matchWrap2.topMargin = UiKitKt.dp(this, 6);
        Unit unit2 = Unit.INSTANCE;
        panel.addView(text3, matchWrap2);
        text4 = UiKit.INSTANCE.text(r12, "Percobaan " + record.getAttempt() + "  â€¢  " + record.getRequestedCopies() + " salinan  â€¢  " + record.getBytesSent() + "/" + record.getTotalBytes() + " byte", (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : true);
        LinearLayout.LayoutParams matchWrap3 = matchWrap();
        matchWrap3.topMargin = UiKitKt.dp(this, 4);
        Unit unit3 = Unit.INSTANCE;
        panel.addView(text4, matchWrap3);
        if (record.getStatus() == JobStatus.PRINTING || record.getBytesSent() > 0) {
            WorkbenchProgressView workbenchProgressView = new WorkbenchProgressView(this, null, 2, null);
            workbenchProgressView.setProgressPercent(record.getProgressPercent());
            LinearLayout.LayoutParams matchWrap4 = matchWrap();
            matchWrap4.topMargin = UiKitKt.dp(this, 10);
            Unit unit4 = Unit.INSTANCE;
            panel.addView(workbenchProgressView, matchWrap4);
        }
        String error = record.getError();
        if (error != null) {
            text6 = UiKit.INSTANCE.text(r12, error, (r14 & 4) != 0 ? 16.0f : 14.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : statusColor(record.getStatus()), (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
            LinearLayout.LayoutParams matchWrap5 = matchWrap();
            matchWrap5.topMargin = UiKitKt.dp(this, 11);
            Unit unit5 = Unit.INSTANCE;
            panel.addView(text6, matchWrap5);
        }
        String help = record.getHelp();
        if (help != null) {
            text5 = UiKit.INSTANCE.text(r12, help, (r14 & 4) != 0 ? 16.0f : 13.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
            LinearLayout.LayoutParams matchWrap6 = matchWrap();
            matchWrap6.topMargin = UiKitKt.dp(this, 6);
            Unit unit6 = Unit.INSTANCE;
            panel.addView(text5, matchWrap6);
        }
        JobHistoryRules jobHistoryRules = JobHistoryRules.INSTANCE;
        JobStatus status = record.getStatus();
        String cachedPdfPath = record.getCachedPdfPath();
        if (cachedPdfPath != null) {
            z = true;
        }
        z = false;
        boolean canRetry = jobHistoryRules.mayRetry(status, z);
        if (canRetry) {
            Button button = UiKit.INSTANCE.button(this, "Cetak ulang sebagai tugas baru", false, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MainActivity.jobCard$lambda$58(MainActivity.this, record);
                }
            });
            LinearLayout.LayoutParams matchWrap7 = matchWrap();
            matchWrap7.topMargin = UiKitKt.dp(this, 13);
            Unit unit7 = Unit.INSTANCE;
            panel.addView(button, matchWrap7);
        }
        return panel;
    }

    static final Unit jobCard$lambda$58(MainActivity this$0, JobRecord $record) {
        this$0.requestRetry($record);
        return Unit.INSTANCE;
    }

    private final void requestRetry(final JobRecord record) {
        Function0 action = new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.requestRetry$lambda$60(MainActivity.this, record);
            }
        };
        if (JobHistoryRules.INSTANCE.requiresDuplicateWarning(record.getStatus(), record.getBytesSent(), record.getDeliveryAttempted())) {
            showWorkbenchDialog("Ada risiko cetak ganda", "Sebagian data mungkin sudah diterima printer. Periksa kertas terlebih dahulu. Melanjutkan akan membuat percobaan baru dan dapat mencetak nota dua kali.", "Batal", "Sudah diperiksa", action);
        } else {
            action.invoke();
        }
    }

    static final Unit requestRetry$lambda$60(MainActivity this$0, JobRecord $record) {
        this$0.startRetry($record);
        return Unit.INSTANCE;
    }

    private final void startRetry(JobRecord record) {
        ManualPrintController manualPrintController = this.manualPrint;
        if (manualPrintController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manualPrint");
            manualPrintController = null;
        }
        boolean started = manualPrintController.retry(record, new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.startRetry$lambda$62(MainActivity.this, (JobRecord) obj);
            }
        }, new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.startRetry$lambda$64(MainActivity.this, (JobRecord) obj);
            }
        });
        if (!started) {
            Toast.makeText(this, "Berkas cetak ulang tidak tersedia atau tugas lain masih berjalan", 0).show();
        }
    }

    static final Unit startRetry$lambda$62(final MainActivity this$0, JobRecord it) {
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.runOnUiThread(new Runnable() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda47
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startRetry$lambda$62$lambda$61(MainActivity.this);
            }
        });
        return Unit.INSTANCE;
    }

    static final void startRetry$lambda$62$lambda$61(MainActivity this$0) {
        if (this$0.currentScreen == Screen.HISTORY) {
            this$0.showHistory();
        }
    }

    static final Unit startRetry$lambda$64(final MainActivity this$0, final JobRecord result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this$0.runOnUiThread(new Runnable() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                MainActivity.startRetry$lambda$64$lambda$63(MainActivity.this, result);
            }
        });
        return Unit.INSTANCE;
    }

    static final void startRetry$lambda$64$lambda$63(MainActivity this$0, JobRecord $result) {
        String error;
        MainActivity mainActivity = this$0;
        if ($result.getStatus() == JobStatus.COMPLETED) {
            error = "Data cetak ulang diterima transport Bluetooth";
        } else {
            error = $result.getError();
            if (error == null) {
                error = "Cetak ulang gagal";
            }
        }
        Toast.makeText(mainActivity, error, 1).show();
        if (this$0.currentScreen == Screen.HISTORY) {
            this$0.showHistory();
        }
    }

    private final void confirmClearHistory() {
        showWorkbenchDialog("Hapus riwayat terlihat?", "Catatan dan PDF cetak ulang akan dihapus. Penanda pencegah duplikasi untuk tugas yang sudah selesai tetap disimpan.", "Batal", "Hapus", new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.confirmClearHistory$lambda$65(MainActivity.this);
            }
        });
    }

    static final Unit confirmClearHistory$lambda$65(MainActivity this$0) {
        JobRepository jobRepository = this$0.jobs;
        if (jobRepository == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jobs");
            jobRepository = null;
        }
        jobRepository.clearVisibleHistory();
        this$0.showHistory();
        return Unit.INSTANCE;
    }

    private final void showSettings() {
        TextView text;
        TextView text2;
        TextView text3;
        TextView text4;
        TextView textView = this.topTitle;
        AppPreferences appPreferences = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topTitle");
            textView = null;
        }
        textView.setText("Pengaturan output");
        TextView textView2 = this.topStatus;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("topStatus");
            textView2 = null;
        }
        textView2.setText("58 MM");
        AppPreferences appPreferences2 = this.preferences;
        if (appPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
            appPreferences2 = null;
        }
        PrinterConfig config = appPreferences2.loadConfig();
        Pair<ScrollView, LinearLayout> screenBody = screenBody();
        ScrollView scroll = screenBody.component1();
        LinearLayout body = screenBody.component2();
        body.addView(UiKit.INSTANCE.eyebrow(this, "Profil printer"));
        text = UiKit.INSTANCE.text(r12, "Kalibrasi hasil", (r14 & 4) != 0 ? 16.0f : 23.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        add(body, text, 5);
        text2 = UiKit.INSTANCE.text(r12, "Nilai tersimpan langsung dan dipakai oleh tugas cetak berikutnya.", (r14 & 4) != 0 ? 16.0f : 14.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        add(body, text2, 7);
        add(body, choicePanel("Kertas / kepala cetak", "58 mm memakai area aktif 384 dot pada mayoritas printer. Pilih 576 hanya bila spesifikasi hardware menyatakannya.", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("384 dot / 203 dpi â€” standar", Integer.valueOf(PrinterConfig.DEFAULT_DOT_WIDTH)), TuplesKt.to("576 dot / 300 dpi â€” hardware terverifikasi", Integer.valueOf(PrinterConfig.WIDE_DOT_WIDTH))}), Integer.valueOf(config.getDotWidth()), new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$67(MainActivity.this, ((Integer) obj).intValue());
            }
        }), 18);
        add(body, choicePanel("Konversi monokrom", "Floydâ€“Steinberg menjaga detail logo; ambang tegas lebih tajam untuk teks.", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Floydâ€“Steinberg", DitherMode.FLOYD_STEINBERG), TuplesKt.to("Ambang tegas", DitherMode.THRESHOLD)}), config.getDitherMode(), new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$69(MainActivity.this, (DitherMode) obj);
            }
        }), 10);
        add(body, densityPanel(config), 10);
        add(body, seekPanel("Feed setelah cetak", config.getFeedLines() + " baris", config.getFeedLines(), 12, new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$71(MainActivity.this, ((Integer) obj).intValue());
            }
        }), 10);
        add(body, choicePanel("Perintah potong", "Printer tanpa pemotong sebaiknya memakai mode tanpa potong.", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Tanpa potong", CutMode.NONE), TuplesKt.to("Potong penuh", CutMode.FULL), TuplesKt.to("Potong sebagian", CutMode.PARTIAL)}), config.getCutMode(), new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$73(MainActivity.this, (CutMode) obj);
            }
        }), 10);
        add(body, choicePanel("Code page teks diagnostik", "Dokumen utama dicetak sebagai bitmap; code page hanya untuk teks ESC/POS diagnostik.", CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("CP437 / 0", 0), TuplesKt.to("WPC1252 / 16", 16), TuplesKt.to("CP866 / 17", 17), TuplesKt.to("CP852 / 18", 18)}), Integer.valueOf(config.getCodePage()), new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$75(MainActivity.this, ((Integer) obj).intValue());
            }
        }), 10);
        add(body, seekPanel("Timeout koneksi", (config.getConnectTimeoutMs() / 1000) + " detik", (config.getConnectTimeoutMs() / 1000) - 3, 27, new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$77(MainActivity.this, ((Integer) obj).intValue());
            }
        }), 10);
        add(body, seekPanel("Timeout kirim per chunk", (config.getSendTimeoutMs() / 1000) + " detik", (config.getSendTimeoutMs() / 1000) - 3, 57, new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$79(MainActivity.this, ((Integer) obj).intValue());
            }
        }), 10);
        add(body, switchPanel("Pangkas ruang putih struk", "Memotong baris putih berlebih di atas/bawah dan mempertahankan margin aman.", config.getTrimWhitespace(), new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$81(MainActivity.this, ((Boolean) obj).booleanValue());
            }
        }), 10);
        add(body, switchPanel("Mode Odoo: perbesar isi (uji coba)", "Opsional untuk nota Odoo/POS yang sempit di tengah halaman. RapPrint memangkas margin putih dan memperbesar isi maksimal 25%. Mulai dari mati; pratinjau Android tidak berubah.", config.getAutoFitContent(), new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$83(MainActivity.this, ((Boolean) obj).booleanValue());
            }
        }), 10);
        add(body, UiKit.INSTANCE.eyebrow(this, "Diagnostik"), 26);
        AppPreferences appPreferences3 = this.preferences;
        if (appPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        } else {
            appPreferences = appPreferences3;
        }
        String lastError = appPreferences.getLastError();
        String str = lastError;
        String str2 = lastError;
        add(body, messagePanel(str == null || StringsKt.isBlank(str) ? "Tidak ada galat terakhir" : "Galat terakhir", lastError == null ? "RapPrint belum merekam kegagalan pengiriman." : lastError, !(str2 == null || StringsKt.isBlank(str2))), 10);
        LinearLayout tips = UiKit.INSTANCE.panel(this, 16);
        tips.addView(UiKit.INSTANCE.text(this, "URUTAN PEMERIKSAAN", 11.0f, getColor(R.color.cobalt), true, true));
        text3 = UiKit.INSTANCE.text(r17, "1  Pastikan printer menyala dan tidak terhubung ke ponsel lain.\n2  Pasangkan ulang dari pengaturan Bluetooth bila alamat berubah.\n3  Pilih 384/576 dot sesuai kepala cetak.\n4  Jika batas waktu terlewati setelah data terkirim, periksa kertas sebelum mencetak ulang.", (r14 & 4) != 0 ? 16.0f : 14.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.ink), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 11);
        Unit unit = Unit.INSTANCE;
        tips.addView(text3, matchWrap);
        add(body, tips, 10);
        text4 = UiKit.INSTANCE.text(r17, "Privasi: riwayat tidak menyimpan isi nota. PDF hanya disimpan sementara untuk cetak ulang, dibatasi 20 MB/60 tugas, dan dapat dihapus dari Riwayat.", (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        add(body, text4, 12);
        setContent(scroll);
    }

    static final Unit showSettings$lambda$67(MainActivity this$0, final int value) {
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda45
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$67$lambda$66(value, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$67$lambda$66(int $value, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, $value, null, 0, 0, null, 0, 0, 0, false, false, 1022, null);
    }

    static final Unit showSettings$lambda$69(MainActivity this$0, final DitherMode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$69$lambda$68(DitherMode.this, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$69$lambda$68(DitherMode $value, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, $value, 0, 0, null, 0, 0, 0, false, false, 1021, null);
    }

    static final Unit showSettings$lambda$71(MainActivity this$0, final int value) {
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$71$lambda$70(value, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$71$lambda$70(int $value, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, null, 0, $value, null, 0, 0, 0, false, false, 1015, null);
    }

    static final Unit showSettings$lambda$73(MainActivity this$0, final CutMode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$73$lambda$72(CutMode.this, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$73$lambda$72(CutMode $value, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, null, 0, 0, $value, 0, 0, 0, false, false, 1007, null);
    }

    static final Unit showSettings$lambda$75(MainActivity this$0, final int value) {
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$75$lambda$74(value, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$75$lambda$74(int $value, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, null, 0, 0, null, $value, 0, 0, false, false, 991, null);
    }

    static final Unit showSettings$lambda$77(MainActivity this$0, final int value) {
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$77$lambda$76(value, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$77$lambda$76(int $value, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, null, 0, 0, null, 0, ($value + 3) * 1000, 0, false, false, 959, null);
    }

    static final Unit showSettings$lambda$79(MainActivity this$0, final int value) {
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$79$lambda$78(value, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$79$lambda$78(int $value, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, null, 0, 0, null, 0, 0, ($value + 3) * 1000, false, false, 895, null);
    }

    static final Unit showSettings$lambda$81(MainActivity this$0, final boolean enabled) {
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$81$lambda$80(enabled, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$81$lambda$80(boolean $enabled, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, null, 0, 0, null, 0, 0, 0, $enabled, false, 767, null);
    }

    static final Unit showSettings$lambda$83(MainActivity this$0, final boolean enabled) {
        this$0.updateConfig(new Function1() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainActivity.showSettings$lambda$83$lambda$82(enabled, (PrinterConfig) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final PrinterConfig showSettings$lambda$83$lambda$82(boolean $enabled, PrinterConfig it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrinterConfig.copy$default(it, 0, null, 0, 0, null, 0, 0, 0, false, $enabled, 511, null);
    }

    private final View densityPanel(PrinterConfig config) {
        TextView label;
        TextView text;
        LinearLayout panel = UiKit.INSTANCE.panel(this, 15);
        label = UiKit.INSTANCE.text(r3, "Kepadatan ambang", (r14 & 4) != 0 ? 16.0f : 16.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        String upperCase = config.getDensityLabel().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        final TextView value = UiKit.INSTANCE.pill(this, upperCase + " / " + config.getThreshold(), getColor(R.color.cobalt), getColor(R.color.soft_blue));
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        row.setGravity(16);
        row.addView(label, new LinearLayout.LayoutParams(0, -2, 1.0f));
        row.addView(value);
        panel.addView(row);
        final WorkbenchSlider workbenchSlider = new WorkbenchSlider(this, null, 2, null);
        workbenchSlider.setMaximum(160);
        workbenchSlider.setCurrentValue(config.getThreshold() - 64);
        workbenchSlider.setContentDescription("Kepadatan ambang, " + config.getThreshold());
        workbenchSlider.setOnValueChanged(new Function2() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.densityPanel$lambda$87$lambda$86(MainActivity.this, value, workbenchSlider, ((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
            }
        });
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 8);
        Unit unit = Unit.INSTANCE;
        panel.addView(workbenchSlider, matchWrap);
        text = UiKit.INSTANCE.text(r8, "Nilai lebih tinggi menghasilkan lebih banyak titik hitam.", (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        panel.addView(text);
        return panel;
    }

    static final Unit densityPanel$lambda$87$lambda$86(MainActivity this$0, TextView $value, WorkbenchSlider $this_apply, int progress, boolean fromUser) {
        if (fromUser) {
            int threshold = progress + 64;
            AppPreferences appPreferences = this$0.preferences;
            AppPreferences appPreferences2 = null;
            if (appPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferences");
                appPreferences = null;
            }
            PrinterConfig updated = PrinterConfig.copy$default(appPreferences.loadConfig(), 0, null, threshold, 0, null, 0, 0, 0, false, false, 1019, null).normalized();
            AppPreferences appPreferences3 = this$0.preferences;
            if (appPreferences3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferences");
            } else {
                appPreferences2 = appPreferences3;
            }
            appPreferences2.saveConfig(updated);
            String upperCase = updated.getDensityLabel().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            $value.setText(upperCase + " / " + updated.getThreshold());
            $this_apply.setContentDescription("Kepadatan ambang, " + updated.getThreshold());
        }
        return Unit.INSTANCE;
    }

    private final <T> View choicePanel(String title, String caption, List<? extends Pair<String, ? extends T>> choices, T selected, final Function1<? super T, Unit> onSelected) {
        TextView text;
        TextView text2;
        TextView text3;
        LinearLayout.LayoutParams layoutParams;
        int dp;
        final MainActivity mainActivity = this;
        T t = selected;
        LinearLayout panel = UiKit.INSTANCE.panel(mainActivity, 15);
        text = UiKit.INSTANCE.text(r9, title, (r14 & 4) != 0 ? 16.0f : 16.0f, (r14 & 8) != 0 ? mainActivity.getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        panel.addView(text);
        text2 = UiKit.INSTANCE.text(r9, caption, (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? mainActivity.getColor(R.color.ink) : mainActivity.getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = mainActivity.matchWrap();
        matchWrap.topMargin = UiKitKt.dp(mainActivity, 5);
        Unit unit = Unit.INSTANCE;
        panel.addView(text2, matchWrap);
        LinearLayout linearLayout = new LinearLayout(mainActivity);
        linearLayout.setOrientation(1);
        final List rows = new ArrayList();
        final Ref.ObjectRef activeValue = new Ref.ObjectRef();
        activeValue.element = t;
        List<? extends Pair<String, ? extends T>> list = choices;
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Pair pair = (Pair) obj;
            int i3 = i;
            String str = (String) pair.component1();
            final Object component2 = pair.component2();
            LinearLayout linearLayout2 = new LinearLayout(mainActivity);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(16);
            linearLayout2.setMinimumHeight(UiKitKt.dp(mainActivity, 50));
            Ref.ObjectRef activeValue2 = activeValue;
            List rows2 = rows;
            Iterable iterable = list;
            linearLayout2.setPadding(UiKitKt.dp(mainActivity, 12), UiKitKt.dp(mainActivity, 7), UiKitKt.dp(mainActivity, 9), UiKitKt.dp(mainActivity, 7));
            linearLayout2.setClickable(true);
            linearLayout2.setFocusable(true);
            text3 = UiKit.INSTANCE.text(r26, str, (r14 & 4) != 0 ? 16.0f : 14.0f, (r14 & 8) != 0 ? mainActivity.getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : Intrinsics.areEqual(component2, t), (r14 & 32) != 0 ? false : false);
            TextView pill = UiKit.INSTANCE.pill(mainActivity, "PILIH", mainActivity.getColor(R.color.muted), mainActivity.getColor(R.color.paper));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams2.setMarginEnd(UiKitKt.dp(this, 8));
            Unit unit2 = Unit.INSTANCE;
            linearLayout2.addView(text3, layoutParams2);
            linearLayout2.addView(pill);
            rows2.add(new ChoiceVisual(component2, linearLayout2, text3, pill));
            mainActivity = this;
            activeValue = activeValue2;
            rows = rows2;
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda46
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MainActivity.choicePanel$lambda$96$lambda$94(Ref.ObjectRef.this, component2, onSelected, rows, mainActivity, view);
                }
            });
            LinearLayout linearLayout3 = linearLayout2;
            LinearLayout.LayoutParams matchWrap2 = mainActivity.matchWrap();
            if (i3 == 0) {
                layoutParams = matchWrap2;
                dp = 0;
            } else {
                layoutParams = matchWrap2;
                dp = UiKitKt.dp(mainActivity, 6);
            }
            matchWrap2.topMargin = dp;
            Unit unit3 = Unit.INSTANCE;
            linearLayout.addView(linearLayout3, layoutParams);
            t = selected;
            i = i2;
            list = iterable;
        }
        choicePanel$refreshRows(rows, activeValue, mainActivity);
        LinearLayout.LayoutParams matchWrap3 = mainActivity.matchWrap();
        matchWrap3.topMargin = UiKitKt.dp(mainActivity, 11);
        Unit unit4 = Unit.INSTANCE;
        panel.addView(linearLayout, matchWrap3);
        return panel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void choicePanel$refreshRows(List<ChoiceVisual<T>> list, Ref.ObjectRef<T> objectRef, MainActivity mainActivity) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ChoiceVisual choiceVisual = (ChoiceVisual) it.next();
            boolean areEqual = Intrinsics.areEqual(choiceVisual.getValue(), objectRef.element);
            choiceVisual.getContainer().setBackground(mainActivity.selectionSurface(areEqual));
            choiceVisual.getLabel().setTextColor(mainActivity.getColor(areEqual ? R.color.cobalt_dark : R.color.ink));
            choiceVisual.getLabel().setTypeface(Typeface.create("sans-serif-medium", areEqual ? 1 : 0));
            choiceVisual.getMarker().setText(areEqual ? "AKTIF" : "PILIH");
            choiceVisual.getMarker().setTextColor(mainActivity.getColor(areEqual ? R.color.cobalt : R.color.muted));
            choiceVisual.getMarker().setBackground(mainActivity.markerSurface(areEqual));
            choiceVisual.getContainer().setSelected(areEqual);
            choiceVisual.getContainer().setContentDescription(((Object) choiceVisual.getLabel().getText()) + ", " + (areEqual ? "dipilih" : "tidak dipilih"));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final void choicePanel$lambda$96$lambda$94(Ref.ObjectRef $activeValue, Object obj, Function1 $onSelected, List $rows, MainActivity this$0, View it) {
        if (!Intrinsics.areEqual($activeValue.element, obj)) {
            $activeValue.element = obj;
            $onSelected.invoke(obj);
            choicePanel$refreshRows($rows, $activeValue, this$0);
        }
    }

    private final View seekPanel(final String title, String initialLabel, int progress, int max, final Function1<? super Integer, Unit> onChanged) {
        TextView text;
        LinearLayout panel = UiKit.INSTANCE.panel(this, 15);
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(0);
        row.setGravity(16);
        text = UiKit.INSTANCE.text(r10, title, (r14 & 4) != 0 ? 16.0f : 16.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        row.addView(text, new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView label = UiKit.INSTANCE.text(this, initialLabel, 12.0f, getColor(R.color.cobalt), true, true);
        row.addView(label);
        panel.addView(row);
        final WorkbenchSlider workbenchSlider = new WorkbenchSlider(this, null, 2, null);
        workbenchSlider.setMaximum(max);
        workbenchSlider.setCurrentValue(RangesKt.coerceIn(progress, 0, max));
        workbenchSlider.setContentDescription(title + ", " + initialLabel);
        workbenchSlider.setOnValueChanged(new Function2() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.seekPanel$lambda$100$lambda$99(Function1.this, label, title, workbenchSlider, ((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
            }
        });
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 8);
        Unit unit = Unit.INSTANCE;
        panel.addView(workbenchSlider, matchWrap);
        return panel;
    }

    static final Unit seekPanel$lambda$100$lambda$99(Function1 $onChanged, TextView $label, String $title, WorkbenchSlider $this_apply, int value, boolean fromUser) {
        String str;
        if (fromUser) {
            $onChanged.invoke(Integer.valueOf(value));
            if (StringsKt.startsWith$default($title, "Feed", false, 2, (Object) null)) {
                str = value + " baris";
            } else {
                str = (value + 3) + " detik";
            }
            $label.setText(str);
            $this_apply.setContentDescription($title + ", " + ((Object) $label.getText()));
        }
        return Unit.INSTANCE;
    }

    private final View switchPanel(final String title, final String caption, boolean checked, final Function1<? super Boolean, Unit> onChanged) {
        TextView text;
        TextView text2;
        final LinearLayout panel = UiKit.INSTANCE.panel(this, 15);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        text = UiKit.INSTANCE.text(r11, title, (r14 & 4) != 0 ? 16.0f : 16.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        linearLayout2.addView(text);
        text2 = UiKit.INSTANCE.text(r11, caption, (r14 & 4) != 0 ? 16.0f : 12.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 5);
        Unit unit = Unit.INSTANCE;
        linearLayout2.addView(text2, matchWrap);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(0, -2, 1.0f));
        final TextView marker = UiKit.INSTANCE.pill(this, "", getColor(R.color.muted), getColor(R.color.paper));
        linearLayout.addView(marker);
        panel.addView(linearLayout);
        final Ref.BooleanRef active = new Ref.BooleanRef();
        active.element = checked;
        panel.setMinimumHeight(UiKitKt.dp(this, 58));
        panel.setClickable(true);
        panel.setFocusable(true);
        panel.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.clvprinter.smartprint.setup.MainActivity$switchPanel$2
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                Intrinsics.checkNotNullParameter(host, "host");
                Intrinsics.checkNotNullParameter(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Switch.class.getName());
                info.setCheckable(true);
                info.setChecked(Ref.BooleanRef.this.element);
            }
        });
        panel.setOnClickListener(new View.OnClickListener() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda41
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.switchPanel$lambda$105(Ref.BooleanRef.this, onChanged, panel, marker, this, title, caption, view);
            }
        });
        switchPanel$refresh(marker, active, this, panel, title, caption);
        return panel;
    }

    private static final void switchPanel$refresh(TextView marker, Ref.BooleanRef active, MainActivity this$0, LinearLayout panel, String $title, String $caption) {
        marker.setText(active.element ? "AKTIF" : "MATI");
        marker.setTextColor(this$0.getColor(active.element ? R.color.success : R.color.muted));
        marker.setBackground(this$0.toggleSurface(active.element));
        panel.setBackground(this$0.selectionSurface(active.element));
        panel.setSelected(active.element);
        panel.setContentDescription($title + ", " + (active.element ? "aktif" : "mati") + ". " + $caption);
    }

    static final void switchPanel$lambda$105(Ref.BooleanRef $active, Function1 $onChanged, LinearLayout $panel, TextView $marker, MainActivity this$0, String $title, String $caption, View it) {
        $active.element = !$active.element;
        $onChanged.invoke(Boolean.valueOf($active.element));
        switchPanel$refresh($marker, $active, this$0, $panel, $title, $caption);
        $panel.sendAccessibilityEvent(1);
    }

    private final void updateConfig(Function1<? super PrinterConfig, PrinterConfig> transform) {
        AppPreferences appPreferences = this.preferences;
        AppPreferences appPreferences2 = null;
        if (appPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
            appPreferences = null;
        }
        AppPreferences appPreferences3 = this.preferences;
        if (appPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        } else {
            appPreferences2 = appPreferences3;
        }
        appPreferences.saveConfig(transform.invoke(appPreferences2.loadConfig()).normalized());
    }

    private final TextView statusPill(JobStatus status) {
        String label;
        int background;
        switch (WhenMappings.$EnumSwitchMapping$1[status.ordinal()]) {
            case 1:
                label = "ANTRI";
                break;
            case 2:
                label = "MENCETAK";
                break;
            case 3:
                label = "TERKIRIM";
                break;
            case 4:
                label = "GAGAL";
                break;
            case 5:
                label = "DIBATALKAN";
                break;
            case 6:
                label = "TIDAK PASTI";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        switch (WhenMappings.$EnumSwitchMapping$1[status.ordinal()]) {
            case 3:
                background = R.color.soft_green;
                break;
            case 4:
                background = R.color.soft_red;
                break;
            case 5:
            case 6:
                background = R.color.soft_amber;
                break;
            default:
                background = R.color.soft_blue;
                break;
        }
        return UiKit.INSTANCE.pill(this, label, statusColor(status), getColor(background));
    }

    private final int statusColor(JobStatus status) {
        int i;
        switch (WhenMappings.$EnumSwitchMapping$1[status.ordinal()]) {
            case 3:
                i = R.color.success;
                break;
            case 4:
                i = R.color.danger;
                break;
            case 5:
            case 6:
                i = R.color.warning;
                break;
            default:
                i = R.color.cobalt;
                break;
        }
        return getColor(i);
    }

    private final View messagePanel(String title, String copy, boolean danger) {
        TextView text;
        TextView text2;
        LinearLayout panel = UiKit.INSTANCE.panel(this, 15);
        int color = getColor(danger ? R.color.danger : R.color.cobalt);
        panel.setBackground(borderedPanel(color, getColor(danger ? R.color.soft_red : R.color.paper_white)));
        text = UiKit.INSTANCE.text(r6, title, (r14 & 4) != 0 ? 16.0f : 15.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : color, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        panel.addView(text);
        text2 = UiKit.INSTANCE.text(r11, copy, (r14 & 4) != 0 ? 16.0f : 13.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 6);
        Unit unit = Unit.INSTANCE;
        panel.addView(text2, matchWrap);
        return panel;
    }

    static /* synthetic */ View messagePanel$default(MainActivity mainActivity, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return mainActivity.messagePanel(str, str2, z);
    }

    private final GradientDrawable borderedPanel(int stroke, int fill) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(UiKitKt.dp(this, 6));
        gradientDrawable.setColor(fill);
        gradientDrawable.setStroke(UiKitKt.dp(this, 1), stroke);
        return gradientDrawable;
    }

    private final GradientDrawable selectionSurface(boolean active) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(UiKitKt.dp(this, 5));
        gradientDrawable.setColor(getColor(active ? R.color.soft_blue : R.color.paper_white));
        gradientDrawable.setStroke(UiKitKt.dp(this, active ? 2 : 1), getColor(active ? R.color.cobalt : R.color.rule));
        return gradientDrawable;
    }

    private final GradientDrawable markerSurface(boolean active) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(UiKitKt.dp(this, 4));
        gradientDrawable.setColor(getColor(active ? R.color.paper_white : R.color.paper));
        gradientDrawable.setStroke(UiKitKt.dp(this, 1), getColor(active ? R.color.cobalt : R.color.rule));
        return gradientDrawable;
    }

    private final GradientDrawable toggleSurface(boolean active) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(UiKitKt.dp(this, 4));
        gradientDrawable.setColor(getColor(active ? R.color.soft_green : R.color.paper));
        gradientDrawable.setStroke(UiKitKt.dp(this, 1), getColor(active ? R.color.success : R.color.rule));
        return gradientDrawable;
    }

    private final Pair<ScrollView, LinearLayout> screenBody() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setClipToPadding(false);
        scroll.setOverScrollMode(1);
        LinearLayout body = new LinearLayout(this);
        body.setOrientation(1);
        body.setPadding(UiKitKt.dp(this, 18), UiKitKt.dp(this, 18), UiKitKt.dp(this, 18), UiKitKt.dp(this, 28));
        scroll.addView(body, new ViewGroup.LayoutParams(-1, -2));
        return TuplesKt.to(scroll, body);
    }

    private final void setContent(View view) {
        FrameLayout frameLayout = this.contentContainer;
        FrameLayout frameLayout2 = null;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentContainer");
            frameLayout = null;
        }
        frameLayout.removeAllViews();
        FrameLayout frameLayout3 = this.contentContainer;
        if (frameLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentContainer");
        } else {
            frameLayout2 = frameLayout3;
        }
        frameLayout2.addView(view, new FrameLayout.LayoutParams(-1, -1));
    }

    private final void add(LinearLayout parent, View child, int topMarginDp) {
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, topMarginDp);
        Unit unit = Unit.INSTANCE;
        parent.addView(child, matchWrap);
    }

    private final LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private final void requestBluetoothPermission() {
        if (Build.VERSION.SDK_INT < 31) {
            showSetup();
            return;
        }
        String string = getString(R.string.bluetooth_permission_reason);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        showWorkbenchDialog("Akses printer terpasang", string, "Nanti", "Lanjut", new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.requestBluetoothPermission$lambda$115(MainActivity.this);
            }
        });
    }

    static final Unit requestBluetoothPermission$lambda$115(MainActivity this$0) {
        this$0.requestPermissions(new String[]{"android.permission.BLUETOOTH_CONNECT"}, REQUEST_BLUETOOTH);
        return Unit.INSTANCE;
    }

    private final void showWorkbenchDialog(String title, String message, String negativeLabel, String positiveLabel, final Function0<Unit> onPositive) {
        TextView text;
        TextView text2;
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        FrameLayout stage = new FrameLayout(this);
        stage.setPadding(UiKitKt.dp(this, 20), UiKitKt.dp(this, 20), UiKitKt.dp(this, 20), UiKitKt.dp(this, 20));
        LinearLayout card = UiKit.INSTANCE.panel(this, 20);
        card.setBackground(borderedPanel(getColor(R.color.ink), getColor(R.color.paper_white)));
        card.setElevation(UiKitKt.dp(this, 12));
        card.addView(UiKit.INSTANCE.eyebrow(this, "Konfirmasi / RapPrint"));
        text = UiKit.INSTANCE.text(r7, title, (r14 & 4) != 0 ? 16.0f : 21.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : 0, (r14 & 16) != 0 ? false : true, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap = matchWrap();
        matchWrap.topMargin = UiKitKt.dp(this, 8);
        Unit unit = Unit.INSTANCE;
        card.addView(text, matchWrap);
        text2 = UiKit.INSTANCE.text(r8, message, (r14 & 4) != 0 ? 16.0f : 14.0f, (r14 & 8) != 0 ? getColor(R.color.ink) : getColor(R.color.muted), (r14 & 16) != 0 ? false : false, (r14 & 32) != 0 ? false : false);
        LinearLayout.LayoutParams matchWrap2 = matchWrap();
        matchWrap2.topMargin = UiKitKt.dp(this, 9);
        Unit unit2 = Unit.INSTANCE;
        card.addView(text2, matchWrap2);
        LinearLayout actions = new LinearLayout(this);
        actions.setOrientation(0);
        actions.setGravity(8388613);
        Button button = UiKit.INSTANCE.button(this, negativeLabel, false, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showWorkbenchDialog$lambda$121(dialog);
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, UiKitKt.dp(this, 50), 1.0f);
        layoutParams.setMarginEnd(UiKitKt.dp(this, 5));
        Unit unit3 = Unit.INSTANCE;
        actions.addView(button, layoutParams);
        Button button2 = UiKit.INSTANCE.button(this, positiveLabel, true, new Function0() { // from class: com.clvprinter.smartprint.setup.MainActivity$$ExternalSyntheticLambda31
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MainActivity.showWorkbenchDialog$lambda$123(dialog, onPositive);
            }
        });
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, UiKitKt.dp(this, 50), 1.0f);
        layoutParams2.setMarginStart(UiKitKt.dp(this, 5));
        Unit unit4 = Unit.INSTANCE;
        actions.addView(button2, layoutParams2);
        LinearLayout.LayoutParams matchWrap3 = matchWrap();
        matchWrap3.topMargin = UiKitKt.dp(this, 18);
        Unit unit5 = Unit.INSTANCE;
        card.addView(actions, matchWrap3);
        stage.addView(card, new FrameLayout.LayoutParams(-1, -2, 17));
        dialog.setContentView(stage);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(2);
            window.setDimAmount(0.52f);
        }
        dialog.show();
        Window window2 = dialog.getWindow();
        if (window2 != null) {
            window2.setLayout(-1, -2);
        }
    }

    static final Unit showWorkbenchDialog$lambda$121(Dialog $dialog) {
        $dialog.dismiss();
        return Unit.INSTANCE;
    }

    static final Unit showWorkbenchDialog$lambda$123(Dialog $dialog, Function0 $onPositive) {
        $dialog.dismiss();
        $onPositive.invoke();
        return Unit.INSTANCE;
    }

    private final void openBluetoothSettings() {
        openSettings(new Intent("android.settings.BLUETOOTH_SETTINGS"), "Pengaturan Bluetooth tidak tersedia");
    }

    private final void openPrintSettings() {
        openSettings(new Intent("android.settings.ACTION_PRINT_SETTINGS"), "Pengaturan layanan cetak tidak tersedia");
    }

    private final void openSettings(Intent intent, String failure) {
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, failure, 1).show();
            startActivity(new Intent("android.settings.SETTINGS"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean isPrintServiceEnabled() {
        boolean z;
        Object m8constructorimpl;
        boolean z2;
        boolean z3 = false;
        try {
            Result.Companion companion = Result.INSTANCE;
            MainActivity mainActivity = this;
            ComponentName componentName = new ComponentName(mainActivity, (Class<?>) ReceiptPrintService.class);
            if (Build.VERSION.SDK_INT >= 33) {
                z2 = ((PrintManager) mainActivity.getSystemService(PrintManager.class)).isPrintServiceEnabled(componentName);
                z = false;
            } else {
                String string = Settings.Secure.getString(mainActivity.getContentResolver(), ENABLED_PRINT_SERVICES_SETTING);
                if (string == null) {
                    string = "";
                }
                String str = string;
                z2 = true;
                Iterable split$default = StringsKt.split$default((CharSequence) str, new char[]{':'}, false, 0, 6, (Object) null);
                Collection arrayList = new ArrayList();
                Iterator it = split$default.iterator();
                while (it.hasNext()) {
                    ComponentName unflattenFromString = ComponentName.unflattenFromString((String) it.next());
                    if (unflattenFromString != null) {
                        z = z3;
                        try {
                            arrayList.add(unflattenFromString);
                        } catch (Throwable th) {
                            th = th;
                            Result.Companion companion2 = Result.INSTANCE;
                            m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th));
                            Boolean valueOf = Boolean.valueOf(z);
                            if (Result.m14isFailureimpl(m8constructorimpl)) {
                            }
                            return ((Boolean) m8constructorimpl).booleanValue();
                        }
                    } else {
                        z = z3;
                    }
                    z3 = z;
                }
                z = z3;
                Iterable iterable = (List) arrayList;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it2 = iterable.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z2 = z;
                            break;
                        }
                        if (Intrinsics.areEqual((ComponentName) it2.next(), componentName)) {
                            break;
                        }
                    }
                } else {
                    z2 = z;
                }
            }
            m8constructorimpl = Result.m8constructorimpl(Boolean.valueOf(z2));
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
        Boolean valueOf2 = Boolean.valueOf(z);
        if (Result.m14isFailureimpl(m8constructorimpl)) {
            m8constructorimpl = valueOf2;
        }
        return ((Boolean) m8constructorimpl).booleanValue();
    }

    private final String nextActionTitle(boolean permissionReady, boolean printerReady, boolean serviceReady, boolean batteryReady) {
        return !permissionReady ? "Berikan izin perangkat" : !printerReady ? "Pasangkan dan pilih printer" : !serviceReady ? "Aktifkan RapPrint" : !batteryReady ? "Amankan layanan dari latar" : "Jalur cetak sudah siap";
    }

    private final String nextActionCopy(BondedPrinterState state, boolean printerReady, boolean serviceReady, boolean batteryReady) {
        return state instanceof BondedPrinterState.PermissionRequired ? "Izin hanya digunakan untuk membaca dan menghubungkan perangkat yang sudah dipasangkan." : state instanceof BondedPrinterState.HardwareUnavailable ? "Perangkat ini tidak menyediakan Bluetooth Classic. Antarmuka tetap dapat ditinjau, tetapi pengiriman fisik tidak tersedia." : state instanceof BondedPrinterState.BluetoothDisabled ? "Nyalakan Bluetooth, lalu pilih printer yang sudah dipasangkan." : !printerReady ? "Pilih satu perangkat terpasang sebagai target tes; pilihan di dialog cetak tetap menjadi tujuan tugas dokumen." : !serviceReady ? "Buka pengaturan pencetakan dan aktifkan RapPrint â€” Printer Struk." : !batteryReady ? "Kecualikan RapPrint dari penghemat baterai agar sistem tetap bisa membangunkan layanan cetak setelah restart tanpa membuka aplikasi." : "Jalankan tes diagnostik, lalu cetak situs dari Chrome dengan window.print().";
    }

    private final String formatDate(long timestamp) {
        String format = new SimpleDateFormat("dd MMM, HH:mm", Locale.forLanguageTag("id-ID")).format(new Date(timestamp));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* compiled from: MainActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007Â¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0013\u001a\u00028\u0000HÃ†\u0003Â¢\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0005HÃ†\u0003J\t\u0010\u0015\u001a\u00020\u0007HÃ†\u0003J\t\u0010\u0016\u001a\u00020\u0007HÃ†\u0003J<\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÃ†\u0001Â¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÃ–\u0003J\t\u0010\u001c\u001a\u00020\u001dHÃ–\u0001J\t\u0010\u001e\u001a\u00020\u001fHÃ–\u0001R\u0013\u0010\u0003\u001a\u00028\u0000Â¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005Â¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007Â¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007Â¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011Â¨\u0006 "}, d2 = {"Lcom/rapprinter/smartprint/setup/MainActivity$ChoiceVisual;", "T", "", "value", "container", "Landroid/widget/LinearLayout;", "label", "Landroid/widget/TextView;", "marker", "<init>", "(Ljava/lang/Object;Landroid/widget/LinearLayout;Landroid/widget/TextView;Landroid/widget/TextView;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getContainer", "()Landroid/widget/LinearLayout;", "getLabel", "()Landroid/widget/TextView;", "getMarker", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;Landroid/widget/LinearLayout;Landroid/widget/TextView;Landroid/widget/TextView;)Lcom/rapprinter/smartprint/setup/MainActivity$ChoiceVisual;", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class ChoiceVisual<T> {
        private final LinearLayout container;
        private final TextView label;
        private final TextView marker;
        private final T value;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ChoiceVisual copy$default(ChoiceVisual choiceVisual, Object obj, LinearLayout linearLayout, TextView textView, TextView textView2, int i, Object obj2) {
            if ((i & 1) != 0) {
                obj = choiceVisual.value;
            }
            if ((i & 2) != 0) {
                linearLayout = choiceVisual.container;
            }
            if ((i & 4) != 0) {
                textView = choiceVisual.label;
            }
            if ((i & 8) != 0) {
                textView2 = choiceVisual.marker;
            }
            return choiceVisual.copy(obj, linearLayout, textView, textView2);
        }

        public final T component1() {
            return this.value;
        }

        /* renamed from: component2, reason: from getter */
        public final LinearLayout getContainer() {
            return this.container;
        }

        /* renamed from: component3, reason: from getter */
        public final TextView getLabel() {
            return this.label;
        }

        /* renamed from: component4, reason: from getter */
        public final TextView getMarker() {
            return this.marker;
        }

        public final ChoiceVisual<T> copy(T value, LinearLayout container, TextView label, TextView marker) {
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(marker, "marker");
            return new ChoiceVisual<>(value, container, label, marker);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChoiceVisual)) {
                return false;
            }
            ChoiceVisual choiceVisual = (ChoiceVisual) other;
            return Intrinsics.areEqual(this.value, choiceVisual.value) && Intrinsics.areEqual(this.container, choiceVisual.container) && Intrinsics.areEqual(this.label, choiceVisual.label) && Intrinsics.areEqual(this.marker, choiceVisual.marker);
        }

        public int hashCode() {
            return ((((((this.value == null ? 0 : this.value.hashCode()) * 31) + this.container.hashCode()) * 31) + this.label.hashCode()) * 31) + this.marker.hashCode();
        }

        public String toString() {
            return "ChoiceVisual(value=" + this.value + ", container=" + this.container + ", label=" + this.label + ", marker=" + this.marker + ")";
        }

        public ChoiceVisual(T t, LinearLayout container, TextView label, TextView marker) {
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(marker, "marker");
            this.value = t;
            this.container = container;
            this.label = label;
            this.marker = marker;
        }

        public final T getValue() {
            return this.value;
        }

        public final LinearLayout getContainer() {
            return this.container;
        }

        public final TextView getLabel() {
            return this.label;
        }

        public final TextView getMarker() {
            return this.marker;
        }
    }
}

