INSERT INTO users (first_name, last_name, street, house_number, postal_code, city, country, phone_number, iban, email, password, user_role, enabled, locked, created_at)
VALUES ('John','Doe','Veemarktstraat','12','4811ZJ','Breda','Nederland','+31618588947','NL12INGB047583365','john.doe@gmail.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, current_timestamp),
       ('Bart','Grootoonk','Stationsstraat','47','5867HH','Tilburg','Nederland','+31676893445','NL23ABNA083465899','bart.grootoonk@email.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Johan','de Visser','Zuidermarkt','7C','2298HK','Groningen','Nederland','+31623354789','NL12INGB014365578','johandevisser@live.nl','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Berend','Wolfstra','Beverweg','156','8766UO','Breda','Nederland','+31615477998','NL63RABO043667890','berendwolfstra@yahoo.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Kees','van Dongen','Parelsebaan','2','2857TY','Eindhoven','Nederland','+31665877034','NL12INGB031224567','kees.vandongen@hetnet.nl','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Merel','van Laren','Poolseweg','48D','6755JQ','Breda','Nederland','+31664855136','NL12INGB078695722','merelvanlaren@gmail.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Sofia','Bulgara','Torenstraat','18','4758UY','Amsterdam','Nederland','+31618675075','NL63RABO027586957','sofiabulgara@hotmail.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Ronald','Peyger','Dubbelsebaan','23','9273SO','Maastricht','Nederland','+31639687961','NL12INGB011334657','ronaldpeyger@gmail.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Ruud','Klozel','John F. Kennedylaan','112','3968GJ','Tilburg','Nederland','+31618477689','NL23ABNA047565767','ruudklozel@gmail.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP),
       ('Bas','de Ruyter','Ginnekenweg','34','4812BK','Breda','Nederland','+31634355789','NL12INGB044879685','basderuyter@gmail.com','$2a$10$J1cVbLW4.wDH.oydt1R3Gupx0qb3mw885FfLNE8U5Oz2Rc.YFbmry','USER', true, false, CURRENT_TIMESTAMP);

INSERT INTO confirmation_token (confirmed_at, created_at, expired_at, token, user_id)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', '88bf0629-45d3-4897-be7c-062bdf21e7d9', 1),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', '466b0d03-68a7-4663-bce5-eae2a7325a80', 2),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', 'eac05c5c-4f89-422d-9766-0ec5e2e4edab', 3),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', 'f42259dd-c184-4d50-b273-08b2841bc667', 4),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', '5ba86400-c642-4a70-b26e-8439ceb88607', 5),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', 'ca0bdbbf-9fc3-4008-8992-4b21b45e8a22', 6),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', '6a3aab52-b8f1-4212-844f-42014adf6c71', 7),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', 'f369ec15-cd7b-4a12-bfbf-1e95a30816c4', 8),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', '45fbba19-2e0d-4c65-9b51-97266e83f98a', 9),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 HOUR', '05bf9bf1-9580-4480-ae6f-b2bc490cdc79', 10);

INSERT INTO timeslot (start_at, end_at)
VALUES ('07:00:00','11:30:00'),
       ('12:00:00','16:30:00'),
       ('17:00:00','21:30:00');

INSERT INTO insurance (insurance_type_id, name_translation_tag, description_translation_tag, price)
VALUES ('ALL_RISK', 'c301da85-3034-474a-9ff1-e19441e89414', 'f9d07bbf-324a-466e-a5ec-7a0bf7382b04', 49.99),
       ('ALL_RISK_INTERNATIONAL','0859d301-4a66-4846-8e2f-9287efad8211','37d9f0cb-309f-4c51-bf4d-8b2366c3de00', 69.99),
       ('BASIC_COVERAGE', 'f5c2279f-5352-494c-af52-19daad480c99', '2f6f6409-9ba1-4ec5-9545-eb2604d1ccef', 29.99),
       ('BASIC_COVERAGE_INTERNATIONAL','e24b0e88-5cbe-451b-b7fc-cd9b4a968932','c9077a96-712b-4a6f-920b-4d12b7495388', 39.99);

INSERT INTO translation (translation_tag, language, content)
VALUES ('c301da85-3034-474a-9ff1-e19441e89414','NL','All risk verzekering'),
       ('c301da85-3034-474a-9ff1-e19441e89414','EN','All risk insurance'),
       ('0859d301-4a66-4846-8e2f-9287efad8211','NL','All risk verzekering internationaal'),
       ('0859d301-4a66-4846-8e2f-9287efad8211','EN','All risk insurance international'),
       ('37d9f0cb-309f-4c51-bf4d-8b2366c3de00','NL','Altijd verzekerd bij schade zonder eigen risico, zelfs als u het zelf veroorzaakt. Ook in het buitenland.'),
       ('37d9f0cb-309f-4c51-bf4d-8b2366c3de00','EN','Always insured with damage without excess, even if you cause it yourself. Also internationally.'),
       ('f9d07bbf-324a-466e-a5ec-7a0bf7382b04','NL','Altijd verzekerd bij schade zonder eigen risico, zelfs als u het zelf veroorzaakt.'),
       ('f9d07bbf-324a-466e-a5ec-7a0bf7382b04','EN','Always insured with damage without excess, even if you cause it yourself.'),
       ('f5c2279f-5352-494c-af52-19daad480c99','NL','Basis verzekering'),
       ('f5c2279f-5352-494c-af52-19daad480c99','EN','Basic insurance'),
       ('2f6f6409-9ba1-4ec5-9545-eb2604d1ccef','NL','Basis verzekering bij diefstal schade of pech. €300,- eigen risico.'),
       ('2f6f6409-9ba1-4ec5-9545-eb2604d1ccef','EN','Basic insurance in case of theft, damage or breakdown. €300,- excess.'),
       ('e24b0e88-5cbe-451b-b7fc-cd9b4a968932','NL','Basis verzekering internationaal'),
       ('e24b0e88-5cbe-451b-b7fc-cd9b4a968932','EN','Basic insurance international'),
       ('c9077a96-712b-4a6f-920b-4d12b7495388','NL','Basis verzekering bij diefstal schade of pech, ook in het buitenland. €300,- eigen risico.'),
       ('c9077a96-712b-4a6f-920b-4d12b7495388','EN','Basic insurance in case of theft, damage or breakdown, also internationally. €300,- excess.');

INSERT INTO location (street, house_number, postal_code, city, country, latitude, longitude, user_id, created_at)
VALUES ('Ernst Casimirstraat','21','4811KS','Breda','Nederland', 51.585154037140306, 4.770362791146651, 1, CURRENT_TIMESTAMP),
       ('Mozartlaan', '230', '5011SW', 'Tilburg', 'Nederland', 51.58014601022593, 5.075165935722362, 2, CURRENT_TIMESTAMP),
       ('Meidoornpad', '11', '9713NM', 'Groningen', 'Nederland', 53.22400126117017, 6.585193827500725, 3, CURRENT_TIMESTAMP),
       ('Bloemenblauwtje', '74', '4814TT', 'Breda', 'Nederland', 51.59223154103231, 4.745041429181561, 4, CURRENT_TIMESTAMP);

INSERT INTO car (brand, brand_type, model, license_plate_number, consumption, created_at, car_type, location_id, user_id)
VALUES ('Honda', 'H-RV', 'EX-L', '465-HK-3', 5.7, CURRENT_TIMESTAMP, 'ICE', 1, 1),
       ('Volkswagen', 'Polo', '1.6 TDI', '4-YY-685', 4.45, CURRENT_TIMESTAMP, 'ICE', 2, 2),
       ('Hyundai', 'ix35', 'FCEV', '739-PD-2', 0.59, CURRENT_TIMESTAMP, 'FCEV', 3, 3),
       ('Peugeot', '208', 'e', '3-UP-869', 16.4, CURRENT_TIMESTAMP, 'BEV', 4, 4);

INSERT INTO rental_plan (car_id, user_id, available_from, available_until, price, created_at)
VALUES (1, 1, '2021-11-01', '2021-11-03', 80.00, CURRENT_TIMESTAMP),
       (2, 2, '2021-12-01', '2021-12-03', 60.00, CURRENT_TIMESTAMP),
       (3, 3, '2021-11-01', '2021-11-03', 90.00, CURRENT_TIMESTAMP),
       (4, 4, '2021-11-01', '2021-12-01', 60.00, CURRENT_TIMESTAMP);

INSERT INTO reservation (reservation_number, paid_at, price, status, user_id, created_at)
VALUES ('GK7K6JDK2P', CURRENT_TIMESTAMP, 1129.99, 'COMPLETED', 5, CURRENT_TIMESTAMP),
       ('LC1KVDVIS0', CURRENT_TIMESTAMP, 409.99, 'COMPLETED', 6, CURRENT_TIMESTAMP),
       ('1PWBX420XU', CURRENT_TIMESTAMP, 789.99, 'COMPLETED', 7, CURRENT_TIMESTAMP),
       ('X7KNYDER0H', CURRENT_TIMESTAMP, 839.99, 'COMPLETED', 8, CURRENT_TIMESTAMP),
       ('PM5D26E9IF', CURRENT_TIMESTAMP, 434.99, 'COMPLETED', 9, CURRENT_TIMESTAMP),
       ('2IAUI1BU3E', CURRENT_TIMESTAMP, 1659.99, 'COMPLETED', 10, CURRENT_TIMESTAMP);

INSERT INTO product (created_at, price, status, insurance_type_id, rental_plan_id, reservation_number)
VALUES (CURRENT_TIMESTAMP, 1080.00, 'COMPLETED', 'ALL_RISK', 1, 'GK7K6JDK2P'),
       (CURRENT_TIMESTAMP, 360.00, 'COMPLETED', 'ALL_RISK', 1, 'LC1KVDVIS0'),
       (CURRENT_TIMESTAMP, 720.00, 'COMPLETED', 'ALL_RISK_INTERNATIONAL', 1, '1PWBX420XU'),
       (CURRENT_TIMESTAMP, 810.00, 'COMPLETED', 'BASIC_COVERAGE', 2, 'X7KNYDER0H'),
       (CURRENT_TIMESTAMP, 405.00, 'COMPLETED', 'BASIC_COVERAGE', 3, 'PM5D26E9IF'),
       (CURRENT_TIMESTAMP, 1620.00, 'COMPLETED', 'BASIC_COVERAGE_INTERNATIONAL', 4, '2IAUI1BU3E');

INSERT INTO payment (created_at, paid_at, payment_status, price, reservation_number)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SUCCESS', 1129.99, 'GK7K6JDK2P'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SUCCESS', 409.99, 'LC1KVDVIS0'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SUCCESS', 789.99, '1PWBX420XU'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SUCCESS', 839.99, 'X7KNYDER0H'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SUCCESS', 434.99, 'PM5D26E9IF'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SUCCESS', 1659.99, '2IAUI1BU3E');

INSERT INTO car_timeslot_availability (car_id, timeslot_id, rental_plan_id, start_at, product_id, end_at, status, created_at)
VALUES (1, 1, 1, '2021-11-01 07:00:00', 2, '2021-11-01 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 2, 1, '2021-11-01 12:00:00', 3, '2021-11-01 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 3, 1, '2021-11-01 17:00:00', 3, '2021-11-01 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 1, 1, '2021-11-02 07:00:00', NULL, '2021-11-02 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 2, 1, '2021-11-02 12:00:00', NULL, '2021-11-02 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 3, 1, '2021-11-02 17:00:00', NULL, '2021-11-02 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 1, 1, '2021-11-03 07:00:00', NULL, '2021-11-03 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 2, 1, '2021-11-03 12:00:00', NULL, '2021-11-03 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 3, 1, '2021-11-03 17:00:00', NULL, '2021-11-03 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 1, 2, '2021-12-01 07:00:00', NULL, '2021-12-01 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 2, 2, '2021-12-01 12:00:00', NULL, '2021-12-01 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 3, 2, '2021-12-01 17:00:00', NULL, '2021-12-01 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 1, 2, '2021-12-02 07:00:00', NULL, '2021-12-02 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 2, 2, '2021-12-02 12:00:00', NULL, '2021-12-02 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 3, 2, '2021-12-02 17:00:00', NULL, '2021-12-02 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 1, 2, '2021-12-03 07:00:00', 4, '2021-12-03 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 2, 2, '2021-12-03 12:00:00', 4, '2021-12-03 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (2, 3, 2, '2021-12-03 17:00:00', 4, '2021-12-03 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 1, 3, '2021-11-01 07:00:00', NULL, '2021-11-01 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 2, 3, '2021-11-01 12:00:00', NULL, '2021-11-01 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 3, 3, '2021-11-01 17:00:00', NULL, '2021-11-01 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 1, 3, '2021-11-02 07:00:00', NULL, '2021-11-02 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 2, 3, '2021-11-02 12:00:00', NULL, '2021-11-02 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 3, 3, '2021-11-02 17:00:00', NULL, '2021-11-02 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 1, 3, '2021-11-03 07:00:00', NULL, '2021-11-03 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 2, 3, '2021-11-03 12:00:00', NULL, '2021-11-03 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (3, 3, 3, '2021-11-03 17:00:00', 5, '2021-11-03 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 1, 4, '2021-11-01 07:00:00', 6, '2021-11-01 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 2, 4, '2021-11-01 12:00:00', 6, '2021-11-01 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 3, 4, '2021-11-01 17:00:00', 6, '2021-11-01 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 1, 4, '2021-11-02 07:00:00', 6, '2021-11-02 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 2, 4, '2021-11-02 12:00:00', 6, '2021-11-02 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 3, 4, '2021-11-02 17:00:00', 6, '2021-11-02 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 1, 4, '2021-11-03 07:00:00', NULL, '2021-11-03 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 2, 4, '2021-11-03 12:00:00', NULL, '2021-11-03 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (4, 3, 4, '2021-11-03 17:00:00', NULL, '2021-11-03 21:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 1, 1, '2021-10-02 07:00:00', 1, '2021-10-02 11:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 2, 1, '2021-10-02 12:00:00', 1, '2021-10-02 16:30:00', 'OPEN', CURRENT_TIMESTAMP),
       (1, 3, 1, '2021-10-02 17:00:00', 1, '2021-10-02 21:30:00', 'OPEN', CURRENT_TIMESTAMP);

INSERT INTO driver_activity (acceleration, average_speed, created_at, distance_driven, last_synced_at, top_speed, car_id, reservation_number)
VALUES (10.6, 50, CURRENT_TIMESTAMP, 112.5, '2021-10-02 21:30:00', 170, 1, 'GK7K6JDK2P');