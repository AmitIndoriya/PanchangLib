//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.suntimes;

public class Time {
    protected int hour;
    protected int minute;
    protected int second;

    public Time(int var1, int var2, int var3) {
        this.hour = var1;
        this.minute = var2;
        this.second = var3;
    }

    public Time(double var1) {
        while(var1 < 0.0D) {
            var1 += 24.0D;
        }

        while(var1 >= 24.0D) {
            var1 -= 24.0D;
        }

        this.hour = (int)var1;
        this.minute = (int)((var1 - (double)this.hour) * 60.0D);
        this.second = (int)(((var1 - (double)this.hour) * 60.0D - (double)this.minute) * 60.0D);
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getSecond() {
        return this.second;
    }

    public double getFractionalHours() {
        return (double)this.hour + (double)this.minute / 60.0D + (double)this.second / 3600.0D;
    }

    public String toString() {
        String var1 = "";
        if (this.hour < 10) {
            var1 = var1 + "0" + this.hour;
        } else {
            var1 = var1 + "" + this.hour;
        }

        var1 = var1 + ":";
        if (this.minute < 10) {
            var1 = var1 + "0" + this.minute;
        } else {
            var1 = var1 + "" + this.minute;
        }

        var1 = var1 + ":";
        if (this.second < 10) {
            var1 = var1 + "0" + this.second;
        } else {
            var1 = var1 + "" + this.second;
        }

        return var1;
    }
}
