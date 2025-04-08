// ======================
// Global Variables
// ======================
let currentUser = null;
const API_BASE_URL = 'http://localhost:8080/HomeTutorSystem';

// ======================
// DOM Ready
// ======================
document.addEventListener('DOMContentLoaded', function() {
    checkLoginStatus();
    setupEventListeners();
});

// ======================
// Check Login Status
// ======================
function checkLoginStatus() {
    const token = localStorage.getItem('authToken') || null;
    if (token && token !== 'undefined') {
        try {
            currentUser = JSON.parse(localStorage.getItem('user'));
            if (currentUser) {
                updateUIForLoggedInUser();
                return;
            }
        } catch (e) {
            console.error('Error parsing user data: ', e);
        }
    }
        redirectToLogin();
}

// ======================
// Event Listeners
// ======================
function setupEventListeners() {
    // Login Form
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', handleLogin);
    }

    // Register Form
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', handleRegister);
    }

    // Tutor Registration Form
    const tutorForm = document.getElementById('tutorForm');
    if (tutorForm) {
        tutorForm.addEventListener('submit', handleTutorRegistration);
    }

    // Logout Button
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', handleLogout);
    }

    // Search Tutors Form
    const searchForm = document.getElementById('searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', handleTutorSearch);
    }
}

// ======================
// Authentication Handlers
// ======================
async function handleLogin(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());

    try {
        const response = await fetch(`${API_BASE_URL}/user?action=login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            localStorage.setItem('authToken', result.token);
            localStorage.setItem('user', JSON.stringify(result.user));
            currentUser = result.user;
            window.location.href = 'dashboard.html';
        } else {
            showError('Invalid username or password');
        }
    } catch (error) {
        showError('Login failed. Please try again.');
    }
}

async function handleRegister(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());

    try {
        const response = await fetch(`${API_BASE_URL}/user?action=register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Registration successful! Please login.');
            window.location.href = 'login.html';
        } else {
            showError('Registration failed. Username may already exist.');
        }
    } catch (error) {
        showError('Registration error. Please try again.');
    }
}

function handleLogout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
    currentUser = null;
    redirectToLogin();
}

// ======================
// Tutor Registration
// ======================
async function handleTutorRegistration(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const availability = Array.from(document.querySelectorAll('input[name="availability"]:checked'))
        .map(el => el.value)
        .join('|');

    const data = {
        name: formData.get('name'),
        address: formData.get('address'),
        phone: formData.get('phone'),
        grades: formData.get('grades'),
        experience: formData.get('experience'),
        subjects: formData.get('subjects'),
        availability: availability,
        username: currentUser.username
    };

    try {
        const response = await fetch(`${API_BASE_URL}/tutor?action=register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Tutor registration successful!');
            window.location.href = 'dashboard.html';
        } else {
            showError('Tutor registration failed.');
        }
    } catch (error) {
        showError('Server error. Please try again.');
    }
}

// ======================
// Tutor Search
// ======================
async function handleTutorSearch(e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const filters = Object.fromEntries(formData.entries());

    try {
        const response = await fetch(`${API_BASE_URL}/tutor?subject=${filters.subject}&grade=${filters.grade}`);
        if (response.ok) {
            const tutors = await response.json();
            displayTutors(tutors);
        } else {
            showError('No tutors found.');
        }
    } catch (error) {
        showError('Search failed. Please try again.');
    }
}

function displayTutors(tutors) {
    const container = document.getElementById('tutors-container');
    container.innerHTML = '';

    tutors.forEach(tutor => {
        const tutorCard = `
            <div class="tutor-card">
                <h3>${tutor.name}</h3>
                <p><strong>Subjects:</strong> ${tutor.subjects}</p>
                <p><strong>Grades:</strong> ${tutor.grades}</p>
                <p><strong>Availability:</strong> ${tutor.availability}</p>
                <button onclick="bookTutor('${tutor.username}')">Book Now</button>
            </div>
        `;
        container.innerHTML += tutorCard;
    });
}

// ======================
// Booking Functionality
// ======================
async function bookTutor(tutorUsername) {
    if (!currentUser) {
        redirectToLogin();
        return;
    }

    const bookingData = {
        studentUsername: currentUser.username,
        tutorUsername: tutorUsername,
        date: new Date().toISOString()
    };

    try {
        const response = await fetch(`${API_BASE_URL}/booking`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(bookingData)
        });

        if (response.ok) {
            alert('Booking successful!');
        } else {
            showError('Booking failed. Please try again.');
        }
    } catch (error) {
        showError('Server error during booking.');
    }
}

// ======================
// UI Helpers
// ======================
function updateUIForLoggedInUser() {
    const usernameDisplay = document.getElementById('username-display');
    const tutorStatus = document.getElementById('tutor-status');
    const becomeTutorSection = document.getElementById('become-tutor-section');
    const tutorDashboard = document.getElementById('tutor-dashboard');

    if (usernameDisplay && currentUser && currentUser.username) {
        usernameDisplay.textContent = currentUser.username;
    }
    const isTutor = Boolean(currentUser?.isTutor);


    if (isTutor) {
        if (tutorStatus) tutorStatus.textContent = '(Tutor Account)';
        if (becomeTutorSection) becomeTutorSection.classList.add('hidden');
        if (tutorDashboard) tutorDashboard.classList.remove('hidden');
    } else {
        if (becomeTutorSection) becomeTutorSection.classList.remove('hidden');
        if (tutorDashboard) tutorDashboard.classList.add('hidden');
    }
}

function showError(message) {
    const errorElement = document.getElementById('error-message');
    if (errorElement) {
        errorElement.textContent = message;
        errorElement.style.display = 'block';
        setTimeout(() => errorElement.style.display = 'none', 5000);
    } else {
        alert(message);
    }
}

function redirectToLogin() {
    if (!window.location.href.includes('login.html')) {
        window.location.href = 'login.html';
    }
}