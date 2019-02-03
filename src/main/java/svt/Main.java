package svt;

import svt.view.FileInput;
import svt.view.SampleFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInput fileInput = new FileInput();
        String readerFile = fileInput.getFile();
        try (FileReader reader = new FileReader(readerFile); FileWriter writer = new FileWriter("text1.txt")) {
            int c;
            // чтение по символам
            while ((c = reader.read()) != -1) {
                if ((char) c != ',') {
                    // запись по символам
                    writer.append((char) c);
                    System.out.print((char) c);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}