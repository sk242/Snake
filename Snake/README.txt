=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: sklein24
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. The first core feature is collections. For my snake's body, I used a linkedlist. This made
  the most sense to me since I only needed access to the tail and head quickly. The tail I used for
  potion movement and the head of the list had to synchronize with the snake's head (it is in red). 
  When I was accessing any part of the snake's body, I always did it through iteration since there
  was never a situation where I would only move an internal segment of the body.

  2. The second core concept was inheritance/subtyping. I used this for my consumables, which was 
  an anonymous class extended from GameObj. Consumable was different from the snake portions that 
  extended GameObj because they were uniform in size and had unique ways to spawn. The apple 
  required me to come up with a random position generator that would move it around without it
  intersecting the snake's body on the grid. In contrast, spawn for the potion considered the size 
  of the snake, as when it had a size of 15 it would show up. Further, while apple simply shifted 
  after intersecting the snake head, the potion tracked the snake's tail, dangling in order to 
  create the idea of a "chase" or riskier reward. This could not have been done with a variable 
  since it required taking into account the situation, snake, and not just direction. 

  3. The third core concept was testing, which I used for the gameCourt as well as random position 
  generator. The random position generator took into account edge cases such as receiving null, as
  well as testing that there was no intersection with the snake's body. For the game, I tested 
  various collisions to make sure they were registered and operated properly. One edge case for
  example was visible versus invisible potions. When a potion was invisble, it should not have 
  affected the snake's size, even if the snake was intersecting it. By differentiating the scenarios
  in my GameCourt, I was able to allow for unit testing. 

  4. The fourth core concept was File IO. I used this for a high score keeper that demonstrated 
  persistance. I used buffered readers when reading from the document to extract data, and then 
  wrote to the document names and scores. They were differentiated by colons instead of commas and 
  I used try catches to handle IOExceptions. The scores are also sorted through my arrayList and 
  object arrays.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  	Apple: This is a consumable that snakes eat to grow bigger. There was only one apple in my game
  	and each time it intersected the snake, it moved. 
  	Consumable: This is an abstract class that extended GameObj to standardize sizes and establish 
  	the spawn function.
  	Game: This implements runnable and shows the frame/widgets of the GUI.
  	GameCourt: This established the interactions between different game objects and also contained
  	my high score implementation. 
  	GameObj: Similar to the starter code. The biggest changes were I didn't use bouncing and that I 
  	changed the intersections to test if intersection occurred rather than if it would occur. 
  	Potion: Another consumable. The spawn function here causes it to attach to a snake's tail if the 
  	snake is large enough. 
  	RandomPositionGenerator: Took advantage of Java's random number generating functions and took 
  	into account the position of a snake to prevent overlap. 
  	SnakeBody: This is the individual body pieces of the snake. It extended gameObj and was part of 
  	a collection later on.
  	SnakeHead: Extended gameObj. It was the head of the snake.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
	The process of coding the game felt like repeatedly taking two steps forward and one step back.
	It took me a really long time to figure out how to get the random positions to not overlap and
	also how to adjust the snake body without causing the head of the body list to overlap with the 
	head. I also was really frustrated with the scoreboard, since it kept updating on me with the 
	top option just slowly permeating down the list or not saving between games. There was also just
	a lot of school work that prevented me from making my mask game :( 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
	I definitely think my code was redundant in places. For example, with the scoreboard I 
	have two buffered readers, one for reading, and one for initializing that isn't necessary.
	I also don't know if I should have had a separate snake head option. It made sense in the moment
	because it took in mouse keys and could be drawn differently but it make iterating through the 
	linkedlist clunky. I think I did separate functionality however. I made mutiple classes and 
	methods that allowed for cross-applications. However, I also did find testing difficult just
	because of the tick() so I might try to refactor around that if given the opportunity to. Edit:
	I 100% wish I could refactor the initHighScore section because it could be much more modular
	if I wanted more entries I think.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
  Potion: https://www.google.com/url?sa=i&url=https%3A%2F%2Fcoincost.net%2Fen%2Fcurrency%2Fsmall-
  love-potion&psig=AOvVaw2pKPlIwrxyRRunLXxJEXYZ&ust=1607717409566000&source=images&cd=vfe&ved=0CAIQ
  jRxqFwoTCLCHqZ6cxO0CFQAAAAAdAAAAABAD
  Apple:https://www.google.com/url?sa=i&url=http%3A%2F%2Fwww.downloadclipart.net%2Fbrowse%2F61084%2
  Fcartoon-red-apple-clipart&psig=AOvVaw0EVAVq6bDs7We7tdy22pzG&ust=1607743482412000&source=images&c
  d=vfe&ved=0CAIQjRxqFwoTCIiagbD9xO0CFQAAAAAdAAAAABAD
  I also played the snake game on google
========================
=: Diary :=
========================
12/4 7PM 
	Today I'm starting the project. I've made a very rough layout and am planning to implement
	the board and the sprite class. I think the board will need updates throughout, but the sprite
	class will be really helpful since everything else is just a subclass of it. 

12/10 
	I completely forgot to keep track of this. It was a really enjoyable project but also just 
	really frustrating trying to debug. 
	I didn't think I'd be able to finish the high score feature but it's done!
	