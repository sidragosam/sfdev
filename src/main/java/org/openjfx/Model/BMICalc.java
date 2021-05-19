package org.openjfx.Model;

public class BMICalc {
    private double magassag;
    private double suly;

    public BMICalc(double suly, double magassag) {
        this.magassag = magassag;
        this.suly = suly;
    }

    public double szamitBMI(){
        double bmiS;
        bmiS = this.suly / ((this.magassag / 100) * (this.magassag / 100));
        return bmiS;
    }
}
