CREATE TABLE users (
    id VARCHAR(255) NOT NULL,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    email VARCHAR(255) UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    created_by VARCHAR(255),
    last_modified_by VARCHAR(255),
    PRIMARY KEY (id)
);

-- Inserting data for the users table
INSERT INTO
    users (
        id,
        first_name,
        last_name,
        email,
        password,
        role,
        created_date,
        last_modified_date,
        created_by,
        last_modified_by
    )
VALUES
    (
        '1',
        'Said',
        'BENDRIOUE',
        'said.bendrioue@gmail.com',
        '$2a$10$uc/s52rhIvQ92vNWKV5AGOwn5fBQe2zZ.rcT2kN4qFoOGzlyxQbdC',
        'USER',
        '2023-08-01 10:00:00',
        '2023-08-01 10:00:00',
        'system',
        'system'
    );

CREATE TABLE restaurants (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    description TEXT,
    type VARCHAR(255),
    image_url VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    address VARCHAR(255),
    owner_id VARCHAR(255),
    created_date TIMESTAMP,
    created_by VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE menus (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    description TEXT,
    template_id TEXT,
    image_url VARCHAR(255),
    restaurant_id VARCHAR(255),
    created_date DATE,
    created_by VARCHAR(255),
    last_modified_date DATE,
    last_modified_by VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- -- Inserting data for the restaurant table
INSERT INTO
    restaurants (
        id,
        name,
        description,
        type,
        image_url,
        email,
        phone,
        address,
        owner_id,
        created_date,
        created_by,
        last_modified_date,
        last_modified_by
    )
VALUES
    (
        '3ac2b39ad528f8c8c5dc77c59abb683d',
        'Delicious Eats',
        'A cozy restaurant serving a variety of mouthwatering dishes.',
        'Casual Dining',
        'http://localhost:8080/api/files/3ac2b39ad528f8c8c5dc77c59abb683d.jpg',
        'info@deliciouseats.com',
        '555-123-4567',
        '123 Main St, Cityville',
        '1',
        '2023-08-26 10:00:00',
        'admin',
        '2023-08-26 10:00:00',
        'admin'
    );

-- -- Inserting data for the menus table
INSERT INTO
    menus (
        id,
        name,
        description,
        template_id,
        image_url,
        restaurant_id,
        created_date,
        created_by,
        last_modified_date,
        last_modified_by
    )
VALUES
    (
        '0a3760fca29d0807d67c824b875fff05',
        'Menu 1',
        'Description for Menu 1',
        '1',
        'http://localhost:8080/api/files/0a3760fca29d0807d67c824b875fff05.jpeg',
        '3ac2b39ad528f8c8c5dc77c59abb683d',
        '2023-08-27',
        'user_1',
        '2023-08-27',
        'user_1'
    );