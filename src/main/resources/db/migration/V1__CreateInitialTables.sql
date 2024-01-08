CREATE TABLE roles (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255),
                       PRIMARY KEY (id)
);


CREATE TABLE customers (
                           id INT NOT NULL AUTO_INCREMENT,
                           name VARCHAR(255),
                           email VARCHAR(255),
                           phone_number VARCHAR(255),
                           PRIMARY KEY (id)
);
CREATE TABLE user
(
    id         int          NOT NULL AUTO_INCREMENT,
    username   varchar(20)  NOT NULL,
    password   varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE user_role
(
    id        int         NOT NULL AUTO_INCREMENT,
    user_id   int         NOT NULL,
    role_name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES user (id)
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
                                 PRIMARY KEY (id),
                                 FOREIGN KEY (account_id) REFERENCES  user(id)
);
CREATE TABLE parking_garage_utility (
                                        id INT NOT NULL AUTO_INCREMENT,
                                        toilet BOOLEAN,
                                        electric_charge_point BOOLEAN,
                                        floors INT,
                                        parking_spaces INT,
                                        parking_garage_id INT,
                                        parking_spaces_electric INT,
                                        FOREIGN KEY (parking_garage_id) REFERENCES parking_garages(id),
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

CREATE TABLE image (
                         id INT NOT NULL AUTO_INCREMENT,
                         image_path VARCHAR(255),
                         garage_id INT NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (garage_id) REFERENCES parking_garages(id)
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
                          price DOUBLE,
                          PRIMARY KEY (id),
                          FOREIGN KEY (customer_id) REFERENCES customers(id),
                          FOREIGN KEY (car_id) REFERENCES cars(id),
                          FOREIGN KEY (garage_id) REFERENCES parking_garages(id),
                          FOREIGN KEY (service_id) REFERENCES service(id)
);


CREATE TABLE price_list (
                          id INT NOT NULL AUTO_INCREMENT,
                          garage_id INT,
                          start_date VARCHAR(2000) NOT NULL,
                          end_date VARCHAR(2000) NOT NULL,
                          extra_day_price DOUBLE,
                          day1_price DOUBLE,
                          day2_price DOUBLE,
                          day3_price DOUBLE,
                          day4_price DOUBLE,
                          day5_price DOUBLE,
                          day6_price DOUBLE,
                          day7_price DOUBLE,
                          day8_price DOUBLE,
                          day9_price DOUBLE,
                          day10_price DOUBLE,
                          day11_price DOUBLE,
                          day12_price DOUBLE,
                          day13_price DOUBLE,
                          day14_price DOUBLE,
                          day15_price DOUBLE,
                          day16_price DOUBLE,
                          day17_price DOUBLE,
                          day18_price DOUBLE,
                          day19_price DOUBLE,
                          day20_price DOUBLE,
                          day21_price DOUBLE,
                          day22_price DOUBLE,
                          day23_price DOUBLE,
                          day24_price DOUBLE,
                          day25_price DOUBLE,
                          day26_price DOUBLE,
                          day27_price DOUBLE,
                          day28_price DOUBLE,
                          day29_price DOUBLE,
                          day30_price DOUBLE,
                          PRIMARY KEY (id),
                          FOREIGN KEY (garage_id) REFERENCES parking_garages(id)
);
