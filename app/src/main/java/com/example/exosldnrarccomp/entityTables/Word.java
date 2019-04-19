package com.example.exosldnrarccomp.entityTables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


// Représente une table
@Entity(tableName = "word_table")
public class Word {

    // champ id autoIncrémenté
    @PrimaryKey(autoGenerate = true)
    private int wordId;

    // ne pouvant pas être null
    @NonNull
    // donne un nom à la colonne
    @ColumnInfo(name = "word")
    private String mWord;

    // Contructeur
    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    }

    public int getWordId() {
        return this.wordId;
    }

    public String getWord() {
        return this.mWord;
    }
}
