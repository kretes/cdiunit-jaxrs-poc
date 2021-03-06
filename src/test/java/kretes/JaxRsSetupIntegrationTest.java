package kretes;

import com.sun.jersey.server.impl.cdi.CDIExtension;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.jboss.weld.servlet.WeldListener;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.DummyHttpRequest;
import org.jglue.cdiunit.InRequestScope;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(CdiRunner.class)
@AdditionalClasses({CDIExtension.class,DummyHttpRequest.class,Root.class})
@InRequestScope
public class JaxRsSetupIntegrationTest {

    private JerseyTest test;

    @Inject
    private Bean bean;

    @Before
    public void setUp() throws Exception {
        AppDescriptor appDescriptor = new WebAppDescriptor.Builder("kretes").contextListenerClass(WeldListener.class).build();
        test = new JerseyTest(appDescriptor){};
        test.setUp();
    }

    @After
    public void tearDown() throws Exception {
        test.tearDown();
    }
    
    @Test
    public void shouldReturnHelloWorld() throws Exception {

        assert bean != null;

        expect().
            body("response", equalTo("true")).
        when().
            get("http://localhost:9998/");
    }

}
