

delete from dependents;
delete from manager;
delete from sales_person;
delete from return;
delete from supply_details;
delete from supply;
delete from purchase_details;
delete from purchase;
delete from book;
delete from store;
delete from supplier;
delete from employee;
delete from customer;
commit;
drop table dependents purge;
drop table manager purge;
drop table sales_person purge; 
drop table return purge;
drop table supply_details purge;
drop table supply purge;
drop table purchase_details purge;
drop table purchase purge;
drop table supplier purge;
drop table store purge;
drop table book purge;
drop table employee purge;
drop table customer purge;