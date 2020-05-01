
## 表结构

```SQL
CREATE TABLE `USERS` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `loginId` varchar(11) DEFAULT NULL,
  `loginPsd` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```