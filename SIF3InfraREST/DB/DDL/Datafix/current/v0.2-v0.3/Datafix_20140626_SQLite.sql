-- -----------------------------------------------------
-- Table SIF3_ENV_TEMPLATE
-- -----------------------------------------------------
CREATE  TABLE SIF3_ENV_TEMPLATE (
  ENV_TEMPLATE_ID VARCHAR(50) NOT NULL ,
  TEMPLATE_FILE_NAME VARCHAR(100) NOT NULL ,
  PRIMARY KEY (ENV_TEMPLATE_ID) );

-- -----------------------------------------------------
-- Table SIF3_APP_TEMPLATE
-- -----------------------------------------------------
CREATE  TABLE SIF3_APP_TEMPLATE (
  APP_TEMPLATE_ID INTEGER PRIMARY KEY AUTOINCREMENT ,
  SOLUTION_ID VARCHAR(100) NULL ,
  APPLICATION_KEY VARCHAR(100) NULL ,
  PASSWORD VARCHAR(100) NULL ,
  USER_TOKEN VARCHAR(200) NULL ,
  INSTANCE_ID VARCHAR(200) NULL ,
  AUTH_METHOD VARCHAR(20) NULL ,
  ENV_TEMPLATE_ID VARCHAR(50) NULL ,
  CONSTRAINT FK_APP_TMPLT_TO_ENV_TMPLT
    FOREIGN KEY (ENV_TEMPLATE_ID )
    REFERENCES SIF3_ENV_TEMPLATE (ENV_TEMPLATE_ID )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE INDEX SOL_APPKEY_IDX ON SIF3_APP_TEMPLATE (SOLUTION_ID ASC, APPLICATION_KEY ASC) ;

CREATE INDEX APPKEY_IDX ON SIF3_APP_TEMPLATE (APPLICATION_KEY ASC) ;

CREATE INDEX SOL_APPKEY_USERTK_IDX ON SIF3_APP_TEMPLATE (SOLUTION_ID ASC, APPLICATION_KEY ASC, USER_TOKEN ASC) ;

CREATE INDEX APPKEY_USERTK_IDX ON SIF3_APP_TEMPLATE (APPLICATION_KEY ASC, USER_TOKEN ASC) ;

CREATE INDEX IDX_APP_TMPLT_TO_ENV_TMPLT ON SIF3_APP_TEMPLATE (ENV_TEMPLATE_ID ASC) ;

-- -----------------------------------------------------
-- Alter to SIF3_SESSION
-- -----------------------------------------------------
-- The command below is not supported by SQLite. You need to remove and re-create the SIF3_SESSION Table altogether.
-- ALTER TABLE SIF3_SESSION MODIFY (SOLUTION_ID null);

-- -----------------------------------------------------
-- Insert for Default Environment
-- -----------------------------------------------------
insert into SIF3_ENV_TEMPLATE values ('DEV_LOCAL','devLocal.xml');

insert into SIF3_APP_TEMPLATE (APP_TEMPLATE_ID,SOLUTION_ID,APPLICATION_KEY,PASSWORD,USER_TOKEN,INSTANCE_ID,AUTH_METHOD,ENV_TEMPLATE_ID) 
values (1, 'test', 'TestSIS', 'Password1', null, null, 'Basic', 'DEV_LOCAL');

