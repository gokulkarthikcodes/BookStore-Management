

--Display details of managers who have atleast one dependents
SELECT * 
from employee
where employeeid IN(
select employeeid
from manager where 
employeeid in (
select memployeeid
from dependents
group by memployeeid
)
);

--Display top 3 customers based on quantity of purchases
SELECT c.customerid,c.first_name,c.last_name 
from customer c, (select customerid from purchase_details group by customerid order by sum(quantity) desc) p
where p.customerid=c.customerid and rownum<=3;


--Display supplier whose book has the maximum returns

select * 
from supplier 
where supplierid in 
(select supplierid
from supply s1
where exists(
select storeid,bookid
from return r
group by (storeid,bookid)
having sum(quantity)>= all(
select sum(quantity)
from return
group by (storeid,bookid))and r.storeid=s.storeid and r.bookid=s.bookid));



select bookid 



max(sum(quantity))