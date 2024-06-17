CREATE TABLE destination_description (
   destination_description_id UUID NOT NULL,
   destination_id UUID NOT NULL,
   image1 BYTEA NOT NULL,
   image2 BYTEA NOT NULL,
   name VARCHAR(255) NOT NULL,
   meta VARCHAR(160) NOT NULL,
   descriptive_text VARCHAR(1000),
   CONSTRAINT pk_destination_description PRIMARY KEY (destination_description_id)
);