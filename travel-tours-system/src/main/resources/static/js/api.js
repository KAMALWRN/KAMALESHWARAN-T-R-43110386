// API Configuration and Helper Functions

const API_BASE_URL = '/api';

// Get JWT token from localStorage
function getToken() {
    return localStorage.getItem('token');
}

// Set JWT token in localStorage
function setToken(token) {
    localStorage.setItem('token', token);
}

// Remove token from localStorage
function removeToken() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
}

// Get current user from localStorage
function getCurrentUser() {
    const userStr = localStorage.getItem('user');
    return userStr ? JSON.parse(userStr) : null;
}

// Set current user in localStorage
function setCurrentUser(user) {
    localStorage.setItem('user', JSON.stringify(user));
}

// Check if user is authenticated
function isAuthenticated() {
    return getToken() !== null;
}

// Check if user is admin
function isAdmin() {
    const user = getCurrentUser();
    return user && user.roles && user.roles.includes('ROLE_ADMIN');
}

// API Request Helper
async function apiRequest(url, options = {}) {
    const token = getToken();
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };
    
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}${url}`, {
            ...options,
            headers
        });
        
        if (response.status === 401) {
            // Unauthorized - logout user
            removeToken();
            window.location.href = 'login.html';
            return null;
        }
        
        const data = await response.json();
        return { data, status: response.status, ok: response.ok };
    } catch (error) {
        console.error('API Error:', error);
        return { error: error.message, ok: false };
    }
}

// Auth API
const authAPI = {
    async login(email, password) {
        return await apiRequest('/auth/signin', {
            method: 'POST',
            body: JSON.stringify({ email, password })
        });
    },
    
    async register(userData) {
        return await apiRequest('/auth/signup', {
            method: 'POST',
            body: JSON.stringify(userData)
        });
    }
};

// Categories API
const categoriesAPI = {
    async getAll() {
        return await apiRequest('/categories/public/all');
    }
};

// Packages API
const packagesAPI = {
    async getAll() {
        return await apiRequest('/packages/public/all');
    },
    
    async getById(id) {
        return await apiRequest(`/packages/public/${id}`);
    },
    
    async getByCategory(categoryId) {
        return await apiRequest(`/packages/public/category/${categoryId}`);
    }
};

// Bookings API
const bookingsAPI = {
    async create(bookingData) {
        return await apiRequest('/bookings', {
            method: 'POST',
            body: JSON.stringify(bookingData)
        });
    },
    
    async getMyBookings() {
        return await apiRequest('/bookings/my-bookings');
    },
    
    async getAll() {
        return await apiRequest('/bookings');
    },
    
    async updateStatus(id, status) {
        return await apiRequest(`/bookings/${id}/status?status=${status}`, {
            method: 'PUT'
        });
    }
};

// Enquiries API
const enquiriesAPI = {
    async create(enquiryData) {
        return await apiRequest('/enquiries/public/create', {
            method: 'POST',
            body: JSON.stringify(enquiryData)
        });
    },
    
    async getAll() {
        return await apiRequest('/enquiries');
    },
    
    async getUnread() {
        return await apiRequest('/enquiries/unread');
    },
    
    async markAsRead(id) {
        return await apiRequest(`/enquiries/${id}/read`, {
            method: 'PUT'
        });
    }
};

// Newsletter API
const newsletterAPI = {
    async subscribe(email) {
        return await apiRequest('/newsletter/subscribe', {
            method: 'POST',
            body: JSON.stringify({ email })
        });
    }
};

// Payments API
const paymentsAPI = {
    async create(bookingId) {
        return await apiRequest(`/payments/create/${bookingId}`, {
            method: 'POST'
        });
    },
    
    async process(paymentId, paymentMethod) {
        return await apiRequest(`/payments/process/${paymentId}?paymentMethod=${paymentMethod}`, {
            method: 'POST'
        });
    },
    
    async getByBookingId(bookingId) {
        return await apiRequest(`/payments/booking/${bookingId}`);
    },
    
    async getByPaymentId(paymentId) {
        return await apiRequest(`/payments/${paymentId}`);
    },
    
    async getAll() {
        return await apiRequest('/payments');
    }
};

// Show alert message
function showAlert(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    const container = document.querySelector('.container') || document.body;
    container.insertBefore(alertDiv, container.firstChild);
    
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

// Format currency
function formatCurrency(amount) {
    return new Intl.NumberFormat('en-IN', {
        style: 'currency',
        currency: 'INR'
    }).format(amount);
}

// Format date
function formatDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('en-IN', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });
}

