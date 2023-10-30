CREATE TABLE Admin (
                       Id INT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(255),
                       password VARCHAR(255),
                       parking_garage_id INT,
                       PRIMARY KEY (Id)
);

CREATE TABLE Parking_garage (
                                Id INT NOT NULL AUTO_INCREMENT,
                                location VARCHAR(255),
                                booking_id INT,
                                PRIMARY KEY (Id)
);

CREATE TABLE Customer (
                          Id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(255),
                          email VARCHAR(255),
                          mobile INT,
                          PRIMARY KEY (Id)
);

CREATE TABLE Car (
                     Id INT NOT NULL AUTO_INCREMENT,
                     customer_id INT,
                     license_plate VARCHAR(255),
                     brand VARCHAR(255),
                     model VARCHAR(255),
                     electric BOOLEAN,
                     PRIMARY KEY (Id),
                     FOREIGN KEY (customer_id) REFERENCES Customer(Id)
);

CREATE TABLE Bookings (
                          Id INT NOT NULL AUTO_INCREMENT,
                          customer_id INT,
                          car_id INT,
                          start_date DATETIME,
                          end_date DATETIME,
                          flight_number_departure INT,
                          flight_number_arrival INT,
                          location_id INT,
                          service_id INT,
                          PRIMARY KEY (Id),
                          FOREIGN KEY (customer_id) REFERENCES Customer(Id),
                          FOREIGN KEY (car_id) REFERENCES Car(Id)
);

CREATE TABLE Location (
                          Id INT NOT NULL AUTO_INCREMENT,
                          parkingSlot INT,
                          floor INT,
                          PRIMARY KEY (Id)
);

CREATE TABLE Service (
                         Id INT NOT NULL AUTO_INCREMENT,
                         price DOUBLE,
                         type ENUM('type1', 'type2'), <-- actual enum values here
                         PRIMARY KEY (Id)
    );