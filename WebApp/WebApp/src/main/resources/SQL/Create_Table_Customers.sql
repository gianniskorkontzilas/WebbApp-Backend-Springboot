CREATE TABLE Customers (
    id SERIAL PRIMARY KEY,
    vat_number VARCHAR(15) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    store_id INT NOT NULL,
    FOREIGN KEY (store_id) REFERENCES Stores(id)
);