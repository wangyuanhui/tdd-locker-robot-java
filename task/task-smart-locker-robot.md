### Tasking

1. when:存包  given: all Lockers are full                          then: 存取失败，不返回ticket，得到失败信息

2. when:存包  given: locker 2 has most free rooms                  then: 存到locker 2，返回ticket

3. when:存包  given: locker 1 and locker 2 have same free rooms    then: 存到locker 1 或 locker 2，返回ticket

4. when:取包  given: ticket invalid                                then: 取包失败，得到失败信息

5. when:取包  given: ticket valid                                  then: 取包成功，返回包