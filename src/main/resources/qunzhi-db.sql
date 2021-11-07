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

 Date: 07/11/2021 17:15:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device_log
-- ----------------------------
DROP TABLE IF EXISTS `device_log`;
CREATE TABLE `device_log`  (
  `id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_id` int NOT NULL,
  `mac` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `first_online` datetime(3) NULL DEFAULT NULL,
  `cumulative_online` int NOT NULL DEFAULT 0,
  `last_online` datetime(3) NULL DEFAULT NULL,
  `state` smallint NULL DEFAULT NULL COMMENT '0:离线 1:在线',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_log
-- ----------------------------
INSERT INTO `device_log` VALUES ('1', 1, 'virtual:site', '2021-10-01 22:08:29.000', 3, '2021-10-24 22:08:42.000', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '智能家居系统', '智能家居/生活小家电', 'Wi-Fi', '5a220f0122af47fe9acf13a37ad93dc7', NULL, 2, 0, '2021-10-18 20:19:04.000', '2021-10-26 10:27:20.658', NULL);
INSERT INTO `project` VALUES (2, '智慧社区系统', '管理系统/社区', 'Wi-Fi', '5a220f0122af47fe9acf13a37ad93dc8', NULL, 2, 0, '2021-10-19 08:35:51.000', '2021-10-28 11:31:01.329', NULL);
INSERT INTO `project` VALUES (3, '智慧城市系统', NULL, NULL, '5a220f0122af47fe9acf13a37ad93dc9', NULL, NULL, NULL, '2021-10-21 20:11:33.000', '2021-10-26 16:22:54.616', NULL);
INSERT INTO `project` VALUES (4, '智慧园区系统', NULL, NULL, '5a220f0122af47fe9acf13a37ad93dc5', NULL, NULL, NULL, '2021-10-21 20:11:54.000', '2021-10-26 16:23:00.202', NULL);
INSERT INTO `project` VALUES (5, '智慧校园系统', NULL, NULL, '5a220f0122af47fe9acf13a37ad93dc4', NULL, NULL, NULL, '2021-10-21 20:12:22.000', '2021-10-27 19:04:04.788', NULL);
INSERT INTO `project` VALUES (6, '智能图书馆系统', '管理系统/后台', 'Wi-Fi', '51adf172-bd11-484f-a06b-02515ae5feb9', '51df266a-d2a2-3d0e-b109-e1f2ec424b8b', 2, 0, '2021-10-29 12:28:47.269', '2021-10-29 12:28:47.269', NULL);

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
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '0.未审批 1.已审批 2.已拒绝',
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
  `current_user_count` int UNSIGNED NULL DEFAULT 0,
  `demand` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_extra
-- ----------------------------

-- ----------------------------
-- Table structure for service_recommend
-- ----------------------------
DROP TABLE IF EXISTS `service_recommend`;
CREATE TABLE `service_recommend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `function_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of service_recommend
-- ----------------------------
INSERT INTO `service_recommend` VALUES (1, '用户管理', '用户注册', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/login.zip');
INSERT INTO `service_recommend` VALUES (2, '用户管理', '权限验证', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/login.zip');
INSERT INTO `service_recommend` VALUES (3, '用户管理', '用户登录', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/login.zip');
INSERT INTO `service_recommend` VALUES (4, '网站公告', '公告发布', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/bulltin.zip');
INSERT INTO `service_recommend` VALUES (5, '网站公告', '消息发布', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/bulltin.zip');
INSERT INTO `service_recommend` VALUES (6, '数据管理', '数据存储', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/dataManage.zip');
INSERT INTO `service_recommend` VALUES (7, '数据管理', '可视化展示', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/dataManage.zip');
INSERT INTO `service_recommend` VALUES (8, '设备接入', 'wifi接入', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/instrument.zip\r\n');
INSERT INTO `service_recommend` VALUES (9, '设备接入', '蓝牙接入', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/instrument.zip\r\n');
INSERT INTO `service_recommend` VALUES (10, '设备接入', '设备管理', 'https://qunzhi-code.oss-cn-hangzhou.aliyuncs.com/instrument.zip\r\n');

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
  `is_admin` smallint UNSIGNED NULL DEFAULT 0,
  `token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '嗜血枭雄', '曹操', 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245683@qq.com', '18858781650', '969718359', '浙江省杭州市萧山区萧山经济开发区钱农东路8号西电杭州研究院', '浙江省/杭州市/萧山区', 1, '112181289351778304', '2021-10-17 22:19:56.482', '2021-10-26 16:08:44.370');
INSERT INTO `user` VALUES (2, '飞飞公主', '张飞', 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245682@qq.com', NULL, NULL, NULL, NULL, 0, '', '2021-10-18 15:22:17.675', '2021-10-26 15:51:24.254');
INSERT INTO `user` VALUES (3, '勇敢牛牛', '牛魔', 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245680@qq.com', NULL, NULL, NULL, NULL, 0, '', '2021-10-18 20:17:09.138', '2021-10-26 15:51:24.363');
INSERT INTO `user` VALUES (4, '机关重炮', '墨子', 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245689@qq.com', NULL, NULL, NULL, NULL, 0, '', '2021-10-25 13:23:25.608', '2021-10-26 19:23:21.658');
INSERT INTO `user` VALUES (5, '一夫当关', '关羽', 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245679@qq.com', NULL, NULL, NULL, NULL, 0, '', '2021-10-25 20:28:01.710', '2021-10-26 19:23:22.604');
INSERT INTO `user` VALUES (6, '皇叔蹦迪', '刘备', 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245677@qq.com', NULL, NULL, NULL, NULL, 0, '', '2021-10-25 13:27:15.834', '2021-10-26 19:23:48.413');
INSERT INTO `user` VALUES (7, 'caoxuexi1', '曹操1', 'Qvg6b85lKYQ4Urx02ffq2w==', NULL, '752245688@qq.com', NULL, NULL, NULL, NULL, 0, '112139571877253120', '2021-10-26 21:35:23.914', '2021-10-26 21:35:23.914');

-- ----------------------------
-- Table structure for user_project
-- ----------------------------
DROP TABLE IF EXISTS `user_project`;
CREATE TABLE `user_project`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int UNSIGNED NOT NULL,
  `project_id` int UNSIGNED NOT NULL,
  `user_role` smallint NULL DEFAULT NULL COMMENT '用户对于项目的角色：1.组长  2.组员',
  `user_nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_project
-- ----------------------------
INSERT INTO `user_project` VALUES (1, 1, 1, 1, '嗜血枭雄', '曹操', '2021-10-18 19:48:42.148', '2021-10-26 16:08:44.371', NULL);
INSERT INTO `user_project` VALUES (2, 1, 2, 1, '嗜血枭雄', '曹操', '2021-10-19 08:37:38.000', '2021-10-26 16:08:44.371', NULL);
INSERT INTO `user_project` VALUES (3, 2, 1, 2, '飞飞公主', '张飞', '2021-10-20 19:22:22.186', '2021-10-26 13:10:56.670', NULL);
INSERT INTO `user_project` VALUES (4, 3, 1, 2, '勇敢牛牛', '牛魔', '2021-10-21 20:04:18.503', '2021-10-26 13:11:04.705', NULL);
INSERT INTO `user_project` VALUES (5, 5, 1, 2, '机关重炮', '墨子', '2021-10-26 16:19:08.969', '2021-10-26 16:19:08.969', NULL);
INSERT INTO `user_project` VALUES (6, 1, 6, 1, '嗜血枭雄', '曹操', '2021-10-29 12:28:52.527', '2021-10-29 12:28:52.527', NULL);

SET FOREIGN_KEY_CHECKS = 1;
