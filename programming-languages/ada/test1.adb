with text_io;
use text_io;

procedure Test1 is
	task Foo;
	task body Foo is
	begin
		Put("In foo");
		New_Line;
	end Foo;
	task Bar;
	task body Bar is
	begin
		Put("In bar");
		New_Line;
	end Bar;

begin
	Put("Hello World"); New_Line;
end Test1;


-- two tasks run concurrently together with the procedure.
-- we cannot tell which one will print first.