# Dungeon-Crawling Console Game

### This game framework allows to interactively navigate through the dungeon (room by room) by typing move direction (n, s, e or w)
### It displays room name you are in, possible move directions and dungeon map and indication of where you are in the dungeon

### Some features:
- Dungeon map is defined in input file.
- Max map size is 20*20 rooms
- Each line specifies room name and connections to other rooms: `a3 n:a1 s:a0 w:a4`
- a0 to b6 are room names (they can be any 2-character names for simplicity)
- n, s, e, w are doors leading to next rooms located north, south, east or west from current room.
- It also allows entering multiple directions at a time: 'nnese' means you are making list of moves n, n, e, s, e

![example.png](example.png)
