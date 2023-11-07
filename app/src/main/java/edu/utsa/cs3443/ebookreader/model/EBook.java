package edu.utsa.cs3443.ebookreader.model;

import edu.utsa.cs3443.ebookreader.BookActivity;
import edu.utsa.cs3443.ebookreader.MainActivity;

public abstract class EBook {

    private String title;
    private String fileName;
    private String path;
    private String bookCover;

    public EBook(String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path + "/" + this.fileName;
    }
    public String getBookCover() {
        return this.bookCover;
    }
    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }
    public String toString() {
        return this.title;
    }
    public abstract String readBook(BookActivity activity);
    //TODO: Add BookActivity view as input argument of readBook method

}
