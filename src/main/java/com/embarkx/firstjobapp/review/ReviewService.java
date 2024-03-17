package com.embarkx.firstjobapp.review;

import com.embarkx.firstjobapp.job.Job;

import java.util.List;

public interface ReviewService {
    List<Review> findAllByCompany(Long companyId);
    void createReview(Long companyId, Review review);
    Review getReviewById(Long id);
    Boolean deleteReviewById(Long id);

    Boolean updateReviewById(Long id, Review review);
}
