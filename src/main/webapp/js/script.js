document.addEventListener('DOMContentLoaded', function() {
    // Check if we're on the profile page
    if (document.getElementById('profileView')) {
        // Get URL parameters
        const urlParams = new URLSearchParams(window.location.search);
        const action = urlParams.get('action');
        const tutorId = urlParams.get('id');

        // Initialize tutor data (in a real app, this would come from the server)
        let tutorData = {};

        // If we're editing an existing profile, load the data
        if (action === 'edit' && tutorId) {
            loadTutorProfile(tutorId);
        }

        // If we're creating a new profile, show the edit form
        if (action === 'create') {
            document.getElementById('profileView').style.display = 'none';
            document.getElementById('profileEdit').style.display = 'block';
            document.getElementById('action').value = 'create';
        }

        // Set up event listeners
        document.getElementById('enableEdit').addEventListener('click', function() {
            document.getElementById('profileView').style.display = 'none';
            document.getElementById('profileEdit').style.display = 'block';
        });

        document.getElementById('cancelEdit').addEventListener('click', function() {
            document.getElementById('profileView').style.display = 'block';
            document.getElementById('profileEdit').style.display = 'none';
        });

        document.getElementById('deleteProfile').addEventListener('click', function() {
            if (confirm('Are you sure you want to delete your profile? This cannot be undone.')) {
                document.getElementById('action').value = 'delete';
                document.getElementById('profileForm').submit();
            }
        });

        document.getElementById('logout').addEventListener('click', function() {
            // In a real app, you would call a logout API
            window.location.href = 'tutor-login.html';
        });
    }

    // Login form validation
    if (document.getElementById('loginForm')) {
        document.getElementById('loginForm').addEventListener('submit', function(e) {
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            if (!email || !password) {
                e.preventDefault();
                alert('Please fill in all fields');
            }
        });
    }
});

function loadTutorProfile(tutorId) {
    // In a real application, this would be an AJAX call to the server
    // For this example, we'll simulate loading data

    // Simulated tutor data
    const simulatedTutorData = {
        id: tutorId,
        name: "John Doe",
        address: "123 Tutor Street, Education City",
        phone: "555-123-4567",
        subjects: "Mathematics, Physics, Calculus",
        grades: "9-12, College",
        experience: "5",
        hourlyRate: "45.00",
        availability: "Monday 3pm-7pm, Wednesday 3pm-7pm, Saturday 10am-2pm",
        bio: "Experienced math tutor with a passion for helping students understand complex concepts."
    };

    // Populate view mode
    document.getElementById('viewName').textContent = simulatedTutorData.name;
    document.getElementById('viewAddress').textContent = simulatedTutorData.address;
    document.getElementById('viewPhone').textContent = simulatedTutorData.phone;
    document.getElementById('viewSubjects').textContent = simulatedTutorData.subjects;
    document.getElementById('viewGrades').textContent = simulatedTutorData.grades;
    document.getElementById('viewExperience').textContent = simulatedTutorData.experience;
    document.getElementById('viewRate').textContent = simulatedTutorData.hourlyRate;
    document.getElementById('viewAvailability').textContent = simulatedTutorData.availability;

    // Populate edit form
    document.getElementById('tutorId').value = simulatedTutorData.id;
    document.getElementById('name').value = simulatedTutorData.name;
    document.getElementById('address').value = simulatedTutorData.address;
    document.getElementById('phone').value = simulatedTutorData.phone;
    document.getElementById('subjects').value = simulatedTutorData.subjects;
    document.getElementById('grades').value = simulatedTutorData.grades;
    document.getElementById('experience').value = simulatedTutorData.experience;
    document.getElementById('hourlyRate').value = simulatedTutorData.hourlyRate;
    document.getElementById('bio').value = simulatedTutorData.bio;

    // Parse availability (simplified for this example)
    // In a real app, you would properly parse the availability string
}