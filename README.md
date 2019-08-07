# Gmecord

Gmecord is a simple Discord bot/Spark webserver that connects a Discord channel and a Groupme chat. 

## What's working
* The following Groupme attachment types will be parsed to Discord:
	* Images
	* Locations

* Mentioning Discord users. (case-sensitive)
* Mentioning Groupme users. (case-sensitive)

## What isn't
Everything should be mostly functional.

## Configuration
The configuration of the bot must be in the same directory as the jar file, and must be called `Config.json`. A file will automatically be created with all the configuration settings if the file does not exist. An explanation of configuration is as follows:
* `discordToken`: The token of your Discord bot.
* `groupMeToken`: Your personal Groupme token. This is needed to let Discord mention Groupme users.
* `botID`: The ID of the Groupme bot. Allows for sending messages to Groupme.
* `channel`: The Discord channel we should listen for messages on.
* `webAuthenticationEnabled`: If you would like basic HTTP authentication, enable it here. It's recommended you set up a reverse proxy (nginx or Apache fit this bill fine) and do authentication there.
* `username`: The username for web authentication.
* `password`: The password for web authentication.
* `botName`: The name of the bot on Groupme, so messages sent from Discord aren't sent back.
* `groupID`: The ID of the Groupme group. This is needed to let Discord mention Groupme users.

## Builds
Builds should be posted to [Jenkins.](https://jenkins.banditoz.io/job/gmecord/)
