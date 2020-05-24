### Tasking

1. when:存包  given: all Lockers are full                             then: 存取失败，不返回ticket，得到失败信息

2. when:存包  given: locker 1 has 1 free, locker2 has 2 free          then: 存到locker 2，返回ticket

3. when:存包  given: locker 1 has 2 free, locker2 has 1 free          then: 存到locker 1，返回ticket

4. when:存包  given: locker 1 and locker 2 have same most free rooms  then: 存到locker 1 或 locker 2，返回ticket

5. when:取包  given: ticket invalid                                   then: 取包失败，得到失败信息

6. when:取包  given: ticket valid                                     then: 取包成功，返回包


#### review
1. keep primary robot
2. more specific on most free rooms