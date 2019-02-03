package svt.view;

import javax.swing.*;
import java.awt.*;

public class FileInput implements Input {
       @Override
    public String getFile() {
        JFrame f = new SampleFrame("File Dialog ");
        f.setVisible(true);
        FileDialog fd = new FileDialog(f, "File Dialog");
        fd.setVisible(true);
        String fdS = fd.getFile();
        String fdD = fd.getDirectory();
        String readerFile = fdD + fdS;
        return readerFile;
    }
}
