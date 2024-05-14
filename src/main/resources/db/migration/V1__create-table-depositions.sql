CREATE TABLE depositions (
  deposition_id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   testimony VARCHAR(255) NOT NULL,
   image_url VARCHAR(255),
   CONSTRAINT pk_depositions PRIMARY KEY (deposition_id)
);