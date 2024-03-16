package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public Boolean deleteJobById(Long id){
        Iterator<Job> iterator =jobs.iterator();
        while(iterator.hasNext()){
            Job job =iterator.next();
            if(job.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updateJobById(Long id, Job job){
        for(Job currJob : jobs) {
            if (currJob.getId().equals(id)) {
                currJob.setId(id);
                currJob.setDescription(job.getDescription());
                currJob.setLocation(job.getLocation());
                currJob.setTitle(job.getTitle());
                currJob.setMinSalary(job.getMinSalary());
                currJob.setMaxSalary(job.getMaxSalary());
                return false;
            }
        }
        createJob(job);
        return true;
    }
}
