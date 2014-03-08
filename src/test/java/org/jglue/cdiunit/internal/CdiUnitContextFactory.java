package org.jglue.cdiunit.internal;

import org.jglue.cdiunit.internal.CdiUnitContextFactory;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.naming.*;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;
import java.util.Properties;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CdiUnitContextFactory implements InitialContextFactory {

    private static CdiUnitContext context;

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        if (context == null) {
            context = new CdiUnitContext();
        }
        return context;
    }
}
