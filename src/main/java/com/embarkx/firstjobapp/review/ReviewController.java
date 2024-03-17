package com.embarkx.firstjobapp.review;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.review.impl.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    private ReviewServiceImpl reviewServiceImpl;

    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }
    @GetMapping
    public ResponseEntity<List<Review>> findAllForCompany(@PathVariable Long companyId){
        List<Review> reviews = reviewServiceImpl.findAllByCompany(companyId);
//       return new ResponseEntity<>(new ArrayList<>(jobs),HttpStatus.OK);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Review review, @PathVariable Long companyId){
        reviewServiceImpl.createReview(companyId, review);
        return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> findReview(@PathVariable Long reviewId){
        Review review = reviewServiceImpl.getReviewById(reviewId);
//       return new ResponseEntity<>(new ArrayList<>(jobs),HttpStatus.OK);
        return ResponseEntity.ok(review);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Review> getJobById(@PathVariable Long id){
//        Review review =reviewServiceImpl.getReviewById(id);
//        if(review == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(review, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteReviewById(@PathVariable Long id){
//        Boolean isSuccess =reviewServiceImpl.deleteReviewById(id);
//        if(!isSuccess){
//            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>("Job Deleted", HttpStatus.OK);
//    }
//
    @RequestMapping(value ="/{reviewId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId,@RequestBody Review review){
        Boolean isCreated =reviewServiceImpl.updateReviewById(reviewId, review);
        if(isCreated){
            return new ResponseEntity<>("Review Created",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Review updated", HttpStatus.OK);
    }
}
