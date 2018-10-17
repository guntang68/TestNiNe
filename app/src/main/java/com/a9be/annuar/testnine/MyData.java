package com.a9be.annuar.testnine;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyData {

    private String m, n;
    private List<String> g;
    private Tab tabs;
    private List<Row> rows;

    public String getM() {
        return m;
    }

    public String getN() {
        return n;
    }

    public List<String> getG() {
        return g;
    }

    public Tab getTabs() {
        return tabs;
    }

    public List<Row> getRows() {
        return rows;
    }

    public class Tab {

        @SerializedName("0")
        private String t0;

        @SerializedName("1")
        private String t1;

        @SerializedName("2")
        private String t2;

        @SerializedName("3")
        private String t3;

        public String getT0() {
            return t0;
        }

        public String getT1() {
            return t1;
        }

        public String getT2() {
            return t2;
        }

        public String getT3() {
            return t3;
        }
    }

    public static class Row {
        private String tab, type;
        private List<Element> element;

        public String getTab() {
            return tab;
        }

        public String getType() {
            return type;
        }

        public List<Element> getElement() {
            return element;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setElement(List<Element> element) {
            this.element = element;
        }
    }

    public static class Element {
        private String l,a;

        public String getL() {
            return l;
        }

        public String getA() {
            return a;
        }

        public void setL(String l) {
            this.l = l;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

}
