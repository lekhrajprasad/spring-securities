ALTER TABLE myusers DROP CONSTRAINT IF EXISTS "CONSTRAINT_85C";
DROP TABLE IF EXISTS myusers;
create table myusers(
    username VARCHAR(35) PRIMARY KEY,
    password VARCHAR(200) NOT NULL,
    firstname VARCHAR(35),
    lastname VARCHAR(35),
    email VARCHAR(35),
    phone VARCHAR(10),
    active TINYINT NOT NULL
);
DROP TABLE IF EXISTS myroles;
create table myroles(
    user_role_id int PRIMARY KEY,
    username varchar(35) NOT NULL,
    role varchar(25) NOT NULL,
    CONSTRAINT CONSTRAINT_85C FOREIGN KEY (username) REFERENCES myusers(username)
);
insert into myusers values('myhello','$2a$10$v7lmEJMsm4h7u66q3Iv6LePkJE8zUBJxlakT0ZfFvUEBB2FUkW81W','hello','hello','hello@jlc',12345,1);
insert into myroles values(1,'myhello','ROLE_CUSTOMER');
--generated password using https://bcrypt-generator.com/ round consider must be matched with round