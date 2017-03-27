ALTER TABLE SIF3_SESSION ADD FINGERPRINT VARCHAR(255) NULL AFTER ENVIRONMENT_ID;

-- Upgarding 3.0.1 to 3.2.1
update SIF3_SESSION
set ENVIRONMENT_XML=REPLACE(ENVIRONMENT_XML,'http://www.sifassociation.org/infrastructure/3.0.1','http://www.sifassociation.org/infrastructure/3.2.1');

-- Upgarding 3.1 to 3.2.1
update SIF3_SESSION
set ENVIRONMENT_XML=REPLACE(ENVIRONMENT_XML,'http://www.sifassociation.org/infrastructure/3.1','http://www.sifassociation.org/infrastructure/3.2.1');
