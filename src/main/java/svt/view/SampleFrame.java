package svt.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SampleFrame extends JFrame implements ActionListener {
    private MenuBar menuBar;
    private Menu menu;
    private MenuItem itemOpen;
    private MenuItem itemExit;
    private FileDialog fileDialog;
    private TextArea textArea;

    public SampleFrame(String title) {
        super(title);
        setVisible(false);
        setSize(250, 200);
        setLocationRelativeTo(null);
        textArea = new TextArea(10, 30);
        textArea.append(" Введите через меню имя файла\n");
                menuBar = new MenuBar();
        menu = new Menu("Файл");
        itemOpen = new MenuItem("Открыть...");
        itemExit = new MenuItem("Выйти");

        menu.add(itemOpen);
        menu.add("-");
        menu.add(itemExit);
        menuBar.add(menu);
        itemOpen.addActionListener(this);
        itemExit.addActionListener(this);

        setMenuBar(menuBar);
        // this.addWindowListener(this);
        setLayout(new BorderLayout());
        add("Center", textArea);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(itemOpen)) {
            fileDialog = new FileDialog(this, "Open file");
           fileDialog.setVisible(true);
            String fd = fileDialog.getDirectory();
            String fn = fileDialog.getFile();

            textArea.append("\n" + "  Входной файл  : " + "\n");
            textArea.append(fd + fn + "\n" + "\n");
            textArea.append("    Выходной файл : " + "\n");
            textArea.append(fd + "out" + fn + "\n");

            String readerFile = fd + fn;
            String writerFile = fd + "out" + fn;
            try {
                deleteCommas(readerFile, writerFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            setVisible(false);
            System.exit(0);
        }
    }

    private  void deleteCommas(String readerFile, String writerFile) throws IOException {
        try (FileReader reader = new FileReader(readerFile); FileWriter writer = new FileWriter(writerFile)) {
            int c;
            // чтение по символам
            while ((c = reader.read()) != -1) {
                if ((char) c != ',') {
                    // запись по символам
                    writer.append((char) c);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SampleFrame sampleFrame = new SampleFrame("Delete Commas");
        sampleFrame.setVisible(true);
    }
}
