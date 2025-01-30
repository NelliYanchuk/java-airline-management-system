USE airline;
-- Insert admin user
INSERT INTO users (username, password, role) VALUES ('admin', 'adminpassword', 'ADMIN');

-- Insert regular users
INSERT INTO users (username, password, role) VALUES ('user1', 'password1', 'USER');
INSERT INTO users (username, password, role) VALUES ('user2', 'password2', 'USER');
INSERT INTO users (username, password, role) VALUES ('user3', 'password3', 'USER');
INSERT INTO users (username, password, role) VALUES ('user4', 'password4', 'USER');
INSERT INTO users (username, password, role) VALUES ('user5', 'password5', 'USER');

-- Insert sample airports
INSERT INTO airports (id, name, location) VALUES (1, 'John F. Kennedy International Airport', 'New York, USA');
INSERT INTO airports (id, name, location) VALUES (2, 'Los Angeles International Airport', 'Los Angeles, USA');
INSERT INTO airports (id, name, location) VALUES (3, 'Chicago O\'Hare International Airport', 'Chicago, USA');
INSERT INTO airports (id, name, location) VALUES (4, 'Hartsfield-Jackson Atlanta International Airport', 'Atlanta, USA');
INSERT INTO airports (id, name, location) VALUES (5, 'Dallas/Fort Worth International Airport', 'Dallas, USA');

-- Insert sample flights
INSERT INTO flights (id, origin_airport_id, destination_airport_id, departure_date, arrival_date, available_seats, status) VALUES 
(1, 1, 2, '2025-02-01 08:00:00', '2025-02-01 11:00:00', 100, TRUE),
(2, 1, 3, '2025-02-02 09:00:00', '2025-02-02 11:30:00', 100, TRUE),
(3, 2, 4, '2025-02-03 10:00:00', '2025-02-03 13:00:00', 100, TRUE),
(4, 3, 5, '2025-02-04 12:00:00', '2025-02-04 14:30:00', 100, TRUE),
(5, 4, 1, '2025-02-05 14:00:00', '2025-02-05 17:00:00', 100, TRUE);