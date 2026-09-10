package com.clvprinter.smartprint.power;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.PowerManager;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryOptimizationHelper.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bÃ†\u0002\u0018\u00002\u00020\u0001B\t\b\u0002Â¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007R \u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u0006\u000f"}, d2 = {"Lcom/rapprinter/smartprint/power/BatteryOptimizationHelper;", "", "<init>", "()V", "isIgnoringBatteryOptimizations", "", "context", "Landroid/content/Context;", "requestIgnoreBatteryOptimizationsIntent", "Landroid/content/Intent;", "oemAutoStartIntent", "OEM_AUTOSTART_ACTIVITIES", "", "Lkotlin/Pair;", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class BatteryOptimizationHelper {
    public static final BatteryOptimizationHelper INSTANCE = new BatteryOptimizationHelper();
    private static final List<Pair<String, String>> OEM_AUTOSTART_ACTIVITIES = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"), TuplesKt.to("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"), TuplesKt.to("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity"), TuplesKt.to("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"), TuplesKt.to("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"), TuplesKt.to("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"), TuplesKt.to("com.asus.mobilemanager", "com.asus.mobilemanager.autostart.AutoStartActivity"), TuplesKt.to("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")});

    private BatteryOptimizationHelper() {
    }

    public final boolean isIgnoringBatteryOptimizations(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        if (powerManager == null) {
            return true;
        }
        return powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
    }

    public final Intent requestIgnoreBatteryOptimizationsIntent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        return intent;
    }

    public final Intent oemAutoStartIntent(Context context) {
        Intent intent;
        Object m8constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Iterator<T> it = OEM_AUTOSTART_ACTIVITIES.iterator();
        do {
            intent = null;
            if (!it.hasNext()) {
                break;
            }
            Pair pair = (Pair) it.next();
            Intent component = new Intent().setComponent(new ComponentName((String) pair.component1(), (String) pair.component2()));
            Intrinsics.checkNotNullExpressionValue(component, "setComponent(...)");
            BatteryOptimizationHelper batteryOptimizationHelper = INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                m8constructorimpl = Result.m8constructorimpl(packageManager.resolveActivity(component, 65536));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m14isFailureimpl(m8constructorimpl)) {
                m8constructorimpl = null;
            }
            if (((ResolveInfo) m8constructorimpl) != null) {
                intent = component;
            }
        } while (intent == null);
        return intent;
    }
}

