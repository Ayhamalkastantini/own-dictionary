package com.example.wordlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordlist.R;
import com.example.wordlist.domain.Words;
import com.example.wordlist.network.WordsTranslaterAPI;
import com.example.wordlist.ui.add.AddNewWordActivity;
import com.example.wordlist.ui.api.TranslateActivity;
import com.example.wordlist.ui.list.WordAdapter;
import com.example.wordlist.ui.list.WordViewModel;
import com.example.wordlist.ui.sensors.gps.LocationActivity;
import com.example.wordlist.ui.sensors.thermometer.ThermometerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * The class Main activity extends application compat activity
 */
public class MainActivity extends AppCompatActivity {
    //veiw Model
    private WordViewModel mWordViewModel;

    //RecyclerView
    private RecyclerView mRecyclerView;
    private WordAdapter mWordAdapter;
    private WordsTranslaterAPI wordsTranslaterAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getString(R.string.app_name);
        //floating button
        FloatingActionButton floatingActionButton = findViewById(R.id.button_add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override

        /**
         *
         * On click
         *
         * @param view  the view
         */
            public void onClick(View view) {

                //go to the add activity
                Intent i = new Intent(MainActivity.this, AddNewWordActivity.class);
                startActivityForResult(i, 1);
            }
        });


        //recycler view
        mRecyclerView = findViewById(R.id.words_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        //connect Recyclerview With adapter
        mWordAdapter = new WordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);

        //View Model
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
            @Override

            /**
             *
             * On changed
             *
             * @param words  the words
             */
            public void onChanged(List<Words> words) {

                //Update UI
                //RecyclerView
                mWordAdapter.setWords(words);
                // Toast.makeText(MainActivity.this, "on Changed worked", Toast.LENGTH_LONG).show();
            }

        });

        mWordAdapter.OnItemClickListener(new WordAdapter.OnItemCliclListener() {
            @Override

/**
 *
 * On item click
 *
 * @param word  the word
 */
            public void onItemClick(Words word) {

                Intent i = new Intent(MainActivity.this, AddNewWordActivity.class);
                i.putExtra(AddNewWordActivity.EXTRA_ID, word.getId());
                i.putExtra(AddNewWordActivity.EXTRA_WORD, word.getWordName());
                i.putExtra(AddNewWordActivity.EXTRA_TYPE, word.getWordType());
                i.putExtra(AddNewWordActivity.EXTRA_MEANING, word.getWordMeaning());
                startActivity(i);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override

/**
 *
 * On move
 *
 * @param RecyclerView  the recycler view
 * @param RecyclerView.ViewHolder  the recycler view. view holder
 * @return boolean
 */
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override

/**
 *
 * On swiped
 *
 * @param RecyclerView.ViewHolder  the recycler view. view holder
 * @param direction  the direction
 */
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //delete item
                int position = viewHolder.getAdapterPosition();
                mWordViewModel.delete(mWordAdapter.getWordAt(position));
            }
        }).attachToRecyclerView(mRecyclerView);

    }

    @Override

/**
 *
 * On create options menu
 *
 * @param menu  the menu
 * @return boolean
 */
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override

/**
 *
 * On options item selected
 *
 * @param MenuItem  the menu item
 * @return boolean
 */
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.translate:
                Intent i = new Intent(MainActivity.this, TranslateActivity.class);
                startActivityForResult(i, 1);
                return true;
            case R.id.location:
                Intent j = new Intent(MainActivity.this, LocationActivity.class);
                startActivityForResult(j, 1);
                return true;
            case R.id.thermometer:
                Intent c = new Intent(MainActivity.this, ThermometerActivity.class);
                startActivityForResult(c, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
