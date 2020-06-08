package Common;

import Application.ApplicationManager;
import Recorder.SpecializedScreenRecorder;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Application.ApplicationManager.cleanDir;
import static Application.ApplicationManager.getDriver;
import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.VideoFormatKeys.MIME_AVI;
import static org.monte.media.VideoFormatKeys.MediaType;
import static org.monte.media.VideoFormatKeys.*;

public class BaseTest {
    private ScreenRecorder screenRecorder;
    public ApplicationManager app;
    File file = new File("");

    @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browserName) {
        app = new ApplicationManager();
        ApplicationManager.getInstance(browserName);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getDriver().quit();
    }

    @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    public void startVideo(String browser) {
        try {
            // Clean video dir
            File video = new File(file.getAbsoluteFile() + "\\videos\\");
            // Start REC Video
            startRecording(video);
            Reporter.log("</b><details><summary>Click to see last screen...</summary> <br></b>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startRecording(File file) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Rectangle captureSize = new Rectangle(0, 0, width, height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                        Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, "temp");
        this.screenRecorder.start();
    }

    @BeforeSuite(alwaysRun = true)
    public void CleanUp() {
        // Clean screen dir
        File file = new File("");
        cleanDir(file.getAbsoluteFile() + "\\screenshots\\");
        cleanDir(file.getAbsoluteFile() + "\\videos\\");
    }

    public void stopRecording() throws Exception {
        this.screenRecorder.stop();
    }

    @Parameters({"browserName"})
    @AfterMethod(alwaysRun = true)
    public void result(ITestResult result, String browser) throws Exception {
        stopRecording();
        Path yourFile = Paths.get(file.getAbsolutePath() + "\\videos\\temp.avi");
        Files.move(yourFile, yourFile
                .resolveSibling(file.getAbsolutePath() + "\\videos\\" + result.getMethod().getMethodName() + ".avi"));
        String className = this.getClass().getSimpleName();
        if (result.isSuccess()) {
            Reporter.setCurrentTestResult(result);
            Reporter.log("</details></b>");
            Reporter.log("<a href=..\\videos\\" + result.getMethod().getMethodName() + ".avi download=\"filename\">Download video</a>");
            app.logs().passedTest("Class - " + className + " TEST - " + result.getMethod().getMethodName(), browser);
            System.out.println("Class - " + className + "  TEST - " + result.getMethod().getMethodName()
                    + " Passed! browser - " + browser + " \n ");
        }

        if (!result.isSuccess()) {
            Reporter.setCurrentTestResult(result);
            Reporter.log("</details></b>");
            Reporter.log("<a href=..\\videos\\" + result.getMethod().getMethodName() + ".avi download=\"filename\">Download video</a>");
            app.logs().failTest("Class - " + className + "  TEST - " + result.getMethod().getMethodName(), browser);
            System.out.println("Class - " + className + "  TEST - " + result.getMethod().getMethodName()
                    + " Failure!  browser - " + browser + " \n ");
            app.logs().get_Log();
        }
    }
}
