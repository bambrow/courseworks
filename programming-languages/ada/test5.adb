with text_io;

procedure Test5 is
	use text_io;
	package int_io is new integer_io(integer);
	use int_io;
	task type T is
		entry start(x, y: integer);
	end T;
	task body T is
	begin
		accept start(x, y: integer) do
			for i in integer range x..y loop
				Put(i); New_Line;
			end loop;
		end start;
	end T;

	Foo, Bar: T;

begin
	Put("Start");
	Foo.start(1,10);
	Bar.start(11,20);
	Put("Finish");
end Test5;

-- same as test3
-- 1..10 will always be printed before 11..20
-- because the for loop and the print is in the accept block