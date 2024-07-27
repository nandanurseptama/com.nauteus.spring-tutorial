CREATE DATABASE spring_tutor owner postgres ENCODING 'UTF-8';

/** 
Please switch the database before execute these whole line line
**/


CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE public.users (
	id uuid DEFAULT uuid_generate_v4() NOT NULL,
	email varchar(256) DEFAULT NULL::character varying NULL,
	phone_number varchar(16) DEFAULT NULL::character varying NULL,
	"password" varchar(256) NOT NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at timestamp NULL,
	deleted_at timestamp NULL,
	CONSTRAINT users_id_pk PRIMARY KEY (id)
);

