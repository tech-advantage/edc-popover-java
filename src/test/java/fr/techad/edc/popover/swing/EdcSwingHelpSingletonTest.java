package fr.techad.edc.popover.swing;

import fr.techad.edc.popover.internal.swing.components.IconButton;
import fr.techad.edc.popover.internal.swing.components.IconButtonListener;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The edc Swing Help Singleton Test
 */
public class EdcSwingHelpSingletonTest {

    @Test
    public void shouldGetAJComponent() {
                 /* Configuration */
        EdcSwingHelpSingleton.getInstance().getEdcClient().setServerUrl("https://demo.easydoccontents.com");
        EdcSwingHelpSingleton.getInstance().setIconPath("icons/icon1-32px.png");
        EdcSwingHelpSingleton.getInstance().setLanguageCode("en");
        JComponent component = EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center");
        Assert.assertTrue(component instanceof IconButton);
        IconButton iconButton = (IconButton) component;
        ActionListener[] actionListeners = iconButton.getActionListeners();
        boolean contain = false;
        for (ActionListener actionListener : actionListeners) {
            contain |= actionListener instanceof IconButtonListener;
        }
        Assert.assertTrue(contain);
    }

}