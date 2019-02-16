package svt.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SampleFrame2  extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem itemOpen;
    private JMenuItem itemExit;
    private FileDialog fileDialog;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;


    public SampleFrame2(String title) {
        super(title);
        setVisible(false);
        setSize(250, 200);
        setLocationRelativeTo(null);

        label1=new JLabel( "  Входной файл  : ");
        label2=new JLabel( );
        label3=new JLabel("    Выходной файл : " );
        label4=new JLabel();

        menuBar = new JMenuBar();
        menu = new JMenu("Файл");
        itemOpen = new JMenuItem("Открыть...");
        itemExit = new JMenuItem("Выход");

        menu.add(itemOpen);
        menu.add("-");
        menu.add(itemExit);
        menuBar.add(menu);
        itemOpen.addActionListener(this);
        itemExit.addActionListener(this);

        setJMenuBar(menuBar);

        // this.addWindowListener(this);
        setLayout(new FlowLayout());
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        label1.setVisible(false);
        label3.setVisible(false);

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

            label1.setVisible(true);
            label3.setVisible(true);
            label2.setText(fd + fn );
           label4.setText(fd + "out" + fn );

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
        SampleFrame2 sampleFrame = new SampleFrame2("Delete Commas");
        sampleFrame.setVisible(true);
    }
}