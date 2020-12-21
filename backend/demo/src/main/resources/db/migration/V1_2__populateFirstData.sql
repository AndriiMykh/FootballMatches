insert into person(id,email,name,password,phone_number) 
		values
		(10000,'wertuha22@gmail.com','Andrii','1111','123123123'),
		(10001,'wertuha23@gmail.com','Mykola','1111','123123124'),
		(10002,'wertuha24@gmail.com','Max','1111','123123125');
insert into event(id,place,time,available_places) 
		values
		(20000,'Pushkina5',now() +INTERVAL '10 month',20),
		(20001,'Pushkina6',now() +INTERVAL '15 month',5),
		(20002,'Pushkina7',now() +INTERVAL '20 month',3);
		
		