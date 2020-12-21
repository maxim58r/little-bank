DROP TRIGGER IF EXISTS `db_little_bank`.`accounts_BEFORE_INSERT`;

DELIMITER $$
USE `db_little_bank`$$
CREATE DEFINER = CURRENT_USER TRIGGER `db_little_bank`.`accounts_BEFORE_INSERT` BEFORE INSERT ON `accounts` FOR EACH ROW
BEGIN
    IF new.`validity_period` is null
    THEN
        set new.`validity_period` = DATE_ADD(NOW(), INTERVAL 4 YEAR);
    end if;
END$$
DELIMITER ;

INSERT INTO `db_little_bank`.`users` (`address`, `date_of_birth`, `email`, `fullname`, `phone`)
VALUES ('samara', '1990-04-25', 'dimitri@mail.com', 'dmitri', '+7937-666-66-44');

INSERT INTO `db_little_bank`.`users` (`address`, `date_of_birth`, `email`, `fullname`, `phone`)
VALUES ('penza', '1982-12-25', 'alexnemovr@gmail.com', 'alex', '+7937-212-87-08');

INSERT INTO `db_little_bank`.`users` (`address`, `date_of_birth`, `email`, `fullname`, `phone`)
VALUES ('new_york', '2000-11-25', 'maxim58r@gmail.com', 'maxim', '+7937-412-20-62');

INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('354000', '1');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('900000', '1');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('150000', '1');

INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('354000', '2');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('900000', '2');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('150000', '2');

INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('354000', '3');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('900000', '3');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`)
VALUES ('150000', '3');

INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('1500', '1');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('200', '2');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('600', '3');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('1500', '4');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('200', '5');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('600', '6');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('1500', '7');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('200',  '8');
INSERT INTO `db_little_bank`.`transactions` (`amount`, `account_account_number`)
VALUES ('600', '9');



