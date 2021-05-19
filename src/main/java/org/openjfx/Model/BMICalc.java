package org.openjfx.Model;

public class BMICalc {
    private double magassag;
    private double suly;

    public double getMagassag() {
        return magassag;
    }

    public void setMagassag(double magassag) {
        this.magassag = magassag;
    }

    public double getSuly() {
        return suly;
    }

    public void setSuly(double suly) {
        this.suly = suly;
    }

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
