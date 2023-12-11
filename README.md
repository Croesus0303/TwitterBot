# TwitterBot(@Mevstige)
Twitter bot with producer and consumer. This bot tweets trending news during the day for specified categories at specified times of each day.

# Java Trends Service(Producer)
The purpose of this service is to pull trending news according to categories. At the same time, it converts the captured news into a specific format and push it to RabbitMQ. You can deliver news in any category with this service.

# Python Twitter Service(Consumer)
The purpose of this service is to send tweets. This service is connected to the bot account we want to use. With this service you can send tweets anytime we want. Also this service listens to RabbitMQ message queue. If there is a new message pushed by from Java Trends Service. Our consumer takes the message and sends the tweet.

# RabbitMQ

We have a RabbitMQ server. This server provides communication between Java Trends Service and Python Twitter Service. This server gets messages from Trends Service. Also this server listening by Python Twitter Service. Python Twitter Service triggers when new messages arrived to RabbitMQ.

# Scheduler

We created jobs for Java Trends Service calls. We added calls at certain hours.

# Architecture

<img width="657" alt="Ekran Resmi 2023-11-06 22 08 00" src="https://github.com/Croesus0303/TwitterBot/assets/45857730/11af2191-4e3d-428c-9e06-ade91776c68c">
