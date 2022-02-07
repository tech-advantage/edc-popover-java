# edc-popover-java

To use edc publication in your Java application, you can use this utility. Work only for swing application.

## edc Version

Current release is compatible with edc v3.0+

## How can I get the latest release?

You can pull it from the central Maven repositories:

### Maven
```xml
<dependency>
  <groupId>fr.techad</groupId>
  <artifactId>edc-popover</artifactId>
  <version>3.1.0-SNAPSHOT</version>
</dependency>
```

### Gradle
```groovy
    compile group: 'fr.techad', name: 'edc-popover', version: '3.1.0-SNAPSHOT'

```

## Configuration 

We will be able to configure the url to get the documentation and the widget properties.

| Properties | Method | Default | Description |
|--|--|--|--|
| Icon Path| ``setIconPath`` | icons/icon-32px.png| The help icon displays in the component |
| Language Code | ``setLanguageCode`` | en | The help language code |
| Tooltip | ``setTooltipLabel`` | '' | The tooltip displays on the help icon |
| Summary Help | ``setSummaryDisplay`` |false| Display the help summary dialog |
| Hover display popover | ``setHoverDisplayPopover`` |false| Display the popover when the mouse is over it |
| Title | ``setTitleDisplay`` | true | Display the title in the help dialog |
| Separator | ``setSeparatorDisplay`` | true | Display the separator in the help header |
| Header title color | ``setHeaderTitleColor`` | BLACK | Color of the title in the header of the help dialog |
| Background color | ``setBackgroundColor`` | WHITE | Background color of the help dialog |
| Separator color | ``setSeparatorColor`` | #3C8DBC | Separator color of the help dialog |
| Font attributes | ``setFontAttributes`` | "Dialog", Font.BOLD, 20 | Font attributes of the help dialog |
| Close Icon | ``setCloseIconPath`` | popover/close1.png | The close icon display in the summary dialog |
| Internal Browser size | ``setBrowserSize`` | 1024, 600 | Set the embedded browser |

### with Injection

Based on Guice, you need to include EdcClientModule and EdcPopoverModule in the injector creation.

```java
Injector injector = Guice.createInjector(new EdcClientModule(), new EdcPopoverModule());
```

To declare the server url, you have to inject ``EdcClient``

To customise this widget, you have to inject ``EdcSwingHelp``.
Then call the following method


```java
public class Example {
  private EdcSwingHelp help;

  @Inject
  public Example(EdcSwingHelp help) {
      this.help=help;
  }

  public void configure() {
    help.setIconPath("my-icon.png");
    help.setLanguageCode("fr");
    help.setTooltipLabel("edc Help");
    help.setSummaryDisplay(true);
    help.setBackgroundColor(Color.BLUE);
    help.setCloseIconPath("popover/close2.png");
    help.setHelpViewer(HelpViewer.EMBEDDED_VIEWER);
    help.setBrowserSize(1600, 900);
  }
}
```

### with Singleton

If you develop your software without injection engine. You can use include your documentation with  ``EdcSwingHelpSingleton``

To define the server url:  
```java
EdcSwingHelpSingleton.getInstance().getEdcClient().setServerUrl("https://demo.easydoccontents.com");
```  

To change the icon path and the default language
```java
EdcSwingHelpSingleton.getInstance().setIconPath("my-icon.png");
EdcSwingHelpSingleton.getInstance().setLanguageCode("fr");
EdcSwingHelpSingleton.getInstance().setTooltipLabel("edc Help");
EdcSwingHelpSingleton.getInstance().setSummaryDisplay(true);
EdcSwingHelpSingleton.getInstance().setBackgroundColor(Color.BLUE);
EdcSwingHelpSingleton.getInstance().setCloseIconPath("popover/close2.png");
EdcSwingHelpSingleton.getInstance().setInternalBrowser(false);
EdcSwingHelpSingleton.getInstance().setBrowserSize(1600, 900);
```

#### Config desktop viewer path

If you want to use the desktop viewer, you should define the path
```
EdcSwingHelpSingleton.getInstance().setHelpViewer(HelpViewer.EDC_DESKTOP_VIEWER);
EdcSwingHelpSingleton.getInstance().setViewerDesktopPath("Define the path here");
```

## Add the contextual button

By default, this engine create a button with icon.
On clic, the system browser is open and the documentation is displayed.

To create the component, you just need to 

### with injection

Inject ``EdcSwingHelp`` and call the method ``createComponent`` with two parameters : the main and sub key, you are defined in the brick  
```java
EdcSwingHelp.createComponent("fr.techad.edc", "help.center");
```

### with Singleton

Get the instance of ``EdcSwingHelpSingleton`` and call the method ``createComponent`` with two parameters : the main and sub key, you are defined in the brick  

```java
EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center");
```

## Add the contextual button with a customized icon

If you want to change the default icon for some button, you can call the createComponent method and set the icon path. 

### with injection

Inject ``EdcSwingHelp`` and call the method ``createComponent`` with 3 parameters : the main and sub key, you are defined in the brick and the icon path
```java
EdcSwingHelp.createComponent("fr.techad.edc", "help.center", "popover/close1.png");
```

### with Singleton

Get the instance of ``EdcSwingHelpSingleton`` and call the method ``createComponent`` with 3 parameters : the main and sub key, you are defined in the brick and the icon path 

```java
EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center", "popover/close1.png");
```

## Language selection
You can set the language for the content and the popover labels by calling the method ``setLanguageCode`` (see the Example section below).

Label translations can be modified in the associated i18n json files, present in the documentation export (at [yourDocPath]/popover/i18n/ (*.json)).

There is one file per language (see file structure below), and files should be named following the ISO639-1 two letters standards 
(ie en.json, it.json...).

As an example, here is the en.json file used by default:

```json
{
  "labels": {
    "articles": "Need more...",
    "links": "Related topics"
  }
}
```

You can find a simple implementation in a swing environment in the example section below

## Example
To see this utility in action, just run this example

```java
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
        EdcSwingHelpSingleton.getInstance().setIconPath("icons/icon-32px.png");
        EdcSwingHelpSingleton.getInstance().setLanguageCode("en");
        EdcSwingHelpSingleton.getInstance().setTooltipLabel("Help");
        EdcSwingHelpSingleton.getInstance().setSummaryDisplay(true);
        EdcSwingHelpSingleton.getInstance().setTitleDisplay(true);
        EdcSwingHelpSingleton.getInstance().setBackgroundColor(Color.WHITE);
        EdcSwingHelpSingleton.getInstance().setSeparatorColor(Color.RED);
        EdcSwingHelpSingleton.getInstance().setCloseIconPath("popover/close2.png");
        EdcSwingHelpSingleton.getInstance().setHelpViewer(HelpViewer.EMBEDDED_VIEWER);
        EdcSwingHelpSingleton.getInstance().setBrowserSize(1600, 900);
        EdcSwingHelpSingleton.getInstance().setFontAttributes(new Font("Dialog", Font.BOLD, 20));
        EdcSwingHelpSingleton.getInstance().setHeaderTitleColor(Color.BLUE);
        
        JFrame f = new JFrame();
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.TRAILING);
        f.setLayout(layout);

        // Add the language selector
        JComboBox<String> langSelect = createLangSelector();
        helpIconPanel.add(langSelect);

        // Create the button with default icon
        f.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center"));
        // Create the button with a user defined icon
        f.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center", "popover/close1.png"));
        f.setPreferredSize(new Dimension(400,400));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
    
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
```

## How can I build this project ?

This project is based on *gradle*. You can install the artefacts in your local cache with:

``gradle clean PublishToMavenLocal``

## License

MIT [TECH'advantage](mailto:contact@tech-advantage.com)
