package cz.memsource.entrytest.projects;

import lombok.Data;

import java.util.Collection;

@Data
public class Project {

    private String name;
    private ProjectStatus status;
    private String sourceLang;
    private Collection<String> targetLangs;
}
