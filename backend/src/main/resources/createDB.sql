DROP TABLE IF EXISTS TILE;
DROP TABLE IF EXISTS TILESET;

CREATE TABLE TILE(id SERIAL PRIMARY KEY, img bytea, block integer[3][3]);
CREATE TABLE TILESET(id SERIAL PRIMARY KEY, tiles integer[48]);
