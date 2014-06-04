-- 用户表User
CREATE TABLE `ls_s_user` (
       `id` int(11) NOT NULL auto_increment,
       `account` varchar(64) default NULL,
       `password` varchar(32) default NULL,
       `name` varchar(60) default NULL,
       `email` varchar(60) default NULL,
       `cellphone` varchar(20) default NULL,
       `departmentId` int(11) default NULL,
       `createTime` timestamp NOT NULL DEFAULT 0,
       `updateTime` timestamp NULL DEFAULT 0 ON UPDATE CURRENT_TIMESTAMP,
       `enable` int(11) default 1 COMMENT '是否使用 1. 是 0.否',
       PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 角色表Role
CREATE TABLE `ls_s_role` (
     `id` int(11) NOT NULL auto_increment,
     `name` varchar(255) default NULL,
     `enable` int(11) default 1 COMMENT '是否使用 1. 是 0.否',
     PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户组
CREATE TABLE `ls_s_group` (
     `id` int(11) NOT NULL auto_increment,
     `name` varchar(255) default NULL,
     `departmentId` int(11) default NULL,
     `enable` int(11) default 1 COMMENT '是否使用 1. 是 0.否',
     PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 资源表resource
CREATE TABLE `ls_s_resource` (
     `id` int(11) NOT NULL auto_increment,
     `name` varchar(255) default NULL,
     `url` varchar(255) default NULL comment '权限所对应的本级url地址',
     `modelId` int(11) default NULL comment '所属模块',
     `fullurl` varchar(255) default NULL comment '权限所对应的完整url地址',
     `createTime` timestamp NOT NULL DEFAULT 0,
     `updateTime` timestamp NULL DEFAULT 0 ON UPDATE CURRENT_TIMESTAMP,     
     PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 菜单模块表model
CREATE TABLE `ls_s_model` (
     `id` int(11) NOT NULL auto_increment,
     `name` varchar(255) default NULL,
     `url` varchar(255) default NULL comment '模块所对应的菜单url地址',
     `createTime` timestamp NOT NULL DEFAULT 0,
     `updateTime` timestamp NULL DEFAULT 0 ON UPDATE CURRENT_TIMESTAMP,     
     PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 角色资源表role_resource
CREATE TABLE `ls_s_role_resource` (
      `id` int(11) NOT NULL auto_increment,
      `rsId` int(11) NOT NULL,
      `rId` int(11)  NOT NULL,
      PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 角色用户组表
CREATE TABLE `ls_s_role_group` (
      `id` int(11) NOT NULL auto_increment,
      `rId` int(11)  NOT NULL COMMENT 'roleID',
      `gId` int(11)  NOT NULL COMMENT 'groupID',
      PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户关联组表user_group
CREATE TABLE `ls_s_user_group` (
     `id` int(11) NOT NULL auto_increment,
     `uId` int(11)  NOT NULL COMMENT 'userID',
     `gId` int(11)  NOT NULL COMMENT 'groupID',
     PRIMARY KEY  (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

