import org.testng.ITestListener;
import org.testng.ITestResult;

public class testnglistener extends weathershopper implements ITestListener
{
    @Override
    public void onTestStart(ITestResult result)
    {

    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        System.out.println("PASSED TEST");
        passed();
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        System.out.println("FAILED TEST");
        failed();
    }

    @Override
    public void onTestSkipped(org.testng.ITestResult result)
    {
        /* compiled code */
    }
}
