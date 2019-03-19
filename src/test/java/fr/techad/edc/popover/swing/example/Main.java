package fr.techad.edc.popover.swing.example;

import fr.techad.edc.client.model.InvalidUrlException;
import fr.techad.edc.popover.swing.EdcSwingHelpSingleton;

import javax.swing.*;
import java.awt.*;
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

    private static void createAndShowGUI()  {
        /* Configuration */
        EdcSwingHelpSingleton.getInstance().getEdcClient().setServerUrl("https://beta.easydoccontents.com");
        // EdcSwingHelpSingleton.getInstance().setIconPath("icons/icon-32px.png");
        // EdcSwingHelpSingleton.getInstance().setLanguageCode("en");
        // EdcSwingHelpSingleton.getInstance().setInternalBrowser(false);
        EdcSwingHelpSingleton.getInstance().setTooltipLabel("Help");
        EdcSwingHelpSingleton.getInstance().setSummaryDisplay(true);
        EdcSwingHelpSingleton.getInstance().setBackgroundColor(Color.WHITE);
        EdcSwingHelpSingleton.getInstance().setCloseIconPath("popover/close2.png");
        EdcSwingHelpSingleton.getInstance().setAutoDisabledMode(true);
        JFrame f = new JFrame();
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.TRAILING);
        f.setLayout(layout);


        // Create the button
        try {
            EdcSwingHelpSingleton.getInstance().getEdcClient().loadContext();
        } catch (IOException | InvalidUrlException e) {
            e.printStackTrace();
        }
        f.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center"));
        f.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc.configuration", "storehouses2"));
        f.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center", "popover/close1.png"));
        f.setPreferredSize(new Dimension(400, 400));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
