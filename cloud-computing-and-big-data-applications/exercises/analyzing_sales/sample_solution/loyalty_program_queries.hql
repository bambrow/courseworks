--  Returns the HOME phone number for customer ID 1200866
SELECT phone['HOME'] FROM loyalty_program WHERE cust_id=1200866;

-- Returns the third element from the orders array for customer ID 1200866
SELECT orders[2] FROM loyalty_program WHERE cust_id=1200866;

-- Returns the value of the total attribute from the order_value struct
SELECT order_value.total FROM loyalty_program WHERE cust_id=1200866;

