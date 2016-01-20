//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.saucelabs.testng;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.common.Utils;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import java.util.HashMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class SauceOnDemandTestListenerStubby extends TestListenerAdapter {
    private static final String SELENIUM_BROWSER = "SELENIUM_BROWSER";
    private static final String SELENIUM_PLATFORM = "SELENIUM_PLATFORM";
    private static final String SELENIUM_VERSION = "SELENIUM_VERSION";
    private static final String SELENIUM_IS_LOCAL = "SELENIUM_IS_LOCAL";
    private SauceOnDemandSessionIdProvider sessionIdProvider;
    private SauceREST sauceREST;
    private boolean isLocal = false;
    public static boolean verboseMode = true;

    public SauceOnDemandTestListenerStubby() {
    }

    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        String local = Utils.readPropertyOrEnv("SELENIUM_IS_LOCAL", "");
        if(local != null && !local.equals("")) {
            this.isLocal = true;
        }

        String browser = System.getenv("SELENIUM_BROWSER");
        if(browser != null && !browser.equals("")) {
            System.setProperty("browser", browser);
        }

        String platform = System.getenv("SELENIUM_PLATFORM");
        if(platform != null && !platform.equals("")) {
            System.setProperty("os", platform);
        }

        String version = System.getenv("SELENIUM_VERSION");
        if(version != null && !version.equals("")) {
            System.setProperty("version", version);
        }

    }

    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        if(!this.isLocal) {
            if(verboseMode && result.getInstance() instanceof SauceOnDemandSessionIdProvider) {
                this.sessionIdProvider = (SauceOnDemandSessionIdProvider)result.getInstance();
                if(this.sessionIdProvider.getSessionId() != null) {
                    System.out.println(String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", new Object[]{this.sessionIdProvider.getSessionId(), result.getMethod().getMethodName()}));
                }
            }

            SauceOnDemandAuthentication sauceOnDemandAuthentication;
            if(result.getInstance() instanceof SauceOnDemandAuthenticationProvider) {
                SauceOnDemandAuthenticationProvider provider = (SauceOnDemandAuthenticationProvider)result.getInstance();
                sauceOnDemandAuthentication = provider.getAuthentication();
            } else {
                sauceOnDemandAuthentication = new SauceOnDemandAuthentication();
            }

            this.sauceREST = new SauceREST(sauceOnDemandAuthentication.getUsername(), sauceOnDemandAuthentication.getAccessKey());
        }
    }

    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        if(!this.isLocal) {
            this.markJobAsFailed();
            this.printPublicJobLink();
        }
    }

    private void markJobAsFailed() {
        if(this.sauceREST != null && this.sessionIdProvider != null) {
            String sessionId = this.sessionIdProvider.getSessionId();
            if(sessionId != null) {
                HashMap updates = new HashMap();
                updates.put("passed", Boolean.valueOf(false));
                //Utils.addBuildNumberToUpdate(updates);
                this.sauceREST.updateJobInfo(sessionId, updates);
            }
        }

    }

    private void printPublicJobLink() {
        if(verboseMode && this.sauceREST != null && this.sessionIdProvider != null) {
            String sessionId = this.sessionIdProvider.getSessionId();
            String authLink = this.sauceREST.getPublicJobLink(sessionId);
            System.out.println("Job link: " + authLink);
        }

    }

    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        if(!this.isLocal) {
            this.markJobAsPassed();
        }
    }

    private void markJobAsPassed() {
        if(this.sauceREST != null && this.sessionIdProvider != null) {
            String sessionId = this.sessionIdProvider.getSessionId();
            if(sessionId != null) {
                HashMap updates = new HashMap();
                updates.put("passed", Boolean.valueOf(true));
                //Utils.addBuildNumberToUpdate(updates);
                this.sauceREST.updateJobInfo(sessionId, updates);
            }
        }

    }
}
