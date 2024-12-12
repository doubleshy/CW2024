COMP2042 Coursework

1. GitHub Repository:

2. Run the Project
In the src folder, find the Main class under the com.example.demo.controller package.
Run the main method in the Main class.
Maven will build the project and the application will start running.

3. Implemented and working features

3.1 Bug fixes
Fixed the issue of entering level 2: changed the shield image format from .jpg to .png, and added timeline.stop() when the level is completed.
Ensure the shield follows the level 2 boss: track the boss's position through getLayoutX() + getTranslateX() and getLayoutY() + getTranslateY(), and use setLayoutX() and setLayoutY() to update the shield position.

3.2 General features
User aircraft movement: supports horizontal and vertical movement.
Menu system:
Main menu: includes start game, exit options.
Loading page: insert loading page between levels to hide delay.
End page: Pop up a page after the level ends to provide exit function.

3.3 Specific level functions
Level 1: Display kill count.
Level 2: Display Boss's health bar.
Level 3: Display kill count and pet help.

4. Unimplemented functions
Randomly generated boost items (increase health, speed or projectile damage).
Complex movement patterns of enemy aircraft (such as zigzag, circular movement).
Level selection page.
Game difficulty settings (such as enemy aircraft speed, projectile speed and user health).

5. Newly added Java
LevelThree                     implements the survival mode level of level 3.
SecondEnemyPlane        implements the second enemy.
TransitionScreen.java      implements the buffer between levels.
EndMenu.java                 implements the end page.
KillCountDisplay.java       implements the display of the number of kills.
PetPower.java                 implements the function of pet help.
BossHealthDisplay.java    implements the display of Boss health.

6. Modified Java classes

UserPlane.java:
Added horizontal bounds constants X_UPPER_BOUND and X_LOWER_BOUND.
Updated updatePosition() method to limit horizontal movement range.
Added horizontal movement methods moveLeft() and moveRight() and stop method stopHorizontally().

ShieldImage.java:
Changes Made:
Safe Image Loading:
Implemented a resource check for the shield image to prevent crashes if the resource is unavailable.
Prints a warning message to the console if the image resource cannot be located.
Initial Position Setting:
Shield image is positioned based on initial and passed during object construction.xPositionyPosition
Purpose:
To provide a reusable and dynamic visual effect representing a temporary invulnerability state.
To enhance visual feedback for players when a shield power-up is activated or deactivated.
To ensure robust image loading and error handling for smoother gameplay experience.

Main.java:
Added initializeMainMenu() method to initialize the main menu scene and set the stage's scene to the main menu.
Added startLevelOne() method for a button to start Level One and set the stage's scene to Level One.

TransitionScreen.java
use TransitionScreen()来添加了一个关卡之间的缓冲，使关卡之间的衔接更加流畅。

Boss.java
Custom Shield
ntroduced a shield mechanism using activateShield(),isShielded and deactivateShield() methods.
The shield can be activated based on a random probability and deactivates after a set duration (MAX_FRAMES_WITH_SHIELD).
Health display:
Added a instance to display the boss’s health visually 
The health bar dynamically updates its position and size based on the boss's health percentage.
Purpose:
To introduce a challenging and dynamic boss entity with unique mechanics such as a temporary shield, randomized movement, and health tracking.
To improve gameplay depth and visual feedback by integrating a health bar and shield effects.

controller.java
Dynamic Level Transition Using Reflection:
Introduced the method to dynamically load levels by their fully qualified class names using reflection.goToLevel(String className)
Ensured that levels are instantiated using their constructors, with height and width passed dynamically from the .Stage
Level Initialization:
Used to set up the scene for the current level.LevelParent.initializeScene()
Passed the object dynamically to to ensure proper stage management for each level.StageLevelParent.startGame()
Purpose:
To enable flexible and dynamic level transitions without hardcoding specific levels, ensuring scalability.

LevelOne.java
Level-Specific Initialization:
Set up a unique background image () and initial player health ().BACKGROUND_IMAGE_NAMEPLAYER_INITIAL_HEALTH
Kill-Based Advancement:
Introduced a kill target () that the player must achieve to progress to the next level.KILLS_TO_ADVANCE
Added and methods to manage level transitions.isKillTargetReached()handleLevelAdvance()
Enemy Spawning Logic:
Added enemy spawn control based on and the current number of enemies.ENEMY_SPAWN_PROBABILITY
Implemented methods like and to handle spawning dynamically.shouldSpawnEnemy()spawnEnemy()
Game Over Management:
Enhanced to handle both game loss and advancement scenarios.checkIfGameOver()
Dynamic UI Integration:
Instantiated a to manage health display and other UI elements specific to this level.LevelView
Purpose:
To establish the foundational gameplay elements for the first level, including a kill-based progression system and dynamic enemy spawning.
To introduce level-specific visuals and audio for an immersive experience.
To manage the player’s health and transition states (win or loss) effectively.

LevelTwo.java
Changes Made:
Boss-Focused Gameplay:
Added a instance as the primary enemy for this level.Boss
Boss Health Bar and Shield Integration:
Added methods to display the boss's health bar and shield image on the scene.
Game Over Management:
Implemented to handle win (boss defeat) and lose (user destroyed) scenarios.checkIfGameOver()
Purpose:
To provide a focused and intense boss battle as the core challenge of Level Two.
To integrate the boss’s unique mechanics (health bar, shield) into the gameplay.
To enhance immersion with level-specific music and UI elements tailored for a boss encounter.

LevelParent.java
Integration of Managers:
Centralized management and integration of various game components through the following managers:
ActorManager: Handles the addition, removal, and updates of actors such as enemies, projectiles, and power-ups.
GameLoopManager: Manages the game loop, ensuring the game state updates at fixed intervals.
GameStateManager: Manages transitions between different game states, such as playing, paused, and game over.
CollisionManager: Detects and handles collisions between game entities like projectiles and planes.
SceneManager: Manages the scene graph, including UI elements, background, and transitions between levels.
EnemyManager: Spawns enemies dynamically and controls their behaviors such as movement and firing.
EventHandler: Handles game events, including wins, losses, and other state transitions.
Purpose:
To provide a robust and reusable foundation for all levels in the game.
To centralize and modularize key functionalities like actor management, game state updates, and UI integration.
To improve scalability and ease of adding new levels by abstracting level-specific behaviors into child classes.
