# Towers

Playing around at writing a tower defence game. Trying a slightly new concept of having modular towers, called bunkers. A bunker can have different modules installed to alter its capabilities. Currently bunkers support a tracking device and a weapon.

Tracking devices handle target selection and relay targets to the weapon, which fires at them.

![Screenshot](https://github.com/qwerky/Towers/raw/master/screenshot.png)

##### 25 April 2013

Got a reasonably good start with levels, creeps, bunkers and stuff. UI is basic but fine for now.

##### 26 April 2013

 - Added creep health and projectile damage
 - Killing creeps now gives money
 - Added cruise launcher which fires really cool seeking missiles!

##### 29 April 2013

 - Ability to build bunkers

##### 30 April 2013

 - Double buffered display
 - Added tech tree and ability to upgrade the weapons and tracking system in a bunker

##### 3 May 2013

 - Health bars for creeps
 - New medium range radar tracker
 - Descriptions for arms and trackers
 
##### 7 May 2013

 - Added Vector2D class to handle position and movement of things
 - Create Light MG arms which fires in bursts and leads targets

##### 8 May 2013

 - Further fleshed out out tech tree

##### 10 May 2013

 - Added more creeps and waves
 - Added new nearest to base tracker
 - Added sell button for bunkers
 - Added help dialog

### TODO

 - Flesh out tech tree with a few more trackers and weapons
 - Better UI - more stuff in the panels
 - Ammo storage module
  - weapons consume ammo from storage
  - when storage is empty long cooldown to reload
 - Multiple levels
 - Better art
 - Sounds
 - Help page details
