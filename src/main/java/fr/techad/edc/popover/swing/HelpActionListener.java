package fr.techad.edc.popover.swing;

import java.awt.event.ActionListener;

/**
 * Listener to define a help behavior.
 */
public interface HelpActionListener extends ActionListener {

    /**
     * Define the keys and language code to apply when the event will be sent.
     *
     * @param mainKey      the main key
     * @param subKey       the sub key
     * @param languageCode the language code
     */
    void setKeysAndLanguageCode(String mainKey, String subKey, String languageCode);

}
