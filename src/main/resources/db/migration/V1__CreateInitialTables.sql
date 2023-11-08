CREATE TABLE roles (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255),
                       PRIMARY KEY (id)
);
CREATE TABLE accounts (
                          id INT NOT NULL AUTO_INCREMENT,
                          username VARCHAR(255),
                          email VARCHAR(255),
                          password VARCHAR(255),
                          role_id INT,
                          PRIMARY KEY (id),
                          FOREIGN KEY(role_id) REFERENCES roles(id)
);
CREATE TABLE parking_garage_utility (
                                        id INT NOT NULL AUTO_INCREMENT,
                                        toilet BOOLEAN,
                                        electric_charge_point BOOLEAN,
                                        floors INT,
                                        parking_spaces INT,
                                        parking_spaces_electric INT,
                                        PRIMARY KEY (id)
);

CREATE TABLE parking_garages (
                                 id INT NOT NULL AUTO_INCREMENT,
                                 name VARCHAR(255),
                                 airport VARCHAR(255),
                                 location VARCHAR(255),
                                 account_id INT,
                                 travel_time INT,
                                 travel_distance INT,
                                 phone_number VARCHAR(255),
                                 parking_garage_utility_id INT,
                                 PRIMARY KEY (id),
                                 FOREIGN KEY (parking_garage_utility_id) REFERENCES parking_garage_utility(id),
                                 FOREIGN KEY (account_id) REFERENCES  accounts(id)
);


CREATE TABLE customers (
                           id INT NOT NULL AUTO_INCREMENT,
                           name VARCHAR(255),
                           email VARCHAR(255),
                           phone_number VARCHAR(255),
                           PRIMARY KEY (id)
);

CREATE TABLE cars (
                      id INT NOT NULL AUTO_INCREMENT,
                      customer_id INT,
                      license_plate VARCHAR(255),
                      brand VARCHAR(255),
                      model VARCHAR(255),
                      electric BOOLEAN,
                      PRIMARY KEY (Id),
                      FOREIGN KEY (customer_id) REFERENCES customers(id)
);
CREATE TABLE service (
                         id INT NOT NULL AUTO_INCREMENT,
                         type ENUM('Valet', 'Shuttle'), -- actual enum values here
                         PRIMARY KEY (id)
);


CREATE TABLE bookings (
                          id INT NOT NULL AUTO_INCREMENT,
                          customer_id INT,
                          car_id INT,
                          start_date DATETIME,
                          end_date DATETIME,
                          flight_number_departure INT,
                          flight_number_arrival INT,
                          garage_id INT,
                          service_id INT,
                          PRIMARY KEY (id),
                          FOREIGN KEY (customer_id) REFERENCES customers(id),
                          FOREIGN KEY (car_id) REFERENCES cars(id),
                          FOREIGN KEY (garage_id) REFERENCES parking_garages(id),
                          FOREIGN KEY (service_id) REFERENCES service(id)
);

