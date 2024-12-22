CREATE TABLE IF NOT EXISTS merchant (
    `id` BINARY(16) NOT NULL UNIQUE,
    `user_id` BINARY(16) NOT NULL UNIQUE,
    `name` VARCHAR(64) NOT NULL,
    `discount` DECIMAL(64) NOT NULL,
    image_url VARCHAR(256),
    merchant_type VARCHAR(64, 2),
    created_by VARCHAR(255),
    creation_date DATETIME,
    last_modified_by VARCHAR(255),
    last_modified_date DATETIME,

    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;