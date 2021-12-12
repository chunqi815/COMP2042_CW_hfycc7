# Brick Destroy Game

This is a simple arcade game. The aim is to destroy all the walls with a ball. Press Spacebar to play/pause and start. To move paddle, press A to move left and press D to move right. To open console, press SHIFT+ALT+F1.

### Project Description
-Maintaining and extending a re-implementation of Brick Destroy Game
-Convert Swing to JavaFX

### Maintainence 
-Converted Swing to JavaFX and implementing MVC at the same time by seperating GUI (view) from the controllers.
-Redesigned GUI using SceneBuilder for a smoother look and linking it to its individual controllers. 
-Added Background Image by linking to .FXML(view) using .CSS.
-Simplified Brick,Ball and Player class codes.
-Encapsulated ball, brick, player Class 
	-Moved collision with scene,brick and paddle codes from wall class to ball class.
	-Moved collision with bottom code from wall class to gameController class.
-Simplified brick child classes (CementBrick,ClayBrick,SteelBrick class)
	-Only has one function that sets brick properties and brick strength.
-Simplified brick class
	-Moved makeChessBoardType and makeSingleType function from wall class and deleted wall class.
	-Modified previous code to match JavaFX
-Changed player class and ball class codes to match JavaFX
-Implemented Console modal using JavaFX
	-Changed codes to match JavaFX

### Changes
-Set Ball speed to 1 frame per 20 Miliseconds.
-Added label to display current score during gameplay.	

### Addition
-Added Penalty and Rewards
	- Score +10 for clearing level
	- Score -10 for losing one ball count
-Implemented permanent highscore
	- High score displayed at game over page.
-Added Extra Levels
	-Added level that contains all cement
	-Added level that contains all steel that runs endless and speeds up.

