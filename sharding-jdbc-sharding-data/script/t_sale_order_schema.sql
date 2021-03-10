/*
 Navicat Premium Data Transfer

 Source Server         : 测试环境
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 10.220.147.133:33306
 Source Schema         : gaia_order

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 09/03/2021 15:05:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sale_order
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_order`;
CREATE TABLE `t_sale_order`  (
  `order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原单订单号',
  `relation_order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联单号（外部客户自己的订单号）',
  `region_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域编码',
  `region_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `order_channel` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'SHSC' COMMENT '订单渠道(SHSC、HDL)',
  `order_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单类型（销售订单、补账订单）',
  `company_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司编码',
  `company_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `company_short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司简称',
  `shop_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '门店编码',
  `shop_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '门店名称',
  `shop_short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '门店简称',
  `delivery_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单主体编码',
  `delivery_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单主体名称',
  `delivery_short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单主体简称',
  `delivery_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单主体类型(公司、门店、档口)',
  `examine_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核状态(待审核、审核通过、审核未通过)',
  `order_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `delivery_date` date NULL DEFAULT NULL COMMENT '交货日期,格式为：YYYY-MM-DD',
  `order_source` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单来源:APP、接口、代客下单、PC',
  `order_main_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下单主体类型:客户、门店、档口',
  `delivery_address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `delivery_contact` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货联系人名称',
  `delivery_contact_tel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货联系人 电话',
  `payment_type` tinyint(4) NULL DEFAULT NULL COMMENT '支付标识，1：需要支付；0：不需支付，默认不需支付 （没用到）',
  `settlement_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结算方式 ',
  `settlement_status` tinyint(4) NULL DEFAULT NULL COMMENT '结算状态：0:未支付；1：已支付',
  `despatch_mode` tinyint(4) NULL DEFAULT NULL COMMENT '1：自提；0：送货上门',
  `urgent_flag` tinyint(4) UNSIGNED NULL DEFAULT 1 COMMENT '是否加急：0：加急；1：非加急',
  `freight_free_flag` tinyint(4) NULL DEFAULT NULL COMMENT '免运费标识：0:免运费；1：非免运费',
  `fee_dept_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '费用承担部门编码',
  `fee_dept_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '费用承担部门名称：在加急单时需要指定,值为费用承担部门编码',
  `can_cancel` tinyint(2) NULL DEFAULT 1 COMMENT '是否可以取消 1是 0 否  列表页展示使用',
  `split_group` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拆分 组 ZS FACTORY OTHER',
  `relation_origin_order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '补签订单关联的原单号',
  `payer_id` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款方编码',
  `payer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款方名称',
  `delivery_period` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '到货时间段',
  `receive_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货方编码',
  `receive_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货方全称',
  `receive_short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货方简称',
  `delivery_address_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址编码',
  `push_flag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下推交付状态 success,failed',
  `error_message` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '错误信息',
  `order_flag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单标识 :  NORMAL、正常订单    ABNORMAL、异常订单',
  `cancel_reason` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '取消原因',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `order_creator_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下单人姓名',
  `order_creator_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下单人账号id',
  `order_create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `update_person` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `sap_delivery_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sap订单主体编码',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `region_flag_create_time_idx`(`region_code`, `order_flag`, `delivery_date`, `examine_status`, `order_status`, `order_type`, `order_source`, `company_id`, `shop_id`, `delivery_id`, `order_create_time`) USING BTREE COMMENT '区域编码、订单标识、创建时间联合索引',
  INDEX `relation_order_idx`(`relation_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
