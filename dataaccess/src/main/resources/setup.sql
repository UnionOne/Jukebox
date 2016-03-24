CREATE TABLE account(
  id SERIAL PRIMARY KEY NOT NULL,
  login CHAR(50) UNIQUE NOT NULL,
  email CHAR(50) UNIQUE NOT NULL,
  password CHAR(50) NOT NULL,
  first_name CHAR(50) NOT NULL,
  last_name CHAR(50) NOT NULL,
  jukebox_id INTEGER NOT NULL
);

CREATE TABLE role(
  id SERIAL PRIMARY KEY NOT NULL,
  name CHAR(25) NOT NULL
);

CREATE TABLE account_role(
  id SERIAL PRIMARY KEY NOT NULL,
  account_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL
);

CREATE TABLE track(
  id SERIAL PRIMARY KEY NOT NULL,
  name CHAR(50) NOT NULL,
  duration CHAR(50) NOT NULL,
  genre CHAR(50) NOT NULL,
  album CHAR(50) NOT NULL,
  band CHAR(50) NOT NULL,
  link CHAR(100) NOT NULL
);

CREATE TABLE jukebox(
  id SERIAL PRIMARY KEY NOT NULL
);

CREATE TABLE jukebox_track(
  id SERIAL PRIMARY KEY NOT NULL,
  jukebox_id INTEGER NOT NULL,
  track_id INTEGER NOT NULL
);

TRUNCATE TABLE account_role RESTART IDENTITY CASCADE;

TRUNCATE TABLE account RESTART IDENTITY CASCADE;

TRUNCATE TABLE role RESTART IDENTITY CASCADE;
