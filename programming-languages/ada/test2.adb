with text_io;

procedure Test2 is
	use text_io;
	package int_io is new integer_io(integer);
	use int_io;
	task Foo;
	task body Foo is
	begin
		for i in integer range 1..10 loop
			Put(i); New_Line;
		end loop;
	end Foo;
	task Bar;
	task body Bar is
	begin
		for i in integer range 11..20 loop
			Put(i); New_Line;
		end loop;
	end Bar;

begin
	null;
end Test2;

-- two tasks will run concurrently.
-- we cannot tell which number will be printed next.