with text_io;

procedure Test3 is
	use text_io;
	package int_io is new integer_io(integer);
	use int_io;
	task Foo is
		entry e1;
	end Foo;
	task body Foo is
	begin
		accept e1 do
			for i in integer range 1..10 loop
				Put(i); New_Line;
			end loop;
		end e1;
	end Foo;
	task Bar is
		entry e2;
	end Bar;
	task body Bar is
	begin
		accept e2 do
			for i in integer range 11..20 loop
				Put(i); New_Line;
			end loop;
		end e2;
	end Bar;

begin
	Put("Start");
	Foo.e1;
	Bar.e2;
	Put("Finish");
end Test3;

-- accept .. do will wait for the signal, and do the task in the accept block
-- and then return a signal that the accept is finished
-- and then the procedure can continue to the next line
-- therefore, 1..10 will always print before 11.20