// Home Page JavaScript

document.addEventListener('DOMContentLoaded', function() {
    checkAuthStatus();
    loadCategories();
    loadPackages();
    setupNewsletter();
    setupLogout();
});

// Check authentication status and update navigation
function checkAuthStatus() {
    const user = getCurrentUser();
    const loginNav = document.getElementById('loginNav');
    const registerNav = document.getElementById('registerNav');
    const userNav = document.getElementById('userNav');
    const userName = document.getElementById('userName');
    const adminLink = document.getElementById('adminLink');
    
    if (isAuthenticated() && user) {
        loginNav.style.display = 'none';
        registerNav.style.display = 'none';
        userNav.style.display = 'block';
        userName.textContent = user.firstName || user.email;
        
        if (isAdmin()) {
            adminLink.style.display = 'block';
        }
    } else {
        loginNav.style.display = 'block';
        registerNav.style.display = 'block';
        userNav.style.display = 'none';
    }
}

// Load categories
async function loadCategories() {
    const container = document.getElementById('categoriesContainer');
    container.innerHTML = '<div class="col-12 text-center"><div class="spinner-border text-primary"></div></div>';
    
    const response = await categoriesAPI.getAll();
    
    if (response && response.ok) {
        const categories = response.data;
        container.innerHTML = '';
        
        categories.forEach(category => {
            const col = document.createElement('div');
            col.className = 'col-md-3 col-sm-6 mb-4';
            col.innerHTML = `
                <div class="card category-card h-100" onclick="window.location.href='packages.html?category=${category.id}'">
                    <img src="${category.imageUrl || 'https://via.placeholder.com/300'}" class="card-img-top" alt="${category.name}">
                    <div class="card-body">
                        <h5 class="card-title">${category.name}</h5>
                        <p class="card-text text-muted">${category.description || ''}</p>
                        <small class="text-muted">${category.tourCount || 0} packages</small>
                    </div>
                </div>
            `;
            container.appendChild(col);
        });
    } else {
        container.innerHTML = '<div class="col-12 text-center text-danger">Failed to load categories</div>';
    }
}

// Load featured packages
async function loadPackages() {
    const container = document.getElementById('packagesContainer');
    container.innerHTML = '<div class="col-12 text-center"><div class="spinner-border text-primary"></div></div>';
    
    const response = await packagesAPI.getAll();
    
    if (response && response.ok) {
        const packages = response.data.slice(0, 6); // Show only 6 featured packages
        container.innerHTML = '';
        
        packages.forEach(pkg => {
            const col = document.createElement('div');
            col.className = 'col-md-4 col-sm-6 mb-4';
            const price = pkg.discountPrice && pkg.discountPrice > 0 ? pkg.discountPrice : pkg.price;
            const originalPrice = pkg.discountPrice && pkg.discountPrice > 0 ? pkg.price : null;
            
            col.innerHTML = `
                <div class="card package-card h-100">
                    <img src="${pkg.mainImageUrl || 'https://via.placeholder.com/400'}" class="card-img-top" alt="${pkg.title}">
                    <div class="card-body">
                        <h5 class="card-title">${pkg.title}</h5>
                        <p class="card-text text-muted">
                            <i class="fas fa-map-marker-alt"></i> ${pkg.destination || 'N/A'}
                            <br>
                            <i class="fas fa-calendar"></i> ${pkg.duration} days
                        </p>
                        <div class="mb-3">
                            ${originalPrice ? `<span class="package-discount">${formatCurrency(originalPrice)}</span> ` : ''}
                            <span class="package-price">${formatCurrency(price)}</span>
                        </div>
                        <a href="package-details.html?id=${pkg.id}" class="btn btn-primary w-100">View Details</a>
                    </div>
                </div>
            `;
            container.appendChild(col);
        });
    } else {
        container.innerHTML = '<div class="col-12 text-center text-danger">Failed to load packages</div>';
    }
}

// Setup newsletter subscription
function setupNewsletter() {
    const subscribeBtn = document.getElementById('subscribeBtn');
    const emailInput = document.getElementById('newsletterEmail');
    const messageDiv = document.getElementById('newsletterMessage');
    
    subscribeBtn.addEventListener('click', async function() {
        const email = emailInput.value.trim();
        
        if (!email) {
            messageDiv.innerHTML = '<div class="alert alert-warning">Please enter your email</div>';
            return;
        }
        
        if (!email.includes('@')) {
            messageDiv.innerHTML = '<div class="alert alert-warning">Please enter a valid email</div>';
            return;
        }
        
        subscribeBtn.disabled = true;
        subscribeBtn.innerHTML = '<span class="spinner-border spinner-border-sm"></span> Subscribing...';
        
        const response = await newsletterAPI.subscribe(email);
        
        if (response && response.ok) {
            messageDiv.innerHTML = '<div class="alert alert-success">Successfully subscribed to newsletter!</div>';
            emailInput.value = '';
        } else {
            const errorMsg = response?.data?.message || 'Failed to subscribe. Please try again.';
            messageDiv.innerHTML = `<div class="alert alert-danger">${errorMsg}</div>`;
        }
        
        subscribeBtn.disabled = false;
        subscribeBtn.innerHTML = '<i class="fas fa-paper-plane"></i> Subscribe';
    });
}

// Setup logout
function setupLogout() {
    const logoutBtn = document.getElementById('logoutBtn');
    const myBookingsLink = document.getElementById('myBookingsLink');
    const adminLink = document.getElementById('adminLink');
    
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function() {
            removeToken();
            window.location.href = 'index.html';
        });
    }
    
	if (myBookingsLink) {
	    myBookingsLink.addEventListener('click', function(e) {
	        e.preventDefault();
	        window.location.href = 'my-bookings.html';
	    });
	}
    
    if (adminLink) {
        adminLink.addEventListener('click', function(e) {
            e.preventDefault();
            window.location.href = 'admin-dashboard.html';
        });
    }
}

