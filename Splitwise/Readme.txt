Api-
1. /NewGroup-  It consumes groupname object.And it doesn't have return type.Groupname object should contain  user object with username Only.
2. /NewUser- It consumes user object and a request param contains group id. user object should contain username only.
3. /AddExpenses/Equal- It consumes amount to be divided and Groupid along with userid of person who have paid the bill.
4. /AddExpenses/Exact- It consumes exactamount object which contains name of partner and the amount to be added along with userid, and Groupcode who have paid the bill.
5. /AddExpenses/Percentage- It consumes percentagehelper object which have a list of personname and percentage. along with userid of paid user,and Groupid and the amount to be divided.
6. /Borrow- It consumes userid and groupid and return an arraylist of arraylist 1st arraylist depict from whom user have borrowed money and 2nd arraylist depict who owes the user.