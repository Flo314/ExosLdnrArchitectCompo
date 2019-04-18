package com.example.exosldnrarccomp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exosldnrarccomp.R;
import com.example.exosldnrarccomp.entityTables.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    // ViewHolder
    class WordViewHolder extends RecyclerView.ViewHolder{

        private final TextView wordItemView;

        private WordViewHolder(View itemView){
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }


    private final LayoutInflater inflater;
    private List<Word> mWords;

    // constructeur
    WordListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return  new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords != null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            // Couvre le cas où les données ne sont pas encore prête
            holder.wordItemView.setText("No word");
        }
    }

    void setmWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // est appelé plusieurs fois et quand il est appelé la première fois
    // mwords n'a pas été mis à jour il est null
    @Override
    public int getItemCount() {
        if(mWords != null) return mWords.size();
        else return 0;
    }
}
