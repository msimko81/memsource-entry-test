package cz.memsource.entrytest.projects;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProjectsServiceIT {

    @Autowired
    private ProjectsService projectsService;

    @Test
    //very simple smoke test of functionality
    void listProjects() {
        var projects = projectsService.listProjects();
        assertNotNull(projects);
    }
}
