create table users
(
    id         bigint auto_increment primary key,
    username   varchar(100) not null,
    password   varchar(100) not null,
    email      varchar(100) not null,
    hobby      varchar(100) null,
    birthday   date         null,
    created_at datetime(6)  null,
    updated_at datetime(6)  null,
    constraint unique (email)
);
CREATE TABLE retired_email (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               email VARCHAR(100) NOT NULL
);

create table boards
(
    id         bigint not null auto_increment PRIMARY KEY,
    user_id    bigint,
    contents   longtext,
    created_at datetime(6),
    updated_at datetime(6),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
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

create table follows
(
    id          bigint not null auto_increment primary key,
    follower_id bigint not null,
    followed_id bigint not null,
    constraint fk_follows_follower foreign key (follower_id) references users (id) on delete cascade,
    constraint fk_follows_followed foreign key (followed_id) references users (id) on delete cascade,
    constraint uc_follower_followed unique (follower_id, followed_id)
);

CREATE TABLE likes
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment_id BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    CONSTRAINT Like_UNIQUE UNIQUE (comment_id, user_id),
    CONSTRAINT fk_likes_comment FOREIGN KEY (comment_id) REFERENCES comments (id) ON DELETE CASCADE,
    CONSTRAINT fk_likes_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
