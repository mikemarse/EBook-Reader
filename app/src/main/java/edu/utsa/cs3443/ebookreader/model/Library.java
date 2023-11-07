package edu.utsa.cs3443.ebookreader.model;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import edu.utsa.cs3443.ebookreader.MainActivity;

public class Library {

    private String libraryName;
    private ArrayList<EBook> books;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        books = new ArrayList<>();
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public ArrayList<EBook> getBooks() {
        return this.books;
    }
    public void setBooks(ArrayList<EBook> book) {
        this.books.addAll(book);
    }

    public void addBook(EBook book) {
        this.books.add(book);
    }

    public EBook getBook(int index) {
        return books.get(index);
    }

    public EBook getBook(String title) {
        for(EBook book: books) {
            if(book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }
        return null;
    }

    public void loadBooks(MainActivity activity) {
        AssetManager manager = activity.getAssets();
        String[] libBooks;
        try {
            //read all the files names that are under the assets
            libBooks = manager.list(this.libraryName);
            for (String bookFileName: libBooks) {
                //create a new object of EBook
                //example of bookFileName: lion_king.txt
                Log.d("Message for Book File Name", bookFileName);
                String[] split = bookFileName.split("\\.");
                String bookTitle = split[0]; //lion_king
                EBook book = new TxtBook(bookTitle, bookFileName);
                book.setPath(this.libraryName);
                Log.d("Look for bookCover", bookTitle);
                book.setBookCover(bookTitle);
                books.add(book);
            }
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
