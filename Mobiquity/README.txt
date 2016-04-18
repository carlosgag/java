As requested the project has a Packer class with a pack method. This method reads 
from a file the different lines containing items for a package. For each line, is 
created an object OptimizePackage from an auxiliary method, to store the maximum 
weight allowed and the list of potential items to be added to the package. This object 
also has a method that is executed, that returns the optimized list of items for the 
package, considering the total weight less or equal to the maximum allowed, and the 
maximum cost for the package. Finally, the pack method process the list of items 
and add the information as requested, to the string that will be returned. This is 
done for every line contained in the source file.

The PackageItem class represents an item, and only contain the information related 
with the index, cost and weight.
The OptimizePackage class as explained before has the information about the maximum 
weight allowed for the package, and the list of potential items. The functionalities 
here are the method to add an item to the list and the auxiliary method that returns 
the optimized list. This method calls an OptimizationStrategy implementation and 
execute the method getOptimizedList on the specified implementation.

A possible change for this is to execute all the available implementations of the 
optimization, to have the best solution. In this case is only used an implementation 
oriented to dynamic programming.

This is a classical problem of algorithms called the "Knapsack problem" and has a 
lot of implementations an examples in several books and web pages. Was used this page: 
http://introcs.cs.princeton.edu/java/96optimization/Knapsack.java.html 
because of its simplicity, for the Dynamic programming implementation.
Other approach (exposed in MyOptimization implementation and not finished yet) is 
to use a Package class with the attributes: packageItems, totalWeight & totalCost. 
This abstraction allow us to store in a list different  distributions of a package. 
This list is temporary while the algorithm is being executed. When finished, from 
the list is selected the best solution.

As a final note, the costs are stored as integers because the examples provided are as 
integers. The weights also are provided as Double, but for simplification, are changed 
to grams (to have the weight as integers).

There are several solutions for the exposed problem. Because of the time provided for the
solution, and due to the classical solution provided by the algorithms books, I think that
the most important requirement is not related with the algorithm itself, but the program
structure. For this, after the 4 hs spent on the problem, I dedicated only 1hr more, to 
review the solution, check if the MyOptimization implementation could be done quickly, and 
this documentation was finished to expose the approach and the different decisions made.

For the general analysis and design I used 1h and 30 mins, for the first version implementation
2hs and 30 mins. For the MyOptimization approach 30 mins, and for the documentation 30 mins.