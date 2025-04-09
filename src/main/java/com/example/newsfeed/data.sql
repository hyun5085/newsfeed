create table users
(
    birthday   date         null,
    created_at datetime(6)  null,
    id         bigint auto_increment primary key,
    updated_at datetime(6)  null,
    email      varchar(100) not null,
    hobby      varchar(100) null,
    password   varchar(100) not null,
    username   varchar(100) not null,
    constraint unique (email)
);

create table boards
(
    id         bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    contents   longtext,
    user_id    bigint,
    primary key (id)
);

CREATE TABLE comments
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_id   BIGINT       NOT NULL,
    user_id    BIGINT       NOT NULL,
    contents   VARCHAR(100) NOT NULL,
    created_at datetime(6),
    updated_at datetime(6),
    FOREIGN KEY (board_id) REFERENCES boards (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);