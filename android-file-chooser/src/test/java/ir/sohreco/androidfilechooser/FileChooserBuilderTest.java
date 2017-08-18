package ir.sohreco.androidfilechooser;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;

@RunWith(JUnit4.class)
public class FileChooserBuilderTest {
    @Rule
    public final TemporaryFolder testFolder = new TemporaryFolder();
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private FileChooser.Builder builder;
    private static FileChooser.ChooserListener dummyChooserListener;

    @BeforeClass
    public static void setupDummyChooserListener() {
        dummyChooserListener = new FileChooser.ChooserListener() {
            @Override
            public void onSelect(String path) {

            }
        };
    }

    @Test
    public void passingNullAsChooserTypeThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("chooserType can not be null.");

        builder = new FileChooser.Builder(null, dummyChooserListener);
    }

    @Test
    public void passingNullAsChooserListenerThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("chooserListener can not be null.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, null);
    }

    /**
     * setFileFormats Tests.
     */

    @Test
    public void settingFileFormatsWhenChooserTypeIsDirectoryChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set file formats when chooser type is DIRECTORY_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setFileFormats(new String[]{".png"});
    }

    @Test
    public void passingNullAsFileFormatsThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("File formats can't be null. If you want all types of files to be shown, simply don't set this parameter.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setFileFormats(null);
    }

    @Test
    public void passingAnEmptyArrayAsFileFormatsThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("File formats can't be empty. If you want all types of files to be shown, simply don't set this parameter.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setFileFormats(new String[]{});
    }

    @Test
    public void enablingMultipleFileSelectionWhenChooserTypeIsDirectoryChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Multiple file selection can't be enabled when chooser type is DIRECTORY_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setMultipleFileSelectionEnabled(true);
    }

    /**
     * setInitialDirectory Tests
     */

    @Test
    public void passingNullAsInitialDirectoryThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("initialDirectory can't be null.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setInitialDirectory(null);
    }

    @Test
    public void passingANonExistingPathAsInitialDirectoryThrowsIllegalArgumentException() throws IOException {
        File nonExistingDirectory = testFolder.newFolder();
        nonExistingDirectory.delete();

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(nonExistingDirectory.getPath() + " Does not exist.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setInitialDirectory(nonExistingDirectory);
    }

    @Test
    public void passingAFileAsInitialDirectoryThrowsIllegalArgumentException() throws IOException {
        File file = testFolder.newFile();

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(file.getPath() + " Is not a directory.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setInitialDirectory(file);
    }

    /**
     * Select Multiple Files Button Tests
     */

    @Test
    public void settingSelectMultipleFileButtonTextWhenChooserTypeIsDirectoryChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select multiple files button's text when chooser type is DIRECTORY_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setSelectMultipleFilesButtonText("SELECT FILES");
    }

    @Test
    public void settingSelectMultipleFilesButtonTextSizeWhenChooserTypeIsDirectoryChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select multiple files button's text size when chooser type is DIRECTORY_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setSelectMultipleFilesButtonTextSize(25);
    }

    @Test
    public void passingANegativeNumberOrZeroAsSelectMultipleFilesButtonTextSizeThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("textSize can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setSelectMultipleFilesButtonTextSize(0);
    }

    @Test
    public void settingSelectMultipleFilesButtonTextColorWhenChooserTypeIsDirectoryChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select multiple files button's text color when chooser type is DIRECTORY_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setSelectMultipleFilesButtonTextColor(android.R.color.background_dark);
    }

    @Test
    public void passingANegativeNumberOrZeroAsSelectMultipleFilesTextColorThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("colorId can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setSelectMultipleFilesButtonTextColor(-1);
    }

    @Test
    public void settingSelectMultipleFilesButtonBackgroundWhenChooserTypeIsDirectoryChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select multiple files button's background when chooser type is DIRECTORY_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setSelectMultipleFilesButtonBackground(android.R.drawable.checkbox_off_background);
    }

    @Test
    public void passingANegativeNumberOrZeroAsSelectMultipleFilesButtonBackgroundThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("backgroundId can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setSelectMultipleFilesButtonBackground(0);
    }

    /**
     * Select Directory Button Tests
     */
    @Test
    public void settingSelectDirectoryButtonTextWhenChooserTypeIsFileChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select directory button's text when chooser type is FILE_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setSelectDirectoryButtonText("");
    }

    @Test
    public void settingSelectDirectoryButtonTextSizeWhenChooserTypeIsFileChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select directory button's text size when chooser type is FILE_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setSelectDirectoryButtonTextSize(20);
    }

    @Test
    public void passingANegativeNumberOrZeroAsSelectDirectoryButtonTextSizeThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("textSize can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setSelectDirectoryButtonTextSize(-10);
    }

    @Test
    public void settingSelectDirectoryButtonTextColorWhenChooserTypeIsFileChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select directory button's text color when chooser type is FILE_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setSelectDirectoryButtonTextColor(android.R.color.background_dark);
    }

    @Test
    public void passingANegativeNumberOrZeroAsSelectDirectoryButtonTextColorThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("colorId can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setSelectDirectoryButtonTextColor(0);
    }

    @Test
    public void settingSelectDirectoryButtonBackgroundWhenChooserTypeIsFileChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set select directory button's background when chooser type is FILE_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setSelectDirectoryButtonBackground(android.R.drawable.checkbox_off_background);
    }

    @Test
    public void settingFileIconWhenChooserTypeIsDirectoryChooserThrowsIllegalStateException() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Can't set file icon when chooser type is DIRECTORY_CHOOSER.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.DIRECTORY_CHOOSER, dummyChooserListener)
                .setFileIcon(android.R.drawable.btn_default);
    }

    @Test
    public void passingANegativeNumberOrZeroAsFileIconThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("iconId can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setFileIcon(-1);
    }

    @Test
    public void passingANegativeNumberOrZeroAsDirectoryIconThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("iconId can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setDirectoryIcon(0);
    }

    @Test
    public void passingANegativeNumberOrZeroAsPreviousDirectoryButtonIconThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("conId can't be less than or equal to zero.");

        builder = new FileChooser.Builder(FileChooser.ChooserType.FILE_CHOOSER, dummyChooserListener)
                .setPreviousDirectoryButtonIcon(0);
    }
}
