package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import sun.plugin.perf.PluginRollup;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //to create a new project
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errormap=new HashMap<>();
            for(FieldError error:result.getFieldErrors()){
                errormap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errormap, HttpStatus.BAD_REQUEST);
        }
        Project project1=projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project, HttpStatus.CREATED);

    }


    @GetMapping("/{projectId}")
    //both given in getmapping and path varible has to match
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId){
        Project project=projectService.findProjectByIdentifier(projectId.toUpperCase());
     return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public  ResponseEntity<String> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId.toUpperCase());
     return new ResponseEntity<String>("Project id ' "+ projectId + " ' is deleted", HttpStatus.OK);
    }

  /*  @PostMapping("/updateProj/{projectId}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable String projectId){

        Project project1=projectService.updateProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);

    } */
}
