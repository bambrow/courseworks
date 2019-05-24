with text_io;
with Msort;

procedure Main is
	use text_io;
	use Msort;
	package int_io is new integer_io(integer);
	use int_io;

	A: int_array;

	task Reader is
		entry read;
	end Reader;
	task Sum is
		entry sum;
	end Sum;
	task Printer is
		entry print_array;
		entry print_sum(s: in integer);
	end Printer;

	task body Reader is
	begin
		accept read;
		for i in integer range 1..LENGTH loop
			get(A(i));
		end loop;
	end Reader;

	task body Sum is
		s: integer:= 0;
	begin
		accept sum;
		for i in A'range loop
			s := s + A(i);
		end loop;
		Printer.print_sum(s);
	end Sum;

	task body Printer is
	begin
		accept print_array;
		for i in A'range loop
			put(A(i));
			new_line;
		end loop;
		accept print_sum(s: in integer) do
			put("sum: ");
			put(s); 
			new_line;
		end print_sum;
	end Printer;

begin
	Reader.read;
	Sort(A);
	Printer.print_array;
	Sum.sum;
end Main;

