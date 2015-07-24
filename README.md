# RogueTraderUtilities

The project runs out of Star.java

it generates a few attributes for itself

then it generates a list of generic elements

it then replaces those generic elements with specific classes
* these are subclasses of the generic, so that a single list could hold them
* is there are better data structure that can hold multiple object types?

the random number generator method is used by all classes
* should it be a utility class or something instead?

probability distributions are done very hack-y
* I have a list of possible outcomes
* then a distribution list, with how often each outcome should be
* then I randomly pick from the distribution list, which then gives me the index for the outcome list
* the distribution list is nice because i dont have to worry about changing all of the % probabilities every time i want to alter the list
* instead i can just increase an outcomes probability by adding a few more of its index into the distribution list
* but this feels wrong somehow

the probability of certain attributes are affected by previous attributes
* is there a better way to specify this other than switch cases?
* is there a better way to have probability distributions?

the anomaly generator is simply used to generate a string from a list
* didnt want to have the huge list in the planet class
* then again, planet is the only one to use it
* is this bad form?
