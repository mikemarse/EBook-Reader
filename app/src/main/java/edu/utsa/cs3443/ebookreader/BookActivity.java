package edu.utsa.cs3443.ebookreader;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.utsa.cs3443.ebookreader.model.EBook;

public class BookActivity extends AppCompatActivity {

    private EBook book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        String bookName = getIntent().getStringExtra(MainActivity.getKey());
        //use the string bookName to retrieve the EBook object
        //
        book = MainActivity.getClickedBook(bookName);
        //setup image view
        displayCoverImage();
        //set up the textview for title
        displayTitle();
        //set up the textview for content
        displayText();
        //set up ImageView, TextView for title, and setup the TextView for content
    }

    private void displayCoverImage() {
        //Step 1: Create a local variable for imageView and wire it up with the ImageView from xml file
        ImageView imView = (ImageView) findViewById(R.id.book_cover);
        //Step 2: Get the location where the image for this specific book is stored
        int imageResource = getResources().getIdentifier(book.getBookCover(), "drawable", getPackageName());
        //Step 3: Set the imageResource ID for the imView
        imView.setImageResource(imageResource);
    }

    private void displayTitle() {
        //Step 1: Create a TextView local variable and wire it with TextView in the xml file
        TextView txtViewTitle = (TextView) findViewById(R.id.book_title);
        //Step 2: Get the title of the current book object and set it for the TextView
        txtViewTitle.setText(book.getTitle());
    }

    private void displayText() {
        //Step 1: Create a TextView local variable and wire it with the content TextView in the xml file
        TextView txtVewContent = (TextView) findViewById(R.id.book_text);
        //Step 2: Load the content of this book from its asset file and set it for the TextView
        txtVewContent.setText(book.readBook(this));
    }
}