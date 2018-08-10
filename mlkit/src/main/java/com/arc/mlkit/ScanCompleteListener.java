package com.arc.mlkit;

import java.util.ArrayList;

public interface ScanCompleteListener {
    void onDetected(ArrayList<String> stringList, ArrayList<Integer> coordinateList);
}
