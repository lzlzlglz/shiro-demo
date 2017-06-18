drop table if exists t_sys_users;
drop table if exists t_sys_roles;
drop table if exists t_sys_users_roles;
drop table if exists t_sys_permissions;
drop table if exists t_sys_roles_permissions;

create table t_sys_users (
  id bigint auto_increment,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  locked bool default false,
  constraint pk_t_sys_users primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_t_sys_users_username on sys_users(username);

create table t_sys_roles (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_t_sys_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_t_sys_roles_role on sys_roles(role);

create table t_sys_permissions (
  id bigint auto_increment,
  permission varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_t_sys_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_t_sys_permissions_permission on sys_permissions(permission);

create table t_sys_users_roles (
  user_id bigint,
  role_id bigint,
  constraint pk_t_sys_users_roles primary key(user_id, role_id)
) charset=utf8 ENGINE=InnoDB;

create table t_sys_roles_permissions (
  role_id bigint,
  permission_id bigint,
  constraint pk_t_sys_roles_permissions primary key(role_id, permission_id)
) charset=utf8 ENGINE=InnoDB;