package com.clvprinter.smartprint.setup;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiKit.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"dp", "", "Landroid/content/Context;", "value", "sp", "", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UiKitKt {
    public static final int dp(Context $this$dp, int value) {
        Intrinsics.checkNotNullParameter($this$dp, "<this>");
        return (int) (value * $this$dp.getResources().getDisplayMetrics().density);
    }

    public static final float sp(Context $this$sp, float value) {
        Intrinsics.checkNotNullParameter($this$sp, "<this>");
        return $this$sp.getResources().getDisplayMetrics().scaledDensity * value;
    }
}
