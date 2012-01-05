#!/usr/bin/env python
# encoding: utf-8
"""
untitled.py

Created by Sebastian Österlund on 2011-10-30.
Copyright (c) 2011 Irlplanner.com. All rights reserved.
"""

import sys
import os
import math
import random
endx = 17
endy = 18

startx = 0
starty = 0
node_list=[]
boardsize = 20
def generateObstacle(x,y):
    b = 0
    c = random.random()
    if y > 0 and x > 0:
        if x == endx and y == endy:
            b = 0
        elif board[y-1][x]:
            b = 1
        elif random.random()>0.9:
            b = 1

#		elif board[y][x-1]:
#			b = 1
        if b == 1 and random.random()>0.3:
            return 1
        else:
            return 0
    elif y==0 and c >0.6:
        return 1
    else:
        return 0

board = []
for row in range(0,boardsize):
	board.append([])
	for col in range(0,boardsize):
		board[row].append(generateObstacle(col, row))
for row in board:
	print row

class Node():
    def __init__(self, x,y,parent=None,g=0):
        self.x = x
        self.y = y
        self.g = g
        self.parent = parent
        self.h = self.distance()
        self.f = self.g + self.h
        self.closed = 0
        self.open = 0
    def distance(self):
        x = abs(self.x - endx)
        y = abs(self.y - endy)
        return math.sqrt(x**2+y**2)
        
    def getNodeByCoordinate(self, xy):
        prev = None
        global node_list
        for node in node_list:
            if node.x == xy[0] and node.y == xy[1]:
                prev = node
                break
        if prev:
            return prev
        else: 
            return None
    def neighbors(self):
        
        neighbors = [(self.x+1, self.y),(self.x-1,self.y),(self.x,self.y+1),(self.x,self.y-1)]
        nodes = []
        for neighbor in neighbors:
            if neighbor[0] not in range(0,boardsize):
                pass
            elif neighbor[1] not in range(0,boardsize):
                pass
            elif board[neighbor[1]][neighbor[0]] == 1:
                print "Wall!"
            else:
                node = self.getNodeByCoordinate(neighbor)
                if  node == None:
                    node = Node(neighbor[0], neighbor[1],self,self.g+1)
                    global node_list
                    node_list.append(node)
                nodes.append(node)
                 
        return nodes
        
def getLowestNode(nodes):
    node = sorted(nodes, key=lambda no: no.f)
    print "Lowest: ", node[0].x, ", ", node[0].y, 
    return node[0]
def getWay(node):
    way = []
    way.append(node)
    p = node.parent
    while p != None:
        way.append(p)
        p = p.parent
    return way[::-1]
    
def Astar():
    
    open_list=[]
    closed_list=[]
    start = Node(startx, starty)
    open_list.append(start)
    while len(open_list)>0:
        node = getLowestNode(open_list)
        if node.x == endx and node.y == endy:
            print "Goal"
            way = getWay(node)
            return way
            break
        open_list.remove(node)
        closed_list.append(node)
        print "Checking neighbors"
        for neighbor in node.neighbors():
            if neighbor in closed_list:
                continue
            tentative_g = node.g + 1
            tentative_is_better = False
            
            if neighbor not in open_list:
                print "appending neighbor"
                open_list.append(neighbor)
                tentative_is_better = True
            elif tentative_g < neighbor.g:
                tentative_is_better = True
            
            if tentative_is_better == True:
                neighbor.g = tentative_g
                neighbor.parent = node
                neighbor.h = neighbor.distance()
    else:
        return False
#way = Astar()

#for point in way:
#    print "(", point.x, ", ", point.y, ")"
#    board[point.y][point.x] = 2
#for row in board:
#    print row

class AStar:
    def __init__(self,size, start, end):
        self.boardsize = size
        global boardsize
        global board
        boardsize = self.boardsize
        global startx
        global starty
        global endx
        global endy
        startx = start[0]
        starty=start[1]
        endx=end[0]
        endy=end[1]
        board = []
        for row in range(0,boardsize):
            board.append([])
            for col in range(0,boardsize):
                board[row].append(generateObstacle(col, row))


    def getBoard(self):
        way = Astar()
        print "-------"
        for point in way:
            print "(", point.x, ", ", point.y, ")"
            board[point.y][point.x] = 2
        #for row in board:
        #    print row
        return board
