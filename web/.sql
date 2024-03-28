CREATE DATABASE timecode;

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

CREATE TABLE languages(
   ext VARCHAR(5) PRIMARY KEY,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE oss(
   id_os INT UNSIGNED ZEROFILL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE users_projects(
   api_key CHAR(20) BINARY NOT NULL,
   id_project INT UNSIGNED ZEROFILL NOT NULL,
   CONSTRAINT pk_user_project PRIMARY KEY(api_key, id_project),
   CONSTRAINT fk_user_project FOREIGN KEY(api_key)
   REFERENCES users(api_key),
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

SELECT SUM(AL.modify_rows)
FROM activities_languages AS AL
INNER JOIN activities AS AC USING(id_activity)
WHERE AC.api_key = 'api_key'
AND AC.date = NOW();
-- sum rows modified today

SELECT AVG(sum_modify_rows) AS avg_modify_rows
FROM (
   SELECT id_activity, SUM(modify_rows) AS sum_modify_rows
   FROM activities_languages
   INNER JOIN activities AS AC USING(id_activity)
   WHERE AC.api_key = ''
   GROUP BY id_activity
) AS subtable;
-- avarage rows modified for each activity

SELECT SUM(modify_rows) / (NOW() - U.date + 1) AS daily_modify_rows
FROM activities_languages AS AL
INNER JOIN activities AS AC USING(id_activity)
INNER JOIN users AS U USING(api_key)
WHERE AC.api_key = '';
-- avarage rows modified for each day

SELECT L.name, ROUND(PL.num_rows / T.total_rows * 100, 1) AS percent
FROM projects_languages AS PL
INNER JOIN languages AS L USING (ext)
INNER JOIN (
   SELECT id_project, SUM(num_rows) AS total_rows
   FROM projects_languages
   GROUP BY id_project
) AS T USING (id_project)
WHERE PL.id_project = '0000000003';
-- percent project

SELECT L.name, ROUND(PL.num_rows / T.total_rows * 100, 1) AS percent
FROM projects_languages AS PL
INNER JOIN projects AS PR USING(id_project)
INNER JOIN users_projects AS UR USING(id_project)
INNER JOIN languages AS L USING (ext)
INNER JOIN (
   SELECT id_project, SUM(num_rows) AS total_rows
   FROM projects_languages
   GROUP BY id_project
) AS T USING (id_project)
WHERE UR.api_key = "tHmw03bVYtxqv6Augkxa"
-- percent user
