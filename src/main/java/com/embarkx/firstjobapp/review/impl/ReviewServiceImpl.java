package com.embarkx.firstjobapp.review.impl;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.Review;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyRepository companyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Review> findAllByCompany(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public void createReview(Long companyId, Review review) {
        Optional<Company> companyOptional =companyRepository.findById(companyId);
        review.setCompany(companyOptional.get());
        reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteReviewById(Long id){
        try{
            reviewRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Boolean updateReviewById(Long id, Review review){
        Optional<Review> reviewOptional =reviewRepository.findById(id);
        if(reviewOptional.isPresent()){
            Review currReview =reviewOptional.get();
            currReview.setReviewText(review.getReviewText());
            reviewRepository.save(currReview);
            return false;
        }
        createReview(id, reviewOptional.get());
        return true;
    }
}
