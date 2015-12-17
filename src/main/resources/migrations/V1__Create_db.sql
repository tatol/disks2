CREATE TABLE disk (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(30) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO disk VALUES (1,'крестный отец'),(2,'воин'),(3,'маска'),(4,'крокодил Данди'),(5,'Алладин'),(6,'Халк'),(7,'Терминатор'),(8,'мизери'),(9,'день сурка'),(10,'кошмар на улице вязов');

CREATE TABLE user (
id int(11) NOT NULL AUTO_INCREMENT,
login varchar(30) NOT NULL,
password varchar(60) NOT NULL,
enabled tinyint(4) NOT NULL DEFAULT '1',
PRIMARY KEY (id),
UNIQUE KEY uniq (login)
);

INSERT INTO user VALUES (1,'user','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y',1),(2,'user2','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y',1),(3,'admin','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y',1);

CREATE TABLE takenitem (
  id int(11) NOT NULL AUTO_INCREMENT,
  diskid int(11) NOT NULL,
  userid int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_user_idx (userid),
  KEY fk_disk_idx (diskid),
  CONSTRAINT fk_disk_idx FOREIGN KEY (diskid) REFERENCES disk (id),
  CONSTRAINT fk_user_idx FOREIGN KEY (userid) REFERENCES user (id)
);

INSERT INTO takenitem VALUES (23,1,2),(26,5,1),(36,3,2),(42,10,2),(43,4,1),(44,6,1);

CREATE TABLE givenitem (
  id int(11) NOT NULL AUTO_INCREMENT,
  diskid int(11) NOT NULL,
  fromid int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_user_idx_idx (fromid),
  KEY fk_disk_idx_idx (diskid),
  CONSTRAINT fk_disk_idx_idx FOREIGN KEY (diskid) REFERENCES disk (id),
  CONSTRAINT fk_user_idx_idx FOREIGN KEY (fromid) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO givenitem VALUES (2,5,2),(5,1,1);

CREATE TABLE user_roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uni_user_role (role,user_id),
  KEY fk_user (user_id),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user (id)
);

INSERT INTO user_roles VALUES (3,3,'role_admin'),(1,1,'role_user'),(6,2,'role_user');