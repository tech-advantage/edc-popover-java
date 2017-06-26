# edc-popover-java

To use edc publication in your Java application, you can use this utility. Work only for swing application.

## Configuration 

### with Injection

Based on Guice, you need to include EdcClientModule and EdcPopoverModule in the injector creation.

```java
Injector injector = Guice.createInjector(new EdcClientModule(), new EdcPopoverModule());
```

To declare the server url, you have to inject ``EdcClient``

To change the icon and the language, you have to inject ``EdcSwingHelp``.
Then call ``setIconPath`` and ``setLanguageCode`` to define the new value

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
  }
}
```

The default value : 

Members | Default value
--------|--------------
Icon Path| icons/icon-32px.png
Language Code | en

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
```

## Add the contextual button

By default, this engine create a button with icon.
On clic, the system browser is open and the documentation is displayed.

To create the component, you just need to 

### with injection

Inject ``EdcSwingHelp`` and call the method ``createComponent`` with two parameter : the main and sub key, you are defined in the brick  
```java
EdcSwingHelp.createComponent("fr.techad.edc", "help.center")
```

### with Singleton

Get the instance of ``EdcSwingHelpSingleton`` and call the method ``createComponent`` with two parameter : the main and sub key, you are defined in the brick  

```java
EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center")
```

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

        JFrame f = new JFrame();
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.TRAILING);
        f.setLayout(layout);


        // Create the button
        f.add(EdcSwingHelpSingleton.getInstance().createComponent("fr.techad.edc", "help.center"));
        f.setPreferredSize(new Dimension(400,400));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
```

## License

MIT [TECH'advantage](mailto:contact@tech-advantage.com)