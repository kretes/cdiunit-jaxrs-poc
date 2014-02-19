package kretes;

import com.sun.jersey.server.impl.cdi.CDIExtension;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
@Produces("application/vnd.collection.next+json")
@RequestScoped
public class Root {
    
    @Inject
    private Bean bean;

    @Inject
    private CDIExtension e;
    
    @GET
    public String get(){
        return String.format("{\"response\" : \"%s\"}", e != null);
        
    }
    
}
