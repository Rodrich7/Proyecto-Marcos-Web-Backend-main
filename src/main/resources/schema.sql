-- PERMISSIONS
CREATE TABLE permissions (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) UNIQUE NOT NULL
);

-- ROLES
CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) UNIQUE NOT NULL
);

-- USERS
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role_id INT NOT NULL,
  CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- DESTINATIONS
CREATE TABLE destinations (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  country VARCHAR(100) NOT NULL
);

-- AIRPLANES
CREATE TABLE airplanes (
  id SERIAL PRIMARY KEY,
  model VARCHAR(255) NOT NULL,
  capacity INT NOT NULL
);

-- FLIGHTS
CREATE TABLE flights (
  id SERIAL PRIMARY KEY,
  airplane_id INT NOT NULL,
  destination_id INT NOT NULL,
  departure_time TIMESTAMP NOT NULL,
  arrival_time TIMESTAMP NOT NULL,
  CONSTRAINT fk_airplane FOREIGN KEY (airplane_id) REFERENCES airplanes(id),
  CONSTRAINT fk_destination FOREIGN KEY (destination_id) REFERENCES destinations(id)
);

-- PASSENGERS
CREATE TABLE passengers (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(255)
);

-- RESERVATIONS
CREATE TABLE reservations (
  id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  flight_id INT NOT NULL,
  reservation_date TIMESTAMP NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_flight FOREIGN KEY (flight_id) REFERENCES flights(id)
);

-- SEATS
CREATE TABLE seats (
  id SERIAL PRIMARY KEY,
  airplane_id INT NOT NULL,
  seat_number VARCHAR(10) NOT NULL,
  class VARCHAR(50),
  CONSTRAINT fk_airplane_seat FOREIGN KEY (airplane_id) REFERENCES airplanes(id)
);

-- RESERVATION_PASSENGERS
CREATE TABLE reservation_passengers (
  id SERIAL PRIMARY KEY,
  reservation_id INT NOT NULL,
  passenger_id INT NOT NULL,
  seat_id INT NOT NULL,
  CONSTRAINT fk_reservation FOREIGN KEY (reservation_id) REFERENCES reservations(id),
  CONSTRAINT fk_passenger FOREIGN KEY (passenger_id) REFERENCES passengers(id),
  CONSTRAINT fk_seat FOREIGN KEY (seat_id) REFERENCES seats(id)
);

-- PAYMENTS
CREATE TABLE payments (
  id SERIAL PRIMARY KEY,
  reservation_id INT NOT NULL,
  amount NUMERIC(10,2) NOT NULL,
  payment_date TIMESTAMP NOT NULL,
  payment_method VARCHAR(50),
  CONSTRAINT fk_reservation_payment FOREIGN KEY (reservation_id) REFERENCES reservations(id)
);

-- FLIGHT_PRICES
CREATE TABLE flight_prices (
  id SERIAL PRIMARY KEY,
  flight_id INT NOT NULL,
  price NUMERIC(10,2) NOT NULL,
  class VARCHAR(50),
  CONSTRAINT fk_flight_price FOREIGN KEY (flight_id) REFERENCES flights(id)
);

-- BENEFITS
CREATE TABLE benefits (
  id SERIAL PRIMARY KEY,
  code VARCHAR(50) UNIQUE NOT NULL,
  name VARCHAR(100) NOT NULL,
  description TEXT
);

-- FLIGHT_PRICE_BENEFITS
CREATE TABLE flight_price_benefits (
  id SERIAL PRIMARY KEY,
  flight_price_id INT NOT NULL,
  benefit_id INT NOT NULL,
  value VARCHAR(100),
  extra_info TEXT,
  CONSTRAINT fk_flight_price_benefit FOREIGN KEY (flight_price_id) REFERENCES flight_prices(id),
  CONSTRAINT fk_benefit FOREIGN KEY (benefit_id) REFERENCES benefits(id)
);

-- ADDITIONAL_SERVICES
CREATE TABLE additional_services (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  price NUMERIC(10,2),
  icon VARCHAR(50)
);

-- RESERVATION_ADDITIONAL_SERVICES
CREATE TABLE reservation_additional_services (
  id SERIAL PRIMARY KEY,
  reservation_id INT NOT NULL,
  additional_service_id INT NOT NULL,
  CONSTRAINT fk_reservation_additional_service FOREIGN KEY (reservation_id) REFERENCES reservations(id),
  CONSTRAINT fk_additional_service FOREIGN KEY (additional_service_id) REFERENCES additional_services(id)
);

-- FLIGHT_ADDITIONAL_SERVICES
CREATE TABLE flight_additional_services (
  id SERIAL PRIMARY KEY,
  flight_id INT NOT NULL,
  additional_service_id INT NOT NULL,
  CONSTRAINT fk_flight_additional_service FOREIGN KEY (flight_id) REFERENCES flights(id),
  CONSTRAINT fk_additional_service_flight FOREIGN KEY (additional_service_id) REFERENCES additional_services(id)
);
