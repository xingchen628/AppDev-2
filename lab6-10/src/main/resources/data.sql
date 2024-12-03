INSERT INTO household (eircode, number_of_occupants, max_number_of_occupants, owner_occupied) VALUES
                                                                                                  ('D02XY45', 3, 5, 1),
                                                                                                  ('A94B6F3', 4, 6, 0),
                                                                                                  ('T12AB34', 2, 4, 1),
                                                                                                  ('C15DE67', 5, 7, 1),
                                                                                                  ('F12GH89', 1, 2, 0);

INSERT INTO pets (name, animal_type, breed, age, household_eircode) VALUES
                                                                        ('Bella', 'Dog', 'Labrador', 4, 'D02XY45'),
                                                                        ('Max', 'Cat', 'Siamese', 2, 'T12AB34'),
                                                                        ('Charlie', 'Bird', 'Parrot', 3, 'C15DE67');
INSERT INTO users (username, password, role, locked, first_name, last_name, county)
VALUES
    ('admin@example.com', '', 'ADMIN', false, 'Admin', 'User', 'Cork'),
    ('user@example.com', '', 'USER', false, 'Regular', 'User', 'Kerry');