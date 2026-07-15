const API_URL = 'http://localhost:8080/api';

// State Management
const State = {
    token: localStorage.getItem('token'),
    user: JSON.parse(localStorage.getItem('user')),

    isLoggedIn() {
        return !!this.token;
    },

    hasRole(role) {
        return this.user && this.user.role === role;
    },

    logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        window.location.href = 'index.html';
    }
};

// API Helper
const api = {
    async request(endpoint, options = {}) {
        const headers = {
            'Content-Type': 'application/json',
            ...(State.token ? { 'Authorization': `Bearer ${State.token}` } : {})
        };

        const config = {
            ...options,
            headers: {
                ...headers,
                ...options.headers
            }
        };

        try {
            const response = await fetch(`${API_URL}${endpoint}`, config);
            if (response.status === 401) {
                State.logout(); // Auto logout on 401
                return null;
            }
            if (!response.ok) {
                // Safely try to parse the error body - it might be JSON or plain text
                const text = await response.text();
                let errorMessage = 'Something went wrong';
                try {
                    const json = JSON.parse(text);
                    errorMessage = json.message || json.error || text;
                } catch (_) {
                    errorMessage = text || errorMessage;
                }
                throw new Error(errorMessage);
            }
            // Some requests might not return content (like Void)
            const text = await response.text();
            if (!text) return {};
            try {
                return JSON.parse(text);
            } catch (_) {
                return { message: text };
            }
        } catch (error) {
            console.error('API Error:', error);
            showToast(error.message, 'error');
            throw error;
        }
    },

    get(endpoint) {
        return this.request(endpoint, { method: 'GET' });
    },

    post(endpoint, body) {
        return this.request(endpoint, { method: 'POST', body: JSON.stringify(body) });
    },

    put(endpoint, body) {
        return this.request(endpoint, { method: 'PUT', body: JSON.stringify(body) });
    },

    uploadFile(endpoint, file) {
        const formData = new FormData();
        formData.append('file', file);
        return fetch(`${API_URL}${endpoint}`, {
            method: 'POST',
            headers: {
                ...(State.token ? { 'Authorization': `Bearer ${State.token}` } : {})
            },
            body: formData
        }).then(res => res.json());
    }
};

// UI Helpers
function showToast(message, type = 'info') {
    const toast = document.createElement('div');
    toast.className = 'toast';
    toast.style.display = 'block';
    toast.style.backgroundColor = type === 'error' ? 'var(--danger)' : 'var(--dark)';
    toast.textContent = message;
    document.body.appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 3000);
}

function updateNav() {
    const navLinks = document.getElementById('nav-links');
    if (!navLinks) return;

    if (State.isLoggedIn()) {
        let dashboardLink = '';
        if (State.hasRole('ADMIN')) dashboardLink = '<li><a href="admin-dashboard.html">Admin</a></li>';
        else if (State.hasRole('ORPHANAGE')) dashboardLink = '<li><a href="orphanage-dashboard.html">My Orphanage</a></li>';
        else dashboardLink = '<li><a href="donor-dashboard.html">Dashboard</a></li><li><a href="donor-profile.html">Profile</a></li>';

        navLinks.innerHTML = `
            <li><a href="index.html">Home</a></li>
            <li><a href="orphanages.html">Orphanages</a></li>
            ${dashboardLink}
            <li><a href="#" onclick="State.logout(); return false;">Logout</a></li>
        `;
    } else {
        navLinks.innerHTML = `
            <li><a href="index.html">Home</a></li>
            <li><a href="orphanages.html">Find Orphanages</a></li>
            <li><a href="login.html" class="btn btn-outline">Login</a></li>
            <li><a href="register.html" class="btn btn-primary">Sign Up</a></li>
        `;
    }
}

// Init
document.addEventListener('DOMContentLoaded', updateNav);
