document.addEventListener('DOMContentLoaded', async function () {
    if (!isAuthenticated()) {
        window.location.href = 'login.html';
        return;
    }

    await loadMyBookings();
});

async function loadMyBookings() {
    const container = document.getElementById('myBookingsContainer');
    const response = await bookingsAPI.getMyBookings();

    if (!response || !response.ok) {
        container.innerHTML = '<div class="alert alert-danger">Failed to load bookings.</div>';
        return;
    }

    const bookings = response.data;
    if (!bookings.length) {
        container.innerHTML = '<p class="text-muted">You have no bookings yet.</p>';
        return;
    }

    let html = `
        <div class="table-container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Booking #</th>
                        <th>Package</th>
                        <th>Travelers</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Booked On</th>
                    </tr>
                </thead>
                <tbody>
    `;

    bookings.forEach(b => {
        html += `
            <tr>
                <td>${b.bookingNumber}</td>
                <td>${b.tourPackageTitle}</td>
                <td>${b.numberOfTravelers}</td>
                <td>${formatCurrency(b.totalAmount)}</td>
                <td><span class="badge status-${b.status.toLowerCase()}">${b.status}</span></td>
                <td>${formatDate(b.bookingDate)}</td>
            </tr>
        `;
    });

    html += '</tbody></table></div>';
    container.innerHTML = html;
}