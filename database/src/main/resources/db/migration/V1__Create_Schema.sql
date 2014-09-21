CREATE TABLE Person (
  id int not null AUTO_INCREMENT,
  name varchar(100) not null,
  surname varchar(100) not null,

  CONSTRAINT person_pk PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE User (
  id int not null AUTO_INCREMENT,
  username varchar(100) not null,
  password varchar(100) not null,

  person_id int,

  CONSTRAINT user_pk PRIMARY KEY (id),
  INDEX user_person_idx (person_id),
  FOREIGN KEY (person_id) REFERENCES Person(id) ON DELETE CASCADE
) ENGINE=INNODB;