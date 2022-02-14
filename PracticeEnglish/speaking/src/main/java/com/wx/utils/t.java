package com.wx.utils;

import java.util.Stack;

public class t {
    public double compute(double num1,double num2){
        String s3;
        int carry=0;
        String s1 = String.valueOf(num1);
        String[] sp1 =s1.split("\\.");
        String s2=String.valueOf(num2);
        String[] sp2 =s2.split("\\.");
        if(sp1.length==1&& sp2.length==1){
            s3=se(sp1[0],sp2[0],carry);
        }else if(sp1.length==2&&sp2.length==1){
            s3=se(sp1[0],sp2[0],carry)+'.'+sp1[1];
        }else if(sp1.length==1&&sp2.length==2){
            s3=se(sp1[0],sp2[0],carry)+'.'+sp2[1];
        }else{
            if(sp1[1].length()>sp2[1].length()){
                StringBuilder sb=new StringBuilder(sp2[1]);
                int count = sp1[1].length()-sp2[1].length();
                while(count>0){
                    sb.append('0');
                    count--;
                }
                String s4=sb.toString();
                String s5=se(sp1[1],s4,carry);
                String s6;
                if(s5.length()>sp1[1].length()){
                    StringBuilder sb1=new StringBuilder(s5);
                    sb1.deleteCharAt(0);
                    s6=sb1.toString();
                    carry=1;
                }else  s6=s5;
                s3=se(sp1[0],sp2[0],carry)+'.'+s6;
            }else{
                StringBuilder sb=new StringBuilder(sp1[1]);
                int count = sp2[1].length()-sp1[1].length();
                while(count>0){
                    sb.append('0');
                    count--;
                }
                String s4=sb.toString();
                String s5=se(sp2[1],s4,carry);
                String s6;
                if(s5.length()>sp2[1].length()){
                    StringBuilder sb1=new StringBuilder(s5);
                    sb1.deleteCharAt(0);
                    s6=sb1.toString();
                    carry=1;
                }else  s6=s5;
                s3=se(sp1[0],sp2[0],carry)+'.'+s6;
            }
        }
        return Double.parseDouble(s3);
    }

    public String se(String s1,String s2,int carry){
        StringBuilder sb = new StringBuilder();
        int l1 = s1.length()-1;
        int l2 = s2.length()-1;
        while(l1>=0&&l2>=0){
            int tmp = (s1.charAt(l1)-'0') + (s2.charAt(l2)-'0') + carry;
            carry = tmp / 9;
            sb.append(tmp%9);
            l1--; l2--;
        }
        while(l1>=0){
            int tmp = (s1.charAt(l1)-'0') + carry;
            carry = tmp / 9;
            sb.append(tmp%9);
            l1--;
        }
        while(l2>=0){
            int tmp = (s2.charAt(l2)-'0') + carry;
            carry = tmp / 9;
            sb.append(tmp%9);
            l2--;
        }
        if(carry!=0) sb.append(carry);
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args){
        t y=new t();
        double d=y.compute(3.1,4.8);
        System.out.println(d);


    }
}
