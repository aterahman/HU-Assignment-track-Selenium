import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class testnglistener extends weathershopper implements ITestListener
{


    @Override
    public void onTestStart(ITestResult result)
    {
        log.info("Test has started"+result);

    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        log.info("PASSED TEST "+result);

    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        log.info("FAILED TEST "+result);

    }

    @Override
    public void onTestSkipped(org.testng.ITestResult result)
    {
        /* compiled code */
    }
}
