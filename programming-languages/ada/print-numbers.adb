with text_io;
procedure PrintNums is 
	use text_io;
	package int_io is new integer_io(integer);
	use int_io;

	task task1 is
		entry E;
	end task1;

	task task2 is
		entry F;
	end task2;

	task body task1 is
	begin
		for i in integer range 1..100 loop
			put(i);
			if i mod 10 = 0 then
				task2.F;
				accept E;
			end if;
		end loop;
		task2.F;
	end task1;
	
	task body task2 is
	begin
		accept F;
		for i in integer range 201..300 loop
			put(i);
			if i mod 10 = 0 then
				task1.E;
				accept F;
			end if;
		end loop;
	end task2;

begin
	null;
end PrintNums;
