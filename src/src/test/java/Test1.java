package test.java;


import com.lesfurets.jenkins.unit.BasePipelineTest;
import org.junit.Test;

public class Test1 extends BasePipelineTest {

    //...

    @Test
    public void should_execute_without_errors() throws Exception {
        System.out.println("SALUT");
//        def script = loadScript("job/exampleJob.jenkins");
//        script.execute();
//        printCallStack();
    }
}