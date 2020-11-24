# --- !Ups

create table app_user
(
    id bigserial not null,
    time_zone      varchar(255) not null,
    zen_auth_token varchar(255) not null,
    zen_last_sync_timestamp int8 not null,
    primary key (id)
);