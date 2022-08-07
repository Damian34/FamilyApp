
CREATE SCHEMA IF NOT EXISTS FamilyDB;
CREATE SCHEMA IF NOT EXISTS FamilyMemberDB;

DROP TABLE IF EXISTS FamilyDB.Family;
DROP TABLE IF EXISTS FamilyMemberDB.FamilyMember;

CREATE TABLE FamilyDB.Family(
	id SERIAL PRIMARY KEY,
	familyName varchar,
	nrOfAdults int default 0,
	nrOfChildren int default 0,
	nrOfInfants int default 0
);

CREATE TABLE FamilyMemberDB.FamilyMember(
	id SERIAL PRIMARY KEY,
	familyId int NOT NULL,
	familyName varchar,
	givenName varchar,
	age int
);
