package com.example.wordlist.ui.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.wordlist.R;
import com.example.wordlist.domain.Words;


/**
 * The class Add new word activity extends application compat activity
 */
public class AddNewWordActivity extends AppCompatActivity {

    private EditText wordEditText;
    private EditText meaningEditText;
    private EditText typeEditText;

    private boolean editMode;

    private int mID;

    public static final String EXTRA_ID = "com.example.wordlist.extraid";

    public static final String EXTRA_WORD = "com.example.wordlist.word";
    public static final String EXTRA_MEANING = "com.example.wordlist.meaning";
    public static final String EXTRA_TYPE = "com.example.wordlist.type";

    //view Model for adding new word Activity
    private AddNewWordViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        wordEditText = findViewById(R.id.edit_text_word);
        meaningEditText = findViewById(R.id.edit_text_meaning);
        typeEditText = findViewById(R.id.edit_text_type);

        Intent i = getIntent();
        if(i.hasExtra(EXTRA_ID))
        {
            //Update word
            setTitle("Edit Word");
            editMode = true;
            mID = i.getIntExtra(EXTRA_ID, -1);
            wordEditText.setText(i.getStringExtra(EXTRA_WORD).toString());
            meaningEditText.setText(i.getStringExtra(EXTRA_MEANING));
            typeEditText.setText(i.getStringExtra(EXTRA_TYPE));
        }else{
            //insert new word
            setTitle("Add new Word");
            editMode = false;
        }
        mViewModel = ViewModelProviders.of(this).get(AddNewWordViewModel.class);
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
        m.inflate(R.menu.menu, menu);
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
            case R.id.save_word:
                saveWord();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }




    /**
     *
     * Save word
     *
     */
    public void saveWord() {

        String word = wordEditText.getText().toString().trim();
        String meaning = meaningEditText.getText().toString().trim();
        String type = typeEditText.getText().toString().trim();

        Words wordObject = new Words(word, meaning, type);

        if (word.isEmpty() || type.isEmpty() || meaning.isEmpty()) {
            Toast.makeText(AddNewWordActivity.this, "please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        if(editMode){
            wordObject.setId(mID);
            mViewModel.update(wordObject);
        }else{
            mViewModel.insert(wordObject);
        }
        finish();
    }

}












