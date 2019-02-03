package svt.view;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SampleFrame extends JFrame {
    private JLabel jLabel;
       public SampleFrame(String title)
    {
        super(title);
        setSize(150, 100);
        jLabel = new JLabel("    Укажите имя файла");
        add( jLabel);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
}
