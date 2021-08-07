package viewTests;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import de.astradeni.backupmanager.Manager;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class RootLayoutTest extends ApplicationTest {
	
	Pane mainroot;
	Pane overview;
	Stage mainstage;
	
    @Override
    public void start(Stage stage) throws IOException, TimeoutException {
    	Manager manager = new Manager();
    	manager.setPrimaryStage(stage);
        
        manager.initRootLayout();
    }
	
    @After
    public void tearDown () throws Exception {
      FxToolkit.hideStage();
      release(new KeyCode[]{});
      release(new MouseButton[]{});
    }
    
	@Test
    public void should_contain_button_with_text() {
        FxAssert.verifyThat("#myButton", LabeledMatchers.hasText("Exit"));
        FxAssert.verifyThat("#fileButton", LabeledMatchers.hasText("Files"));
    }
	
	@Test
	public void showFilesTest() {
		clickOn("#fileButton");
		FxAssert.verifyThat(window("File List"), WindowMatchers.isShowing());
		FxAssert.verifyThat("#overviewButton", LabeledMatchers.hasText("OK"));
	}
	
	@Test
	public void ifWindowNotEmpty() {
		clickOn("#fileButton");
		TextArea files = lookup("#field").query();
		Assert.assertTrue(files.getText().length()>0);
	
		
	}
	

  

}
