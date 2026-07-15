CREATE TABLE IF NOT EXISTS users
(
    id              bigint primary key auto_increment,
    full_name       varchar(100)        not null,
    email           varchar(255) unique not null,
    password        varchar(255)        not null,
    role            varchar(20)         not null,
    is_active       boolean             not null default true,
    profile_picture varchar(255),
    created_at      timestamp           not null default current_timestamp,
    updated_at      timestamp           not null default current_timestamp
);




