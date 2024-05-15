CREATE DATABASE LECTI;
USE LECTI;

CREATE TABLE USERS (
id INT PRIMARY KEY NOT NULL auto_increment,
first_name VARCHAR(40) NOT NULL,
last_name VARCHAR(40) NOT NULL,
password VARCHAR(200) NOT NULL,
email VARCHAR(40) NOT NULL
);


CREATE TABLE PLAYER(
id INT PRIMARY KEY NOT NULL auto_increment,
player_name VARCHAR(40) NOT NULL,
birth_date DATETIME NOT NULL,
total_crowns INT DEFAULT 0,
spent_crowns INT DEFAULT 0,
user_id INT,
recommended_module INT,
foreign key (USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE AVATAR(
id INT PRIMARY KEY NOT NULL auto_increment,
avatar_name VARCHAR(40) NOT NULL,
url VARCHAR(50),
value INT NOT NULL
);


CREATE TABLE UNLOCKED_AVATAR(
player_id INT,
avatar_id INT,
foreign key (player_id) REFERENCES PLAYER(ID),
foreign key (avatar_id) REFERENCES AVATAR(ID)
);

CREATE TABLE MODULE(
id INT PRIMARY KEY NOT NULL auto_increment,
description VARCHAR(40) NOT NULL
);

CREATE TABLE APPLE(
id INT PRIMARY KEY NOT NULL auto_increment,
name VARCHAR(40) NOT NULL,
module_id INT,
max_score INT,
foreign key (module_id) REFERENCES MODULE(id)
);

CREATE TABLE EXERCISE_TYPE(
id INT PRIMARY KEY NOT NULL auto_increment,
description VARCHAR(40) NOT NULL
);

CREATE TABLE EXERCISE(
id INT PRIMARY KEY NOT NULL auto_increment,
name VARCHAR(40) NOT NULL,
exercise_type_id INT,
apple_id INT,
parameters VARCHAR(300) NOT NULL,
foreign key (exercise_type_id) REFERENCES EXERCISE_TYPE(id),
foreign key (apple_id) REFERENCES APPLE(id)
);


CREATE TABLE RESULT(
player_id INT,
apple_id INT,
score INT,
foreign key (player_id) REFERENCES PLAYER(id),
foreign key (apple_id) REFERENCES APPLE(id)
);










