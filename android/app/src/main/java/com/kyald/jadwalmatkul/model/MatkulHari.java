package com.kyald.jadwalmatkul.model;

import java.util.ArrayList;
import java.util.List;

public class MatkulHari {

    public static final List<Hari> ITEMS = new ArrayList<Hari>();

    private static void addItem(Hari item) {
        ITEMS.add(item);
    }

    public static class Hari {
        public final String id;
        public final String hari;

        public Hari(String id, String hari) {
            this.id = id;
            this.hari = hari;
        }
    }
}