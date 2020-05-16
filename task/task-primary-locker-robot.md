### 属性
空闲状态，存包中，取包中

### Tasking

when:存包  given: 机器人 不空闲,                                       then: 存取失败，不返回ticket，得到失败信息

when:存包  given: 机器人 空闲, all Lockers are full                    then: 存取失败，不返回ticket，得到失败信息

when:存包  given: 机器人 空闲, locker 1 is available, 2 is available   then: 存取成，存到1，返回ticket

when:存包  given: 机器人 空闲, locker 1 is full, 2 is available        then: 存取成，存到2，返回ticket

when:取包  given: 机器人 不空闲                                         then: 取包失败，得到失败信息

when:取包  given: 机器人 空闲，ticket invalid                           then: 取包失败，得到失败信息

when:取包  given: 机器人 空闲，ticket valid                             then: 取包成功，返回包