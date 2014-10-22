This folder contains notes about the hacks made into the original crfsuite.hpp.

CHANGE LOG

1) Printing training process in Trainer::message(...) which is left empty
void Trainer::message(const std::string& msg)
{
	std::cout << msg << std::flush;
}


