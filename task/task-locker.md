## 用户动作
- 存包
- 取包

## Locker  
- 属性：数量，ticket

## Room 
- 状态：空，占用
- 动作：开，关

## Tasking

when 存包  given 存在空  then 并返回一个ticket。 

when 存包  given 全部占用  then 失败,无ticket返回

when 取包  given ticket正确  then 开柜子，柜子状态清空，ticket失效。

when 取包  given ticket错误  then 失败

when 查询Locker状态  given 存一个包  then 显示Locker可用room数量-1

when 查询Locker状态  given 取一个包  then 显示Locker可用room数量+1
