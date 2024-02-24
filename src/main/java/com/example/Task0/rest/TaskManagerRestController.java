package com.example.Task0.rest;
import com.example.Task0.entity.Task;
import com.example.Task0.entity.User;
import com.example.Task0.logmessages.LogMessages;
import com.example.Task0.service.TaskService;
import com.example.Task0.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


//This Class is the Controller class the deal with the client layer(in our case web and postman)
@RestController
@RequestMapping("/TaskManager")
public class TaskManagerRestController {
    private UserService userService;
    private TaskService taskService;
    //Constructor dependency injection for user service and task service
    @Autowired
    public TaskManagerRestController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }
    //Get request for getting all users
    @GetMapping("/users")
    public List<User> findAllUsers(){
        LogMessages.logger.info("Users are showed");
        return userService.findAll();

    }
    //Get request for getting all tasks
    @GetMapping("/tasks")
    public List<Task> findAllTasks(){
        LogMessages.logger.info("Tasks are showed");
        return taskService.findAll();
    }
    //Post request for adding new user
    @PostMapping("/users")
    public User addUser(@RequestBody User theUser){

        LogMessages.logger.info("User with Id:"+theUser.getId()+" is inserted");

        return userService.save(theUser);

    }
    //Put request for update a user
    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser){
        if (userService.findById(theUser.getId())==null){
            LogMessages.logger.error("You try to update non existing id");
            throw new IdNotFoundError("Non existing id");}

        LogMessages.logger.info("User with Id:"+theUser.getId()+" is updated");

        return userService.save(theUser);
    }
    //Post request for add new task
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task theTask){


        if (userService.findById(theTask.getUser().getId())==null){
            LogMessages.logger.error("Non existing foreign key");
            throw new IdNotFoundError("Non existing foreign key");
        }
        LogMessages.logger.info("Task with Id:"+theTask.getUser().getId()+" is inserted");
        return taskService.save(theTask);

    }
    //Put request fot update a task
    @PutMapping("/tasks")
    public Task updateTask(@RequestBody Task theTask){
        if (taskService.findById(theTask.getId())==null){
            LogMessages.logger.error("You try to update non existing id");
            throw new IdNotFoundError("Non existing id");}
        else if (userService.findById(theTask.getUser().getId())==null){
            LogMessages.logger.error("non existing foreign key");
            throw new IdNotFoundError("Non existing foreign key");
        }
        LogMessages.logger.info("Task with Id:"+theTask.getId()+" is updated");
        return taskService.save(theTask);
    }
    //Delete request for deleting a user depending on its id
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        if (userService.findById(id)==null){
            LogMessages.logger.error("You try to delete non existing id");
            throw new IdNotFoundError("Non existing id");}
        userService.deleteUserById(id);
        LogMessages.logger.info("User with Id:"+id+" is deleted");
        return "User with Id:"+id+" is deleted";

    }
    //Delete request for deleting a task depending on its id
    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable int id){
        if (taskService.findById(id)==null){
            LogMessages.logger.error("You try to delete non existing id");
            throw new IdNotFoundError("Non existing id");}
        taskService.deleteUserById(id);
        LogMessages.logger.info("Task with Id:"+id+" is deleted");
        return "Task with Id:"+id+" is deleted";
    }


}
