package cz.memsource.entrytest.projects;

import cz.memsource.entrytest.clients.AuthorizedMemsourceClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {

    private final AuthorizedMemsourceClientFactory memsourceClientFactory;

    @Override
    public Collection<Project> listProjects() {
        return memsourceClientFactory.client().get().uri("/api2/v1/projects")
                .retrieve().toEntity(ProjectsListPayload.class)
                .block().getBody().getContent();
    }
}
