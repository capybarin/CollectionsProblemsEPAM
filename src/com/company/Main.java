package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public HashMap<String, Integer> wordMap = new HashMap<>();

    public  void readFile(){
        String text="";
        Scanner in = null;
        try {
            in = new Scanner(new File("text.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (in.hasNext())
            text += in.nextLine().toUpperCase()+"\r\n";
        String[] words = text.split("\\W");
        for (String str:words) {
            if (str.length()>=3)
                getTrigraphs(str);
        }
        sort(wordMap);
    }

    public void getTrigraphs(String word){
        if(wordMap.containsKey(word.substring(0,3))){
            if(word.length() == 3)
                wordMap.put(word, wordMap.get(word.substring(0, 3)) + 1);
            else{
                wordMap.put(word.substring(0,3),wordMap.get(word.substring(0,3))+1);
                getTrigraphs(word.substring(1));
            }
        }else{
            if(word.length() == 3)
                wordMap.put(word,1);
            else{
                wordMap.put(word.substring(0, 3), 1);
                getTrigraphs(word.substring(1));
            }
        }
    }

    public void sort(Map<String , Integer> map){
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> a, Map.Entry<String,Integer> b) {
                return a.getKey().compareTo(b.getKey());
            }
        });
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.readFile();
    }
}
