# First-Ascension
First Ascension is a two-player, turn-based battle 
game where each player selects a team of three characters from a predefined 
character catalog. Players take turns choosing to attack or defend. 
Characters have different stats, including speed, attack, defense, and health, 
which affect the outcome of battles.


# Reflection
Overall, nothing much changed from our specification that what we actually
built. There were 2 methods in battlemanager where I decided to combine into
one but for the most part, we followed what we put in our spec. Also we didn't
put an option to display stats mainly because we want the model to work and we
wanted an element of suprise but that may be something to add in the future.
When it comes to the model, it stays seperate from the view. We didn't have 
to move anything across the model/view line to make our program work.

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
defense system, or a more effective battle system. Also, having an option to
display stats will be useful for the game.
Also, we will work on making the visuals better and adding more
characters. Our next step is to perform more thorough testing of the game to 
make sure all features work as expected. This includes testing different 
battle scenarios, checking that defeated characters are removed from the 
queue correctly, verifying that the correct player wins, and making sure 
invalid inputs are handled appropriately. 

When it comes to adding a non-trivial feature, we will either add a timer 
system, or we will add new abilities and moves for the characters to use. Right
now, characters can attack and defend but we could at a status move that can 
prevent a character from attacking, using the random func. Because we are adding
this, we need to test to see if the new abillities are working and if the timer
system is counting down properly. Both of these stretch goals would probably be
put into the battle manager class and the GameController and the GameView will
change to add these implementations. The timer system would be done by Ayo and 
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
#------------------------------------------------------------------
# Deliverable 4
# First-Ascension
First Ascension is a two-player, turn-based battle 
game where each player selects a team of three characters from a predefined 
character catalog. Players take turns choosing to attack or defend. 
Characters have different stats, including speed, attack, defense, and health, 
which affect the outcome of battles.

#Reflection
For Deliverable 4, we implemented our First Ascension prototype and added 
random character selection as our non-trivial stretch goal. The feature 
allows each player to choose between manually selecting their characters or 
having three characters randomly selected from the catalog.

The random selection feature mainly affects GameController and the GameView
classes. The GameController handles the selection process by creating a 
temporary list of available characters, shuffling the list, and adding the 
first three characters to the player's team. The GameView displays the 
selection menu and receives the player's choice.

Our implementation mostly followed the model-view-controller design we 
established in Deliverable 3. The controller coordinates the selection process, 
the view handles user input and output, and the model maintains the actual 
game state. The feature didn't directly require the view to directly modify 
model data as the controller used the existing model methods to build each 
player's team. This helped us maintain separation between the model and view.

The main change from our original plan was overall, adding a different stretch
goal compared to our plan. We were going to add a timer system and new status
moves but found that to be very heavily involved so we opted for a different
one, which is letting the program randomly select 3 random character. What 
suprised us is that it was a lot easier to implement the stretch goal as all
of the methods were there, we just needed to change the view and the game
controller. Otherwise, everything else stayed the same.

Overall, the program is working but the test classes still need fixing, as 
for some reason, we are unable to play the tests because of some project error.
One design change that could make future features cheaper to implement would be 
separating character selection into its own helper or strategy class.



#Next Steps
We will continue to finish testing and making sure the program is working as
there are some issues going on with the tests not running. Then, we will test
the whole game just to make sure everything is working again, and we will add
1 or 2 more stretch goals, being either the status move system, or the timer
system. The other stretch goals, like the double battle system, will be dropped
because they are simply too hard to implement and we want to focus on making
sure the program flows smoothly.

For our demo plan, from 0-30 sec, we will introduce what first ascension is and
explain how to play the game. From 30 sec to 1 min, we will show the character 
selection menu and demonstrate random selection for both players. Next 30 
seconds, display both teams and explain how characters are stored in FIFO order.
Then from 1:30 - 2:15, we will demonstrate the attack and defense system,
showing how damage is dealt. With the rest of the time, we will finish the
battle, demonstrate how the character switches next in line once a character
dies, and showcase any bad inputs.

| Class | Team Member | Contribution |
|---|---|---|
| CharacterTest.java | Ayomide | Created and implemented |
| LinkedQueueTest.java | Ayomide | Created and implemented |
| Node.java | Ayomide | Created and implemented |
| PlayerTest.java | Kevin | Created and implemented |
| CharacterCatalogTest.java | Aditi | Created and implemented |
| BattleManagerTest.java | Aditi | Created and implemented |
| Stretch Goal | Aditi | Created and implemented |

Generative Ai was used to help implement and organize all of our test cases.
Also, it helped with figuring out what changes we need to make to implement
our stretch goal. Finally, we used AI to help with errors that the program is
showing and fixing the settings of the project because many of our tests 
wouldn't run.

































