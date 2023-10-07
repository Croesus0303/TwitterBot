import json
import logging

import configparser
import threading

import pika

from twitterApi.service.service import Service
from rest_framework.decorators import api_view
from rest_framework.response import Response

from twitterApi.model.tweet import Tweet
from twitterApi.utils.rabbitUtility import RabbitUtility

config = configparser.ConfigParser(interpolation=None)
config.read('config.ini')

username = config["rabbit"]["username"]
password = config["rabbit"]["password"]
host = config["rabbit"]["host"]
port = int(config["rabbit"]["port"])

# Create a connection to the RabbitMQ server
credentials = pika.PlainCredentials(username, password)
parameters = pika.ConnectionParameters(host=host, port=port, credentials=credentials, heartbeat=0)
connection = pika.BlockingConnection(parameters)
channel = connection.channel()

# Declare a queue to consume messages from
queue_name = 'TrendObject'
channel.queue_declare(queue=queue_name)

# Set up the consumer with the callback function
channel.basic_consume(queue=queue_name, on_message_callback=RabbitUtility.callback, auto_ack=True)

print('Waiting for messages. To exit, press Ctrl+C')

# Start consuming messages
consumer_thread = threading.Thread(target=channel.start_consuming())
consumer_thread.start()
@api_view(['GET', 'POST', 'DELETE'])
def send_tweet_service(request):
    if request.method == 'POST':
        request_body = json.loads(request.body.decode('utf-8'))
        tweet = Tweet(**request_body)
        try:
            Service.send_tweet(tweet=tweet)
            logging.info("Send tweet succesfull")
        except Exception as e:
            logging.warning("Send tweet error" + str(e))
        return Response("Success")









