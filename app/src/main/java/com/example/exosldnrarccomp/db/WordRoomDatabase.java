package com.example.exosldnrarccomp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.exosldnrarccomp.daoInterface.WordDao;
import com.example.exosldnrarccomp.entityTables.Word;

/* Room utilise le DAO pour émettre les requêtes à ses bases de données
LiveData emet  des requêtes  de manière
asynchrone sur un autre thread dit « background »
*/
@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    // déclarez les DAO que vous utiliserez avec la base de données
    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;

    // Singleton pour avoir une seule instance de la database
    public static WordRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    // création de la database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
