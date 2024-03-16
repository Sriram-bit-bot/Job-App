package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs=new ArrayList<>();
    JobRepository jobRepository;
    // Now the object will be injected by spring and for this reason we have a constructor
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobById(Long id){
        try{
            jobRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateJobById(Long id, Job job){
        Optional<Job> jobOptional =jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job currJob =jobOptional.get();
            currJob.setId(id);
            currJob.setDescription(job.getDescription());
            currJob.setLocation(job.getLocation());
            currJob.setTitle(job.getTitle());
            currJob.setMinSalary(job.getMinSalary());
            currJob.setMaxSalary(job.getMaxSalary());
            jobRepository.save(currJob);
            return false;
        }
        createJob(job);
        return true;
    }
}
