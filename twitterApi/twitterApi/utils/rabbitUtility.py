from twitterApi.model.tweet import Tweet
from twitterApi.service.service import Service
import json
import logging

class RabbitUtility:

    def callback(ch, method, properties, body):
        request_body = json.loads(body.decode('utf-8'))
        tweet = Tweet(**request_body)
        try:
            Service.send_tweet(tweet=tweet)
            logging.info("Send tweet succesfull")
        except Exception as e :
            logging.warning("Send tweet error" + str(e))
