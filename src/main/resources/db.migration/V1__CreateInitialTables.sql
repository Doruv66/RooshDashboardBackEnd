CREATE TABLE Admin (
                       id INT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(255),
                       password VARCHAR(255),
                       parking_garage_id INT,
                       PRIMARY KEY (id)
);

CREATE TABLE Parking_garage (
                                id INT NOT NULL AUTO_INCREMENT,
                                name VARCHAR(255),
                                airport VARCHAR(255),
                                location VARCHAR(255),
                                travelTime INT,
                                travelDistance INT,
                                phoneNumber INT,
                                booking_id INT,
                                parking_garage_utility_id INT,
                                PRIMARY KEY (id),
                                FOREIGN KEY (parking_garage_utility_id) REFERENCES Parking_garage(id)
);

CREATE TABLE Parking_garage_utility (
                                id INT NOT NULL AUTO_INCREMENT,
                                parking_garage_id INT,
                                toilet BOOLEAN,
                                electricChargePoint BOOLEAN,
                                floors INT,
                                parkingSpaces INT,
                                parkingSpacesElectric INT,
                                PRIMARY KEY (id)
);

CREATE TABLE Customer (
                          id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(255),
                          email VARCHAR(255),
                          mobile INT,
                          PRIMARY KEY (id)
);

CREATE TABLE Car (
                     id INT NOT NULL AUTO_INCREMENT,
                     customer_id INT,
                     license_plate VARCHAR(255),
                     brand VARCHAR(255),
                     model VARCHAR(255),
                     electric BOOLEAN,
                     PRIMARY KEY (Id),
                     FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

CREATE TABLE Bookings (
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
                          FOREIGN KEY (customer_id) REFERENCES Customer(id),
                          FOREIGN KEY (car_id) REFERENCES Car(id)
);


CREATE TABLE Service (
                         id INT NOT NULL AUTO_INCREMENT,
                         type ENUM('Valet', 'Shuttle'), -- actual enum values here
                         PRIMARY KEY (Id)
    );