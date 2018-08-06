DROP TABLE IF EXISTS `guest_profile`;
SET @@auto_increment_increment=2;
CREATE TABLE `guest_profile` (
    `guest_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(50) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(15) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`guest_id`)
)  ENGINE=INNODB AUTO_INCREMENT=100;

DROP TABLE IF EXISTS `resort_reservation`;
SET @@auto_increment_increment=5;
CREATE TABLE `resort_reservation` (
    `r_reservation_number` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `guest_id_fk` BIGINT(20) UNSIGNED NOT NULL,
    `room_type` VARCHAR(50) NOT NULL,
    `arrival_date` DATETIME NOT NULL,
    `departure_date` DATETIME NOT NULL,
    `no_of_people` INT(11) NOT NULL,
    `status` VARCHAR(10) NOT NULL,
    `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`r_reservation_number`),
    KEY `guest_id_fk_idx` (`guest_id_fk`),
    CONSTRAINT `guest_id` FOREIGN KEY (`guest_id_fk`)
        REFERENCES `guest_profile` (`guest_id`)
        ON DELETE CASCADE ON UPDATE CASCADE
)  ENGINE=INNODB AUTO_INCREMENT=5000;

DROP TABLE IF EXISTS `dinning_reservation`;
SET @@auto_increment_increment=3;
CREATE TABLE `dinning_reservation` (
  `d_reservation_number` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `guest_id_fk` BIGINT(20) unsigned NOT NULL,
  `dinning_type` VARCHAR(50) NOT NULL,
  `arrival_date` DATETIME NOT NULL,
  `no_of_people` INT(11) unsigned NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`d_reservation_number`),
  KEY `guest_id_fk_d_idx` (`guest_id_fk`),
  CONSTRAINT `guest_id_fk_d` FOREIGN KEY (`guest_id_fk`) REFERENCES `guest_profile` (`guest_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT;