package de.uniba.rz.backend;

import de.uniba.rz.backend.get.Search;
import de.uniba.rz.backend.post.Create;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/")
public class CrudApi extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(Create.class);
        resources.add(Search.class);

        return resources;
    }
}
