package com.embarkx.firstjobapp.job;

import com.embarkx.firstjobapp.job.impl.JobServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(name ="/jobs")
// Request Mapping annotation at class level will add this path(/jobs) to all handler methods
public class JobController {
    private JobService jobService;
    // Here, we are not initializing the jobService object, spring will inject the object here,
    // We just have to declare a constructor for it
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @GetMapping()
    public ResponseEntity<List<Job>> findAll(){
        List<Job> jobs = jobService.findAll();
//       return new ResponseEntity<>(new ArrayList<>(jobs),HttpStatus.OK);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job =jobService.getJobById(id);
        if(job == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        Boolean isSuccess =jobService.deleteJobById(id);
        if(!isSuccess){
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Job Deleted", HttpStatus.OK);
    }

//    @PutMapping("/jobs/{id}")
    @RequestMapping(value ="{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id,@RequestBody Job job){
        Boolean isCreated =jobService.updateJobById(id, job);
        if(isCreated){
            return new ResponseEntity<>("Job Created",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Job updated", HttpStatus.OK);
    }
}
