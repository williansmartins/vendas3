/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : fiap

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2014-05-02 15:23:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_produto
-- ----------------------------
DROP TABLE IF EXISTS `tbl_produto`;
CREATE TABLE `tbl_produto` (
  `codigo` bigint(20) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `estoque` varchar(255) NOT NULL,
  `preco` decimal(19,2) NOT NULL,
  `quantidade_estoque` int(11) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tbl_produto
-- ----------------------------
INSERT INTO `tbl_produto` VALUES ('0', 'Iphone 4S', 'deposito2', '1000.00', '5');
INSERT INTO `tbl_produto` VALUES ('1', 'Notebook HP G42', 'deposito1', '1200.00', '2');
INSERT INTO `tbl_produto` VALUES ('2', 'Mouse Microsoft Wireless 5000', 'deposito2', '150.00', '1');
INSERT INTO `tbl_produto` VALUES ('3', 'Impressora Laser HP', 'deposito3', '540.00', '1');
INSERT INTO `tbl_produto` VALUES ('4', 'Teclado Wireless', 'deposito3', '120.00', '2');
INSERT INTO `tbl_produto` VALUES ('5', 'Leitor de Cartão SD', 'deposito3', '25.50', '3');
INSERT INTO `tbl_produto` VALUES ('6', 'Mascara do homem de ferro', 'deposito3', '230.00', '2');
INSERT INTO `tbl_produto` VALUES ('7', 'Ipad 2 - com defeito', 'deposito3', '40.00', '1');
INSERT INTO `tbl_produto` VALUES ('8', 'Fita de leds (1 metro)', 'deposito3', '12.00', '5');
INSERT INTO `tbl_produto` VALUES ('9', 'Tx car - PPA', 'deposito3', '29.90', '0');
INSERT INTO `tbl_produto` VALUES ('10', 'Laser Point Green', 'deposito3', '19.80', '12');
