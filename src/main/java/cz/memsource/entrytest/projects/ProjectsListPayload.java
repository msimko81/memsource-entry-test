package cz.memsource.entrytest.projects;

import lombok.Data;

import java.util.Collection;

@Data
class ProjectsListPayload {

    private Collection<Project> content;
}
