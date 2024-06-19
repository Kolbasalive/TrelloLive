create table users
(
    user_id     uuid primary key,
    name        varchar not null,
    email       varchar not null,
    password    varchar not null,
    role        varchar not null
);