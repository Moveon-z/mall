DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
                                      `token_id` varchar(256) DEFAULT NULL,
                                      `token` blob,
                                      `authentication_id` varchar(48) NOT NULL,
                                      `user_name` varchar(256) DEFAULT NULL,
                                      `client_id` varchar(256) DEFAULT NULL,
                                      `authentication` blob,
                                      `refresh_token` varchar(256) DEFAULT NULL,
                                      PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
                                   `userId` varchar(256) DEFAULT NULL,
                                   `clientId` varchar(256) DEFAULT NULL,
                                   `scope` varchar(256) DEFAULT NULL,
                                   `status` varchar(10) DEFAULT NULL,
                                   `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
                                        `client_id` varchar(48) NOT NULL COMMENT '客户端ID，主要用于标识对应的应用',
                                        `resource_ids` varchar(256) DEFAULT NULL,
                                        `client_secret` varchar(256) DEFAULT NULL COMMENT '客户端秘钥，BCryptPasswordEncoder加密',
                                        `scope` varchar(256) DEFAULT NULL COMMENT '对应的范围',
                                        `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '认证模式',
                                        `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '认证后重定向地址',
                                        `authorities` varchar(256) DEFAULT NULL,
                                        `access_token_validity` int(11) DEFAULT NULL COMMENT '令牌有效期',
                                        `refresh_token_validity` int(11) DEFAULT NULL COMMENT '令牌刷新周期',
                                        `additional_information` varchar(4096) DEFAULT NULL,
                                        `autoapprove` varchar(256) DEFAULT NULL,
                                        PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
                                      `token_id` varchar(256) DEFAULT NULL,
                                      `token` blob,
                                      `authentication_id` varchar(48) NOT NULL,
                                      `user_name` varchar(256) DEFAULT NULL,
                                      `client_id` varchar(256) DEFAULT NULL,
                                      PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
                              `code` varchar(256) DEFAULT NULL,
                              `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
                                       `token_id` varchar(256) DEFAULT NULL,
                                       `token` blob,
                                       `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;