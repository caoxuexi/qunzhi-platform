/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : qunzhi-db

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 22/10/2021 16:01:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `category` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `chat_method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `power_dissipation` smallint NULL DEFAULT NULL COMMENT '1.低 2.正常 3.高',
  `share` smallint NULL DEFAULT NULL COMMENT '0.不共享 1.共享',
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '智能家居系统', NULL, NULL, NULL, NULL, NULL, NULL, '2021-10-18 20:19:04.000', '2021-10-21 19:53:08.529', NULL);
INSERT INTO `project` VALUES (2, '智慧社区系统', NULL, NULL, NULL, NULL, NULL, NULL, '2021-10-19 08:35:51.000', '2021-10-19 08:35:58.000', NULL);
INSERT INTO `project` VALUES (3, '智慧城市系统', NULL, NULL, NULL, NULL, NULL, NULL, '2021-10-21 20:11:33.000', '2021-10-21 20:11:36.000', NULL);
INSERT INTO `project` VALUES (4, '智慧园区系统', NULL, NULL, NULL, NULL, NULL, NULL, '2021-10-21 20:11:54.000', '2021-10-21 20:11:57.000', NULL);
INSERT INTO `project` VALUES (5, '智慧校园系统', NULL, NULL, NULL, NULL, NULL, NULL, '2021-10-21 20:12:22.000', '2021-10-21 20:12:26.000', NULL);

-- ----------------------------
-- Table structure for project_application
-- ----------------------------
DROP TABLE IF EXISTS `project_application`;
CREATE TABLE `project_application`  (
  `id` int NOT NULL,
  `project_mac` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `project_id` int NOT NULL,
  `first_online_time` datetime(3) NULL DEFAULT NULL,
  `last_onlon_time` datetime NULL DEFAULT NULL,
  `online_count` int NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_application
-- ----------------------------

-- ----------------------------
-- Table structure for project_extra
-- ----------------------------
DROP TABLE IF EXISTS `project_extra`;
CREATE TABLE `project_extra`  (
  `id` int NOT NULL,
  `project_id` int NOT NULL,
  `current_user_count` int NULL DEFAULT NULL,
  `demand` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_extra
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nickname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `realname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `profile` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qq` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `district` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_admin` smallint NULL DEFAULT NULL,
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'default', NULL, 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245683@qq.com', NULL, NULL, NULL, NULL, 1, '2021-10-17 22:19:56.482', '2021-10-19 22:50:48.640');
INSERT INTO `user` VALUES (2, 'default', NULL, 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245682@qq.com', NULL, NULL, NULL, NULL, 0, '2021-10-18 15:22:17.675', '2021-10-19 22:50:50.951');
INSERT INTO `user` VALUES (3, 'default', NULL, 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245680@qq.com', NULL, NULL, NULL, NULL, 0, '2021-10-18 20:17:09.138', '2021-10-19 22:50:51.975');
INSERT INTO `user` VALUES (4, 'default', NULL, 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '969718359@qq.com', NULL, NULL, NULL, NULL, 0, '2021-10-18 23:04:53.523', '2021-10-19 22:56:51.501');

-- ----------------------------
-- Table structure for user_project
-- ----------------------------
DROP TABLE IF EXISTS `user_project`;
CREATE TABLE `user_project`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int UNSIGNED NOT NULL,
  `project_id` int UNSIGNED NOT NULL,
  `user_role` smallint NULL DEFAULT NULL COMMENT '用户对于项目的角色：1.组长  2.组员',
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_project
-- ----------------------------
INSERT INTO `user_project` VALUES (1, 1, 1, 1, '2021-10-18 19:48:42.148', '2021-10-21 20:01:53.088', NULL);
INSERT INTO `user_project` VALUES (2, 1, 2, 1, '2021-10-19 08:37:38.000', '2021-10-21 19:49:17.216', NULL);
INSERT INTO `user_project` VALUES (3, 2, 1, 2, '2021-10-20 19:22:22.186', '2021-10-21 19:52:54.695', NULL);
INSERT INTO `user_project` VALUES (5, 3, 1, 2, '2021-10-21 20:04:18.503', '2021-10-21 20:05:16.791', NULL);

SET FOREIGN_KEY_CHECKS = 1;
