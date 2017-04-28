-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

CREATE SEQUENCE app_user_id_seq
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;

CREATE TABLE app_user (
	app_user_id integer DEFAULT nextval('app_user_id_seq'::regclass) NOT NULL,
	first_name varchar(32) NOT NULL,
	last_name varchar(32) NOT NULL,
	email varchar(64) NOT NULL UNIQUE,
	phone varchar(32),
	password_hash varchar(128) NOT NULL,
	salt varchar(256) NOT NULL,
	create_date timestamp NOT NULL DEFAULT now(),
	user_type integer NOT NULL DEFAULT 1,
	num_verified_potholes integer DEFAULT 0,
	CONSTRAINT pk_app_user_app_user_id PRIMARY KEY (app_user_id)
);

CREATE TABLE potholes (
	pothole_id serial NOT NULL,
	status int DEFAULT 0 NOT NULL,
	reported_by int NOT NULL,
	date_reported timestamp DEFAULT NOW() NOT NULL,
	latitude DECIMAL(9,6) NOT NULL,
	longitude DECIMAL(9,6) NOT NULL,
	severity int DEFAULT 0 NOT NULL,
	comments varchar(128),
	date_scheduled_for_repair DATE,
	CONSTRAINT pk_potholes_pothole_id PRIMARY KEY (pothole_id),
	CONSTRAINT fk_reported_by_user_id FOREIGN KEY (reported_by) REFERENCES app_user(app_user_id)
);



COMMIT;