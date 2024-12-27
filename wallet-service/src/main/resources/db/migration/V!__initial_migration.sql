CREATE TABLE IF NOT EXISTS wallet_asset (
    id BINARY(16) NOT NULL UNIQUE,
    `available_balance` DECIMAL(64, 2) NOT NULL,
    `reserved_balance` DECIMAL(64, 2) NOT NULL,
    `locked` BIT NOT NULL,
    `currency_id` BINARY(16) NOT NULL,
    `user_id` BINARY(16) NOT NULL,
    created_by VARCHAR(255),
    creation_date DATETIME,
    last_modified_by VARCHAR(255),
    last_modified_date DATETIME,

    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `wallet_transaction` (
    id BINARY(16) NOT NULL UNIQUE,
    user_id BINARY(16) NOT NULL,
    amount DECIMAL(64, 2) NOT NULL,
    `type` VARCHAR(15) NOT NULL,
    `purpose` VARCHAR(35) NOT NULL,
    `currency_id` BINARY(16) NOT NULL,
    `reference` VARCHAR(255) NOT NULL UNIQUE,
    `status` VARCHAR(30) NOT NULL,
    `paid` BIT NOT NULL,
    created_by VARCHAR(255),
    creation_date DATETIME,
    last_modified_by VARCHAR(255),
    last_modified_date DATETIME,

    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;