DROP SEQUENCE IF EXISTS users_seq;
DROP SEQUENCE IF EXISTS groups_seq;
DROP SEQUENCE IF EXISTS users_groups_seq;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS users_groups;
-- Table: groups
CREATE TABLE groups (
    group_id int  NOT NULL,
    group_name varchar(20)  NOT NULL,
    group_description text  NOT NULL,
    CONSTRAINT groups_pk PRIMARY KEY (group_id)
);
CREATE SEQUENCE groups_seq;

-- Table: users
CREATE TABLE users (
    user_id int  NOT NULL,
    user_login varchar(20)  NOT NULL,
    user_password varchar(20)  NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);
CREATE SEQUENCE users_seq;

-- Table: users_groups
CREATE TABLE users_groups (
    user_group_id int  NOT NULL,
    user_id int  NOT NULL,
    group_id int  NOT NULL,
    CONSTRAINT users_groups_pk PRIMARY KEY (user_group_id)
);
CREATE SEQUENCE users_groups_seq;

-- foreign keys
-- Reference: users_groups_groups (table: users_groups)
ALTER TABLE users_groups ADD CONSTRAINT users_groups_groups
    FOREIGN KEY (group_id)
    REFERENCES groups (group_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: users_groups_users (table: users_groups)
ALTER TABLE users_groups ADD CONSTRAINT users_groups_users
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

