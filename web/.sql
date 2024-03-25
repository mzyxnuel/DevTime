CREATE DATABASE timecode;

CREATE TABLE users(
   id_user INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   surname VARCHAR(100) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   psw VARCHAR(100) NOT NULL,
   date DATE NOT NULL,
   CONSTRAINT c_email CHECK(email LIKE '%@%')
);

CREATE TABLE projects(
   id_project INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE activities(
   id_activity INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   date DATE UNIQUE NOT NULL,
   time INT NOT NULL,
   id_user INT UNSIGNED ZEROFILL NOT NULL,
   id_project INT UNSIGNED ZEROFILL NOT NULL,
   id_os INT UNSIGNED ZEROFILL,
   CONSTRAINT fk_user_activity FOREIGN KEY(id_user)
   REFERENCES users(id_user),
   CONSTRAINT fk_project_activity FOREIGN KEY(id_project)
   REFERENCES projects(id_project),
   CONSTRAINT fk_os_activity FOREIGN KEY(id_os)
   REFERENCES oss(id_os)
);

CREATE TABLE languages(
   ext VARCHAR(5) PRIMARY KEY,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE oss(
   id_os INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE users_projects(
   id_user INT UNSIGNED ZEROFILL NOT NULL,
   id_project INT UNSIGNED ZEROFILL NOT NULL,
   CONSTRAINT pk_user_project PRIMARY KEY(id_user, id_project),
   CONSTRAINT fk_user_project FOREIGN KEY(id_user)
   REFERENCES users(id_user),
   CONSTRAINT fk_project_user FOREIGN KEY(id_project)
   REFERENCES projects(id_project)
);

CREATE TABLE projects_languages(
   id_project INT UNSIGNED ZEROFILL NOT NULL,
   ext VARCHAR(5),
   num_rows INT NOT NULL,
   CONSTRAINT pk_project_language PRIMARY KEY(id_project, ext),
   CONSTRAINT fk_project_language FOREIGN KEY(id_project)
   REFERENCES projects(id_project),
   CONSTRAINT fk_language_project FOREIGN KEY(ext)
   REFERENCES languages(ext)
);

CREATE TABLE activities_languages(
   id_activity INT UNSIGNED ZEROFILL NOT NULL,
   ext VARCHAR(5),
   modify_rows INT NOT NULL,
   CONSTRAINT pk_activity_language PRIMARY KEY(id_activity, ext),
   CONSTRAINT fk_activity_language FOREIGN KEY(id_activity)
   REFERENCES activities(id_activity),
   CONSTRAINT fk_language_activity FOREIGN KEY(ext)
   REFERENCES languages(ext)
);

SELECT SUM(AL.modify_rows)
FROM activities_languages AS AL
INNER JOIN activities AS AC USING(id_activity)
WHERE AC.id_user = 'id_user'
AND AC.date = NOW();
-- sum rows modified today

SELECT AVG(sum_modify_rows) AS avg_modify_rows
FROM (
   SELECT id_activity, SUM(modify_rows) AS sum_modify_rows
   FROM activities_languages
   INNER JOIN activities AS AC USING(id_activity)
   WHERE AC.id_user = ''
   GROUP BY id_activity
) AS subtable;
-- avarage rows modified for each activity

SELECT SUM(modify_rows) / (NOW() - U.date + 1) AS daily_modify_rows
FROM activities_languages AS AL
INNER JOIN activities AS AC USING(id_activity)
INNER JOIN users AS U USING(id_user)
WHERE AC.id_user = '';
-- avarage rows modified for each day
