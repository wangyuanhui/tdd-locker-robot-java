### 属性
空闲状态，存包中，取包中

### Tasking

when:存包给robot  given: all Lockers are full                    then: robot存包失败，不返回ticket，得到失败信息

when:存包给robot  given: locker 1 is available, 2 is available   then: robot存包成功，存到1，返回ticket

when:存包给robot  given: locker 1 is full, 2 is available        then: robot存包成功，存到2，返回ticket

when:从robot取包  given: ticket invalid                          then: robot取包失败，得到失败信息

when:从robot取包  given: ticket valid                            then: robot取包成功，返回包


#### review
1. taksing test 写错了
2. Bag 存入null的 bug