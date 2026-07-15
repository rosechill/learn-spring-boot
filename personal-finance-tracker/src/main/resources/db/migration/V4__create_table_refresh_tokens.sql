CREATE TABLE IF NOT EXISTS  refresh_tokens
(
    id         bigint primary key auto_increment,
    user_id    bigint    not null,
    token      varchar(512),
    revoked    boolean   not null default false,
    expires_at timestamp not null,
    created_at timestamp not null default current_timestamp,
    constraint fk_refresh_token_user foreign key (user_id) references users (id) on delete cascade
);














