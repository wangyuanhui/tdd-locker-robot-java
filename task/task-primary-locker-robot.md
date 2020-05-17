### 属性
空闲状态，存包中，取包中

### Tasking

when:存包  given: all Lockers are full                    then: 存取失败，不返回ticket，得到失败信息

when:存包  given: locker 1 is available, 2 is available   then: 存取成，存到1，返回ticket

when:存包  given: locker 1 is full, 2 is available        then: 存取成，存到2，返回ticket

when:取包  given: ticket invalid                          then: 取包失败，得到失败信息

when:取包  given: ticket valid                            then: 取包成功，返回包


#### review
1. taksing test 写错了
2. Bag 存入null的 bug