//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

public class Mod {
    int mod;

    public Mod(int mod) {
        this.mod = mod;
    }

    public double correct(double n) {
        return n < 0.0D ? n % (double)this.mod + (double)this.mod : n % (double)this.mod;
    }
}
