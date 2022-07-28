#include "CTokenGenerator.h"
#include <cstring>
#include <time.h>
#define TOKEN_LENGTH 15

#define TOKEN_CHARS "123456789abcdfghjklnmnpqrstvwxyzABCDFGHJKLMNPQRTWXY"

CTokenGenerator::CTokenGenerator(){
	n_gen = strlen(TOKEN_CHARS);
	srand(time(NULL));
	eng = std::mt19937(rand());
}
void CTokenGenerator::GetNextToken(char* __token_out){
	for (int i = 0 ; i < TOKEN_LENGTH;i++){
		__token_out[i]  = TOKEN_CHARS[uniform_dist(eng)%n_gen];
	}
}
// Get the length of each token, in characters 
int CTokenGenerator::GetTokenLength() { return TOKEN_LENGTH; }
