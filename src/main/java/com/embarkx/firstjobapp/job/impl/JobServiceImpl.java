package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs=new ArrayList<>();
    private Long jobId =1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(jobId);
        jobs.add(job);
        jobId+=1;
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job: jobs){
            if((id).equals(job.getId())){
                return job;
            }
        }
        return null;
    }
}
