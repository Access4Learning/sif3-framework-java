--
-- SIF3_SESSION
--

-- Upgarding 3.0.1 to 3.3
update SIF3_SESSION
set ENVIRONMENT_XML=REPLACE(ENVIRONMENT_XML,'http://www.sifassociation.org/infrastructure/3.0.1','http://www.sifassociation.org/infrastructure/3.3');

-- Upgarding 3.1 to 3.3
update SIF3_SESSION
set ENVIRONMENT_XML=REPLACE(ENVIRONMENT_XML,'http://www.sifassociation.org/infrastructure/3.1','http://www.sifassociation.org/infrastructure/3.3');

-- Upgarding 3.2.1 to 3.3
update SIF3_SESSION
set ENVIRONMENT_XML=REPLACE(ENVIRONMENT_XML,'http://www.sifassociation.org/infrastructure/3.2.1','http://www.sifassociation.org/infrastructure/3.3');

--
-- JOB related tables
--

-- Upgarding 3.2.1 to 3.3
update SIF3_JOB
set JOB_XML=REPLACE(JOB_XML,'http://www.sifassociation.org/infrastructure/3.2.1','http://www.sifassociation.org/infrastructure/3.3');

-- Upgarding 3.2.1 to 3.3
update SIF3_JOB_EVENT
set JOB_XML=REPLACE(JOB_XML,'http://www.sifassociation.org/infrastructure/3.2.1','http://www.sifassociation.org/infrastructure/3.3');

