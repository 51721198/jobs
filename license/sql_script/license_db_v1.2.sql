ALTER TABLE `license_hospital`
DROP COLUMN `hospital_id`,
MODIFY COLUMN `hospital_number`  int(100) NOT NULL FIRST ,
MODIFY COLUMN `hospital_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `hospital_number`,
MODIFY COLUMN `hospital_phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `hospital_name`,
MODIFY COLUMN `hospital_address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL AFTER `hospital_phone`,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`hospital_number`);