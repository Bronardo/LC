package Runners;

import com.lcContest.AvoidFloodinTheCity;
import com.lcContest.MakingFileNamesUnique;
import com.lcContest.XOROperationinanArray;

public class WeeklyContest194 {
    public static void main(String args[]){
        //5440. XOR Operation in an Array
        //xOrOperationinanArray();
        //5441. Making File Names Unique
        //makingFileNamesUnique();
        //5442. Avoid Flood in The City
        avoidFloodinthecity();
    }
    public static void xOrOperationinanArray(){
        XOROperationinanArray s = new XOROperationinanArray();
        System.out.println(s.xorOperation(5,0));
        System.out.println(s.xorOperation(4,3));
        System.out.println(s.xorOperation(1,7));
        System.out.println(s.xorOperation(10,5));
    }
    public static void makingFileNamesUnique(){
        MakingFileNamesUnique s = new MakingFileNamesUnique();
        String[] in = {"gta","gta(1)","gta","avalon"};
        String[] in2 = {"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"};


        System.out.println(s.getFolderNames(in2));
    }
    public static void avoidFloodinthecity(){
        AvoidFloodinTheCity s = new AvoidFloodinTheCity();
//        int[] in = {1,2,3,4};
//        int[] in = {1,2,0,0,2,1};
//        int[] in = {1,2,0,1,2};
//        int[] in = {1,1,0,0};
        int[] in = {1,0,2,3,0,1,2};
        System.out.println(s.avoidFlood(in).toString());
    }
}
