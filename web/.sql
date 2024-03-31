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

INSERT INTO `languages` (`ext`, `name`) VALUES
('ai', 'Adobe Illustrator File'),
('asm', 'Assembly Language Source File'),
('asp', 'Active Server Pages'),
('aspx', 'ASP.NET Page'),
('bash', 'Bash Script'),
('bat', 'Batch File'),
('bmp', 'Bitmap Image'),
('c', 'C'),
('cfg', 'Configuration File'),
('cgi', 'CGI Script'),
('class', 'Java Class File'),
('cmd', 'Windows Command Script'),
('conf', 'Configuration File'),
('confi', 'Configuration File'),
('cpp', 'C++'),
('cs', 'C#'),
('cspro', 'C# Project File'),
('css', 'CSS'),
('csv', 'CSV Data'),
('dll', 'Dynamic Link Library'),
('doc', 'Microsoft Word Document'),
('docx', 'Microsoft Word Open XML Document'),
('ear', 'Java Enterprise Archive'),
('env', 'Environment File'),
('exe', 'Executable File'),
('fxml', 'FXML'),
('gif', 'GIF Image'),
('go', 'Go Source File'),
('hs', 'Haskell Source File'),
('htacc', 'Apache .htaccess File'),
('html', 'HTML'),
('htpas', 'Apache .htpasswd File'),
('ico', 'Icon File'),
('ini', 'Configuration File'),
('jar', 'Java Archive'),
('java', 'Java'),
('jpeg', 'JPEG Image'),
('jpg', 'JPEG Image'),
('js', 'JavaScript'),
('json', 'JSON Data'),
('jsp', 'JavaServer Pages'),
('jsx', 'JSX File'),
('kt', 'Kotlin Source File'),
('less', 'Less Stylesheet'),
('licen', 'License File'),
('log', 'Log File'),
('lua', 'Lua Script'),
('makef', 'Makefile'),
('md', 'Markdown Document'),
('mp3', 'MP3 Audio'),
('mp4', 'MP4 Video'),
('odp', 'OpenDocument Presentation'),
('ods', 'OpenDocument Spreadsheet'),
('odt', 'OpenDocument Text Document'),
('pdf', 'PDF Document'),
('php', 'PHP'),
('pl', 'Perl Script'),
('png', 'PNG Image'),
('ppt', 'Microsoft PowerPoint Presentation'),
('pptx', 'Microsoft PowerPoint Open XML Presentation'),
('proj', 'Generic Project File'),
('prope', 'Properties File'),
('ps1', 'PowerShell Script'),
('psd', 'Adobe Photoshop Document'),
('py', 'Python'),
('rar', 'RAR Archive'),
('rb', 'Ruby Source File'),
('readm', 'Readme File'),
('rs', 'Rust'),
('rst', 'reStructuredText Document'),
('rtf', 'Rich Text Format'),
('scala', 'Scala Source File'),
('scss', 'Sass Stylesheet'),
('setti', 'Settings File'),
('sh', 'Shell Script'),
('sln', 'Visual Studio Solution File'),
('sql', 'SQL'),
('svg', 'Scalable Vector Graphics'),
('tiff', 'TIFF Image'),
('tpl', 'Template File'),
('ts', 'TypeScript File'),
('tsv', 'Tab-Separated Values File'),
('tsx', 'React TypeScript File'),
('twig', 'Twig Template'),
('txt', 'Text Document'),
('vb', 'Visual Basic Source File'),
('vbpro', 'Visual Basic Project File'),
('vbs', 'VBScript File'),
('vcxpr', 'Visual C++ Project File'),
('vue', 'Vue.js Component'),
('war', 'Java Web Archive'),
('xls', 'Microsoft Excel Spreadsheet'),
('xlsm', 'Microsoft Excel Macro-Enabled Workbook'),
('xlsx', 'Microsoft Excel Open XML Spreadsheet'),
('xml', 'XML Document'),
('xsd', 'XML Schema Definition'),
('yaml', 'YAML Document'),
('yml', 'YAML Document'),
('zip', 'ZIP Archive');
