package gameapplication.view.filechooser;


import gameapplication.view.board.BoardView;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Nicolas Bouquiaux
 */
public class FileChooserView {
    public BoardView getView() {
        return view;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

    public Path getPath() {
        return path;
    }

    private final BoardView view = new BoardView();
    private final FileChooser fileChooser;
    private final Path path;

    public File getFile() {
        return file;
    }

    private File file;


    public FileChooserView() {
        this.path = Settings.getPath(Category.BOARDS.settingsKey);
        this.file = Paths.get(file.toURI()).toFile();


        openFile(view.getView(), Category.BOARDS, Format.FEN);
        saveFile(view.getView(), Category.BOARDS, Format.FEN);
        createChooser(Category.BOARDS, Format.FEN);
        handleResult(Category.BOARDS, file);
        forceExtension(new File("").toPath(), Format.FEN.extension);
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(Settings.getPath(Category.BOARDS.settingsKey).toFile());

    }
    public enum Category {
        BOARDS;
        final String settingsKey;

        Category() {
            settingsKey = "FileChoosers.Category." + name();
        }
    }

    public enum Format {
        FEN;

        final String extension;
        Format() {
            extension = "fen";
        }

        String description() {
            return "FileChoosers.Format." + name() + ".description" + '.' + this.extension;
        }

        String defaultFileName() {
            return "FileChoosers.Format." + name() + ".defaultFileName" + '.' + this.extension;
        }

    }


    public void openFile(Node ownerNode, Category category, Format format) {
        FileChooser chooser = createChooser(category, format);
        Path file = Paths.get(chooser.showOpenDialog(ownerNode.getScene().getWindow()).toURI());
        handleResult(category, file.toFile());

    }

    public void saveFile(Node ownerNode, Category category, Format format) {
        FileChooser chooser = createChooser(category, format);
        chooser.setInitialFileName(format.defaultFileName());
        File file = chooser.showSaveDialog(ownerNode.getScene().getWindow());

        forceExtension(handleResult(category, file), format.extension);
    }

    private static FileChooser createChooser(Category category, Format format) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(format.description(), "*." + format.extension);
        chooser.getExtensionFilters().add(filter);
        chooser.setSelectedExtensionFilter(filter);
        chooser.setInitialDirectory(Settings.getPath(category.settingsKey).toFile());
        return chooser;
    }

    private static Path handleResult(Category category, File file) {
        if (file != null) {
            Path path = file.toPath();
            Settings.putPath(category.settingsKey, path.getParent());
            return path;
        }
        return null;
    }

    private static Path forceExtension(Path file, String extension) {
        if (file != null) {
            String fileName = file.getFileName().toString();
            if (!fileName.isEmpty()) {
                int sepIdx = fileName.indexOf('.');
                if (sepIdx < 0) fileName += '.';
                if (sepIdx == (fileName.length() - 1)) fileName += extension;
                return file.getParent().resolve(fileName);
            }
        }
        return file;
    }




}



