package com.aeiton.adventro;

import com.aeiton.adventro.Model.JournalPage;

import java.util.ArrayList;

/**
 * Created by User on 19-Feb-17.
 */
public class JournalData {
    private static JournalData ourInstance = new JournalData();

    public static JournalData getInstance() {
        return ourInstance;
    }

    public static int id;

    public static ArrayList<JournalPage> pages = new ArrayList<>();

    private JournalData() {


    }
}
