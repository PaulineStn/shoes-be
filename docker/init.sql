-- Créer la table 'users'
CREATE TABLE "users" (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Créer la table 'shoe'
CREATE TABLE "shoe" (
    shoe_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Créer la table 'order'
CREATE TABLE "orders" (
    order_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) DEFAULT 'pending',
    FOREIGN KEY (user_id) REFERENCES "users"(user_id) ON DELETE CASCADE
);

-- Créer la table 'reviews'
CREATE TABLE "reviews" (
    review_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    shoe_id INTEGER NOT NULL,
    rating INTEGER CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES "users"(user_id) ON DELETE CASCADE,
    FOREIGN KEY (shoe_id) REFERENCES "shoe"(shoe_id) ON DELETE CASCADE
);

-- Ajouter une table pivot 'order_shoe' pour gérer les relations commandes/chaussures
CREATE TABLE "order_shoe" (
    order_shoe_id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL,
    shoe_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    FOREIGN KEY (order_id) REFERENCES "orders"(order_id) ON DELETE CASCADE,
    FOREIGN KEY (shoe_id) REFERENCES "shoe"(shoe_id) ON DELETE CASCADE
);

-- Insérer des données initiales pour 'users'
INSERT INTO "users" (username, email, password) VALUES
('john_doe', 'john@example.com', 'hashed_password_123'),
('jane_smith', 'jane@example.com', 'hashed_password_456');

-- Insérer des données initiales pour 'shoe'
INSERT INTO "shoe" (name, description, price, stock) VALUES
('Running Shoes', 'Lightweight running shoes', 79.99, 50),
('Basketball Sneakers', 'High-top sneakers for basketball', 119.99, 30),
('Casual Loafers', 'Comfortable everyday loafers', 49.99, 100),
('Winter Boots', 'Durable boots for winter conditions', 129.99, 20);

-- Insérer des données initiales pour 'order'
INSERT INTO "orders" (user_id, total_price, status) VALUES
(1, 159.98, 'completed'),
(2, 49.99, 'pending');

-- Insérer des données initiales pour 'order_shoe'
INSERT INTO "order_shoe" (order_id, shoe_id, quantity) VALUES
(1, 1, 1), -- John Doe ordered 1 Running Shoe
(1, 2, 1), -- John Doe ordered 1 Basketball Sneaker
(2, 3, 1); -- Jane Smith ordered 1 Casual Loafer

-- Insérer des données initiales pour 'reviews'
INSERT INTO "reviews" (user_id, shoe_id, rating, comment) VALUES
(1, 1, 5, 'Excellent running shoes, very comfortable!'),
(1, 2, 4, 'Good sneakers, but a bit tight.'),
(2, 3, 5, 'Perfect for daily wear.');

