package ru.sevstal.sandbox;

public class Equality {

  public static void main (String []args){
    int s1 =4;
   int s2 =4;
   String a1= "Chrome";
   String b1 = new String(a1);
    System.out.println(s1==s2);
    System.out.println(a1.equals(b1));
  }
}
