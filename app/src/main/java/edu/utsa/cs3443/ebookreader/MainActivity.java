package edu.utsa.cs3443.ebookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;

import edu.utsa.cs3443.ebookreader.model.EBook;
import edu.utsa.cs3443.ebookreader.model.Library;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String libraryName;
    private static Library library;
    private TextView libTextView;

    //define a static final key that can be used for passing arguments to other views.
    private static final String bookName = "book";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set up the library name
        this.libraryName = "Library1";
        createLibrary();
        updateTextView();
        //setupButton();
        dynamicSetupButton();
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button)view;
        String bookNameClicked = clickedButton.getText().toString().toLowerCase();
        //find the EBook object associated with the bookName?
//        clickedBook = this.library.getBook(bookNameClicked);
        //Create a new intent and pass the EBook object to the new view, which is BookView
        Intent intent = new Intent(MainActivity.this, BookActivity.class);
        //when passing values to new Activities you can only pass string objects
        //we need to pass EBook object, but we cannot!
        intent.putExtra(bookName, bookNameClicked);
        startActivity(intent);

    }

    private void createLibrary() {
        library = new Library(this.libraryName);
        library.loadBooks(this);
    }

    private void updateTextView() {
        //wire the textView to this controller class
        this.libTextView = findViewById(R.id.library_name);
        libTextView.setText(this.libraryName);
    }

    /* private void setupButton() {
        int[] buttonIDs = {R.id.book1, R.id.book2};
        for (int i = 0; i < buttonIDs.length; i++) {
            Button button = findViewById(buttonIDs[i]);
            button.setText(this.library.getBook(i).getTitle());
            button.setOnClickListener(this);
        }
    } */

    private void dynamicSetupButton() {
        //grab the layout from the activity_main and wire it toa local layout variable
        LinearLayout scrollLayout = findViewById(R.id.scroll_linear_layout);
        //ScrollView sView = findViewById(R.id.scroll_layout);
        //for each book in the library, create a new button and attach it to the root layout
        for(EBook book: library.getBooks()) {
            //create a new button
            Button butt = new Button(this);
            butt.setText(book.getTitle());
            butt.setOnClickListener(this);
            //set up the attributes of the button for example its size
            LinearLayout.LayoutParams buttonAttributes = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //add or attach the button to the root layout
            //rootLayout.addView(butt, buttonAttributes);
            scrollLayout.addView(butt, buttonAttributes);
        }
    }

    public static String getKey() {
        return bookName;
    }

    public static EBook getClickedBook(String bookTitle) {
        return library.getBook(bookTitle);
    }
}