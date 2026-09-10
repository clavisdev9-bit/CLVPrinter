package com.android.tools.r8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* compiled from: D8$$SyntheticClass */
@SynthesizedClassV2(apiLevel = 26, kind = 5, versionHash = "3b119036505a817327d30bbe4a430e8676906c2ab0a3363856806bcd3b289007")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes9.dex */
public /* synthetic */ @interface LambdaMethod {
    String holder();

    String method();

    String proto();
}
