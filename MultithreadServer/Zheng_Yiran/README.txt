Yiran Zheng
zhengyr@brandeis.edu
CS 131

All submitted work is mine.

The main idea of the master server is that it contains many basic server. Every time when a client is trying to connect to the master server, it is being assigned to a basic server according to its rate. 
In order to assuring multithread without race condition, we need to synchronized on the basic server of each server when a client is trying to connect to the server. If there are multithread using the basic server, then they need to go grab the lock of basic server one  by one. 
