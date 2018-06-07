CREATE TABLE USERS (
  ID INT NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR(100) NOT NULL,
  EMAIL VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  ROLE VARCHAR(45) NOT NULL,
  LASTPASSWORDRESETDATE VARCHAR(45) NULL,
  ENABLED BIT(1) NULL DEFAULT 1,
  PRIMARY KEY (ID),
  UNIQUE INDEX EMAIL_UNIQUE (EMAIL ASC),
  UNIQUE INDEX USERNAME_UNIQUE (USERNAME ASC));


CREATE TABLE PRODUCT_TYPES (
  ID INT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(45) NOT NULL,
  DESCRIPTIONS VARCHAR(255) NULL,
  PRIMARY KEY (ID));


CREATE TABLE PRODUCTS (
  ID INT NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(100) NOT NULL,
  DESCRIPTIONS LONGTEXT NULL,
  PRICE DECIMAL NOT NULL,
  TYPE_FK INT NOT NULL,
  COLOR VARCHAR(100) NULL,
  PRODUCER VARCHAR(255) NULL,
  PRIMARY KEY (ID),
  INDEX PRODUCTS_TYPES_IDX (TYPE_FK ASC),
  INDEX PRODUCTS_NAME (NAME ASC),
  CONSTRAINT PRODUCTS_TYPES
    FOREIGN KEY (TYPE_FK)
    REFERENCES PRODUCT_TYPES (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE PICTURES (
  ID INT NOT NULL AUTO_INCREMENT,
  LABEL VARCHAR(255) NULL,
  SRC VARCHAR(45) NOT NULL,
  PRODUCT_FK INT NOT NULL,
  PRIMARY KEY (ID),
  INDEX PRODUCTS_IMAGES_IDX (PRODUCT_FK ASC),
  CONSTRAINT PRODUCTS_IMAGES
    FOREIGN KEY (PRODUCT_FK)
    REFERENCES PRODUCTS (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE RATINGS (
  ID INT NOT NULL AUTO_INCREMENT,
  USER_FK INT NOT NULL,
  PRODUCT_FK INT NOT NULL,
  RATING INT NOT NULL,
  DATE TIMESTAMP NOT NULL,
  DESCRIPTIONS LONGTEXT NULL,
  PRIMARY KEY (ID),
  INDEX USERS_RATINGS_IDX (USER_FK ASC),
  CONSTRAINT USERS_RATINGS
    FOREIGN KEY (USER_FK)
    REFERENCES USERS (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT PRODUCTS_RATINGS
  FOREIGN KEY (PRODUCT_FK)
  REFERENCES PRODUCTS (ID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);


CREATE TABLE COMMENTS (
  ID INT NOT NULL AUTO_INCREMENT,
  USER_FK INT NOT NULL,
  PRODUCT_FK INT NOT NULL,
  DATE TIMESTAMP NOT NULL,
  DESCRIPTIONS LONGTEXT NULL,
  PRIMARY KEY (ID),
  INDEX USERS_COMMENTS_IDX (USER_FK ASC),
  CONSTRAINT USERS_COMMENTS
    FOREIGN KEY (USER_FK)
    REFERENCES USERS (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT PRODUCTS_COMMENTS
  FOREIGN KEY (PRODUCT_FK)
  REFERENCES PRODUCTS (ID)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

    

