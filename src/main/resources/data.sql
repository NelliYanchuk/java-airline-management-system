USE airline;
-- Insert admin user
INSERT INTO users (username, password, role) VALUES ('admin', 'adminpassword', 'ADMIN');

-- Insert regular users
INSERT INTO users (username, password, role) VALUES ('user1', 'password1', 'USER');
INSERT INTO users (username, password, role) VALUES ('user2', 'password2', 'USER');
INSERT INTO users (username, password, role) VALUES ('user3', 'password3', 'USER');
INSERT INTO users (username, password, role) VALUES ('user4', 'password4', 'USER');
INSERT INTO users (username, password, role) VALUES ('user5', 'password5', 'USER');