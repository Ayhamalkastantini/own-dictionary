package com.example.wordlist.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordlist.R;
import com.example.wordlist.domain.Words;

import java.util.ArrayList;
import java.util.List;


/**
 * The class Word adapter extends recycler view. adapter< word adapter. word view holder>
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Words> mWordList = new ArrayList<>();

    private OnItemCliclListener mListener;
    @NonNull
    @Override

/**
 *
 * On create view holder
 *
 * @param ViewGroup  the view group
 * @param viewType  the view type
 * @return WordViewHolder
 */
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override

/**
 *
 * On bind view holder
 *
 * @param WordViewHolder  the word view holder
 * @param position  the position
 */
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {



        Words currentWord = mWordList.get(position);
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewType.setText(currentWord.getWordType());
        holder.textViewMeaning.setText(currentWord.getWordMeaning());
    }



    /**
     *
     * Sets the words
     *
     * @param words  the words
     */


    public  void setWords(List<Words> words)
    {



        mWordList = words;
        notifyDataSetChanged();
    }
    @Override

/**
 *
 * Gets the item count
 *
 * @return the item count
 */
    public int getItemCount() {



        return mWordList.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;


        /**
         *
         * Word view holder
         *
         * @param View  the view
         * @return public
         */
        public WordViewHolder(@NonNull View itemView) {



            super(itemView);
            textViewWord = itemView.findViewById(R.id.word_text_view);
            textViewMeaning = itemView.findViewById(R.id.meaning_text_view);
            textViewType = itemView.findViewById(R.id.type_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override

/**
 *
 * On click
 *
 * @param view  the view
 */
                public void onClick(View view) {



                    int index = getAdapterPosition();

                    if(true)
                    {
                        mListener.onItemClick(mWordList.get(index));
                    }
                }
            });
        }
    }

    public interface OnItemCliclListener
    {
        void onItemClick(Words word);
    }



    /**
     *
     * On item click listener
     *
     * @param listener  the listener
     */


    public void OnItemClickListener(OnItemCliclListener listener)
    {



        mListener = listener;
    }



    /**
     *
     * Gets the word at
     *
     * @param pos  the pos
     * @return the word at
     */


    public Words getWordAt(int pos)
    {



        return mWordList.get(pos);
    }
}
