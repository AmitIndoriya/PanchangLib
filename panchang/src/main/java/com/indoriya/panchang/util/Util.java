//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.util;


import com.indoriya.panchang.Masa;

public class Util {
    public static final String CONSTANTSFILEPATH = System.getProperty("user.dir");

    public Util() {
    }

    public static double fract(double x) {
        long i = (long)x;
        double y = x - (double)i;
        return y;
    }

    public static String makelength(String x, int y) {
        int diff = y - x.length();

        for(int i = 0; i < diff; ++i) {
            x = "0" + x;
        }

        return x;
    }

    public String dms(double x) {
        String[] parts = new String[3];
        int deg = (int)x;
        parts[0] = makelength(String.valueOf(deg), 2);
        double temp = x - (double)((int)x);
        int min = (int)(temp * 60.0D);
        parts[1] = makelength(String.valueOf(min), 2);
        temp *= 60.0D;
        temp -= (double)((int)temp);
        int sec = (int)(temp * 60.0D + 0.5D);
        parts[2] = makelength(String.valueOf(sec), 2);
        String sdms = parts[0] + this.getDashString(1) + parts[1] + this.getDashString(1) + parts[2];
        return sdms;
    }

    public static String getNorthSouthDir(double latitude) {
        return latitude < 0.0D ? "S" : "N";
    }

    public static String getEastWestDir(double longitude) {
        return longitude < 0.0D ? "W" : "E";
    }

    public String getDashString(int noOfDash) {
        String DASH = "";
        if (this.getLanguageCode().equalsIgnoreCase("0")) {
            DASH = ":";
        } else if (this.getLanguageCode().equalsIgnoreCase("1")) {
            DASH = "&";
        }

        String dash = "";

        for(int i = 0; i < noOfDash; ++i) {
            dash = dash + DASH;
        }

        return dash;
    }

    public String getLanguageCode() {
        return "0";
    }

    public static double ghati(double ghati) {
        return ghati / 60.0D * 24.0D;
    }

    public static int nextTithi(int tithiNum) {
        return tithiNum != 30 ? tithiNum + 1 : 1;
    }

    public static int nextNakshatra(int nakshatraNum) {
        return nakshatraNum != 27 ? nakshatraNum + 1 : 1;
    }

    public static void printInDms(double[] array) {
        Util util = new Util();

        for(int i = 0; i < array.length; ++i) {
            System.out.print(util.dms(array[i]) + " | ");
        }

        System.out.println();
    }

    public static void print(int[] array) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " | ");
        }

        System.out.println();
    }

    public static void print(double[] array) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " | ");
        }

        System.out.println();
    }

    public static void print(String[] array) {
        for(int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                System.out.println(array[i]);
            }
        }

        System.out.println();
    }

    public static void print(double jd) {
        int[] date = Masa.fromJulian(jd);
        if (jd != 0.0D) {
            System.out.println(date[2] + "-" + date[1] + "-" + date[0]);
        } else {
            System.out.println("000-00-00");
        }

    }

    public static void printDMS(double dmsDouble) {
        System.out.println((new Util()).dms(dmsDouble));
    }
}
