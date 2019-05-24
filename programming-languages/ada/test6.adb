with text_io;

procedure Test6 is
	use text_io;
	package int_io is new integer_io(integer);
	use int_io;
	task type T is
		entry start(x, y: integer);
	end T;
	task body T is
		m: integer;
		n: integer;
	begin
		accept start(x, y: integer) do
			m := x;
			n := y;
		end start;
		for i in integer range m..n loop
			Put(i); New_Line;
		end loop;
	end T;

	Foo, Bar: T;

begin
	Put("Start");
	Foo.start(1,10);
	Bar.start(11,20);
	Put("Finish");
end Test6;

-- unlike test5
-- in this code we cannot tell which number will be printed next
-- because the for loop and the print statement is not in the accept block