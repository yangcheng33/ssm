CREATE DATABASE IF NOT EXISTS ssm DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

USE ssm;

CREATE TABLE IF NOT EXISTS `central_app` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `version` varchar(255) NOT NULL DEFAULT '' COMMENT '版本',
  `description` varchar(500) NOT NULL DEFAULT '' COMMENT '描述',
  `os` tinyint(3) unsigned NOT NULL COMMENT '系统，0：ios，1：android',
  `git_url` varchar(500) NOT NULL DEFAULT '' COMMENT 'git仓库地址',
  `git_branch` varchar(255) NOT NULL DEFAULT '' COMMENT '默认git分支',
  `creator` varchar(255) NOT NULL DEFAULT '' COMMENT '创建人',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(255) NOT NULL DEFAULT '' COMMENT '最后修改人',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `central_app` (`id`, `name`, `version`, `description`, `git_url`, `os`, `git_branch`, `creator`, `gmt_create`, `modifier`, `gmt_modified`)
VALUES
  (5, 'app', '1.0.1', 'test', '', 0, '', '', '2017-11-07 17:14:32', '', '2017-11-07 17:14:32');
