// prefix_adder.cpp : Defines the entry point for the console application.
//
// author: Chris Gill cdgill@cse.wustl.edu
//
// purpose: definitions for a simple prefix adder program, which
// takes the command line arguments as a prefix addition
// expression and computes an integer result.

// to compile this on Linux, comment out the following line
#include "stdafx.h"
#include "prefix_adder.h"

#include <iostream> // For standard output stream and manipulators.
#include <string>   // For standard C++ strings.
#include <sstream>  // For standard string streams.
#include <cstring>  // For C-style string functions

// Symbols used from standard namespace.
using namespace std;

enum result_codes {success, bad_command_line, unexpected_end_of_expression, unknown_exception=-1};

int main (int argc, char *argv[])
{
  // A few useful constants for argument positions  
  const int minimum_arguments = 2;         
  const int starting_index = 1;   
  const int program_name_index = 0;
       
  if (argc < minimum_arguments || 
      strcmp (argv[starting_index], "--help") == 0) {        
       
    usage (argv[program_name_index]);      
    return bad_command_line;   
  }    
       
  try {         
       
    // Pass the current and last index to use, and the array, to the  
    // expression parsing function, and store the result.    
       
    int current_position = starting_index; 
    int arguments = argc - 1; 
    int value = parse_and_compute (current_position, arguments, argv); 
       
    // Print out the result, and return success value.       
       
    cout << "The value calculated is " << value << endl;     
    return success;   
  }    
  catch (int i)  {         
       
    cout << "error: caught exception integer " << i << endl;    
    return i;  
  }    
  catch (...)  {         
       
    cout << "caught exception" << endl;    
    return unknown_exception;  
  }    
}      
  
// Helper function to print out the program's usage message. 
void usage (char * program_name) {         
       
  cout << "Usage: " << program_name << " <argument> [<argument>]..." << endl   
       << "Purpose: computes program arguments as prefix addition expression"  
       << endl; 
       
}      
       
       
// Helper function to parse the input symbols and compute a value.    
int parse_and_compute (int current_index, int last_index, char *argv[]) {    
       
       
  // make sure we're still in the argument range    
  if (current_index > last_index) {        
      int i = unexpected_end_of_expression;
        throw i;  
      }         
       
       
  // look for a single-symbol addition operator     
  if (strlen (argv[current_index]) == 1 && 
      *(argv[current_index]) == '+') {     
       
        int first_operand =       
  parse_and_compute (++current_index,      
   last_index, argv);    
       
        int second_operand =      
  parse_and_compute (current_index,        
   last_index, argv);    
       
        return first_operand + second_operand;      
  }    
       
  // treat anything else as an integer     
  else {        
       
    int result; 
    istringstream i (argv[current_index++]);        
    i >> result;         
    return result;       
       
  }  
}
