CREATE TABLE IF NOT EXISTS user_role (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50)
);

INSERT INTO user_role (id, name) VALUES (1, 'employee');
INSERT INTO user_role (id, name) VALUES (2, 'manager');
INSERT INTO user_role (id, name) VALUES (3, 'customer');

CREATE TABLE IF NOT EXISTS user (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50),
  role_id BIGINT,
  FOREIGN KEY (role_id) REFERENCES user_role(id)
);

INSERT INTO user (id, name, role_id) VALUES (1, 'user.emp', 1);
INSERT INTO user (id, name, role_id) VALUES (2, 'user.manager', 2);
INSERT INTO user (id, name, role_id) VALUES (3, 'user.customer', 3);

CREATE TABLE IF NOT EXISTS car (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  number VARCHAR(10),
  country VARCHAR(2),
);

INSERT INTO car (id, number, country) VALUES (1, 'AB1234', 'BY');
INSERT INTO car (id, number, country) VALUES (2, 'ABC987', 'LT');
INSERT INTO car (id, number, country) VALUES (3, '1234KW', 'PL');

CREATE TABLE IF NOT EXISTS shift (
 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 openingTime DATETIME,
 closingTime DATETIME,
 user_id BIGINT,
 FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO shift (id, openingTime, closingTime, user_id) VALUES (1, '2019-05-05 09:00:00', '2019-05-05 20:59:59', 1);
INSERT INTO shift (id, openingTime, closingTime, user_id) VALUES (2, '2019-05-05 21:00:00', '2019-05-06 08:30:00', 2);

CREATE TABLE IF NOT EXISTS insurance (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  payment FLOAT,
  amount FLOAT,
  car_id BIGINT,
  shift_id BIGINT,
  FOREIGN KEY (car_id) REFERENCES car(id),
  FOREIGN KEY (shift_id) REFERENCES shift(id)
);

INSERT INTO insurance (id, payment, amount, car_id, shift_id) VALUES (1, 12.5, 600, 1, 1);
INSERT INTO insurance (id, payment, amount, car_id, shift_id) VALUES (2, 15, 800.45, 2, 2)

