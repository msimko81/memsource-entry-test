package cz.memsource.entrytest.projects;

import java.util.Collection;

/**
 * Projects service. Provides method for listing memsource projects.
 */
public interface ProjectsService {

    /**
     * List memsource projects.
     *
     * @return a list of memsource projects
     */
    Collection<Project> listProjects();
}
