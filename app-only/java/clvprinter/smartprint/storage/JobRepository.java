package com.clvprinter.smartprint.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.clvprinter.smartprint.storage.JobRecord;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: JobRepository.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003Â¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0011J\u0006\u0010\u001a\u001a\u00020\u0013J\u000e\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0011J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u001e\u0010\u001e\u001a\u00020\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0016\u0010\u001f\u001a\u00020\u00132\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0010\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0011H\u0002R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004Â¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004Â¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004Â¢\u0006\u0002\n\u0000Â¨\u0006#"}, d2 = {"Lcom/rapprinter/smartprint/storage/JobRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "appContext", "kotlin.jvm.PlatformType", "preferences", "Landroid/content/SharedPreferences;", "retryDirectory", "Ljava/io/File;", "records", "", "Lcom/rapprinter/smartprint/storage/JobRecord;", "find", "id", "", "upsert", "", "record", "durable", "", "isCompleted", "sourceJobId", "fingerprint", "clearVisibleHistory", "retryFile", "recordId", "load", "save", "enforceCacheLimit", "sanitize", "value", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class JobRepository {
    private static final long MAX_CACHE_BYTES = 20971520;
    private static final int MAX_RECORDS = 60;
    private static final String RETRY_DIRECTORY = "retry_cache";
    private final Context appContext;
    private final SharedPreferences preferences;
    private final File retryDirectory;
    private static final Object PROCESS_LOCK = new Object();

    public JobRepository(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        this.preferences = this.appContext.getSharedPreferences(AppPreferences.FILE_NAME, 0);
        File file = new File(this.appContext.getFilesDir(), RETRY_DIRECTORY);
        file.mkdirs();
        this.retryDirectory = file;
    }

    public final List<JobRecord> records() {
        List<JobRecord> sortedWith;
        synchronized (PROCESS_LOCK) {
            sortedWith = CollectionsKt.sortedWith(load(), new Comparator() { // from class: com.clvprinter.smartprint.storage.JobRepository$records$lambda$2$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((JobRecord) t2).getTimestamp()), Long.valueOf(((JobRecord) t).getTimestamp()));
                }
            });
        }
        return sortedWith;
    }

    public final JobRecord find(String id) {
        Object obj;
        JobRecord jobRecord;
        Intrinsics.checkNotNullParameter(id, "id");
        synchronized (PROCESS_LOCK) {
            Iterator it = load().iterator();
            while (true) {
                if (it.hasNext()) {
                    obj = it.next();
                    if (Intrinsics.areEqual(((JobRecord) obj).getId(), id)) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            jobRecord = (JobRecord) obj;
        }
        return jobRecord;
    }

    public static /* synthetic */ void upsert$default(JobRepository jobRepository, JobRecord jobRecord, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        jobRepository.upsert(jobRecord, z);
    }

    public final void upsert(JobRecord record, boolean durable) {
        List list;
        Intrinsics.checkNotNullParameter(record, "record");
        synchronized (PROCESS_LOCK) {
            int i = 0;
            try {
                List mutableList = CollectionsKt.toMutableList((Collection) load());
                int i2 = 0;
                Iterator it = mutableList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i2 = -1;
                        break;
                    } else if (Intrinsics.areEqual(((JobRecord) it.next()).getId(), record.getId())) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i2 >= 0) {
                    mutableList.set(i2, record);
                } else {
                    mutableList.add(record);
                }
                if (record.getStatus() == JobStatus.COMPLETED) {
                    Set<String> stringSet = this.preferences.getStringSet(AppPreferences.KEY_TOMBSTONES, SetsKt.emptySet());
                    if (stringSet == null) {
                        stringSet = SetsKt.emptySet();
                    }
                    Set mutableSet = CollectionsKt.toMutableSet(stringSet);
                    mutableSet.add(JobHistoryRules.INSTANCE.tombstoneKey(record.getSourceJobId(), record.getFingerprint()));
                    if (!this.preferences.edit().putStringSet(AppPreferences.KEY_TOMBSTONES, mutableSet).commit()) {
                        throw new IllegalStateException("Penanda idempotensi tidak dapat disimpan".toString());
                    }
                }
                List take = CollectionsKt.take(CollectionsKt.sortedWith(mutableList, new Comparator() { // from class: com.clvprinter.smartprint.storage.JobRepository$upsert$lambda$12$$inlined$sortedByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Long.valueOf(((JobRecord) t2).getTimestamp()), Long.valueOf(((JobRecord) t).getTimestamp()));
                    }
                }), MAX_RECORDS);
                Collection arrayList = new ArrayList();
                for (Object obj : mutableList) {
                    JobRecord jobRecord = (JobRecord) obj;
                    int i3 = i;
                    Iterable iterable = take;
                    boolean z = false;
                    if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                        Iterator it2 = iterable.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            Iterable iterable2 = iterable;
                            Iterator it3 = it2;
                            if (Intrinsics.areEqual(((JobRecord) it2.next()).getId(), jobRecord.getId())) {
                                z = true;
                                break;
                            } else {
                                iterable = iterable2;
                                it2 = it3;
                            }
                        }
                    }
                    if (!z) {
                        arrayList.add(obj);
                    }
                    i = i3;
                }
                List list2 = (List) arrayList;
                Collection arrayList2 = new ArrayList();
                Iterator it4 = list2.iterator();
                while (it4.hasNext()) {
                    String cachedPdfPath = ((JobRecord) it4.next()).getCachedPdfPath();
                    if (cachedPdfPath != null) {
                        list = list2;
                        arrayList2.add(cachedPdfPath);
                    } else {
                        list = list2;
                    }
                    list2 = list;
                }
                Iterator it5 = ((List) arrayList2).iterator();
                while (it5.hasNext()) {
                    new File((String) it5.next()).delete();
                }
                try {
                    save(take, durable);
                    enforceCacheLimit(take);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final boolean isCompleted(String sourceJobId, String fingerprint) {
        boolean contains;
        Intrinsics.checkNotNullParameter(sourceJobId, "sourceJobId");
        Intrinsics.checkNotNullParameter(fingerprint, "fingerprint");
        synchronized (PROCESS_LOCK) {
            String str = JobHistoryRules.INSTANCE.tombstoneKey(sourceJobId, fingerprint);
            Set<String> stringSet = this.preferences.getStringSet(AppPreferences.KEY_TOMBSTONES, SetsKt.emptySet());
            if (stringSet == null) {
                stringSet = SetsKt.emptySet();
            }
            contains = stringSet.contains(str);
        }
        return contains;
    }

    public final void clearVisibleHistory() {
        synchronized (PROCESS_LOCK) {
            try {
                Iterable load = load();
                Collection arrayList = new ArrayList();
                Iterator it = load.iterator();
                while (it.hasNext()) {
                    String cachedPdfPath = ((JobRecord) it.next()).getCachedPdfPath();
                    if (cachedPdfPath != null) {
                        arrayList.add(cachedPdfPath);
                    }
                }
                Iterator it2 = ((List) arrayList).iterator();
                while (it2.hasNext()) {
                    new File((String) it2.next()).delete();
                }
                try {
                    if (!this.preferences.edit().putString(AppPreferences.KEY_HISTORY_JSON, "[]").commit()) {
                        throw new IllegalStateException("Riwayat cetak tidak dapat dihapus".toString());
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final File retryFile(String recordId) {
        Intrinsics.checkNotNullParameter(recordId, "recordId");
        return new File(this.retryDirectory, sanitize(recordId) + ".pdf");
    }

    private final List<JobRecord> load() {
        Object m8constructorimpl;
        Object m8constructorimpl2;
        String string = this.preferences.getString(AppPreferences.KEY_HISTORY_JSON, "[]");
        String raw = string != null ? string : "[]";
        try {
            Result.Companion companion = Result.INSTANCE;
            JobRepository jobRepository = this;
            JSONArray jSONArray = new JSONArray(raw);
            List createListBuilder = CollectionsKt.createListBuilder();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    JobRecord.Companion companion3 = JobRecord.INSTANCE;
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                    m8constructorimpl2 = Result.m8constructorimpl(companion3.fromJson(jSONObject));
                } catch (Throwable th) {
                    Result.Companion companion4 = Result.INSTANCE;
                    m8constructorimpl2 = Result.m8constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m14isFailureimpl(m8constructorimpl2)) {
                    m8constructorimpl2 = null;
                }
                JobRecord jobRecord = (JobRecord) m8constructorimpl2;
                if (jobRecord != null) {
                    createListBuilder.add(jobRecord);
                }
            }
            m8constructorimpl = Result.m8constructorimpl(CollectionsKt.build(createListBuilder));
        } catch (Throwable th2) {
            Result.Companion companion5 = Result.INSTANCE;
            m8constructorimpl = Result.m8constructorimpl(ResultKt.createFailure(th2));
        }
        List emptyList = CollectionsKt.emptyList();
        if (Result.m14isFailureimpl(m8constructorimpl)) {
            m8constructorimpl = emptyList;
        }
        return (List) m8constructorimpl;
    }

    private final void save(List<JobRecord> records, boolean durable) {
        JSONArray array = new JSONArray();
        Iterator it = records.iterator();
        while (it.hasNext()) {
            array.put(((JobRecord) it.next()).toJson());
        }
        SharedPreferences.Editor editor = this.preferences.edit().putString(AppPreferences.KEY_HISTORY_JSON, array.toString());
        if (durable) {
            if (!editor.commit()) {
                throw new IllegalStateException("Riwayat cetak tidak dapat disimpan".toString());
            }
        } else {
            editor.apply();
        }
    }

    private final void enforceCacheLimit(List<JobRecord> records) {
        Collection arrayList = new ArrayList();
        Iterator it = records.iterator();
        while (it.hasNext()) {
            String cachedPdfPath = ((JobRecord) it.next()).getCachedPdfPath();
            if (cachedPdfPath != null) {
                arrayList.add(cachedPdfPath);
            }
        }
        Iterable iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it2 = iterable.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new File((String) it2.next()));
        }
        Collection arrayList3 = new ArrayList();
        for (Object obj : (List) arrayList2) {
            if (((File) obj).exists()) {
                arrayList3.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : (List) arrayList3) {
            if (hashSet.add(((File) obj2).getAbsolutePath())) {
                arrayList4.add(obj2);
            }
        }
        List files = CollectionsKt.sortedWith(arrayList4, new Comparator() { // from class: com.clvprinter.smartprint.storage.JobRepository$enforceCacheLimit$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((File) t2).lastModified()), Long.valueOf(((File) t).lastModified()));
            }
        });
        Iterator it3 = files.iterator();
        long total = 0;
        while (it3.hasNext()) {
            total += ((File) it3.next()).length();
        }
        for (File file : CollectionsKt.asReversed(files)) {
            long length = file.length();
            if (total > MAX_CACHE_BYTES && file.delete()) {
                total -= length;
            }
        }
    }

    private final String sanitize(String value) {
        return StringsKt.take(new Regex("[^A-Za-z0-9._-]").replace(value, "_"), 80);
    }
}

