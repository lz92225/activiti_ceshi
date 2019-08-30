/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 30/08/2019 17:19:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_tz_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_tz_message`;
CREATE TABLE `tb_tz_message`  (
  `act_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `act_def_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `act_deadline_time` bigint(20) NULL DEFAULT NULL,
  `act_warning_time` bigint(20) NULL DEFAULT NULL,
  `read_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `back_color_bit_id` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `font_color_bit_id` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `rec_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `biz_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sys_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `task_num` datetime(3) NULL DEFAULT NULL,
  `rec_disp_num` datetime(3) NULL DEFAULT NULL,
  `rec_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `event_type_id` bigint(20) NULL DEFAULT NULL,
  `main_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `main_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sub_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sub_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `part_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `coordinate_x` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `coordinate_y` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `deadline_time` bigint(20) NULL DEFAULT NULL,
  `warning_time` bigint(20) NULL DEFAULT NULL,
  `act_property_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `display_style_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `max_event_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `func_forbid_reporter_info_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `reporter_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `event_src_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `event_desc` longblob NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `district_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `street_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `community_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` bigint(20) NULL DEFAULT NULL,
  `human_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `unit_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `act_state_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `act_sup_state_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `act_ard_state_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `act_create_time` bigint(20) NULL DEFAULT NULL,
  `act_time_state_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `act_deadline_char` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `act_remain_char` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `reporter_contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `other_task_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `operating` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `proc_time_state_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`act_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_tz_message
-- ----------------------------
INSERT INTO `tb_tz_message` VALUES ('11951', '535', 1560931463000, 1560929663000, '1', '0', '0', '6072', '33', '1', '2019-06-16 00:15:00.000', NULL, '1', 1, '81000', '城市绿化', '81025', '绿地、绿化带', 'null', '0.0', '0.0', 1560669346000, 1560669346000, '6', '2', '81025', 'null', '张女士', '12345热线案件', 0xE5B882E6B091E58F8DE698A0E585ACE59BADE8A2ABE5819CE8BDA6E58DA0E794A8EFBC8CE5B882E6B091E58F8DE698A0EFBC8CE9809AE5B79EE58CBAE78E89E6A1A5E8A197E98193E9809AE5928CE59BADE5B08FE58CBAE69781E8BEB9E69C89E4B880E4B8AAE5B08FE585ACE59BADEFBC8CE4BD86E698AFE7BB8FE5B8B8E69C89E8BDA6E5819CE694BEE59CA8E6ADA4EFBC8CE698A8E5A4A9E4B88BE58D8836E782B9E58F91E78EB0E8BDA6E983BDE5819CE6BBA1E4BA86EFBC8CE5B88CE69C9BE5B8AEE58AA9E8A7A3E586B3E585ACE59BADE8A2ABE5819CE8BDA6E58DA0E794A8E997AEE9A298E38082E6B3A8EFBC9AE8AFB7E58F8AE697B6E59091E69DA5E794B5E4BABAE58F8DE9A688E58A9EE79086E68385E586B5E38082E6B3A8EFBC9AE9809AE5B79EE58CBAE78E89E6A1A5E8A197E98193E59B9EE5A48DE9809AE5928CE59BADE5B08FE58CBAE591A8E8BEB9E585ACE59BADE5B19EE59BADE69E97E5B180EFBC8CE58F8DE698A0E585ACE59BADE8A2ABE5819CE8BDA6E58DA0E794A8E79A84E997AEE9A298E5BBBAE8AEAEE8BDACE59BADE69E97E5B180E58A9EE79086EFBC81, '通和园小区旁边', '通州区', '通州区', '通州区', 1560669346000, '城市管理委', '城市管理委', '正常移交 ', '0', '0', 1560934689000, '3', '1小时', '超时54分钟', '56542677', '电[2019]A-56390413', '已派单待处置', '3');
INSERT INTO `tb_tz_message` VALUES ('20847', '535', 1565246432000, 1565244632000, '0', '1', '1', '7707', '33', '1', '2019-08-08 00:08:00.000', NULL, '1', 1, '80389', '公共设施', '80408', '污水井盖', NULL, '1.2993125939559E7', '4821242.674498', 1565269200000, NULL, '6', '2', '80408', NULL, NULL, '网格案件', 0xE6B58BE8AF95E6A188E4BBB6, '测试案件', '通州区', '张家湾镇', '上马头村', 1565237432000, NULL, '城市管理委', '正常移交 ', '0', '0', 1565237432000, '3', '1小时', '1小时', NULL, '2019通州社管0553174', '已派单待处置', '0');

SET FOREIGN_KEY_CHECKS = 1;
