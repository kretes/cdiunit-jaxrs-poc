package kretes;

import com.sun.jersey.server.impl.cdi.CDIExtension;
import com.sun.jersey.test.framework.JerseyTest;
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
public class JaxRsSetupIntegrationTest {

    @Inject
    Bean bean;

    private JerseyTest test;

    @Before
    public void setUp() throws Exception {
        test = new JerseyTest("kretes"){};
        test.setUp();        
    }

    @After
    public void tearDown() throws Exception {
        test.tearDown();
    }
    
    @Test
    @InRequestScope
    public void shouldReturnHelloWorld() throws Exception {
        assert bean != null;

        expect().
            body("response", equalTo("true")).
        when().
            get(address("/"));
    }

    private String address(String path) {
        return String.format("http://localhost:9998%s", path);
    }
    
}
