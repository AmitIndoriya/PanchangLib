//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang;

public class MasaPurnimanta extends HoroHelper {
    public MasaPurnimanta() {
    }

    public static void main(String[] args) {
        Place place = new Place(28.6139D, 77.209D, 5.5D);
        Masa objMasa = new Masa();
        int feb2 = (int)Masa.toJulian(2015, 2, 2);
        int feb3 = (int)Masa.toJulian(2015, 2, 3);
        int feb4 = (int)Masa.toJulian(2015, 2, 4);
        int mar4 = (int)Masa.toJulian(2015, 3, 4);
        int mar5 = (int)Masa.toJulian(2015, 3, 5);
        int mar6 = (int)Masa.toJulian(2015, 3, 6);
        int jun2 = (int)Masa.toJulian(2015, 6, 2);
        int jun3 = (int)Masa.toJulian(2015, 6, 3);
        int jun17 = (int)Masa.toJulian(2015, 6, 17);
        int jul2 = (int)Masa.toJulian(2015, 7, 2);
        int jul3 = (int)Masa.toJulian(2015, 7, 3);
        int[] mp00 = objMasa.masaPurnimanta((double)feb2, place);
        System.out.println("2015, feb, 2 Magha = " + mp00[0] + " " + mp00[1] + " " + mp00[2]);
        int[] mp0 = objMasa.masaPurnimanta((double)feb3, place);
        System.out.println("2015, feb, 3 Magha(fullmoon) = " + mp0[0] + " " + mp0[1] + " " + mp0[2]);
        int[] mp = objMasa.masaPurnimanta((double)feb4, place);
        System.out.println("2015, feb, 4 Start Phalguna = " + mp[0] + " " + mp[1] + " " + mp[2]);
        System.out.println("\n");
        int[] mp1 = objMasa.masaPurnimanta((double)mar4, place);
        System.out.println("2015, mar, 4 Phalguna = " + mp1[0] + " " + mp1[1] + " " + mp1[2]);
        int[] mp2 = objMasa.masaPurnimanta((double)mar5, place);
        System.out.println("2015, mar, 5 Phalguna (fullmoon)= " + mp2[0] + " " + mp2[1] + " " + mp2[2]);
        int[] mp3 = objMasa.masaPurnimanta((double)mar6, place);
        System.out.println("2015, mar, 6 start Chaitra = " + mp3[0] + " " + mp3[1] + " " + mp3[2]);
        System.out.println("\n");
        int[] m = objMasa.masaPurnimanta((double)jun2, place);
        System.out.println("2015, june, 2 Jyeshtha(full moon) = " + m[0] + " " + m[1] + " " + m[2]);
        int[] m1 = objMasa.masaPurnimanta((double)jun3, place);
        System.out.println("2015, june, 3 Start Ashadha = " + m1[0] + " " + m1[1] + " " + m1[2]);
        System.out.println("\n");
        System.out.println("2015, jun, 16 New Moon(Amavasha)");
        int[] m0 = objMasa.masaPurnimanta((double)jun17, place);
        System.out.println("2015, jun, 17 Start Ashadha(Adhik) = " + m0[0] + " " + m0[1] + " " + m0[2]);
        System.out.println("\n");
        int[] m2 = objMasa.masaPurnimanta((double)jul2, place);
        System.out.println("2015, july, 2 Ashadha(Adhik) = " + m2[0] + " " + m2[1] + " " + m2[2]);
        int[] m3 = objMasa.masaPurnimanta((double)jul3, place);
        System.out.println("2015, july, 3 Ashadha(Adhik) = " + m3[0] + " " + m3[1] + " " + m3[2]);
        System.out.println("\n");
    }
}
