package cz.memsource.entrytest.projects;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;

    @GetMapping("/projects")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not allowed to view the resource"),
            @ApiResponse(responseCode = "500", description = "Some other error, e.g. required 3rd party service is not available"),
    })
    public Collection<Project> listProjects() {
        return projectsService.listProjects();
    }
}
