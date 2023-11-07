package edu.utsa.cs3443.ebookreader.model;

import android.content.res.AssetManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import edu.utsa.cs3443.ebookreader.BookActivity;

public class TxtBook extends EBook{

    public TxtBook(String title, String fileName) {
        super(title, fileName);
    }
    @Override
    public String readBook(BookActivity activity) {
        //TODO: Access the asset folder of BookActivity view
        AssetManager manager = activity.getAssets();
        String page = "";
        Scanner scan;

        try {
            InputStream input = manager.open(getPath());
            scan = new Scanner(input);
            String line = "";
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                page = page + line;
            }
            return page;
        } catch (FileNotFoundException e) {
            System.out.println("File not found! :(");
        } catch(IOException e) {
            System.err.println("IO Exception");
        }
        return null;
    }
}
