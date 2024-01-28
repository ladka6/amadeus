CREATE TABLE airports (
    id UUID PRIMARY KEY,
    city VARCHAR(255) NOT NULL
);

CREATE TABLE flights (
    id UUID PRIMARY KEY,
    departure_airport_id UUID REFERENCES airports(id),
    arrival_airport_id UUID REFERENCES airports(id),
    departure_date_time TIMESTAMP,
    return_date_time TIMESTAMP,
    price DOUBLE PRECISION
);
