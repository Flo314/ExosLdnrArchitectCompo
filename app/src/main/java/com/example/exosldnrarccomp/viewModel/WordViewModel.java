package com.example.exosldnrarccomp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.exosldnrarccomp.entityTables.Word;
import com.example.exosldnrarccomp.repository.WordRepository;

import java.util.List;

/*
Son rôle est de délivrer les
données à l’interface et supporter les changements de configuration. Elle agit comme un centre de
communication entre le référentiel (WordRepository) et l’interface utilisateur
 */
public class WordViewModel extends AndroidViewModel {

    // référence privée au repository WordRepository
    private WordRepository mWordRepository;

    // référence privée à la liste de mots pour mettre en cache les données
    private LiveData<List<Word>> mAllwords;

    // constructeur de la classe qui instancie le repository et la liste de mots
    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);
        mAllwords = mWordRepository.getmAllWords();
    }

    // Getter pour la liste de mots
    // Son implémentation est caché de L'UI
    LiveData<List<Word>> getmAllwords(){
        return mAllwords;
    }

    // Wrapper méthode insert qui fait appel à la fonction insert du WordRepository
    // Son implémentation est caché de L'UI
    public void insert(Word word){
        mWordRepository.insert(word);
    }
}
