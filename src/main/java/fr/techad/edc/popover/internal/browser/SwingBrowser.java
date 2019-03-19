package fr.techad.edc.popover.internal.browser;

import com.sun.javafx.webkit.WebConsoleListener;
import fr.techad.edc.popover.browser.Browser;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class SwingBrowser extends JFrame implements Browser {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwingBrowser.class);
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();
    private final JButton btnGo = new JButton("Go");
    private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();
    private JFXPanel jfxPanel;
    private WebEngine engine;

    private boolean isInit = false;

    public SwingBrowser() {
        super();
    }

    private static String toURL(String str) {
        try {
            return new URL(str).toExternalForm();
        } catch (MalformedURLException exception) {
            return null;
        }
    }

    private void initComponents() {
        LOGGER.debug("Init components");
        jfxPanel = new JFXPanel();
        createScene();

        ActionListener al = e -> loadURL(txtURL.getText());

        btnGo.addActionListener(al);
        txtURL.addActionListener(al);

        progressBar.setPreferredSize(new Dimension(150, 18));
        progressBar.setStringPainted(true);

        JPanel topBar = new JPanel(new BorderLayout(5, 0));
        topBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        topBar.add(txtURL, BorderLayout.CENTER);
        topBar.add(btnGo, BorderLayout.EAST);

        JPanel statusBar = new JPanel(new BorderLayout(5, 0));
        statusBar.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        statusBar.add(lblStatus, BorderLayout.CENTER);
        statusBar.add(progressBar, BorderLayout.EAST);

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(jfxPanel, BorderLayout.CENTER);
        panel.add(statusBar, BorderLayout.SOUTH);

        getContentPane().add(panel);

        setPreferredSize(new Dimension(1024, 600));
        pack();
        isInit = true;
        LOGGER.debug("Init components done!");

    }

    private void createScene() {
        LOGGER.debug("Create the scene with runLAter invocation");
        Platform.runLater(() -> {
            LOGGER.debug("Create Scene: create the WebView");
            WebView view = new WebView();
            LOGGER.debug("Create Scene: get the engine");
            engine = view.getEngine();

            LOGGER.debug("Create Scene: configure the engine");
            engine.titleProperty().addListener((observable, oldValue, newValue) -> SwingUtilities.invokeLater(() -> SwingBrowser.this.setTitle(newValue)));

            engine.setOnStatusChanged(event -> SwingUtilities.invokeLater(() -> lblStatus.setText(event.getData())));
            engine.locationProperty().addListener((ov, oldValue, newValue) -> SwingUtilities.invokeLater(() -> txtURL.setText(newValue)));
            engine.getLoadWorker().workDoneProperty().addListener((observableValue, oldValue, newValue) -> SwingUtilities.invokeLater(() -> progressBar.setValue(newValue.intValue())));
            engine.getLoadWorker().exceptionProperty()
                    .addListener((o, old, value) -> {
                        LOGGER.debug("Exception during rendering: {}, {}, {}", o, old, value);
                        if (engine.getLoadWorker().getState() == State.FAILED) {
                            SwingUtilities.invokeLater(() ->
                                    JOptionPane.showMessageDialog(
                                            panel,
                                            (value != null)
                                                    ? engine.getLocation() + "\n" + value.getMessage()
                                                    : engine.getLocation() + "\nUnexpected error.",
                                            "Loading error...",
                                            JOptionPane.ERROR_MESSAGE)
                            );
                        }
                    });
            // Log the error display in the console.
            WebConsoleListener.setDefaultListener((webView, message, lineNumber, sourceId) -> LOGGER.info("Console: [{} : {}] {}", sourceId, lineNumber, message));
            LOGGER.debug("Create Scene: set the view as scene");
            jfxPanel.setScene(new Scene(view));
            LOGGER.debug("Create Scene: done");
        });
    }

    @Override
    public void loadURL(final String url) {
        if (!isInit)
            initComponents();

        Platform.runLater(() -> {
            String tmp = toURL(url);

            if (tmp == null) {
                tmp = toURL("http://" + url);
            }
            LOGGER.debug("Load the url: {}", tmp);

            engine.load(tmp);
        });
    }

    @Override
    public void showBrowser(boolean show) {
        LOGGER.debug("showBrowser: {}", show);
        setVisible(show);
    }
}
