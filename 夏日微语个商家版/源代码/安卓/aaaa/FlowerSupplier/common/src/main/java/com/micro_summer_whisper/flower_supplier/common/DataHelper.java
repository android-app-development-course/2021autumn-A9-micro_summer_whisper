package com.micro_summer_whisper.flower_supplier.common;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class DataHelper {
    private static final DataHelper helper= new DataHelper();
    public static DataHelper getInstance() {return helper;}
    Map<String, WeakReference<Object>> data = new HashMap<String, WeakReference<Object>>();
    public void saveData(String id, Object object) {
        data.put(id, new WeakReference<Object>(object));
    }
    public <T> T getData(String id) {
        WeakReference<Object> objectWeakReference = data.get(id);
        return (T)objectWeakReference.get();
    }

    public <T> T removeData(String id){
        WeakReference<Object> objectWeakReference = data.remove(id);
        return (T)objectWeakReference.get();
    }
}
