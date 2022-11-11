# Vote 项目

## Solidity 要求完成

* [ ] 用户登录注册
* [ ] 用户创建投票项目
* [ ] 用户对项目进行投票(需要密码)
* [ ] 用户对项目进行关闭
* [ ] 用户查看自身所有项目




## SpringBoot 部分

### Controller 层

* [ ] 创建 solidity 智能合约所实现的功能

  | 服务             | URL       | 参数               |
  | ---------------- | --------- | ------------------ |
  | 用户登录         | /login    | account            |
  | 用户注册         | /register | account            |
  | 创建投票项目     | /creater  | projectName,passwd |
  | 投票             | /poll     | memeber,passwd     |
  | 关闭项目         | /close    | projectId,passwd   |
  | 查看自身所有项目 | /myself   |                    |

* [ ] 完成用户登录的 Service 创建



## Vue 部分

### 创建相应页面

| 功能             | 页面         |
| ---------------- | ------------ |
| 登录             | login.vue    |
| 注册             | register.vue |
| 创建投票项目     | create.vue   |
| 投票             | poll.vue     |
| 关闭项目         | close.vue    |
| 查看自身所有项目 | myself.vue   |

