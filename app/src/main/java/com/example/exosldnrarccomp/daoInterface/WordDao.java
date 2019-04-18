package com.example.exosldnrarccomp.daoInterface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.exosldnrarccomp.entityTables.Word;

import java.util.List;

// objet qui nous permettra d’accéder aux données. Il contiendra les requêtes
// SQL et les différentes méthodes d’appel.
@Dao
public interface WordDao {

    // Un insert sans SQL
    @Insert
    void insert (Word word);

    // Exécuter ses propres requête avec @Query il n'y pas d'annotaion pour deleteAll
    // il existe une annotation @Delete et @Update
    @Query("DELETE FROM word_table")
    void deleteAll();

    // Select All avec clause ORDER BY est facultatif
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    // LiveData en tant que valeur de retour qui observe les changements de données
    // Room met à jour le liveData lorsque la Bdd est mis à jour
    LiveData<List<Word>> getAllWords();
}
