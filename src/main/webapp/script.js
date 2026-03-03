// Global Variables
let cart = [];
let currentUser = null;

// Initialize the app
document.addEventListener('DOMContentLoaded', function() {
    loadCartFromStorage();
    updateCartCount();
    initializeEventListeners();
    
    // Page-specific initializations
    if (document.getElementById('restaurantsGrid')) {
        initializeRestaurantPage();
    }
    
    if (document.getElementById('loginForm')) {
        initializeLoginPage();
    }
    
    if (document.getElementById('registerForm')) {
        initializeRegisterPage();
    }
    
    if (document.querySelector('.menu-grid')) {
        initializeMenuPage();
    }
    
    if (document.querySelector('.cart-items')) {
        initializeCartPage();
    }
});

// Event Listeners
function initializeEventListeners() {
    // Search functionality
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', handleSearch);
    }
    
    const menuSearch = document.getElementById('menuSearch');
    if (menuSearch) {
        menuSearch.addEventListener('input', handleMenuSearch);
    }
    
    // Password toggle
    const togglePassword = document.getElementById('togglePassword');
    if (togglePassword) {
        togglePassword.addEventListener('click', togglePasswordVisibility);
    }
    
    const toggleConfirmPassword = document.getElementById('toggleConfirmPassword');
    if (toggleConfirmPassword) {
        toggleConfirmPassword.addEventListener('click', togglePasswordVisibility);
    }
}

// Restaurant Page Functions
function initializeRestaurantPage() {
    const restaurantCards = document.querySelectorAll('.restaurant-card');
    restaurantCards.forEach(card => {
        card.addEventListener('click', function() {
            const restaurantName = this.querySelector('h3').textContent;
            goToMenu(restaurantName);
        });
    });
}

function handleSearch(event) {
    const searchTerm = event.target.value.toLowerCase();
    const restaurantCards = document.querySelectorAll('.restaurant-card');
    
    restaurantCards.forEach(card => {
        const restaurantName = card.querySelector('h3').textContent.toLowerCase();
        const cuisine = card.querySelector('.cuisine').textContent.toLowerCase();
        
        if (restaurantName.includes(searchTerm) || cuisine.includes(searchTerm)) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
}

function handleMenuSearch(event) {
    const searchTerm = event.target.value.toLowerCase();
    const menuItems = document.querySelectorAll('.menu-item');
    
    menuItems.forEach(item => {
        const itemName = item.querySelector('h3').textContent.toLowerCase();
        const description = item.querySelector('.description').textContent.toLowerCase();
        
        if (itemName.includes(searchTerm) || description.includes(searchTerm)) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function goToMenu(restaurantName) {
    // Store selected restaurant in session storage
    sessionStorage.setItem('selectedRestaurant', restaurantName);
    window.location.href = 'menu.html';
}

// Menu Page Functions
function initializeMenuPage() {
    const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function() {
            const itemName = this.dataset.item;
            const itemPrice = parseFloat(this.dataset.price);
            addToCart(itemName, itemPrice);
        });
    });
    
    // Load selected restaurant name
    const selectedRestaurant = sessionStorage.getItem('selectedRestaurant');
    if (selectedRestaurant) {
        const restaurantNameElement = document.getElementById('restaurantName');
        if (restaurantNameElement) {
            restaurantNameElement.textContent = selectedRestaurant;
        }
    }
}

// Cart Functions
function addToCart(itemName, itemPrice, quantity = 1) {
    const existingItem = cart.find(item => item.name === itemName);
    
    if (existingItem) {
        existingItem.quantity += quantity;
    } else {
        cart.push({
            id: Date.now(),
            name: itemName,
            price: itemPrice,
            quantity: quantity
        });
    }
    
    saveCartToStorage();
    updateCartCount();
    showNotification(`${itemName} added to cart!`);
}

function removeFromCart(itemId) {
    cart = cart.filter(item => item.id !== itemId);
    saveCartToStorage();
    updateCartCount();
    updateCartDisplay();
}

function updateQuantity(itemId, change) {
    const item = cart.find(item => item.id === itemId);
    if (item) {
        item.quantity += change;
        if (item.quantity <= 0) {
            removeFromCart(itemId);
        } else {
            saveCartToStorage();
            updateCartDisplay();
        }
    }
}

function updateCartCount() {
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    const cartCounts = document.querySelectorAll('.cart-count');
    cartCounts.forEach(count => {
        count.textContent = totalItems;
    });
}

function updateCartDisplay() {
    if (!document.querySelector('.cart-items')) return;
    
    const cartItemsContainer = document.querySelector('.cart-items');
    const emptyCart = document.getElementById('emptyCart');
    
    if (cart.length === 0) {
        cartItemsContainer.innerHTML = '';
        emptyCart.style.display = 'block';
        updateBillSummary();
        return;
    }
    
    emptyCart.style.display = 'none';
    
    let cartHTML = '';
    cart.forEach(item => {
        cartHTML += `
            <div class="cart-item" data-item-id="${item.id}">
                <div class="cart-item-image">
                    <img src="https://picsum.photos/seed/${item.name.replace(/\s+/g, '')}/150/150.jpg" alt="${item.name}">
                </div>
                <div class="cart-item-details">
                    <h3>${item.name}</h3>
                    <p class="item-description">Delicious food item</p>
                    <div class="item-price">₹${item.price.toFixed(2)}</div>
                </div>
                <div class="cart-item-actions">
                    <div class="quantity-control">
                        <button class="quantity-btn decrease" onclick="updateQuantity(${item.id}, -1)">
                            <i class="fas fa-minus"></i>
                        </button>
                        <span class="quantity" id="quantity-${item.id}">${item.quantity}</span>
                        <button class="quantity-btn increase" onclick="updateQuantity(${item.id}, 1)">
                            <i class="fas fa-plus"></i>
                        </button>
                    </div>
                    <button class="remove-btn" onclick="removeItem(${item.id})">
                        <i class="fas fa-trash"></i>
                        Remove
                    </button>
                </div>
            </div>
        `;
    });
    
    cartItemsContainer.innerHTML = cartHTML;
    updateBillSummary();
}

function updateBillSummary() {
    const subtotal = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    const gst = subtotal * 0.05;
    const delivery = cart.length > 0 ? 30 : 0;
    const total = subtotal + gst + delivery;
    
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    
    // Update summary elements
    const elements = {
        totalItems: totalItems,
        subtotalAmount: subtotal.toFixed(2),
        gstAmount: gst.toFixed(2),
        deliveryAmount: delivery.toFixed(2),
        totalAmount: total.toFixed(2),
        checkoutTotal: total.toFixed(2)
    };
    
    Object.keys(elements).forEach(key => {
        const element = document.getElementById(key);
        if (element) {
            element.textContent = elements[key];
        }
    });
}

// Cart Page Functions
function initializeCartPage() {
    updateCartDisplay();
}

function removeItem(itemId) {
    removeFromCart(itemId);
    showNotification('Item removed from cart');
}

function proceedToCheckout() {
    if (cart.length === 0) {
        showNotification('Your cart is empty!', 'error');
        return;
    }
    
    const modal = document.getElementById('checkoutModal');
    if (modal) {
        modal.classList.add('active');
    }
}

function closeModal() {
    const modal = document.getElementById('checkoutModal');
    if (modal) {
        modal.classList.remove('active');
    }
}

function goToRestaurants() {
    window.location.href = 'index.html';
}

// Authentication Functions
function initializeLoginPage() {
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', handleLogin);
    }
}

function initializeRegisterPage() {
    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', handleRegister);
    }
}

function handleLogin(event) {
    event.preventDefault();
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    // Simple validation (in real app, this would be server-side)
    if (email && password) {
        currentUser = { email, name: email.split('@')[0] };
        localStorage.setItem('currentUser', JSON.stringify(currentUser));
        showNotification('Login successful!');
        setTimeout(() => {
            window.location.href = 'index.html';
        }, 1000);
    }
}

function handleRegister(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    const password = formData.get('password');
    const confirmPassword = formData.get('confirmPassword');
    
    if (password !== confirmPassword) {
        showNotification('Passwords do not match!', 'error');
        return;
    }
    
    // Simple validation (in real app, this would be server-side)
    currentUser = {
        name: formData.get('fullName'),
        username: formData.get('username'),
        email: formData.get('email'),
        phone: formData.get('phone'),
        address: formData.get('address')
    };
    
    localStorage.setItem('currentUser', JSON.stringify(currentUser));
    showNotification('Registration successful!');
    setTimeout(() => {
        window.location.href = 'index.html';
    }, 1000);
}

// Utility Functions
function togglePasswordVisibility(event) {
    const button = event.currentTarget;
    const input = button.previousElementSibling;
    const icon = button.querySelector('i');
    
    if (input.type === 'password') {
        input.type = 'text';
        icon.classList.remove('fa-eye');
        icon.classList.add('fa-eye-slash');
    } else {
        input.type = 'password';
        icon.classList.remove('fa-eye-slash');
        icon.classList.add('fa-eye');
    }
}

function showNotification(message, type = 'success') {
    // Create notification element
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    
    // Style the notification
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: ${type === 'success' ? '#48c479' : '#e23744'};
        color: white;
        padding: 1rem 1.5rem;
        border-radius: 8px;
        box-shadow: 0 4px 20px rgba(0,0,0,0.2);
        z-index: 10000;
        transform: translateX(100%);
        transition: transform 0.3s ease;
    `;
    
    document.body.appendChild(notification);
    
    // Animate in
    setTimeout(() => {
        notification.style.transform = 'translateX(0)';
    }, 100);
    
    // Remove after 3 seconds
    setTimeout(() => {
        notification.style.transform = 'translateX(100%)';
        setTimeout(() => {
            document.body.removeChild(notification);
        }, 300);
    }, 3000);
}

function applyPromo() {
    const promoCode = document.getElementById('promoCode').value.trim();
    
    if (!promoCode) {
        showNotification('Please enter a promo code', 'error');
        return;
    }
    
    // Simple promo code validation (in real app, this would be server-side)
    const validPromos = {
        'SAVE10': 0.1,
        'SAVE20': 0.2,
        'FIRST50': 50
    };
    
    if (validPromos[promoCode]) {
        const discount = validPromos[promoCode];
        showNotification(`Promo code applied! You saved ${discount * 100}%`, 'success');
        // Update the bill summary with discount
        updateBillSummaryWithDiscount(discount);
    } else {
        showNotification('Invalid promo code', 'error');
    }
}

function updateBillSummaryWithDiscount(discount) {
    const subtotal = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    const gst = subtotal * 0.05;
    const delivery = cart.length > 0 ? 30 : 0;
    
    let discountAmount = 0;
    if (discount < 1) {
        discountAmount = subtotal * discount;
    } else {
        discountAmount = discount;
    }
    
    const total = subtotal + gst + delivery - discountAmount;
    
    // Update summary elements
    const elements = {
        subtotalAmount: subtotal.toFixed(2),
        gstAmount: gst.toFixed(2),
        deliveryAmount: delivery.toFixed(2),
        totalAmount: total.toFixed(2),
        checkoutTotal: total.toFixed(2)
    };
    
    Object.keys(elements).forEach(key => {
        const element = document.getElementById(key);
        if (element) {
            element.textContent = elements[key];
        }
    });
}

// Storage Functions
function saveCartToStorage() {
    localStorage.setItem('cart', JSON.stringify(cart));
}

function loadCartFromStorage() {
    const savedCart = localStorage.getItem('cart');
    if (savedCart) {
        cart = JSON.parse(savedCart);
    }
    
    const savedUser = localStorage.getItem('currentUser');
    if (savedUser) {
        currentUser = JSON.parse(savedUser);
    }
}

// Checkout Form Handler
document.addEventListener('DOMContentLoaded', function() {
    const checkoutForm = document.getElementById('checkoutForm');
    if (checkoutForm) {
        checkoutForm.addEventListener('submit', function(event) {
            event.preventDefault();
            
            const address = document.getElementById('deliveryAddress').value;
            const deliveryTime = document.getElementById('deliveryTime').value;
            const paymentMethod = document.getElementById('paymentMethod').value;
            const specialInstructions = document.getElementById('specialInstructions').value;
            
            // Process order (in real app, this would be sent to server)
            const order = {
                items: cart,
                address: address,
                deliveryTime: deliveryTime,
                paymentMethod: paymentMethod,
                specialInstructions: specialInstructions,
                total: document.getElementById('totalAmount').textContent,
                timestamp: new Date().toISOString()
            };
            
            // Clear cart and show success message
            cart = [];
            saveCartToStorage();
            updateCartCount();
            
            showNotification('Order placed successfully! 🎉');
            closeModal();
            
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 2000);
        });
    }
    
    // Close modal when clicking outside
    const modal = document.getElementById('checkoutModal');
    if (modal) {
        modal.addEventListener('click', function(event) {
            if (event.target === modal) {
                closeModal();
            }
        });
    }
});

// Social login handlers (placeholder functions)
function handleSocialLogin(provider) {
    showNotification(`${provider} login coming soon!`, 'info');
}

// Add social login event listeners
document.addEventListener('DOMContentLoaded', function() {
    const socialButtons = document.querySelectorAll('.social-btn');
    socialButtons.forEach(button => {
        button.addEventListener('click', function() {
            if (this.classList.contains('google')) {
                handleSocialLogin('Google');
            } else if (this.classList.contains('facebook')) {
                handleSocialLogin('Facebook');
            }
        });
    });
});
