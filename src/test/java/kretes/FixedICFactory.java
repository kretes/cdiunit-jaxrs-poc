package kretes;

import org.jglue.cdiunit.internal.CdiUnitContext;

import javax.naming.*;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;
import java.util.Properties;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class FixedICFactory implements InitialContextFactory {

    public static class DefaultParser implements NameParser
    {
        static Properties syntax = new Properties();
        static
        {
            syntax.put("jndi.syntax.direction", "left_to_right");
            syntax.put("jndi.syntax.separator", "/");
            syntax.put("jndi.syntax.ignorecase", "false");
        }
        public Name parse (String name)
                throws NamingException
        {
            return new CompoundName (name, syntax);
        }
    }

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        Context initialContext = new CdiUnitContext();
        final Context spy = spy(initialContext);
        doReturn(new DefaultParser()).when(spy).getNameParser(anyString());
        doReturn(spy).when(spy).lookup("com");
        doReturn(spy).when(spy).lookup("sun");
        doReturn(spy).when(spy).lookup("jersey");
        doReturn(spy).when(spy).lookup("config");
        return spy;
    }
}
