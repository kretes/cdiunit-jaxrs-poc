package org.jglue.cdiunit.internal;

import kretes.FixedICFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;

public class CdiUnitContextFactory implements InitialContextFactory {

    private static Context context;

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        if(context == null) {
            context = new FixedICFactory().getInitialContext(environment);
        }
        return context;
    }

}
