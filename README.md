# First-Ascension
First Ascension is a two-player, turn-based battle 
game where each player selects a team of three characters from a predefined 
character catalog. Players take turns choosing to attack or defend. 
Characters have different stats, including speed, attack, defense, and health, 
which affect the outcome of battles.


# Reflection
Overall, nothing much changed from our specification that what we actually
built. There were 2 methods in battlemanager where I decided to combine into
one but for the most part, we followed what we put in our spec.  When it comes
to the model, it stays seperate from the view. We didn't have to move anything 
across the model/view line to make our program work.

One challenge was coordinating the responsibilities of the different classes. 
We needed to make sure that the Controller handled user input without 
directly managing the underlying game data. Also, we struggled to get the 
github to work properly as whenever we tried to make a new respository
the actual project would end up not being a java project, so we had to find
a way to change the project to make a java project and implement everything
we need, like a source folder. There doesn't seem to be anything missing yet
as the program seems to catch all of the errors properly. Overall, we were 
able to implement the main features we originally planned and get the different 
classes working together.
 
# Next Steps
Overall, we finished the MVP but we could implement a more effective 
defense system, or a more effective battle system that allows for more 
strategy. Also, we will work on making the visuals better.
Our next step is to perform more thorough testing of the game to 
make sure all features work as expected. This includes testing different 
battle scenarios, checking that defeated characters are removed from the 
queue correctly, verifying that the correct player wins, and making sure 
invalid inputs are handled appropriately. 

When it comes to adding a non-trivial feature, we will either add a timer 
system, or we will add new abilities and moves for the characters to use. Right
now, characters can attack and defend but we could at a status move that can 
prevent a character from attacking, using the random func. Because we are adding
this, we need to test to see if the new abillities are working and if the timer
system is counting down properly. The timer system would be done by Ayo and 
the addition of new abillities and moves would be implemented by Kevin or Aditi.


# Work Attribution
| Class | Team Member | Contribution |
|---|---|---|
| Character.java | Ayomide | Created and implemented |
| LinkedQueue.java | Ayomide | Created and implemented |
| Player.java | Kevin | Created and implemented |
| CharacterCatalog.java | Aditi | Created and implemented |
| BattleManager.java | Aditi | Created and implemented |
| GameView.java | Aditi | Created and implemented |
| GameController.java | Aditi | Created and implemented |

Gen ai was used to help with the implementation of the classes and to help 
connect each class with eachother to get the program to work. For example, 
we used AI to help come up with an attack formula.
