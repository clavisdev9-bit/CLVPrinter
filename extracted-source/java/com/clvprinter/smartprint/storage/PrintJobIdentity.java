package com.clvprinter.smartprint.storage;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PrintJobIdentity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/clvprinter/smartprint/storage/PrintJobIdentity;", "", "<init>", "()V", "existingTagOrNull", "", "tag", "newTag", "uuid", "Ljava/util/UUID;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class PrintJobIdentity {
    public static final PrintJobIdentity INSTANCE = new PrintJobIdentity();

    private PrintJobIdentity() {
    }

    public final String existingTagOrNull(String tag) {
        if (tag == null || StringsKt.isBlank(tag)) {
            return null;
        }
        return tag;
    }

    public static /* synthetic */ String newTag$default(PrintJobIdentity printJobIdentity, UUID uuid, int i, Object obj) {
        if ((i & 1) != 0) {
            uuid = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID(...)");
        }
        return printJobIdentity.newTag(uuid);
    }

    public final String newTag(UUID uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return "clvprint-job:" + uuid;
    }
}
