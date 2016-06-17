/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : license

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2016-06-07 15:08:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `client_verify`
-- ----------------------------
DROP TABLE IF EXISTS `client_verify`;
CREATE TABLE `client_verify` (
`encrypted_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`source_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `license_detail`
-- ----------------------------
DROP TABLE IF EXISTS `license_detail`;
CREATE TABLE `license_detail` (
`serial_number_id`  int(100) NOT NULL AUTO_INCREMENT ,
`source_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`create_day`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`expired_date`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`encrypted_number`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`expired_flag`  int(10) UNSIGNED ZEROFILL NOT NULL ,
`valid_days`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`hospital_number`  int(100) NOT NULL ,
PRIMARY KEY (`serial_number_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Table structure for `license_hospital`
-- ----------------------------
DROP TABLE IF EXISTS `license_hospital`;
CREATE TABLE `license_hospital` (
`hospital_id`  int(100) NOT NULL AUTO_INCREMENT ,
`hospital_number`  int(100) NULL DEFAULT NULL ,
`hospital_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`hospital_phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`hospital_address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`hospital_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `license_user`
-- ----------------------------
DROP TABLE IF EXISTS `license_user`;
CREATE TABLE `license_user` (
`user_id`  int(100) NOT NULL AUTO_INCREMENT ,
`username`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`user_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Auto increment value for `license_detail`
-- ----------------------------
ALTER TABLE `license_detail` AUTO_INCREMENT=2;

-- ----------------------------
-- Auto increment value for `license_hospital`
-- ----------------------------
ALTER TABLE `license_hospital` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `license_user`
-- ----------------------------
ALTER TABLE `license_user` AUTO_INCREMENT=1;
