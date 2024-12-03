CREATE SCHEMA IF NOT EXISTS pets;
SET SCHEMA pets;
CREATE TABLE pets (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      animal_type VARCHAR(255) NOT NULL,
                      breed VARCHAR(255) NOT NULL,
                      age INT NOT NULL
);
CREATE TABLE household (
                           eircode VARCHAR(8) PRIMARY KEY,
                           number_of_occupants INT NOT NULL,
                           max_number_of_occupants INT NOT NULL,
                           owner_occupied BIT NOT NULL
);
CREATE TABLE users (
                       username VARCHAR(50) NOT NULL PRIMARY KEY,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(50) NOT NULL, -- Add this column
                       locked BOOLEAN NOT NULL,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       county VARCHAR(50) NOT NULL
);

CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES users (username)
);
ALTER TABLE pets ADD COLUMN household_eircode VARCHAR(8);
ALTER TABLE pets ADD CONSTRAINT fk_household FOREIGN KEY (household_eircode) REFERENCES household (eircode) ON DELETE CASCADE;
