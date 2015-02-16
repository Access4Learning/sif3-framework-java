-- -----------------------------------------------------
-- Insert for Default Environment
-- -----------------------------------------------------
insert into SIF3_ENV_TEMPLATE values ('DEV_LOCAL','devLocal.xml');

insert into SIF3_APP_TEMPLATE (APP_TEMPLATE_ID,SOLUTION_ID,APPLICATION_KEY,PASSWORD,USER_TOKEN,INSTANCE_ID,AUTH_METHOD,ENV_TEMPLATE_ID) 
values (1, 'test', 'TestSIS', 'Password1', null, null, 'Basic', 'DEV_LOCAL');
