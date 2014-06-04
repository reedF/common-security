-- 用户表User
CREATE TABLE ls_s_user (
       id bigserial NOT NULL ,
       account varchar(64) default NULL,
       password varchar(32) default NULL,
       name varchar(60) default NULL,
       email varchar(60) default NULL,
       cellphone varchar(20) default NULL,
       departmentId integer default NULL,
       createTime timestamp with  time zone,
       updateTime timestamp with  time zone,
       enable integer default 1,
       PRIMARY KEY  (id)
);

-- 角色表Role
CREATE TABLE ls_s_role (
     id bigserial NOT NULL ,
     name varchar(255) default NULL,
     enable integer default 1 ,
     PRIMARY KEY  (id)
);

-- 用户组
CREATE TABLE ls_s_group (
     id bigserial NOT NULL ,
     name varchar(255) default NULL,
     departmentId integer default NULL,
     enable integer default 1,
     PRIMARY KEY  (id)
);

-- 资源表resource
CREATE TABLE ls_s_resource (
     id bigserial NOT NULL ,
     name varchar(255) default NULL,
     url varchar(255) default NULL,
     modelId integer default NULL,
     fullurl varchar(255) default NULL,
     createTime timestamp with  time zone,
     updateTime timestamp with  time zone,     
     PRIMARY KEY  (id)
);

-- 菜单模块表model
CREATE TABLE ls_s_model (
     id bigserial NOT NULL ,
     name varchar(255) default NULL,
     url varchar(255) default NULL,
     createTime timestamp with  time zone,
     updateTime timestamp with  time zone,     
     PRIMARY KEY  (id)
);

-- 角色资源表role_resource
CREATE TABLE ls_s_role_resource (
      id bigserial NOT NULL ,
      rsId integer NOT NULL,
      rId integer  NOT NULL,
      PRIMARY KEY  (id)
);

-- 角色用户组表
CREATE TABLE ls_s_role_group (
      id bigserial NOT NULL ,
      rId integer  NOT NULL ,
      gId integer  NOT NULL,
      PRIMARY KEY  (id)
);

-- 用户关联组表user_group
CREATE TABLE ls_s_user_group (
     id bigserial NOT NULL ,
     uId integer  NOT NULL ,
     gId integer  NOT NULL ,
     PRIMARY KEY  (id)
);

