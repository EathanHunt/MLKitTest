package com.arc.mlkit.barcodescanning;

import android.graphics.Rect;

import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;

import java.util.ArrayList;

public class Barcode {
    private Rect boundingRect;
    private String displayValue;

    Barcode(FirebaseVisionBarcode barcode){
        boundingRect = barcode.getBoundingBox();
        displayValue = barcode.getDisplayValue();
    }

    private Barcode(){
        boundingRect = new Rect();
        displayValue = "";
    }

    public static Barcode build(ArrayList<String> stringList, ArrayList<Integer> coordinateList){
        Barcode barcode = new Barcode();
        barcode.setDisplayValue(stringList.get(0));
        if(coordinateList.size() >= 4){
            barcode.setBoundingRect(new Rect(coordinateList.get(0), coordinateList.get(1), coordinateList.get(2), coordinateList.get(3)));
        }
        return barcode;
    }

    ArrayList<String> getStringList(FirebaseVisionBarcode barcode){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add(barcode.getDisplayValue());
        return stringList;
    }

    ArrayList<Integer> getCoordinateList(FirebaseVisionBarcode barcode){
        ArrayList<Integer> coordinateList = new ArrayList<>();
        if(barcode.getBoundingBox() != null){
            coordinateList.add(barcode.getBoundingBox().left);
            coordinateList.add(barcode.getBoundingBox().top);
            coordinateList.add(barcode.getBoundingBox().right);
            coordinateList.add(barcode.getBoundingBox().bottom);
        }
        return coordinateList;
    }

    public Rect getBoundingRect() {
        return boundingRect;
    }

    public void setBoundingRect(Rect boundingRect) {
        this.boundingRect = boundingRect;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
