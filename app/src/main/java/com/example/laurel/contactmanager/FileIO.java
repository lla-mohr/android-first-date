package com.example.laurel.contactmanager;

import android.os.Environment;
import android.widget.EditText;

import com.example.laurel.contactmanager.contactapp.ContactManagerContent;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

public class FileIO {
    String filename = "input.txt";
    FileInputStream inputStream;
    ContactManagerContent inStream;
    static File file = null;
    boolean bExists;
    PrintWriter pw;
    final String LINE_SEPARATOR = "\r\n";

    FileIO() {
        //Open new directory and file, if need be
        try {
            File newFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "MsgFolder");
            if (!newFolder.exists()) {
                bExists = newFolder.mkdirs();
            }
            try {
                file = new File(newFolder, filename);
                file.createNewFile();
            } catch (Exception ex) {
                System.out.println("ex: " + ex);
            }
        } catch (Exception e) {
            System.out.println("e: " + e);
        }

        try {
            //Open a stream to add items from file, if it already exists

            inputStream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            inStream = new ContactManagerContent(br);
        } catch (Exception e) {

        }

    }
    public void save() {
        //Save is called in order to output the LinkedHashMap (in order hash map) to a file

        try {
            ContactManagerContent.Person w;
            File newFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "MsgFolder");
            if (!newFolder.exists()) {
                bExists = newFolder.mkdirs();
            }
            file = new File(newFolder, filename);
            file.createNewFile();
            pw = new PrintWriter(file);
            for (Map.Entry<String, ContactManagerContent.Person> entry : ContactManagerContent.ITEM_MAP.entrySet()) {
                w = entry.getValue();
                pw.write(w.id + "," + w.firstName + "," + w.lastName + "," + w.phoneNumber + "," + w.email + LINE_SEPARATOR);
            }
            pw.close();

        } catch (Exception ex) {
            System.out.println("Error creating PW: " + ex.getMessage());
        }
    }

}
