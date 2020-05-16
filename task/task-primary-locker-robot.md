### 属性
空闲状态，存包中，取包中

### Tasking

when:存包  given: 机器人 不空闲,                            then: 存取失败，不返回ticket，得到失败信息

when:存包  given: 机器人 空闲, Locker full                  then: 存取失败，不返回ticket，得到失败信息

when:存包  given: 机器人 空闲, Locker empty                 then: 存取成，返回ticket

when:取包  given: 机器人 不空闲                              then: 取包失败，得到失败信息

when:取包  given: 机器人 空闲，ticket invalid                then: 取包失败，得到失败信息

when:取包  given: 机器人 空闲，ticket valid                  then: 取包成功，返回包