-- -----------------------------------------------------
-- Insert for Default Environment
-- -----------------------------------------------------
insert into SIF3_ENV_TEMPLATE values ('DEV_LOCAL','devLocal.xml');

insert into SIF3_APP_TEMPLATE (APP_TEMPLATE_ID,SOLUTION_ID,APPLICATION_KEY,PASSWORD,USER_TOKEN,INSTANCE_ID,AUTH_METHOD,ENV_TEMPLATE_ID) 
values (1, 'test', 'TestSIS', 'Password1', null, null, 'Basic', 'DEV_LOCAL');


-- -----------------------------------------------------
-- Insert for Default Job Template
-- -----------------------------------------------------
-- Consumer
INSERT INTO SIF3_JOB_TEMPLATE (JOB_TEMPLATE_ID,JOB_URL_NAME,ADAPTER_TYPE,TEMPLATE_FILE_NAME) 
VALUES (1,'RolloverStudents','CONSUMER','rolloverStudentJob.xml');

-- Provider: DIRECT
INSERT INTO SIF3_JOB_TEMPLATE (JOB_TEMPLATE_ID,JOB_URL_NAME,ADAPTER_TYPE,TEMPLATE_FILE_NAME) 
VALUES (2,'RolloverStudents','ENVIRONMENT_PROVIDER','rolloverStudentJob.xml');

-- Provider: BROKERED
INSERT INTO SIF3_JOB_TEMPLATE (JOB_TEMPLATE_ID,JOB_URL_NAME,ADAPTER_TYPE,TEMPLATE_FILE_NAME) 
VALUES (3,'RolloverStudents','PROVIDER','rolloverStudentJob.xml');
