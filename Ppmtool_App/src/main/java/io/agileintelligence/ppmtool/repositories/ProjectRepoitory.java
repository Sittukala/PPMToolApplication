package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepoitory extends CrudRepository<Project,Long> {

    Project findByProjectIdentifier(String project);

    @Override
    Iterable<Project> findAll();

}

