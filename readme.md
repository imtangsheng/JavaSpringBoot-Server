# 开始

## gradle
- 在解压Gradle压缩包后,不要直接将bin文件夹添加到PATH环境变量中。

- 修改或创建一个GRADLE_HOME环境变量,值为Gradle解压后的安装目录路径。

- 将安装目录下的bin文件夹路径追加到PATH变量value值中。%GRADLE_HOME%\bin


## Spring Framework 下载
https://spring.io/projects/spring-boot

- 参考，下载推荐的java版本，然后根据教程下载创建一个新项目hello world构建
- 配置选择jdk版本，java的目录，同gradle配置。然后构建工具选择gradle的目录。等待自动下载构建完成项目构建
- 运行构建选项jdk，模板，运行配置。运行。
  信息发送失败。
  

## 更新日志

- 2024-2-17 
  - 打包测试，切换java17版本测试
  - 增加 OpenApi 支持，只需要添加|

- 2023-12-05
  - 使用Spring Integration 建立一个 UDP 服务器 ，接收消息
  - TCP 服务器测试
  - 测试WebSocket客户端

- 2023-12-04
  - 增加MySql支持，测试自动建立数据库，表，自适应类型和格式

- 2023-12-02
  - 构建 RESTful Web 服务。postman使用post方法，body form-data输入表单的三个参数，其中有个返回的隐藏参数
  - 安全验证登录，除了/home /login 其他都会跳转到 login页面
  - 创建不安全的 Web 应用程序