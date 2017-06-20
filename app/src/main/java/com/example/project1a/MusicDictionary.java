package com.example.project1a;


public class MusicDictionary {

    private String item;
    private String word;
    private String def;
    private int id;

    public static final String ID = "id";
    public  static final String WORD = "word";
    public static  final String DEF = "def";

    public String getItem(){
        return item;
    }
    public void setItem(String item){
        this.item = item;
    }
    public String getWord(){
        return word;
    }
    public  void setWord(String word){
        this.word = word;
    }
    public String getDef(){
        return def;
    }
    public  void setDef(String def){
        this.def = def;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        String result = sb.toString();
        return result;
    }



}
