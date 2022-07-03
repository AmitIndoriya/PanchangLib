//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.indoriya.panchang.festivals;

import com.indoriya.panchang.AscTable;
import com.indoriya.panchang.Masa;
import com.indoriya.panchang.Muhurta;
import com.indoriya.panchang.Place;
import com.indoriya.panchang.util.Util;

import java.util.ArrayList;

public class DiwaliMuhurta {
    public DiwaliMuhurta() {
    }

    public static double[] findDiwaliMuhurta(int diwaliDateJd, Place place) {
        double diwaliMuhStart = 0.0D;
        double diwaliMuhEnd = 0.0D;
        double vrishabhaStart = 0.0D;
        double vrishabhaEnd = 0.0D;
        double mahanishitaLakshmiStart = 0.0D;
        double mahanishitaLakshmiEnd = 0.0D;
        double mahanishitaStart = 0.0D;
        double mahanishitaEnd = 0.0D;
        double simhaStart = 0.0D;
        double simhaEnd = 0.0D;
        double sunset = Masa.getSunSet((double)diwaliDateJd, place);
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)diwaliDateJd, place);
        double[] nightMuhurta = Muhurta.getNightDivisons((double)diwaliDateJd, place, sunset, 15);
        double[] laganArray = AscTable.ascInADay((double)diwaliDateJd, place);
        double pradoshStart = nightMuhurta[0];
        double pradoshEnd = nightMuhurta[3];

        int i;
        for(i = 0; i < 24; i += 2) {
            if ((int)laganArray[i] == 2) {
                vrishabhaStart = i != 0 ? laganArray[i - 1] : laganArray[laganArray.length - 1];
                vrishabhaEnd = laganArray[i + 1];
                break;
            }
        }

        if ((int)tithi[0] == 30) {
            diwaliMuhStart = vrishabhaStart > nightMuhurta[3] ? nightMuhurta[0] : (tithi[1] > vrishabhaStart ? vrishabhaStart : nightMuhurta[0]);
            diwaliMuhEnd = tithi[1] > nightMuhurta[3] ? (nightMuhurta[3] > vrishabhaEnd ? vrishabhaEnd : nightMuhurta[3]) : tithi[1];
            if (tithi[1] > nightMuhurta[7]) {
                mahanishitaLakshmiStart = nightMuhurta[7];
                mahanishitaLakshmiEnd = tithi[1] > nightMuhurta[8] ? nightMuhurta[8] : tithi[1];
            }
        } else {
            diwaliMuhStart = tithi[1] > nightMuhurta[0] ? (tithi[1] > vrishabhaStart ? tithi[1] : vrishabhaStart) : (nightMuhurta[0] > vrishabhaStart ? nightMuhurta[0] : vrishabhaStart);
            diwaliMuhEnd = vrishabhaEnd > nightMuhurta[3] ? nightMuhurta[3] : vrishabhaEnd;
            mahanishitaLakshmiStart = nightMuhurta[7];
            mahanishitaLakshmiEnd = nightMuhurta[8];
        }

        if (diwaliMuhStart > diwaliMuhEnd) {
            diwaliMuhStart = 0.0D;
            diwaliMuhEnd = 0.0D;
        }

        mahanishitaStart = nightMuhurta[7];
        mahanishitaEnd = nightMuhurta[8];

        for(i = 0; i < 24; i += 2) {
            if ((int)laganArray[i] == 5) {
                simhaStart = i != 0 ? laganArray[i - 1] : laganArray[laganArray.length - 1];
                simhaEnd = laganArray[i + 1];
                break;
            }
        }

        return new double[]{diwaliMuhStart, diwaliMuhEnd, pradoshStart, pradoshEnd, vrishabhaStart, vrishabhaEnd, mahanishitaLakshmiStart, mahanishitaLakshmiEnd, mahanishitaStart, mahanishitaEnd, simhaStart, simhaEnd};
    }

    public static ArrayList<ArrayList<Double>> findDiwaliChoghadiyaMuhurta(int diwaliDateJd, Place place) {
        Masa objMasa = new Masa();
        double[] tithi = objMasa.tithi((double)diwaliDateJd, place);
        double[] dayMuhurta = Muhurta.getDayDivisons((double)diwaliDateJd, place, Masa.getSunRise((double)diwaliDateJd, place), 8);
        double[] nightMuhurta = Muhurta.getNightDivisons((double)diwaliDateJd, place, Masa.getSunSet((double)diwaliDateJd, place), 8);
        int[] dayChogadiya = Muhurta.getChogadiyaDayName((double)diwaliDateJd);
        int[] nightChogadiya = Muhurta.getChogadiyaNightName((double)diwaliDateJd);
        int[] chogadiya = new int[16];
        System.arraycopy(dayChogadiya, 0, chogadiya, 0, 8);
        System.arraycopy(nightChogadiya, 0, chogadiya, 8, 8);
        double[] chogadiyaTime = new double[17];
        System.arraycopy(dayMuhurta, 0, chogadiyaTime, 0, 9);
        System.arraycopy(nightMuhurta, 1, chogadiyaTime, 9, 8);
        ArrayList<ArrayList<Double>> chogValTimeList = new ArrayList();
        boolean aryChogListflag = false;
        boolean aryChogTimeStartflag = true;
        double startTime = 0.0D;
        double endTime = 0.0D;
        int buffer = 0;
        ArrayList chogAryList;
        ArrayList chogTimeAryList;
        int i;
        if ((int)tithi[0] == 30) {
            chogAryList = new ArrayList();
            chogTimeAryList = new ArrayList();

            for(i = 0; i < 16; ++i) {
                if (tithi[1] > chogadiyaTime[i]) {
                    if (aryChogListflag) {
                        chogAryList = new ArrayList();
                        chogTimeAryList = new ArrayList();
                    }

                    if (i < 8) {
                        if (chogadiya[i] != 1 && chogadiya[i] != 2 && chogadiya[i] != 3 && chogadiya[i] != 5) {
                            aryChogListflag = true;
                            aryChogTimeStartflag = true;
                        } else {
                            chogAryList.add((double)chogadiya[i]);
                            aryChogListflag = false;
                            if (aryChogTimeStartflag) {
                                startTime = chogadiyaTime[i];
                                aryChogTimeStartflag = false;
                            }

                            endTime = tithi[1] > chogadiyaTime[i + 1] ? chogadiyaTime[i + 1] : tithi[1];
                            ++buffer;
                        }
                    } else if (chogadiya[i] != 0 && chogadiya[i] != 1 && chogadiya[i] != 2 && chogadiya[i] != 5) {
                        aryChogListflag = true;
                        aryChogTimeStartflag = true;
                    } else {
                        chogAryList.add(7.0D + (double)chogadiya[i]);
                        aryChogListflag = false;
                        if (aryChogTimeStartflag) {
                            startTime = chogadiyaTime[i];
                            aryChogTimeStartflag = false;
                        }

                        endTime = tithi[1] > chogadiyaTime[i + 1] ? chogadiyaTime[i + 1] : tithi[1];
                        ++buffer;
                    }
                }

                if (chogAryList.size() > 0 && (aryChogListflag && buffer > 0 || i == 15 && buffer > 0)) {
                    chogTimeAryList.add(startTime);
                    chogTimeAryList.add(endTime);
                    chogValTimeList.add(chogAryList);
                    chogValTimeList.add(chogTimeAryList);
                    buffer = 0;
                }
            }
        } else {
            chogAryList = new ArrayList();
            chogTimeAryList = new ArrayList();

            for(i = 0; i < 16; ++i) {
                if (tithi[1] < chogadiyaTime[i] || tithi[1] > chogadiyaTime[i] && tithi[1] < chogadiyaTime[i + 1]) {
                    if (aryChogListflag) {
                        chogAryList = new ArrayList();
                        chogTimeAryList = new ArrayList();
                    }

                    if (i < 8) {
                        if (chogadiya[i] != 1 && chogadiya[i] != 2 && chogadiya[i] != 3 && chogadiya[i] != 5) {
                            aryChogListflag = true;
                            aryChogTimeStartflag = true;
                        } else {
                            chogAryList.add((double)chogadiya[i]);
                            aryChogListflag = false;
                            if (aryChogTimeStartflag) {
                                startTime = tithi[1] > chogadiyaTime[i] && tithi[1] < chogadiyaTime[i + 1] ? tithi[1] : chogadiyaTime[i];
                                aryChogTimeStartflag = false;
                            }

                            endTime = chogadiyaTime[i + 1];
                            ++buffer;
                        }
                    } else if (chogadiya[i] != 0 && chogadiya[i] != 1 && chogadiya[i] != 2 && chogadiya[i] != 5) {
                        aryChogListflag = true;
                        aryChogTimeStartflag = true;
                    } else {
                        chogAryList.add(7.0D + (double)chogadiya[i]);
                        aryChogListflag = false;
                        if (aryChogTimeStartflag) {
                            startTime = tithi[1] > chogadiyaTime[i] && tithi[1] < chogadiyaTime[i + 1] ? tithi[1] : chogadiyaTime[i];
                            aryChogTimeStartflag = false;
                        }

                        endTime = chogadiyaTime[i + 1];
                        ++buffer;
                    }
                }

                if (chogAryList.size() > 0 && (aryChogListflag && buffer > 0 || i == 15 && buffer > 0)) {
                    chogTimeAryList.add(startTime);
                    chogTimeAryList.add(endTime);
                    chogValTimeList.add(chogAryList);
                    chogValTimeList.add(chogTimeAryList);
                    buffer = 0;
                }
            }
        }

        return chogValTimeList;
    }

    public static void main(String[] args) {
        Place NewDelhi = new Place(28.6139D, 77.209D, 5.5D);
        int jd = (int)Masa.toJulian(2020, 11, 14);
        double[] muhurta = findDiwaliMuhurta(jd, NewDelhi);
        findDiwaliChoghadiyaMuhurta(jd, NewDelhi);
        Util objUtil = new Util();
        System.out.println("Diwali muharta Start: " + objUtil.dms(muhurta[0]));
        System.out.println("Diwali muharta End  : " + objUtil.dms(muhurta[1]));
        System.out.println("Pradosh Start  : " + objUtil.dms(muhurta[2]));
        System.out.println("Pradosh End  : " + objUtil.dms(muhurta[3]));
        System.out.println("Vrishabha Start: " + objUtil.dms(muhurta[4]));
        System.out.println("Vrishabha End  : " + objUtil.dms(muhurta[5]));
        System.out.println("Lakshmi Mahanishita Start  : " + objUtil.dms(muhurta[6]));
        System.out.println("Lakshmi Mahanishita End  : " + objUtil.dms(muhurta[7]));
        System.out.println("Mahanishita Kaal Start  : " + objUtil.dms(muhurta[8]));
        System.out.println("Mahanishita Kaal End  : " + objUtil.dms(muhurta[9]));
        System.out.println("Simha Kaal Start  : " + objUtil.dms(muhurta[10]));
        System.out.println("Simha Kaal End  : " + objUtil.dms(muhurta[11]));
    }
}
