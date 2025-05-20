document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('.rating-input .star');
    let selectedRating = 0;

    stars.forEach(star => {
        star.addEventListener('click', function() {
            const value = parseInt(this.getAttribute('data-value'));
            selectedRating = value;
            
            stars.forEach((s, index) => {
                if (index < value) {
                    s.classList.add('active');
                    s.textContent = '★';
                } else {
                    s.classList.remove('active');
                    s.textContent = '☆';
                }
            });
        });
    });

    // Tag Selection
    const tags = document.querySelectorAll('.tag');
    tags.forEach(tag => {
        tag.addEventListener('click', function() {
            this.classList.toggle('selected');
        });
    });

    // Submit Review
    const submitBtn = document.getElementById('submit-review');
    submitBtn.addEventListener('click', async function() {
        const reviewText = document.getElementById('review-text').value;
        const isAnonymous = document.getElementById('anonymous').checked;
        
        if (selectedRating === 0) {
            alert('Please select a rating');
            return;
        }

        const selectedTags = Array.from(document.querySelectorAll('.tag.selected'))
            .map(tag => tag.getAttribute('data-tag'));

        const reviewData = {
            tutorId: 1, 
            studentId: 1, 
            rating: selectedRating,
            comment: reviewText,
            isAnonymous: isAnonymous,
            tags: selectedTags
        };

        try {
            const response = await fetch('/api/reviews', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(reviewData)
            });

            if (response.ok) {
                alert('Review submitted successfully!');
                location.reload();
            } else {
                alert('Failed to submit review');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('An error occurred');
        }
    });

    // Filter Reviews
    const sortSelect = document.getElementById('sort-reviews');
    sortSelect.addEventListener('change', function() {
        const sortBy = this.value;
        console.log('Sort by:', sortBy);
    });

    // Rating Filter
    const filterStars = document.querySelectorAll('.filter-star');
    filterStars.forEach(star => {
        star.addEventListener('click', function() {
            const rating = this.getAttribute('data-rating');
            console.log('Filter by rating:', rating);
        });
    });
});
