@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private TutorRepository tutorRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    public List<Review> getReviewsByTutor(Long tutorId) {
        return reviewRepository.findByTutorId(tutorId);
    }
    
    public Review submitReview(ReviewRequest reviewRequest) {
        Tutor tutor = tutorRepository.findById(reviewRequest.getTutorId())
            .orElseThrow(() -> new RuntimeException("Tutor not found"));
        
        Student student = studentRepository.findById(reviewRequest.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student not found"));
        
        Review review = new Review();
        review.setTutor(tutor);
        review.setStudent(student);
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setAnonymous(reviewRequest.isAnonymous());
        review.setCreatedAt(LocalDateTime.now());
        
        return reviewRepository.save(review);
    }
    
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}