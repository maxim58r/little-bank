

INSERT INTO `db_little_bank`.`users` (`address`, `date_of_birth`, `email`, `fullname`, `phone`)
VALUES ('samara', '1990-04-25', 'dimitri@mail.com', 'max', '79652584812');

INSERT INTO `db_little_bank`.`users` (`address`, `date_of_birth`, `email`, `fullname`, `phone`)
VALUES ('penza', '1982-12-25', 'alexnemovr@gmail.com', 'max', '79854442415');

INSERT INTO `db_little_bank`.`users` (`address`, `date_of_birth`, `email`, `fullname`, `phone`)
VALUES ('new_york', '2000-11-25', 'maxim58r@gmail.com', 'max', '79374122062');

INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('354000', '1');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('900000', '1');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('150000', '1');

INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('354000', '2');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('900000', '2');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('150000', '2');

INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('354000', '3');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('900000', '3');
INSERT INTO `db_little_bank`.`accounts` (`amount`, `owner`) VALUES ('150000', '3');

INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('1500', '2020-12-12', '1');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('200', '2019-12-12', '2');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('600', '2018-12-12', '3');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('1500', '2020-12-12', '4');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('200', '2019-12-12', '5');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('600', '2018-12-12', '6');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('1500', '2020-12-12', '7');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('200', '2019-12-12', '8');
INSERT INTO `db_little_bank`.`transactions` (`ammount`, `date_time`, `account_account_number`) VALUES ('600', '2018-12-12', '9');
