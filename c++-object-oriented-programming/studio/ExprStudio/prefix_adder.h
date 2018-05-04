// prefix_adder.h
//
// author: Chris Gill cdgill@cse.wustl.edu
//
// purpose: Declarations for a simple prefix adder program, which
//          takes the command line arguments as a prefix addition
//          expression and computes an integer result.


#ifndef PREFIX_ADDER_H
#define PREFIX_ADDER_H

// Function prototypes
void usage (char * program_name);
int parse_and_compute (int current_index, int last_index, char *argv[]);

#endif /* PREFIX_ADDER_H */
