DROP TABLE TRANSPORTATION;
DROP TABLE CARGO;
DROP TABLE CARRIER;

CREATE TABLE IF NOT EXISTS  CARGO (
   ID                       BIGINT  NOT NULL PRIMARY KEY,
   NAME                     VARCHAR(50) NOT NULL,
   WEIGHT                   INT NOT NULL,
   ENTITY_TYPE              VARCHAR(10) NOT NULL,
   CLOTHERS_SIZE            VARCHAR(50),
   CLOTHERS_MATERIAL         VARCHAR(50),
   FOOD_EXPIRATION_DATE     TIMESTAMP,
   FOOD_STORE_TEMPERATURE   INT
);

CREATE TABLE IF NOT EXISTS CARRIER (
   ID           BIGINT      NOT NULL PRIMARY KEY,
   NAME         VARCHAR(50) NOT NULL,
   ADDRESS      VARCHAR(50) NOT NULL,
   ENTITY_TYPE  VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS TRANSPORTATION (
   ID                     BIGINT       NOT NULL  PRIMARY KEY,
   CARGO_ID               BIGINT       NOT NULL,
   CARRIER_ID             BIGINT       NOT NULL,
   DESCRIPTION            VARCHAR(50),
   BILL_TO                VARCHAR(50)  NOT NULL,
   BEGIN_DATE             TIMESTAMP,

   FOREIGN KEY (CARGO_ID)   references  CARGO(ID),
   FOREIGN KEY (CARRIER_ID) references  CARRIER(ID)
);
