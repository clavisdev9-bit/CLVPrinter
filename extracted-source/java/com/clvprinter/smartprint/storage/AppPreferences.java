package com.clvprinter.smartprint.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.clvprinter.smartprint.printer.CutMode;
import com.clvprinter.smartprint.printer.DitherMode;
import com.clvprinter.smartprint.printer.PrinterConfig;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppPreferences.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0010\n\u0002\b\u0004\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dJ2\u0010\u001f\u001a\u0002H \"\u0010\b\u0000\u0010 \u0018\u0001*\b\u0012\u0004\u0012\u0002H 0!2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\"\u001a\u0002H H\u0082\b¢\u0006\u0002\u0010#R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR(\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000f¨\u0006%"}, d2 = {"Lcom/clvprinter/smartprint/storage/AppPreferences;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "value", "", "selectedPrinterAddress", "getSelectedPrinterAddress", "()Ljava/lang/String;", "setSelectedPrinterAddress", "(Ljava/lang/String;)V", "selectedPrinterName", "getSelectedPrinterName", "setSelectedPrinterName", "lastError", "getLastError", "setLastError", "loadConfig", "Lcom/clvprinter/smartprint/printer/PrinterConfig;", "saveConfig", "", "config", "registerListener", "listener", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "unregisterListener", "enumValueOrDefault", "T", "", "default", "(Ljava/lang/String;Ljava/lang/Enum;)Ljava/lang/Enum;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class AppPreferences {
    public static final String FILE_NAME = "clvprint_preferences";
    private static final String KEY_AUTO_FIT_CONTENT = "auto_fit_content";
    private static final String KEY_CODE_PAGE = "code_page";
    private static final String KEY_CONNECT_TIMEOUT = "connect_timeout";
    private static final String KEY_CUT_MODE = "cut_mode";
    private static final String KEY_DITHER_MODE = "dither_mode";
    private static final String KEY_DOT_WIDTH = "dot_width";
    private static final String KEY_FEED_LINES = "feed_lines";
    public static final String KEY_HISTORY_JSON = "job_history_json";
    private static final String KEY_LAST_ERROR = "last_error";
    private static final String KEY_PRINTER_ADDRESS = "printer_address";
    private static final String KEY_PRINTER_NAME = "printer_name";
    private static final String KEY_SEND_TIMEOUT = "send_timeout";
    private static final String KEY_THRESHOLD = "threshold";
    public static final String KEY_TOMBSTONES = "completed_tombstones";
    private static final String KEY_TRIM = "trim_whitespace";
    private final SharedPreferences preferences;

    public AppPreferences(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.preferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    public final String getSelectedPrinterAddress() {
        return this.preferences.getString(KEY_PRINTER_ADDRESS, null);
    }

    public final void setSelectedPrinterAddress(String value) {
        this.preferences.edit().putString(KEY_PRINTER_ADDRESS, value).apply();
    }

    public final String getSelectedPrinterName() {
        return this.preferences.getString(KEY_PRINTER_NAME, null);
    }

    public final void setSelectedPrinterName(String value) {
        this.preferences.edit().putString(KEY_PRINTER_NAME, value).apply();
    }

    public final String getLastError() {
        return this.preferences.getString(KEY_LAST_ERROR, null);
    }

    public final void setLastError(String value) {
        this.preferences.edit().putString(KEY_LAST_ERROR, value).apply();
    }

    public final PrinterConfig loadConfig() {
        Object m8constructorimpl;
        Object m8constructorimpl2;
        int i = this.preferences.getInt(KEY_DOT_WIDTH, PrinterConfig.DEFAULT_DOT_WIDTH);
        String string = this.preferences.getString(KEY_DITHER_MODE, null);
        Enum r1 = DitherMode.FLOYD_STEINBERG;
        if (string != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                AppPreferences appPreferences = this;
                m8constructorimpl2 = Result.m8constructorimpl(DitherMode.valueOf(string));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8constructorimpl2 = Result.m8constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m14isFailureimpl(m8constructorimpl2)) {
                m8constructorimpl2 = null;
            }
            Enum r0 = (Enum) m8constructorimpl2;
            if (r0 != null) {
                r1 = r0;
            }
        }
        DitherMode ditherMode = (DitherMode) r1;
        int i2 = this.preferences.getInt(KEY_THRESHOLD, 150);
        int i3 = this.preferences.getInt(KEY_FEED_LINES, 4);
        String string2 = this.preferences.getString(KEY_CUT_MODE, null);
        Enum r12 = CutMode.PARTIAL;
        if (string2 != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                AppPreferences appPreferences2 = this;
                m8constructorimpl = Result.m8constructorimpl(CutMode.valueOf(string2));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th2));
            }
            Enum r2 = (Enum) (Result.m14isFailureimpl(m8constructorimpl) ? null : m8constructorimpl);
            if (r2 != null) {
                r12 = r2;
            }
        }
        return new PrinterConfig(i, ditherMode, i2, i3, (CutMode) r12, this.preferences.getInt(KEY_CODE_PAGE, 0), this.preferences.getInt(KEY_CONNECT_TIMEOUT, 10000), this.preferences.getInt(KEY_SEND_TIMEOUT, 15000), this.preferences.getBoolean(KEY_TRIM, true), this.preferences.getBoolean(KEY_AUTO_FIT_CONTENT, false)).normalized();
    }

    public final void saveConfig(PrinterConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        PrinterConfig safe = config.normalized();
        this.preferences.edit().putInt(KEY_DOT_WIDTH, safe.getDotWidth()).putString(KEY_DITHER_MODE, safe.getDitherMode().name()).putInt(KEY_THRESHOLD, safe.getThreshold()).putInt(KEY_FEED_LINES, safe.getFeedLines()).putString(KEY_CUT_MODE, safe.getCutMode().name()).putInt(KEY_CODE_PAGE, safe.getCodePage()).putInt(KEY_CONNECT_TIMEOUT, safe.getConnectTimeoutMs()).putInt(KEY_SEND_TIMEOUT, safe.getSendTimeoutMs()).putBoolean(KEY_TRIM, safe.getTrimWhitespace()).putBoolean(KEY_AUTO_FIT_CONTENT, safe.getAutoFitContent()).apply();
    }

    public final void registerListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public final void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    private final /* synthetic */ <T extends Enum<T>> T enumValueOrDefault(String value, T r10) {
        Object m8constructorimpl;
        if (value != null) {
            String str = value;
            try {
                Result.Companion companion = Result.INSTANCE;
                AppPreferences appPreferences = this;
                Intrinsics.reifiedOperationMarker(5, "T");
                m8constructorimpl = Result.m8constructorimpl(Enum.valueOf(null, str));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th));
            }
            T t = (T) (Result.m14isFailureimpl(m8constructorimpl) ? null : m8constructorimpl);
            if (t != null) {
                return t;
            }
        }
        return r10;
    }
}
