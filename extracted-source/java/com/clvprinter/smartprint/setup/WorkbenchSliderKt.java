package com.clvprinter.smartprint.setup;

import android.content.Context;
import kotlin.Metadata;

/* compiled from: WorkbenchSlider.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0002¨\u0006\u0004"}, d2 = {"dp", "", "Landroid/content/Context;", "value", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WorkbenchSliderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final float dp(Context $this$dp, float value) {
        return $this$dp.getResources().getDisplayMetrics().density * value;
    }
}
