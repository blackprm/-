# 测试表sql
```sql
CREATE TABLE `test`.`Untitled`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;
```
--- 
# token 测试
1. 要请求资源时,只能先获得`token`,然后在每次请求资源时,将获取到的token字段设置为header,才有权限取得资源
2. 要获取token请发`post请求`至`/api/user`表单数据为 `id=用户id`,`lastName=用户名` ,只有数据库中存在时,才会返回token串
3. 测试获取的token有无效果时,请发送`get请求`至 `/api/user` , 并在请求头中设置`token=token串`,请求成功返回`ok`,错误返回其他信息。
--- 
# restful api
0. (因浏览器兼容性问题,发送`delete`和`put`请求时需要在表单中添加`_method=put|delete`)
1. post: 表示创建操作
2. get: 表示取得操作
3. delete: 表示删除操作
4. put： 表示更新操作
