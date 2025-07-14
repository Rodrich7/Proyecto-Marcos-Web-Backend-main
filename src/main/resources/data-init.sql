-- PERMISSIONS
INSERT INTO permissions (id, name) VALUES (1, 'CREATE');
INSERT INTO permissions (id, name) VALUES (2, 'READ');
INSERT INTO permissions (id, name) VALUES (3, 'UPDATE');
INSERT INTO permissions (id, name) VALUES (4, 'DELETE');
INSERT INTO permissions (id, name) VALUES (5, 'REFACTOR');

-- ROLES
INSERT INTO roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'USER');
INSERT INTO roles (id, name) VALUES (3, 'INVITED');
INSERT INTO roles (id, name) VALUES (4, 'DEVELOPER');

-- ROLE_PERMISSIONS
INSERT INTO role_permissions (role_id, permission_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4); -- ADMIN
INSERT INTO role_permissions (role_id, permission_id) VALUES (2, 1), (2, 2); -- USER
INSERT INTO role_permissions (role_id, permission_id) VALUES (3, 2); -- INVITED
INSERT INTO role_permissions (role_id, permission_id) VALUES (4, 1), (4, 2), (4, 3), (4, 4), (4, 5); -- DEVELOPER

-- USERS
INSERT INTO users (id, username, password, email, is_enabled, account_No_Expired, account_No_Locked, credential_No_Expired)
VALUES (1, 'santiago', '$2a$10$alDHPqptkxJ2/XffiFPDEOUzl/zR.a0yN2a7jbReHLMecbHGUi5nu','santiago@example.com', true, true, true, true),
       (2, 'daniel', '$2a$10$alDHPqptkxJ2/XffiFPDEOUzl/zR.a0yN2a7jbReHLMecbHGUi5nu','daniel@example.com', true, true, true, true),
       (3, 'andrea', '$2a$10$alDHPqptkxJ2/XffiFPDEOUzl/zR.a0yN2a7jbReHLMecbHGUi5nu','andrea@example.com', true, true, true, true),
       (4, 'anyi', '$2a$10$alDHPqptkxJ2/XffiFPDEOUzl/zR.a0yN2a7jbReHLMecbHGUi5nu', 'anyi@example.com', true, true, true, true);



-- USER_ROLES
INTO user_roles (user_id, role_id) VALUES (1, 1), (2, 2), (3, 3), (4, 4);

-- Destinations
INSERT INTO destinations (id, city, country, image_url, airport_code, continent) VALUES
(1, 'Bogotá', 'Colombia', 'https://images.unsplash.com/photo-1506744038136-46273834b3fb', 'BOG', 'SOUTH_AMERICA'),
(2, 'Medellín', 'Colombia', 'https://images.unsplash.com/photo-1464983953574-0892a716854b', 'MDE', 'SOUTH_AMERICA'),
(3, 'Cartagena', 'Colombia', 'https://images.unsplash.com/photo-1502086223501-7ea6ecd79368', 'CTG', 'SOUTH_AMERICA'),
(4, 'Lima', 'Peru', 'https://images.unsplash.com/photo-1509228468518-180dd4864904', 'LIM', 'SOUTH_AMERICA'),
(5, 'Quito', 'Ecuador', 'https://images.unsplash.com/photo-1464983953574-0892a716854b', 'UIO', 'SOUTH_AMERICA'),
(6, 'Buenos Aires', 'Argentina', 'https://images.unsplash.com/photo-1519681393784-d120267933ba', 'EZE', 'SOUTH_AMERICA'),
(7, 'Madrid', 'España', 'https://images.unsplash.com/photo-1464983953574-0892a716854b', 'MAD', 'EUROPE');

-- Airlines
INSERT INTO airlines (id, name, country) VALUES
(1, 'Avianca', 'Colombia'),
(2, 'LATAM', 'Chile'),
(3, 'American Airlines', 'USA'),
(4, 'Air France', 'France');


-- Airplanes
INSERT INTO airplanes (id, model, capacity, registration_number, airline_id) VALUES
(1, 'Boeing 737', 180, 'ABC123', 1),
(2, 'Airbus A320', 150, 'XYZ789', 2),
(3, 'Boeing 787', 240, 'DEF456', 2),
(4, 'Airbus A330', 260, 'GHI012', 3),
(5, 'Boeing 777', 300, 'JKL345', 4),
(6, 'Airbus A350', 280, 'MNO678', 1);


-- Flights
INSERT INTO flights (id, flight_number, origin_id, destination_id, departure_time, arrival_time, airplane_id, status) VALUES
(1, 'AV101', 1, 2, '2025-05-11T08:00:00', '2025-05-25T10:00:00', 1, 'PROGRAMMED'),
(2, 'AV202', 2, 3, '2025-05-11T12:00:00', '2025-05-25T14:30:00', 2, 'PROGRAMMED'),
(3, 'LA303', 3, 4, '2025-05-11T09:00:00', '2025-05-11T11:00:00', 3, 'PROGRAMMED'),
(4, 'AA404', 4, 1, '2025-05-12T15:00:00', '2025-05-12T17:00:00', 4, 'PROGRAMMED'),
(5, 'AF505', 1, 5, '2025-05-13T09:30:00', '2025-05-13T12:00:00', 5, 'PROGRAMMED'),
(6, 'LA606', 2, 6, '2025-05-14T14:00:00', '2025-05-14T16:30:00', 6, 'PROGRAMMED'),
(7, 'AV707', 3, 7, '2025-05-15T07:00:00', '2025-05-15T09:30:00', 1, 'PROGRAMMED'),
(8, 'AA808', 4, 2, '2025-05-16T16:00:00', '2025-05-16T18:30:00', 2, 'PROGRAMMED'),
(9, 'IB909', 5, 6, '2025-05-17T08:00:00', '2025-05-17T10:30:00', 3, 'PROGRAMMED'),
(10, 'KL1010', 6, 7, '2025-05-18T14:00:00', '2025-05-18T16:30:00', 4, 'PROGRAMMED'),
(11, 'LH1111', 7, 1, '2025-05-19T09:00:00', '2025-05-19T11:30:00', 5, 'PROGRAMMED'),
(12, 'BA1212', 1, 3, '2025-05-20T15:00:00', '2025-05-20T17:30:00', 6, 'PROGRAMMED'),
(13, 'AF1313', 2, 4, '2025-05-21T10:00:00', '2025-05-21T12:30:00', 1, 'PROGRAMMED'),
(14, 'DL1414', 3, 5, '2025-05-22T07:00:00', '2025-05-22T09:30:00', 2, 'PROGRAMMED'),
(15, 'UA1515', 4, 6, '2025-05-23T12:00:00', '2025-05-23T14:30:00', 3, 'PROGRAMMED'),
(16, 'QF1616', 5, 7, '2025-05-24T08:30:00', '2025-05-24T11:00:00', 4, 'PROGRAMMED'),
(17, 'EK1717', 6, 1, '2025-05-25T13:00:00', '2025-05-25T15:30:00', 5, 'PROGRAMMED'),
(18, 'QR1818', 7, 2, '2025-05-26T09:30:00', '2025-05-26T12:00:00', 6, 'PROGRAMMED'),
(19, 'AA1919', 1, 4, '2025-05-27T14:00:00', '2025-05-27T16:30:00', 1, 'PROGRAMMED'),
(20, 'AV2020', 2, 5, '2025-05-28T11:00:00', '2025-05-28T13:30:00', 2, 'PROGRAMMED'),
(21, 'LA2121', 3, 6, '2025-05-29T08:00:00', '2025-05-29T10:30:00', 3, 'PROGRAMMED'),
(22, 'IB2222', 4, 7, '2025-05-30T10:00:00', '2025-05-30T12:30:00', 4, 'PROGRAMMED'),
(23, 'KL2323', 5, 1, '2025-05-31T15:00:00', '2025-05-31T17:30:00', 5, 'PROGRAMMED'),
(24, 'LH2424', 6, 2, '2025-06-01T07:30:00', '2025-06-01T10:00:00', 6, 'PROGRAMMED'),
(25, 'BA2525', 7, 3, '2025-06-02T12:00:00', '2025-06-02T14:30:00', 1, 'PROGRAMMED'),
(26, 'AF2626', 1, 5, '2025-06-03T09:00:00', '2025-06-03T11:30:00', 2, 'PROGRAMMED'),
(27, 'DL2727', 2, 6, '2025-06-04T14:00:00', '2025-06-04T16:30:00', 3, 'PROGRAMMED'),
(28, 'UA2828', 3, 7, '2025-06-05T08:00:00', '2025-06-05T10:30:00', 4, 'PROGRAMMED'),
(29, 'QF2929', 4, 1, '2025-06-06T10:00:00', '2025-06-06T12:30:00', 5, 'PROGRAMMED'),
(30, 'EK3030', 5, 2, '2025-06-07T13:00:00', '2025-06-07T15:30:00', 6, 'PROGRAMMED'),
(31, 'AV5001', 1, 2, '2025-05-10 08:00:00', '2025-05-10 10:00:00', 1, 'PROGRAMMED'),
(32, 'AV5002', 2, 3, '2025-05-11 12:30:00', '2025-05-11 14:45:00', 2, 'PROGRAMMED'),
(33, 'LA5003', 3, 4, '2025-05-12 09:15:00', '2025-05-12 11:30:00', 3, 'PROGRAMMED'),
(34, 'AA5004', 4, 5, '2025-05-13 13:00:00', '2025-05-13 15:15:00', 4, 'PROGRAMMED'),
(35, 'AF5005', 5, 6, '2025-05-14 07:45:00', '2025-05-14 10:00:00', 5, 'PROGRAMMED'),
(36, 'LA5006', 6, 7, '2025-05-15 10:00:00', '2025-05-15 12:15:00', 6, 'PROGRAMMED'),
(37, 'AV5007', 7, 1, '2025-05-16 15:00:00', '2025-05-16 17:20:00', 1, 'PROGRAMMED'),
(38, 'AA5008', 1, 3, '2025-05-17 08:30:00', '2025-05-17 11:00:00', 2, 'PROGRAMMED'),
(39, 'IB5009', 2, 4, '2025-05-18 14:00:00', '2025-05-18 16:30:00', 3, 'PROGRAMMED'),
(40, 'KL5010', 3, 5, '2025-05-19 09:00:00', '2025-05-19 11:20:00', 4, 'PROGRAMMED');

-- Flight Prices
INSERT INTO flight_prices (id, flight_id, class, price) VALUES
(1, 1, 'ECONOMY', 350.00), (2, 1, 'BUSINESS', 700.00), (3, 1, 'FIRST_CLASS', 1200.00),
(4, 2, 'ECONOMY', 360.00), (5, 2, 'BUSINESS', 710.00), (6, 2, 'FIRST_CLASS', 1210.00),
(7, 3, 'ECONOMY', 370.00), (8, 3, 'BUSINESS', 720.00), (9, 3, 'FIRST_CLASS', 1220.00),
(10, 4, 'ECONOMY', 380.00), (11, 4, 'BUSINESS', 730.00), (12, 4, 'FIRST_CLASS', 1230.00),
(13, 5, 'ECONOMY', 390.00), (14, 5, 'BUSINESS', 740.00), (15, 5, 'FIRST_CLASS', 1240.00),
(16, 6, 'ECONOMY', 400.00), (17, 6, 'BUSINESS', 750.00), (18, 6, 'FIRST_CLASS', 1250.00),
(19, 7, 'ECONOMY', 410.00), (20, 7, 'BUSINESS', 760.00), (21, 7, 'FIRST_CLASS', 1260.00),
(22, 8, 'ECONOMY', 420.00), (23, 8, 'BUSINESS', 770.00), (24, 8, 'FIRST_CLASS', 1270.00),
(25, 9, 'ECONOMY', 430.00), (26, 9, 'BUSINESS', 780.00), (27, 9, 'FIRST_CLASS', 1280.00),
(28, 10, 'ECONOMY', 440.00), (29, 10, 'BUSINESS', 790.00), (30, 10, 'FIRST_CLASS', 1290.00),
(31, 11, 'ECONOMY', 450.00), (32, 11, 'BUSINESS', 800.00), (33, 11, 'FIRST_CLASS', 1300.00),
(34, 12, 'ECONOMY', 460.00), (35, 12, 'BUSINESS', 810.00), (36, 12, 'FIRST_CLASS', 1310.00),
(37, 13, 'ECONOMY', 470.00), (38, 13, 'BUSINESS', 820.00), (39, 13, 'FIRST_CLASS', 1320.00),
(40, 14, 'ECONOMY', 480.00), (41, 14, 'BUSINESS', 830.00), (42, 14, 'FIRST_CLASS', 1330.00),
(43, 15, 'ECONOMY', 490.00), (44, 15, 'BUSINESS', 840.00), (45, 15, 'FIRST_CLASS', 1340.00),
(46, 16, 'ECONOMY', 500.00), (47, 16, 'BUSINESS', 850.00), (48, 16, 'FIRST_CLASS', 1350.00),
(49, 17, 'ECONOMY', 510.00), (50, 17, 'BUSINESS', 860.00), (51, 17, 'FIRST_CLASS', 1360.00),
(52, 18, 'ECONOMY', 520.00), (53, 18, 'BUSINESS', 870.00), (54, 18, 'FIRST_CLASS', 1370.00),
(55, 19, 'ECONOMY', 530.00), (56, 19, 'BUSINESS', 880.00), (57, 19, 'FIRST_CLASS', 1380.00),
(58, 20, 'ECONOMY', 540.00), (59, 20, 'BUSINESS', 890.00), (60, 20, 'FIRST_CLASS', 1390.00),
(61, 21, 'ECONOMY', 550.00), (62, 21, 'BUSINESS', 900.00), (63, 21, 'FIRST_CLASS', 1400.00),
(64, 22, 'ECONOMY', 560.00), (65, 22, 'BUSINESS', 910.00), (66, 22, 'FIRST_CLASS', 1410.00),
(67, 23, 'ECONOMY', 570.00), (68, 23, 'BUSINESS', 920.00), (69, 23, 'FIRST_CLASS', 1420.00),
(70, 24, 'ECONOMY', 580.00), (71, 24, 'BUSINESS', 930.00), (72, 24, 'FIRST_CLASS', 1430.00),
(73, 25, 'ECONOMY', 590.00), (74, 25, 'BUSINESS', 940.00), (75, 25, 'FIRST_CLASS', 1440.00),
(76, 26, 'ECONOMY', 600.00), (77, 26, 'BUSINESS', 950.00), (78, 26, 'FIRST_CLASS', 1450.00),
(79, 27, 'ECONOMY', 610.00), (80, 27, 'BUSINESS', 960.00), (81, 27, 'FIRST_CLASS', 1460.00),
(82, 28, 'ECONOMY', 620.00), (83, 28, 'BUSINESS', 970.00), (84, 28, 'FIRST_CLASS', 1470.00),
(85, 29, 'ECONOMY', 630.00), (86, 29, 'BUSINESS', 980.00), (87, 29, 'FIRST_CLASS', 1480.00),
(88, 30, 'ECONOMY', 640.00), (89, 30, 'BUSINESS', 990.00), (90, 30, 'FIRST_CLASS', 1490.00),
(91, 31, 'ECONOMY', 650.00), (92, 31, 'BUSINESS', 1000.00), (93, 31, 'FIRST_CLASS', 1500.00),
(94, 32, 'ECONOMY', 660.00), (95, 32, 'BUSINESS', 1010.00), (96, 32, 'FIRST_CLASS', 1510.00),
(97, 33, 'ECONOMY', 670.00), (98, 33, 'BUSINESS', 1020.00), (99, 33, 'FIRST_CLASS', 1520.00),
(100, 34, 'ECONOMY', 680.00), (101, 34, 'BUSINESS', 1030.00), (102, 34, 'FIRST_CLASS', 1530.00),
(103, 35, 'ECONOMY', 690.00), (104, 35, 'BUSINESS', 1040.00), (105, 35, 'FIRST_CLASS', 1540.00),
(106, 36, 'ECONOMY', 700.00), (107, 36, 'BUSINESS', 1050.00), (108, 36, 'FIRST_CLASS', 1550.00),
(109, 37, 'ECONOMY', 710.00), (110, 37, 'BUSINESS', 1060.00), (111, 37, 'FIRST_CLASS', 1560.00),
(112, 38, 'ECONOMY', 720.00), (113, 38, 'BUSINESS', 1070.00), (114, 38, 'FIRST_CLASS', 1570.00),
(115, 39, 'ECONOMY', 730.00), (116, 39, 'BUSINESS', 1080.00), (117, 39, 'FIRST_CLASS', 1580.00),
(118, 40, 'ECONOMY', 740.00), (119, 40, 'BUSINESS', 1090.00), (120, 40, 'FIRST_CLASS', 1590.00);

-- Passengers
INSERT INTO passengers (
    id, user_id, first_name, last_name, gender, document_type, document_number, document_expiration, email, phone, nationality, birth_date
) VALUES
(1, 1, 'Juan', 'Pérez', 'MALE', 'DNI', '12345678', '2030-06-15', 'juan.perez@email.com', '3001234567', 'Colombian', '1990-01-01'),
(2, 2, 'Ana', 'García', 'FEMALE', 'PASAPORTE', 'P9876543', '2028-12-20', 'ana.garcia@email.com', '3009876543', 'Peruvian', '1985-05-12');



-- Reservations
INSERT INTO reservations (id, user_id, flight_id, reservation_date, status) VALUES
(1, 1, 1, '2025-05-01T12:00:00', 'CONFIRMED'),
(2, 2, 2, '2025-05-01T13:00:00', 'CONFIRMED'),
(3, 3, 1, '2025-05-01T16:00:00', 'CONFIRMED'),
(4, 4, 2, '2025-05-01T17:00:00', 'CONFIRMED');

INSERT INTO seat_type_extra_price (seat_type, extra_price) VALUES
('NORMAL', 0.00),
('PREMIUM', 30.00),
('EMERGENCY_EXIT', 15.00);

-- Seats
INSERT INTO seats (id, flight_id, seat_number, class, seat_type, seat_status) VALUES
-- Fila 1 (Premium)
(1, 1, '1A', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(2, 1, '1B', 'ECONOMY', 'PREMIUM', 'OCCUPIED'),
(3, 1, '1C', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(4, 1, '1D', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(5, 1, '1E', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(6, 1, '1F', 'ECONOMY', 'PREMIUM', 'SELECTED'),
(7, 1, '1G', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(8, 1, '1H', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),

-- Fila 2 (Premium)
(9, 1, '2A', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(10, 1, '2B', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(11, 1, '2C', 'ECONOMY', 'PREMIUM', 'OCCUPIED'),
(12, 1, '2D', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(13, 1, '2E', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(14, 1, '2F', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(15, 1, '2G', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(16, 1, '2H', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),

-- Fila 3 (Emergency Exit)
(17, 1, '3A', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(18, 1, '3B', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(19, 1, '3C', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(20, 1, '3D', 'ECONOMY', 'EMERGENCY_EXIT', 'OCCUPIED'),
(21, 1, '3E', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(22, 1, '3F', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(23, 1, '3G', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(24, 1, '3H', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),

-- Fila 4 (Emergency Exit)
(25, 1, '4A', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(26, 1, '4B', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(27, 1, '4C', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(28, 1, '4D', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(29, 1, '4E', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(30, 1, '4F', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(31, 1, '4G', 'ECONOMY', 'EMERGENCY_EXIT', 'OCCUPIED'),
(32, 1, '4H', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),

-- Filas 5-8 (Normal, algunos ocupados y seleccionados)
(33, 1, '5A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(34, 1, '5B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(35, 1, '5C', 'ECONOMY', 'NORMAL', 'OCCUPIED'),
(36, 1, '5D', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(37, 1, '5E', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(38, 1, '5F', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(39, 1, '5G', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(40, 1, '5H', 'ECONOMY', 'NORMAL', 'AVAILABLE'),

(41, 1, '6A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(42, 1, '6B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(43, 1, '6C', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(44, 1, '6D', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(45, 1, '6E', 'ECONOMY', 'NORMAL', 'SELECTED'),
(46, 1, '6F', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(47, 1, '6G', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(48, 1, '6H', 'ECONOMY', 'NORMAL', 'AVAILABLE'),

(49, 1, '7A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(50, 1, '7B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(51, 1, '7C', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(52, 1, '7D', 'ECONOMY', 'NORMAL', 'OCCUPIED'),
(53, 1, '7E', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(54, 1, '7F', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(55, 1, '7G', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(56, 1, '7H', 'ECONOMY', 'NORMAL', 'AVAILABLE'),

(57, 1, '8A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(58, 1, '8B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(59, 1, '8C', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(60, 1, '8D', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(61, 1, '8E', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(62, 1, '8F', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(63, 1, '8G', 'ECONOMY', 'NORMAL', 'OCCUPIED'),
(64, 1, '8H', 'ECONOMY', 'NORMAL', 'AVAILABLE'),

-- Filas 9-12 (Normal, algunos seleccionados y ocupados)
(65, 1, '9A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(66, 1, '9B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(67, 1, '9C', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(68, 1, '9D', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(69, 1, '9E', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(70, 1, '9F', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(71, 1, '9G', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(72, 1, '9H', 'ECONOMY', 'NORMAL', 'OCCUPIED'),

(73, 1, '10A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(74, 1, '10B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(75, 1, '10C', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(76, 1, '10D', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(77, 1, '10E', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(78, 1, '10F', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(79, 1, '10G', 'ECONOMY', 'NORMAL', 'SELECTED'),
(80, 1, '10H', 'ECONOMY', 'NORMAL', 'AVAILABLE'),

(81, 1, '11A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(82, 1, '11B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(83, 1, '11C', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(84, 1, '11D', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(85, 1, '11E', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(86, 1, '11F', 'ECONOMY', 'NORMAL', 'OCCUPIED'),
(87, 1, '11G', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(88, 1, '11H', 'ECONOMY', 'NORMAL', 'AVAILABLE'),

(89, 1, '12A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(90, 1, '12B', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(91, 1, '12C', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(92, 1, '12D', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(93, 1, '12E', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(94, 1, '12F', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(95, 1, '12G', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(96, 1, '12H', 'ECONOMY', 'NORMAL', 'SELECTED'),

-- Ejemplo para vuelo 2 (ajusta según el precio base de cada clase)
(97, 2, '14A', 'ECONOMY', 'NORMAL', 'AVAILABLE'),
(98, 2, '14B', 'ECONOMY', 'PREMIUM', 'AVAILABLE'),
(99, 2, '14C', 'ECONOMY', 'EMERGENCY_EXIT', 'AVAILABLE'),
(100, 2, '14D', 'BUSINESS', 'NORMAL', 'OCCUPIED'),
(101, 2, '14E', 'BUSINESS', 'PREMIUM', 'AVAILABLE'),
(102, 2, '14F', 'FIRST_CLASS', 'NORMAL', 'AVAILABLE'),
(103, 2, '14G', 'FIRST_CLASS', 'PREMIUM', 'OCCUPIED'),
(104, 2, '14H', 'ECONOMY', 'NORMAL', 'SELECTED');


-- ReservationPassengers
INSERT INTO reservation_passengers (id, reservation_id, passenger_id, seat_id) VALUES
(1, 1, 1, 1),
(2, 1, 2, 2),
(3, 2, 1, 1),
(4, 2, 2, 2);



-- Payments
INSERT INTO payments (id, reservation_id, amount, payment_date, payment_method) VALUES
(1, 1, 350, '2025-05-01T14:00:00', 'CREDIT_CARD'),
(2, 2, 420, '2025-05-01T15:00:00', 'PAYPAL'),
(3, 3, 370, '2025-05-01T18:00:00', 'CREDIT_CARD'),
(4, 4, 410, '2025-05-01T19:00:00', 'PAYPAL');



-- Benefits
INSERT INTO benefits (id, code, name, description) VALUES
(1, 'EQUIPAJE_MANO', 'Equipaje de mano', 'Hasta 10kg, 1 pieza'),
(2, 'EQUIPAJE_BODEGA', 'Equipaje en bodega', 'Hasta 23kg, 1 pieza'),
(3, 'ASIENTO_PREFERENTE', 'Asiento preferente', 'Selección de asiento preferente'),
(4, 'WIFI', 'WiFi a bordo', 'Acceso a internet durante el vuelo');

-- Flight Price Benefits
INSERT INTO flight_price_benefits (id, flight_price_id, benefit_id, value, extra_info) VALUES
(1, 1, 1, 'Incluido', null),
(2, 1, 2, 'No incluido', null),
(3, 1, 3, 'No incluido', null),
(4, 1, 4, 'No incluido', null),
(5, 3, 1, 'Incluido', null),
(6, 3, 2, 'Incluido', null),
(7, 3, 3, 'Incluido', null),
(8, 3, 4, 'Incluido', null),
(9, 2, 1, 'Incluido', null),
(10, 2, 2, 'No incluido', null),
(11, 2, 3, 'No incluido', null),
(12, 2, 4, 'No incluido', null),
(13, 4, 1, 'Incluido', null),
(14, 4, 2, 'Incluido', null),
(15, 4, 3, 'Incluido', null),
(16, 4, 4, 'Incluido', null);

-- Servicios adicionales
INSERT INTO additional_services (id, name, description, price, icon) VALUES
(1, 'WiFi a bordo', 'Acceso a internet durante el vuelo', 12.50, 'wifi'),
(2, 'Sala VIP', 'Acceso a la sala VIP del aeropuerto', 30.00, 'briefcase'),
(3, 'Comida especial', 'Menú especial a bordo', 18.00, 'meal'),
(4, 'Embarque prioritario', 'Prioridad al abordar el avión', 8.00, 'arrow-up-circle'),
(5, 'Seguro de viaje', 'Cobertura de seguro durante el viaje', 20.00, 'shield');

-- Relacionar reservas con servicios adicionales
INSERT INTO reservation_additional_services (id, reservation_id, additional_service_id) VALUES
(1, 1, 1), -- Reserva 1 adquirió WiFi a bordo
(2, 1, 2), -- Reserva 1 adquirió Sala VIP
(3, 2, 3), -- Reserva 2 adquirió Comida especial
(4, 3, 4), -- Reserva 3 adquirió Embarque prioritario
(5, 4, 5); -- Reserva 4 adquirió Seguro de viaje

-- Relacionar vuelos con servicios adicionales
INSERT INTO flight_additional_services (id, flight_id, additional_service_id) VALUES
(1, 1, 1), -- Vuelo 1 tiene WiFi a bordo
(2, 1, 2), -- Vuelo 1 tiene Sala VIP
(3, 2, 3), -- Vuelo 2 tiene Comida especial
(4, 2, 4), -- Vuelo 2 tiene Embarque prioritario
(5, 3, 1), -- Vuelo 3 tiene WiFi a bordo
(6, 3, 5), -- Vuelo 3 tiene Seguro de viaje
(7, 4, 2), -- Vuelo 4 tiene Sala VIP
(8, 5, 3), -- Vuelo 5 tiene Comida especial
(9, 6, 4), -- Vuelo 6 tiene Embarque prioritario
(10, 7, 5); -- Vuelo 7 tiene Seguro de viaje


-- AiInteractions
-- INSERT INTO ai_interactions (id, user_id, interaction_type, message, response, created_at) VALUES
-- (1, 1, 'INQUIRY', '¿Cuál es mi vuelo?', 'Tu vuelo es AV101', '2025-05-01T16:00:00'),
-- (2, 2, 'CHECK_IN', '¿Puedo hacer check-in?', 'El check-in está disponible 24h antes del vuelo.', '2025-05-01T17:00:00'),
-- (3, 3, 'INQUIRY', '¿Hay wifi en el avión?', 'Sí, hay wifi disponible.', '2025-05-01T18:00:00'),
-- (4, 4, 'INQUIRY', '¿Puedo llevar equipaje extra?', 'Sí, pero tiene un costo adicional.', '2025-05-01T19:00:00');

-- Ajustar secuencias para evitar conflictos de clave primaria en inserts automáticos
SELECT setval('permissions_id_seq', (SELECT MAX(id) FROM permissions));
SELECT setval('roles_id_seq', (SELECT MAX(id) FROM roles));
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('destinations_id_seq', (SELECT MAX(id) FROM destinations));
SELECT setval('airplanes_id_seq', (SELECT MAX(id) FROM airplanes));
SELECT setval('flights_id_seq', (SELECT MAX(id) FROM flights));
SELECT setval('passengers_id_seq', (SELECT MAX(id) FROM passengers));
SELECT setval('reservations_id_seq', (SELECT MAX(id) FROM reservations));
SELECT setval('seats_id_seq', (SELECT MAX(id) FROM seats));
SELECT setval('reservation_passengers_id_seq', (SELECT MAX(id) FROM reservation_passengers));
SELECT setval('payments_id_seq', (SELECT MAX(id) FROM payments));
SELECT setval('flight_prices_id_seq', (SELECT MAX(id) FROM flight_prices));
-- SELECT setval('ai_interactions_id_seq', (SELECT MAX(id) FROM ai_interactions));
SELECT setval('benefits_id_seq', (SELECT MAX(id) FROM benefits));
SELECT setval('additional_services_id_seq', (SELECT MAX(id) FROM additional_services));
SELECT setval('reservation_additional_services_id_seq', (SELECT MAX(id) FROM reservation_additional_services));
SELECT setval('flight_additional_services_id_seq', (SELECT MAX(id) FROM flight_additional_services));
SELECT setval('flight_price_benefits_id_seq', (SELECT MAX(id) FROM flight_price_benefits));
