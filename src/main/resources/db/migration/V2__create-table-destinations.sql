CREATE TABLE destinations (
  destination_id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   price DOUBLE PRECISION NOT NULL,
   image BYTEA NOT NULL,
   CONSTRAINT pk_destinations PRIMARY KEY (destination_id)
);