import configparser
import threading
from twitterApi.utils.rabbitUtility import RabbitUtility

import pika


config = configparser.ConfigParser(interpolation=None)
config.read('config.ini')

class RabbitMqConsumer():

    exchange_name = 'TrendObject'

def start_consumer():
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
    channel.start_consuming()


consumer_thread = threading.Thread(target=start_consumer)
consumer_thread.start()