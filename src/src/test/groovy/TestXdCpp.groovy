package com.lesfurets.jenkins
import com.lesfurets.jenkins.unit.BasePipelineTest
import static org.junit.Assert.assertEquals
import static com.lesfurets.jenkins.unit.MethodCall.callArgsToString
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertTrue

class Utils {
    def getProperty(String name) {
        if (name == 'platform')
            return "win"
        else
            return metaClass.getProperty(this, name)
    }
    def grunt(Object str, Object args) {
        //print args
        assertEquals("[--cpp, --no-build, --no-post-compile, --junit=\${testReportPath}, --target=Release]", args.toString())
    }
    def invokeMethod(String name, Object args) {
        println "called invokeMethod $name $args"
    }
}

public class stageResults {
    Closure update(a1, s2, s3, a4, Closure c) { return c() }
}


class TestXDCpp extends BasePipelineTest {


    class A {
        static void m1() {
            println 'WORKS!!!!'
        }
    }

    @Override
    @Before
    void setUp() throws Exception {
        super.setUp()

        binding.setVariable('LABEL_CPP_TESTS', 'cpp')
        binding.setVariable('PROP_CPP_BLASTER', 1)
        binding.setVariable('_buildDescription', "")
        binding.setVariable('env', [BRANCH_NAME:"master"])
        binding.setVariable('TIMEOUT_LENGTH_XSMALL', '100')
        binding.setVariable('testReportPath', '100')
        binding.setVariable('default_channel', 'master_build')
        binding.setVariable('_slackStageFailed', 'master_build')
        binding.setProperty("stageResults", new stageResults())
//        binding.setVariable('u', A.&m1)
    }

    @Test
    public void should_execute_without_errors() throws Exception {
        def script = loadScript("../job/exampleJob.jenkins");
        script.execute();
        printCallStack();

        assertTrue(helper.callStack.findAll { call ->
            call.methodName == "sh"
        }.any { call ->
            callArgsToString(call).contains("git --test")
        })
    }

    @Test
    public void should_execute_without_errors3() throws Exception {
        def script = loadScript("../job/cpp.jenkins")
        script.run("Release", new Utils())
        printCallStack()
    }
}