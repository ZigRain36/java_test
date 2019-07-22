package ru.sevstal.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP1"};

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    for (String l : languages) {
      System.out.println("Я хочу выучить" + l);
    }
    System.out.println("Я хочу выучить " + langs[2]);

    /* String [] langs = new String [4];
    langs [0]= "Java";
    langs [1]= "C#";
    langs [2]= "Python";
    langs [3]= "PHP";

     длина массива langs.length пройтись по всему массиву (до посднего массива включительно [0][1][2][3]
     как результат будет Я хочу выучить Java
    Я хочу выучить C#
    Я хочу выучить Python
    Я хочу выучить PHP
    */
    for (int i = 0; i < langs.length; i++) {
      System.out.println("Я хочу выучить " + langs[i]);

    }
    List<String> languagest = Arrays.asList("Java", "C#", "Python", "PHP-1");
    for (String l : languagest) {
      System.out.println("Я хочу выучить" + l);
    }
    List<String> languagestn = Arrays.asList("Java", "C#", "Python", "PHP-2");
    for (int i = 0; i < languagestn.size(); i++) {
      System.out.println("Я хочу выучить" + languagestn.get(i)); //  "array"+ "for" - массив с условиями
    }
    List<String> languagesten = Arrays.asList("Java", "C#", "Python", "PHP-3");
    for (String l : languagesten) {
      System.out.println("Я хочу выучить" + l);
    }

  }
}