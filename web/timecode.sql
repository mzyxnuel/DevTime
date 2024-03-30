CREATE DATABASE timecode;

USE timecode;

CREATE TABLE users(
   api_key CHAR(20) BINARY PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   surname VARCHAR(100) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   psw VARCHAR(100) NOT NULL,
   date DATE NOT NULL
);

CREATE TABLE projects(
   id_project INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE languages(
   ext VARCHAR(5) PRIMARY KEY,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE oss(
   id_os INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE activities(
   id_activity INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   date DATE NOT NULL,
   time INT NOT NULL,
   api_key CHAR(20) BINARY NOT NULL,
   id_project INT UNSIGNED ZEROFILL NOT NULL,
   id_os INT UNSIGNED ZEROFILL,
   CONSTRAINT fk_user_activity FOREIGN KEY(api_key)
   REFERENCES users(api_key)
   ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT fk_project_activity FOREIGN KEY(id_project)
   REFERENCES projects(id_project)
   ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT fk_os_activity FOREIGN KEY(id_os)
   REFERENCES oss(id_os)
   ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE users_projects(
   api_key CHAR(20) BINARY NOT NULL,
   id_project INT UNSIGNED ZEROFILL NOT NULL,
   CONSTRAINT pk_user_project PRIMARY KEY(api_key, id_project),
   CONSTRAINT fk_user_project FOREIGN KEY(api_key)
   REFERENCES users(api_key)
   ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT fk_project_user FOREIGN KEY(id_project)
   REFERENCES projects(id_project)
   ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE projects_languages(
   id_project INT UNSIGNED ZEROFILL NOT NULL,
   ext VARCHAR(5) NOT NULL,
   num_rows INT NOT NULL,
   CONSTRAINT pk_project_language PRIMARY KEY(id_project, ext),
   CONSTRAINT fk_project_language FOREIGN KEY(id_project)
   REFERENCES projects(id_project)
   ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT fk_language_project FOREIGN KEY(ext)
   REFERENCES languages(ext)
   ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE activities_languages(
   id_activity INT UNSIGNED ZEROFILL NOT NULL,
   ext VARCHAR(5) NOT NULL,
   modify_rows INT NOT NULL,
   CONSTRAINT pk_activity_language PRIMARY KEY(id_activity, ext),
   CONSTRAINT fk_activity_language FOREIGN KEY(id_activity)
   REFERENCES activities(id_activity)
   ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT fk_language_activity FOREIGN KEY(ext)
   REFERENCES languages(ext)
   ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO oss VALUES(NULL, 'Windows'), (NULL, 'Linux'), (NULL, 'MacOS');
