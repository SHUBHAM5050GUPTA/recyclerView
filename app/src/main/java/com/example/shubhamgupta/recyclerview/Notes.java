package com.example.shubhamgupta.recyclerview;

import java.util.ArrayList;

/**
 * Created by Shubham Gupta on 12-07-2017.
 */

public class Notes {

    private   String title;
    private String description;

    public Notes(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  static ArrayList<Notes> getRandomNotes (int noOfNotes)
    {

        ArrayList<Notes> mNotes=new ArrayList<>();
         for(int i=0;i<noOfNotes;i++)
         {
             Notes mNote= new Notes("Title "+i,"Description "+i);
             mNotes.add(mNote);
         }
        return mNotes;


    }
}
