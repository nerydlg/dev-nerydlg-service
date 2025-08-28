-- DB Schema for nerydlg.dev
-- WARNING: This will drop any preexisting table with the same name
-- DROP DOMAIN TABLE

DROP TABLE IF EXISTS n_domain;
-- CREATE DOMAIN TABLE
CREATE TABLE n_domain (
    id SERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    description VARCHAR(255),
    expiration_date TIMESTAMP NOT NULL default '2050-01-01 00:00:00'
);

-- DROP TABLE IF EXISTS
DROP TABLE IF EXISTS n_user;
-- CREATE USER TABLE
CREATE TABLE n_user (
	id SERIAL PRIMARY KEY,
    domain_id INTEGER REFERENCES n_domain(id),
	username VARCHAR(50) NOT NULL,
	password VARCHAR(255) NOT NULL,
    status int NOT NULL DEFAULT 0, -- 0 = active, 1 = suspended, 99 = deleted
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    created_at TIMESTAMP NOT NULL default now(),
    updated_at TIMESTAMP NOT NULL default now(),
    expiration_date TIMESTAMP NOT NULL default '2050-01-01 00:00:00'
);

-- DROP TABLE n_tag IF EXISTS
DROP TABLE IF EXISTS n_tag;
-- CREATE TABLE TAG
CREATE TABLE n_tag(
    id SERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL
);

-- DROP TABLE n_blog IF EXISTS
DROP TABLE IF EXISTS n_blog;
-- CREATE TABLE n_blog
CREATE TABLE n_blog(
    id SERIAL PRIMARY KEY,
    title VARCHAR(180) NOT NULL,
    content TEXT NOT NULL,
    publication_date TIMESTAMP NOT NULL default now(),
    updated_at TIMESTAMP NOT NULL default now(),
    lang_code VARCHAR(10) NOT NULL,
    user_id INTEGER REFERENCES n_user(id),
    domain_id INTEGER REFERENCES n_domain(id)
);

-- DROP TABLE n_blog_tags
DROP TABLE IF EXISTS "n_blog_tags";
-- CRATE TABLE n_blog_tags
CREATE TABLE n_blog_tags(
    id SERIAL PRIMARY KEY,
    blog_id INTEGER REFERENCES n_blog(id),
    tag_id INTEGER REFERENCES n_tag(id)
);

-- DROP TABLE n_comment
DROP TABLE IF EXISTS n_comment;
-- CRATE TABLE n_comment
CREATE TABLE n_comment(
    id SERIAL PRIMARY KEY,
    blog_id INTEGER REFERENCES n_blog(id),
    author VARCHAR(120) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL default now()
);