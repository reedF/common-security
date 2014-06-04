# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: test
# ------------------------------------------------------
# Server version 5.0.67-community-nt

#
# Dumping data for table ls_s_group
#

LOCK TABLES `ls_s_group` WRITE;
/*!40000 ALTER TABLE `ls_s_group` DISABLE KEYS */;
INSERT INTO `ls_s_group` VALUES (1,'系统管理',NULL,NULL);
INSERT INTO `ls_s_group` VALUES (2,'普通用户组',NULL,1);
INSERT INTO `ls_s_group` VALUES (3,'testGroup',NULL,NULL);
/*!40000 ALTER TABLE `ls_s_group` ENABLE KEYS */;
UNLOCK TABLES;

#
# Dumping data for table ls_s_model
#

LOCK TABLES `ls_s_model` WRITE;
/*!40000 ALTER TABLE `ls_s_model` DISABLE KEYS */;
INSERT INTO `ls_s_model` VALUES (1,'系统服务管理','/admin','2013-05-10 12:09:39','2013-05-13 14:11:14');
INSERT INTO `ls_s_model` VALUES (2,'Home','/','2013-05-10 12:09:39','2013-05-13 14:15:18');
/*!40000 ALTER TABLE `ls_s_model` ENABLE KEYS */;
UNLOCK TABLES;

#
# Dumping data for table ls_s_resource
#

LOCK TABLES `ls_s_resource` WRITE;
/*!40000 ALTER TABLE `ls_s_resource` DISABLE KEYS */;
INSERT INTO `ls_s_resource` VALUES (1,'查看用户列表','listuser.do',1,'/admin/listuser.do','2013-05-10 12:10:06','2013-05-13 12:16:22');
INSERT INTO `ls_s_resource` VALUES (2,'Home主页','index.jsp',2,'/index.jsp','2013-05-10 12:09:39','2013-05-13 14:15:32');
INSERT INTO `ls_s_resource` VALUES (3,'查看用户组列表','listgroup.do',1,'/admin/listgroup.do','2013-05-10 12:10:06','2013-05-13 14:31:56');
INSERT INTO `ls_s_resource` VALUES (4,'查看角色列表','listrole.do',1,'/admin/listrole.do','2013-05-10 12:10:06','2013-05-13 14:33:07');
INSERT INTO `ls_s_resource` VALUES (5,'查看资源列表','listresource',1,'/admin/listresource.do','2013-05-10 12:10:06','2013-05-13 14:33:07');
INSERT INTO `ls_s_resource` VALUES (6,'查看模块列表','listmodel.do',1,'/admin/listmodel.do','2013-05-10 12:10:06','2013-05-13 14:33:07');
INSERT INTO `ls_s_resource` VALUES (7,'添加用户','useradd.do',1,'/admin/useradd.do','2013-05-10 12:10:06','2013-05-13 14:36:18');
INSERT INTO `ls_s_resource` VALUES (8,'更新用户','useredit.do',1,'/admin/useredit.do','2013-05-10 12:10:06','2013-05-13 14:33:07');
INSERT INTO `ls_s_resource` VALUES (9,'删除用户','userdel.do',1,'/admin/userdel.do','2013-05-10 12:10:06','2013-05-14 13:27:10');
INSERT INTO `ls_s_resource` VALUES (10,'查看用户','userview.do',1,'/admin/userview.do','2013-05-10 12:10:06','2013-05-13 14:33:07');
INSERT INTO `ls_s_resource` VALUES (11,'添加模块','modeladd.do',1,'/admin/modeladd.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (12,'更新模块','modeledit.do',1,'/admin/modeledit.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (13,'查看模块','modelview.do',1,'/admin/modelview.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (14,'删除模块','modeldel.do',1,'/admin/modeldel.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (15,'查看资源','resourceview.do',1,'/admin/resourceview.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (16,'添加资源','resourceadd.do',1,'/admin/resourceadd.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (17,'更新资源','resourceedit.do',1,'/admin/resourceedit.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (18,'删除资源','resourcedel.do',1,'/admin/resourcedel.do','2013-05-10 12:10:06','2013-05-10 12:10:06');
INSERT INTO `ls_s_resource` VALUES (19,'查看用户组','groupview.do',1,'/admin/groupview.do','2013-05-14 14:35:12','2013-05-14 14:35:12');
INSERT INTO `ls_s_resource` VALUES (20,'添加用户组','groupadd.do',1,'/admin/groupadd.do','2013-05-14 14:42:30','2013-05-14 14:59:17');
INSERT INTO `ls_s_resource` VALUES (21,'更新用户组','groupedit.do',1,'/admin/groupedit.do','2013-05-14 14:58:45','2013-05-14 14:59:46');
INSERT INTO `ls_s_resource` VALUES (22,'删除用户组','groupdel.do',1,'/admin/groupdel.do','2013-05-14 15:00:31',NULL);
INSERT INTO `ls_s_resource` VALUES (23,'查看角色','roleview.do',1,'/admin/roleview.do','2013-05-14 15:46:38',NULL);
INSERT INTO `ls_s_resource` VALUES (24,'添加角色','roleadd.do',1,'/admin/roleadd.do','2013-05-14 15:47:06',NULL);
INSERT INTO `ls_s_resource` VALUES (25,'更新角色','roleedit.do',1,'/admin/roleedit.do','2013-05-14 15:47:30',NULL);
INSERT INTO `ls_s_resource` VALUES (26,'删除角色','roledel.do',1,'/admin/roledel.do','2013-05-14 15:47:45',NULL);
/*!40000 ALTER TABLE `ls_s_resource` ENABLE KEYS */;
UNLOCK TABLES;

#
# Dumping data for table ls_s_role
#

LOCK TABLES `ls_s_role` WRITE;
/*!40000 ALTER TABLE `ls_s_role` DISABLE KEYS */;
INSERT INTO `ls_s_role` VALUES (1,'系统管理员',NULL);
INSERT INTO `ls_s_role` VALUES (2,'普通用户',1);
INSERT INTO `ls_s_role` VALUES (3,'test',NULL);
/*!40000 ALTER TABLE `ls_s_role` ENABLE KEYS */;
UNLOCK TABLES;

#
# Dumping data for table ls_s_role_group
#

LOCK TABLES `ls_s_role_group` WRITE;
/*!40000 ALTER TABLE `ls_s_role_group` DISABLE KEYS */;
INSERT INTO `ls_s_role_group` VALUES (1,1,1);
INSERT INTO `ls_s_role_group` VALUES (2,2,2);
INSERT INTO `ls_s_role_group` VALUES (4,3,3);
/*!40000 ALTER TABLE `ls_s_role_group` ENABLE KEYS */;
UNLOCK TABLES;

#
# Dumping data for table ls_s_role_resource
#

LOCK TABLES `ls_s_role_resource` WRITE;
/*!40000 ALTER TABLE `ls_s_role_resource` DISABLE KEYS */;
INSERT INTO `ls_s_role_resource` VALUES (1,1,1);
INSERT INTO `ls_s_role_resource` VALUES (2,2,1);
INSERT INTO `ls_s_role_resource` VALUES (3,2,2);
INSERT INTO `ls_s_role_resource` VALUES (4,3,1);
INSERT INTO `ls_s_role_resource` VALUES (5,4,1);
INSERT INTO `ls_s_role_resource` VALUES (6,5,1);
INSERT INTO `ls_s_role_resource` VALUES (7,6,1);
INSERT INTO `ls_s_role_resource` VALUES (8,7,1);
INSERT INTO `ls_s_role_resource` VALUES (9,8,1);
INSERT INTO `ls_s_role_resource` VALUES (10,9,1);
INSERT INTO `ls_s_role_resource` VALUES (11,10,1);
INSERT INTO `ls_s_role_resource` VALUES (12,11,1);
INSERT INTO `ls_s_role_resource` VALUES (13,12,1);
INSERT INTO `ls_s_role_resource` VALUES (14,13,1);
INSERT INTO `ls_s_role_resource` VALUES (15,14,1);
INSERT INTO `ls_s_role_resource` VALUES (16,15,1);
INSERT INTO `ls_s_role_resource` VALUES (17,16,1);
INSERT INTO `ls_s_role_resource` VALUES (18,17,1);
INSERT INTO `ls_s_role_resource` VALUES (19,18,1);
INSERT INTO `ls_s_role_resource` VALUES (20,19,1);
INSERT INTO `ls_s_role_resource` VALUES (21,20,1);
INSERT INTO `ls_s_role_resource` VALUES (22,21,1);
INSERT INTO `ls_s_role_resource` VALUES (23,22,1);
INSERT INTO `ls_s_role_resource` VALUES (24,23,1);
INSERT INTO `ls_s_role_resource` VALUES (25,24,1);
INSERT INTO `ls_s_role_resource` VALUES (26,25,1);
INSERT INTO `ls_s_role_resource` VALUES (27,26,1);
INSERT INTO `ls_s_role_resource` VALUES (33,2,3);
INSERT INTO `ls_s_role_resource` VALUES (34,4,3);
INSERT INTO `ls_s_role_resource` VALUES (35,5,3);
/*!40000 ALTER TABLE `ls_s_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

#
# Dumping data for table ls_s_user
#

LOCK TABLES `ls_s_user` WRITE;
/*!40000 ALTER TABLE `ls_s_user` DISABLE KEYS */;
INSERT INTO `ls_s_user` VALUES (1,'admin','c3ce8345d3599ab8a4c337d9fb0d0d93','系统管理员',NULL,NULL,NULL,'2013-05-10 12:09:21','2013-05-14 18:19:34',1);
INSERT INTO `ls_s_user` VALUES (2,'2','c2e9b357641f2b1ae8aa14ce31265639',NULL,'',NULL,NULL,'2013-05-10 16:16:57',NULL,NULL);
INSERT INTO `ls_s_user` VALUES (3,'3','395dd96c9918d23251589ff9dc290d04','','',NULL,NULL,'2013-05-10 16:45:37','2013-05-14 17:21:39',NULL);
INSERT INTO `ls_s_user` VALUES (4,'eee','31dcd3d60be07dee6445e6ad1a958bb6','xxxxxxxx',NULL,NULL,NULL,'2013-05-14 17:15:49','2013-05-14 17:17:26',NULL);
/*!40000 ALTER TABLE `ls_s_user` ENABLE KEYS */;
UNLOCK TABLES;

#
# Dumping data for table ls_s_user_group
#

LOCK TABLES `ls_s_user_group` WRITE;
/*!40000 ALTER TABLE `ls_s_user_group` DISABLE KEYS */;
INSERT INTO `ls_s_user_group` VALUES (2,2,2);
INSERT INTO `ls_s_user_group` VALUES (15,4,2);
INSERT INTO `ls_s_user_group` VALUES (16,3,3);
INSERT INTO `ls_s_user_group` VALUES (17,1,1);
/*!40000 ALTER TABLE `ls_s_user_group` ENABLE KEYS */;
UNLOCK TABLES;


