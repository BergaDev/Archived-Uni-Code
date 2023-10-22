/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

CREATE TABLE EMPLOYEE(
ENUM 	    DECIMAL(12)	NOT NULL,
FNAME	    VARCHAR(50)	NOT NULL,
INITIALS    VARCHAR(5)	    NULL,
LNAME       VARCHAR(50) NOT NULL,
DOB 	    DATE            NULL,
BLDG        DECIMAL(3) 	NOT NULL,
STREET      VARCHAR(50)	NOT NULL,
SUBURB      VARCHAR(50)	NOT NULL,
STATE	    VARCHAR(5)	NOT NULL,
ZIPCODE     DECIMAL(4)	NOT NULL,
 CONSTRAINT EMPLOYEE_PKEY PRIMARY KEY(ENUM) );

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

CREATE TABLE DRIVER(
ENUM 	    DECIMAL(12) NOT NULL,
LNUM 	    DECIMAL(8) 	NOT NULL,
STATUS 	    VARCHAR(10) NOT NULL,
 CONSTRAINT DRIVER_PKEY PRIMARY KEY(ENUM),
 CONSTRAINT DRIVER_UNIQUE UNIQUE(LNUM),
 CONSTRAINT DRIVER_FKEY FOREIGN KEY(ENUM) REFERENCES EMPLOYEE(ENUM),
 CONSTRAINT DRIVER_STATUS CHECK ( STATUS IN ('AVAILABLE', 'BUSY', 'ON LEAVE')) );

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

CREATE TABLE ADMIN(
ENUM 	    DECIMAL(12) NOT NULL,
POSITION    VARCHAR(50) NOT NULL,
 CONSTRAINT ADMIN_PKEY PRIMARY KEY(ENUM),
 CONSTRAINT ADMIN_FKEY FOREIGN KEY(ENUM) REFERENCES EMPLOYEE(ENUM) );

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

CREATE TABLE TRUCK(
REGNUM 	    VARCHAR(10) NOT NULL,
CAPACITY    DECIMAL(7) 	NOT NULL,
WEIGHT 	    DECIMAL(7) 	NOT NULL,
STATUS 	    VARCHAR(10) NOT NULL,
 CONSTRAINT TRUCK_PKEY PRIMARY KEY(REGNUM),
 CONSTRAINT TRUCK_STATUS CHECK ( STATUS IN ('AVAILABLE', 'USED', 'MAINTAINED')),
 CONSTRAINT TRUCK_WEIGHT CHECK ( WEIGHT > 0.0 AND WEIGHT < 500000 ),
 CONSTRAINT TRUCK_CAPACITY CHECK ( CAPACITY > 0.0 AND CAPACITY < 100000 ) );

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

CREATE TABLE TRIP(
TNUM 	    DECIMAL(10) NOT NULL,
LNUM 	    DECIMAL(8) 	NOT NULL,
REGNUM 	    VARCHAR(10) NOT NULL,
TDATE       DATE 	NOT NULL,
 CONSTRAINT TRIP_PKEY PRIMARY KEY (TNUM),
 CONSTRAINT TRIP_CKEY UNIQUE (LNUM, REGNUM, TDATE),
 CONSTRAINT TRIP_FKEY1 FOREIGN KEY (LNUM) REFERENCES DRIVER(LNUM),
 CONSTRAINT TRIP_FKEY2 FOREIGN KEY (REGNUM) REFERENCES TRUCK(REGNUM) );

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

CREATE TABLE TRIPLEG(
TNUM 	    DECIMAL(10) NOT NULL,
LEGNUM 	    DECIMAL(2) 	NOT NULL,
DEPARTURE   VARCHAR(30) NOT NULL,
DESTINATION VARCHAR(30) NOT NULL,
 CONSTRAINT TRIPLEG_PKEY PRIMARY KEY (TNUM, LEGNUM),
 CONSTRAINT TRIPLEG_UNIQUE UNIQUE(TNUM, DEPARTURE, DESTINATION),
 CONSTRAINT TRIPLEG_FKEY1 FOREIGN KEY (TNUM) REFERENCES TRIP(TNUM) );

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

