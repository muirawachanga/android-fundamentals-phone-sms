package com.example.bituls.sms_trial;

import java.util.Arrays;
import java.util.List;

public class ThreadTrial {
    int times = 0;
    public void steve(final List<String> AllNumber) {

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (times = 0; times < AllNumber.size(); times++) {
                        Thread.sleep(10000);
                        try {
                            String number = AllNumber.get(times);
                            System.out.println(number);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });
        th.start();
    }

//    public static void main(String[] args){
//        int times;
//        List<String> AllNumber = Arrays.asList("0790713103","0792650939");
//        try {
//            for (times = 0; times < AllNumber.size(); times++) {
//                Thread.sleep(10000);
//                try {
//                    String number = AllNumber.get(times);
//                    System.out.println(number);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
}
