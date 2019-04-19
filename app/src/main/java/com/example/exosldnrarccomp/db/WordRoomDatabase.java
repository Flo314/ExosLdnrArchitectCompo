package com.example.exosldnrarccomp.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback) // le callback
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /*
    Pour supprimer tout le contenu et repeupler la base de données à chaque démarrage de l'application,
    créez un objet RoomDatabase.Callback dont vous surchargez la methode « onOpen() ».
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void ,Void>{

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db){
            mDao = db.wordDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);

            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }
}
