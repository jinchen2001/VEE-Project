package com.example.VEEProject.server;

import com.example.VEEProject.mapper.Project;
import com.example.VEEProject.mapper.ProjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
@RestController
public class DBServer {
    ProjectRepository projectRepo;
    public static void main(String args[]){
        SpringApplication.run(DBServer.class,args);
    }
    @RequestMapping("/addNewProject")
    public String addNewProject(@RequestParam("name") String projectName) {
        Project freshProject = new Project(projectName);
        projectRepo.save(freshProject);
        return "OK";
    }
    @RequestMapping("/getAllProjects")
    public String getAllProjects() {
        String response = "";
        Iterable<Project> projects = projectRepo.findAll();
        for(Project proj: projects) {
            response += proj.getProjectName() + " " + proj.getId() + "<br>\n";
        }
        return response;
    }

    @RequestMapping("/getProjectName")
    public String getProjectName(@RequestParam("id") Long projectID) {
        Optional<Project> result = projectRepo.findById(projectID);
        if(result.isPresent()) return result.get().getProjectName();
        else return "Unknown Project";
    }

    @RequestMapping("/renameProject")
    public String renameName(@RequestParam("id") Long projectID,
                             @RequestParam("name") String newName) {
        Optional<Project> result = projectRepo.findById(projectID);
        if(result.isPresent()) {
            Project existingProject = result.get();
            existingProject.setProjectName(newName);
            projectRepo.save(existingProject);
            return "OK";
        }
        else return "Unknown Project";
    }

    @RequestMapping("/deleteProject")
    public String deleteProject(@RequestParam("id") Long projectID) {
        if(projectRepo.existsById(projectID)) {
            projectRepo.deleteById(projectID);
            return projectID + " deleted";
        }
        else return projectID + " not found";
    }

}
