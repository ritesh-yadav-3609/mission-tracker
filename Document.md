
# SHIELD Application

## Problem statement
Captain Fury has requested to make an application for the S.H.I.E.L.D to track the 
missions assigned to each avenger. Currently there are 6 avengers in the team, Iron 
Man, Captain America, Hulk, Thor, Black Widow and Hawkeye. With time more 
super heroes can be added in the team. We need to create an application which can 
track the missions status assigned to each avenger and can also assign a new 
mission to the avengers. As of now Captain Fury can assign maximum two 
missions to any avenger at any point of time, Captain can also update the mission 
status. Avengers can check the mission details assigned to other avengers but 
cannot assign a new task, but for now we will create an app for the captain fury 
only. But we need to keep in mind that at some point avengers will also be able to 
access the app with few less privileges.


## Aim:
> To learn all concepts of spring in details.


## Features
```
Created and updated audit
Exception handling
	Auauthorized
	Access denined
	Value not found
	Mission greater than 2
	If mission completed
	Notification contact
	404 
	global
Jwt authentication
Basic authentication
Validation
Pojos
Json view
Enums with converter
Authorization
MappedSuperclass
Inheritance
Relationship
	One to one
	Many to one
	One to Many
Indexes
Constraint
lombok depedency
```


# API Guide

## Fury

### First to create fury as a admin call api

> Method: GET

> URL: http://localhost:8080/fury

### Get JWT token for authentication

> Method: POST

> URL: http://localhost:8080/login

> Body: 

```
{
    "username":"fury",
    "password":"fury"
}
```


## Avenger

### Add new avenger

> Method: POST

> URL: http://localhost:8080/avenger

> Authentication: Bearer token

> Body: 

```
{
    "password":"password",
    "username":"username",
    "notifiction":{
        "type":"notification_type", // "SMS", "EMAIL", "PAGER"
        "contact":"contact_detail"
    }
}
```

### List avengers

> Method: GET

> URL: http://localhost:8080/avenger

> Authentication: Bearer token

> Parameter: (Optional)

```
status=value	// "FREE", "PARTIAL", "BUSY"
avenger=value
```

### Update avenger

> Method: PUT

> URL: http://localhost:8080/avenger/{avenger_name}

> Authentication: Bearer token

> Body: 

```
{
    "password":"password",
    "username":"username",
    "notifiction":{
        "type":"notification_type", // "SMS", "EMAIL", "PAGER"
        "contact":"contact_detail"
    }
}
```

### Get avenger status

> Method: GET

> URL: http://localhost:8080/avenger/status

> Authentication: Bearer token

> Parameter:

```
avenger=value
```

### Get avenger notification details

> Method: GET

> URL: http://localhost:8080/avenger/notification

> Authentication: Bearer token

> Parameter:

```
avenger=value
```

### Update avenger notification details

> Method: POST

> URL: http://localhost:8080/avenger/notification

> Authentication: Bearer token

> Body: 

```
{
    "avenger":"Avenger name",
    "type":"Notification type",	// "SMS", "EMAIL", "PAGER"
    "contact":"contact detail"
}
```


## Privilege

### Assign privilege to member

> Method: POST

> URL: http://localhost:8080/member/privilege

> Authentication: Bearer token

> Body: 

```
{
    "privilege":"privilege_name",
    "avenger":"Avenger name"
}
```

### Get member privilege list

> Method: GET

> URL: http://localhost:8080/member/privilege

> Authentication: Bearer token

> Parameter:

```
member=value
```

### Remove member privilege

> Method: DELETE

> URL: http://localhost:8080/member/privilege/{privilege_id}

> Authentication: Bearer token


## Mission

### List mission

> Method: GET

> URL: http://localhost:8080/mission

> Authentication: Bearer token

> Parameter: (Optional)

```
status=value	// "ASSIGN", "COMPLETED"
avenger=value
```

### Add new mission

> Method: POST

> URL: http://localhost:8080/mission

> Authentication: Bearer token

> Body: 

```
{
    "name":"Mission name",
    "description":"Mission description",
    "avenger":"Avenger name"
}
```

### Update mission

> Method: PUT

> URL: http://localhost:8080/mission/{mission_id}

> Authentication: Bearer token

> Body: 

```
{
    "name":"Mission name",
    "description":"Mission description",
    "avenger":"Avenger name"
}
```

### Update mission status

> Method: POST

> URL: http://localhost:8080/mission/status

> Authentication: Bearer token

> Body: 

```
{
    "id":mission_id,
    "status":"COMPLETED"
}
```


