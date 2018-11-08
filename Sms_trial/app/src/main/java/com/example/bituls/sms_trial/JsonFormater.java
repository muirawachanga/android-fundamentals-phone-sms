package com.example.bituls.sms_trial;

import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonFormater {

    public List<Part> data;
    public String bettervalue;
    public List<String> numbers = new ArrayList<>();

    public List<Part> getDatas() {
        return data;
    }

    JsonFormater (){

    }
    public List<String> FormatGet(String str){
        Gson gson = new Gson();

        JsonFormater obj  = gson.fromJson(str, JsonFormater.class);

        List<Part> datas = obj.getDatas();

        for(Part parts : datas){
            bettervalue = parts.getSubject();
            if(bettervalue.contains("-"))
            {
            String[] splitted = bettervalue.split("-");
//            System.out.println(bettervalue);
            String no = splitted[1];
            if(no.length() == 10){
            numbers.add(no);}
            }

        }
        System.out.print(numbers);
        return numbers;

    }

//        public static void main(String[] args){
//            System.out.println("Hello World");
//            String empList ="{\"data\":[{\"subject\":\"Ram-0718215557\",\"empId\":1},{\"subject\":\"Surya-0719123456\",\"empId\":2},{\"subject\":\"Prasants\",\"empId\":3}]}";
//            JsonFormater format = new JsonFormater();
//            format.FormatGet(empList);
//            for (String no: format.FormatGet(empList)){
//                System.out.println(no);
//            }
//        }


}

//public class JsonFormater {
//    public String bettervalue;
//    public List<String> numbers = new ArrayList<>();
//    public ArrayList<InnerClassName> data = new ArrayList<InnerClassName>();
//    public static class InnerClassName {
//        String status;
//        String subject;
//        String name;
//    }
//    public void FormatGet(String str){
//        Gson gson = new Gson();
//        final JsonFormater className = gson.fromJson(str, JsonFormater.class);
//        int currentTotal = className.data.size();
//        for(InnerClassName e: className.data){
//            bettervalue = e.subject;
//            System.out.println(bettervalue);
//            String[] splitted = bettervalue.split("-");
////            String no = splitted[1];
//            System.out.println(splitted);
////            numbers.add(no);
//        }
////        System.out.println(numbers);
//   }
//    public static void main(String[] args){
//            System.out.println("Hello World");
//            String empList ="{\"data\":[{\"subject\":\"Ram-092920\",\"empId\":1},{\"subject\":\"Surya\",\"empId\":2},{\"subject\":\"Prasants\",\"empId\":3}]}";
//            JsonFormater format = new JsonFormater();
//            format.FormatGet(empList);
//        }

//}