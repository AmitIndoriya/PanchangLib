//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.util;

public class Interpolate {
    public Interpolate() {
    }

    public static double inverse_lagrange(double[] x, double[] y, double ya) {
        double total = 0.0D;

        for(int i = 0; i < x.length; ++i) {
            double numer = 1.0D;
            double denom = 1.0D;

            for(int j = 0; j < x.length; ++j) {
                if (j != i) {
                    numer *= ya - y[j];
                    denom *= y[i] - y[j];
                }
            }

            total += numer * x[i] / denom;
        }

        return total;
    }

    public static double[] unwrap_angles(double[] angles) {
        double[] result = angles;

        for(int i = 1; i < angles.length; ++i) {
            if (result[i] < result[i - 1]) {
                result[i] += 360.0D;
            }
        }

        return result;
    }

    public static double[] unwrap_anglesNew(double[] angles) {
        double[] result = angles;

        for(int i = 1; i < angles.length; ++i) {
            if (result[i] < result[i - 1]) {
                result[i] += 360.0D;
                if (result[i] < result[i - 1]) {
                    result[i] += 360.0D;
                }
            }
        }

        return result;
    }
}
