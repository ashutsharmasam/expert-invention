package hr.algo.dp.equal;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int[] diffLookup = {
            0,
            1, // 1
            1, // 2
            2, // 2, 1
            2, // 2, 2
    };

    static HashMap<Integer, Integer> itemFreq;
    static ArrayList<Integer> keys;

    static int equal(int[] arr) {
        itemFreq = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(null == itemFreq.get(arr[i])){
                itemFreq.put(arr[i], 1);
            }
            else {
                itemFreq.put(arr[i], 1+itemFreq.get(arr[i]));
            }
        }
        keys = new ArrayList(itemFreq.keySet());
        Collections.sort(keys);
        int totalSteps = calculateSteps(0);
        for (int i = 1; i <= 2; i++) {
            int totalSteps_i = calculateSteps(i);
            if(totalSteps_i < totalSteps) totalSteps = totalSteps_i;
        }

        return totalSteps;
    }

    static int calculateSteps(int shift){
        int totalSteps = 0;
        if(shift != 0) totalSteps += itemFreq.get(keys.get(0));
        int base = keys.get(0) - shift;
        for (int i=1; i<keys.size(); i++){
            int key = keys.get(i);
            int diff = key-base;
            int step_5 = diff / 5;
            int rest = diff % 5;
            int step = step_5 + diffLookup[rest];
            totalSteps += itemFreq.get(key) * step;
        }
        return totalSteps;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            int result = equal(arr);
            System.out.println(result);
        }
        in.close();
    }
}