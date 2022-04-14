package gameapplication.view.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Nicolas Bouquiaux
 */
public class MenuView extends MenuBar {
    private MenuItem miSave;

    private MenuItem miLoad;

    private final MenuItem miRestart;

    public MenuView() {
        Menu mnFile = new Menu("File");
        Menu mnHelp = new Menu("Help");
        miSave = new MenuItem("Save");
        miLoad = new MenuItem("Load");
        miRestart = new MenuItem("Restart");
        mnFile.getItems().addAll(miSave, miLoad, miRestart);
        super.getMenus().addAll(mnFile, mnHelp);
    }

    public MenuItem getMiSave() {
        return miSave;
    }

    public void setMiSave(MenuItem miSave) {
        this.miSave = miSave;
    }

    public MenuItem getMiLoad() {
        return miLoad;
    }

    public void setMiLoad(MenuItem miLoad) {
        this.miLoad = miLoad;
    }

    public MenuItem getMiRestart() {
        return miRestart;
    }
}
