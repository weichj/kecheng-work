/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : javaee

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 30/04/2023 18:22:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `Num` int NOT NULL AUTO_INCREMENT,
  `Shipper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Origin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `GoodsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Carriers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `State` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `transporter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Num`) USING BTREE,
  INDEX `Shipper`(`Shipper` ASC) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`Shipper`) REFERENCES `account` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '2', '北京', '湖南', '臭豆腐', '圆通', '', NULL);
INSERT INTO `order` VALUES (2, '1', '1', '1', '1', '华为', '出库中', '周炳旭');
INSERT INTO `order` VALUES (3, '2', '2', '2', '2', '2', '出库中', '未分配');
INSERT INTO `order` VALUES (5, '3', '3', '3', '3', '3', '出库中', '未分配');
INSERT INTO `order` VALUES (6, '3', '3', '3', '3', '3', '出库中', '未分配');
INSERT INTO `order` VALUES (8, '2', '北京', '广西', '螺狮粉', '申通', '出库中', '韦昌靖');
INSERT INTO `order` VALUES (9, '1', '北京', '云南', '大象', '京东', '出库中', '杜剑宇');
INSERT INTO `order` VALUES (10, '3', '北京', '陕西', '肉夹馍', '中通', '出库中', '未分配');
INSERT INTO `order` VALUES (18, '1', '北京', '江西赣州', '猪肉干', '韵达', '出库中', '张泽清');

SET FOREIGN_KEY_CHECKS = 1;
