//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

public class Place {
    public double latitude;
    public double longitude;
    public double timezone;

    public Place(double latitude, double longitude, double timezone) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }

    public String toString() {
        return this.latitude + ", " + this.longitude + ", " + this.timezone;
    }
}
