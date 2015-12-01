package mainpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.Files;

import libcppgen.*;

/**
 * Created by k on 30.11.2015.
 */



public class MainDialogForm extends JFrame{
    private JPanel contentPanel;
    private JButton buttonToSelectDestination;
    private JButton buttonToGenerate;
    private JCheckBox checkBoxGenerateBoth;
    private JTextField textFieldToEnterFileName;
    private JComboBox comboBoxToSelectPattern;
    /*Form components*/



    /*Java components*/
    private JFileChooser fileChooser;
    /*Java components*/



    /**/
    private class WindowEventListener implements WindowListener {
        public void windowClosing(WindowEvent arg0) {
            fileManager.cleanUp();
            System.exit(0);
        }

        public void windowOpened(WindowEvent arg0) {}
        public void windowClosed(WindowEvent arg0) {}
        public void windowIconified(WindowEvent arg0) {}
        public void windowDeiconified(WindowEvent arg0) {}
        public void windowActivated(WindowEvent arg0) {}
        public void windowDeactivated(WindowEvent arg0) {}
    }
    /**/


    /*Other...*/
    OutputFileManager fileManager;
    OutputFileCreator fileHCreator;
    OutputFileCreator fileCppCreator;
    /*Other...*/

    public MainDialogForm ()
    {
        super("CodeGenerator");
        setContentPane(contentPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = new Dimension(800, 500);
        this.setSize(d);
        this.setBackground(Color.DARK_GRAY);


        setUp();
        buttonSetUp();
        setVisible(true);
    }



    private void selectDestination ()
    {
        if (fileManager != null) {
            fileManager.cleanUp();
        }
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int error = fileChooser.showOpenDialog(this);
        if (error == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(MainDialogForm.this, "Something wrong !");
            return;
        }

        fileManager = new OutputFileManager(fileChooser.getSelectedFile());
        fileManager.addErrorListener(new FileManagerEventListener() {
            @Override
            public void eventPerformed(FileManagerEvent e) {
                JOptionPane.showMessageDialog(MainDialogForm.this, e.getCause());
            }
        });

        String name = textFieldToEnterFileName.getText();
        if (checkFileNameConv(name) == true) {
            createFiles(name);
        } else {
            createFiles("DefaultName");
        }

    }

    private boolean createFiles (String name)
    {
        if (fileManager == null) {
            return false;
        }

        fileCppCreator = fileManager.addFileCreator(name, "cpp");
        if (checkBoxGenerateBoth.isSelected()) {
            fileHCreator = fileManager.addFileCreator(name, "h");
        }


        return true;
    }








    private void setUp ()
    {
        fileChooser = new JFileChooser();
        this.addWindowListener(new WindowEventListener());

    }


    private void buttonSetUp ()
    {
        buttonToSelectDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDestination();
            }
        });
        /**/
        /**/
        buttonToGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    boolean checkFileNameConv (String name)
    {
        if (name.isEmpty() == true) {
            return false;
        }
        if (name.contains("./:'-=+@#$%^&*()") == true) {
            return false;
        }
        return true;
    }
}
