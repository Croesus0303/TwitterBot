from twitterApi.constants.constants import Constants
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
            logging.info(Constants.LoggingMessages.createTweetSuccessMessage)
        except Exception as e :
            logging.warning(Constants.LoggingMessages.createTweetUnSuccessMessage + str(e))
