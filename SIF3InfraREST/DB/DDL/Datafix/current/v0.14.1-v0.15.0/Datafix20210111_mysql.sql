ALTER TABLE SIF3_APP_TEMPLATE ADD INFRA_VERSION VARCHAR(20) NULL AFTER AUTH_METHOD;

ALTER TABLE SIF3_SESSION ADD INFRA_VERSION VARCHAR(20) NULL AFTER PASSWORD;