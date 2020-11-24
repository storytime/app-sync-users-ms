# --- !Ups
ALTER TABLE app_user
    ADD COLUMN ynab_auth_token varchar(255);

ALTER TABLE app_user
    ADD COLUMN ynab_sync_enabled boolean;
