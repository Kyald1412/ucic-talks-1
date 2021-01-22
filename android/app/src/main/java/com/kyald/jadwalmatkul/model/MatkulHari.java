package com.kyald.jadwalmatkul.model;

import java.util.ArrayList;
import java.util.List;

public class MatkulHari {

    public static final List<MatkulItem> ITEMS = new ArrayList<MatkulItem>();

    private static void addItem(MatkulItem item) {
        ITEMS.add(item);
    }

    public static class MatkulItem {
        public final String id;
        public final String matkul;
        public final String hari;
        public final String id_hari;

        public MatkulItem(String id, String matkul, String hari, String id_hari) {
            this.id = id;
            this.matkul = matkul;
            this.hari = hari;
            this.id_hari = id_hari;
        }
    }
}