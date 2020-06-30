package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.repositories.ProjectRepoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepoitory projectRepository;

    public Project saveOrUpdateProject(Project project){


        return projectRepository.save(project);
    }

    public Project findProjectByIdentifier(String projectid){
        return projectRepository.findByProjectIdentifier(projectid);
    }

    public  Iterable<Project> findAllProjects(){

        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectid){

        Project project=projectRepository.findByProjectIdentifier(projectid);

         projectRepository.delete(project);

    }

   /* public Project updateProjectByIdentifier(String projectid){

        Project project=projectRepository.findByProjectIdentifier(projectid);

        return projectRepository.save(project);

    } */
}
