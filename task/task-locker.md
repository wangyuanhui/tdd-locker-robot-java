## 用户动作
- 存包
- 取包

## Locker  
- 属性：数量，ticket

## Room 
- 状态：空，占用
- 动作：开，关

## Tasking

存包 存在空  开柜子，柜子状态占用，并返回一个ticket。 

存包 全部占用 失败,无票据返回

取包 ticket正确 开柜子，柜子状态清空，ticket失效。

取包 ticket错误 失败

查询Locker状态  存一个包  显示Locker可用room数量+1

查询Locker状态  取一个包  显示Locker可用room数量-1
