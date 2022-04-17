DROP TABLE IF EXISTS `settings`;

CREATE TABLE `settings`
(
    `name`       varchar(45) PRIMARY KEY,
    `min_length`       int(10),
    `max_length`     int(10),
    `types`         varchar(50),
    `min_count_map`  varchar(50),
);