package com.example.exosldnrarccomp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.exosldnrarccomp.daoInterface.WordDao;
import com.example.exosldnrarccomp.db.WordRoomDatabase;
import com.example.exosldnrarccomp.entityTables.Word;

import java.util.List;

/* Classe qui « centralise » l'accès à plusieurs sources de données
Gère les opérations de données
Fournit une API propre au reste de l'application pour les données de l'application
Implémente la logique permettant de décider d'extraire des données d'un
réseau ou d'utiliser les résultats mis en cache dans une base de données locale
 */
public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
    }

    // contructeur
    public void WordPepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    // Getter qui notifiera l’observateur quand une donnée sera modifiée
    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    /*wrapper  pour exécuter la requête dans un autre thread que le thread principal
     Room vérifie qu’aucune opération longue n’est exécutée sur le thread principal.
     Si c’est le cas, il bloque l’interface
     */
    public void insert (Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    /*classe AsyncTask  permet d'effectuer des opérations en arrière-plan
     et de publier des résultats sur le thread d'interface utilisateur
      sans avoir à manipuler les threads et / ou les gestionnaires
     */
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;


        // constructeur
        public insertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
