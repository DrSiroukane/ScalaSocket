# IRC
## Connect to server IRC:
- To connect to IRC server we will need server address and its port too.
- To stay connected we need to resend each time we received a message "^PING.*" the same message replacing "PING" by "PONG".

## IRC syntax:
### Register:
```
USER username current_address('localhost') host_address alternative
NICK nickname
```
### Join/Create channel:
If the channel exist you will just join it
Else you will create on and you will be the admin (has right to kick users)
```
JOIN #channel
```
### Send Public Message:
```
PRIVMSG #channel : message
```
### Send Private Message:
```
PRIVMSG nickname : message
```


