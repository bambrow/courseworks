with text_io;

procedure Test4 is
	use text_io;
	package int_io is new integer_io(integer);
	use int_io;
	task Foo is
		entry e1;
	end Foo;
	task body Foo is
	begin
		accept e1;
		for i in integer range 1..10 loop
			Put(i); New_Line;
		end loop;
	end Foo;
	task Bar is
		entry e2;
	end Bar;
	task body Bar is
	begin
		accept e2;
		for i in integer range 11..20 loop
			Put(i); New_Line;
		end loop;
	end Bar;

begin
	Put("Start");
	Foo.e1;
	Bar.e2;
	Put("Finish");
end Test4;

-- this is just like test2
-- we cannot tell which number will be printed next
-- unlike test3, the for loop and the print statement is not in the accept block