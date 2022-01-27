package fr.techad.edc.popover.swing.example;

import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.model.HelpViewer;
import fr.techad.edc.popover.swing.EdcSwingHelp;
import fr.techad.edc.popover.swing.EdcSwingHelpSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * TECH ADVANTAGE
 * All right reserved
 * Created by cochon on 22/06/2017.
 */
public class Main {
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        /* Configuration */
        EdcSwingHelpSingleton.getInstance().getEdcClient().setServerUrl("https://demo.easydoccontents.com");
        EdcSwingHelpSingleton.getInstance().setTooltipLabel("Help");
        EdcSwingHelpSingleton.getInstance().setSummaryDisplay(true);
        EdcSwingHelpSingleton.getInstance().setTitleDisplay(true);
        EdcSwingHelpSingleton.getInstance().setBackgroundColor(Color.WHITE);
        EdcSwingHelpSingleton.getInstance().setSeparatorColor(Color.RED);
        EdcSwingHelpSingleton.getInstance().setCloseIconPath("popover/close3.png");
        EdcSwingHelpSingleton.getInstance().setAutoDisabledMode(true);
        EdcSwingHelpSingleton.getInstance().setHelpViewer(HelpViewer.EDC_DESKTOP_VIEWER);
        EdcSwingHelpSingleton.getInstance().setViewerDesktopPath("C:\\Users\\bracq\\Desktop\\EDC help viewer\\EDC help viewer.exe");
        EdcSwingHelpSingleton.getInstance().setBrowserSize(1600, 900);

        /* Main wndow */
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());

        /* Top Panel to expose the icon help */
        JPanel helpIconPanel = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.TRAILING);
        helpIconPanel.setLayout(layout);
        f.add(helpIconPanel, BorderLayout.NORTH);

        JComboBox<String> langSelect = createLangSelector();
        helpIconPanel.add(langSelect);

        helpIconPanel.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center"));
        helpIconPanel.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc.configuration", "storehouses"));
        helpIconPanel.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc.configuration", "storehouses2"));
        helpIconPanel.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center", "icons/icon2-32px.png"));
        helpIconPanel.setPreferredSize(new Dimension(400, 400));

        /* Bottom panel to expose mouse listener management */
        JPanel buttonPanel = new JPanel();

        /* Button Help */
        JButton btn = new JButton("Help Info");
        buttonPanel.add(btn);
        btn.addMouseListener(EdcSwingHelpSingleton.getInstance().getMouseListener("fr.techad.edc", "help.center"));

        /* Jlabel Help */
        JLabel label = new JLabel("JLabel: Click here for help");
        buttonPanel.add(label);
        label.addMouseListener(EdcSwingHelpSingleton.getInstance().getMouseListener("fr.techad.edc.configuration", "storehouses"));

        /* Button as link */
        JButton link = new JButton();
        link.setText("<HTML><FONT color=\"#000099\"><U>Storehouse Help</U></FONT></HTML>");
        link.setHorizontalAlignment(SwingConstants.LEFT);
        link.setBorderPainted(false);
        link.setContentAreaFilled(false);
        link.setOpaque(false);
        link.addMouseListener(EdcSwingHelpSingleton.getInstance().getMouseListener("fr.techad.edc.configuration", "storehouses"));
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(link);

        f.add(buttonPanel, BorderLayout.SOUTH);

        try {
            EdcSwingHelpSingleton.getInstance().getEdcClient().loadContext();
        } catch (IOException | InvalidUrlException e) {
            e.printStackTrace();
        }

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    /**
     * Return a JComboBox for selecting the popover language
     *
     * @return the JComboBox<String> containing languages
     */
    private static JComboBox<String> createLangSelector() {
        EdcSwingHelp edcSwingHelp = EdcSwingHelpSingleton.getInstance();
        String[] langOptions = {"en", "fr", "ru", "vi", "zh", "it", "es"};

        JComboBox comboBox = new JComboBox(langOptions);
        comboBox.setSelectedIndex(0);

        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    String newLang = (String) event.getItem();
                    // Change the language to be used in popover for content and labels
                    edcSwingHelp.setLanguageCode(newLang);
                }
            }
        });
        return comboBox;
    }
}
