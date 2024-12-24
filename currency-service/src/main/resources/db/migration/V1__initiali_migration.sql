CREATE TABLE IF NOT EXISTS currency (
    `id` BINARY(16) NOT NULL UNIQUE,
    `name` VARCHAR(64) NOT NULL,
    `symbol` VARCHAR(30) NOT NULL UNIQUE,
    `enabled` BIT NOT NULL,
    `supported` BIT NOT NULL,
    created_by VARCHAR(255),
    creation_date DATETIME,
    last_modified_by VARCHAR(255),
    last_modified_date DATETIME,

    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;