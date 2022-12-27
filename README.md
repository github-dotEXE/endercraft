# endercraft
Minecraft spigot utility plugin "Endercraft"
<pre>
Versions:
  Tested up to: 1.19.2
  Tested from: 1.15

!!! Dependencies !!!
Meins Core: https://github.com/github-dotEXE/meins_core/

Commands and Permissions:
  exampleCMD:
    /CMD [args here] : permission
      description for cmd here
    
  heal:
    /heal : endercraft.heal
      heals your hearts and your health
    
  sudo:
    /sudo [Player] [Command...] : EnderCraft.sudo
      executes the [Command...] as [Player]
    
  flyspeed:
    /flyspeed [1-100] : EnderCraft.flySpeed
      changes the speed of flying in range 1 to 100
      10 is the default
    
  fly:
    /fly : EnderCraft.fly
      makes the command executor able to fly nomatter the gamemode
      sideeffect: player doesn't take falldamage
    
  godmode:
    /godmode : EnderCraft.godmode
      makes the command executor invurnable to any damages
      neither will mobs try to attack
    
  ec:
    /ec : endercraft.ec
      opens enderchest of command executor for command executor
    /ec [Player] : endercraft.Super_ec
      opens enderchest of [Player] for command executor
    
  craft:
    /craft : endercraft.craft
      opens crafting table for command executor
    
  invsee:
    /invsee [Player] : EnderCraft.invsee
      opens inventory of [Player] for command executor
    
  vanish: : EnderCraft.vanish
    /vanish
      makes command executor invisible 
      removes command executor from tab list
    /vanish []
      makes command executor invisible 
      removes command executor from tab list
      sends a leave/join message in chat
    
  chat:
    /chat [Message]
      sends [Message] in chat as if it was coming from the command executor
      (this is useless and was used for sudo cmd)
      (this will get removed and will be replaced with a implementation of writing messages with the sudo cmd)
      
  enchantitem:
    enchantitem [enchantment] [level]! : EnderCraft.enchantitem
      enchants the item the command executors main hand with [enchantment] at level [level] 
      
  position: : endercraft.position
    /position set [name]
      sets a new position called [name]
    /position get [name]
      points the head of command executor to coordinates of position [name]
    /position del [name]
      removes the position [name]
    /position list
      lists all positions made yet
    /position pos [x] [y] [z]
      points the head of command executor to coordinates
    (positions are public across the server)
    
  setspawn:
    /setspawn : EnderCraft.setspawn
      sets a spawn at position of command executor which can be used for /spawn
      once a spawn is set it will teleport players to it everytime they log on
      (the teleport on log on will be changeable in the future)
      
  delspawn:
    /delspawn : EnderCraft.delspawn
      deletes the spawn
      
  spawn:
    /spawn : EnderCraft.spawn
      teleports the command executor to spawn
    
  realtime:
    /realtime : EnderCraft.realtime
      takes the system time and converts it to minecraft time
      (accurate to some degree but might be changed in the future to get real sunrise and sunset information)
    
  rename:
    /rename [Name...] : EnderCraft.rename
      renames the item in the main hand of the command executor to [Name...]
      & will be converted to § meaning you can use color codes
      
  itemlock:
    /itemlock [Code] : EnderCraft.itemlock
      "locks" an item for use in item sorters
      changes the lore of an item to: command executor name + [code]
      (this means noone can make this item again other than the creator (bc of the playername in lore))
      (practical for currency items or tokens)
      
  repair:
    /repair : EnderCraft.repair
      repairs the item in the mainhand of the command executor
    
  floatsign:
    /floatsign [Color] [TEXT..] : EnderCraft.floatsign
      creates an invisible armorstand at command executor with a visible name named: §[Color] + [TEXT..]
   
   
Disambiguation:
  [xxx] ([] with anything inside) = variable
  [xxx...] ([] with anything and dots inside) = variable which extends to more than the 1 arg its on
  [] (empty []) = anything does the job
  : xxx (: with anything behind it) == the permission needed for the command
</pre>
