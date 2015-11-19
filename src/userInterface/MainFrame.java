package userInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Arnold on 2015-11-12.
 */
public class MainFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 640;

    private JFileChooser fileChooser;
    private String filePath;
    private BufferedImage graph;

    private JDialog aboutDialog;
    private JTextArea textArea;
    private JLabel pictureLabel;
    private JPanel panel;
    private JButton showGraphButton;
    private JButton showPictureButton;


    public MainFrame() {
        createFileChooser("Pliki png", "png");
        createMenu();
        this.add(panel = new InfoButtonPanel(), BorderLayout.EAST);
        pictureLabel = new JLabel();
        JScrollPane pictureScrollPane = new JScrollPane(pictureLabel);
        this.add(pictureScrollPane, BorderLayout.CENTER);
        pack();
    }


    private void createMenu() {
        //utworzenie paska menu oraz menu pliku
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openFileItem = new JMenuItem("Open file");
        JMenuItem closeItem = new JMenuItem("Close");
        openFileItem.addActionListener(new OpenFileAction());
        closeItem.addActionListener(new CloseAction());
        //utworzenie menu about
        JMenu aboutMenu = new JMenu("About");
        JMenuItem infoItem = new JMenuItem("Info");
        infoItem.addActionListener(new ShowInfoAction());


        MainFrame.this.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        fileMenu.add(openFileItem);
        fileMenu.add(closeItem);
        aboutMenu.add(infoItem);

    }

    private void createFileChooser(String fileLabel, String... fileExtensions) {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(fileLabel, fileExtensions);
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }


    private class InfoButtonPanel extends JPanel {
        public InfoButtonPanel() {
            setLayout(new BorderLayout());
            add(new JScrollPane(textArea = new JTextArea(10, 15)), BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(showPictureButton = new JButton("Show picture"));
            buttonPanel.add(showGraphButton = new JButton("Show Graph"));
            add(buttonPanel,BorderLayout.SOUTH);
            showPictureButton.setEnabled(false);
            showGraphButton.setEnabled(false);
            showPictureButton.addActionListener(new ShowPictureAction());
            //showGraphButton.addActionListener(new ShowGraphAction());
            textArea.setEnabled(false);
        }
    }

    private class AboutDialog extends JDialog {
        public AboutDialog(JFrame owner) {
            super(owner, "Info");
            JLabel centerLabel = new JLabel("" +
                    "<html><h1><b>E-media</b></h1>Autor: Arnold Woznica <br/>Indeks: 200374</html>");
            JPanel southPanel = new JPanel();
            JButton okButton = new JButton("ok");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
            //dodanie wszystkich elementow
            southPanel.add(okButton);
            add(centerLabel, BorderLayout.CENTER);
            add(southPanel, BorderLayout.SOUTH);
            pack();
        }

    }

    private class ShowPictureAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            pictureLabel.setIcon(new ImageIcon(filePath));
        }
    }

    private class ShowInfoAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (aboutDialog == null)
                aboutDialog = new AboutDialog(MainFrame.this);
            aboutDialog.setVisible(true);
        }
    }




    private class OpenFileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser.setCurrentDirectory(new File("."));
            int result = fileChooser.showOpenDialog(MainFrame.this);
            {
                if (result == JFileChooser.APPROVE_OPTION) {
                    graph = null;
                    showPictureButton.setEnabled(true);
                    showGraphButton.setEnabled(true);
                    filePath = fileChooser.getSelectedFile().getPath();
                    textArea.append("File:" + '\n' + filePath + '\n');
                }

            }
        }
    }

    private class CloseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
}
