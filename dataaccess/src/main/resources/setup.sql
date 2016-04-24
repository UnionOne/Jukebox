CREATE TABLE account(
  id SERIAL PRIMARY KEY NOT NULL,
  login TEXT UNIQUE NOT NULL,
  email TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  jukebox_id INTEGER,
  edit BOOLEAN NOT NULL
);

CREATE TABLE role(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE account_role(
  id SERIAL PRIMARY KEY NOT NULL,
  account_id INTEGER NOT NULL REFERENCES account ON DELETE CASCADE,
  role_id INTEGER NOT NULL REFERENCES role ON DELETE CASCADE
);

CREATE TABLE track(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT,
  duration TEXT,
  genre_id INTEGER,
  album TEXT,
  band TEXT,
  link TEXT,
  edit BOOLEAN
);

CREATE TABLE jukebox(
  id SERIAL PRIMARY KEY NOT NULL
);

CREATE TABLE jukebox_track(
  id SERIAL PRIMARY KEY NOT NULL,
  jukebox_id INTEGER REFERENCES jukebox ON DELETE CASCADE,
  track_id INTEGER REFERENCES track ON DELETE CASCADE
);

CREATE TABLE genre(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE album(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  year TEXT,
  band TEXT,
  description TEXT,
  edit BOOLEAN NOT NULL
);

CREATE TABLE band(
  id SERIAL PRIMARY KEY NOT NULL,
  name TEXT UNIQUE NOT NULL,
  year TEXT,
  description TEXT,
  edit BOOLEAN NOT NULL
);

CREATE TABLE member(
  id SERIAL PRIMARY KEY NOT NULL,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  biography TEXT,
  edit BOOLEAN NOT NULL
);

CREATE TABLE band_member(
  id SERIAL PRIMARY KEY NOT NULL,
  band_id INTEGER REFERENCES band ON DELETE CASCADE,
  member_id INTEGER REFERENCES member ON DELETE CASCADE
);

CREATE TABLE band_album(
  id SERIAL PRIMARY KEY NOT NULL,
  band_id INTEGER REFERENCES band ON DELETE CASCADE,
  album_id INTEGER REFERENCES album ON DELETE CASCADE
);

CREATE TABLE album_track(
  id SERIAL PRIMARY KEY NOT NULL,
  album_id INTEGER REFERENCES album ON DELETE CASCADE,
  track_id INTEGER REFERENCES track ON DELETE CASCADE
);

TRUNCATE TABLE account_role RESTART IDENTITY CASCADE;

TRUNCATE TABLE account RESTART IDENTITY CASCADE;

TRUNCATE TABLE role RESTART IDENTITY CASCADE;
