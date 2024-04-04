CREATE TABLE weather_event
(
    id SERIAL PRIMARY KEY,
    date_time TIMESTAMP(6) WITHOUT TIME ZONE,
    city VARCHAR(255),
    apparent_temperature DOUBLE PRECISION,
    humidity DOUBLE PRECISION,
    precip_intensity DOUBLE PRECISION,
    precip_probability DOUBLE PRECISION,
    precip_type VARCHAR(255),
    temperature DOUBLE PRECISION,
    visibility DOUBLE PRECISION,
    wind_speed DOUBLE PRECISION
);

CREATE SEQUENCE weather_event_seq START WITH 1 INCREMENT BY 1;