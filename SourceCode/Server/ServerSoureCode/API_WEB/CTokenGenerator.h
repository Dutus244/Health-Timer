#ifndef __TOKEN_H__
#define __TOKEN_H__
#include <random>
class CTokenGenerator {
private:
	int n_gen;
	std::mt19937 eng;
	std::uniform_int_distribution<unsigned int> uniform_dist{ 0, UINT32_MAX };
public:
	CTokenGenerator();
	void GetNextToken(char* __token_out);
	// Get the length of each token, in characters 
	int GetTokenLength() ;
};

#endif